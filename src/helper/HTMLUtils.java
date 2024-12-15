package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.constant.Constable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import helper.classes.NameClassWrapper;
import helper.classes.utils.Constants;
import helper.classes.utils.General;
import helper.classes.utils.Players;

public class HTMLUtils {
	
	
	public static String getTmpFileNameInclPath() {
		String fileName ="";
		try {
			fileName = File.createTempFile("temp-file", "tmp").getParent();
			fileName = fileName+"\\TwowAnalyzerResult.html";
			fileName = fileName.replace("\\", "/");
		} catch (IOException e) {
			//error
		}
		return fileName;
	}

	public static void writeFile(String str, boolean tmpFile) {
		String fileName ="";
		if(tmpFile) {
			fileName = getTmpFileNameInclPath();
		} else {
			fileName = "C:/development/spielerei/result.html";
		}
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.ISO_8859_1));
		    writer.write(str);
		    writer.close();		
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static String getAsHTMLString(String given, String raidName, String date, String start, String end) {
		StringBuffer strBuf = new StringBuffer();
	 	strBuf.append("<html>");
		strBuf.append("<head>");
		strBuf.append("<style>");
		strBuf.append(".classTable {");
		strBuf.append("  border-collapse: collapse;");
		strBuf.append("  border: 1px solid black;");
		strBuf.append("}");
		strBuf.append(".classTable td,  .classTable tr, .classTable th{");
		strBuf.append("  border-collapse: collapse;");
		strBuf.append("  border: 1px solid black;");
		strBuf.append("}");
		strBuf.append(" .classTable tr:nth-child(odd) { ");
		strBuf.append("  background-color: #f2f2f2");
		strBuf.append("}");

		strBuf.append("</style>");
		strBuf.append("</head>");
		strBuf.append("Raid from: "+date+" "+start+" to: "+end);		
		strBuf.append("<body>");		
		strBuf.append("<table class='borderTable'>");		
		strBuf.append("<tr>");		
		strBuf.append("<td>");		
		strBuf.append(given);
		strBuf.append("</td>");		
		strBuf.append("</tr>");				
		strBuf.append("</table>");		
		strBuf.append("</body>");		
		strBuf.append("</html>");		
		return strBuf.toString();
	}
	
	public static String getAllPlayers(HashMap<String, ArrayList<NameClassWrapper>> allPlayers) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<div style='font-size: 20; font-weight: bold;' >=>Players in raid</div><br>");
		strBuf.append("<table>");		
		strBuf.append("<tr>");		
		strBuf.append("<td style='background-color: "+Constants.DRUIDCOLOR+";'><b>DRUID</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.HUNTERCOLOR+";'><b>HUNTER</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.MAGECOLOR+";'><b>MAGE</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.PALADINCOLOR+";'><b>PALADIN</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.PRIESTCOLOR+";'><b>PRIEST</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.ROGUECOLOR+";'><b>ROGUE</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.SHAMANCOLOR+";'><b>SHAMAN</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.WARLOCKCOLOR+";'><b>WARLOCK</b></td>");		
		strBuf.append("<td style='background-color: "+Constants.WARRIORCOLOR+";'><b>WARRIOR</b></td>");		
		strBuf.append("</tr>");		
		strBuf.append("<tr>");		

		
		strBuf.append(getPlayerClassFromAllAsHTML("DRUID", allPlayers,Constants.DRUIDCOLOR ));					
		strBuf.append(getPlayerClassFromAllAsHTML("HUNTER", allPlayers, Constants.HUNTERCOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("MAGE", allPlayers, Constants.MAGECOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("PALADIN", allPlayers, Constants.PALADINCOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("PRIEST", allPlayers, Constants.PRIESTCOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("ROGUE", allPlayers, Constants.ROGUECOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("SHAMAN", allPlayers, Constants.SHAMANCOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("WARLOCK", allPlayers, Constants.WARLOCKCOLOR));					
		strBuf.append(getPlayerClassFromAllAsHTML("WARRIOR", allPlayers, Constants.WARRIORCOLOR));					

		strBuf.append("</tr>");		
		strBuf.append("</table>");		
		return strBuf.toString();
	}
	
	private static String getPlayerClassFromAllAsHTML(String className, HashMap<String, ArrayList<NameClassWrapper>> allPlayers, String color) {
		ArrayList<NameClassWrapper> ncw = allPlayers.get(className);
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<td style='background-color: "+color+";'>");					
		strBuf.append("<table>");		
		for (NameClassWrapper nameClassWrapper : ncw) {
			String guild = "";
			if(!nameClassWrapper.getGuild().equals("nil")) {
				guild = "("+nameClassWrapper.getGuild()+")";
			}
			strBuf.append("<tr><td><b>"+nameClassWrapper.getName()+"</b> "+guild+"</td></tr>");		
		}
		strBuf.append("</table>");		
		strBuf.append("</td>");					
		return strBuf.toString();
	}

}
