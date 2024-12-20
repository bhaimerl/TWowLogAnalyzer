package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import helper.Raids.Boss;
import helper.classes.NameClassWrapper;
import helper.classes.utils.besonderes.BarovUtils;

public class General {

	
	public static ArrayList<String> getLogsFromBossByName(String bossname, ArrayList<String> completeLog) {
		ArrayList<String> result = new ArrayList<>();
		int i=1;
		for (String currentLine : completeLog) {
			if(currentLine.contains(bossname)) {
				if(!currentLine.contains("Hunter's Mark")) {
					result.add(currentLine);
				}
			}
		}
		return result;
	}
	
	public static ArrayList<String> getOnlySunderLogs(ArrayList<String> logList) {
		ArrayList<String> sunderLogs = new ArrayList<>();
		for (String string : logList) {
			if(string.contains("Sunder Armor")) {
				sunderLogs.add(string);
			}
		}
		return sunderLogs;
	}
	

	
	
	
	public static ArrayList<String> getCursesOnBoss(ArrayList<String> logList) {
		ArrayList<String> sunderLogs = new ArrayList<>();
		for (String string : logList) {
			if(string.contains("is afflicted by Curse")) {
				if(string.contains("Recklessness") || string.contains("Shadow") || string.contains("Elements")) {
					sunderLogs.add(string);
				}
			}
		}
		return sunderLogs;
	}
	
	
	
	//is afflicted by Sunder Armor (5).
	public static String getSunderArmorStack(String logline) {
		String stack="0";
		String saStr = "is afflicted by Sunder Armor (";
		int hit = logline.indexOf(saStr);
		if(logline.contains(saStr)) {
			stack= logline.substring(hit+saStr.length());
			stack = stack.replace(").", "");
//			String name = getPlayerName(logline);
//			String time = getTimeFromLog(logline);
//			stack = time+" "+name+" Sunder Armor Nr: "+stack;
		}
		return stack;
	}

	public static String getTimeFromLog(String logline) {
		return getEntryAtPosition(logline,1);
	}

	public static String getPlayerName(String logline) {
		String playerName = "";
		if(logline!=null) {
			StringTokenizer strTok = new StringTokenizer(logline, " ");
			strTok.nextElement();
			strTok.nextElement();
			//Name
			playerName = (String) strTok.nextElement();
		}
		return playerName;
	}
	
	public static String getEntryAtPosition(String logline, int position) {
		String result = "";
		if(logline!=null) {
			int cnt = 0;
			StringTokenizer strTok = new StringTokenizer(logline, " ");
			while(strTok.hasMoreElements()) {
				result = strTok.nextToken();
				if(cnt == position) {
					return result;
				}
				cnt +=1;
			}
		}
		return result;
	}	
	
	public static int getAmountGains(String searchString, String logline) {
		int amount = 0;
		if(logline!=null) {
			String date, time, name, gains, value;
			if(logline.indexOf(searchString)>=0) {
				StringTokenizer strTok = new StringTokenizer(logline, " ");
				date = (String) strTok.nextElement(); //Datum
				time = (String) strTok.nextElement(); //Uhrzeit
				name = (String) strTok.nextElement();//Name
				gains = (String) strTok.nextElement();//gains
				//amount
				try {
					//manchmal gibt es komische Eintraege, wie Klarasprudel (Kel'Th..) gains.....
					while(gains!=null && gains.indexOf("gains")==-1) {
						gains = (String) strTok.nextElement();
					}
					amount+=Integer.parseInt(strTok.nextElement()+"");
				} catch(Exception e) {
					System.out.println("Problem bei: "+name+" mit dieser Zeile: "+logline);
				}
			}
		}
		return amount;
	}		
	
	public static int getAmountAtEnd(String searchString, String logline) {
		return getAmountAtEnd(searchString, "KANNNICHTDRIN_SEIN", logline);
	}
	
	public static int getDmgAmountForNightFall(String bossname, String logline) {
		int dmgAmount = 0;
		//suffers?
		if(logline.indexOf("suffers")>=0) {
			dmgAmount = getSuffersDmg(bossname, logline);
		} else {
			//for at end?
			dmgAmount = getAmountAtEnd(bossname, logline);
		}
		return dmgAmount;
	}
	
	public static int getSuffersDmg(String bossname, String logLine) {
		int result = 0;
		if(logLine.indexOf("suffers")>=0 && logLine.indexOf(bossname)>=0) {
			StringTokenizer strTok = new StringTokenizer(logLine, " ");
			strTok.nextElement(); //Datum
			strTok.nextElement(); //Uhrzeit
			strTok.nextElement(); //Name
			strTok.nextElement();//suffers			
			if(bossname.contains(" ")) {
				while(!strTok.nextToken().contains("suffers")) {
					continue;
				}
			}
			String amount = strTok.nextElement()+"";//amount
			result = Integer.parseInt(amount); //suffersAmount
		}
		return result;
	}

