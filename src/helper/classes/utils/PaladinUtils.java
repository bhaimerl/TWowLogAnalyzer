package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.NameClassWrapper;
import helper.classes.Paladin;

public class PaladinUtils {
	
	public static HashMap<String, Paladin> paladinMap = new HashMap<>();;

    private static Paladin getPaladinByName(String name) {
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
    
	public static void findEntryForPaladin(String logline,
			HashMap<String, ArrayList<NameClassWrapper>> allValidPLayers) {
		String currentPlayer = General.getPlayerName(logline);
		if (!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.PALADIN)) {
			return;
		}
		
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
		
	}
	
    public static String getPaladinHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!paladinMap.isEmpty()) {
			SortedSet<String> paladins =  new TreeSet<>(paladinMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.PALADINCOLOR).append(";'>")
                  .append("<td colspan='18'>"+Constants.PALADIN+"</td></tr><tr>")
                  .append("<th>Name</th>")
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
                  .append("<th>Redemption</th>")
                  .append("<th>Cleanse</th>")
                  .append("<th>Windfury procs</th>")
                  .append("<th>Flametongue procs</th>")
                  .append("<th>HolyStrike Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest HolyStrike</th>")
            	  .append("<th>CrusaderStrike Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest CrusaderStrike</th>")
            	  .append("<th>SealOfCommand Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest SoC</th>")
            	  .append("<th>JudgementOfCommand Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest JoC</th>")
            	  .append("<th>Exorcism Hit/Crit</th><th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Exorcism</th>")
                  .append("</tr>");

            for (String palaName : paladins) {
                Paladin pala= paladinMap.get(palaName);
                    strBuf.append("<tr>")
                          .append("<td>").append(palaName).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(pala.getManaFromBow()).append("</td>")
                          .append("<td>").append(pala.getRedemption()).append("</td>")
                          .append("<td>").append(pala.getCleanse()/3).append("</td>")
                          .append("<td>").append(pala.getWindFury()).append("</td>") 
                          .append("<td>").append(pala.getFlametongue()).append("</td>")
                          .append("<td>").append(pala.getHolyStrikeHit()).append(" / ").append(pala.getHolyStrikeCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(pala.getHighestHolyStrike()).append(" => ").append(pala.getHighestHolyStrikeTarget()).append("</td>")
                          .append("<td>").append(pala.getCrusaderStrikeHit()).append(" / ").append(pala.getCrusaderStrikeCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(pala.getHighestCrusaderStrike()).append(" => ").append(pala.getHighestCrusaderStrikeTarget()).append("</td>")
                          .append("<td>").append(pala.getSealOfCommandHit()).append(" / ").append(pala.getSealOfCommandCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(pala.getHighestSealOfCommand()).append(" => ").append(pala.getHighestSealOfCommandTarget()).append("</td>")
                          .append("<td>").append(pala.getJudgementOfCommandHit()).append(" / ").append(pala.getJudgementOfCommandCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(pala.getHighestJudgementOfCommand()).append(" => ").append(pala.getHighestJudgementOfCommandTarget()).append("</td>")
                          .append("<td>").append(pala.getExorcismHit()).append(" / ").append(pala.getExorcismCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\" style=\"display: none;\">").append(pala.getHighestExorcism()).append(" => ").append(pala.getHighestExorcismTarget()).append("</td>")
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
			logline.contains(Constants.sealOfCommandHit) || 
			logline.contains(Constants.judgementOfCommandHit) || 
			logline.contains(Constants.exorcismHit) )) {
			isPaladin = true;
		}
		return isPaladin;
	}
    
	

}
