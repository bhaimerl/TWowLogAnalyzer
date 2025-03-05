package helper.classes;

public class Priest extends Caster{ 
	
	public int renew = 0;
	public void incrementRenew() {
		this.renew++;
	}
	public int resurrection = 0;
	public void incrementResurrection() {
		this.resurrection++;
	}
	
	public int innervate = 0;
	public void incrementInnervate() {
		this.innervate++;
	}
	
	
	public int epiphany = 0;
	public void incrementEpiphany() {
		this.epiphany++;
	}
	public int holyNova = 0;
	public void incrementHolyNova() {
		this.holyNova++;
	}

	public int enlighten = 0;
	public void incrementEnlighten() {
		this.enlighten++;
	}
	
	public int mindFLay = 0;
	public void incrementMindFLay() {
		this.mindFLay++;
	}
	
	public int proclaimChampion  = 0;
	public void incrementChampion() {
		this.proclaimChampion++;
	}
	
	public int dispellMagic  = 0;
	public void incrementDispellMagic() {
		this.dispellMagic++;
	}

	public int flashHealHit=0;
	public void incrementflashHealHit() {
		this.flashHealHit++;
	}
	public int flashHealCrit=0;
	public void incrementflashHealCrit() {
		this.flashHealCrit++;
	}
	int highestflashHeal = 0;
	String highestflashHealTarget ="";
    public void updateHighestflashHealAmount(int amount, String target) {
        if (amount > this.highestflashHeal) {
            this.highestflashHeal = amount;
            this.highestflashHealTarget = target;
        }
    }
    
	public int smiteHit=0;
	public void incrementsmiteHit() {
		this.smiteHit++;
	}
	public int smiteCrit=0;
	public void incrementsmiteCrit() {
		this.smiteCrit++;
	}
	int highestsmite = 0;
	String highestsmiteTarget ="";
    public void updateHighestsmiteAmount(int amount, String target) {
        if (amount > this.highestsmite) {
            this.highestsmite = amount;
            this.highestsmiteTarget = target;
        }
    }
    
	public int holyfireHit=0;
	public void incrementholyfireHit() {
		this.holyfireHit++;
	}
	public int holyfireCrit=0;
	public void incrementholyfireCrit() {
		this.holyfireCrit++;
	}
	int highestholyfire = 0;
	String highestholyfireTarget ="";
    public void updateHighestholyfireAmount(int amount, String target) {
        if (amount > this.highestholyfire) {
            this.highestholyfire = amount;
            this.highestholyfireTarget = target;
        }
    }    
    
    public int t2GreaterHeal= 0;
	public void incrementT2GreaterHeal() {
		this.t2GreaterHeal++;
	}

    public int t2GreaterHealAmount = 0;
    public void addT2GreaterHealAmount(int amount) {
        this.t2GreaterHealAmount += amount;
    }
    
