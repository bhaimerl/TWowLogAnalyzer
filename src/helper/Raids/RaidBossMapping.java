package helper.Raids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RaidBossMapping {
	
	
	public static HashMap<String, ArrayList<String>> raidBossMap = new HashMap<>();

	
	//Naxx
	//SpiderWing
	public static String anubRekhan="Anub'Rekhan";
	public static String grandWidowFaerlina="Grand Widow Faerlina";
	public static String maexxna="Maexxna";
	
	//MIlitary
	public static String instructorRazuvious="Instructor Razuvious";
	public static String gothik ="Gothik the Harvester";
	public static String zeliek ="Sir Zeliek";
	public static String mograine ="Highlord Mograine";
	public static String blaumeux ="Lady Blaumeux";
	public static String thaneKortazz ="Thane Korth'azz";
	
	//plague
	public static String nothThePlaguebringer="Noth the Plaguebringer";
	public static String heiganTheUnclean="Heigan the Unclean";
	public static String loatheb="Loatheb";
	//Abom
	public static String patchwerk = "Patchwerk";
	public static String grobolus = "Grobbulus";
	public static String stalagg = "Stalagg";
	public static String feugen = "Feugen";	
	public static String thaddius = "Thaddius";
	public static String gluth = "Gluth";	
	//upper 
	public static String sapphiron = "Sapphiron";
	public static String kelThuzad = "Kel'Thuzad";
	
	//AQ40
	public static String skeram ="The Prophet Skeram";
	public static String trioVem = "Vem";
	public static String trioYauj = "Princess Yauj";
	public static String trioKri = "Lord Kri";
	public static String battleGuardSarture = "Battleguard Sartura";
	public static String  fankriss ="Fankriss the Unyielding";
	public static String viscidus ="Viscidus";
	public static String huhuran ="Princess Huhuran";
	public static String veklor = "Vek'lor";
	public static String veknilash = "Vek'nilash";
	public static String ouro ="Ouro";
	public static String cthun = "C'Thun";
	
	
	//ES
	public static String solnius = "Solnius";
	public static String erennius = "Erennius";
	
	
	//BWL
	public static String razorGore = "Razorgore the Untamed";
	public static String valeastrasz = "Vaelastrasz the Corrupt";
	public static String lashLayer = "Broodlord Lashlayer";
	public static String firemaw = "Firemaw";
	public static String ebonroc ="Ebonroc";
	public static String flamegor ="Flamegor";
	public static String chromaggus ="Chromaggus";
	public static String nefarian ="Nefarian";
	
	//MC
	public static String lucifron ="Lucifron";
	public static String magmadar ="Magmadar";
	public static String gehennas = "Gehennas"; 
	public static String garr = "Garr";
	public static String geddon = "Baron Geddon";
	public static String shazrah ="Shazzrah";
	public static String sulfuron ="Sulfuron Harbinger";
	public static String golemagg = "Golemagg";
	public static String ragnaros = "Ragnaros";
	
	//Ony
	public static String onyxia = "Onyxia";
	
	
	public static void assign() {
		ArrayList<String> naxxramas = new ArrayList<>();
		naxxramas.add(anubRekhan);
		naxxramas.add(grandWidowFaerlina);
		naxxramas.add(maexxna);
		naxxramas.add(instructorRazuvious);
		naxxramas.add(gothik);
		naxxramas.add(zeliek);
		naxxramas.add(mograine);
		naxxramas.add(blaumeux);
		naxxramas.add(thaneKortazz);
		naxxramas.add(nothThePlaguebringer);
		naxxramas.add(heiganTheUnclean);
		naxxramas.add(loatheb);
		naxxramas.add(patchwerk);
		naxxramas.add(grobolus);
		naxxramas.add(stalagg);
		naxxramas.add(feugen);
		
		naxxramas.add(thaddius);
		naxxramas.add(gluth);
		naxxramas.add(sapphiron);
		naxxramas.add(kelThuzad);		
		raidBossMap.put("Naxxramas", naxxramas);
		
		
		ArrayList<String> aq40 = new ArrayList<>();
		aq40.add(skeram);
		aq40.add(trioVem);
		aq40.add(trioYauj);
		aq40.add(trioKri);
		aq40.add(battleGuardSarture);
		aq40.add(fankriss);
		aq40.add(viscidus);
		aq40.add(huhuran);
		aq40.add(veklor);
		aq40.add(veknilash);
		aq40.add(ouro);
		aq40.add(cthun);
		raidBossMap.put("AQ40", aq40);
		
		
		ArrayList<String> emeraldSanktum = new ArrayList<>();
		emeraldSanktum.add(solnius);
		emeraldSanktum.add(erennius);
		raidBossMap.put("Emerald Sanktum", emeraldSanktum);
		
		ArrayList<String> ony = new ArrayList<>();
		ony.add(onyxia);
		raidBossMap.put("Onyxia", ony);
		
		ArrayList<String> bwl = new ArrayList<>();
		bwl.add(razorGore);
		bwl.add(valeastrasz);
		bwl.add(lashLayer);
		bwl.add(firemaw);
		bwl.add(ebonroc);
		bwl.add(flamegor);
		bwl.add(chromaggus);
		bwl.add(nefarian);
		raidBossMap.put("Blackwing Layer", bwl);

		
		ArrayList<String> mc = new ArrayList<>();
		mc.add(lucifron);
		mc.add(magmadar);
		mc.add(gehennas);
		mc.add(garr);
		mc.add(geddon);
		mc.add(shazrah);
		mc.add(sulfuron);
		mc.add(golemagg);
		mc.add(ragnaros);
		raidBossMap.put("Molten Core", mc);
		
	}
	
	public static Set<String> getRaidFromBosses(ArrayList<String> bosses) {
		Set<String> raids = new HashSet<>();
		if(raidBossMap.size()==0) {
			assign();
		}
		Set<String> keySet = raidBossMap.keySet();
		for (String currentBoss : bosses) {
			for (String raid : keySet) {
				ArrayList<String> bossesFromRaid = raidBossMap.get(raid);
				if(bossesFromRaid.contains(currentBoss)) {
					raids.add(raid);
				}
			}
		}
		return raids;
		
	}
	
	
	public static ArrayList<String> getBossesFromLog(ArrayList<String> logList) {
		if(raidBossMap.size()==0) {
			assign();
		}
		ArrayList<String> bossesFound = new ArrayList<>();
		ArrayList<String> allBosses = new ArrayList<>();	
		Set<String> keySet = raidBossMap.keySet();
		for (String raid : keySet) {
			allBosses.addAll(raidBossMap.get(raid));
		}
		//hits bossname, dann boss vorhanden
		for (String string : logList) {
			for (String currentBoss : allBosses) {
                if (string.contains("hits "+currentBoss)) {
                	bossesFound.add(currentBoss);
                	allBosses.remove(currentBoss);
                	break;
                }
            }			
		}
		return bossesFound;
	}
	
	
}
