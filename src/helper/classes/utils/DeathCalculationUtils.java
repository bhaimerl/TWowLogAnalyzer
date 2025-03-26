package helper.classes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import helper.classes.Player;

public class DeathCalculationUtils {
	
//	
//	public static void getDeathCause(ArrayList<String> allLogs) {
//	    long startTime1 = System.nanoTime();
//		//getDeathCauseAlt(allLogs);
//		long endTime1 = System.nanoTime();
//	    long startTime2 = System.nanoTime();
//		getDeathCauseNeu(allLogs);
//		long endTime2 = System.nanoTime();
//	    double durationInMilliseconds1 = (endTime1 - startTime1) / 1_000_000_000.0;
//	    double durationInMilliseconds2 = (endTime2 - startTime2) / 1_000_000_000.0;
//		System.out.println( "getDeathCause() alt vs neu: "+durationInMilliseconds1+"  vs. "+durationInMilliseconds2);
//	}	
		
	
	public static void getDeathCause(ArrayList<String> allLogs) {
	    for (String logEntry : allLogs) {
	        if (logEntry.contains("dies")) {
	            String currentPlayer = General.getPlayerName(logEntry);

	            if (Players.isNameAValidPlayerInRaid(currentPlayer) && logEntry.contains(currentPlayer + " dies.")) {
	                //System.out.println("ok found a valid player died " + currentPlayer);

	                int msTogoBack = Players.isPlayerAPriest(currentPlayer) ? 11000 : 1500;
	                DateTime deathTime = General.getTimeFromLogAsDateTime(logEntry);
	                List<String> logsInInterval = General.getLogsWithinIntervallPLusTolerance(
	                        deathTime.minusMillis(msTogoBack), deathTime, 1000, allLogs);

	                String deathCause = "unclear why! (" + General.getTimeFromLog(logEntry) + ")";
	                for (int i = logsInInterval.size() - 1; i >= 0; i--) {
	                    String deathLine = logsInInterval.get(i);
	                    if ((deathLine.contains(currentPlayer + " is killed") || 
	                         deathLine.contains("hits " + currentPlayer) || 
	                         deathLine.contains("crits " + currentPlayer) || 
	                         deathLine.contains(currentPlayer + " suffers")) && 
	                        !deathLine.contains(currentPlayer + " dies.")) {
	                        deathCause = deathLine.substring(18) + " (" + General.getTimeFromLog(deathLine).substring(0, 8) + ")";
	                        break;
	                    }
	                }

	                String playerClass = Players.getClassFromPlayer(currentPlayer);
	                if (playerClass != null) {
	                    Map<String, Function<String, Player>> playerClassMap = Map.of(
	                        "WARRIOR", WarriorUtils::getWarriorByName,
	                        "DRUID", DruidUtils::getDruidByName,
	                        "HUNTER", HunterUtils::getHunterByName,
	                        "MAGE", MageUtils::getMageByName,
	                        "PALADIN", PaladinUtils::getPaladinByName,
	                        "PRIEST", PriestUtils::getPriestByName,
	                        "ROGUE", RogueUtils::getRogueByName,
	                        "SHAMAN", ShamanUtils::getShamanByName,
	                        "WARLOCK", WarlockUtils::getWarlockByName
	                    );

	                    Player player = playerClassMap.getOrDefault(playerClass, name -> null).apply(currentPlayer);
	                    if (player != null) {
	                        player.incrementDeath();
	                        player.addDeathCause(deathCause);
	                    }
	                }

	                //System.out.println("Ok player " + currentPlayer + " (" + playerClass + ") died by " + deathCause);
	            }
	        }
	    }
	}
	
	
//	public static void getDeathCauseNeu(ArrayList<String> allLogs) {
//	    // Liste von Spielernamen holen
//	    List<String> allPlayers = Players.allPlayerNamesInRaid();
//	    
//	    // Filtern der Logeinträge, die einen Spieler-Tod betreffen
//	    long startTime2 = System.nanoTime();
//	    ArrayList<String> nurDieRelevantenZeilenWoEinSpielerStirbt = getDeathsForPlayers(allLogs, allPlayers);
//		long endTime2 = System.nanoTime();
//	    double durationInMilliseconds1 = (endTime2 - startTime2) / 1_000_000_000.0;
//	    System.out.println("Calced all player dies entries takes: "+durationInMilliseconds1);
//	    
//	    // Einmalige Erstellung der Map für die Klassen von Spielern
//	    Map<String, Function<String, Player>> playerClassMap = Map.of(
//	        "WARRIOR", WarriorUtils::getWarriorByName,
//	        "DRUID", DruidUtils::getDruidByName,
//	        "HUNTER", HunterUtils::getHunterByName,
//	        "MAGE", MageUtils::getMageByName,
//	        "PALADIN", PaladinUtils::getPaladinByName,
//	        "PRIEST", PriestUtils::getPriestByName,
//	        "ROGUE", RogueUtils::getRogueByName,
//	        "SHAMAN", ShamanUtils::getShamanByName,
//	        "WARLOCK", WarlockUtils::getWarlockByName
//	    );
//
//	    // Gehe alle relevanten Logeinträge durch
//	    for (String logEntry : nurDieRelevantenZeilenWoEinSpielerStirbt) {
//	        String currentPlayer = General.getPlayerName(logEntry);
//	        
//	        // Berechne die Todeszeit
//	        int msTogoBack = Players.isPlayerAPriest(currentPlayer) ? 11000 : 1500;
//	        DateTime deathTime = General.getTimeFromLogAsDateTime(logEntry);
//
//	        // Hole Logs im Zeitintervall der letzten Sekunden des Todes
//	        List<String> logsInInterval = General.getLogsWithinIntervallPLusTolerance(deathTime.minusMillis(msTogoBack), deathTime, 1000, allLogs);
//
//	        String deathCause = "unclear why! (" + General.getTimeFromLog(logEntry) + ")";
//
//	        // Durchsuche Logs im Zeitintervall
//	        for (String deathLine : logsInInterval) {
//	            if (deathLine.contains(currentPlayer + " is killed") || 
//	                deathLine.contains("hits " + currentPlayer) || 
//	                deathLine.contains("crits " + currentPlayer) || 
//	                deathLine.contains(currentPlayer + " suffers") && 
//	                !deathLine.contains(currentPlayer + " dies.")) {
//
//	                deathCause = deathLine.substring(18) + " (" + General.getTimeFromLog(deathLine).substring(0, 8) + ")";
//	                break;
//	            }
//	        }
//
//	        // Hole die Klasse des Spielers
//	        String playerClass = Players.getClassFromPlayer(currentPlayer);
//	        if (playerClass != null) {
//	            // Hole den entsprechenden Spieler über die Map
//	            Player player = playerClassMap.getOrDefault(playerClass, name -> null).apply(currentPlayer);
//	            if (player != null) {
//	                player.incrementDeath();
//	                player.addDeathCause(deathCause);
//	            }
//	        }
//	    }
//	}
	
	
//	// Methode zum Finden von Todesursachen für bestimmte Spieler
//    public static ArrayList<String> getDeathsForPlayers(ArrayList<String> allLogs, List<String> allPlayers) {
//        ArrayList<String> deathLogs = new ArrayList<>();
//        
//        // Erstelle eine Regex für alle Spieler aus der Liste
//        StringBuilder playerPatternBuilder = new StringBuilder();
//        for (String playerName : allPlayers) {
//            if (playerPatternBuilder.length() > 0) {
//                playerPatternBuilder.append("|"); // Oder-Verknüpfung für die Spieler
//            }
//            playerPatternBuilder.append(playerName); // Füge den Spielernamen zum Pattern hinzu
//        }
//        
//        // Falls keine Namen in der Liste sind, gibt es keine gültigen Einträge
//        if (playerPatternBuilder.length() == 0) {
//            return deathLogs;
//        }
//        
//        // Das vollständige Regex-Muster für die Suche nach Spielername + " dies."
//        String deathPatternString = "^.*(" + playerPatternBuilder.toString() + ") dies\\.$";
//        Pattern deathPattern = Pattern.compile(deathPatternString);  // Sucht nach "spielername dies."
//        
//        // Gehe jede Logzeile durch
//        for (String logEntry : allLogs) {
//            Matcher matcher = deathPattern.matcher(logEntry);
//            
//            // Wenn das Muster gefunden wird, füge die Zeile zur Liste der Todesursachen hinzu
//            if (matcher.find()) {
//                deathLogs.add(logEntry);
//            }
//        }
//        
//        return deathLogs;
//    }	
}
