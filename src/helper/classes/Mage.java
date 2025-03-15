package helper.classes;

import org.apache.commons.lang3.StringUtils;

public class Mage extends Caster {


	int fireBlastCrits = 0;
	int fireBlastHits = 0;
	int highestFireBlast = 0;
	String highestFireBlastTarget ="";
    public void updateHighestFireBlastAmount(int amount, String target) {
        if (amount > this.highestFireBlast) {
            this.highestFireBlast = amount;
            this.highestFireBlastTarget = target;
        }
    }
	
	int arcaneRuptureCrits = 0;
	int arcaneRuptureHits = 0;
	int highestArcaneRupture=0;
	String highestArcaneRuptureTarget="";
    public void updateHighestArcaneRuptureAmount(int amount, String target) {
        if (amount > this.highestArcaneRupture) {
            this.highestArcaneRupture = amount;
            this.highestArcaneRuptureTarget = target;
        }
    }
	
	int fireBallCrits = 0;
	int fireBallHits = 0;
	int highestFireBall=0;
	String highestFireBallTarget="";
    public void updateHighestFireBallAmount(int amount, String target) {
        if (amount > this.highestFireBall) {
            this.highestFireBall = amount;
            this.highestFireBallTarget = target;
        }
    }
	
	
	int arcaneSurgeCrits = 0;
	int arcaneSurgeHits = 0;	
	int highestArcaneSurge=0;
	String highestArcaneSurgeTarget="";
    public void updateHighestArcaneSurgeAmount(int amount, String target) {
        if (amount > this.highestArcaneSurge) {
            this.highestArcaneSurge = amount;
            this.highestArcaneSurgeTarget = target;
        }
    }
	

	int arcaneMissleCrits = 0;
	int arcaneMissleHits = 0;
	int highestArcaneMissle=0;
	String highestArcaneMissleTarget="";
    public void updateHighestArcaneMissleAmount(int amount, String target) {
        if (amount > this.highestArcaneMissle) {
            this.highestArcaneMissle = amount;
            this.highestArcaneMissleTarget = target;
        }
    }
	
	
	int pyroBlastCrits = 0;
	int pyroBlastHits = 0;
	int highestPyroBlast=0;
	String highestPyroBlastTarget="";
    public void updateHighestPyroBlastAmount(int amount, String target) {
        if (amount > this.highestPyroBlast) {
            this.highestPyroBlast = amount;
            this.highestPyroBlastTarget = target;
        }
    }
	
	int scorchCrits = 0;
	int scorchHits = 0;
	int highestScorch=0;
	String higehstScorchTarget="";
    public void updateHighestScorchAmount(int amount, String target) {
        if (amount > this.highestScorch) {
            this.highestScorch = amount;
            this.higehstScorchTarget = target;
        }
    }

    public void incrementFireBlastCrits() {
        this.fireBlastCrits++;
    }
    public void incrementFireBlastHits() {
        this.fireBlastHits++;
    }
    public void incrementArcaneRuptureCrits() {
    	this.arcaneRuptureCrits++;
    }
    public void incrementArcaneRuptureHits() {
    	this.arcaneRuptureHits++;
    }
	public void incrementFireBallCrits() {
		this.fireBallCrits++;
	}
	public void incrementFireBallHits() {
		this.fireBallHits++;
	}
	public void incrementArcaneSurgeCrits() {
		this.arcaneSurgeCrits++;
	}
	public void incrementArcaneSurgeHits() {
		this.arcaneSurgeHits++;
	}
	public void incrementArcaneMisslesCrits() {
		this.arcaneMissleCrits++;
	}
	public void incrementArcaneMisslesHits() {
		this.arcaneMissleHits++;
	}
	public void incrementPyroBlastCrits() {
		this.pyroBlastCrits++;
	}
	public void incrementPyroBlastHits() {
		this.pyroBlastHits++;
	}
	public void incrementScorchCrits() {
		this.scorchCrits++;
	}
	public void incrementScorchHits() {
		this.scorchHits++;
	}
	
	
	
	
	public int getFireBlastCrits() {
		return fireBlastCrits;
	}

	public int getFireBlastHits() {
		return fireBlastHits;
	}

	public int getHighestFireBlast() {
		return highestFireBlast;
	}

	public String getHighestFireBlastTarget() {
		return StringUtils.abbreviate(highestFireBlastTarget,15);
	}

	public int getArcaneRuptureCrits() {
		return arcaneRuptureCrits;
	}

	public int getArcaneRuptureHits() {
		return arcaneRuptureHits;
	}

	public int getHighestArcaneRupture() {
		return highestArcaneRupture;
	}

	public String getHighestArcaneRuptureTarget() {
		return StringUtils.abbreviate(highestArcaneRuptureTarget,15);
	}

	public int getFireBallCrits() {
		return fireBallCrits;
	}

	public int getFireBallHits() {
		return fireBallHits;
	}

	public int getHighestFireBall() {
		return highestFireBall;
	}

	public String getHighestFireBallTarget() {
		return StringUtils.abbreviate(highestFireBallTarget,15);
	}

	public int getArcaneSurgeCrits() {
		return arcaneSurgeCrits;
	}

