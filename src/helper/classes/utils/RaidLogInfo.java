package helper.classes.utils;

public class RaidLogInfo {
	

	public static String getUNiqueFileName(String raids, String raidDate, String raidTime, String raidGuild) {
		//DD_MM_hh_mm_raids_guildname
		//1/5_19:23:53.938_Blackwing Layer_Molten Core__schmetterlingsbrigade
		String date = raidDate.replace("/", "_");
		String time = raidTime.substring(0,8).replace(":", "");
		time = time.replace(".", "");
		String givenRaids = raids.replace(" ","");
		String guild =  raidGuild.replace(" ", "");
		String parserVersion = (Constants.VERSION).replace(".", "");
		return date+"_"+Players.getYear()+"_"+givenRaids+"_"+parserVersion+"_"+guild;
	}}
