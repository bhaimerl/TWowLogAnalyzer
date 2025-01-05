package helper.classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.classes.NameClassWrapper;
import helper.classes.Rogue;

public class RogueUtils {
    public static HashMap<String, Rogue> rogueMap = new HashMap<>(); 

    private static Rogue getRogueByName(String name) {
        return rogueMap.computeIfAbsent(name, k -> new Rogue());
    }

    private static void updateRogueStats(String logline, String playerName, String keyword, Consumer<Rogue> action) {
        if (logline.contains(keyword)) {
        	Rogue rogue = getRogueByName(playerName);
            action.accept(rogue);
        }
    }

    private static void processAbility(String logline, String playerName, String hitKeyword, String critKeyword,
                                       Consumer<Rogue> hitAction, Consumer<Rogue> critAction,
                                       Consumer<Rogue> highestAmountAction) {
    	updateRogueStats(logline, playerName, hitKeyword, hitAction);
    	updateRogueStats(logline, playerName, critKeyword, critAction);

        if (logline.contains(hitKeyword) || logline.contains(critKeyword)) {
        	Rogue rogue = getRogueByName(playerName);
            highestAmountAction.accept(rogue);
        }
    }

	
	
	public static void findEntryForRogue(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	String currentPlayer = General.getPlayerName(logline);
    	if(!General.isPlayerInClassList(allValidPLayers, currentPlayer, Constants.ROGUE)) {
    		return;
    	}
    	updateRogueStats(logline, currentPlayer, Constants.sliceAndDice, Rogue::incrementSliceAndDice);
    	updateRogueStats(logline, currentPlayer, Constants.bladeFlurry, Rogue::incrementBladeFlurry);
    	updateRogueStats(logline, currentPlayer, Constants.windfury, Rogue::incrementWindFury);
    	updateRogueStats(logline, currentPlayer, Constants.crusader, Rogue::incrementCrusader);
    	updateRogueStats(logline, currentPlayer, Constants.flametongue, Rogue::incrementFlameTongue);

        processAbility(logline, currentPlayer, Constants.backStabHits, Constants.backStabcrits, 
                Rogue::incrementBackStabHits, Rogue::incrementBackStabCrits,
                warrior -> warrior.updateHighestBackstabAmount(General.getAmountAtEnd(Constants.backStabHits, Constants.backStabcrits, logline),
                		General.getTarget(Constants.backStabHits, Constants.backStabcrits, logline))
        );
        
        
        processAbility(logline, currentPlayer, Constants.eviscerateHits, Constants.eviscerateCrits, 
                Rogue::incrementEviscerateHit, Rogue::incrementEviscerateCrit,
                warrior -> warrior.updateHighestEviscerateAmount(General.getAmountAtEnd(Constants.eviscerateHits, Constants.eviscerateCrits, logline),
                		General.getTarget(Constants.eviscerateHits, Constants.eviscerateCrits, logline))
        );

        processAbility(logline, currentPlayer, Constants.sinisterStrikeHits, Constants.sinisterStrikeCrits, 
                Rogue::incrementSinisterStrikeHit, Rogue::incrementSinisterStrikeCrit,
                warrior -> warrior.updateHighestSinisterStrikeAmount(General.getAmountAtEnd(Constants.sinisterStrikeHits, Constants.sinisterStrikeCrits, logline),
                		General.getTarget(Constants.sinisterStrikeHits, Constants.sinisterStrikeCrits, logline))
        );
	}
	
	
	public static String getRogues() {
		StringBuffer strBuf = new StringBuffer();
		if(rogueMap!=null && rogueMap.size()>0) {
			SortedSet<String> rogues =  new TreeSet<>(rogueMap.keySet());			
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: "+Constants.ROGUECOLOR+";'><td colspan='12'>"+Constants.ROGUE+"</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Windfury Procs</th>");
			strBuf.append("<th>Crusader Procs</th>");
			strBuf.append("<th>Flametongue procs</th>");
			strBuf.append("<th>Slice&Dice procs</th>");
			strBuf.append("<th>Blade Flurry procs</th>");
			strBuf.append("<th>Backstab Hit/Crit</th>");
			strBuf.append("<th class=\"toggle-column-highlights\" style=\"display: none;\">Highest BS</th>");
			strBuf.append("<th>Eviscerate Hit/Crit</th>");
			strBuf.append("<th class=\"toggle-column-highlights\" style=\"display: none;\">Highest Evsc</th>");
			strBuf.append("<th>SinisterStrike Hit/Crit</th>");
			strBuf.append("<th class=\"toggle-column-highlights\" style=\"display: none;\">Highest SS</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String rogueName : rogues) {
				Rogue rogue = rogueMap.get(rogueName);
					strBuf.append("<tr>");
					strBuf.append("<td>"+rogueName+"</td>");
					strBuf.append("<td>"+rogue.getWindFury()+"</td>");
					strBuf.append("<td>"+rogue.getCrusader()+"</td>");
					strBuf.append("<td>"+rogue.getFlametongue()+"</td>");
					strBuf.append("<td>"+rogue.getSliceAndDice()+"</td>");
					strBuf.append("<td>"+rogue.getBladeFlurry()+"</td>");
					strBuf.append("<td>"+rogue.getBackStabHits()+" / "+rogue.getBackStabCrits()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\" style=\"display: none;\">"+rogue.getHighestBackStabAmount()+"=> "+rogue.getHighestBackStabTarget()+"</td>");					
					strBuf.append("<td>"+rogue.getEviscerateHit()+" / "+rogue.getEviscerateCrit()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\" style=\"display: none;\">"+rogue.getHighestEviscerateAmount()+"=> "+rogue.getHighestEviscerateTarget()+"</td>");					
					strBuf.append("<td>"+rogue.getSinisterStrikeHit()+" / "+rogue.getSinisterStrikeCrit()+"</td>");
					strBuf.append("<td class=\"toggle-column-highlights\" style=\"display: none;\">"+rogue.getHighestSinisterStrikeAmount()+"=> "+rogue.getHighestSinisterStrikeTarget()+"</td>");					
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
		
	public static boolean isRogue(String logline) {
		boolean isRogue = false;
		if((logline.contains(Constants.sliceAndDice) || 
			logline.contains(Constants.bladeFlurry) || 
			logline.contains(Constants.backStabHits) || 
			logline.contains(Constants.backStabHits) || 
			logline.contains(Constants.sinisterStrikeHits) )) {
			isRogue = true;
		}
		return isRogue;
	}
}