	public int getArcaneSurgeHits() {
		return arcaneSurgeHits;
	}

	public int getHighestArcaneSurge() {
		return highestArcaneSurge;
	}

	public String getHighestArcaneSurgeTarget() {
		return StringUtils.abbreviate(highestArcaneSurgeTarget,15);
	}

	public int getArcaneMissleCrits() {
		return arcaneMissleCrits;
	}

	public int getArcaneMissleHits() {
		return arcaneMissleHits;
	}

	public int getHighestArcaneMissle() {
		return highestArcaneMissle;
	}

	public String getHighestArcaneMissleTarget() {
		return StringUtils.abbreviate(highestArcaneMissleTarget,15);
	}

	public int getPyroBlastCrits() {
		return pyroBlastCrits;
	}

	public int getPyroBlastHits() {
		return pyroBlastHits;
	}

	public int getHighestPyroBlast() {
		return highestPyroBlast;
	}

	public String getHighestPyroBlastTarget() {
		return StringUtils.abbreviate(highestPyroBlastTarget,15);
	}

	public int getScorchCrits() {
		return scorchCrits;
	}

	public int getScorchHits() {
		return scorchHits;
	}

	public int getHighestScorch() {
		return highestScorch;
	}

	public String getHigehstScorchTarget() {
		return higehstScorchTarget;
	}

	public void setFireBlastCrits(int fireBlastCrits) {
		this.fireBlastCrits = fireBlastCrits;
	}

	public void setFireBlastHits(int fireBlastHits) {
		this.fireBlastHits = fireBlastHits;
	}

	public void setHighestFireBlast(int highestFireBlast) {
		this.highestFireBlast = highestFireBlast;
	}

	public void setHighestFireBlastTarget(String highestFireBlastTarget) {
		this.highestFireBlastTarget = highestFireBlastTarget;
	}

	public void setArcaneRuptureCrits(int arcaneRuptureCrits) {
		this.arcaneRuptureCrits = arcaneRuptureCrits;
	}

	public void setArcaneRuptureHits(int arcaneRuptureHits) {
		this.arcaneRuptureHits = arcaneRuptureHits;
	}

	public void setHighestArcaneRupture(int highestArcaneRupture) {
		this.highestArcaneRupture = highestArcaneRupture;
	}

	public void setHighestArcaneRuptureTarget(String highestArcaneRuptureTarget) {
		this.highestArcaneRuptureTarget = highestArcaneRuptureTarget;
	}

	public void setFireBallCrits(int fireBallCrits) {
		this.fireBallCrits = fireBallCrits;
	}

	public void setFireBallHits(int fireBallHits) {
		this.fireBallHits = fireBallHits;
	}

	public void setHighestFireBall(int highestFireBall) {
		this.highestFireBall = highestFireBall;
	}

	public void setHighestFireBallTarget(String highestFireBallTarget) {
		this.highestFireBallTarget = highestFireBallTarget;
	}

	public void setArcaneSurgeCrits(int arcaneSurgeCrits) {
		this.arcaneSurgeCrits = arcaneSurgeCrits;
	}

	public void setArcaneSurgeHits(int arcaneSurgeHits) {
		this.arcaneSurgeHits = arcaneSurgeHits;
	}

	public void setHighestArcaneSurge(int highestArcaneSurge) {
		this.highestArcaneSurge = highestArcaneSurge;
	}

	public void setHighestArcaneSurgeTarget(String highestArcaneSurgeTarget) {
		this.highestArcaneSurgeTarget = highestArcaneSurgeTarget;
	}

	public void setArcaneMissleCrits(int arcaneMissleCrits) {
		this.arcaneMissleCrits = arcaneMissleCrits;
	}

	public void setArcaneMissleHits(int arcaneMissleHits) {
		this.arcaneMissleHits = arcaneMissleHits;
	}

	public void setHighestArcaneMissle(int highestArcaneMissle) {
		this.highestArcaneMissle = highestArcaneMissle;
	}

	public void setHighestArcaneMissleTarget(String highestArcaneMissleTarget) {
		this.highestArcaneMissleTarget = highestArcaneMissleTarget;
	}

	public void setPyroBlastCrits(int pyroBlastCrits) {
		this.pyroBlastCrits = pyroBlastCrits;
	}

	public void setPyroBlastHits(int pyroBlastHits) {
		this.pyroBlastHits = pyroBlastHits;
	}

	public void setHighestPyroBlast(int highestPyroBlast) {
		this.highestPyroBlast = highestPyroBlast;
	}

	public void setHighestPyroBlastTarget(String highestPyroBlastTarget) {
		this.highestPyroBlastTarget = highestPyroBlastTarget;
	}

	public void setScorchCrits(int scorchCrits) {
		this.scorchCrits = scorchCrits;
	}

	public void setScorchHits(int scorchHits) {
		this.scorchHits = scorchHits;
	}

	public void setHighestScorch(int highestScorch) {
		this.highestScorch = highestScorch;
	}

	public void setHigehstScorchTarget(String higehstScorchTarget) {
		this.higehstScorchTarget = higehstScorchTarget;
	}

	
	

}
