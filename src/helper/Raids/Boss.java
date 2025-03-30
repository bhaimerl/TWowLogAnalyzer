package helper.Raids;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import helper.classes.utils.Constants;

public class Boss implements Comparable<Boss> {
	public HashMap<String, Integer> playerParryCount;
	public HashMap<String, String> playerDeathCause;
	public int nightFallProcs=0;
	public int nightFallDmg = 0;
	
	public String name;
	public String firstHitTime;
	public String firstHitTimeOnly;
	public String diedTime="";
	public String setDiedTimeWithDate="";
	public String helpedToSunderUntil5="";
	public String sunderTimes="";
	public String base64DmgString ="";
	public String base64DebuffsString ="";


	
	public ArrayList<String> nightFallAppliedList = new ArrayList<String>();
	public ArrayList<String> nightFallFadedList = new ArrayList<String>();
	public ArrayList<String> faerieFireAppliedist = new ArrayList<>();	
	public ArrayList<String> faerieFireFadesList = new ArrayList<>();	
	public ArrayList<String> sunderArmorAppliedist = new ArrayList<>();	
	public ArrayList<String> sunderArmorFadesList = new ArrayList<>();	
	public ArrayList<String> exposedArmorAppliedist = new ArrayList<>();	
	public ArrayList<String> exposedArmorFadesList = new ArrayList<>();	
	public ArrayList<String> curseOfShadowsAppliedList = new ArrayList<>();;
	public ArrayList<String> curseOfShadowsFadedList = new ArrayList<>();;
	public ArrayList<String> curseOfRecklessnessAppliedList = new ArrayList<>();;
	public ArrayList<String> curseOfRecklessnessFadedList = new ArrayList<>();;
	public ArrayList<String> curseOfElementsAppliedList = new ArrayList<>();;
	public ArrayList<String> curseOfElementsFadedList = new ArrayList<>();;
	public java.util.Date firstHitDate = null;
	public java.util.Date diedTimeDate = null;
	public ArrayList<String> dispells = new ArrayList<>();
	public HashMap<String, Integer> dispellerMap = new HashMap<>();
	
	
	public String faerieFireApplied ="";
	public String faerieFireCastTimes ="";	
	
	
	
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
			java.util.Date parse = Constants.sdf.parse(firstHitTime);
			this.setFirstHitDate(parse);
		} catch (ParseException e) {}
		
	}
	public void setDiedTime(String diedTime) {
		this.diedTime = diedTime;
		try {
			java.util.Date parse = Constants.sdf.parse(diedTime);
			this.setDiedTimeDate(parse);
		} catch (ParseException e) {}
		
	}
	
	public void setHelpedToSunderUntil5(String helpedToSunderUntil5) {
		this.helpedToSunderUntil5 = helpedToSunderUntil5;
	}
	public void setSunderTimes(String sunderTimes) {
		this.sunderTimes = sunderTimes;
	}
	
	public ArrayList<String> getCurseOfRecklessnessAppliedList() {
		return curseOfRecklessnessAppliedList;
	}
	public ArrayList<String> getCurseOfRecklessnessFadedList() {
		return curseOfRecklessnessFadedList;
	}
	public void setCurseOfRecklessnessAppliedList(ArrayList<String> curseOfRecklessnessAppliedList) {
		this.curseOfRecklessnessAppliedList = curseOfRecklessnessAppliedList;
	}
	public void setCurseOfRecklessnessFadedList(ArrayList<String> curseOfRecklessnessFadedList) {
		this.curseOfRecklessnessFadedList = curseOfRecklessnessFadedList;
	}
	public java.util.Date getFirstHitDate() {
		return firstHitDate;
	}
	public java.util.Date getDiedTimeDate() {
		return diedTimeDate;
	}
	public void setFirstHitDate(java.util.Date firstHitDate) {
		this.firstHitDate = firstHitDate;
	}
	public void setDiedTimeDate(java.util.Date diedTimeDate) {
		this.diedTimeDate = diedTimeDate;
	}
	
	public ArrayList<String> getCurseOfShadowsAppliedList() {
		return curseOfShadowsAppliedList;
	}
	public ArrayList<String> getCurseOfShadowsFadedList() {
		return curseOfShadowsFadedList;
	}
	public ArrayList<String> getCurseOfElementsAppliedList() {
		return curseOfElementsAppliedList;
	}
	public ArrayList<String> getCurseOfElementsFadedList() {
		return curseOfElementsFadedList;
	}
	public void setCurseOfShadowsAppliedList(ArrayList<String> curseOfShadowsAppliedList) {
		this.curseOfShadowsAppliedList = curseOfShadowsAppliedList;
	}
	public void setCurseOfShadowsFadedList(ArrayList<String> curseOfShadowsFadedList) {
		this.curseOfShadowsFadedList = curseOfShadowsFadedList;
	}
	public void setCurseOfElementsAppliedList(ArrayList<String> curseOfElementsAppliedList) {
		this.curseOfElementsAppliedList = curseOfElementsAppliedList;
	}
	public void setCurseOfElementsFadedList(ArrayList<String> curseOfElementsFadedList) {
		this.curseOfElementsFadedList = curseOfElementsFadedList;
	}
	@Override
	public int compareTo(Boss o) {
		if(this.firstHitDate!=null && o.getFirstHitDate()!=null) {
		return this.firstHitDate.compareTo(o.getFirstHitDate());
		}
		return 0;
	}
	
	
	public HashMap<String, Integer> getPlayerParryCount() {
		return playerParryCount;
	}
	public void setPlayerParryCount(HashMap<String, Integer> playerParryCount) {
		this.playerParryCount = playerParryCount;
	}
	public int getNightFallProcs() {
		return nightFallProcs;
	}
	public int getNightFallDmg() {
		return nightFallDmg;
	}
	public void setNightFallProcs(int nightFallProcs) {
		this.nightFallProcs = nightFallProcs;
	}
	public void setNightFallDmg(int nightFallDmg) {
		this.nightFallDmg = nightFallDmg;
	}
	public HashMap<String, String> getPlayerDeathCause() {
		return playerDeathCause;
	}
	public void setPlayerDeathCause(HashMap<String, String> playerDeathCause) {
		this.playerDeathCause = playerDeathCause;
	}
	public String getFaerieFireApplied() {
		return faerieFireApplied;
	}
	public String getFaerieFireCastTimes() {
		return faerieFireCastTimes;
	}
	public void setFaerieFireApplied(String faerieFireApplied) {
		this.faerieFireApplied = faerieFireApplied;
	}
	public void setFaerieFireCastTimes(String faerieFireCastTimes) {
		this.faerieFireCastTimes = faerieFireCastTimes;
	}
	public ArrayList<String> getDispells() {
		return dispells;
	}
	
	public void setDispells(ArrayList<String> dispells) {
		this.dispells = dispells;
	}
	
    public HashMap<String, Integer> getSortedDispeller() {
    	// Namen aus der Liste extrahieren und zählen
	    for (String eintrag : dispells) {
	        // Namen extrahieren (alles vor der Klammer)
	        String name = eintrag.split("\\(")[0];
	        dispellerMap.put(name, dispellerMap.getOrDefault(name, 0) + 1);
	    }
	    return dispellerMap;
    }
	public ArrayList<String> getExposedArmorAppliedist() {
		return exposedArmorAppliedist;
	}
	public ArrayList<String> getExposedArmorFadesList() {
		return exposedArmorFadesList;
	}
	public void setExposedArmorAppliedist(ArrayList<String> exposedArmorAppliedist) {
		this.exposedArmorAppliedist = exposedArmorAppliedist;
	}
	public void setExposedArmorFadesList(ArrayList<String> exposedArmorFadesList) {
		this.exposedArmorFadesList = exposedArmorFadesList;
	}
	public String getBase64DmgString() {
		return base64DmgString;
	}
	public void setBase64DmgString(String base64DmgString) {
		this.base64DmgString = base64DmgString;
	}
	public ArrayList<String> getSunderArmorAppliedist() {
		return sunderArmorAppliedist;
	}
	public void setSunderArmorAppliedist(ArrayList<String> sunderArmorAppliedist) {
		this.sunderArmorAppliedist = sunderArmorAppliedist;
	}
	public ArrayList<String> getSunderArmorFadesList() {
		return sunderArmorFadesList;
	}
	public void setSunderArmorFadesList(ArrayList<String> sunderArmorFadesList) {
		this.sunderArmorFadesList = sunderArmorFadesList;
	}
	public ArrayList<String> getFaerieFireAppliedist() {
		return faerieFireAppliedist;
	}
	public void setFaerieFireAppliedist(ArrayList<String> faerieFireAppliedist) {
		this.faerieFireAppliedist = faerieFireAppliedist;
	}
	public ArrayList<String> getFaerieFireFadesList() {
		return faerieFireFadesList;
	}
	public void setFaerieFireFadesList(ArrayList<String> faerieFireFadesList) {
		this.faerieFireFadesList = faerieFireFadesList;
	}
	public String getSetDiedTimeWithDate() {
		return setDiedTimeWithDate;
	}
	public void setSetDiedTimeWithDate(String setDiedTimeWithDate) {
		this.setDiedTimeWithDate = setDiedTimeWithDate;
	}
	public String getFirstHitTimeOnly() {
		return firstHitTimeOnly;
	}
	public void setFirstHitTimeOnly(String firstHitTimeOnly) {
		this.firstHitTimeOnly = firstHitTimeOnly;
	}
	public String getBase64DebuffsString() {
		return base64DebuffsString;
	}
	public void setBase64DebuffsString(String base64DebuffsString) {
		this.base64DebuffsString = base64DebuffsString;
	}
	public ArrayList<String> getNightFallAppliedList() {
		return nightFallAppliedList;
	}
	public void setNightFallAppliedList(ArrayList<String> nightFallAppliedList) {
		this.nightFallAppliedList = nightFallAppliedList;
	}
	public ArrayList<String> getNightFallFadedList() {
		return nightFallFadedList;
	}
	public void setNightFallFadedList(ArrayList<String> nightFallFadedList) {
		this.nightFallFadedList = nightFallFadedList;
	}
	
	
	
}
