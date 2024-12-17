package helper.Raids;


import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class Boss implements Comparable<Boss> {
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");		
	public String name;
	public String firstHitTime;
	public String diedTime;
	public String helpedToSunderUntil5="";
	public String sunderTimes="";
	public String curseOfShadow="";
	public String curseOfRecklessness="";
	public String curseOfElements="";
	public java.util.Date firstHitDate = null;
	
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
		try {
			java.util.Date parse = formatter.parse(firstHitTime);
			this.setFirstHitDate(parse);
		} catch (ParseException e) {}
		
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
	
	
	public SimpleDateFormat getFormatter() {
		return formatter;
	}
	public java.util.Date getFirstHitDate() {
		return firstHitDate;
	}
	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}
	public void setFirstHitDate(java.util.Date firstHitDate) {
		this.firstHitDate = firstHitDate;
	}
	@Override
	public String toString() {
		return "Boss [name=" + name + ", firstHitTime=" + firstHitTime + ", diedTime=" + diedTime
				+ ", helpedToSunderUntil5=" + helpedToSunderUntil5 + ", sunderTimes=" + sunderTimes + ", curseOfShadow="
				+ curseOfShadow + ", curseOfRecklessness=" + curseOfRecklessness + ", curseOfElements="
				+ curseOfElements + "]";
	}
	@Override
	public int compareTo(Boss o) {
		if(this.firstHitDate!=null && o.getFirstHitDate()!=null) {
		return this.firstHitDate.compareTo(o.getFirstHitDate());
		}
		return 0;
	}
	
	
	
	

}
