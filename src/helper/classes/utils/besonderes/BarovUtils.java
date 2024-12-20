package helper.classes.utils.besonderes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

import helper.Raids.Boss;
import helper.Raids.RaidBossMapping;
import helper.classes.Barov;
import helper.classes.Hunter;
import helper.classes.NameClassWrapper;
import helper.classes.Warrior;
import helper.classes.utils.Constants;
import helper.classes.utils.General;

public class BarovUtils {

	
    public static HashMap<String, Barov> barovMap = new HashMap<>(); 
	

    private static Barov getBarovByName(String name) {
        return barovMap.computeIfAbsent(name, k -> new Barov());
    }
	    
    private static void updateBarovStats(String logline, String playerName, String keyword1, String keyword2, Consumer<Barov> action, HashMap<String, ArrayList<NameClassWrapper>> allPlayers) {
        if (logline.contains(keyword1) || logline.contains(keyword2)) {
        	Barov barov = getBarovByName(playerName);
        	if(barov.getPlayerName()==null) {
        		barov.setPlayerName(playerName);
        	}
        	if(barov.getPlayerClass()==null) {
        		barov.setPlayerClass(General.getPlayerClass(allPlayers, playerName));
        	}
            action.accept(barov);
        }
    }
    
    
	//Servant of Weldon Barov (Inkling) 
	
//	ArrayList<String> logsFromBossByName = NightFallUtils.getLogsFromBossByName("Loatheb", fileAsArrayList);
	public static String countBarovCallsOnHuhuran(ArrayList<String> allData) {
		StringBuffer strBuf = new StringBuffer();
		String bossname = "Huhuran";
		String time = "";
		String date = "";
		Set<String> entry = new HashSet();
		ArrayList<String> logsFromBossByName = General.getLogsFromBossByName(bossname, allData);
		String currentPLayer = "";
		for (String currentLogLine : logsFromBossByName) {
			if(currentLogLine.indexOf("Servant")>=0 && currentLogLine.indexOf("Barov")>=0) {
				currentPLayer = General.getEntryAtPosition(currentLogLine, 6);
				date = General.getEntryAtPosition(currentLogLine, 0);
				time = General.getEntryAtPosition(currentLogLine, 1);
				if(currentPLayer.indexOf("(")>=0 && currentPLayer.indexOf(")")>=0) {
					currentPLayer= currentPLayer.replace("(", "");
					currentPLayer= currentPLayer.replace(")", "");
					entry.add(currentPLayer);
				}
			}
		}
		
		strBuf.append("<br>");				
		strBuf.append("<table align=\"left\"");
		strBuf.append("<tr><td colspan='2'>"+bossname+" ("+entry.size()+") BarovCaller used</td></tr>");
		strBuf.append("<tr>");
		strBuf.append("<th>Nr</th>");
		strBuf.append("<th>Name</th>");
		strBuf.append("<tr>");
		
		
		//System.out.println(entry.size()+" BarovCaller used within BossLogs from: "+date+" "+time+" =>"+bossname);
		Iterator<String> iterator = entry.iterator();
		int i=1;
		while(iterator.hasNext()) {
			strBuf.append("<tr>");
			strBuf.append("<td>"+i+++"</td>");
			strBuf.append("<td>"+iterator.next()+"</td>");
			strBuf.append("<tr>");
		}
		strBuf.append("</table>");
		return strBuf.toString();
	}
	
	
	
	public static void findEntryForFrostUser(String logline, HashMap<String, ArrayList<NameClassWrapper>>  allValidPLayers) {
    	String currentPlayer = General.getPlayerName(logline);
    	if(logline.contains("Frostbolt")) {
        	updateBarovStats(logline, currentPlayer, "Frostbolt hits Viscidus", "Frostbolt crits Viscidus", Barov::incrementFrostBall, allValidPLayers);
    	} else if(logline.contains("Frost damage.")){
    		if(currentPlayer.equals("Mitjatyr")) {
        		System.out.println(logline);
    		}
        	updateBarovStats(logline, currentPlayer, "hits Viscidus", "crits Viscidus", Barov::incrementOthers, allValidPLayers);
    	}
	}
	
	public static String getViscidusCheck() {
		//clean all first
		StringBuffer strBuf = new StringBuffer();
		if(barovMap!=null) {
			SortedSet<String> barover =  new TreeSet<>(barovMap.keySet());
			strBuf.append("<br>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: ;'><td colspan='3'>Viscidus Frost Damage</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Frost Bolts</th>");
			strBuf.append("<th>Other Frost Attacks</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String baroverName : barover) {
				Barov barov = barovMap.get(baroverName);
					strBuf.append("<tr>");
					strBuf.append("<td>"+barov.getPlayerName()+"</td>");
					strBuf.append("<td>"+barov.getFrostBalls()+"</td>");
					strBuf.append("<td>"+barov.getOtherFrostMagic()+"</td>");
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
	
	
	public static String doAQChecksTogether(ArrayList<String> allData) {
		StringBuffer strBuf = new StringBuffer();
		//leer = ca 182zeichen
		String barovCheck = countBarovCallsOnHuhuran(allData);
		//leer = ca 185zeichen
		String frostOilCheck = getViscidusCheck();
		
		if(barovCheck!=null && barovCheck.length()>200 && frostOilCheck!=null && frostOilCheck.length()>200) {
			strBuf.append("<table class='classTable' align=\"left\"");
			strBuf.append("<tr><td colspan='2'>Spezific AQ40 checks</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th></th>");
			strBuf.append("<th></th>");
			strBuf.append("</tr>");
			strBuf.append("<tr>");
			strBuf.append("<td>"+barovCheck+"</td>");
			strBuf.append("<td>"+frostOilCheck+"</td>");
			strBuf.append("<tr>");
		}
		return strBuf.toString();		
	}
	
}
