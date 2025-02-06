package helper.classes.utils.besonderes;

import java.util.ArrayList;

import helper.FileUtils;
import helper.classes.utils.General;

public class WarlockShadowTranceCheck {
	
	public static ArrayList<ArrayList<String>> getWarlockShadowTranceLogs(ArrayList<String> allLogs) {
		ArrayList<ArrayList<String>> allLogsWithShadowTrance = new ArrayList<>();
		
		ArrayList<String> matcherLines = new ArrayList<>();
		String name = null;
		for (String currentLine : allLogs) {
			if(currentLine.contains("gains Shadow Trance (1).")) {
				name = General.getPlayerName(currentLine);			
				matcherLines = new ArrayList<>();
			}
			if(name!=null && currentLine.contains(name) && currentLine.contains("Shadow")) {
				matcherLines.add(currentLine);
				System.out.println(currentLine);
				if(currentLine.contains("Shadow Trance fades from")) {
					allLogsWithShadowTrance.add(matcherLines);
					name = null;
				}
			}
		}
		
		FileUtils.saveEntriesToFile(allLogsWithShadowTrance);
		return allLogsWithShadowTrance;
	}

}
