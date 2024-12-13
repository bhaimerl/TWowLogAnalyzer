package helper.classes.utils;

import java.util.HashMap;
import java.util.Set;

import helper.classes.Rogue;
import helper.classes.Warlock;

public class RogueUtils {
	
	public static HashMap<String, Rogue> rogueMap = null;
	
	private static Rogue getRogueByName(String name) {
		Rogue current = null;
		if(rogueMap==null) {
			rogueMap = new HashMap<>();
		}
		if(rogueMap.get(name)==null) {
			current = new Rogue();
			rogueMap.put(name, current);
		} else {
			current = rogueMap.get(name);
		}
		return current;
	}	
	
	
	public static void findEntryForRogue(String logline) {
		
		String windfury="gains 1 extra attack through Windfury";
		String crusader= "gains Holy Strength";
		String flametongue="Flametongue Attack";
		String sliceAndDice="gains Slice and Dice";
		String bladeFlurry="gains Blade Flurry";
		String backStabHits="Backstab hits";
		String backStabcrits="Backstab crits";
		String eviscerateHits="Eviscerate hits";
		String eviscerateCrits="Eviscerate crits";
		String sinisterStrikeHits="Sinister Strike hits";
		String sinisterStrikeCrits="Sinister Strike crits";
				
		
		if(logline.indexOf(sliceAndDice)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setSliceAndDice(rog.getSliceAndDice()+1);
		}
		if(logline.indexOf(bladeFlurry)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setBladeFlurry(rog.getBladeFlurry()+1);
		}
		if(logline.indexOf(windfury)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setWindFury(rog.getWindFury()+1);
		}
		if(logline.indexOf(crusader)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setCrusader(rog.getCrusader()+1);
		}
		if(logline.indexOf(flametongue)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setFlametongue(rog.getFlametongue()+1);
		}
		if(logline.indexOf(backStabHits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setBackStabHits(rog.getBackStabHits()+1);
		}
		if(logline.indexOf(backStabcrits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setBackStabCrits(rog.getBackStabCrits()+1);
		}
		if(logline.indexOf(backStabHits)>=0 || logline.indexOf(backStabcrits)>=0) {
			Rogue rogue = getRogueByName(General.getPlayerName(logline));
			int currentAmount = General.getAmountAtEnd(backStabHits, backStabcrits, logline);
			if(currentAmount>=rogue.getHighestBackStabAmount()) {
				rogue.setHighestBackStabAmount(currentAmount);
				rogue.setHighestBackStabTarget(General.getTarget(backStabHits, backStabcrits, logline));
			}
		}
		if(logline.indexOf(eviscerateHits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setEviscerateHit(rog.getEviscerateHit()+1);
		}
		if(logline.indexOf(eviscerateCrits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setEviscerateCrit(rog.getEviscerateCrit()+1);
		}
		if(logline.indexOf(eviscerateHits)>=0 || logline.indexOf(eviscerateCrits)>=0) {
			Rogue rogue = getRogueByName(General.getPlayerName(logline));
			int currentAmount = General.getAmountAtEnd(eviscerateHits, eviscerateCrits, logline);
			if(currentAmount>=rogue.getHighestEviscerateAmount()) {
				rogue.setHighestEviscerateAmount(currentAmount);
				rogue.setHighestEviscerateTarget(General.getTarget(eviscerateHits, eviscerateCrits, logline));
			}
		}
		if(logline.indexOf(sinisterStrikeHits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setSinisterStrikeHit(rog.getSinisterStrikeHit()+1);
		}
		if(logline.indexOf(sinisterStrikeCrits)>=0) {
			Rogue rog = getRogueByName(General.getPlayerName(logline));
			rog.setSinisterStrikeCrit(rog.getSinisterStrikeCrit()+1);
		}
		if(logline.indexOf(sinisterStrikeHits)>=0 || logline.indexOf(sinisterStrikeCrits)>=0) {
			Rogue rogue = getRogueByName(General.getPlayerName(logline));
			int currentAmount = General.getAmountAtEnd(sinisterStrikeHits, sinisterStrikeCrits, logline);
			if(currentAmount>=rogue.getHighestSinisterStrikeAmount()) {
				rogue.setHighestSinisterStrikeAmount(currentAmount);
				rogue.setHighestSinisterStrikeTarget(General.getTarget(sinisterStrikeHits, sinisterStrikeCrits, logline));
			}
		}
		
	}
	
	/*
	 * 			strBuf.append("<html>");
			strBuf.append("<head>");
			strBuf.append("<style>");
			strBuf.append("table, th, td {");
			strBuf.append("  border: 1px solid black;");
			strBuf.append("}");
			strBuf.append("</style>");
			strBuf.append("</head>");
			strBuf.append("<body>");	

	 */

	
	
	public static String getRogues() {
		StringBuffer strBuf = new StringBuffer();
		if(rogueMap!=null) {
			Set<String> rogues =  rogueMap.keySet();
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: "+Constants.ROGUECOLOR+";'><td colspan='12'>ROGUE</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Windfury Procs</th>");
			strBuf.append("<th>Crusader Procs</th>");
			strBuf.append("<th>Flametongue procs</th>");
			strBuf.append("<th>Slice&Dice procs</th>");
			strBuf.append("<th>Blade Flurry procs</th>");
			strBuf.append("<th>Backstab Hit/Crit</th>");
			strBuf.append("<th>Highest BS</th>");
			strBuf.append("<th>Eviscerate Hit/Crit</th>");
			strBuf.append("<th>Highest Evsc</th>");
			strBuf.append("<th>SinisterStrike Hit/Crit</th>");
			strBuf.append("<th>Highest SS</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String rogueName : rogues) {
				Rogue rogue = rogueMap.get(rogueName);
				if(rogue.getSliceAndDice()>0 || rogue.getBladeFlurry()>0) {
					strBuf.append("<tr>");
					strBuf.append("<td>"+rogueName+"</td>");
					strBuf.append("<td>"+rogue.getWindFury()+"</td>");
					strBuf.append("<td>"+rogue.getCrusader()+"</td>");
					strBuf.append("<td>"+rogue.getFlametongue()+"</td>");
					strBuf.append("<td>"+rogue.getSliceAndDice()+"</td>");
					strBuf.append("<td>"+rogue.getBladeFlurry()+"</td>");
					strBuf.append("<td>"+rogue.getBackStabHits()+" / "+rogue.getBackStabCrits()+"</td>");
					strBuf.append("<td>"+rogue.getHighestBackStabAmount()+"=> "+rogue.getHighestBackStabTarget()+"</td>");					
					strBuf.append("<td>"+rogue.getEviscerateHit()+" / "+rogue.getEviscerateCrit()+"</td>");
					strBuf.append("<td>"+rogue.getHighestEviscerateAmount()+"=> "+rogue.getHighestEviscerateTarget()+"</td>");					
					strBuf.append("<td>"+rogue.getSinisterStrikeHit()+" / "+rogue.getSinisterStrikeCrit()+"</td>");
					strBuf.append("<td>"+rogue.getHighestSinisterStrikeAmount()+"=> "+rogue.getHighestSinisterStrikeTarget()+"</td>");					
					strBuf.append("</tr>");
				}
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
}
