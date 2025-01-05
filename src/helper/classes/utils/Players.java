package helper.classes.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import helper.classes.NameClassWrapper;

public class Players {
	
	public static ArrayList<NameClassWrapper> uniqueList = new ArrayList<>();
	
	public static boolean isNameAValidPlayerInRaid(String playerName) {
		for (NameClassWrapper nameClassWrapper : uniqueList) {
			if(nameClassWrapper.getName().equals(playerName)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<NameClassWrapper> identifyAllPlayers(ArrayList<String> completeLog) {	
		uniqueList = new ArrayList<>();
		HashMap<String, NameClassWrapper> nameClassMap = new HashMap<>();
		StringTokenizer strTok = null;
		String name = "";
		String playerClass = "";
		String guild = "";
		int combatantCounter = 0;
		for (String currentLogLine : completeLog) {
			//12/6 19:16:40.023  COMBATANT_INFO: 06.12.24 19:16:40&Ravenya&DRUID&NightElf&3&nil&ist auch dabei&Officer&1&nil&22732:928:0:0&nil&nil&nil&nil&nil&nil&nil&nil&17063:928:0:0&18879:928:0:0&nil&nil&22938:2622:0:0&nil&nil&23198:0:0:0&5976:0:0:0&nil
			if(currentLogLine.indexOf("COMBATANT_INFO:")>=0) {
				combatantCounter+=1;
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
		
		
		//
		if(combatantCounter==0) {
			System.out.println("Combatant_info not found, need a different way to identify players");
		//performance boost, is pleyer schon vorhanden, skip
			for (String currentLogLine : completeLog) {
				if(WarriorUtils.isWarrior(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.WARRIOR, "nil"));				
				}
				if(WarlockUtils.isWarlock(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.WARLOCK, "nil"));				
				}
				if(RogueUtils.isRogue(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.ROGUE, "nil"));				
				}			
				if(MageUtils.isMage(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.MAGE, "nil"));				
				}
				if(PaladinUtils.isPaladin(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.PALADIN, "nil"));				
				}
				if(HunterUtils.isHunter(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.HUNTER, "nil"));				
				}
				if(DruidUtils.isDruid(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.DRUID, "nil"));				
				}
				if(ShamanUtils.isShaman(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.SHAMAN, "nil"));				
				}
				if(PriestUtils.isPriest(currentLogLine)) {
					nameClassMap.put(General.getPlayerName(currentLogLine), new NameClassWrapper(General.getPlayerName(currentLogLine), Constants.PRIEST, "nil"));				
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
		Collections.sort(druids);
		Collections.sort(hunters);
		Collections.sort(mages);
		Collections.sort(paladins);
		Collections.sort(priests);
		Collections.sort(rogues);
		Collections.sort(shamans);
		Collections.sort(warlocks);
		Collections.sort(warriors);
		
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
