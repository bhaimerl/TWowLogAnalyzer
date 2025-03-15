package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.classes.NameClassWrapper;
import helper.classes.Warrior;

public class WarriorUtils {
	
    public static HashMap<String, Warrior> warriorMap = new HashMap<>(); 


    
    
    public static Warrior getWarriorByName(String name) {
        return warriorMap.computeIfAbsent(name, k -> new Warrior());
    }

    private static void updateWarriorStats(String logline, String playerName, String keyword, Consumer<Warrior> action) {
        if (logline.contains(keyword)) {
            Warrior warrior = getWarriorByName(playerName);
            action.accept(warrior);
        }
    }
    
    private static boolean updateWarriorStatsForBlock(String logline, String playerName) {
        if (logline.contains("blocked)") && (logline.contains("hits "+playerName) || logline.contains("crits "+playerName))) {
            Warrior warrior = getWarriorByName(playerName);
            Matcher matcher = General.patternBlocks.matcher(logline);
            if (matcher.find()) {
                String hittedPlayer = matcher.group(1);
                int incomingDmg = Integer.parseInt(matcher.group(2));
                int dmgBlocked = Integer.parseInt(matcher.group(3));
                warrior.incrementBlockedCnt();
                warrior.addDmgAmountBlocked(dmgBlocked);
                warrior.addDmgAmountCompleteBeforeBlock(dmgBlocked + incomingDmg);
                return true;
            }            
        }
        return false;
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Warrior> hitAction, Consumer<Warrior> critAction,
                                       Consumer<Warrior> highestAmountAction) {
    	updateWarriorStats(logline, playerName, hitKeyword, hitAction);
    	updateWarriorStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Warrior warrior = getWarriorByName(playerName);
            highestAmountAction.accept(warrior);
        }
    }
	
	public static void findEntryForWarrior(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	String currentPlayer = General.getPlayerName(logline);
    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.WARRIOR)) {
        	currentPlayer = General.getPlayerNameHitted(logline);
    		if(currentPlayer!=null) {
    	    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.WARRIOR)) {
    	    		return;
    	    	}
    		} else {
        		return;
    		}
    	}
    	//3/7 20:03:58.744  Patchwerk 's Hateful Strike hits Kranette for 4790. (298 blocked) (500 absorbed)
    	
    	//KriegerBockValues
    	
    	
    	if(updateWarriorStatsForBlock(logline, currentPlayer)) {
    		//blocked eintrag geschrieben, nicht mehr machen!
    	} else {
        	updateWarriorStats(logline, currentPlayer, Constants.shieldSlam, Warrior::incrementShieldSlam);
        	updateWarriorStats(logline, currentPlayer, Constants.concussionBlow, Warrior::incrementConsussionBlow);
        	updateWarriorStats(logline, currentPlayer, Constants.sunder, Warrior::incrementSunders);
        	updateWarriorStats(logline, currentPlayer, Constants.deathWish, Warrior::incrementDeathWish);
        	updateWarriorStats(logline, currentPlayer, Constants.windfury, Warrior::incrementWindFury);
        	updateWarriorStats(logline, currentPlayer, Constants.flametongue, Warrior::incrementFlameTongue);
        	updateWarriorStats(logline, currentPlayer, Constants.crusader, Warrior::incrementCrusader);
        	updateWarriorStats(logline, currentPlayer, Constants.flurry, Warrior::incrementFlurry);
        	updateWarriorStats(logline, currentPlayer, Constants.enrage, Warrior::incrementEnrage);

        	updateWarriorStats(logline, currentPlayer, Constants.wrath, warrior -> {
                if (logline.contains(Constants.gains)) {
                    warrior.addWrath(General.getAmountGains(Constants.wrath, logline));
                }
            });    	
        	
            processAbility(logline, currentPlayer, Constants.executeHit, Constants.executeCrit, 
                Warrior::incrementExecute, Warrior::incrementExecute,
                warrior -> warrior.updateHighestExecuteAmount(General.getAmountAtEnd(Constants.executeHit, Constants.executeCrit, logline),
                            General.getTarget(Constants.executeHit, Constants.executeCrit, logline))
            );
            
            processAbility(logline, currentPlayer, Constants.mortalStrikeHit, Constants.mortalStrikeCrit, 
                    Warrior::incrementExecute, Warrior::incrementMortalStrike,
                    warrior -> warrior.updateHighestMortalStrikeAmount(General.getAmountAtEnd(Constants.mortalStrikeHit, Constants.mortalStrikeCrit, logline),
                                General.getTarget(Constants.mortalStrikeHit, Constants.mortalStrikeCrit, logline))
            );
            
        	
            processAbility(logline, currentPlayer, Constants.bloodthirstHit, Constants.bloodthirstCrit, 
                    Warrior::incrementBloodThirstAmount, Warrior::incrementBloodThirstAmount,
                    warrior -> warrior.updateHighestBloodthirst(General.getAmountAtEnd(Constants.bloodthirstHit, Constants.bloodthirstCrit, logline),
                    		General.getTarget(Constants.bloodthirstHit, Constants.bloodthirstCrit, logline))
            );
    	}
	}
	
	
	
	public static String getWarriors() {
		//clean all first
		StringBuffer strBuf = new StringBuffer();
		if(warriorMap!=null) {
			SortedSet<String> warriors =  new TreeSet<>(warriorMap.keySet());
			strBuf.append("<br>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: "+Constants.WARRIORCOLOR+";'><td colspan='19'>"+Constants.WARRIOR+"</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Death</th>");
			strBuf.append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>");
			strBuf.append("<th>Sunder Armor</th>");
			strBuf.append("<th>Deathwish</th>");
			strBuf.append("<th>Windfury Procs</th>");
			strBuf.append("<th>Crusader Procs</th>");
			strBuf.append("<th>rage from UB</th>");
			strBuf.append("<th>Flametongue procs</th>");
			strBuf.append("<th>Flurry procs</th>");
			strBuf.append("<th>Enrage procs</th>");
			strBuf.append("<th>DEF: CBlow / SSlam</th>");
			strBuf.append("<th>DEF: Blocks / dmg blocked / blocked %</th>");
			strBuf.append("<th>Executes</th>");
			strBuf.append("<th class=\"toggle-column-highlights\">Max Execute</th>");
			strBuf.append("<th>Mortal Strikes</th>");
			strBuf.append("<th class=\"toggle-column-highlights\">Max MS</th>");
			strBuf.append("<th>Bloodthirsts</th>");
			strBuf.append("<th class=\"toggle-column-highlights\">Max Bloodthirst</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String warriorName : warriors) {
				Warrior warri = warriorMap.get(warriorName); 
				    Double blocked = (double) ((warri.getDmgAmountCompleteBeforeBlock()>0)?(((double)warri.getDmgAmountBlocked()/(double)warri.getDmgAmountCompleteBeforeBlock())*100):0);
					String blockLine = (blocked>0)?blocked.intValue()+"":"0";
					strBuf.append("<tr>");
					strBuf.append("<td>"+warriorName+"</td>");
					strBuf.append("<td>"+warri.getDeathCounter()+"</td>");
					strBuf.append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(warri)+"</td>");					
					strBuf.append("<td>"+warri.getSunders()+"</td>");
					strBuf.append("<td>"+warri.getDeathWish()+"</td>");
					strBuf.append("<td>"+warri.getWindFury()+"</td>");
					strBuf.append("<td>"+warri.getCrusader()+"</td>");
					strBuf.append("<td>"+warri.getWrath()+"</td>");
					strBuf.append("<td>"+warri.getFlametongue()+"</td>");
					strBuf.append("<td>"+warri.getFlurry()+"</td>");
					strBuf.append("<td>"+warri.getEnrage()+"</td>");
					strBuf.append("<td>"+warri.getConsussionBlow()+" / "+warri.getShieldSlam()+"</td>");
					strBuf.append("<td>"+warri.blockedCnt+" / "+warri.getDmgAmountBlocked()+" / "+blockLine+"%</td>");
					strBuf.append("<td>"+warri.getExecuteAmount()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\">"+warri.getHighestExecute()+"=> "+warri.getHighestExecuteTarget()+"</td>");
					strBuf.append("<td>"+warri.getMortalStrikeAmount()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\">"+warri.getHighestMortalStrike()+"=> "+warri.getHighestMortalStrikeTarget()+"</td>");
					strBuf.append("<td>"+warri.getBloodThirstAmount()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\">"+warri.getHighestBloodthirst()+"=> "+warri.getHighestBloodthirstTarget()+"</td>");
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
	public static boolean isWarrior(String logline) {
		boolean isWarrior = false;
		if((logline.contains(Constants.sunder) || 
			logline.contains(Constants.deathWish) || 
			logline.contains(Constants.wrath) || 
			logline.contains(Constants.enrage) || 
			logline.contains(Constants.concussionBlow) || 
			logline.contains(Constants.shieldSlam) || 			
			logline.contains(Constants.bloodthirstHit))) {
			isWarrior = true;
		}
		return isWarrior;
	}
	
}
