package helper.classes.utils;

import java.util.ArrayList;

public class Raid {
	
	
	public static String getRaid(ArrayList<String> loglines) {
		String retVal = "Unknown";
		for (String string : loglines) {
			if(string!=null && string.indexOf("ZONE_INFO")>=0) {
				if(string.indexOf("ahn'qiraj")>=0) {
					return "AQ40";
				} 
				if(string.toLowerCase().indexOf("naxxramas")>=0) {
					return "Naxxramas";
				}
			}
		}
		return retVal;
	}
	
	public static boolean isAQ40Raid(String raid) {
		boolean isAQ = false;
		//12/3 19:26:48.414  ZONE_INFO: 03.12.24 19:26:48&gates of ahn'qiraj&0
		if(raid.equalsIgnoreCase("AQ40")) {
			return true;
		}
		return isAQ;
	}

}
