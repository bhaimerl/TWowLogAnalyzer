package helper.classes.utils.besonderes;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.classes.Healer;

public class HealCommUtils {
	
	public static void main(String[] args) {
		getHealcomm();
	}
	
	
	 public static ArrayList<String> getHealcomm() {
        String filePath = "C:\\Games\\twmoa_1171\\WTF\\Account\\HIGHHEALS\\Nordanaar\\Highhealz\\SavedVariables\\HealCommListener.lua";
        ArrayList<String> values = new ArrayList<>();
        String regex = "\\[\"([^\"]+)\"\\]";
        Pattern pattern = Pattern.compile(regex);

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    values.add(matcher.group(1));
                }
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        return values;
    }	

	public static String getHealCommCheck() {
		
		//lade healcomm liste und prüfe einfahc ob ein healer healer ist, aber nicht in der addon liste steht
		ArrayList<String> healcommusers = getHealcomm();
		
		StringBuffer strBuf = new StringBuffer();
		if(Healer.healerMap!=null) {
			strBuf.append("<br><br>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: light-grey;'><td colspan='2'><b>HealComm Addon Check</b></td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name (used heal spells)</th>");
			strBuf.append("<th>identified on healcomm channel(Lyndrells addon)</th>");
			strBuf.append("</tr>");
			for (String healer : Healer.healerMap) {
					boolean hasAddon = false;
					String value = "<div style='color: red;'>not found</div>";
					if(healcommusers.contains(healer)) {
						hasAddon = true;
						value = "yes";
					}
					strBuf.append("<tr>");
					strBuf.append("<td>"+healer+"</td>");
					strBuf.append("<td>"+value+"</td>");
					strBuf.append("</tr>");
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
	
	
	
}
