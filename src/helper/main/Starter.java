package helper.main;

import java.util.ArrayList;

import helper.FileUtils;
import helper.HTMLUtils;
import helper.Raids.Boss;
import helper.Raids.RaidBossMapping;
import helper.classes.utils.General;
import helper.classes.utils.WarriorUtils;
import helper.classes.utils.besonderes.BarovUtils;
import helper.classes.utils.besonderes.NightFallUtils;

public class Starter {
	
	public static void main(String[] args) { 
		ArrayList<String> fileAsArrayList = FileUtils.getFileAsArrayList("C:\\WoWCombatLogAQAQ.txt");
		ArrayList<String> bossesFromLog = RaidBossMapping.getBossesFromLog(fileAsArrayList);
//		NightFallUtils.calculateNightfall(fileAsArrayList);
//		ArrayList<String> logsFromBossByName = General.getLogsFromBossByName(RaidBossMapping.heiganTheUnclean, fileAsArrayList);
//		Boss boss = General.getBossStats(logsFromBossByName, RaidBossMapping.heiganTheUnclean);
//		System.out.println(boss);
//		for (String string : sunderlogsUntil5) {
//			//System.out.println(string);
//		}
//		ArrayList<String> cursesOnBoss = General.getCursesOnBoss(logsFromBossByName);
//		for (String string : cursesOnBoss) {
//			//System.out.println(string);
//		}
		//HTMLUtils.writeFile(HTMLUtils.getAsHTMLString(warriors), true);
//		
//		
//		//BarovUtils.countBarovCallsOnHuhuran(fileAsArrayList);
////		ArrayList<String> logsFromBossByName = NightFallUtils.getLogsFromBossByName("Loatheb", fileAsArrayList);
////		NightFallUtils.calcDamage(logsFromBossByName);
//		for (String string : fileAsArrayList) {
//			WarriorUtils.findEntryForWarrior(string);
////			RogueUtils.findEntryForRogue(string);
////			WarlockUtils.findEntryForWarlock(string);
////			SpecialUtils.findEntryForSpecial(string);
//		}
////		int size = fileAsArrayList.size();
////		String startTime = Filter.getTime(fileAsArrayList.get(0));
////		String endTime = Filter.getTime(fileAsArrayList.get(size-1));
//		
////		String warriors = WarriorUtils.getWarriors(); 
////		String rogues = RogueUtils.getRogues();
////		String warlocks = WarlockUtils.getWarlocks();
////		
//		//String specials = SpecialUtils.getSpecials();
////		HTMLUtils.writeFile(HTMLUtils.getAsHTMLString(warriors));
	}
}
	
