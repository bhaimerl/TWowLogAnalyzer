package helper.classes.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Seconds;

import helper.Raids.Boss;
import helper.Raids.RaidBossMapping;
import helper.classes.utils.besonderes.NightFallUtils;

public class BossUtils {
	
    public static HashMap<String, Boss> bossMap = new HashMap<>(); 
	

    public static void calculateBossStats(ArrayList<String> completeLog, ArrayList<String> bossesFromLog) {
    	for (String currentBoss : bossesFromLog) {
    		getBossStats(completeLog, currentBoss, "");
    	}
    }
    
    public static ArrayList<String> identifyEngageTimesAfterWipeOrReset(String bossName, ArrayList<String> bossLogs) {
    	ArrayList<String> fightEndTime = new ArrayList<>();
    	//skip
    	if(bossName.equals(RaidBossMapping.gothik)) {
    		return fightEndTime;
    	}
    	//startTimes.add(firstTimeHt);
    	DateTime lastTimeFromLogAsSDF = null;
    	for (String string : bossLogs) {
    		if(lastTimeFromLogAsSDF==null) {
        		lastTimeFromLogAsSDF = General.getTimeFromLogAsDateTime(string);
        		continue;
    		}    		
    		if(lastTimeFromLogAsSDF!=null) {
        		DateTime currentDateTime = General.getTimeFromLogAsDateTime(string);
        		if(true) {
            		int distanceSeconds = Math.abs(Seconds.secondsBetween(currentDateTime, lastTimeFromLogAsSDF).getSeconds());
            		if(distanceSeconds>=50) {
            			fightEndTime.add(General.getStringFromDateTime(lastTimeFromLogAsSDF));
            		}
        		}
        		lastTimeFromLogAsSDF = currentDateTime;
    		}
		}
    	return fightEndTime;
    }

    
	private static void getBossStats(ArrayList<String> completeLog, String bossName, String suffix) { 
		//System.out.println("Call for boss "+bossName);
		ArrayList<String> bossLogs = General.getLogsFromBossByName(bossName, completeLog);
		ArrayList<String> identifyEngageTimesAfterWipeOrReset = identifyEngageTimesAfterWipeOrReset(bossName, bossLogs);
		//wipe oder reset, dann erzeuge neuen bossEintrag
		if(identifyEngageTimesAfterWipeOrReset.size()>0) {
			//haben wir einen eintrag, dann geht das erste log nur bis zu diesem eintrag und wir haben ein weiteres, was ab diesem eintrag beginnt,
			//so können wir wipes resets erkennen und mehrere eintraege erzeugen boss_1, boss_2 usw
			int i=1;
			for (String string : identifyEngageTimesAfterWipeOrReset) {
				//fuer die rekursion begrenztes log bis zur endzeit aus der liste
				ArrayList<String> shortedCompleteLog = General.getLogsUntilPLusTolerance(General.getDateTimeFromString(string), 0, completeLog);
				getBossStats(shortedCompleteLog, bossName, "("+i+++")");
				//fuer den aktuellen weiterlauf alle logs ab diesem zeitpunkt + 100ms
				completeLog = General.getLogsBeginningPLusTolerance(General.getDateTimeFromString(string), 100, completeLog);
				bossLogs = General.getLogsFromBossByName(bossName, completeLog);
			}
		}
		Boss boss = new Boss();
		boss.setName(bossName+suffix);
		HashMap<String, Integer> playerParryCount = new HashMap<>(); 
		HashMap<String, String> playerDeathCause = new HashMap<>();
		//Nightfall
		NightFallUtils.calculateNightfall(bossLogs, boss);
		
		ArrayList<String> sunderlogs = General.getOnlySunderLogs(bossLogs);
		for (int i=0;i<sunderlogs.size();i++) {
			String string = sunderlogs.get(i);
			if(string.contains("casts Sunder Armor")) {
				String playerWhoSundered = General.getPlayerName(string);
				if(!boss.getHelpedToSunderUntil5().contains(playerWhoSundered)) {
					boss.setHelpedToSunderUntil5((boss.getHelpedToSunderUntil5()+" "+General.getPlayerName(string)).trim()+"<br>");
				}
			}
			if(string.contains("is afflicted by Sunder Armor (5).") || string.contains("gains Sunder Armor (5).")) {
				//sunderLogsAppliersUntil5.add(string);
				break;
			}
		}
		for (int i=0;i<sunderlogs.size();i++) {
			String string = sunderlogs.get(i);
			if(string.contains("is afflicted by Sunder Armor (") || string.contains("gains Sunder Armor (")) {
				boss.setSunderTimes((boss.getSunderTimes()+" "+General.getEntryAtPosition(string, 1)).trim()+"<br>");
			}
			if(string.contains("is afflicted by Sunder Armor (5).") || string.contains("gains Sunder Armor (5).")) {
				//sunderLogsAppliersUntil5.add(string);
				boss.setName(bossName+suffix);
				break;
			}
		}
		
		
		//faerie fire
		for (String string : bossLogs) {
			if(string.contains("is afflicted by Faerie Fire ") || string.contains("gains Faerie Fire ")) {
				boss.setFaerieFireApplied((boss.getFaerieFireApplied()+" "+General.getEntryAtPosition(string, 1).substring(0,8)).trim()+"<br>");
			}
			
		}
		
		
		for (String string : bossLogs) {
			//crits mit Cthun sonderlocke
			if(string.contains("casts Curse of Shadows on "+bossName) || string.contains("casts Curse of the Elements on "+bossName) || string.contains("casts Curse of Recklessness on "+bossName) || string.contains("hits "+bossName) || string.contains("crits "+bossName) || string.contains("crits Eye of "+bossName) || string.contains("hits Eye of "+bossName)) {
				boss.setFirstHitTime(General.getEntryAtPosition(string, 0)+" "+General.getEntryAtPosition(string, 1));
				break;
			}
		} 

		for (int a=bossLogs.size()-1;a>0;a--) {
			String currentLine = bossLogs.get(a);
			if(currentLine.contains(boss.getName()+" dies")) {
				boss.setDiedTime(General.getEntryAtPosition(currentLine, 0)+" "+General.getEntryAtPosition(currentLine, 1));
				break;
			}
		}
		for (String string : bossLogs) {
			
			//Parries
			if(string.contains(bossName+" parries.")) {
				String currentPlayer = General.getPlayerName(string);
				if( Players.isNameAValidPlayerInRaid(currentPlayer)) {
					if(playerParryCount.get(currentPlayer)!=null) {
						playerParryCount.put(currentPlayer, playerParryCount.get(currentPlayer)+1);
					} else {
						playerParryCount.put(currentPlayer, 1);					
					}
				}
			}
			
			//Curses applied
			if(string.contains(bossName+" is afflicted by Curse") || string.contains(bossName+" gains Curse")) {
				if(string.contains(bossName+" is afflicted by Curse of Recklessness") || string.contains(bossName+" gains Curse of Recklessness")) {
					boss.getCurseOfRecklessnessAppliedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
				}
				if(string.contains(bossName+" is afflicted by Curse of Shadow") || string.contains(bossName+" gains Curse of Shadow")) {
					boss.getCurseOfShadowsAppliedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
				}
				if(string.contains(bossName+" is afflicted by Curse of the Elements") || string.contains(bossName+" gains Curse of the Elements")) {
					boss.getCurseOfElementsAppliedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
				}
			}
			
			//Curses fades
			if(string.contains("Curse of Recklessness fades from "+bossName) ) {
				boss.getCurseOfRecklessnessFadedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
			}
			if(string.contains("Curse of Shadow fades from "+bossName) ) {
				boss.getCurseOfShadowsFadedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
			}
			if(string.contains("Curse of the Elements fades from "+bossName) ) {
				boss.getCurseOfElementsFadedList().add(General.getEntryAtPosition(string, 1).substring(0,8));
			}

		}
		boss.setPlayerParryCount(playerParryCount);
		
		DateTime startTime = new DateTime(boss.getFirstHitDate());		
		DateTime endTime = new DateTime(boss.getDiedTimeDate());
		ArrayList<String> logsInIntervall = General.getLogsWithinIntervallPLusTolerance(startTime, endTime, 0, completeLog);
		
		//Flüche
		for (String currentLine : logsInIntervall) {
			if(currentLine.contains(Constants.dispelMagic) || currentLine.contains(Constants.cleanse) || currentLine.contains(Constants.purify) || currentLine.contains(Constants.removeCurse) || currentLine.contains(Constants.abolishPoison) || currentLine.contains(Constants.removeLesserCurse)) {
				String playerName = General.getPlayerName(currentLine);
				if(Players.isNameAValidPlayerInRaid(playerName)) {
					String elementToAdd = playerName+"("+General.getEntryAtPosition(currentLine, 1).substring(0,8)+")";
					if(!boss.getDispells().contains(elementToAdd)) {
						boss.getDispells().add(elementToAdd);
					}
				}
			}
		}
		
		//System.out.println("####Current Boss is: "+bossName+" startTime: "+startTime+" endTime: "+endTime);
		for (String string : logsInIntervall) {
			if(string.contains("dies")) {
				String currentPlayer = General.getPlayerName(string);
				if( Players.isNameAValidPlayerInRaid(currentPlayer) && string.contains(currentPlayer+" dies.")) {
					//was ist mit dem spieler passiert, bevor er gestorben ist?
					int msTogoBack = 1500;
					if(Players.isPlayerAPriest(currentPlayer)) {
						msTogoBack = 11000;
					}
					
					ArrayList<String> logsInIntervallUntilPlayerDeath = General.getLogsWithinIntervallPLusTolerance(General.getTimeFromLogAsDateTime(string).minusMillis(msTogoBack), General.getTimeFromLogAsDateTime(string), 1000, logsInIntervall);
					boolean foundCause = false;
					for(int i = logsInIntervallUntilPlayerDeath.size()-1;i>0;i--) {
						String deathLine = logsInIntervallUntilPlayerDeath.get(i);
						if((deathLine.contains(currentPlayer+" is killed") || deathLine.contains("hits "+currentPlayer) || deathLine.contains("crits "+currentPlayer) || deathLine.contains(currentPlayer+" suffers")) && !deathLine.contains(currentPlayer+" dies.")) {
							playerDeathCause.put(currentPlayer, deathLine.substring(18)+" ("+General.getTimeFromLog(deathLine).substring(0,8)+")");
							foundCause = true;
							break;
						}
					}
					if(foundCause==false) {
						playerDeathCause.put(currentPlayer, "unclear why!"+" ("+General.getTimeFromLog(string)+")");
					}
				}
			}
		}
		boss.setPlayerDeathCause(playerDeathCause);
		if(boss.getFirstHitDate()!=null && boss.getSunderTimes()!=null) {
			bossMap.put(bossName+suffix, boss);
		} 
	}	
	
