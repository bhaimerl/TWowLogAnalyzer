package helper.classes.utils.besonderes;

import java.util.ArrayList;

import helper.Raids.Boss;
import helper.classes.besonderes.SpellVulnerability;

public class NightFallUtils {

	
    public static void calculateNightfall(ArrayList<String> bossLogs, Boss boss) {
    		calcNightfallEffect(bossLogs, boss);
    }
	
	
	
	public static ArrayList<ArrayList<String>> getAllLogsWithinElementalVulnerability(ArrayList<String> bossLogs) {
		ArrayList<ArrayList<String>> resultComplete = new ArrayList<>();
		ArrayList<String> dmgIntervallWIthingSpellVulnera = new ArrayList<>();
		int j=0;
		for(int i=0;i<bossLogs.size();i++) {
			String currentBossLog = bossLogs.get(i);
			if(currentBossLog.indexOf("Spell Vulnerability (1).")>=0) {
				dmgIntervallWIthingSpellVulnera = new ArrayList<>();				
				dmgIntervallWIthingSpellVulnera.add(bossLogs.get(i));
				boolean addNext = true;
				while(addNext) {
					i+=1;
					if(bossLogs.size()>i) {
						if(bossLogs.get(i).indexOf("Spell Vulnerability fades from ")>=0) {
							addNext = false;						
							dmgIntervallWIthingSpellVulnera.add(bossLogs.get(i));
						} else {
							addNext = true;
							dmgIntervallWIthingSpellVulnera.add(bossLogs.get(i));
						}
					} else {
						addNext = false;
					}
				}
				resultComplete.add(dmgIntervallWIthingSpellVulnera);
				//return result;
			}
		}
		return resultComplete;
	}
	
	

	public static void calcNightfallEffect(ArrayList<String> logsFromBossByName,Boss boss) {		
		ArrayList<ArrayList<String>> allLogsWithinElementalVulnerability = NightFallUtils.getAllLogsWithinElementalVulnerability(logsFromBossByName);

		SpellVulnerability sv = new SpellVulnerability();
		sv.fillData(allLogsWithinElementalVulnerability, boss);
		
		
//		int amountBlock = 0;
//		int procs = 0;
//		int amountComplete = 0;
//		String start, end = "";;
//		for (ArrayList<String> dmGBlockList: allLogsWithinElementalVulnerability) {
//			amountBlock = 0;
//			for (String currentLine : dmGBlockList) {
//				if(currentLine.indexOf("Fire damage")>=0 || currentLine.indexOf("Arcane damage")>=0 || currentLine.indexOf("Nature damage")>=0 ||
//						currentLine.indexOf("Holy damage")>=0 || currentLine.indexOf("Shadow damage")>=0 || currentLine.indexOf("Frost damage")>=0) {
//					amountBlock += General.getDmgAmountForNightFall(bossName, currentLine);
//				}
//					
//				//hier schauen, aller magischer schaden
//				//Arcane damage
//				//Fire Damage
//				//Lightning Bolt
//				//Holy damage.
//				//Shadow damage
//				//Nature damage
//			}
//			amountComplete+=amountBlock;
////			System.out.println("################ BLOCK DONE DMG Complete: "+amountBlock+ " end: "+end);
//		}		
//		System.out.println("Gesamter magischer Schaden waehrend Nightfall (Spell Vulnerability): " + amountComplete + " fuer insgesamt "+allLogsWithinElementalVulnerability.size()+" procs");
//		System.out.println("Davon 10% sind: "+((amountComplete/100) * 10));
	}
	
}
