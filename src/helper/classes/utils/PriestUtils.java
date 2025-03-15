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
import helper.classes.Priest;
import helper.classes.Warlock;

public class PriestUtils {
	
	public static HashMap<String, Priest> priestMap = new HashMap<>();;

    public static Priest getPriestByName(String name) {
        return priestMap.computeIfAbsent(name, k -> new Priest());
    }

    private static void updatePriestStats(String logline, String playerName, String keyword, Consumer<Priest> action) {
        if (logline.contains(keyword)) {
        	Priest priest = getPriestByName(playerName);
            action.accept(priest);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Priest> hitAction, Consumer<Priest> critAction,
                                       Consumer<Priest> highestAmountAction) {
    	updatePriestStats(logline, playerName, hitKeyword, hitAction);
    	updatePriestStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Priest priest = getPriestByName(playerName);
        	highestAmountAction.accept(priest);
        }
    }
    
    public static void findEntryForPriest(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	
    	//bezieht sich die aktuelle Zeile auf einen Priest?
    	String name1 = General.getPlayerName(logline);
		String name2 = General.getPlayerNameFromEndFrom(logline);
		String winner = "";
		
		//ist einer der beiden ein priest?
		//wenn beide dann gewinnt der erste, wenn nur der 2. dann der 2. 
		boolean name1IsPriest = General.isPlayerInClassList(allValidPLayers, name1, Constants.PRIEST);
		boolean name2IsPriest = General.isPlayerInClassList(allValidPLayers, name2, Constants.PRIEST);
		
		if(name1IsPriest && name2IsPriest) {
			winner = name2;
		} else if(!name1IsPriest && name2IsPriest) {
			winner = name2;			
		} else if(name1IsPriest && !name2IsPriest) {
			winner = name1;			
		} else {
			return;
		}
		String currentPlayer = winner;
		updatePriestStats(logline, currentPlayer, Constants.manFromVampirismTouch, warlock -> warlock
				.addManaFromVampiricTouch(General.getAmountGains(Constants.manFromVampirismTouch, logline)));
		updatePriestStats(logline, currentPlayer, Constants.manaFromJudgement, warlock -> {
			if (logline.contains("Mana")) {
				warlock.addManaFromJudgementOfWisdom(General.getAmountGains(Constants.manaFromJudgement, logline));
			}
		});
		updatePriestStats(logline, currentPlayer, Constants.manaFromBOW, warlock -> {
			if (logline.contains("gains")) {
				warlock.addManaFromBow(General.getAmountGains(Constants.manaFromBOW, logline));
			}
		});
		 
		//T2 greater heal hot
		updatePriestStats(logline, currentPlayer, "health from "+currentPlayer+" 's Greater Heal.", Priest::incrementT2GreaterHeal);
		updatePriestStats(logline, currentPlayer, "health from "+currentPlayer+" 's Greater Heal.", priest -> {
			if (logline.contains("gains")) {
				priest.addT2GreaterHealAmount(General.getAmountGains("health from "+currentPlayer+" 's Greater Heal.", logline));
			}
		});
		
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.powerWordFortitude, Priest::incrementPowerWordFortitude);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.powerWordShield, Priest::incrementpowerWordShield);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.prayerOfFortitude, Priest::incrementPrayerOfFortitude);
		
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.resurrection, Priest::incrementResurrection);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.renew, Priest::incrementRenew);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.epiphany, Priest::incrementEpiphany);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.holyNova, Priest::incrementHolyNova);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.innervate, Priest::incrementInnervate);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.enlighten, Priest::incrementEnlighten);
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.mindFlay, Priest::incrementMindFLay); //8
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.proclaimChampion, Priest::incrementChampion); //9
		updatePriestStats(logline, currentPlayer, currentPlayer+" "+Constants.dispelMagic, Priest::incrementDispellMagic); //10
		
		processAbility(logline, currentPlayer, Constants.flashHealHit, Constants.flashHealCrit,
				Priest::incrementflashHealHit, Priest::incrementflashHealCrit,
				priest -> priest.updateHighestflashHealAmount(
						General.getAmountAtEnd(Constants.flashHealHit, Constants.flashHealCrit, logline),
						General.getTarget(Constants.flashHealHit, Constants.flashHealCrit, logline))); //13

		processAbility(logline, currentPlayer, Constants.greaterHealHit, Constants.greaterHealCrit,
				Priest::incrementgreaterHealHit, Priest::incrementgreaterHealCrit,
				priest -> priest.updateHighestgreaterHealAmount(
						General.getAmountAtEnd(Constants.greaterHealHit, Constants.greaterHealCrit, logline),
						General.getTarget(Constants.greaterHealHit, Constants.greaterHealCrit, logline))); //15
		
		processAbility(logline, currentPlayer, Constants.mindBlastHit, Constants.mindBlastCrit,
				Priest::incrementmindBlastHit, Priest::incrementmindBlastCrit,
				priest -> priest.updateHighestmindBlastAmount(
						General.getAmountAtEnd(Constants.mindBlastHit, Constants.mindBlastCrit, logline),
						General.getTarget(Constants.mindBlastHit, Constants.mindBlastCrit, logline))); //17
		
		processAbility(logline, currentPlayer, Constants.smiteHit, Constants.smiteCrit,
				Priest::incrementsmiteHit, Priest::incrementsmiteCrit,
				priest -> priest.updateHighestsmiteAmount(
						General.getAmountAtEnd(Constants.smiteHit, Constants.smiteCrit, logline),
						General.getTarget(Constants.smiteHit, Constants.smiteCrit, logline))); 
		
		processAbility(logline, currentPlayer, Constants.holyFireHit, Constants.holyFireCrit,
				Priest::incrementholyfireHit, Priest::incrementholyfireCrit,
				priest -> priest.updateHighestholyfireAmount(
						General.getAmountAtEnd(Constants.holyFireHit, Constants.holyFireCrit, logline),
						General.getTarget(Constants.holyFireHit, Constants.holyFireCrit, logline))); 
		
    }
    
    public static String getPriestHTML() {
        StringBuilder strBuf = new StringBuilder();
        if (!priestMap.isEmpty()) {
			SortedSet<String> priests =  new TreeSet<>(priestMap.keySet());			
            strBuf.append("<br><body><table class='classTable' align=\"left\" width='100%'>")
                  .append("<tr style='background-color: ").append(Constants.PRIESTCOLOR).append(";'>")
                  .append("<td colspan='28'>"+Constants.PRIEST+"</td></tr><tr>")
                  .append("<th>Name</th>")
      			  .append("<th>Death</th>")
      			  .append("<th class=\"toggle-column-death\" style=\"display: none;\">DeathCauses</th>")                  
                  .append("<th class=\"toggle-column\" style=\"display: none;\">Mana VampiricTouch</th><th class=\"toggle-column\" style=\"display: none;\">Mana Judgement</th><th class=\"toggle-column\" style=\"display: none;\">Mana BOW</th>")
      			  .append("<th>Renew</th>")
      			  .append("<th>Epiphany</th>")
      			  .append("<th>PW: Fortitude</th>")
      			  .append("<th>PW: Shield</th>")
      			  .append("<th>Prayer of Fortitude</th>")
      			  .append("<th>Holy Nova</th>")
      			  .append("<th>Enlighten</th>")
      			  .append("<th>Mindflay</th>")
      			  .append("<th>Champion Casts</th>")
      			  .append("<th>Resurrection</th>")
      			  .append("<th>Dispell Magic</th>")
      			  .append("<th>T2 Greater Heal procs / complete</th>")
                  .append("<th>Smite Hit/Crit</th><th class=\"toggle-column-highlights\">Max Smite</th>")
                  .append("<th>HolyFire Hit/Crit</th><th class=\"toggle-column-highlights\">Max HF</th>")
                  .append("<th>Flash Heal Hit/Crit</th><th class=\"toggle-column-highlights\">Max FH</th>")
            	  .append("<th>Greater Heal Hit/Crit</th><th class=\"toggle-column-highlights\">Max GH</th>")
            	  .append("<th>MindBlast Hit/Crit</th><th class=\"toggle-column-highlights\">Max MB</th>")
                  .append("</tr>");

            for (String priesterName : priests) {
                Priest priest= priestMap.get(priesterName);
                
                //healercheck
                if(	priest.getFlashHealHit()+priest.getFlashHealCrit() > 10 || priest.renew > 10 || priest.greaterHealHit+priest.greaterHealCrit > 50) {
                	Healer.addHealer(priesterName);
                }
                    strBuf.append("<tr>")
                          .append("<td>").append(priesterName).append("</td>")
      					  .append("<td>"+priest.getDeathCounter()+"</td>")
      					  .append("<td class=\"toggle-column-death\" style=\"display: none;\">"+Players.getDeathCauses(priest)+"</td>")                                                    
      					  .append("<td class=\"toggle-column\" style=\"display: none;\">").append(priest.getManaFromVampiricTouch()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(priest.getManaFromJudgementOfWisdom()).append("</td>")
                          .append("<td class=\"toggle-column\" style=\"display: none;\">").append(priest.getManaFromBow()).append("</td>")
      					  .append("<td>"+priest.getRenew()+"</td>")
      					  .append("<td>"+priest.getEpiphany()+"</td>")
      					  .append("<td>"+priest.getPowerWordFortitude()+"</td>")
      					  .append("<td>"+priest.getPowerWordShield()+"</td>")
      					  .append("<td>"+priest.getPrayerOfFortitude()+"</td>")
      					  .append("<td>"+priest.getHolyNova()+"</td>")
      					  .append("<td>"+priest.getEnlighten()+"</td>")
      					  .append("<td>"+priest.getMindFLay()+"</td>")
      					  .append("<td>"+priest.getProclaimChampion()+"</td>")                          
      					  .append("<td>"+priest.getResurrection()+"</td>")                          
      					  .append("<td>"+priest.getDispellMagic()+"</td>")                          
      					  .append("<td>"+priest.getT2GreaterHeal()+" / "+priest.getT2GreaterHealAmount()+"</td>")                         
      					  
                          .append("<td>").append(priest.getSmiteHit()).append(" / ").append(priest.getSmiteCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(priest.getHighestsmite()).append(" => ").append(priest.getHighestsmiteTarget()).append("</td>")
                          .append("<td>").append(priest.getHolyfireHit()).append(" / ").append(priest.getHolyfireCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(priest.getHighestholyfire()).append(" => ").append(priest.getHighestholyfireTarget()).append("</td>")
                          
      					  
                          .append("<td>").append(priest.getFlashHealHit()).append(" / ").append(priest.getFlashHealCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(priest.getHighestflashHeal()).append(" => ").append(priest.getHighestflashHealTarget()).append("</td>")
                          .append("<td>").append(priest.getGreaterHealHit()).append(" / ").append(priest.getGreaterHealCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(priest.getHighestgreaterHeal()).append(" => ").append(priest.getHighestgreaterHealTarget()).append("</td>")
                          .append("<td>").append(priest.getMindBlastHit()).append(" / ").append(priest.getMindBlastCrit()).append("</td>")
                          .append("<td class=\"toggle-column-highlights\">").append(priest.getHighestmindBlast()).append(" => ").append(priest.getHighestmindBlastTarget()).append("</td>")
                          .append("</tr>");
            }
            strBuf.append("</table>"); 
        }
        return strBuf.toString();
    }    
    
	
	
		public static boolean isPriest(String logline) {
		boolean isPriest = false;
		if((logline.contains(Constants.renew) || 
			logline.contains(Constants.flashHealHit) || 
			logline.contains(Constants.epiphany) || 
			logline.contains(Constants.holyNova) || 
			logline.contains(Constants.enlighten) || 
			logline.contains(Constants.greaterHealHit) || 
			logline.contains(Constants.vampiricEmbrace) || 
			logline.contains(Constants.mindBlastHit) || 
			logline.contains(Constants.mindFlay) )) {
			isPriest = true;
		}
		return isPriest;
	}
	

}
