package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.Druid;
import helper.classes.Healer;
import helper.classes.NameClassWrapper;
import helper.classes.Shaman;

public class ShamanUtils {
	
		
	public static boolean isShaman(String logline) {
		boolean isShaman = false;
		if((logline.contains(Constants.chainHeal) || 
			logline.contains(Constants.lesserHealingWave) || 
			logline.contains(Constants.lighningBolt) || 
			logline.contains(Constants.fireNova) || 
			logline.contains(Constants.frostShock) || 
			logline.contains(Constants.lightningStrike) || 
			logline.contains(Constants.castWindfuryTotem) || 
			logline.contains(Constants.castsEarthTotem) || 
			logline.contains(Constants.castsManSpringTotem) )) {
			isShaman = true;
		}
		return isShaman;
	}
	
	
	public static HashMap<String, Shaman> shamanMap = new HashMap<>();

    public static Shaman getShamanByName(String name) {
        return shamanMap.computeIfAbsent(name, k -> new Shaman());
    }

    private static void updateShamanStats(String logline, String playerName, String keyword, Consumer<Shaman> action) {
        if (logline.contains(keyword)) {
        	Shaman shaman = getShamanByName(playerName);
            action.accept(shaman);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Shaman> hitAction, Consumer<Shaman> critAction,
                                       Consumer<Shaman> highestAmountAction) {
    	updateShamanStats(logline, playerName, hitKeyword, hitAction);
    	updateShamanStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Shaman shaman = getShamanByName(playerName);
            highestAmountAction.accept(shaman);
        }
    }
    
