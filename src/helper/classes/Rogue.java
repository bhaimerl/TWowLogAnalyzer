package helper.classes;

import org.apache.commons.lang3.StringUtils;

public class Rogue extends Melee{
	
	
	public int sliceAndDice=0;
    public void incrementSliceAndDice() {
        this.sliceAndDice++;
    }
	public int bladeFlurry=0;
    public void incrementBladeFlurry() {
        this.bladeFlurry++;
    }
	
	public int backStabHits = 0;
    public void incrementBackStabHits() {
        this.backStabHits++;
    }
	public int backStabCrits = 0;
    public void incrementBackStabCrits() {
        this.backStabCrits++;
    }
	public int highestBackStabAmount = 0;
	public String highestBackStabTarget="";
    public void updateHighestBackstabAmount(int amount, String target) {
        if (amount > this.highestBackStabAmount) {
            this.highestBackStabAmount = amount;
            this.highestBackStabTarget = target;
        }
    }
	
	
	public int eviscerateHit=0;
    public void incrementEviscerateHit() {
        this.eviscerateHit++;
    }
	public int eviscerateCrit=0;
    public void incrementEviscerateCrit() {
        this.eviscerateCrit++;
    }	
	public int highestEviscerateAmount = 0;
	public String highestEviscerateTarget="";
    public void updateHighestEviscerateAmount(int amount, String target) {
        if (amount > this.highestEviscerateAmount) {
            this.highestEviscerateAmount = amount;
            this.highestEviscerateTarget = target;
        }
    }
	
	public int sinisterStrikeHit=0;
    public void incrementSinisterStrikeHit() {
        this.sinisterStrikeHit++;
    }	
	public int sinisterStrikeCrit=0;
    public void incrementSinisterStrikeCrit() {
        this.sinisterStrikeCrit++;
    }	
	public int highestSinisterStrikeAmount = 0;
	public String highestSinisterStrikeTarget="";
    public void updateHighestSinisterStrikeAmount(int amount, String target) {
        if (amount > this.highestSinisterStrikeAmount) {
            this.highestSinisterStrikeAmount = amount;
            this.highestSinisterStrikeTarget = target;
        }
    }
	public int getSliceAndDice() {
		return sliceAndDice;
	}
	public void setSliceAndDice(int sliceAndDice) {
		this.sliceAndDice = sliceAndDice;
	}
	public int getBladeFlurry() {
		return bladeFlurry;
	}
	public void setBladeFlurry(int bladeFlurry) {
		this.bladeFlurry = bladeFlurry;
	}
	public int getBackStabHits() {
		return backStabHits;
	}
	public void setBackStabHits(int backStabHits) {
		this.backStabHits = backStabHits;
	}
	public int getBackStabCrits() {
		return backStabCrits;
	}
	public void setBackStabCrits(int backStabCrits) {
		this.backStabCrits = backStabCrits;
	}
	public int getHighestBackStabAmount() {
		return highestBackStabAmount;
	}
	public void setHighestBackStabAmount(int highestBackStabAmount) {
		this.highestBackStabAmount = highestBackStabAmount;
	}
	public String getHighestBackStabTarget() {
		return StringUtils.abbreviate(highestBackStabTarget,15);
	}
	public void setHighestBackStabTarget(String highestBackStabTarget) {
		this.highestBackStabTarget = highestBackStabTarget;
	}
	public int getEviscerateHit() {
		return eviscerateHit;
	}
	public void setEviscerateHit(int eviscerateHit) {
		this.eviscerateHit = eviscerateHit;
	}
	public int getEviscerateCrit() {
		return eviscerateCrit;
	}
	public void setEviscerateCrit(int eviscerateCrit) {
		this.eviscerateCrit = eviscerateCrit;
	}
	public int getHighestEviscerateAmount() {
		return highestEviscerateAmount;
	}
	public void setHighestEviscerateAmount(int highestEviscerateAmount) {
		this.highestEviscerateAmount = highestEviscerateAmount;
	}
	public String getHighestEviscerateTarget() {
		return StringUtils.abbreviate(highestEviscerateTarget,15);
	}
	public void setHighestEviscerateTarget(String highestEviscerateTarget) {
		this.highestEviscerateTarget = highestEviscerateTarget;
	}
	public int getSinisterStrikeHit() {
		return sinisterStrikeHit;
	}
	public void setSinisterStrikeHit(int sinisterStrikeHit) {
		this.sinisterStrikeHit = sinisterStrikeHit;
	}
	public int getSinisterStrikeCrit() {
		return sinisterStrikeCrit;
	}
	public void setSinisterStrikeCrit(int sinisterStrikeCrit) {
		this.sinisterStrikeCrit = sinisterStrikeCrit;
	}
	public int getHighestSinisterStrikeAmount() {
		return highestSinisterStrikeAmount;
	}
	public void setHighestSinisterStrikeAmount(int highestSinisterStrikeAmount) {
		this.highestSinisterStrikeAmount = highestSinisterStrikeAmount;
	}
	public String getHighestSinisterStrikeTarget() {
		return StringUtils.abbreviate(highestSinisterStrikeTarget,15);
	}
	public void setHighestSinisterStrikeTarget(String highestSinisterStrikeTarget) {
		this.highestSinisterStrikeTarget = highestSinisterStrikeTarget;
	}
	
	
	
}
