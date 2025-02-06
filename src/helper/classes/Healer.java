package helper.classes;

import java.util.ArrayList;

public class Healer {
	
	public static ArrayList<String> healerMap = new ArrayList<>();

	public static void addHealer(String playerName) {
		healerMap.add(playerName);
	}
	
	public boolean isHealer(String name) {
		if(healerMap!=null) {
			if(healerMap.contains(name)) {
				return true;
			}
		}
		return false;
	}

}
