package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.NameClassWrapper;
import helper.classes.Warlock;

public class WarlockUtils {

    public static HashMap<String, Warlock> warlockMap = new HashMap<>(); 

    public static Warlock getWarlockByName(String name) {
        return warlockMap.computeIfAbsent(name, k -> new Warlock());
    }

    private static void updateWarlockStats(String logline, String playerName, String keyword, Consumer<Warlock> action) {
        if (logline.contains(keyword)) {
            Warlock warlock = getWarlockByName(playerName);
            action.accept(warlock);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Warlock> hitAction, Consumer<Warlock> critAction,
                                       Consumer<Warlock> highestAmountAction) {
        updateWarlockStats(logline, playerName, hitKeyword, hitAction);
        updateWarlockStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
            Warlock warlock = getWarlockByName(playerName);
            highestAmountAction.accept(warlock);
        }
    }

    public static void findEntryForWarlock(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	
    	//bezieht sich die aktuelle Zeile auf einen Warlock?
    	String currentPlayer = General.getPlayerName(logline);
    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.WARLOCK)) {
    		return;
    	}
        updateWarlockStats(logline, currentPlayer, "gains Shadow Trance", Warlock::incrementShadowtrance);

        //curses
        updateWarlockStats(logline, currentPlayer, Constants.coE, Warlock::incrementCoeCount);
        updateWarlockStats(logline, currentPlayer, Constants.coS, Warlock::incrementCoSCount);
        updateWarlockStats(logline, currentPlayer, Constants.coR, Warlock::incrementCoRCount);

        
        updateWarlockStats(logline, currentPlayer, "Life Tap.", warlock -> {
            warlock.incrementLifeTaps();
            warlock.addLifeTapMana(General.getAmountGains("Life Tap.", logline));
        });

        updateWarlockStats(logline, currentPlayer, Constants.manFromVampirismTouch, warlock -> 
            warlock.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline))
        );

        updateWarlockStats(logline, currentPlayer, Constants.manaFromJudgement, warlock -> {
            if (logline.contains("Mana")) {
                warlock.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
            }
        });

        updateWarlockStats(logline, currentPlayer, Constants.manaFromBOW, warlock -> {
            if (logline.contains("gains")) {
                warlock.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
            }
        });

        // Shadow Bolt
        processAbility(logline, currentPlayer, Constants.shadowBoltHit, Constants.shadowBoltCrit, 
            Warlock::incrementShadowBoltHits, Warlock::incrementShadowBoltCrits,
            warlock -> warlock.updateHighestSBAmount(General.getAmountAtEnd(Constants.shadowBoltHit, Constants.shadowBoltCrit, logline),
                                                     General.getTarget(Constants.shadowBoltHit, Constants.shadowBoltCrit, logline))
        );

        // Soul Fire
        processAbility(logline, currentPlayer, Constants.soulFirehit, Constants.soulFireCrit, 
            Warlock::incrementSoulFireHits, Warlock::incrementSoulFireCrits,
            warlock -> warlock.updateHighestSFAmount(General.getAmountAtEnd(Constants.soulFirehit, Constants.soulFireCrit, logline),
                                                     General.getTarget(Constants.soulFirehit, Constants.soulFireCrit, logline))
        );

        // Searing Pain
        processAbility(logline, currentPlayer, Constants.searingPainhit, Constants.searingPainCrit, 
            Warlock::incrementSearingPainHits, Warlock::incrementSearingPainCrits,
            warlock -> warlock.updateHighestSPAmount(General.getAmountAtEnd(Constants.searingPainhit, Constants.searingPainCrit, logline),
                                                     General.getTarget(Constants.searingPainhit, Constants.searingPainCrit, logline))
        );

        // Immolate
        updateWarlockStats(logline, currentPlayer, Constants.immolateHit, Warlock::incrementImmolateHits);
        updateWarlockStats(logline, currentPlayer, Constants.immolateCrit, Warlock::incrementImmolateCrits);

        // Conflagrate
        processAbility(logline, currentPlayer, Constants.conflagrateHit, Constants.conflagrateCrit, 
            Warlock::incrementConflagrateHits, Warlock::incrementConflagrateCrits,
            warlock -> warlock.updateHighestCflgrAmount(General.getAmountAtEnd(Constants.conflagrateHit, Constants.conflagrateCrit, logline),
                                                     General.getTarget(Constants.conflagrateHit, Constants.conflagrateCrit, logline))
        );
    }

    public static String getWarlocksHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!warlockMap.isEmpty()) {
			SortedSet<String> warlocks =  new TreeSet<>(warlockMap.keySet());

            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.WARLOCKCOLOR).append(";'>")
                  .append("<td colspan='19'>"+Constants.WARLOCK+"</td></tr><tr>")
                  .append("<th>Name</th>")
      			  .append("<th>Death</th>")
      			  .append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>")                                                      
                  .append("<th>STrance</th><th>LifeTaps</th><th class=\"toggle-column\" style=\"display: none;\">Mana LifeTap</th>")
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
                  .append("<th>CoE|CoS|CoR</th>")                  
                  .append("<th>ShadowBolt Hit/Crit</th><th class=\"toggle-column-highlights\">Max SB</th><th>SoulFire Hit/Crit</th>")
                  .append("<th class=\"toggle-column-highlights\">Max SF</th><th>SearingPain Hit/Crit</th><th class=\"toggle-column-highlights\">Max SP</th>")
                  .append("<th>Immolate Hit/Crit</th><th>Conflagrate Hit/Crit</th><th class=\"toggle-column-highlights\">Max Cflgrt</th></tr>");

            for (String warlockName : warlocks) {
                Warlock warlock = warlockMap.get(warlockName);
                    strBuf.append("<tr>")
                          .append("<td>").append(warlockName).append("</td>")
      					  .append("<td>"+warlock.getDeathCounter()+"</td>")
      					  .append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(warlock)+"</td>")                                                    
                          .append("<td>").append(warlock.getShadowtrance()).append("</td>")
                          .append("<td>").append(warlock.getLifeTaps()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(warlock.getLifeTapMana()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(warlock.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(warlock.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(warlock.getManaFromBow()).append("</td>")
                          .append("<td>").append(warlock.getCoeCount()+" | "+warlock.getCosCount()+" | "+warlock.getCorCount()).append("</td>")
                          .append("<td>").append(warlock.getShadowBoltHits()).append(" / ").append(warlock.getShadowBoltCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(warlock.getHighestSBAmount()).append(" => ").append(warlock.getHighestSBTarget()).append("</td>")
                          .append("<td>").append(warlock.getSoulFireHits()).append(" / ").append(warlock.getSoulFireCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(warlock.getHighestSFAmount()).append(" => ").append(warlock.getHighestSFTarget()).append("</td>")
                          .append("<td>").append(warlock.getSearingPainHits()).append(" / ").append(warlock.getSearingPainCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(warlock.getHighestSPAmount()).append(" => ").append(warlock.getHighestSPTarget()).append("</td>")
                          .append("<td>").append(warlock.getImmolateHits()).append(" / ").append(warlock.getImmolateCrits()).append("</td>")
                          .append("<td>").append(warlock.getConflagrateHits()).append(" / ").append(warlock.getConflagrateCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(warlock.getHighestCflgrAmount()).append(" => ").append(warlock.getHighestCflgrTarget()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }   
	public static boolean isWarlock(String logline) {
		boolean isWarlock = false;
		if((logline.contains(Constants.shadowBoltHit) || 
			logline.contains(Constants.soulFirehit) || 
			logline.contains(Constants.searingPainhit) || 
			logline.contains(Constants.immolateHit) || 
			logline.contains(Constants.conflagrateHit) )) {
			isWarlock = true;
		}
		return isWarlock;
	}
    
}
