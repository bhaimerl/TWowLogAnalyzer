package helper.classes;

public class Barov implements Comparable<Barov>{
	
	public String playerName=null;
	public String playerClass=null;
	public int frostBalls=0;
	public void incrementFrostBall() {
		this.frostBalls++;
	}
	public int otherFrostMagic=0;
	public void incrementOthers() {
		this.otherFrostMagic++;
	}
	public String getPlayerName() {
		return playerName;
	}
	public String getPlayerClass() {
		return playerClass;
	}
	public int getFrostBalls() {
		return frostBalls;
	}
	public int getOtherFrostMagic() {
		return otherFrostMagic;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}
	public void setFrostBalls(int frostBalls) {
		this.frostBalls = frostBalls;
	}
	public void setOtherFrostMagic(int otherFrostMagic) {
		this.otherFrostMagic = otherFrostMagic;
	}
	
	@Override
	public int compareTo(Barov o) {
		if(this.playerClass!=null && o.getPlayerClass()!=null) {
		return this.playerClass.compareTo(o.getPlayerClass());
		}
		return 0;
	}
	
	
	
	
	

}
