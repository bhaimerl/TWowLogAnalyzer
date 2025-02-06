package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.Mage;
import helper.classes.NameClassWrapper;

public class MageUtils {
	
	public static HashMap<String, Mage> mageMap = new HashMap<>();;

    private static Mage getMageByName(String name) {
        return mageMap.computeIfAbsent(name, k -> new Mage());
    }

    private static void updateMageStats(String logline, String playerName, String keyword, Consumer<Mage> action) {
        if (logline.contains(keyword)) {
            Mage mage = getMageByName(playerName);
            action.accept(mage);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Mage> hitAction, Consumer<Mage> critAction,
                                       Consumer<Mage> highestAmountAction) {
    	updateMageStats(logline, playerName, hitKeyword, hitAction);
    	updateMageStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
            Mage mage = getMageByName(playerName);
            highestAmountAction.accept(mage);
        }
    }

	
	public static void findEntryForMage(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	//bezieht sich die aktuelle Zeile auf einen Warlock?
    	String currentPlayer = General.getPlayerName(logline);
    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.MAGE)) {
    		return;
    	}
		
        processAbility(logline, currentPlayer, Constants.fireBlastHits, Constants.fireBlastCrits, 
                Mage::incrementFireBlastHits, Mage::incrementFireBlastCrits,
                mage -> mage.updateHighestFireBlastAmount(General.getAmountAtEnd(Constants.fireBlastHits, Constants.fireBlastCrits, logline),
                									General.getTarget(Constants.fireBlastHits, Constants.fireBlastCrits, logline))
        );
        processAbility(logline, currentPlayer, Constants.fireBallHits, Constants.fireBallCrits, 
            Mage::incrementFireBallHits, Mage::incrementFireBallCrits,
            mage -> mage.updateHighestFireBallAmount(General.getAmountAtEnd(Constants.fireBallHits, Constants.fireBallCrits, logline),
                                                     General.getTarget(Constants.fireBallHits, Constants.fireBallCrits, logline))
        );
        processAbility(logline, currentPlayer, Constants.arcaneRuptureHits, Constants.arcaneRuptureCrits, 
                Mage::incrementArcaneRuptureHits, Mage::incrementArcaneRuptureCrits,
                mage -> mage.updateHighestArcaneRuptureAmount(General.getAmountAtEnd(Constants.arcaneRuptureHits, Constants.arcaneRuptureCrits, logline),
                                                    General.getTarget(Constants.arcaneRuptureHits, Constants.arcaneRuptureCrits, logline))
        );
        processAbility(logline, currentPlayer, Constants.arcaneSurgeHits, Constants.arcaneSurgeCrits, 
                Mage::incrementArcaneSurgeHits, Mage::incrementArcaneSurgeCrits,
                mage -> mage.updateHighestArcaneSurgeAmount(General.getAmountAtEnd(Constants.arcaneSurgeHits, Constants.arcaneSurgeCrits, logline),
                                                    General.getTarget(Constants.arcaneSurgeHits, Constants.arcaneSurgeCrits, logline))
        );
        processAbility(logline, currentPlayer, Constants.arcaneMissleHits, Constants.arcaneMissleCrits, 
                Mage::incrementArcaneMisslesHits, Mage::incrementArcaneMisslesCrits,
                mage -> mage.updateHighestArcaneMissleAmount(General.getAmountAtEnd(Constants.arcaneMissleHits, Constants.arcaneMissleCrits, logline),
                                                    General.getTarget(Constants.arcaneMissleHits, Constants.arcaneMissleCrits, logline))
        );
        processAbility(logline, currentPlayer, Constants.pyroBlastHits, Constants.pyroBlastCrits, 
                Mage::incrementPyroBlastHits, Mage::incrementPyroBlastCrits,
                mage -> mage.updateHighestPyroBlastAmount(General.getAmountAtEnd(Constants.pyroBlastHits, Constants.pyroBlastCrits, logline),
                                                    General.getTarget(Constants.pyroBlastHits, Constants.pyroBlastCrits, logline))
        );

        processAbility(logline, currentPlayer, Constants.scorchHits, Constants.scorchCrits, 
                Mage::incrementScorchHits, Mage::incrementScorchCrits,
                mage -> mage.updateHighestScorchAmount(General.getAmountAtEnd(Constants.scorchHits, Constants.scorchCrits, logline),
                                                    General.getTarget(Constants.scorchHits, Constants.scorchCrits, logline))
        );
        
        //CasterCommon
		//Mana gains
        updateMageStats(logline, currentPlayer, Constants.manFromVampirismTouch, mage -> 
        	mage.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline))
        );

        updateMageStats(logline, currentPlayer, Constants.manaFromJudgement, mage -> {
        if (logline.contains("Mana")) {
        	mage.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
        }
        });

        updateMageStats(logline, currentPlayer, Constants.manaFromBOW, mage -> {
        if (logline.contains("gains")) {
        	mage.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
        }
        });
	}
	
	
	//	int highestFireBlast = 0; / highestFireBall / highestArcaneRupture / highestArcaneSurge / highestArcaneMissle / highestPyroBlast / highestScorch

    public static String getMagesHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!mageMap.isEmpty()) {
			SortedSet<String> mages =  new TreeSet<>(mageMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.MAGECOLOR).append(";'>")
                  .append("<td colspan='18'>"+Constants.MAGE+"</td></tr><tr>")
                  .append("<th>Name</th>")
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
                  .append("<th>FireBlast Hit/Crit</th><th class=\"toggle-column-highlights\">Highest FBlast</th>")
            	  .append("<th>FireBall Hit/Crit</th><th class=\"toggle-column-highlights\">Highest FBall</th>")
            	  .append("<th>ArcaneRupture Hit/Crit</th><th class=\"toggle-column-highlights\">Highest Rupture</th>")
            	  .append("<th>ArcaneSurge Hit/Crit</th><th class=\"toggle-column-highlights\">Highest Surge</th>")
            	  .append("<th>ArcaneMissles Hit/Crit</th><th class=\"toggle-column-highlights\">Highest Missle</th>")
            	  .append("<th>PyroBlast Hit/Crit</th><th class=\"toggle-column-highlights\">Highest PBlast</th>")
            	  .append("<th>Scorch Hit/Crit</th><th class=\"toggle-column-highlights\">Highest Scorch</th>")
                  .append("</tr>");

            for (String mageName : mages) {
                Mage mage= mageMap.get(mageName);
                    strBuf.append("<tr>")
                          .append("<td>").append(mageName).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(mage.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(mage.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(mage.getManaFromBow()).append("</td>")
                          .append("<td>").append(mage.getFireBlastHits()).append(" / ").append(mage.getFireBlastCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestFireBlast()).append(" => ").append(mage.getHighestFireBlastTarget()).append("</td>")

                          .append("<td>").append(mage.getFireBallHits()).append(" / ").append(mage.getFireBallCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestFireBall()).append(" => ").append(mage.getHighestFireBallTarget()).append("</td>")
                          
                          .append("<td>").append(mage.getArcaneRuptureHits()).append(" / ").append(mage.getArcaneRuptureCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestArcaneRupture()).append(" => ").append(mage.getHighestArcaneRuptureTarget()).append("</td>")

                          .append("<td>").append(mage.getArcaneSurgeHits()).append(" / ").append(mage.getArcaneSurgeCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestArcaneSurge()).append(" => ").append(mage.getHighestArcaneSurgeTarget()).append("</td>")

                          .append("<td>").append(mage.getArcaneMissleHits()).append(" / ").append(mage.getArcaneMissleCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestArcaneMissle()).append(" => ").append(mage.getHighestArcaneMissleTarget()).append("</td>")

                          .append("<td>").append(mage.getPyroBlastHits()).append(" / ").append(mage.getPyroBlastCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestPyroBlast()).append(" => ").append(mage.getHighestPyroBlastTarget()).append("</td>")

                          .append("<td>").append(mage.getScorchHits()).append(" / ").append(mage.getScorchCrits()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(mage.getHighestScorch()).append(" => ").append(mage.getHigehstScorchTarget()).append("</td>")

                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
    
	public static boolean isMage(String logline) {
		boolean isMage = false;
		if((logline.contains(Constants.fireBlastHits) || 
			logline.contains(Constants.fireBallHits) || 
			logline.contains(Constants.arcaneMissleHits) || 
			logline.contains(Constants.pyroBlastHits) || 
			logline.contains(Constants.arcaneRuptureHits) )) {
			isMage = true;
		}
		return isMage;
	}
    
	

}
