package helper.classes;

public class Caster extends Player {
	
	
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
	

}
