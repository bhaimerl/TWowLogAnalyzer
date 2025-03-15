package helper.classes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.joda.time.DateTime;

import helper.classes.Player;

public class DeathCalculationUtils {
	
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
}
