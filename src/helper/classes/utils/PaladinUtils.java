package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import helper.classes.Healer;
import helper.classes.NameClassWrapper;
import helper.classes.Paladin;
import helper.classes.Warrior;

public class PaladinUtils {
	
	public static HashMap<String, Paladin> paladinMap = new HashMap<>();;

    public static Paladin getPaladinByName(String name) {
        return paladinMap.computeIfAbsent(name, k -> new Paladin());
    }

    private static void updatePaladinStats(String logline, String playerName, String keyword, Consumer<Paladin> action) {
        if (logline.contains(keyword)) {
        	Paladin paladin = getPaladinByName(playerName);
            action.accept(paladin);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Paladin> hitAction, Consumer<Paladin> critAction,
                                       Consumer<Paladin> highestAmountAction) {
    	updatePaladinStats(logline, playerName, hitKeyword, hitAction);
    	updatePaladinStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Paladin paladin = getPaladinByName(playerName);
            highestAmountAction.accept(paladin);
        }
    }
    
    
    private static boolean updatePaladinStatsForBlock(String logline, String playerName) {
        if (logline.contains("blocked)") && (logline.contains("hits "+playerName) || logline.contains("crits "+playerName))) {
            Paladin paladin = getPaladinByName(playerName);
            Matcher matcher = General.patternBlocks.matcher(logline);
            if (matcher.find()) {
                String hittedPlayer = matcher.group(1);
                int incomingDmg = Integer.parseInt(matcher.group(2));
                int dmgBlocked = Integer.parseInt(matcher.group(3));
                paladin.incrementBlockedCnt();
                paladin.addDmgAmountBlocked(dmgBlocked);
                paladin.addDmgAmountCompleteBeforeBlock(dmgBlocked + incomingDmg);
                return true;
            }            
        }
        return false;
    }    
    
