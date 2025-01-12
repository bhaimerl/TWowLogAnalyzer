package helper.classes;

public class Warlock extends Caster {

    private int shadowtrance;
    private int lifeTaps;
    private int lifeTapMana;
    private int shadowBoltHits;
    private int shadowBoltCrits;
    private int highestSBAmount;
    private String highestSBTarget="";
    private int soulFireHits;
    private int soulFireCrits;
    private int highestSFAmount;
    private String highestSFTarget="";
    private int searingPainHits;
    private int searingPainCrits;
    private int highestSPAmount;
    private String highestSPTarget="";
    private int immolateHits;
    private int immolateCrits;
    private int conflagrateHits;
    private int conflagrateCrits;
    private int highestCflgrAmount;
    private String highestCflgrTarget="";
    
    private int coeCount = 0;
    private int cosCount = 0;
    private int corCount = 0;
    //missing getter
    
    
    
	// Getter and Setter methods
    public int getShadowtrance() {
        return shadowtrance;
    }

    public String getHighestSBTarget() {
		return highestSBTarget;
	}

	public String getHighestSFTarget() {
		return highestSFTarget;
	}

	public String getHighestSPTarget() {
		return highestSPTarget;
	}

	public String getHighestCflgrTarget() {
		return highestCflgrTarget;
	}

	public void incrementShadowtrance() {
        this.shadowtrance++;
    }

    public int getLifeTaps() {
        return lifeTaps;
    }

    public void incrementLifeTaps() {
        this.lifeTaps++;
    }

    public int getLifeTapMana() {
        return lifeTapMana;
    }

    public void addLifeTapMana(int amount) {
        this.lifeTapMana += amount;
    }
    public int getShadowBoltHits() {
        return shadowBoltHits;
    }

    public void incrementShadowBoltHits() {
        this.shadowBoltHits++;
    }

    public int getShadowBoltCrits() {
        return shadowBoltCrits;
    }

    public void incrementShadowBoltCrits() {
        this.shadowBoltCrits++;
    }

    public int getHighestSBAmount() {
        return highestSBAmount;
    }

    public void updateHighestSBAmount(int amount, String target) {
        if (amount > this.highestSBAmount) {
            this.highestSBAmount = amount;
            this.highestSBTarget = target;
        }
    }

    public int getSoulFireHits() {
        return soulFireHits;
    }

    public void incrementSoulFireHits() {
        this.soulFireHits++;
    }

    public int getSoulFireCrits() {
        return soulFireCrits;
    }

    public void incrementSoulFireCrits() {
        this.soulFireCrits++;
    }

    public int getHighestSFAmount() {
        return highestSFAmount;
    }

    public void updateHighestSFAmount(int amount, String target) {
        if (amount > this.highestSFAmount) {
            this.highestSFAmount = amount;
            this.highestSFTarget = target;
        }
    }

    public int getSearingPainHits() {
        return searingPainHits;
    }

    public void incrementSearingPainHits() {
        this.searingPainHits++;
    }

    public int getSearingPainCrits() {
        return searingPainCrits;
    }

    public void incrementSearingPainCrits() {
        this.searingPainCrits++;
    }

    public int getHighestSPAmount() {
        return highestSPAmount;
    }

    public void updateHighestSPAmount(int amount, String target) {
        if (amount > this.highestSPAmount) {
            this.highestSPAmount = amount;
            this.highestSPTarget = target;
        }
    }

    public int getImmolateHits() {
        return immolateHits;
    }

    public void incrementImmolateHits() {
        this.immolateHits++;
    }

    public int getImmolateCrits() {
        return immolateCrits;
    }

    public void incrementImmolateCrits() {
        this.immolateCrits++;
    }

    public int getConflagrateHits() {
        return conflagrateHits;
    }

    public void incrementConflagrateHits() {
        this.conflagrateHits++;
    }

    public int getConflagrateCrits() {
        return conflagrateCrits;
    }

    public void incrementConflagrateCrits() {
        this.conflagrateCrits++;
    }

    public int getHighestCflgrAmount() {
        return highestCflgrAmount;
    }

    public void updateHighestCflgrAmount(int amount, String target) {
        if (amount > this.highestCflgrAmount) {
            this.highestCflgrAmount = amount;
            this.highestCflgrTarget = target;
        }
    }
    
    public void incrementCoeCount() {
        this.coeCount++;
    }
    public void incrementCoSCount() {
        this.cosCount++;
    }
    public void incrementCoRCount() {
        this.corCount++;
    }
    public int getCoeCount() {
		return coeCount;
	}
	public int getCosCount() {
		return cosCount;
	}
	public int getCorCount() {
		return corCount;
	}
    
}
