package helper.classes.utils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import helper.classes.Healer;
import helper.classes.NameClassWrapper;
import helper.classes.Player;
import helper.classes.utils.besonderes.BarovUtils;
import helper.classes.utils.besonderes.TradetLoot;

public class General {
	public static String regexHitCrit = "(?:hits|crits) (\\w+)";
	public static Pattern patternHitCrit = Pattern.compile(regexHitCrit);
    
	public static String regexBlockLine = "(?:hits|crits) (\\w+) for (\\d+)\\. \\((\\d+) blocked\\)";
	public static Pattern patternBlocks = Pattern.compile(regexBlockLine);
	
	public static ArrayList<String> getLogsFromBossByName(String bossname, ArrayList<String> completeLog) {
		ArrayList<String> result = new ArrayList<>();
		for (String currentLine : completeLog) {
			if(currentLine.contains(bossname)) {
				if(!currentLine.contains("Hunter's Mark") && !currentLine.contains("Essence of Sapphiron") && 
				   !currentLine.contains("LOOT:") &&
				   !currentLine.contains("Grobbulus casts Bombard Slime") && !currentLine.contains("Grobbulus begins to cast Bombard Slime.")) {
					result.add(currentLine);
				}
			}
		}
		return result;
	}
	
	public static String getPlayerNameHitted(String logline) {
		String retVal = null;
    	//3/7 20:03:58.744  Patchwerk 's Hateful Strike hits Kranette for 4790. (298 blocked) (500 absorbed)		
		if(logline.contains("blocked)") && (logline.contains("hits") || logline.contains("crits")) ) {
			//ok we have a blocked entry
			Matcher matcher = General.patternHitCrit.matcher(logline);
			 if (matcher.find()) {
                 String hitted = matcher.group(1);
                 retVal = hitted;
             }			
		}
		return retVal;
	}    	
	
	public static ArrayList<String> getOnlySunderLogs(ArrayList<String> logList) {
		List<String> sunderLogs = new ArrayList<>();
		for (String string : logList) {
			if(string.contains("Sunder Armor")) {
				sunderLogs.add(string);
			}
		}
		return (ArrayList<String>) sunderLogs;
	}
	
	public static ArrayList<String> getLogsUntilPLusTolerance(DateTime endTime, int addToleranceMs, ArrayList<String> completeLogs) {
		ArrayList<String> intervallLogs = new ArrayList<>();
		try {
			for (String currentLine : completeLogs) {
				DateTime currentLineDate = getTimeFromLogAsDateTime(currentLine);
				if((currentLineDate.isBefore(endTime.plusMillis(addToleranceMs)) )) {
					intervallLogs.add(currentLine);
				}
			}
		}catch(Exception e) {
			System.out.println("getLogsUntilPLusTolerance()"+e.getLocalizedMessage());
		}
		return intervallLogs;
	}
	
	public static ArrayList<String> getLogsBeginningPLusTolerance(DateTime startTime, int addToleranceMs, ArrayList<String> completeLogs) {
		ArrayList<String> intervallLogs = new ArrayList<>();
		try {
			for (String currentLine : completeLogs) {
				DateTime currentLineDate = getTimeFromLogAsDateTime(currentLine);
				if((currentLineDate.isAfter(startTime.plusMillis(addToleranceMs)))) {
					intervallLogs.add(currentLine);
				}
			}
		}catch(Exception e) {
			System.out.println("getLogsBeginningPLusTolerance()"+e.getLocalizedMessage());
		}
		return intervallLogs;
	}
	
	public static String getOnlyTimeFromDateTimeString(String dtstr) {
		if(dtstr!=null && dtstr.length()>0) {
			DateTime dateTimeFromString = getDateTimeFromString(dtstr);
			Date dt = dateTimeFromString.toDate();
			return Constants.onlyTime.format(dt);
		} else return "";
	}
	
	
	public static ArrayList<String> getLogsWithinIntervallPLusTolerance(DateTime startTime, DateTime endTime, int addToleranceMs, ArrayList<String> completeLogs) {
		ArrayList<String> intervallLogs = new ArrayList<>();
		try {
			for (String currentLine : completeLogs) {
				DateTime currentLineDate = getTimeFromLogAsDateTime(currentLine);
				if((currentLineDate.isAfter(startTime)  && currentLineDate.isBefore(endTime.plusMillis(addToleranceMs)) )) {
					intervallLogs.add(currentLine);
				}
			}
		}catch(Exception e) {
			System.out.println("getLogsWithinIntervallPLusTolerance()"+e.getLocalizedMessage());
		}
		return intervallLogs;
	}
	
