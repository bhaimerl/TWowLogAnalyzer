package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import helper.Raids.Boss;
import helper.Raids.RaidBossMapping;

public class BossUtils {
	
    public static HashMap<String, Boss> bossMap = new HashMap<>(); 
	

    public static void calculateBossStats(ArrayList<String> completeLog, ArrayList<String> bossesFromLog) {
    	for (String currentBoss : bossesFromLog) {
    		getBossStats(completeLog, currentBoss);
    	}
    }

    
	private static void getBossStats(ArrayList<String> completeLog, String bossName) {
		ArrayList<String> bossLogs = General.getLogsFromBossByName(bossName, completeLog);
		ArrayList<String> sunderlogs = General.getOnlySunderLogs(bossLogs);
		Boss boss = new Boss();
		for (int i=0;i<sunderlogs.size();i++) {
			String string = sunderlogs.get(i);
			if(string.contains("casts Sunder Armor")) {
				boss.setHelpedToSunderUntil5((boss.getHelpedToSunderUntil5()+" "+General.getPlayerName(string)).trim());
			}
			if(string.contains("is afflicted by Sunder Armor (5).")) {
				//sunderLogsAppliersUntil5.add(string);
				break;
			}
		}
		for (int i=0;i<sunderlogs.size();i++) {
			String string = sunderlogs.get(i);
			if(string.contains("is afflicted by Sunder Armor (")) {
				boss.setSunderTimes((boss.getSunderTimes()+" "+General.getEntryAtPosition(string, 1)).trim()+"<br>");
			}
			if(string.contains("is afflicted by Sunder Armor (5).")) {
				//sunderLogsAppliersUntil5.add(string);
				boss.setName(bossName);
				break;
			}
		}
		
		for (String string : bossLogs) {
			//crits Anub'Rekhan
			if(string.contains("hits "+bossName) || string.contains("crits "+bossName)) {
				boss.setFirstHitTime(General.getEntryAtPosition(string, 1).substring(0,8));
				break;
			}
		}

		for (int a=bossLogs.size()-1;a>0;a--) {
			String currentLine = bossLogs.get(a);
			if(currentLine.contains(boss.getName()+" dies")) {
				boss.setDiedTime(General.getEntryAtPosition(currentLine, 1).substring(0,8));
			}
		}
		for (String string : bossLogs) {
			if(string.contains("is afflicted by Curse")) {
				if(string.contains("is afflicted by Curse of Recklessness")) {
					boss.setCurseOfRecklessness(General.getEntryAtPosition(string, 1).substring(0,8));
				}
				if(string.contains("is afflicted by Curse of Shadow")) {
					boss.setCurseOfShadow(General.getEntryAtPosition(string, 1).substring(0,8));
				}
				if(string.contains("is afflicted by Curse of the Elements")) {
					boss.setCurseOfElements(General.getEntryAtPosition(string, 1).substring(0,8));
				}
			}
		}
		//Curses
		bossMap.put(bossName, boss);
	}	
	
	public static String getBossStatsHTML() {
		StringBuffer strBuf = new StringBuffer();
		if(bossMap!=null) {
			Set<String> bosses =  bossMap.keySet();			
			strBuf.append("<br><br><div style='font-size: 20; font-weight: bold;' >Boss Stats </div><br>");			
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: gray;'><td colspan='7'>BossStats</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>FirstHit</th>");
			strBuf.append("<th>First 5 Sunders</th>");
			strBuf.append("<th>Curse of Elements</th>");
			strBuf.append("<th>Curse of Shadows</th>");
			strBuf.append("<th>Curse of Recklessness</th>");
			strBuf.append("<th>Boss died</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String bossName : bosses) {
				Boss boss = bossMap.get(bossName);
				if(boss.getName()==null) {
					continue;
				}
					strBuf.append("<tr>");					
					strBuf.append("<td>"+boss.getName()+"</td>");
					strBuf.append("<td>"+boss.getFirstHitTime()+"</td>");
					strBuf.append("<td>"+boss.getSunderTimes()+"</td>");
					strBuf.append("<td>"+boss.getCurseOfElements()+"</td>");
					strBuf.append("<td>"+boss.getCurseOfShadow()+"</td>");
					strBuf.append("<td>"+boss.getCurseOfRecklessness()+"</td>");
					strBuf.append("<td>"+boss.getDiedTime()+"</td>");					
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	

}
