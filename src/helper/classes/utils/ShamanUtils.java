package helper.classes.utils;

public class ShamanUtils {
	
		
	public static boolean isShaman(String logline) {
		boolean isShaman = false;
		if((logline.contains(Constants.chainHeal) || 
			logline.contains(Constants.lesserHealingWave) || 
			logline.contains(Constants.lighningBolt) || 
			logline.contains(Constants.fireNova) || 
			logline.contains(Constants.frostShock) || 
			logline.contains(Constants.lightningStrike) || 
			logline.contains(Constants.castWindfuryTotem) || 
			logline.contains(Constants.castsEarthTotem) || 
			logline.contains(Constants.castsManSpringTotem) )) {
			isShaman = true;
		}
		return isShaman;
	}
	

}
