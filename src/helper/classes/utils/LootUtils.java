package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LootUtils {
	
	public static HashMap<String, ArrayList<String>> playerLootMap = new HashMap<>();
	public static HashMap<String, HashMap<String, Integer>> lootRessouces = new HashMap<>();
	
	public static void addRessourcesLoot(String player, String itemName) {
		if(lootRessouces.containsKey(player)) {
			if(lootRessouces.get(player).containsKey(itemName)) {
				Integer value = lootRessouces.get(player).get(itemName);
				value+=1;
				lootRessouces.get(player).put(itemName, value);
			} else {
				lootRessouces.get(player).put(itemName, 1);
			}
		} else {
			HashMap<String, Integer> newResMap = new HashMap<>();
			newResMap.put(itemName, 1);
			lootRessouces.put(player, newResMap);
		}
	}

	
	public static void addLoot(String player, String itemId, String itemName) {
		if(playerLootMap.containsKey(player)) {
			playerLootMap.get(player).add(itemId+"_"+itemName);
		} else {
			ArrayList<String> lootList = new ArrayList<>();
			lootList.add(itemId+"_"+itemName);
			playerLootMap.put(player, lootList);
		}
	}
	
	public static void assignEpicLoot(ArrayList<String> completeLogs) {
		for (String string : completeLogs) {
			
			//Ressources
			if(string.contains("LOOT:") && string.contains("receives loot:") && (
					string.contains("Core Leather") || string.contains("Rugged Leather") || 
					string.contains("Dark Iron Ore") || string.contains("Dreamscale") ||
					string.contains("Green Dragonscale") || string.contains("Small Dream Shard") ||
					string.contains("Thick Leather")
					)
				) {
		        String regex = "LOOT:.*?&(\\w+) receives loot: .*?\\|Hitem:(\\d+):.*?\\|h\\[(.+?)\\]\\|h";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(string);
		        if (matcher.find()) {
		            // Spielername, Item-ID und Gegenstand extrahieren
		            String playerName = matcher.group(1);
		            String itemName = matcher.group(3);
		            addRessourcesLoot(playerName, itemName);
		        }
				
			}
			
			
			
			if(string.contains("LOOT:") && string.contains("receives loot:") && string.contains("cffa335ee")) {
				//Epic loot found
				//12/8 19:40:48.935  LOOT: 08.12.24 19:40:48&Zaeh receives loot: |cffa335ee|Hitem:21699:0:0:0|h[Barrage Shoulders]|h|rx1.
				// Regulärer Ausdruck zum Extrahieren von Spielername, Gegenstand und Item-ID
		        String regex = "LOOT:.*?&(\\w+) receives loot: .*?\\|Hitem:(\\d+):.*?\\|h\\[(.+?)\\]\\|h";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(string);
		        if (matcher.find()) {
		            // Spielername, Item-ID und Gegenstand extrahieren
		            String playerName = matcher.group(1);
		            String itemId = matcher.group(2);
		            String itemName = matcher.group(3);
		            addLoot(playerName, itemId, itemName);
		        }
			}
		}
		
	}
	
	public static String getLootAsHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!playerLootMap.isEmpty()) {
        	strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: #a335ee;'>")
                  .append("<td colspan='2'>LOOT</td></tr><tr>")
                  .append("<th>Name</th>")
                  .append("<th>Item</th>")
                  .append("</tr>");
    		Set<String> keySet = playerLootMap.keySet();
    		
    		for (String string : keySet) {
    			ArrayList<String> playerLoot = playerLootMap.get(string);
    			strBuf.append("<tr>");
    			strBuf.append("<td>"+string+"</td>");
    			strBuf.append("<td>");
    				for (String currentLoot : playerLoot) {
    					String itemId = General.getEntryAtPosition(currentLoot, 0, "_");
    					String itemName = General.getEntryAtPosition(currentLoot, 1, "_");
    					strBuf.append(itemName+"&nbsp;&nbsp;<a href='https://database.turtle-wow.org/?item="+itemId+"' target='_new'>https://database.turtle-wow.org/?item="+itemId+"</a><br>");
					}
       			strBuf.append("</td>");
//                	  .append("<td>"+playerLootMap.get(string)+"</td>")
//                	  .append("<td><a href='https://database.turtle-wow.org/?item=' target='_new'>"+playerLootMap.get(string)+"</a></td>");
//    			strBuf.append("</tr>");    			
    		}		
    		Set<String> ressourcesSet = lootRessouces.keySet();
    		
    		for (String player : ressourcesSet) {
    			HashMap<String, Integer> playerRessMap = lootRessouces.get(player);
    			Set<String> ressourcesSorted = playerRessMap.keySet();
    			strBuf.append("<tr>");
    			strBuf.append("<td>"+player+"</td>");
    			strBuf.append("<td>");
    				for (String currentRessName : ressourcesSorted) {
    					strBuf.append(currentRessName+"("+playerRessMap.get(currentRessName)+")<br>");
					}
       			strBuf.append("</td>");
    		}		
    		
        }
		return strBuf.toString();
	}
}
