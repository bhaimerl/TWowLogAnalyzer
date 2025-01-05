package helper.classes.utils;

public class DruidUtils {
	

	public static boolean isDruid(String logline) {
		boolean isDruid = false;
		if((logline.contains(Constants.healingTouch) || 
			logline.contains(Constants.poisonCHarge) || 
			logline.contains(Constants.regrowth) || 
			logline.contains(Constants.swipe) || 
			logline.contains(Constants.maul) || 
			logline.contains(Constants.growl) || 
			logline.contains(Constants.savegeFury) )) {
			isDruid = true;
		}
		return isDruid;
	}
	

}
