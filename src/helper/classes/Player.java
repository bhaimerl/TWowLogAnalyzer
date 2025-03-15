package helper.classes;

import java.util.ArrayList;

public class Player {
	
	public int deathCounter = 0;
    public void incrementDeath() {
        this.deathCounter++;
    }
	public ArrayList<String> deathCauses= new ArrayList<>();
    public void addDeathCause(String casue) {
        this.deathCauses.add(casue);
    }
	public ArrayList<String> getDeathCauses() {
		return deathCauses;
	}
	public void setDeathCauses(ArrayList<String> deathCauses) {
		this.deathCauses = deathCauses;
	}
	public int getDeathCounter() {
		return deathCounter;
	}
	public void setDeathCounter(int deathCounter) {
		this.deathCounter = deathCounter;
	}
}