	//Patchwerk 's Hateful Strike hits Kranette for 
	//11/15 21:35:09.309  Patchwerk 's Hateful Strike hits Kranette for 5607. (292 blocked) (500 absorbed)
	//11/15 21:35:09.309  Lyndrell gains 56 Mana from Lyndrell 's Holy Champion.
	public static int getAmountAtEnd(String searchString, String searchString1,  String logline) {
		int amount = 0;
		if(logline!=null) {
			String name;
			//(self damage)  entfernen
			if(logline.indexOf(searchString)>=0 || logline.indexOf(searchString1)>=0) {
				logline = logline.replaceFirst("(self damage) ", "");
				logline = logline.replaceFirst("'s ", "");
				StringTokenizer strTok = new StringTokenizer(logline, " ");
				String date = ""+strTok.nextElement(); //Datum
				String time = ""+strTok.nextElement(); //Uhrzeit
				name = ""+strTok.nextElement(); //Wer

				String ability="";
				//alles was nun kommt bis zu hits oder crits ist ability
				ability = strTok.nextElement()+"";
				String ability2 = strTok.nextElement()+"";
				while(ability2.indexOf("crits")==-1 && ability2.indexOf("hits")==-1) {
					ability = ability+ability2;
					ability2 = strTok.nextElement()+"";
				}
				String crits = ability2; //hits oder crits

				//wer wurde getroffen
				String target = strTok.nextElement()+"";
				String target2 = strTok.nextElement()+"";
				while(target2.indexOf("for")==-1) {
					target = target+target2;
					target2 = strTok.nextElement()+"";
				}
				
				String forEntry = target2;	
				//amount
				try {
					String amnt = strTok.nextElement()+"";
					String rmvAmount =amnt+"";
					rmvAmount = rmvAmount.replace(".", "");
					amount+=Integer.parseInt(rmvAmount);
				} catch(Exception e) {
					System.out.println("Problem bei: "+name+" mit dieser Zeile: "+logline);
					}
			}
		}
		return amount;
	}	
	
	//Patchwerk 's Hateful Strike hits Kranette for 
		//11/15 21:35:09.309  Patchwerk 's Hateful Strike hits Kranette for 5607. (292 blocked) (500 absorbed)
		//11/15 21:35:09.309  Lyndrell gains 56 Mana from Lyndrell 's Holy Champion.
		public static String getTarget(String searchString, String searchString1,  String logline) {
			String targetino = "";
			if(logline!=null) {
				String name;
				//(self damage)  entfernen
				if(logline.indexOf(searchString)>=0 || logline.indexOf(searchString1)>=0) {
					logline = logline.replaceFirst("(self damage) ", "");
					logline = logline.replaceFirst("'s ", "");
					StringTokenizer strTok = new StringTokenizer(logline, " ");
					String date = ""+strTok.nextElement(); //Datum
					String time = ""+strTok.nextElement(); //Uhrzeit
					name = ""+strTok.nextElement(); //Wer

					String ability="";
					//alles was nun kommt bis zu hits oder crits ist ability
					ability = strTok.nextElement()+"";
					String ability2 = strTok.nextElement()+"";
					while(ability2.indexOf("crits")==-1 && ability2.indexOf("hits")==-1) {
						ability = ability+ability2;
						ability2 = strTok.nextElement()+"";
					}
					String crits = ability2; //hits oder crits

					//wer wurde getroffen
					String target = strTok.nextElement()+"";
					String target2 = strTok.nextElement()+"";
					while(target2.indexOf("for")==-1) {
						target = target+target2;
						target2 = strTok.nextElement()+"";
					}
					targetino = target;
				}
			}
			return targetino;
		}			
		
		public static void flushAllClasses()  {
			WarriorUtils.warriorMap = new HashMap<>();
			RogueUtils.rogueMap = new HashMap<>();
			WarlockUtils.warlockMap = new HashMap<>(); 
			MageUtils.mageMap = new HashMap<>();
			HunterUtils.hunterMap = new HashMap<>();
			BossUtils.bossMap = new HashMap<>();
			BarovUtils.barovMap = new HashMap<>();
		}
		
		public static String getPlayerClass(HashMap<String, ArrayList<NameClassWrapper>> allPlayers, String playerName) {
			String playerClass="";
			Set<String> classSet = allPlayers.keySet();
			for (String currentClass : classSet) {
				ArrayList<NameClassWrapper> ncwList = allPlayers.get(currentClass);
				for (NameClassWrapper ncw : ncwList) {
					if(ncw.getName().equalsIgnoreCase(playerName)) {
						return ncw.getPlayerClass();
					}
				}
			}
			return playerClass;
		}
		
		public static boolean isPlayerInClassList(HashMap<String, ArrayList<NameClassWrapper>> allPlayers, String playerName, String className) {
			boolean isThere = false;
			ArrayList<NameClassWrapper> allPlayersOfThisClass = allPlayers.get(className);
			if(allPlayersOfThisClass!=null && allPlayersOfThisClass.size()>0) {
				for (NameClassWrapper nameClassWrapper : allPlayersOfThisClass) {
					if(nameClassWrapper!=null && nameClassWrapper.getName().equals(playerName)) {
						return true;
					}
				}
			}
			return isThere;
		}

	
}
