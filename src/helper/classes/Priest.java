package helper.classes;

public class Priest extends Caster{ 
	
	public int renew = 0;
	public void incrementRenew() {
		this.renew++;
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
	
	
    
    
}
