package helper.classes;

public class Druid extends CasterMelee { 
	
	public int faerieFire = 0;
	public void incrementFaerieFire() {
		this.faerieFire++;
	}

	public int rebirth = 0;
	public void incrementRebirth() {
		this.rebirth++;
	}

	public int removeCurse = 0;
	public void incrementRemoveCurse() {
		this.removeCurse++;
	}

	public int abolishPoison = 0;
	public void incrementAbolishPoison() {
		this.abolishPoison++;
	}
	
	public int improvedRejuvenation = 0;
	public void incrementImprovedRejuvenation() {
		this.improvedRejuvenation++;
	}
	
	public int improvedRegrowth  = 0;
	public void incrementImprovedRegrowth() {
		this.improvedRegrowth++;
	}
	
	public int insectSwarm = 0;
	public void incrementInsectSwarm() {
		this.insectSwarm++;
	}
	
	public int starFireHit=0;
	public void incrementstarFireHit() {
		this.starFireHit++;
	}
	public int starFireCrit=0;
	public void incrementstarFireCrit() {
		this.starFireCrit++;
	}
	int higheststarFire = 0;
	String higheststarFireTarget ="";
    public void updateHigheststarFireAmount(int amount, String target) {
        if (amount > this.higheststarFire) {
            this.higheststarFire = amount;
            this.higheststarFireTarget = target;
        }
    }

	public int moonFireHit=0;
	public void incrementmoonFireHit() {
		this.moonFireHit++;
	}
	public int moonFireCrit=0;
	public void incrementmoonFireCrit() {
		this.moonFireCrit++;
	}
	int highestmoonFire = 0;
	String highestmoonFireTarget ="";
    public void updateHighestmoonFireAmount(int amount, String target) {
        if (amount > this.highestmoonFire) {
            this.highestmoonFire = amount;
            this.highestmoonFireTarget = target;
        }
    }

	public int wrathHit=0;
	public void incrementwrathHit() {
		this.wrathHit++;
	}
	public int wrathCrit=0;
	public void incrementwrathCrit() {
		this.wrathCrit++;
	}
	int highestwrath = 0;
	String highestwrathTarget ="";
    public void updateHighestwrathAmount(int amount, String target) {
        if (amount > this.highestwrath) {
            this.highestwrath = amount;
            this.highestwrathTarget = target;
        }
    }    
	
	public int maulHit=0;
	public void incrementmaulHit() {
		this.maulHit++;
	}
	public int maulCrit=0;
	public void incrementmaulCrit() {
		this.maulCrit++;
	}
	int highestmaul = 0;
	String highestmaulTarget ="";
    public void updateHighestmaulAmount(int amount, String target) {
        if (amount > this.highestmaul) {
            this.highestmaul = amount;
            this.highestmaulTarget = target;
        }
    }

	public int swipeHit=0;
	public void incrementswipeHit() {
		this.swipeHit++;
	}
	public int swipeCrit=0;
	public void incrementswipeCrit() {
		this.swipeCrit++;
	}
	int highestswipe = 0;
	String highestswipeTarget ="";
    public void updateHighestswipeAmount(int amount, String target) {
        if (amount > this.highestswipe) {
            this.highestswipe = amount;
            this.highestswipeTarget = target;
        }
    }
    
	public int shredHit=0;
	public void incrementshredHit() {
		this.shredHit++;
	}
	public int shredCrit=0;
	public void incrementshredCrit() {
		this.shredCrit++;
	}
	int highestshred = 0;
	String highestshredTarget ="";
    public void updateHighestshredAmount(int amount, String target) {
        if (amount > this.highestshred) {
            this.highestshred = amount;
            this.highestshredTarget = target;
        }
    }
	public int getFaerieFire() {
		return faerieFire;
	}

	public int getRemoveCurse() {
		return removeCurse;
	}

	public int getAbolishPoison() {
		return abolishPoison;
	}

	public int getImprovedRejuvenation() {
		return improvedRejuvenation;
	}

	public int getImprovedRegrowth() {
		return improvedRegrowth;
	}

	public int getInsectSwarm() {
		return insectSwarm;
	}

	public int getStarFireHit() {
		return starFireHit;
	}

	public int getStarFireCrit() {
		return starFireCrit;
	}

	public int getHigheststarFire() {
		return higheststarFire;
	}

	public String getHigheststarFireTarget() {
		return higheststarFireTarget;
	}

	public int getMoonFireHit() {
		return moonFireHit;
	}

	public int getMoonFireCrit() {
		return moonFireCrit;
	}

	public int getHighestmoonFire() {
		return highestmoonFire;
	}

	public String getHighestmoonFireTarget() {
		return highestmoonFireTarget;
	}

	public int getWrathHit() {
		return wrathHit;
	}

	public int getWrathCrit() {
		return wrathCrit;
	}

