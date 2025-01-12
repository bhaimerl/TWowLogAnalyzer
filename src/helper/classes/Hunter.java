package helper.classes;

public class Hunter extends CasterMelee{ 
	
	public int autoShotHit=0;
	public void incrementAutoShotHit() {
		this.autoShotHit++;
	}
	public int autoShotCrit=0;
	public void incrementAutoShotCrit() {
		this.autoShotCrit++;
	}
	int highestAutoShot = 0;
	String highestAutoShotTarget ="";
    public void updateHighestAutoShotAmount(int amount, String target) {
        if (amount > this.highestAutoShot) {
            this.highestAutoShot = amount;
            this.highestAutoShotTarget = target;
        }
    }
	
	public int steadyShotHits=0;
	public void incrementSteadyShotHit() {
		this.steadyShotHits++;
	}
	public int steadyShotCrits=0;
	public void incrementSteadyShotCrit() {
		this.steadyShotCrits++;
	}
	int highestSteadyShot = 0;
	String highestSteadyShotTarget ="";
    public void updateHighestSteadyShotAmount(int amount, String target) {
        if (amount > this.highestSteadyShot) {
            this.highestSteadyShot = amount;
            this.highestSteadyShotTarget = target;
        }
    }

    public int multiShotHits=0;
	public void incrementMultiShotHit() {
		this.multiShotHits++;
	}    
	public int multiSHotCrits=0;
	public void incrementMultiShotCrit() {
		this.multiSHotCrits++;
	}
	
	int highestMultiShot = 0;
	String highestMultiShotTarget ="";
    public void updateHighestMultiShotAmount(int amount, String target) {
        if (amount > this.highestMultiShot) {
            this.highestMultiShot = amount;
            this.highestMultiShotTarget = target;
        }
    }

	public int arcaneShotHits=0;
	public void incrementArcaneShotHit() {
		this.arcaneShotHits++;
	}	
	public int arcaneShotCrits=0;
	public void incrementArcaneShotCrit() {
		this.arcaneShotCrits++;
	}	
	int highestArcaneShot = 0;
	String highestArcaneShotTarget ="";
    public void updateHighestArcaneShotAmount(int amount, String target) {
        if (amount > this.highestArcaneShot) {
            this.highestArcaneShot = amount;
            this.highestArcaneShotTarget = target;
        }
    }
	
	public int extraShotHits=0;
	public void incrementExtraShotHit() {
		this.extraShotHits++;
	}		
	public int extraShotCrits=0;
	public void incrementExtraShotCrit() {
		this.extraShotCrits++;
	}		
	int highestExtraShot = 0;
	String highestExtraShotTarget ="";
    public void updateHighestExtraShotAmount(int amount, String target) {
        if (amount > this.highestExtraShot) {
            this.highestExtraShot = amount;
            this.highestExtraShotTarget = target;
        }
    }
	
	public int aimedShotHits=0;
	public void incrementAimedShotHit() {
		this.aimedShotHits++;
	}			
	public int aimedShotCrtis=0;
	public void incrementAimedShotCrit() {
		this.aimedShotCrtis++;
	}		
	int highestAimedShot = 0;
	String highestAimedShotTarget ="";
    public void updateHighestAimedShotAmount(int amount, String target) {
        if (amount > this.highestAimedShot) {
            this.highestAimedShot = amount;
            this.highestAimedShotTarget = target;
        }
    }
	
	public int serpentStringAmount=0;
	public void incrementSerpentSting() {
		this.serpentStringAmount++;
	}		
	
	

	public int getAutoShotHit() {
		return autoShotHit;
	}

	public int getAutoShotCrit() {
		return autoShotCrit;
	}

	public int getHighestAutoShot() {
		return highestAutoShot;
	}

	public String getHighestAutoShotTarget() {
		return highestAutoShotTarget;
	}

	public int getSteadyShotHits() {
		return steadyShotHits;
	}

	public int getSteadyShotCrits() {
		return steadyShotCrits;
	}

	public int getHighestSteadyShot() {
		return highestSteadyShot;
	}

	public String getHighestSteadyShotTarget() {
		return highestSteadyShotTarget;
	}

	public int getMultiShotHits() {
		return multiShotHits;
	}

	public int getMultiSHotCrits() {
		return multiSHotCrits;
	}

	public int getHighestMultiShot() {
		return highestMultiShot;
	}

	public String getHighestMultiShotTarget() {
		return highestMultiShotTarget;
	}

	public int getArcaneShotHits() {
		return arcaneShotHits;
	}

	public int getArcaneShotCrits() {
		return arcaneShotCrits;
	}

	public int getHighestArcaneShot() {
		return highestArcaneShot;
	}

	public String getHighestArcaneShotTarget() {
		return highestArcaneShotTarget;
	}

	public int getExtraShotHits() {
		return extraShotHits;
	}

	public int getExtraShotCrits() {
		return extraShotCrits;
	}

	public int getHighestExtraShot() {
		return highestExtraShot;
	}

	public String getHighestExtraShotTarget() {
		return highestExtraShotTarget;
	}

	public int getAimedShotHits() {
		return aimedShotHits;
	}

	public int getAimedShotCrtis() {
		return aimedShotCrtis;
	}

	public int getHighestAimedShot() {
		return highestAimedShot;
	}

	public String getHighestAimedShotTarget() {
		return highestAimedShotTarget;
	}

	public int getSerpentStringAmount() {
		return serpentStringAmount;
	}
	
	
}