	public static void bla() {
		org.joda.time.DateTime dt =new org.joda.time.DateTime();
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
	
	public static String getStringFromDateTime(DateTime dt) {
		return Constants.sdf.format(dt.toDate());
	}
	public static DateTime getTimeFromLogAsDateTime(String logline) {
		Date retDate = null;
		String dayPlusTime = getEntryAtPosition(logline, 0)+" "+getEntryAtPosition(logline, 1);
		try {
			retDate = Constants.sdf.parse(dayPlusTime);
		}catch(Exception e) {
			System.out.println("getTimeFromLogAsDateTime() Error in line: "+logline+" "+e);
		}
		return new DateTime(retDate);
	}
	
	public static DateTime getDateTimeFromString(String str) {
		Date retDate = null;
		String dayPlusTime = str;
		try {
			retDate = Constants.sdf.parse(dayPlusTime);
		}catch(Exception e) {
			System.out.println("getDateTimeFromString() Error in line: "+str+" "+e);
		}
		return new DateTime(retDate);
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
	
	
	
	public static String getPlayerNameFromEndFrom(String logline) {
		String playerName = null;
		// Regulärer Ausdruck, um den Heiler-Namen zu extrahieren
		String priestAbility ="";
		if(logline.contains("'s Greater Heal")) {
			priestAbility = "'s Greater Heal";
		} else if(logline.contains("'s Mind Flay")) {
			priestAbility = "'s Mind Flay";
		}
        Pattern pattern = Pattern.compile("from (\\w+) "+priestAbility);
        Matcher matcher = pattern.matcher(logline);
        if (matcher.find()) {
        	playerName = matcher.group(1); // Gefundener Name
        }
		return playerName;
	}
	

	public static String getEntryAtPosition(String logline, int position, String delimiter) {
		String result = "";
		if(logline!=null) {
			int cnt = 0;
			StringTokenizer strTok = new StringTokenizer(logline, delimiter);
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
	
	
	public static String getEntryAtPosition(String logline, int position) {
		return getEntryAtPosition(logline, position, " ");
	}	
	
	public static int getAmountGains(String searchString, String logline) {
	    int amount = 0;

	    if (logline != null && logline.contains(searchString)) {
	        // Split the log line into tokens
	        String[] tokens = logline.split(" ");

	        try {
	            // Suche nach dem Token "gains" und parsene den Betrag danach
	            for (int i = 0; i < tokens.length; i++) {
	                if (tokens[i].equals("gains")) {
	                    amount += Integer.parseInt(tokens[i + 1]);
	                    break; // Wir haben den "gains"-Wert gefunden, also abbrechen
	                }
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Problem bei der Verarbeitung von: " + logline);
	        }
	    }
	    return amount;
	}
	
//	public static int getAmountGainsAlt(String searchString, String logline) {
//		int amount = 0;
//		if(logline!=null) {
//			String date, time, name, gains, value;
//			if(logline.indexOf(searchString)>=0) {
//				StringTokenizer strTok = new StringTokenizer(logline, " ");
//				date = (String) strTok.nextElement(); //Datum
//				time = (String) strTok.nextElement(); //Uhrzeit
//				name = (String) strTok.nextElement();//Name
//				gains = (String) strTok.nextElement();//gains
//				//amount
//				try {
//					//manchmal gibt es komische Eintraege, wie Klarasprudel (Kel'Th..) gains.....
//					while(gains!=null && gains.indexOf("gains")==-1) {
//						gains = (String) strTok.nextElement();
//					}
//					amount+=Integer.parseInt(strTok.nextElement()+"");
//				} catch(Exception e) {
//					System.out.println("Problem bei: "+name+" mit dieser Zeile: "+logline);
//				}
//			}
//		}
//		return amount;
//	}		
	
	public static int getAmountAtEnd(String searchString, String logline) {
		return getAmountAtEnd(searchString, "KANNNICHTDRIN_SEIN", logline);
	}
	
	public static int getDmgAmountForNightFall(String bossname, String logline) {
		int dmgAmount = 0;
		//suffers?
		if(logline.indexOf("suffers")>=0) {
			dmgAmount = getSuffersDmg(bossname, logline);
		} else if (logline.contains("reflects")){
			//not yet implemented
		} else {
			//for at end?
			dmgAmount = getAmountAtEnd(bossname, logline);
		}
		return dmgAmount;
	}
	
	
	public static int getSuffersDmg(String bossname, String logLine) {
	    int result = 0;
	    if (logLine == null || !logLine.contains("suffers")) {
	        return result; // Frühzeitiger Exit, wenn "suffers" nicht vorhanden
	    }

	    // Ersetze "Eye of C'Thun" nur, wenn es wirklich vorkommt
	    logLine = logLine.replace("Eye of C'Thun", "C'Thun");

	    // Überprüfe, ob der Bossname im Log vorkommt und "suffers"
	    if (logLine.contains(bossname + " suffers")) {
	        String[] tokens = logLine.split(" ");
	        
	        // Finde die Stelle, an der der Bossname und "suffers" vorkommen
	        for (int i = 0; i < tokens.length; i++) {
	            if (tokens[i].equals(bossname) && i + 1 < tokens.length && tokens[i + 1].equals("suffers")) {
	                // Nächsten Token nach "suffers" ist der Schadenswert
	                try {
	                    result = Integer.parseInt(tokens[i + 2]); // Der Wert nach "suffers"
	                } catch (NumberFormatException e) {
	                    System.out.println("Fehler bei der Zahl: " + logLine + " " + e);
	                }
	                break; // Wenn wir das gefunden haben, brauchen wir nicht weiter zu suchen
	            }
	        }
	    }
	    return result;
	}	
	
	
	
	public static int getAmountAtEnd(String searchString, String searchString1, String logline) {
	    int amount = 0;

	    if (logline != null && (logline.contains(searchString) || logline.contains(searchString1))) {
	        // Entferne 'self damage' und "'s" direkt ohne Regex
	        logline = logline.replace("self damage ", "").replace("'s ", "");

	        // Teile den Logline in Token
	        String[] tokens = logline.split(" ");

	        try {
	            String name = tokens[2];  // Der Name steht immer an Index 2
	            int idx = 3; // Startindex für die Fähigkeitenbezeichnung

	            // Durchlaufen der Tokens, um die Fähigkeit zu extrahieren
	            String ability = tokens[idx];
	            idx++;
	            while (idx < tokens.length && !tokens[idx].matches("hits|crits|heals|critically")) {
	                ability += " " + tokens[idx];
	                idx++;
	            }

	            // Abgleich des Typs (hits, crits, heals)
	            String crits = tokens[idx];  // hits, crits, heals oder critically heals
	            idx++;  // Target name kommt danach

	            // Ziel extrahieren
	            String target = tokens[idx];
	            idx++;
	            while (idx < tokens.length && !tokens[idx].equals("for")) {
	                target += " " + tokens[idx];
	                idx++;
	            }

	            // Betrag extrahieren und zu 'amount' hinzufügen
	            String amountStr = tokens[idx + 1];
	            amount += Integer.parseInt(amountStr.replace(".", ""));
	        } catch (Exception e) {
	            System.out.println("Problem bei der Verarbeitung der Zeile: " + logline);
	        }
	    }

	    return amount;
	}
//
//	//Patchwerk 's Hateful Strike hits Kranette for 
//	//11/15 21:35:09.309  Patchwerk 's Hateful Strike hits Kranette for 5607. (292 blocked) (500 absorbed)
//	//11/15 21:35:09.309  Lyndrell gains 56 Mana from Lyndrell 's Holy Champion.
//	public static int getAmountAtEndAlt(String searchString, String searchString1,  String logline) {
//		int amount = 0;
//		if(logline!=null) {
//			String name;
//			//(self damage)  entfernen
//			if(logline.indexOf(searchString)>=0 || logline.indexOf(searchString1)>=0) {
//				logline = logline.replaceFirst("(self damage) ", "");
//				logline = logline.replaceFirst("'s ", "");
//				StringTokenizer strTok = new StringTokenizer(logline, " ");
//				String date = ""+strTok.nextElement(); //Datum
//				String time = ""+strTok.nextElement(); //Uhrzeit
//				name = ""+strTok.nextElement(); //Wer
//
//				String ability="";
//				//alles was nun kommt bis zu hits oder crits ist ability
//				ability = strTok.nextElement()+"";
//				String ability2 = strTok.nextElement()+"";
//				while(ability2.indexOf("crits")==-1 && ability2.indexOf("hits")==-1 && ability2.indexOf("heals")==-1 && ability2.indexOf("critically heals")==-1) {
//					ability = ability+ability2;
//					try {
//						ability2 = strTok.nextElement()+"";
//					}catch(Exception e) {
//						System.out.println("numm");
//					}
//				}
//				String crits = ability2; //hits oder crits
//
//				//wer wurde getroffen
//				String target = strTok.nextElement()+"";
//				String target2 = strTok.nextElement()+"";
//				while(target2.indexOf("for")==-1) {
//					target = target+target2;
//					target2 = strTok.nextElement()+"";
//				}
//				
//				String forEntry = target2;	
//				//amount
//				try {
//					String amnt = strTok.nextElement()+"";
//					String rmvAmount =amnt+"";
//					rmvAmount = rmvAmount.replace(".", "");
//					amount+=Integer.parseInt(rmvAmount);
//				} catch(Exception e) {
//					System.out.println("Problem bei: "+name+" mit dieser Zeile: "+logline);
//					}
//			}
//		}
//		return amount;
//	}	
	
	public static String getTarget(String searchString, String searchString1, String logline) {
	    if (logline != null && (logline.contains(searchString) || logline.contains(searchString1))) {
	        // Entferne 'self damage' und "'s" direkt ohne Regex
	        logline = logline.replace("self damage ", "").replace("'s ", "");

	        // Teile die Zeile anhand von Leerzeichen
	        String[] tokens = logline.split(" ");

	        String target = "";
	        try {
	            // Die Zeile wird von links nach rechts analysiert
	            int idx = 3; // Ab dem Index 3 nach Datum, Uhrzeit und Name

	            // Fähigkeit extrahieren
	            StringBuilder ability = new StringBuilder(tokens[idx]);
	            idx++;
	            Set<String> validAbilities = new HashSet<>();
	            validAbilities.add("hits");
	            validAbilities.add("crits");
	            validAbilities.add("heals");
	            validAbilities.add("critically heals");

	            // Durchlaufe die Tokens, bis wir auf eines der gültigen Trefferwörter stoßen
	            while (idx < tokens.length && !validAbilities.contains(tokens[idx])) {
	                ability.append(" ").append(tokens[idx]);
	                idx++;
	            }

	            // Trefferart (hits, crits, heals, critically heals) finden
	            String crits = tokens[idx];  // hits, crits, heals oder critically heals
	            idx++;  // Zielname kommt danach

	            // Ziel extrahieren (alles bis "for")
	            target = tokens[idx];
	            idx++;
	            while (idx < tokens.length && !tokens[idx].equals("for")) {
	                target += " " + tokens[idx];
	                idx++;
	            }

	        } catch (Exception e) {
	            System.out.println("Problem bei der Verarbeitung der Zeile: " + logline);
	        }
	        target = target.replaceAll("\\s+", "");
	        return target;
	    }

	    return "";
	}
	
		
		public static void flushAllClasses()  {
			WarriorUtils.warriorMap = new HashMap<>();
			RogueUtils.rogueMap = new HashMap<>();
			WarlockUtils.warlockMap = new HashMap<>(); 
			MageUtils.mageMap = new HashMap<>();
			HunterUtils.hunterMap = new HashMap<>();
			PaladinUtils.paladinMap = new HashMap<>();	
			DruidUtils.druidMap = new HashMap<>();
			PriestUtils.priestMap = new HashMap<>();
			//ShamanUtils
			ShamanUtils.shamanMap = new HashMap<>();
			BossUtils.bossMap = new HashMap<>();
			BarovUtils.barovMap = new HashMap<>();
			LootUtils.playerLootMap = new HashMap<>();
			LootUtils.lootRessouces = new HashMap<>();
			LootUtils.tlList = new ArrayList<TradetLoot>();
			Healer.healerMap = new ArrayList<>();
		}
		
		public static void flushAllGuild()  {
			//flush
			Players.mainGuild = null;
			Players.logYear = null;
			Players.uniqueList = new ArrayList<>();
			Players.guildCountMap = new HashMap<>();
		}
		
		
		//runtimeCalc
		public static Date getStartDate() {
			return new Date();
		}

		public static long getDifferenceInSecondsGivenAndNow(Date startDate) {
			return getDifferenceInSeconds(startDate, new Date());
		}
		public static long getDifferenceInSeconds(Date startDate, Date endDate) {
			return ChronoUnit.SECONDS.between(startDate.toInstant(), endDate.toInstant());
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
		
		public static ArrayList<String> getStringWithDelimterAsArrayList(String str, String delimiter) {
			ArrayList<String> retList = new ArrayList<>();
			StringTokenizer strTok = new StringTokenizer(str, delimiter);
			while(strTok.hasMoreTokens()) {
				retList.add(strTok.nextToken());
			}
			return retList;
		}

	
}