	public int getHighestwrath() {
		return highestwrath;
	}

	public String getHighestwrathTarget() {
		return highestwrathTarget;
	}

	public int getMaulHit() {
		return maulHit;
	}

	public int getMaulCrit() {
		return maulCrit;
	}

	public int getHighestmaul() {
		return highestmaul;
	}

	public String getHighestmaulTarget() {
		return highestmaulTarget;
	}

	public int getSwipeHit() {
		return swipeHit;
	}

	public int getSwipeCrit() {
		return swipeCrit;
	}

	public int getHighestswipe() {
		return highestswipe;
	}

	public String getHighestswipeTarget() {
		return highestswipeTarget;
	}

	public int getShredHit() {
		return shredHit;
	}

	public int getShredCrit() {
		return shredCrit;
	}

	public int getHighestshred() {
		return highestshred;
	}

	public String getHighestshredTarget() {
		return highestshredTarget;
	}


	public void setManaFromVampiricTouch(int manaFromVampiricTouch) {
		this.manaFromVampiricTouch = manaFromVampiricTouch;
	}

	public void setManaFromJudgementOfWisdom(int manaFromJudgementOfWisdom) {
		this.manaFromJudgementOfWisdom = manaFromJudgementOfWisdom;
	}

	public void setManFrombow(int manFrombow) {
		this.manFrombow = manFrombow;
	}

	public void setFaerieFire(int faerieFire) {
		this.faerieFire = faerieFire;
	}

	public void setRemoveCurse(int removeCurse) {
		this.removeCurse = removeCurse;
	}

	public void setAbolishPoison(int abolishPoison) {
		this.abolishPoison = abolishPoison;
	}

	public void setImprovedRejuvenation(int improvedRejuvenation) {
		this.improvedRejuvenation = improvedRejuvenation;
	}

	public void setImprovedRegrowth(int improvedRegrowth) {
		this.improvedRegrowth = improvedRegrowth;
	}

	public void setInsectSwarm(int insectSwarm) {
		this.insectSwarm = insectSwarm;
	}

	public void setStarFireHit(int starFireHit) {
		this.starFireHit = starFireHit;
	}

	public void setStarFireCrit(int starFireCrit) {
		this.starFireCrit = starFireCrit;
	}

	public void setHigheststarFire(int higheststarFire) {
		this.higheststarFire = higheststarFire;
	}

	public void setHigheststarFireTarget(String higheststarFireTarget) {
		this.higheststarFireTarget = higheststarFireTarget;
	}

	public void setMoonFireHit(int moonFireHit) {
		this.moonFireHit = moonFireHit;
	}

	public void setMoonFireCrit(int moonFireCrit) {
		this.moonFireCrit = moonFireCrit;
	}

	public void setHighestmoonFire(int highestmoonFire) {
		this.highestmoonFire = highestmoonFire;
	}

	public void setHighestmoonFireTarget(String highestmoonFireTarget) {
		this.highestmoonFireTarget = highestmoonFireTarget;
	}

	public void setWrathHit(int wrathHit) {
		this.wrathHit = wrathHit;
	}

	public void setWrathCrit(int wrathCrit) {
		this.wrathCrit = wrathCrit;
	}

	public void setHighestwrath(int highestwrath) {
		this.highestwrath = highestwrath;
	}

	public void setHighestwrathTarget(String highestwrathTarget) {
		this.highestwrathTarget = highestwrathTarget;
	}

	public void setMaulHit(int maulHit) {
		this.maulHit = maulHit;
	}

	public void setMaulCrit(int maulCrit) {
		this.maulCrit = maulCrit;
	}

	public void setHighestmaul(int highestmaul) {
		this.highestmaul = highestmaul;
	}

	public void setHighestmaulTarget(String highestmaulTarget) {
		this.highestmaulTarget = highestmaulTarget;
	}

	public void setSwipeHit(int swipeHit) {
		this.swipeHit = swipeHit;
	}

	public void setSwipeCrit(int swipeCrit) {
		this.swipeCrit = swipeCrit;
	}

	public void setHighestswipe(int highestswipe) {
		this.highestswipe = highestswipe;
	}

	public void setHighestswipeTarget(String highestswipeTarget) {
		this.highestswipeTarget = highestswipeTarget;
	}

	public void setShredHit(int shredHit) {
		this.shredHit = shredHit;
	}

	public void setShredCrit(int shredCrit) {
		this.shredCrit = shredCrit;
	}

	public void setHighestshred(int highestshred) {
		this.highestshred = highestshred;
	}

	public void setHighestshredTarget(String highestshredTarget) {
		this.highestshredTarget = highestshredTarget;
	}
	public int getRebirth() {
		return rebirth;
	}
	public void setRebirth(int rebirth) {
		this.rebirth = rebirth;
	}
	

}
