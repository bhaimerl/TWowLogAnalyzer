package helper.classes;

public class Warrior {
	
	public int sunders=0;
	public int deathWish=0;
	public int windFury=0;
	public int crusader=0;
	public int wrath=0;
	public int flametongue=0;
	public int flurry=0;
	public int enrage=0;
	public int executeAmount=0;	
	public int highestExecute=0;
	public int bloodThirstAmount=0;
	public int highestBloodthirst=0;	
	public String highestExecuteTarget = "";
	public String highestBloodthirstTarget = "";	
	
	public String getHighestExecuteTarget() {
		return highestExecuteTarget;
	}
	public void setHighestExecuteTarget(String highestExecuteTarget) {
		this.highestExecuteTarget = highestExecuteTarget;
	}
	public String getHighestBloodthirstTarget() {
		return highestBloodthirstTarget;
	}
	public void setHighestBloodthirstTarget(String highestBloodthirstTarget) {
		this.highestBloodthirstTarget = highestBloodthirstTarget;
	}
	public int getSunders() {
		return sunders;
	}
	public void setSunders(int sunders) {
		this.sunders = sunders;
	}
	public int getDeathWish() {
		return deathWish;
	}
	public void setDeathWish(int deathWish) {
		this.deathWish = deathWish;
	}
	public int getWindFury() {
		return windFury;
	}
	public void setWindFury(int windFury) {
		this.windFury = windFury;
	}
	public int getCrusader() {
		return crusader;
	}
	public void setCrusader(int crusader) {
		this.crusader = crusader;
	}
	public int getWrath() {
		return wrath;
	}
	public void setWrath(int wrath) {
		this.wrath = wrath;
	}
	public int getFlametongue() {
		return flametongue;
	}
	public void setFlametongue(int flametongue) {
		this.flametongue = flametongue;
	}
	public int getFlurry() {
		return flurry;
	}
	public void setFlurry(int flurry) {
		this.flurry = flurry;
	}
	public int getEnrage() {
		return enrage;
	}
	public void setEnrage(int enrage) {
		this.enrage = enrage;
	}
	
	
	
	public int getExecuteAmount() {
		return executeAmount;
	}
	public void setExecuteAmount(int executeAmount) {
		this.executeAmount = executeAmount;
	}
	public int getHighestExecute() {
		return highestExecute;
	}
	public void setHighestExecute(int highestExecute) {
		this.highestExecute = highestExecute;
	}
	public int getBloodThirstAmount() {
		return bloodThirstAmount;
	}
	public void setBloodThirstAmount(int bloodThirstAmount) {
		this.bloodThirstAmount = bloodThirstAmount;
	}
	public int getHighestBloodthirst() {
		return highestBloodthirst;
	}
	public void setHighestBloodthirst(int highestBloodthirst) {
		this.highestBloodthirst = highestBloodthirst;
	}
	@Override
	public String toString() {
	           //("Name | Sunders | Deathwish | WindfuryProcs | CrusaderProcs | extra rage from unbridled wrath | FlametongueProcs | Flurry | Enrage");
		return ""+ sunders + "| " + deathWish + " | " + windFury + " | "+ crusader + " | " + wrath + " | " + flametongue + " | " + flurry + " | "+ enrage + "]";
	}
	
	
}
