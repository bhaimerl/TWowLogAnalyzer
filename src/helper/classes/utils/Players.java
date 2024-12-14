package helper.classes.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import helper.classes.NameClassWrapper;

public class Players {
	public static ArrayList<NameClassWrapper> identifyAllPlayers(ArrayList<String> completeLog) {			
		HashMap<String, NameClassWrapper> nameClassMap = new HashMap<>();
		ArrayList<NameClassWrapper> uniqueList = new ArrayList<>();
		StringTokenizer strTok = null;
		String name = "";
		String playerClass = "";
		String guild = "";
		for (String currentLogLine : completeLog) {
			//12/6 19:16:40.023  COMBATANT_INFO: 06.12.24 19:16:40&Ravenya&DRUID&NightElf&3&nil&ist auch dabei&Officer&1&nil&22732:928:0:0&nil&nil&nil&nil&nil&nil&nil&nil&17063:928:0:0&18879:928:0:0&nil&nil&22938:2622:0:0&nil&nil&23198:0:0:0&5976:0:0:0&nil
			if(currentLogLine.indexOf("COMBATANT_INFO:")>=0) {
				//korrekte zeile
				//schmeiße alles bis auf das erste & zeichen raus:
				currentLogLine = currentLogLine.substring(currentLogLine.indexOf("&")+1);
				strTok = new StringTokenizer(currentLogLine, "&");
				try {
					while(strTok.hasMoreTokens()) {
						name = strTok.nextToken();
						playerClass = strTok.nextToken();
						strTok.nextToken(); // race;
						strTok.nextToken(); // ka
						strTok.nextToken(); //ka
						guild = strTok.nextToken();
						nameClassMap.put(name, new NameClassWrapper(name, playerClass, guild));
						break;
					}
				} catch(Exception e) {
					System.out.println("Problem during sorting this line: "+currentLogLine);
				}
			}
		}
		Set<String> names = nameClassMap.keySet();
		Iterator<String> iterator = names.iterator();
		while(iterator.hasNext()) {
			NameClassWrapper nameClassWrapper = nameClassMap.get(iterator.next());
			if(isThisPlayerValid(nameClassWrapper.getName(), completeLog)) {
				uniqueList.add(nameClassWrapper);
			}
		}
		Collections.sort(uniqueList);
		return uniqueList;
	}
	
	private static boolean isThisPlayerValid(String name, ArrayList<String> completeLog) {
		//Player nur valid, wenn er mehrfach im Log auftaucht
		boolean valid = false;
		//mind 10mal;
		int cnt = 0;
		for (String logLine : completeLog) {
			if(logLine.indexOf(name+" gains")>=0) {
				cnt++;
			}
			if(cnt==3) {
				return true;
			}
		}
		System.out.println("Player: "+name+" will be removed from list, seems to be invalid");
		return valid;
	}
	
	
	public static HashMap<String, ArrayList<NameClassWrapper>> getAllPlayersSortedByClass(ArrayList<String> completeLog) {
		HashMap<String, ArrayList<NameClassWrapper>> resultMap = new HashMap<>();			
		ArrayList<NameClassWrapper> identifyAllPlayers = identifyAllPlayers(completeLog);
		//DRUID	HUNTER	MAGE	PALADIN	PRIEST	ROGUE	SHAMAN	WARLOCK	WARRIOR
		ArrayList<NameClassWrapper> druids = new ArrayList<>();
		ArrayList<NameClassWrapper> hunters = new ArrayList<>();
		ArrayList<NameClassWrapper> mages = new ArrayList<>();
		ArrayList<NameClassWrapper> paladins = new ArrayList<>();
		ArrayList<NameClassWrapper> shamans = new ArrayList<>();
		ArrayList<NameClassWrapper> priests = new ArrayList<>();
		ArrayList<NameClassWrapper> rogues = new ArrayList<>();
		ArrayList<NameClassWrapper> warlocks = new ArrayList<>();
		ArrayList<NameClassWrapper> warriors = new ArrayList<>();
		
		for (NameClassWrapper currentPlayer : identifyAllPlayers) {
			if(currentPlayer.getPlayerClass().equals("DRUID")) {
				druids.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("HUNTER")) {
				hunters.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("MAGE")) {
				mages.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("PALADIN")) {
				paladins.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("PRIEST")) {
				priests.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("ROGUE")) {
				rogues.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("SHAMAN")) {
				shamans.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("WARLOCK")) {
				warlocks.add(currentPlayer);
			}
			if(currentPlayer.getPlayerClass().equals("WARRIOR")) {
				warriors.add(currentPlayer);
			}
		}
		resultMap.put("DRUID", druids);
		resultMap.put("HUNTER", hunters);
		resultMap.put("MAGE", mages);
		resultMap.put("PALADIN", paladins);
		resultMap.put("PRIEST", priests);
		resultMap.put("ROGUE", rogues);
		resultMap.put("SHAMAN", shamans);
		resultMap.put("WARLOCK", warlocks);
		resultMap.put("WARRIOR", warriors);
		return resultMap;
	}
}
