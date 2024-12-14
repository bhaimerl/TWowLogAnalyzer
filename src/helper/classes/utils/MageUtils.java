package helper.classes.utils;

import java.util.HashMap;

import helper.classes.Mage;
import helper.classes.Warlock;

public class MageUtils {
	
	public static HashMap<String, Mage> magemap = null;

	
	private static Mage getMageByName(String name) {
		Mage current = null;
		if(magemap==null) {
			magemap = new HashMap<>();
		}
		if(magemap.get(name)==null) {
			current = new Mage();
			magemap.put(name, current);
		} else {
			current = magemap.get(name);
		}
		return current;
	}	

	
	public static void findEntryForMage(String logline) {
		
		
		
		String fireBlastCrits = "Fire Blast crits";
		String fireBlastHits = "Fire Blast hits";
		
		String arcaneRuptureCrits = "Arcane Rupture crits";
		String arcaneRuptureHits = "Arcane Rupture hits";
		
		String fireBallCrits = "Fireball crits";
		String fireBallHits = "Fireball hits";
		
		String arcaneSurgeCrits = "Arcane Surge crits";
		String arcaneSurgeHits = "Arcane Surge hits";	

		String arcaneMissleCrits = "Arcane Missiles crits";
		String arcaneMissleHits = "Arcane Missiles hits";
		
		String pyroBlastCrits = "Pyroblast crits";
		String pyroBlastHits = "Pyroblast hits";
		
		String scorchCrits = "Scorch crits";
		String scorchHits = "Scorch hits";
		

		String shadowtrance="gains Shadow Trance";
		String lifetaps="Life Tap.";
		String manaFromLifeTaps="Life Tap.";
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
		
		
		//Mana gains
		if(logline.indexOf(Constants.manFromVampirismTouch)>=0) {
			Mage mage = getMageByName(General.getPlayerName(logline));
			mage.setManaFromVampiricTouch(mage.getManaFromVampiricTouch()+General.getAmountGains(Constants.manFromVampirismTouch, logline));
		}
		if(logline.indexOf(Constants.manaFromJudgement)>=0 && logline.indexOf("Mana")>=0) {
			Mage mage = getMageByName(General.getPlayerName(logline));
			mage.setManaFromJudgementOfWisdom(mage.getManaFromJudgementOfWisdom()+General.getAmountGains(Constants.manaFromJudgement, logline));
		}
		if(logline.indexOf(Constants.manaFromBOW)>=0 && logline.indexOf("gains")>=0) {
			Mage mage = getMageByName(General.getPlayerName(logline));
			mage.setManFrombow(mage.getManFrombow()+General.getAmountGains(Constants.manaFromBOW, logline));
		}
		
	}

}
