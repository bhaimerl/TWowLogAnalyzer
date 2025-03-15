package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.Druid;
import helper.classes.Healer;
import helper.classes.Hunter;
import helper.classes.NameClassWrapper;
import helper.classes.Warrior;

public class DruidUtils {
	

	public static boolean isDruid(String logline) {
		boolean isDruid = false;
		if((logline.contains(Constants.healingTouch) || 
			logline.contains(Constants.regrowth) || 
			logline.contains(Constants.swipe) || 
			logline.contains(Constants.maul) || 
			logline.contains(Constants.growl) || 
			logline.contains(Constants.savegeFury) )) {
			isDruid = true;
		}
		return isDruid;
	}
	
	
	public static HashMap<String, Druid> druidMap = new HashMap<>();

    public static Druid getDruidByName(String name) {
        return druidMap.computeIfAbsent(name, k -> new Druid());
    }

    private static void updateDruidStats(String logline, String playerName, String keyword, Consumer<Druid> action) {
        if (logline.contains(keyword)) {
        	Druid druid = getDruidByName(playerName);
            action.accept(druid);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Druid> hitAction, Consumer<Druid> critAction,
                                       Consumer<Druid> highestAmountAction) {
    	updateDruidStats(logline, playerName, hitKeyword, hitAction);
    	updateDruidStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Druid druid = getDruidByName(playerName);
            highestAmountAction.accept(druid);
        }
    }
    
