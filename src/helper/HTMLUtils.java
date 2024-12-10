package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import helper.classes.NameClassWrapper;
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
//		strBuf.append("table, th, td {");
//		strBuf.append("  border: 1px solid black;");
//		strBuf.append("}");
		strBuf.append("</style>");
		strBuf.append("</head>");
		strBuf.append("<body>");		
		strBuf.append("<table>");		
		strBuf.append("<tr><td> Raid from: "+date+" "+start+" to: "+end+"</td></tr>");		
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
	
	public static String getAllPlayers(ArrayList<String> allCnt) {
		HashMap<String, ArrayList<NameClassWrapper>> allPlayers = Players.getAllPlayersSortedByClass(allCnt);
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<table>");		
		strBuf.append("<tr>");		
		strBuf.append("<td>DRUID</td>");		
		strBuf.append("<td>HUNTER</td>");		
		strBuf.append("<td>MAGE</td>");		
		strBuf.append("<td>PALADIN</td>");		
		strBuf.append("<td>PRIEST</td>");		
		strBuf.append("<td>ROGUE</td>");		
		strBuf.append("<td>SHAMAN</td>");		
		strBuf.append("<td>WARLOCK</td>");		
		strBuf.append("<td>WARRIOR</td>");		
		strBuf.append("</tr>");		
		strBuf.append("<tr>");		

		
		strBuf.append(getPlayerClassFromAllAsHTML("DRUID", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("HUNTER", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("MAGE", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("PALADIN", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("PRIEST", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("ROGUE", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("SHAMAN", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("WARLOCK", allPlayers));					
		strBuf.append(getPlayerClassFromAllAsHTML("WARRIOR", allPlayers));					

		strBuf.append("</tr>");		
		strBuf.append("</table>");		
		return strBuf.toString();
	}
	
	private static String getPlayerClassFromAllAsHTML(String className, HashMap<String, ArrayList<NameClassWrapper>> allPlayers) {
		ArrayList<NameClassWrapper> ncw = allPlayers.get(className);
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<td>");					
		strBuf.append("<table>");		
		for (NameClassWrapper nameClassWrapper : ncw) {
			strBuf.append("<tr><td><b>"+nameClassWrapper.getName()+"</b> ("+nameClassWrapper.getGuild()+")</td></tr>");		
		}
		strBuf.append("</table>");		
		strBuf.append("</td>");					
		return strBuf.toString();
	}

}