	public static void findEntryForShaman(String logline,
			HashMap<String, ArrayList<NameClassWrapper>> allValidPLayers) {
		String currentPlayer = General.getPlayerName(logline);
		if (!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.SHAMAN)) {
			return;
		}    
		updateShamanStats(logline, currentPlayer, Constants.manFromVampirismTouch, mage -> mage
				.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline)));

		updateShamanStats(logline, currentPlayer, Constants.manaFromJudgement, mage -> {
			if (logline.contains("Mana")) {
				mage.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
			}
		});

		updateShamanStats(logline, currentPlayer, Constants.manaFromBOW, mage -> {
			if (logline.contains("gains")) {
				mage.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
			}
		});
		updateShamanStats(logline, currentPlayer, Constants.windfury, Shaman::incrementWindFury);
		updateShamanStats(logline, currentPlayer, Constants.flametongue, Shaman::incrementFlameTongue);
		
		
		processAbility(logline, currentPlayer, Constants.chainHealHit, Constants.chainHealCrit,
				Shaman::incrementChainHeal, Shaman::incrementChainHeal,
				shaman -> shaman.updateHighestChainHealAmount(
						General.getAmountAtEnd(Constants.chainHealHit, Constants.chainHealCrit, logline),
						General.getTarget(Constants.chainHealHit, Constants.chainHealCrit, logline)));		

		processAbility(logline, currentPlayer, Constants.lesserHealingWaveHit, Constants.lesserHealingWaveCrits,
				Shaman::incrementLesserHealingWave, Shaman::incrementLesserHealingWave,
				shaman -> shaman.updateHighestLesserHealingWaveAmount(
						General.getAmountAtEnd(Constants.lesserHealingWaveHit, Constants.lesserHealingWaveCrits, logline),
						General.getTarget(Constants.lesserHealingWaveHit, Constants.lesserHealingWaveCrits, logline)));		
		
		processAbility(logline, currentPlayer, Constants.healingWaveHit, Constants.healingWaveCrits,
				Shaman::incrementHealingWave, Shaman::incrementHealingWave,
				shaman -> shaman.updateHighestHealingWaveAmount(
						General.getAmountAtEnd(Constants.healingWaveHit, Constants.healingWaveCrits, logline),
						General.getTarget(Constants.healingWaveHit, Constants.healingWaveCrits, logline)));		
		

		processAbility(logline, currentPlayer, Constants.lightningBoltHit, Constants.lightningBoltCrit,
				Shaman::incrementLightningBolt, Shaman::incrementLightningBolt,
				shaman -> shaman.updateHighestLighningBoltAmount(
						General.getAmountAtEnd(Constants.lightningBoltHit, Constants.lightningBoltCrit, logline),
						General.getTarget(Constants.lightningBoltHit, Constants.lightningBoltCrit, logline)));		
		
		processAbility(logline, currentPlayer, Constants.fireNovaHit, Constants.fireNovaCrit,
				Shaman::incrementFireNova, Shaman::incrementFireNova,
				shaman -> shaman.updateHighestFireNovaAmount(
						General.getAmountAtEnd(Constants.fireNovaHit, Constants.fireNovaCrit, logline),
						General.getTarget(Constants.fireNovaHit, Constants.fireNovaCrit, logline)));		
		
		processAbility(logline, currentPlayer, Constants.frostShockHit, Constants.frostShockCrit,
				Shaman::incrementFrostShock, Shaman::incrementFrostShock,
				shaman -> shaman.updateHighestFrostShockAmount(
						General.getAmountAtEnd(Constants.frostShockHit, Constants.frostShockCrit, logline),
						General.getTarget(Constants.frostShockHit, Constants.frostShockCrit, logline)));		
		
		processAbility(logline, currentPlayer, Constants.lighningStrikeHit, Constants.lightningSTrikeCrit,
				Shaman::incrementLightningStrike, Shaman::incrementLightningStrike,
				shaman -> shaman.updateHighestLigningStrikeAmount(
						General.getAmountAtEnd(Constants.lighningStrikeHit, Constants.lightningSTrikeCrit, logline),
						General.getTarget(Constants.lighningStrikeHit, Constants.lightningSTrikeCrit, logline)));		

		processAbility(logline, currentPlayer, Constants.chainLigheningHits, Constants.chainLigheningCrits,
				Shaman::incrementChainLightening, Shaman::incrementChainLightening,
				shaman -> shaman.updateHighestChainLighteningAmount(
						General.getAmountAtEnd(Constants.chainLigheningHits, Constants.chainLigheningCrits, logline),
						General.getTarget(Constants.chainLigheningHits, Constants.chainLigheningCrits, logline)));		
		
	}
	
    public static String getShamanHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!shamanMap.isEmpty()) {
			SortedSet<String> shamans =  new TreeSet<>(shamanMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.SHAMANCOLOR).append(";'>")
                  .append("<td colspan='24'>"+Constants.SHAMAN+"</td></tr><tr>")
                  .append("<th>Name</th>")
      			  .append("<th>Death</th>")
      			  .append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>")                                    
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
      			  .append("<th>Windfury Procs</th>")
      			  .append("<th>Flametonue Procs</th>")
                  .append("<th>ChainHeals</th><th class=\"toggle-column-highlights\">Max CH</th>")
                  .append("<th>LesserHealingWaves</th><th class=\"toggle-column-highlights\">Max LHW</th>")
                  .append("<th>HealingWaves</th><th class=\"toggle-column-highlights\">Max HW</th>")
            	  .append("<th>ChainLightening</th><th class=\"toggle-column-highlights\">Max CL</th>")
            	  .append("<th>LighningBolts</th><th class=\"toggle-column-highlights\">Max LB</th>")
            	  .append("<th>FireNova</th><th class=\"toggle-column-highlights\">Max FN</th>")
            	  .append("<th>FrostShock</th><th class=\"toggle-column-highlights\">Max FS</th>")
            	  .append("<th>LighningStrike</th><th class=\"toggle-column-highlights\">Max LS</th>")
                  .append("</tr>");

            for (String shamanName : shamans) {
                Shaman shaman = shamanMap.get(shamanName);
                //healercheck
                if(shaman.getLesserHealingWave()>5 || shaman.getChainHeal() > 5) {
                	Healer.addHealer(shamanName);
                }
                    strBuf.append("<tr>")
                          .append("<td>").append(shamanName).append("</td>")
      					  .append("<td>"+shaman.getDeathCounter()+"</td>")
      					  .append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(shaman)+"</td>")                                                    
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(shaman.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(shaman.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(shaman.getManaFromBow()).append("</td>")
        				  .append("<td>"+shaman.getWindFury()+"</td>")
        				  .append("<td>"+shaman.getFlametongue()+"</td>")                          
                          .append("<td>").append(shaman.getChainHeal()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestChainHeal()).append(" => ").append(shaman.getHighestChainTarget()).append("</td>")
                          .append("<td>").append(shaman.getLesserHealingWave()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestLesserHealingWave()).append(" => ").append(shaman.getHighestLesserHealingWaveTarget()).append("</td>")
                          .append("<td>").append(shaman.getHealingWave()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestHealingWave()).append(" => ").append(shaman.getHighestHealingWaveTarget()).append("</td>")
                          .append("<td>").append(shaman.getChainLightening()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestChainLightening()).append(" => ").append(shaman.getHighestChainLighteningTarget()).append("</td>")
                          .append("<td>").append(shaman.getLightningBolt()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestLighningBolt()).append(" => ").append(shaman.getHighestLighningBoltTarget()).append("</td>")
                          .append("<td>").append(shaman.getFireNova()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestFireNova()).append(" => ").append(shaman.getHighestFireNovaTarget()).append("</td>")
                          .append("<td>").append(shaman.getFrostShock()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestFrostShock()).append(" => ").append(shaman.getHighestFrostShockTarget()).append("</td>")
                          .append("<td>").append(shaman.getLightningStrike()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(shaman.getHighestLigningStrike()).append(" => ").append(shaman.getHighestLigningStrikeTarget()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
	
	
	

}
