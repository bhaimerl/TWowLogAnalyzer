package helper.classes;

public class Warrior extends Melee{
	
	public int sunders=0;
    public void incrementSunders() {
        this.sunders++;
    }	
	public int deathWish=0;
    public void incrementDeathWish() {
        this.deathWish++;
    }
	public int flurry=0;
    public void incrementFlurry() {
        this.flurry++;
    }
	public int enrage=0;
    public void incrementEnrage() {
        this.enrage++;
    }

	public int wrath=0;
	
    public int getWrath() {
        return wrath;
    }
    public void addWrath(int amount) {
        this.wrath += amount;
    }
	
	public int executeAmount=0;	
    public void incrementExecute() {
        this.executeAmount++;
    }
	public int highestExecute=0;
	public String highestExecuteTarget = "";
    public void updateHighestExecuteAmount(int amount, String target) {
        if (amount > this.highestExecute) {
            this.highestExecute = amount;
            this.highestExecuteTarget = target;
        }
    }	
	
	public int bloodThirstAmount=0;
    public void incrementBloodThirstAmount() {
        this.bloodThirstAmount++;
    }
	public int highestBloodthirst=0;	
	public String highestBloodthirstTarget = "";
    public void updateHighestBloodthirst(int amount, String target) {
        if (amount > this.highestBloodthirst) {
            this.highestBloodthirst = amount;
            this.highestBloodthirstTarget = target;
        }
    }	
	
	
	public int getSunders() {
		return sunders;
	}
	public int getDeathWish() {
		return deathWish;
	}
	public int getFlurry() {
		return flurry;
	}
	public int getEnrage() {
		return enrage;
	}	
	public int getExecuteAmount() {
		return executeAmount;
	}
	public int getHighestExecute() {
		return highestExecute;
	}
	public String getHighestExecuteTarget() {
		return highestExecuteTarget;
	}
	public int getBloodThirstAmount() {
		return bloodThirstAmount;
	}
	public int getHighestBloodthirst() {
		return highestBloodthirst;
	}
	public String getHighestBloodthirstTarget() {
		return highestBloodthirstTarget;
	}
	
	
}
