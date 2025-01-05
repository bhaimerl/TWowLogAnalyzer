package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.Hunter;
import helper.classes.NameClassWrapper;

public class HunterUtils {
	
	public static HashMap<String, Hunter> hunterMap = new HashMap<>();;

    private static Hunter getHunterByName(String name) {
        return hunterMap.computeIfAbsent(name, k -> new Hunter());
    }

    private static void updateHunterStats(String logline, String playerName, String keyword, Consumer<Hunter> action) {
        if (logline.contains(keyword)) {
        	Hunter hunter = getHunterByName(playerName);
            action.accept(hunter);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Hunter> hitAction, Consumer<Hunter> critAction,
                                       Consumer<Hunter> highestAmountAction) {
    	updateHunterStats(logline, playerName, hitKeyword, hitAction);
    	updateHunterStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Hunter hunter = getHunterByName(playerName);
            highestAmountAction.accept(hunter);
        }
    }
    
	public static void findEntryForHunter(String logline,
			HashMap<String, ArrayList<NameClassWrapper>> allValidPLayers) {
		String currentPlayer = General.getPlayerName(logline);
		if (!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.HUNTER)) {
			return;
		}
		updateHunterStats(logline, currentPlayer, Constants.manFromVampirismTouch, mage -> mage
				.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline)));

		updateHunterStats(logline, currentPlayer, Constants.manaFromJudgement, mage -> {
			if (logline.contains("Mana")) {
				mage.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
			}
		});

		updateHunterStats(logline, currentPlayer, Constants.manaFromBOW, mage -> {
			if (logline.contains("gains")) {
				mage.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
			}
		});
		
//		updateHunterStats(logline, currentPlayer, Constants.serpentStringGains, Hunter::incrementSerpentSting);
//		updateHunterStats(logline, currentPlayer, Constants.serpentStringAfflicted, Hunter::incrementSerpentSting);

		
		processAbility(logline, currentPlayer, Constants.autoShotHit, Constants.autoShotCrit,
				Hunter::incrementAutoShotHit, Hunter::incrementAutoShotCrit,
				hunter -> hunter.updateHighestAutoShotAmount(
						General.getAmountAtEnd(Constants.autoShotHit, Constants.autoShotCrit, logline),
						General.getTarget(Constants.autoShotHit, Constants.autoShotCrit, logline)));
		
		processAbility(logline, currentPlayer, Constants.steadyShotHits, Constants.steadyShotCrits,
				Hunter::incrementSteadyShotHit, Hunter::incrementSteadyShotCrit,
				hunter -> hunter.updateHighestSteadyShotAmount(
						General.getAmountAtEnd(Constants.steadyShotHits, Constants.steadyShotCrits, logline),
						General.getTarget(Constants.steadyShotHits, Constants.steadyShotCrits, logline)));
		
		processAbility(logline, currentPlayer, Constants.multiShotHits, Constants.multiSHotCrits,
				Hunter::incrementMultiShotHit, Hunter::incrementMultiShotCrit,
				hunter -> hunter.updateHighestMultiShotAmount(
						General.getAmountAtEnd(Constants.multiShotHits, Constants.multiSHotCrits, logline),
						General.getTarget(Constants.multiShotHits, Constants.multiSHotCrits, logline)));

		processAbility(logline, currentPlayer, Constants.arcaneShotHits, Constants.arcaneShotCrits,
				Hunter::incrementArcaneShotHit, Hunter::incrementArcaneShotCrit,
				hunter -> hunter.updateHighestArcaneShotAmount(
						General.getAmountAtEnd(Constants.arcaneShotHits, Constants.arcaneShotCrits, logline),
						General.getTarget(Constants.arcaneShotHits, Constants.arcaneShotCrits, logline)));

		processAbility(logline, currentPlayer, Constants.extraShotHits, Constants.extraShotCrits,
				Hunter::incrementExtraShotHit, Hunter::incrementExtraShotCrit,
				hunter -> hunter.updateHighestExtraShotAmount(
						General.getAmountAtEnd(Constants.extraShotHits, Constants.extraShotCrits, logline),
						General.getTarget(Constants.extraShotHits, Constants.extraShotCrits, logline)));

		processAbility(logline, currentPlayer, Constants.aimedShotHits, Constants.aimedShotCrtis,
				Hunter::incrementAimedShotHit, Hunter::incrementAimedShotCrit,
				hunter -> hunter.updateHighestAimedShotAmount(
						General.getAmountAtEnd(Constants.aimedShotHits, Constants.aimedShotCrtis, logline),
						General.getTarget(Constants.aimedShotHits, Constants.aimedShotCrtis, logline)));
		
	}
	
    public static String getHunterHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!hunterMap.isEmpty()) {
			SortedSet<String> hunters =  new TreeSet<>(hunterMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.HUNTERCOLOR).append(";'>")
                  .append("<td colspan='16'>"+Constants.HUNTER+"</td></tr><tr>")
                  .append("<th>Name</th>")
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
                  .append("<th>AutoShot Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Auto</th>")
            	  .append("<th>Steady Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Steady</th>")
            	  .append("<th>MultiShot Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Multi</th>")
            	  .append("<th>ArcaneShot Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Arcane</th>")
            	  .append("<th>ExtraShot Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Extra</th>")
            	  .append("<th>AimedShot Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Aimed</th>")
//            	  .append("<th>Serpent Sting applied</th>")
                  .append("</tr>");

            for (String huterName : hunters) {
                Hunter hunter= hunterMap.get(huterName);
                    strBuf.append("<tr>")
                          .append("<td>").append(huterName).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(hunter.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(hunter.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(hunter.getManFrombow()).append("</td>")
                          .append("<td>").append(hunter.getAutoShotHit()).append(" / ").append(hunter.getAutoShotCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestAutoShot()).append(" => ").append(hunter.getHighestAutoShotTarget()).append("</td>")
                          .append("<td>").append(hunter.getSteadyShotHits()).append(" / ").append(hunter.getSteadyShotCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestSteadyShot()).append(" => ").append(hunter.getHighestSteadyShotTarget()).append("</td>")
                          .append("<td>").append(hunter.getMultiShotHits()).append(" / ").append(hunter.getMultiSHotCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestMultiShot()).append(" => ").append(hunter.getHighestMultiShotTarget()).append("</td>")
                          .append("<td>").append(hunter.getArcaneShotHits()).append(" / ").append(hunter.getArcaneShotCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestArcaneShot()).append(" => ").append(hunter.getHighestArcaneShotTarget()).append("</td>")
                          .append("<td>").append(hunter.getExtraShotHits()).append(" / ").append(hunter.getExtraShotCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestExtraShot()).append(" => ").append(hunter.getHighestExtraShotTarget()).append("</td>")
                          .append("<td>").append(hunter.getAimedShotHits()).append(" / ").append(hunter.getAimedShotCrtis()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(hunter.getHighestAimedShot()).append(" => ").append(hunter.getHighestAimedShotTarget()).append("</td>")
//                          .append("<td>").append(hunter.getSerpentStringAmount()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
 
	public static boolean isHunter(String logline) {
		boolean isHunter = false;
		if((logline.contains(Constants.autoShotHit) || 
			logline.contains(Constants.steadyShotHits) || 
			logline.contains(Constants.multiShotHits) || 
			logline.contains(Constants.aimedShotHits) || 
			logline.contains(Constants.arcaneShotHits) )) {
			isHunter = true;
		}
		return isHunter;
	}
	

}
