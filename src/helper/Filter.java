package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import helper.classes.Warrior;

public class Filter {
	public static HashMap<String, Integer> sundermap = null;
	public static String startTime = "";
	public static String endTime = "";
	
	public static String getTime(String logLine) {
		String retTime = null;
		try {
			StringTokenizer strTok = new StringTokenizer(logLine, " ");
			strTok.nextElement();
			retTime = strTok.nextElement()+"";
		} catch(Exception e) {}
		return retTime;
	}
	
	public static String getStartTime(ArrayList<String> cntList) {
		String internalTime = null;
		int i=0;
		while(internalTime==null && i<=cntList.size()-1) {
			internalTime = getTime(cntList.get(i++));			
		}
		return internalTime;
	}
	
	public static String getEndTime(ArrayList<String> cntList) {
		String internalTime = null;
		int i=cntList.size()-1;
		while(internalTime==null && i>0) {
			internalTime = getTime(cntList.get(i--));			
		}
		return internalTime;
	}
		

}