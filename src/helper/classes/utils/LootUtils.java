package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.classes.utils.besonderes.TradetLoot;

public class LootUtils {
	
	public static HashMap<String, ArrayList<String>> playerLootMap = new HashMap<>();
	public static HashMap<String, HashMap<String, Integer>> lootRessouces = new HashMap<>();
	public static ArrayList<TradetLoot> tlList = new ArrayList<TradetLoot>();
	
	
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
			
			
			//traded loot
			if(string.contains("LOOT_TRADE:") && string.contains("trades item") && string.contains("to")) {
				Pattern pattern = Pattern.compile("LOOT_TRADE: \\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}:\\d{2}\\&(?<trader>\\w+) trades item (?<item>.+?) to (?<receiver>\\w+).", Pattern.MULTILINE);
		        try {
		            Matcher matcher = pattern.matcher(string);
		            
		            while (matcher.find()) {
		                String trader = matcher.group("trader");
		                String item = matcher.group("item");
		                String receiver = matcher.group("receiver");
		                tlList.add(new TradetLoot(item, trader, receiver));
		            }
		        } catch (Exception e) {
		            System.err.println("Error reading log file: " + e.getMessage());
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
		HashMap<String, String> itemLinkMap = new HashMap<String, String>();
        StringBuilder strBuf = new StringBuilder();
        if (!playerLootMap.isEmpty()) {
        	strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: #a335ee; color: white; font-weight: bold;'>")
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
    					String itemLink = "https://database.turtle-wow.org/?item="+itemId;
    					itemLinkMap.put(itemName, itemLink);
    					strBuf.append(itemName+"&nbsp;&nbsp;<a href='"+itemLink+"' target='_new'>"+itemLink+"</a><br>");
					}
       			strBuf.append("</td></tr>");
//                	  .append("<td>"+playerLootMap.get(string)+"</td>")
//                	  .append("<td><a href='https://database.turtle-wow.org/?item=' target='_new'>"+playerLootMap.get(string)+"</a></td>");
    		}		
    		
    		
    		
    		Set<String> ressourcesSet = lootRessouces.keySet();    		
    		if(ressourcesSet.size()>0) {
        		for (String player : ressourcesSet) {
        			HashMap<String, Integer> playerRessMap = lootRessouces.get(player);
        			Set<String> ressourcesSorted = playerRessMap.keySet();
        			strBuf.append("<tr>");
        			strBuf.append("<td>"+player+"</td>");
        			strBuf.append("<td>");
        				for (String currentRessName : ressourcesSorted) {
        					strBuf.append(currentRessName+"("+playerRessMap.get(currentRessName)+")<br>");
    					}
           			strBuf.append("</td></tr>");
        		}	
    			
    		}
			strBuf.append("</table>"); 
			
			
			
			if(tlList.size()>0) {
				//traded loot
	        	strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
	            .append("<tr style='background-color: #a335ee; color: white; font-weight: bold;'>")
	            .append("<td colspan='3'>TradetLoot</td></tr><tr>")
	            .append("<th width='40%'>Item</th>")
	            .append("<th width='30%'>From</th>")
	            .append("<th width='30%'>To</th>")
	            .append("</tr>");
	        	for(TradetLoot tl : tlList) {
        			strBuf.append("<tr>");
        			String itemLink = "";
        			if(itemLinkMap.get(tl.getItem())!=null) {
            			itemLink = itemLinkMap.get(tl.getItem());
            			itemLink ="&nbsp;&nbsp;<a href='"+itemLink+"' target='_new'>"+itemLink+"</a>";
        			}
        			strBuf.append("<td>"+tl.getItem()+itemLink+"</td>");
        			strBuf.append("<td>"+tl.getGiver()+"</td>");
        			strBuf.append("<td>"+tl.getReceiver()+"</td>");
        			strBuf.append("</tr>");
	        	}
				strBuf.append("</table>"); 
				
			}
			
        }
        
		return strBuf.toString();
	}
	
	
}
