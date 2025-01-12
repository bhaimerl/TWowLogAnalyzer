package helper.classes;

public class CasterMelee {

	public int manaFromVampiricTouch=0;
	public int manaFromJudgementOfWisdom=0;
	public int manFrombow=0;	
	
    public void addManaFromVampiricTouch(int amount) {
        this.manaFromVampiricTouch += amount;
    }

    public void addManaFromBow(int amount) {
        this.manFrombow += amount;
    }
    public void addManaFromJudgementOfWisdom(int amount) {
        this.manaFromJudgementOfWisdom += amount;
    }

	public int getManaFromVampiricTouch() {
		return manaFromVampiricTouch;
	}

	public int getManaFromJudgementOfWisdom() {
		return manaFromJudgementOfWisdom;
	}

	public int getManaFromBow() {
		return manFrombow;
	}
	public int windFury=0;
    public void incrementWindFury() {
        this.windFury++;
    }
    public int flametongue=0;
    public void incrementFlameTongue() {
        this.flametongue++;
    }
	public int crusader=0;
    public void incrementCrusader() {
        this.crusader++;
    }    
    
    
	public int getWindFury() {
		return windFury;
	}
	public int getFlametongue() {
		return flametongue;
	}
	public int getCrusader() {
		return crusader;
	}	
	

}
