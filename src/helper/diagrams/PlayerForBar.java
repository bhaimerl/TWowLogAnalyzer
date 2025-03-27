package helper.diagrams;

public class PlayerForBar {
	
	public String playerName;
	public String playerClass;
	public int dmg;
	
	public PlayerForBar(String playerName, String playerClass, int dmg) {
		super();
		this.playerName = playerName;
		this.playerClass = playerClass;
		this.dmg = dmg;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerClass() {
		return playerClass;
	}
	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	

}