    public int powerWordFortitude = 0;
    public void incrementPowerWordFortitude() {
        this.powerWordFortitude++;
    }
    public int prayerOfFortitude = 0;
    public void incrementPrayerOfFortitude() {
        this.prayerOfFortitude++;
    }
    public int powerWordShield = 0;
    public void incrementpowerWordShield() {
        this.powerWordShield++;
    }
    

    
	public int greaterHealHit=0;
	public void incrementgreaterHealHit() {
		this.greaterHealHit++;
	}
	public int greaterHealCrit=0;
	public void incrementgreaterHealCrit() {
		this.greaterHealCrit++;
	}
	int highestgreaterHeal = 0;
	String highestgreaterHealTarget ="";
    public void updateHighestgreaterHealAmount(int amount, String target) {
        if (amount > this.highestgreaterHeal) {
            this.highestgreaterHeal = amount;
            this.highestgreaterHealTarget = target;
        }
    }    
	public int mindBlastHit=0;
	public void incrementmindBlastHit() {
		this.mindBlastHit++;
	}
	public int mindBlastCrit=0;
	public void incrementmindBlastCrit() {
		this.mindBlastCrit++;
	}
	int highestmindBlast = 0;
	String highestmindBlastTarget ="";
    public void updateHighestmindBlastAmount(int amount, String target) {
        if (amount > this.highestmindBlast) {
            this.highestmindBlast = amount;
            this.highestmindBlastTarget = target;
        }
    }
	public int getRenew() {
		return renew;
	}
	public int getEpiphany() {
		return epiphany;
	}
	public int getHolyNova() {
		return holyNova;
	}
	public int getEnlighten() {
		return enlighten;
	}
	public int getMindFLay() {
		return mindFLay;
	}
	public int getProclaimChampion() {
		return proclaimChampion;
	}
	public int getFlashHealHit() {
		return flashHealHit;
	}
	public int getFlashHealCrit() {
		return flashHealCrit;
	}
	public int getHighestflashHeal() {
		return highestflashHeal;
	}
	public String getHighestflashHealTarget() {
		return highestflashHealTarget;
	}
	public int getGreaterHealHit() {
		return greaterHealHit;
	}
	public int getGreaterHealCrit() {
		return greaterHealCrit;
	}
	public int getHighestgreaterHeal() {
		return highestgreaterHeal;
	}
	public String getHighestgreaterHealTarget() {
		return highestgreaterHealTarget;
	}
	public int getMindBlastHit() {
		return mindBlastHit;
	}
	public int getMindBlastCrit() {
		return mindBlastCrit;
	}
	public int getHighestmindBlast() {
		return highestmindBlast;
	}
	public String getHighestmindBlastTarget() {
		return highestmindBlastTarget;
	}
	public void setRenew(int renew) {
		this.renew = renew;
	}
	public void setEpiphany(int epiphany) {
		this.epiphany = epiphany;
	}
	public void setHolyNova(int holyNova) {
		this.holyNova = holyNova;
	}
	public void setEnlighten(int enlighten) {
		this.enlighten = enlighten;
	}
	public void setMindFLay(int mindFLay) {
		this.mindFLay = mindFLay;
	}
	public void setProclaimChampion(int proclaimChampion) {
		this.proclaimChampion = proclaimChampion;
	}
	public void setFlashHealHit(int flashHealHit) {
		this.flashHealHit = flashHealHit;
	}
	public void setFlashHealCrit(int flashHealCrit) {
		this.flashHealCrit = flashHealCrit;
	}
	public void setHighestflashHeal(int highestflashHeal) {
		this.highestflashHeal = highestflashHeal;
	}
	public void setHighestflashHealTarget(String highestflashHealTarget) {
		this.highestflashHealTarget = highestflashHealTarget;
	}
	public void setGreaterHealHit(int greaterHealHit) {
		this.greaterHealHit = greaterHealHit;
	}
	public void setGreaterHealCrit(int greaterHealCrit) {
		this.greaterHealCrit = greaterHealCrit;
	}
	public void setHighestgreaterHeal(int highestgreaterHeal) {
		this.highestgreaterHeal = highestgreaterHeal;
	}
	public void setHighestgreaterHealTarget(String highestgreaterHealTarget) {
		this.highestgreaterHealTarget = highestgreaterHealTarget;
	}
	public void setMindBlastHit(int mindBlastHit) {
		this.mindBlastHit = mindBlastHit;
	}
	public void setMindBlastCrit(int mindBlastCrit) {
		this.mindBlastCrit = mindBlastCrit;
	}
	public void setHighestmindBlast(int highestmindBlast) {
		this.highestmindBlast = highestmindBlast;
	}
	public void setHighestmindBlastTarget(String highestmindBlastTarget) {
		this.highestmindBlastTarget = highestmindBlastTarget;
	}
	public int getDispellMagic() {
		return dispellMagic;
	}
	public void setDispellMagic(int dispellMagic) {
		this.dispellMagic = dispellMagic;
	}
	public int getInnervate() {
		return innervate;
	}
	public void setInnervate(int innervate) {
		this.innervate = innervate;
	}
	public int getResurrection() {
		return resurrection;
	}
	public void setResurrection(int resurrection) {
		this.resurrection = resurrection;
	}
	public int getT2GreaterHealAmount() {
		return t2GreaterHealAmount;
	}
	public void setT2GreaterHealAmount(int t2GreaterHealAmount) {
		this.t2GreaterHealAmount = t2GreaterHealAmount;
	}
	public int getT2GreaterHeal() {
		return t2GreaterHeal;
	}
	public void setT2GreaterHeal(int t2GreaterHeal) {
		this.t2GreaterHeal = t2GreaterHeal;
	}
	public int getPowerWordFortitude() {
		return powerWordFortitude;
	}
	public void setPowerWordFortitude(int powerWordFortitude) {
		this.powerWordFortitude = powerWordFortitude;
	}
	public int getPrayerOfFortitude() {
		return prayerOfFortitude;
	}
	public void setPrayerOfFortitude(int prayerOfFortitude) {
		this.prayerOfFortitude = prayerOfFortitude;
	}
	public int getPowerWordShield() {
		return powerWordShield;
	}
	public void setPowerWordShield(int powerWordShield) {
		this.powerWordShield = powerWordShield;
	}
	public int getSmiteHit() {
		return smiteHit;
	}
	public int getSmiteCrit() {
		return smiteCrit;
	}
	public int getHighestsmite() {
		return highestsmite;
	}
	public String getHighestsmiteTarget() {
		return highestsmiteTarget;
	}
	public int getHolyfireHit() {
		return holyfireHit;
	}
	public int getHolyfireCrit() {
		return holyfireCrit;
	}
	public int getHighestholyfire() {
		return highestholyfire;
	}
	public String getHighestholyfireTarget() {
		return highestholyfireTarget;
	}
	public void setSmiteHit(int smiteHit) {
		this.smiteHit = smiteHit;
	}
	public void setSmiteCrit(int smiteCrit) {
		this.smiteCrit = smiteCrit;
	}
	public void setHighestsmite(int highestsmite) {
		this.highestsmite = highestsmite;
	}
	public void setHighestsmiteTarget(String highestsmiteTarget) {
		this.highestsmiteTarget = highestsmiteTarget;
	}
	public void setHolyfireHit(int holyfireHit) {
		this.holyfireHit = holyfireHit;
	}
	public void setHolyfireCrit(int holyfireCrit) {
		this.holyfireCrit = holyfireCrit;
	}
	public void setHighestholyfire(int highestholyfire) {
		this.highestholyfire = highestholyfire;
	}
	public void setHighestholyfireTarget(String highestholyfireTarget) {
		this.highestholyfireTarget = highestholyfireTarget;
	}
	
	
}
