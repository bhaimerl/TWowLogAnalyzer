package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.joda.time.DateTime;

import helper.classes.Player;

public class DeathCalculationUtils {
	
	
	
	public static void getDeathCause(ArrayList<String> allLogs) {
	    Map<String, String> playerClassCache = new HashMap<>();
	    Map<String, Player> playerCache = new HashMap<>();

	    // Durchlaufe alle Logs einmal und finde Tode
	    for (String logEntry : allLogs) {
	        int diesIndex = logEntry.indexOf(" dies.");
	        if (diesIndex != -1) { // Falls "dies" im Log vorkommt
	            String currentPlayer = General.getPlayerName(logEntry);

	            if (Players.isNameAValidPlayerInRaid(currentPlayer)) {
	                int msTogoBack = Players.isPlayerAPriest(currentPlayer) ? 11000 : 1500;
	                DateTime deathTime = General.getTimeFromLogAsDateTime(logEntry);

	                // Schnelle Suche nach Logs im relevanten Intervall (statt komplette Liste durchsuchen)
	                List<String> logsInInterval = General.getLogsWithinIntervallPLusTolerance(
	                        deathTime.minusMillis(msTogoBack), deathTime, 1000, allLogs);

	                String deathCause = "unclear why! (" + General.getTimeFromLog(logEntry) + ")";
	                for (int i = logsInInterval.size() - 1; i >= 0; i--) {
	                    String deathLine = logsInInterval.get(i);
	                    if ((deathLine.contains(" is killed") || deathLine.contains("hits " + currentPlayer) ||
	                         deathLine.contains("crits " + currentPlayer) || deathLine.contains(currentPlayer+" suffers")) &&
	                        !deathLine.contains(currentPlayer + " dies.")) {
	                        deathCause = deathLine.substring(18) + " (" + General.getTimeFromLog(deathLine).substring(0, 8) + ")";
	                        break;
	                    }
	                }

	                // Spielerklasse nur einmal bestimmen und cachen
	                String playerClass = playerClassCache.computeIfAbsent(currentPlayer, Players::getClassFromPlayer);
	                
	                if (playerClass != null) {
	                    Player player = playerCache.computeIfAbsent(currentPlayer, name -> {
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
	                        return playerClassMap.getOrDefault(playerClass, n -> null).apply(name);
	                    });

	                    if (player != null) {
	                        player.incrementDeath();
	                        player.addDeathCause(deathCause);
	                    }
	                }
	            }
	        }
	    }
	}	
		
}
