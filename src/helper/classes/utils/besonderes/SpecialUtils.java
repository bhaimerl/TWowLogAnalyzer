package helper.classes.utils.besonderes;

import java.util.HashMap;
import java.util.Set;

import helper.classes.Rogue;
import helper.classes.Warlock;
import helper.classes.besonderes.Special;
import helper.classes.utils.General;

public class SpecialUtils {

	public static HashMap<String, Special> specialMap = null;
	
	private static Special getSpecialByName(String name) {
		Special current = null;
		if(specialMap==null) {
			specialMap = new HashMap<>();
		}
		if(specialMap.get(name)==null) {
			current = new Special();
			specialMap.put(name, current);
		} else {
			current = specialMap.get(name);
		}
		return current;
	}	
	
	
	public static void findEntryForSpecial(String logline) {
		
		String holyChampionProccs="from Lyndrell 's Holy Champion";
		String holyChampionGainedMana= "from Lyndrell 's Holy Champion";
		String bossHitsOnKranette="Patchwerk 's Hateful Strike hits Kranette for";
		String incomingDamageAmountOnKranette="Patchwerk 's Hateful Strike hits Kranette for";
		
		if(logline.indexOf(holyChampionProccs)>=0) {
			Special special = getSpecialByName(General.getPlayerName(logline));
			special.setHolyChampionProccs(special.getHolyChampionProccs()+1);
		}
		if(logline.indexOf(holyChampionGainedMana)>=0) {
			Special special = getSpecialByName(General.getPlayerName(logline));
			special.setHolyChampionGainedMana(special.getHolyChampionGainedMana()+General.getAmountGains(holyChampionGainedMana, logline));
		}
		if(logline.indexOf(bossHitsOnKranette)>=0) {
			Special special = getSpecialByName(General.getPlayerName(logline));
			special.setBossHitsOnKranette(special.getBossHitsOnKranette()+1);
		}
		if(logline.indexOf(incomingDamageAmountOnKranette)>=0) {
			Special special = getSpecialByName(General.getPlayerName(logline));
			special.setIncomingDmgAmountKranette(special.getIncomingDmgAmountKranette()+General.getAmountAtEnd(incomingDamageAmountOnKranette, logline));
		}
	}	
	
	
	public static String getSpecials() {
		StringBuffer strBuf = new StringBuffer();
		if(specialMap!=null) {
			Set<String> rogues =  specialMap.keySet();
			strBuf.append("<br>ROGUES");				
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table align=\"left\" style=\"margin: 0px auto;\">");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>HolyChampion proccs</th>");
			strBuf.append("<th>HolyChampion Mana gained</th>");
			strBuf.append("<th>Hits on Kranette</th>");
			strBuf.append("<th>DMGAmount on Kranette</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String rogueName : rogues) {
				Special special = specialMap.get(rogueName);
				if(true) {
					strBuf.append("<tr>");
					strBuf.append("<td>"+rogueName+"</td>");
					strBuf.append("<td>"+special.getHolyChampionProccs()+"</td>");
					strBuf.append("<td>"+special.getHolyChampionGainedMana()+"</td>");
					strBuf.append("<td>"+special.getBossHitsOnKranette()+"</td>");
					strBuf.append("<td>"+special.getIncomingDmgAmountKranette()+"</td>");
					strBuf.append("</tr>");
				}
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}		
	
}
