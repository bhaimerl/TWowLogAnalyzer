package helper.classes.utils;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import helper.classes.Warrior;

public class WarriorUtils {
	
	public static HashMap<String, Warrior> warriorMap = null;

	private static Warrior getWarriorByName(String name) {
		Warrior current = null;
		if(warriorMap==null) {
			warriorMap = new HashMap<>();
		}
		if(warriorMap.get(name)==null) {
			current = new Warrior();
			warriorMap.put(name, current);
		} else {
			current = warriorMap.get(name);
		}
		return current;
	}	
	
	public static int getWrathAmount(String logline) {
		int rageAmount = 0;
		if(logline!=null) {
			String name;
			if(logline.indexOf("Unbridled Wrath")>=0) {
				StringTokenizer strTok = new StringTokenizer(logline, " ");
				strTok.nextElement();
				strTok.nextElement();
				//Name
				name = strTok.nextElement()+"";
				//gains
				strTok.nextElement();
				//rage amount
				try {
					rageAmount+=Integer.parseInt(strTok.nextElement()+"");
				} catch(Exception e) {System.out.println("Problem bei: "+name);}
			}
		}
		return rageAmount;
	}		
	
	public static void findEntryForWarrior(String logline) {
		String sunder= "casts Sunder";
		String deathWish="is afflicted by Death Wish";
		String windfury="gains 1 extra attack through Windfury";
		String crusader= "gains Holy Strength";
		String wrath= "Unbridled Wrath";
		String flametongue="Flametongue Attack";
		String flurry= "gains Flurry";
		String enrage="gains Enrage";
		
		String execute1="'s Execute hits ";
		String execute2="'s Execute crits ";
		String bloodthirst1 = "'s Bloodthirst crits ";
		String bloodthirst2 = "'s Bloodthirst hits ";

		if(logline.indexOf(sunder)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setSunders(war.getSunders()+1);
		}
		if(logline.indexOf(deathWish)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setDeathWish(war.getDeathWish()+1);
		}
		if(logline.indexOf(windfury)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setWindFury(war.getWindFury()+1);
		}
		if(logline.indexOf(crusader)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setCrusader(war.getCrusader()+1);
		}
		if(logline.indexOf(wrath)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setWrath(war.getWrath()+General.getAmountGains("Unbridled Wrath", logline));
		}
		if(logline.indexOf(flametongue)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setFlametongue(war.getFlametongue()+1);
		}
		if(logline.indexOf(flurry)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setFlurry(war.getFlurry()+1);
		}
		if(logline.indexOf(enrage)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setEnrage(war.getEnrage()+1);
		}		
		if(logline.indexOf(execute1)>=0 || logline.indexOf(execute2)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setExecuteAmount(war.getExecuteAmount()+1);
		}
		if(logline.indexOf(execute1)>=0 || logline.indexOf(execute2)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			int currentExecute = General.getAmountAtEnd(execute1, execute2, logline);
			if(currentExecute>=war.getHighestExecute()) {
				war.setHighestExecute(currentExecute);
				String target = General.getTarget(execute1, execute2, logline);
				war.setHighestExecuteTarget(target);
			}
		}
		
		if(logline.indexOf(bloodthirst1)>=0 || logline.indexOf(bloodthirst2)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			war.setBloodThirstAmount(war.getBloodThirstAmount()+1);
		}
		if(logline.indexOf(bloodthirst1)>=0 || logline.indexOf(bloodthirst2)>=0) {
			Warrior war = getWarriorByName(General.getPlayerName(logline));
			int currentBloodthirst = General.getAmountAtEnd(bloodthirst1, bloodthirst2, logline);
			if(currentBloodthirst>=war.getHighestBloodthirst()) {
				war.setHighestBloodthirst(currentBloodthirst);
				String target = General.getTarget(bloodthirst1, bloodthirst2, logline);
				war.setHighestBloodthirstTarget(target);
			}
		}
		
		
		//special.setIncomingDmgAmountKranette(special.getIncomingDmgAmountKranette()+General.getAmountAtEnd(incomingDamageAmountOnKranette, logline));

	}
	
	public static String getWarriors() {
		//clean all first
		StringBuffer strBuf = new StringBuffer();
		if(warriorMap!=null) {
			Set<String> warriors =  warriorMap.keySet();
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table align=\"center\" style=\"margin: 0px auto;\">");
			strBuf.append("<tr style='background-color: brown;'><td colspan='13'>WARRIOR</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Sunder Armor</th>");
			strBuf.append("<th>Deathwish</th>");
			strBuf.append("<th>Windfury Procs</th>");
			strBuf.append("<th>Crusader Procs</th>");
			strBuf.append("<th>rage from UB</th>");
			strBuf.append("<th>Flametongue procs</th>");
			strBuf.append("<th>Flurry procs</th>");
			strBuf.append("<th>Enrage procs</th>");
			strBuf.append("<th>Executes</th>");
			strBuf.append("<th>Highest Execute</th>");
			strBuf.append("<th>Bloodthirsts</th>");
			strBuf.append("<th>Highest Bloodthirst</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String warriorName : warriors) {
				Warrior warri = warriorMap.get(warriorName);
				if(warri.getSunders()>0) {
					strBuf.append("<tr>");
					strBuf.append("<td>"+warriorName+"</td>");
					strBuf.append("<td>"+warri.getSunders()+"</td>");
					strBuf.append("<td>"+warri.getDeathWish()+"</td>");
					strBuf.append("<td>"+warri.getWindFury()+"</td>");
					strBuf.append("<td>"+warri.getCrusader()+"</td>");
					strBuf.append("<td>"+warri.getWrath()+"</td>");
					strBuf.append("<td>"+warri.getFlametongue()+"</td>");
					strBuf.append("<td>"+warri.getFlurry()+"</td>");
					strBuf.append("<td>"+warri.getEnrage()+"</td>");
					strBuf.append("<td>"+warri.getExecuteAmount()+"</td>");
					strBuf.append("<td>"+warri.getHighestExecute()+"=> "+warri.getHighestExecuteTarget()+"</td>");
					strBuf.append("<td>"+warri.getBloodThirstAmount()+"</td>");
					strBuf.append("<td>"+warri.getHighestBloodthirst()+"=> "+warri.getHighestBloodthirstTarget()+"</td>");
					strBuf.append("</tr>");
				}
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
}
