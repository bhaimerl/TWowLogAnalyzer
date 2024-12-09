package helper.classes.utils.besonderes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import helper.classes.utils.General;

public class BarovUtils {

	
	
	//Servant of Weldon Barov (Inkling) 
	
//	ArrayList<String> logsFromBossByName = NightFallUtils.getLogsFromBossByName("Loatheb", fileAsArrayList);
	public static void countBarovCallsOnHuhuran(ArrayList<String> allData) {
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
				entry.add(currentPLayer);
			}
		}
		System.out.println(entry.size()+" BarovCaller used within BossLogs from: "+date+" "+time+" =>"+bossname);
		Iterator<String> iterator = entry.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
