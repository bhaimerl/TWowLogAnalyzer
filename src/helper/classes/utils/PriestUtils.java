package helper.classes.utils;

public class PriestUtils {
	
	
	
		public static boolean isPriest(String logline) {
		boolean isPriest = false;
		if((logline.contains(Constants.renew) || 
			logline.contains(Constants.flashHeal) || 
			logline.contains(Constants.epiphany) || 
			logline.contains(Constants.holyNova) || 
			logline.contains(Constants.enlighten) || 
			logline.contains(Constants.greaterHEal) || 
			logline.contains(Constants.vampiricEmbrace) || 
			logline.contains(Constants.mindBlast) || 
			logline.contains(Constants.mindFlay) )) {
			isPriest = true;
		}
		return isPriest;
	}
	

}