	public static void findEntryForPaladin(String logline,
			HashMap<String, ArrayList<NameClassWrapper>> allValidPLayers) {
		String currentPlayer = General.getPlayerName(logline);
		if (!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.PALADIN)) {
        	currentPlayer = General.getPlayerNameHitted(logline);
    		if(currentPlayer!=null) {
    	    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.PALADIN)) {
    	    		return;
    	    	}
    		} else {
        		return;
    		}

		}
    	if(updatePaladinStatsForBlock(logline, currentPlayer)) {
    		//blocked eintrag geschrieben, nicht mehr machen!
    	} else {		
		
		updatePaladinStats(logline, currentPlayer, Constants.windfury, Paladin::incrementWindFury);
		updatePaladinStats(logline, currentPlayer, Constants.flametongue, Paladin::incrementFlameTongue);
		updatePaladinStats(logline, currentPlayer, Constants.cleanse, Paladin::incrementCleanse);
		updatePaladinStats(logline, currentPlayer, Constants.redemption, Paladin::incrementRedemption);
		
		updatePaladinStats(logline, currentPlayer, Constants.manFromVampirismTouch, paladin -> paladin
				.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline)));

		updatePaladinStats(logline, currentPlayer, Constants.manaFromJudgement, paladin -> {
			if (logline.contains("Mana")) {
				paladin.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
			}
		});
		updatePaladinStats(logline, currentPlayer, Constants.manaFromBOW, paladin -> {
			if (logline.contains("gains")) {
				paladin.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
			}
		});
		
		processAbility(logline, currentPlayer, Constants.holyStrikeHit, Constants.holyStrikeCrit,
				Paladin::incrementHolyStrikeHit, Paladin::incrementHolyStrikeCrit,
				paladin -> paladin.updateHighestHolyStrikeAmount(
						General.getAmountAtEnd(Constants.holyStrikeHit, Constants.holyStrikeCrit, logline),
						General.getTarget(Constants.holyStrikeHit, Constants.holyStrikeCrit, logline)));
		
		processAbility(logline, currentPlayer, Constants.crusaderStrikeHit, Constants.crusaderStrikeCrit,
				Paladin::incrementCrusaderStrikeHit, Paladin::incrementCrusaderStrikeCrit,
				paladin -> paladin.updateHighestCrusaderStrikeAmount(
						General.getAmountAtEnd(Constants.crusaderStrikeHit, Constants.crusaderStrikeCrit, logline),
						General.getTarget(Constants.crusaderStrikeHit, Constants.crusaderStrikeCrit, logline)));

		processAbility(logline, currentPlayer, Constants.sealOfCommandHit, Constants.sealOfCommandCrit,
				Paladin::incrementSealOfCommandHit, Paladin::incrementSealOfCommandCrit,
				paladin -> paladin.updateHighestSealOfCommandAmount(
						General.getAmountAtEnd(Constants.sealOfCommandHit, Constants.sealOfCommandCrit, logline),
						General.getTarget(Constants.sealOfCommandHit, Constants.sealOfCommandCrit, logline)));

		processAbility(logline, currentPlayer, Constants.judgementOfCommandHit, Constants.judgementOfCommandCrit,
				Paladin::incrementJudgementOfCommandHit, Paladin::incrementJudgementOfCommandCrit,
				paladin -> paladin.updateHighestJudgementOfCommandAmount(
						General.getAmountAtEnd(Constants.judgementOfCommandHit, Constants.judgementOfCommandCrit, logline),
						General.getTarget(Constants.judgementOfCommandHit, Constants.judgementOfCommandCrit, logline)));

		processAbility(logline, currentPlayer, Constants.exorcismHit, Constants.exorcismCrit,
				Paladin::incrementExorcismHit, Paladin::incrementExorcismCrit,
				paladin -> paladin.updateHighestExorcismAmount(
						General.getAmountAtEnd(Constants.exorcismHit, Constants.exorcismCrit, logline),
						General.getTarget(Constants.exorcismHit, Constants.exorcismCrit, logline)));
		
		processAbility(logline, currentPlayer, Constants.holyShockHit, Constants.holyShockCrit,
				Paladin::incrementholyShockHit, Paladin::incrementholyShockCrit,
				paladin -> paladin.updatehighestHolyShockAmount(
						General.getAmountAtEnd(Constants.holyShockHit, Constants.holyShockCrit, logline),
						General.getTarget(Constants.holyShockHit, Constants.holyShockCrit, logline)));

		processAbility(logline, currentPlayer, Constants.flashOfLightHit, Constants.flashOfLightCrit,
				Paladin::incrementflashOfLightHit, Paladin::incrementflashOfLightCrit,
				paladin -> paladin.updatehighestFlashOfLightAmount(
						General.getAmountAtEnd(Constants.flashOfLightHit, Constants.flashOfLightCrit, logline),
						General.getTarget(Constants.flashOfLightHit, Constants.flashOfLightCrit, logline)));

		processAbility(logline, currentPlayer, Constants.holyLightHit, Constants.holyLightCrit,
				Paladin::incrementholyLightHit, Paladin::incrementholyLightCrit,
				paladin -> paladin.updatehighestHolyLightAmount(
						General.getAmountAtEnd(Constants.holyLightHit, Constants.holyLightCrit, logline),
						General.getTarget(Constants.holyLightHit, Constants.holyLightCrit, logline)));
    	}
	}
	
    public static String getPaladinHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!paladinMap.isEmpty()) {
			SortedSet<String> paladins =  new TreeSet<>(paladinMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.PALADINCOLOR).append(";'>")
                  .append("<td colspan='27'>"+Constants.PALADIN+"</td></tr><tr>")
                  .append("<th>Name</th>")
      			  .append("<th>Death</th>")
      			  .append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>")                  
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
                  .append("<th>Redem.</th>")
                  .append("<th>Cleanse</th>")
                  .append("<th>Wfury</th>")
                  .append("<th>Flametongue</th>")
                  .append("<th>Blocks/blocked/%</th>")
                  .append("<th>Shock Hit/Crit</th><th class=\"toggle-column-highlights\">Max Schock</th>")
                  .append("<th>FoL Hit/Crit</th><th class=\"toggle-column-highlights\">Max Flash</th>")
                  .append("<th>HL Hit/Crit</th><th class=\"toggle-column-highlights\">Max HL</th>")
                  .append("<th>HS Hit/Crit</th><th class=\"toggle-column-highlights\">Max HS</th>")
            	  .append("<th>CS Hit/Crit</th><th class=\"toggle-column-highlights\">Max CS</th>")
            	  .append("<th>SoC Hit/Crit</th><th class=\"toggle-column-highlights\">Max SoC</th>")
            	  .append("<th>JoC Hit/Crit</th><th class=\"toggle-column-highlights\">Max JoC</th>")
            	  .append("<th>Exor Hit/Crit</th><th class=\"toggle-column-highlights\">Max Exo</th>")
                  .append("</tr>");

            for (String palaName : paladins) {
                Paladin pala= paladinMap.get(palaName);                
	                //healercheck
	                if(	pala.getHolyShockCrit()+pala.getHolyShockHit()>2 || pala.getFlashOfLightHit()+pala.getFlashOfLightCrit()>50) {
	                	Healer.addHealer(palaName);
	                }
				    Double blocked = (double) ((pala.getDmgAmountCompleteBeforeBlock()>0)?(((double)pala.getDmgAmountBlocked()/(double)pala.getDmgAmountCompleteBeforeBlock())*100):0);
					String blockLine = (blocked>0)?blocked.intValue()+"":"0";

                    strBuf.append("<tr>")
                          .append("<td>").append(palaName).append("</td>")
      					  .append("<td>"+pala.getDeathCounter()+"</td>")
      					  .append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(pala)+"</td>")                                                    
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromBow()).append("</td>")
                          .append("<td>").append(pala.getRedemption()).append("</td>")
                          .append("<td>").append(pala.getCleanse()/3).append("</td>")
                          .append("<td>").append(pala.getWindFury()).append("</td>") 
                          .append("<td>").append(pala.getFlametongue()).append("</td>")
      					  .append("<td>"+pala.blockedCnt+" / "+pala.getDmgAmountBlocked()+" / "+blockLine+"%</td>")

                          .append("<td>").append(pala.getHolyShockHit()).append(" / ").append(pala.getHolyShockCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestHolyShock()).append(" => ").append(pala.getHighestHolyShockTarget()).append("</td>")
                          .append("<td>").append(pala.getFlashOfLightHit()).append(" / ").append(pala.getFlashOfLightCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestFlashOfLight()).append(" => ").append(pala.getHighestFlashOfLightTarget()).append("</td>")
                          .append("<td>").append(pala.getHolyLightHit()).append(" / ").append(pala.getHolyLightCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestHolyLight()).append(" => ").append(pala.getHighestHolyLightTarget()).append("</td>")
                        		  
                          .append("<td>").append(pala.getHolyStrikeHit()).append(" / ").append(pala.getHolyStrikeCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestHolyStrike()).append(" => ").append(pala.getHighestHolyStrikeTarget()).append("</td>")
                          .append("<td>").append(pala.getCrusaderStrikeHit()).append(" / ").append(pala.getCrusaderStrikeCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestCrusaderStrike()).append(" => ").append(pala.getHighestCrusaderStrikeTarget()).append("</td>")
                          .append("<td>").append(pala.getSealOfCommandHit()).append(" / ").append(pala.getSealOfCommandCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestSealOfCommand()).append(" => ").append(pala.getHighestSealOfCommandTarget()).append("</td>")
                          .append("<td>").append(pala.getJudgementOfCommandHit()).append(" / ").append(pala.getJudgementOfCommandCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestJudgementOfCommand()).append(" => ").append(pala.getHighestJudgementOfCommandTarget()).append("</td>")
                          .append("<td>").append(pala.getExorcismHit()).append(" / ").append(pala.getExorcismCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(pala.getHighestExorcism()).append(" => ").append(pala.getHighestExorcismTarget()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
    
    
	public static boolean isPaladin(String logline) {
		boolean isPaladin = false;
		if((logline.contains(Constants.holyStrikeHit) || 
			logline.contains(Constants.crusaderStrikeHit) || 
			logline.contains(Constants.flashOfLightHit) || 			
			logline.contains(Constants.sealOfCommandHit) || 
			logline.contains(Constants.judgementOfCommandHit) || 
			logline.contains(Constants.exorcismHit) )) {
			isPaladin = true;
		}
		return isPaladin;
	}
    
	

}