	public static void findEntryForDruid(String logline,
			HashMap<String, ArrayList<NameClassWrapper>> allValidPLayers) {
		String currentPlayer = General.getPlayerName(logline);
		if (!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.DRUID)) {
			return;
		}
		updateDruidStats(logline, currentPlayer, Constants.manFromVampirismTouch, mage -> mage
				.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline)));

		updateDruidStats(logline, currentPlayer, Constants.manaFromJudgement, mage -> {
			if (logline.contains("Mana")) {
				mage.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
			}
		});

		updateDruidStats(logline, currentPlayer, Constants.manaFromBOW, mage -> {
			if (logline.contains("gains")) {
				mage.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
			}
		});
		updateDruidStats(logline, currentPlayer, Constants.windfury, Druid::incrementWindFury);
		updateDruidStats(logline, currentPlayer, Constants.flametongue, Druid::incrementFlameTongue);
		updateDruidStats(logline, currentPlayer, Constants.rebirth, Druid::incrementRebirth);
		
		updateDruidStats(logline, currentPlayer, Constants.faerieFire, Druid::incrementFaerieFire);
		//updateDruidStats(logline, currentPlayer, Constants.growl, Druid::incrementGrowl);
		updateDruidStats(logline, currentPlayer, Constants.improvedRejuvenation, Druid::incrementImprovedRejuvenation);
		updateDruidStats(logline, currentPlayer, Constants.improvedRegrowth, Druid::incrementImprovedRegrowth);		
		updateDruidStats(logline, currentPlayer, Constants.insectSwarm, Druid::incrementInsectSwarm);		

		updateDruidStats(logline, currentPlayer, Constants.abolishPoison, Druid::incrementAbolishPoison);		
		updateDruidStats(logline, currentPlayer, Constants.removeCurse, Druid::incrementRemoveCurse);	
		

		processAbility(logline, currentPlayer, Constants.healingTouchHit, Constants.healingTouchCrit,
				Druid::incrementhealingTouchHit, Druid::incrementhealingTouchCrit,
				druid -> druid.updatehighestsHealingTouchAmount(
						General.getAmountAtEnd(Constants.healingTouchHit, Constants.healingTouchCrit, logline),
						General.getTarget(Constants.healingTouchHit, Constants.healingTouchCrit, logline)));		
		
		processAbility(logline, currentPlayer, Constants.starFireHits, Constants.starFireCrits,
				Druid::incrementstarFireHit, Druid::incrementstarFireCrit,
				druid -> druid.updateHigheststarFireAmount(
						General.getAmountAtEnd(Constants.starFireHits, Constants.starFireCrits, logline),
						General.getTarget(Constants.starFireHits, Constants.starFireCrits, logline)));

		processAbility(logline, currentPlayer, Constants.moonfireHits, Constants.moonFireCrits,
				Druid::incrementmoonFireHit, Druid::incrementmoonFireCrit,
				druid -> druid.updateHighestmoonFireAmount(
						General.getAmountAtEnd(Constants.moonfireHits, Constants.moonFireCrits, logline),
						General.getTarget(Constants.moonfireHits, Constants.moonFireCrits, logline)));
		
		processAbility(logline, currentPlayer, Constants.wrathHits, Constants.wrathCrits,
				Druid::incrementwrathHit, Druid::incrementwrathCrit,
				druid -> druid.updateHighestwrathAmount(
						General.getAmountAtEnd(Constants.wrathHits, Constants.wrathCrits, logline),
						General.getTarget(Constants.wrathHits, Constants.wrathCrits, logline)));

		processAbility(logline, currentPlayer, Constants.maulHits, Constants.maulCrits,
				Druid::incrementmaulHit, Druid::incrementmaulCrit,
				druid -> druid.updateHighestmaulAmount(
						General.getAmountAtEnd(Constants.maulHits, Constants.maulCrits, logline),
						General.getTarget(Constants.maulHits, Constants.maulCrits, logline)));
		
		processAbility(logline, currentPlayer, Constants.swipeHits, Constants.swipeCrits,
				Druid::incrementswipeHit, Druid::incrementswipeCrit,
				druid -> druid.updateHighestswipeAmount(
						General.getAmountAtEnd(Constants.swipeHits, Constants.swipeCrits, logline),
						General.getTarget(Constants.swipeHits, Constants.swipeCrits, logline)));

		processAbility(logline, currentPlayer, Constants.shredHits, Constants.shredCrits,
				Druid::incrementshredHit, Druid::incrementshredCrit,
				druid -> druid.updateHighestshredAmount(
						General.getAmountAtEnd(Constants.shredHits, Constants.shredCrits, logline),
						General.getTarget(Constants.shredHits, Constants.shredCrits, logline)));

	}
	
    public static String getDruidHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!druidMap.isEmpty()) {
			SortedSet<String> druids =  new TreeSet<>(druidMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.DRUIDCOLOR).append(";'>")
                  .append("<td colspan='28'>"+Constants.DRUID+"</td></tr><tr>")
                  .append("<th>Name</th>")
      			  .append("<th>Death</th>")
      			  .append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>")                  
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
      			  .append("<th>Windfury Procs</th>")
      			  .append("<th>Flametonue Procs</th>")
      			  .append("<th>Faerie Fire</th>")
      			  .append("<th>Rejuvenations</th>")
      			  .append("<th>Regrowth</th>")
      			  .append("<th>Insect Swarm</th>")
      			  .append("<th>Rebirth</th>")
      			  .append("<th>De -Curse/-Poison</th>")
                  .append("<th>HealingTouch Hit/Crit</th><th class=\"toggle-column-highlights\">Max HT</th>")
                  .append("<th>Starfire Hit/Crit</th><th class=\"toggle-column-highlights\">Max SF</th>")
            	  .append("<th>Moonfire Hit/Crit</th><th class=\"toggle-column-highlights\">Max MF</th>")
            	  .append("<th>Wrath Hit/Crit</th><th class=\"toggle-column-highlights\">Max Wrath</th>")
            	  .append("<th>Maul Hit/Crit</th><th class=\"toggle-column-highlights\">Max Maul</th>")
            	  .append("<th>Swipe Hit/Crit</th><th class=\"toggle-column-highlights\">Max Swipe</th>")
            	  .append("<th>Shred Hit/Crit</th><th class=\"toggle-column-highlights\">Max Shred</th>")
//            	  .append("<th>Serpent Sting applied</th>")
                  .append("</tr>");

            for (String druidName : druids) {
                Druid druid = druidMap.get(druidName);
                //healercheck
                if(druid.getImprovedRejuvenation()>10 || druid.getImprovedRegrowth()>10 || (druid.getHealingTouchCrit()+druid.getHealingTouchHit())>15) {
                	Healer.addHealer(druidName);
                }
                
                
                    strBuf.append("<tr>")
                          .append("<td>").append(druidName).append("</td>")
      					  .append("<td>"+druid.getDeathCounter()+"</td>")
      					  .append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(druid)+"</td>")					
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(druid.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(druid.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(druid.getManaFromBow()).append("</td>")
        				  .append("<td>"+druid.getWindFury()+"</td>")
        				  .append("<td>"+druid.getFlametongue()+"</td>")
        				  .append("<td>"+druid.getFaerieFire()+"</td>")
        				  .append("<td>"+druid.getImprovedRejuvenation()+"</td>")
        				  .append("<td>"+druid.getImprovedRegrowth()+"</td>")
        				  .append("<td>"+druid.getInsectSwarm()+"</td>")        				  
        				  .append("<td>"+druid.getRebirth()+"</td>")        				  
        				  .append("<td>"+druid.getRemoveCurse()+" / "+druid.getAbolishPoison()+"</td>")        				  
                          .append("<td>").append(druid.getHealingTouchHit()).append(" / ").append(druid.getHealingTouchCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestsHealingTouch()).append(" => ").append(druid.getHighestsHealingTouchTarget()).append("</td>")
                          .append("<td>").append(druid.getStarFireHit()).append(" / ").append(druid.getStarFireCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHigheststarFire()).append(" => ").append(druid.getHigheststarFireTarget()).append("</td>")
                          .append("<td>").append(druid.getMoonFireHit()).append(" / ").append(druid.getMoonFireCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestmoonFire()).append(" => ").append(druid.getHighestmoonFireTarget()).append("</td>")
                          .append("<td>").append(druid.getWrathHit()).append(" / ").append(druid.getWrathCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestwrath()).append(" => ").append(druid.getHighestwrathTarget()).append("</td>")
                          .append("<td>").append(druid.getMaulHit()).append(" / ").append(druid.getMaulCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestmaul()).append(" => ").append(druid.getHighestmaulTarget()).append("</td>")
                          .append("<td>").append(druid.getSwipeHit()).append(" / ").append(druid.getSwipeCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestswipe()).append(" => ").append(druid.getHighestswipeTarget()).append("</td>")
                          .append("<td>").append(druid.getShredHit()).append(" / ").append(druid.getShredCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(druid.getHighestshred()).append(" => ").append(druid.getHighestshredTarget()).append("</td>")
//                          .append("<td>").append(hunter.getSerpentStringAmount()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>");
        }
        return strBuf.toString();
    }
	
    
    
	
	

}
