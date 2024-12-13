package helper.classes.utils.besonderes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import helper.classes.utils.General;

public class BarovUtils {

	
	
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
	
	
	public static String countFrostOilUse(ArrayList<String> allData) {
		StringBuffer strBuf = new StringBuffer();
		//12/3 19:58:48.780  Ballertony begins to cast Frost Oil.
		HashMap<String, Integer> frostOilUsers = new HashMap<>();
		for (String string : allData) {
			if(string.indexOf("Frost Oil.")>=0) {
				String name = General.getPlayerName(string);
				if(frostOilUsers.get(name)==null) {
					frostOilUsers.put(name, 1);
				} else {
					frostOilUsers.put(name, frostOilUsers.get(name)+1);
				}
			}
		}
		strBuf.append("<br>");				
		strBuf.append("<table align=\"left\" >");
		strBuf.append("<tr'><td colspan='3'>Viscidus FrostOil Users("+frostOilUsers.size()+")</td></tr>");
		strBuf.append("<tr>");
		strBuf.append("<th>Nr</th>");
		strBuf.append("<th>Name</th>");
		strBuf.append("<th>FrostOil applied</th>");
		strBuf.append("<tr>");

		Iterator<String> iterator = frostOilUsers.keySet().iterator();
		int i=1;
		while(iterator.hasNext()) {
			strBuf.append("<tr>");
			String currentName=iterator.next();
			strBuf.append("<td>"+i+++"</td>");
			strBuf.append("<td>"+currentName+"</td>");
			strBuf.append("<td>"+frostOilUsers.get(currentName)+"</td>");
			strBuf.append("<tr>");
		}
		strBuf.append("</table>");
		return strBuf.toString();
	}
	
	public static String doAQChecksTogether(ArrayList<String> allData) {
		StringBuffer strBuf = new StringBuffer();
		//leer = ca 182zeichen
		String barovCheck = countBarovCallsOnHuhuran(allData);
		//leer = ca 185zeichen
		String frostOilCheck = countFrostOilUse(allData);
		
		if(barovCheck!=null && barovCheck.length()>200 && frostOilCheck!=null && frostOilCheck.length()>200) {
			strBuf.append("<br><br><div style='font-size: 20; font-weight: bold;' >Spezific AQ40 checks</div><br>");
			strBuf.append("<table class='classTable' align=\"left\"");
			strBuf.append("<tr><td colspan='2'></td></tr>");
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