	public static String getBossStatsHTML() {
		StringBuffer strBuf = new StringBuffer();
		if(bossMap!=null) {
			Set<String> bosses =  bossMap.keySet();		
			ArrayList<Boss> sortedBosses = new ArrayList<>();
			for (String bossName : bosses) {
				Boss boss = bossMap.get(bossName);
				sortedBosses.add(boss);
			}
			strBuf.append("<br><br><div style='font-size: 20; font-weight: bold;' >=>Boss Stats </div><br>");			
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table id='bossStats' class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: gray;'><td colspan='13'>BossStats</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>FirstHit</th>");
			strBuf.append("<th>First 5 Sunders</th>");
			strBuf.append("<th title='Players who initial helped to sunder to 5 Stacks'>First Sunder Appliers</th>");
			strBuf.append("<th>Faerie Fire</th>");
			strBuf.append("<th>CoE applied-fades</th>");
			strBuf.append("<th>CoS applied-fades</th>");
			strBuf.append("<th>CoR applied-fades</th>");
			strBuf.append("<th>Dispell/Decurse/Cleanse...</th>");
			strBuf.append("<th title='Every parry by boss is dps loss for us + probably parry haste for boss :) '>Boss Parried >1</th>");			
			strBuf.append("<th>Player Died</th>");			
			strBuf.append("<th title='this dmg is additional thanks to nightfall'>Nightfall procs/dmg</th>");			
			strBuf.append("<th>Boss died</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			Collections.sort(sortedBosses);
			//um sich alte deaths zu merken, damit die nicht erneut auftauchen
			ArrayList<String> playerDeathCauseold = new ArrayList<>();
			for (Boss boss : sortedBosses) {
				if(boss.getName()==null) {
					continue;
				}
					strBuf.append("<tr>");		
					//wipeOrReset?
					String bossName = boss.getName();
					if(bossName.contains("(") && bossName.contains(")")) {
						strBuf.append("<td style='color: red;'>"+bossName+"</td>");
					} else {
						strBuf.append("<td>"+bossName+"</td>");
					}
					strBuf.append("<td>"+General.getOnlyTimeFromDateTimeString(boss.getFirstHitTime())+"</td>");
					strBuf.append("<td>"+boss.getSunderTimes()+"</td>");
					strBuf.append("<td>"+boss.getHelpedToSunderUntil5()+"</td>");
					strBuf.append("<td>"+boss.getFaerieFireApplied()+"</td>");
					strBuf.append("<td>");
					for (String elementsEntry : mergeCursesAppliedAndFades(boss.getCurseOfElementsAppliedList(), boss.getCurseOfElementsFadedList())) {
						strBuf.append(elementsEntry+"<br>");
					}
					strBuf.append("</td>");

					strBuf.append("<td>");
					for (String shadowEntry : mergeCursesAppliedAndFades(boss.getCurseOfShadowsAppliedList(), boss.getCurseOfShadowsFadedList())) {
						strBuf.append(shadowEntry+"<br>");
					}
					strBuf.append("</td>");

					strBuf.append("<td>");
					for (String reckEntry : mergeCursesAppliedAndFades(boss.getCurseOfRecklessnessAppliedList(), boss.getCurseOfRecklessnessFadedList())) {
						strBuf.append(reckEntry+"<br>");
					}
					strBuf.append("</td>");
					strBuf.append("<td>");
				    // Ergebnis ausgeben
				    for (java.util.Map.Entry<String, Integer> dispeller : boss.getSortedDispeller().entrySet()) {
				    	strBuf.append((dispeller.getKey() + ": " + dispeller.getValue()+"<br>"));
				    }
					
					strBuf.append("</td>");
					strBuf.append("<td>");
					HashMap<String, Integer> playerParryCount = boss.getPlayerParryCount();
					Set<String> keySet = playerParryCount.keySet();
					for (String player : keySet) {
						if(playerParryCount.get(player)>1) {
							strBuf.append(player+":"+playerParryCount.get(player)+"<br>");
						}
					}
					strBuf.append("</td>");					
					strBuf.append("<td>");
					HashMap<String, String> playerDeathCause = boss.getPlayerDeathCause();
					Set<String> playerDeathSet = playerDeathCause.keySet();
					for (String player : playerDeathSet) {
						if(!playerDeathCauseold.contains(playerDeathCause.get(player))) {
							strBuf.append("<b>"+player+":</b><i style='font-size: 11px;' title=\""+playerDeathCause.get(player)+"\">"+StringUtils.abbreviate(playerDeathCause.get(player), 65)+"</i><br>");
							playerDeathCauseold.add(playerDeathCause.get(player));
						}
					}
					strBuf.append("</td>");					
					strBuf.append("<td>"+boss.getNightFallProcs()+" | "+((boss.getNightFallDmg()/100)*10)+"</td>");					
					strBuf.append("<td>"+General.getOnlyTimeFromDateTimeString(boss.getDiedTime())+"</td>");					
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
	
	private static ArrayList<String> mergeCursesAppliedAndFades(ArrayList<String> appliedList, ArrayList<String> fadedList) {
		ArrayList<String> retList = new ArrayList<>();
		for (int i=0;i<appliedList.size();i++) {
			String crntApplied = appliedList.get(i);
			String crntfaded = "";
			if(fadedList!=null && fadedList.size()>(i)) {
				crntfaded = fadedList.get(i);				
			}			
			retList.add(crntApplied+"-"+crntfaded);
		}
		return retList;
	}
	

}
