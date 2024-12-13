package helper.classes.utils;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import helper.classes.Rogue;
import helper.classes.Warlock;
import helper.classes.Warrior;

public class WarlockUtils {
	
	public static HashMap<String, Warlock> warlockmap = null;

	
	private static Warlock getWarlockByName(String name) {
		Warlock current = null;
		if(warlockmap==null) {
			warlockmap = new HashMap<>();
		}
		if(warlockmap.get(name)==null) {
			current = new Warlock();
			warlockmap.put(name, current);
		} else {
			current = warlockmap.get(name);
		}
		return current;
	}	
	
	
	public static void findEntryForWarlock(String logline) {
		
		String shadowtrance="gains Shadow Trance";
		String lifetaps="Life Tap.";
		String manaFromLifeTaps="Life Tap.";
		String manFromVampirismTouch="Vampiric Touch.";
		String manaFromJudgement="Judgement of Wisdom.";
		String manaFromBOW="Greater Blessing of Wisdom.";
		String shadowBolthit="Shadow Bolt hits";
		String shadowBoltCrit="Shadow Bolt crits ";
		String soulFirehit="Soul Fire hits ";
		String soulFireCrit="Soul Fire crits ";
		String searingPainhit="Searing Pain hits ";
		String searingPainCrit="Searing Pain crits ";
		String immolateHit="Immolate hits ";
		String immolateCrit="Immolate crits ";
		String conflagrateHit="Conflagrate hits ";
		String conflagrateCrit="Conflagrate crits ";
		
		
		if(logline.indexOf(shadowtrance)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setShadowtrance(lock.getShadowtrance()+1);
		}
		if(logline.indexOf(lifetaps)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setLifeTaps(lock.getLifeTaps()+1);
		}
		if(logline.indexOf(lifetaps)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setLifeTapMana(lock.getLifeTapMana()+General.getAmountGains(manaFromLifeTaps, logline));
		}
		if(logline.indexOf(manFromVampirismTouch)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setManaFromVampiricTouch(lock.getManaFromVampiricTouch()+General.getAmountGains(manFromVampirismTouch, logline));
		}
		if(logline.indexOf(manaFromJudgement)>=0 && logline.indexOf("Mana")>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setManaFromJudgementOfWisdom(lock.getManaFromJudgementOfWisdom()+General.getAmountGains(manaFromJudgement, logline));
		}
		if(logline.indexOf(manaFromBOW)>=0 && logline.indexOf("gains")>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setManFrombow(lock.getManFrombow()+General.getAmountGains(manaFromBOW, logline));
		}
		if(logline.indexOf(shadowBolthit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setShadowBoltHits(lock.getShadowBoltHits()+1);
		}
		if(logline.indexOf(shadowBoltCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setShadowBoltCrits(lock.getShadowBoltCrits()+1);
		}
		
		if(logline.indexOf(shadowBolthit)>=0 || logline.indexOf(shadowBoltCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			int currentShadowBolt = General.getAmountAtEnd(shadowBolthit, shadowBoltCrit, logline);
			if(currentShadowBolt>=lock.getHighestSBAmount()) {
				lock.setHighestSBAmount(currentShadowBolt);
				String target = General.getTarget(shadowBolthit, shadowBoltCrit, logline);
				lock.setHigheastShadowBoltTarget(target);
			}
		}
		
		
		if(logline.indexOf(soulFirehit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setSoulFireHits(lock.getSoulFireHits()+1);
		}
		if(logline.indexOf(soulFireCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setSoulFireCrits(lock.getSoulFireCrits()+1);
		}
		
		if(logline.indexOf(soulFirehit)>=0 || logline.indexOf(soulFireCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			int currentSoulFire = General.getAmountAtEnd(soulFirehit, soulFireCrit, logline);
			if(currentSoulFire>=lock.getHighestSFAmount()) {
				lock.setHighestSFAmount(currentSoulFire);
				String target = General.getTarget(soulFirehit, soulFireCrit, logline);
				lock.setHighestSoulFireTarget(target);
			}
		}
		
		
		
		if(logline.indexOf(searingPainhit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setSearingPainHits(lock.getSearingPainHits()+1);
		}
		if(logline.indexOf(searingPainCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setSearingPainCrits(lock.getSearingPainCrits()+1);
		}
		
		if(logline.indexOf(searingPainhit)>=0 || logline.indexOf(searingPainCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			int currentSearingPain = General.getAmountAtEnd(searingPainhit, searingPainCrit, logline);
			if(currentSearingPain>=lock.getHigheastSPAmount()) {
				lock.setHigheastSPAmount(currentSearingPain);
				String target = General.getTarget(searingPainhit, searingPainCrit, logline);
				lock.setHighestSearingPainTarget(target);
			}
		}
		
		
		
		if(logline.indexOf(immolateHit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setImmolateHits(lock.getImmolateHits()+1);
		}
		if(logline.indexOf(immolateCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setImmolateCrits(lock.getImmolateCrits()+1);
		}
		if(logline.indexOf(conflagrateHit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setConflagrateHits(lock.getConflagrateHits()+1);
		}
		if(logline.indexOf(conflagrateCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			lock.setConflagrateCrits(lock.getConflagrateCrits()+1);
		}
		if(logline.indexOf(conflagrateHit)>=0 || logline.indexOf(conflagrateCrit)>=0) {
			Warlock lock = getWarlockByName(General.getPlayerName(logline));
			int currentConflagrate = General.getAmountAtEnd(conflagrateHit, conflagrateCrit, logline);
			if(currentConflagrate>=lock.getHighestCflgrAmount()) {
				lock.setHighestCflgrAmount(currentConflagrate);
				String target = General.getTarget(conflagrateHit, conflagrateCrit, logline);
				lock.setHighestConflagrateTarget(target);
			}
		}
		
		
	}
	
	public static String getWarlocks() {
		StringBuffer strBuf = new StringBuffer();
		if(warlockmap!=null) {
			Set<String> warlocks =  warlockmap.keySet();
			strBuf.append("<br>");				
			strBuf.append("<body>");				
			strBuf.append("<table class='classTable' align=\"left\" width='100%'>");
			strBuf.append("<tr style='background-color: "+Constants.WARLOCKCOLOR+";'><td colspan='16'>WARLOCK</td></tr>");
			strBuf.append("<tr>");
			strBuf.append("<th>Name</th>");
			strBuf.append("<th>Shadowtrance</th>");
			strBuf.append("<th>LifeTaps</th>");
			strBuf.append("<th>Mana LifeTap</th>");
			strBuf.append("<th>Mana VampiricTouch</th>");
			strBuf.append("<th>Mana Judgement</th>");
			strBuf.append("<th>Mana BOW</th>");
			strBuf.append("<th>ShadowBolt Hit/Crit</th>");
			strBuf.append("<th>Highest SB</th>");
			strBuf.append("<th>SoulFire Hit/Crit</th>");
			strBuf.append("<th>Highest SF</th>");
			strBuf.append("<th>SearingPain Hit/Crit</th>");
			strBuf.append("<th>Highest SP</th>");
			strBuf.append("<th>Immolate Hit/Crit</th>");
			strBuf.append("<th>Conflagrate Hit/Crit</th>");
			strBuf.append("<th>Highest Cflgrt</th>");
			strBuf.append("</tr>");
			//Ballertony: [sunders=122, deathWish=18, windFury=236, crusader=74, wrath=264, flametongue=314, flurry=313, enrage=161]
			//System.out.println("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
			for (String warlockname : warlocks) {
				Warlock warlock = warlockmap.get(warlockname);
				if(warlock.getLifeTaps()>0) {
					strBuf.append("<tr>");
					strBuf.append("<td>"+warlockname+"</td>");
					strBuf.append("<td>"+warlock.getShadowtrance()+"</td>");
					strBuf.append("<td>"+warlock.getLifeTaps()+"</td>");
					strBuf.append("<td>"+warlock.getLifeTapMana()+"</td>");
					strBuf.append("<td>"+warlock.getManaFromVampiricTouch()+"</td>");
					strBuf.append("<td>"+warlock.getManaFromJudgementOfWisdom()+"</td>");
					strBuf.append("<td>"+warlock.getManFrombow()+"</td>");
					strBuf.append("<td>"+warlock.getShadowBoltHits()+" / "+warlock.getShadowBoltCrits()+"</td>");
					strBuf.append("<td>"+warlock.getHighestSBAmount()+"=> "+warlock.getHigheastShadowBoltTarget()+"</td>");					
					strBuf.append("<td>"+warlock.getSoulFireHits()+" / "+warlock.getSoulFireCrits()+"</td>");
					strBuf.append("<td>"+warlock.getHighestSFAmount()+"=> "+warlock.getHighestSoulFireTarget()+"</td>");
					strBuf.append("<td>"+warlock.getSearingPainHits()+" / "+warlock.getSearingPainCrits()+"</td>");
					strBuf.append("<td>"+warlock.getHigheastSPAmount()+"=> "+warlock.getHighestSearingPainTarget()+"</td>");					
					strBuf.append("<td>"+warlock.getImmolateHits()+" / "+warlock.getImmolateCrits()+"</td>");
					strBuf.append("<td>"+warlock.getConflagrateHits()+" / "+warlock.getConflagrateCrits()+"</td>");
					strBuf.append("<td>"+warlock.getHighestCflgrAmount()+"=> "+warlock.getHighestConflagrateTarget()+"</td>");					
					strBuf.append("</tr>");
				}
			}
			strBuf.append("</table>");
		}
		return strBuf.toString();
	}	
	
}
