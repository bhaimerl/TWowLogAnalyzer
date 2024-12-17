package helper.Raids;

public class Boss {
	
	public String name;
	public String firstHitTime;
	public String diedTime;
	public String helpedToSunderUntil5="";
	public String sunderTimes="";
	public String curseOfShadow="";
	public String curseOfRecklessness="";
	public String curseOfElements="";
	public String getName() {
		return name;
	}
	public String getFirstHitTime() {
		return firstHitTime;
	}
	public String getDiedTime() {
		return diedTime;
	}
	public String getHelpedToSunderUntil5() {
		return helpedToSunderUntil5;
	}
	public String getSunderTimes() {
		return sunderTimes;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFirstHitTime(String firstHitTime) {
		this.firstHitTime = firstHitTime;
	}
	public void setDiedTime(String diedTime) {
		this.diedTime = diedTime;
	}
	public void setHelpedToSunderUntil5(String helpedToSunderUntil5) {
		this.helpedToSunderUntil5 = helpedToSunderUntil5;
	}
	public void setSunderTimes(String sunderTimes) {
		this.sunderTimes = sunderTimes;
	}
	
	
	public String getCurseOfShadow() {
		return curseOfShadow;
	}
	public String getCurseOfRecklessness() {
		return curseOfRecklessness;
	}
	public String getCurseOfElements() {
		return curseOfElements;
	}
	public void setCurseOfShadow(String curseOfShadow) {
		this.curseOfShadow = curseOfShadow;
	}
	public void setCurseOfRecklessness(String curseOfRecklessness) {
		this.curseOfRecklessness = curseOfRecklessness;
	}
	public void setCurseOfElements(String curseOfElements) {
		this.curseOfElements = curseOfElements;
	}
	@Override
	public String toString() {
		return "Boss [name=" + name + ", firstHitTime=" + firstHitTime + ", diedTime=" + diedTime
				+ ", helpedToSunderUntil5=" + helpedToSunderUntil5 + ", sunderTimes=" + sunderTimes + ", curseOfShadow="
				+ curseOfShadow + ", curseOfRecklessness=" + curseOfRecklessness + ", curseOfElements="
				+ curseOfElements + "]";
	}
	
	
	
	

}
