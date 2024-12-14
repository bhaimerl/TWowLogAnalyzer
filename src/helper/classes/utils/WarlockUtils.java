package helper.classes.utils;

import java.util.HashMap;
import java.util.function.Consumer;

import helper.classes.Warlock;

public class WarlockUtils {

    public static HashMap<String, Warlock> warlockMap = new HashMap<>(); 

    private static Warlock getWarlockByName(String name) {
        return warlockMap.computeIfAbsent(name, k -> new Warlock());
    }

    private static void updateWarlockStats(String logline, String keyword, Consumer<Warlock> action) {
        if (logline.contains(keyword)) {
            Warlock warlock = getWarlockByName(General.getPlayerName(logline));
            action.accept(warlock);
        }
    }

    private static void processAbility(String logline, String hitKeyword, String critKeyword,
                                       Consumer<Warlock> hitAction, Consumer<Warlock> critAction,
                                       Consumer<Warlock> highestAmountAction) {
        updateWarlockStats(logline, hitKeyword, hitAction);
        updateWarlockStats(logline, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
            Warlock warlock = getWarlockByName(General.getPlayerName(logline));
            highestAmountAction.accept(warlock);
        }
    }

    public static void findEntryForWarlock(String logline) {
        updateWarlockStats(logline, "gains Shadow Trance", Warlock::incrementShadowtrance);

        updateWarlockStats(logline, "Life Tap.", warlock -> {
            warlock.incrementLifeTaps();
            warlock.addLifeTapMana(General.getAmountGains("Life Tap.", logline));
        });

        updateWarlockStats(logline, Constants.manFromVampirismTouch, warlock -> 
            warlock.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline))
        );

        updateWarlockStats(logline, Constants.manaFromJudgement, warlock -> {
            if (logline.contains("Mana")) {
                warlock.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
            }
        });

        updateWarlockStats(logline, Constants.manaFromBOW, warlock -> {
            if (logline.contains("gains")) {
                warlock.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
            }
        });

        // Shadow Bolt
        processAbility(logline, Constants.shadowBoltHit, Constants.shadowBoltCrit, 
            Warlock::incrementShadowBoltHits, Warlock::incrementShadowBoltCrits,
            warlock -> warlock.updateHighestSBAmount(General.getAmountAtEnd(Constants.shadowBoltHit, Constants.shadowBoltCrit, logline),
                                                     General.getTarget(Constants.shadowBoltHit, Constants.shadowBoltCrit, logline))
        );

        // Soul Fire
        processAbility(logline, Constants.soulFirehit, Constants.soulFireCrit, 
            Warlock::incrementSoulFireHits, Warlock::incrementSoulFireCrits,
            warlock -> warlock.updateHighestSFAmount(General.getAmountAtEnd(Constants.soulFirehit, Constants.soulFireCrit, logline),
                                                     General.getTarget(Constants.soulFirehit, Constants.soulFireCrit, logline))
        );

        // Searing Pain
        processAbility(logline, Constants.searingPainhit, Constants.searingPainCrit, 
            Warlock::incrementSearingPainHits, Warlock::incrementSearingPainCrits,
            warlock -> warlock.updateHighestSPAmount(General.getAmountAtEnd(Constants.searingPainhit, Constants.searingPainCrit, logline),
                                                     General.getTarget(Constants.searingPainhit, Constants.searingPainCrit, logline))
        );

        // Immolate
        updateWarlockStats(logline, Constants.immolateHit, Warlock::incrementImmolateHits);
        updateWarlockStats(logline, Constants.immolateCrit, Warlock::incrementImmolateCrits);

        // Conflagrate
        processAbility(logline, Constants.conflagrateHit, Constants.conflagrateCrit, 
            Warlock::incrementConflagrateHits, Warlock::incrementConflagrateCrits,
            warlock -> warlock.updateHighestCflgrAmount(General.getAmountAtEnd(Constants.conflagrateHit, Constants.conflagrateCrit, logline),
                                                     General.getTarget(Constants.conflagrateHit, Constants.conflagrateCrit, logline))
        );
    }

    public static String getWarlocks() {
        StringBuilder strBuf = new StringBuilder();
        if (!warlockMap.isEmpty()) {
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.WARLOCKCOLOR).append(";'>")
                  .append("<td colspan='16'>WARLOCK</td></tr><tr>")
                  .append("<th>Name</th><th>Shadowtrance</th><th>LifeTaps</th><th>Mana LifeTap</th>")
                  .append("<th>Mana VampiricTouch</th><th>Mana Judgement</th><th>Mana BOW</th>")
                  .append("<th>ShadowBolt Hit/Crit</th><th>Highest SB</th><th>SoulFire Hit/Crit</th>")
                  .append("<th>Highest SF</th><th>SearingPain Hit/Crit</th><th>Highest SP</th>")
                  .append("<th>Immolate Hit/Crit</th><th>Conflagrate Hit/Crit</th><th>Highest Cflgrt</th></tr>");

            for (String warlockName : warlockMap.keySet()) {
                Warlock warlock = warlockMap.get(warlockName);
                if (warlock.getLifeTaps() > 0) {
                    strBuf.append("<tr>")
                          .append("<td>").append(warlockName).append("</td>")
                          .append("<td>").append(warlock.getShadowtrance()).append("</td>")
                          .append("<td>").append(warlock.getLifeTaps()).append("</td>")
                          .append("<td>").append(warlock.getLifeTapMana()).append("</td>")
                          .append("<td>").append(warlock.getManaFromVampiricTouch()).append("</td>")
                          .append("<td>").append(warlock.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td>").append(warlock.getManaFromBow()).append("</td>")
                          .append("<td>").append(warlock.getShadowBoltHits()).append(" / ").append(warlock.getShadowBoltCrits()).append("</td>")
                          .append("<td>").append(warlock.getHighestSBAmount()).append(" => ").append(warlock.getHighestSBTarget()).append("</td>")
                          .append("<td>").append(warlock.getSoulFireHits()).append(" / ").append(warlock.getSoulFireCrits()).append("</td>")
                          .append("<td>").append(warlock.getHighestSFAmount()).append(" => ").append(warlock.getHighestSFTarget()).append("</td>")
                          .append("<td>").append(warlock.getSearingPainHits()).append(" / ").append(warlock.getSearingPainCrits()).append("</td>")
                          .append("<td>").append(warlock.getHighestSPAmount()).append(" => ").append(warlock.getHighestSPTarget()).append("</td>")
                          .append("<td>").append(warlock.getImmolateHits()).append(" / ").append(warlock.getImmolateCrits()).append("</td>")
                          .append("<td>").append(warlock.getConflagrateHits()).append(" / ").append(warlock.getConflagrateCrits()).append("</td>")
                          .append("<td>").append(warlock.getHighestCflgrAmount()).append(" => ").append(warlock.getHighestCflgrTarget()).append("</td>")
                          .append("</tr>");
                }
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
}
