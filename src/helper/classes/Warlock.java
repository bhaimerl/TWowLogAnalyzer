package helper.classes;

public class Warlock {
	
	public int shadowtrance=0;
	//gains Shadow Trance 
	public int manaFromVampiricTouch=0;
	//Vampiric Touch
	public int lifeTaps = 0;
	//Life Tap
	public int lifeTapMana = 0;
	//Life Tap
	public int manaFromJudgementOfWisdom=0;
	// gains 59 Mana from Ajnincolrum 's Judgement of Wisdom.
	public int manFrombow=0;
	//Aixolef gains 39 Mana from Elefanti 's Greater Blessing of Wisdom
	//begins to cast Shadow Bolt.
	public int shadowBoltHits=0;
	//Ellonias 's Shadow Bolt hits Death Talon Dragonspawn for 1241
	public int shadowBoltCrits=0;
	public int highestSBAmount=0;
	public String higheastShadowBoltTarget="";
	//Ellonias 's Shadow Bolt crits Death Talon Dragonspawn
	public int soulFireHits=0;
	public int soulFireCrits=0;
	public int souldFireProc=0;
	public int highestSFAmount=0;
	public String highestSoulFireTarget="";

	public int searingPainHits=0;
	public int searingPainCrits=0;
	public int higheastSPAmount=0;
	public String highestSearingPainTarget="";
	
	public int conflagrateHits=0;
	public int conflagrateCrits=0;
	public int highestCflgrAmount=0;
	public String highestConflagrateTarget="";
	
	public int immolateHits=0;
	public int immolateCrits=0;

	
	public int getShadowtrance() {
		return shadowtrance;
	}
	public void setShadowtrance(int shadowtrance) {
		this.shadowtrance = shadowtrance;
	}
	public int getManaFromVampiricTouch() {
		return manaFromVampiricTouch;
	}
	public void setManaFromVampiricTouch(int manaFromVampiricTouch) {
		this.manaFromVampiricTouch = manaFromVampiricTouch;
	}
	public int getLifeTapMana() {
		return lifeTapMana;
	}
	public void setLifeTapMana(int lifeTapMana) {
		this.lifeTapMana = lifeTapMana;
	}
	
	public int getLifeTaps() {
		return lifeTaps;
	}
	public void setLifeTaps(int lifeTaps) {
		this.lifeTaps = lifeTaps;
	}
	public int getManaFromJudgementOfWisdom() {
		return manaFromJudgementOfWisdom;
	}
	public void setManaFromJudgementOfWisdom(int manaFromJudgementOfWisdom) {
		this.manaFromJudgementOfWisdom = manaFromJudgementOfWisdom;
	}
	public int getManFrombow() {
		return manFrombow;
	}
	public void setManFrombow(int manFrombow) {
		this.manFrombow = manFrombow;
	}
	public int getShadowBoltHits() {
		return shadowBoltHits;
	}
	public void setShadowBoltHits(int shadowBoltHits) {
		this.shadowBoltHits = shadowBoltHits;
	}
	public int getShadowBoltCrits() {
		return shadowBoltCrits;
	}
	public void setShadowBoltCrits(int shadowBoltCrits) {
		this.shadowBoltCrits = shadowBoltCrits;
	}
	public int getSoulFireHits() {
		return soulFireHits;
	}
	public void setSoulFireHits(int soulFireHits) {
		this.soulFireHits = soulFireHits;
	}
	public int getSoulFireCrits() {
		return soulFireCrits;
	}
	public void setSoulFireCrits(int soulFireCrits) {
		this.soulFireCrits = soulFireCrits;
	}
	public int getSouldFireProc() {
		return souldFireProc;
	}
	public void setSouldFireProc(int souldFireProc) {
		this.souldFireProc = souldFireProc;
	}
	public int getSearingPainHits() {
		return searingPainHits;
	}
	public void setSearingPainHits(int searingPainHits) {
		this.searingPainHits = searingPainHits;
	}
	public int getSearingPainCrits() {
		return searingPainCrits;
	}
	public void setSearingPainCrits(int searingPainCrits) {
		this.searingPainCrits = searingPainCrits;
	}
	public int getConflagrateHits() {
		return conflagrateHits;
	}
	public void setConflagrateHits(int conflagrateHits) {
		this.conflagrateHits = conflagrateHits;
	}
	public int getConflagrateCrits() {
		return conflagrateCrits;
	}
	public void setConflagrateCrits(int conflagrateCrits) {
		this.conflagrateCrits = conflagrateCrits;
	}
	public int getImmolateHits() {
		return immolateHits;
	}
	public void setImmolateHits(int immolateHits) {
		this.immolateHits = immolateHits;
	}
	public int getImmolateCrits() {
		return immolateCrits;
	}
	public void setImmolateCrits(int immolateCrits) {
		this.immolateCrits = immolateCrits;
	}
	public int getHighestSFAmount() {
		return highestSFAmount;
	}
	public void setHighestSFAmount(int highestSFAmount) {
		this.highestSFAmount = highestSFAmount;
	}
	public int getHigheastSPAmount() {
		return higheastSPAmount;
	}
	public void setHigheastSPAmount(int higheastSPAmount) {
		this.higheastSPAmount = higheastSPAmount;
	}
	public int getHighestCflgrAmount() {
		return highestCflgrAmount;
	}
	public void setHighestCflgrAmount(int highestCflgrAmount) {
		this.highestCflgrAmount = highestCflgrAmount;
	}
	public int getHighestSBAmount() {
		return highestSBAmount;
	}
	public void setHighestSBAmount(int highestSBAmount) {
		this.highestSBAmount = highestSBAmount;
	}
	public String getHigheastShadowBoltTarget() {
		return higheastShadowBoltTarget;
	}
	public void setHigheastShadowBoltTarget(String higheastShadowBoltTarget) {
		this.higheastShadowBoltTarget = higheastShadowBoltTarget;
	}
	public String getHighestSoulFireTarget() {
		return highestSoulFireTarget;
	}
	public void setHighestSoulFireTarget(String highestSoulFireTarget) {
		this.highestSoulFireTarget = highestSoulFireTarget;
	}
	public String getHighestSearingPainTarget() {
		return highestSearingPainTarget;
	}
	public void setHighestSearingPainTarget(String highestSearingPainTarget) {
		this.highestSearingPainTarget = highestSearingPainTarget;
	}
	public String getHighestConflagrateTarget() {
		return highestConflagrateTarget;
	}
	public void setHighestConflagrateTarget(String highestConflagrateTarget) {
		this.highestConflagrateTarget = highestConflagrateTarget;
	}
	
	
	
}
