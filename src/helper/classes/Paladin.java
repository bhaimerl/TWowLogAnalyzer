package helper.classes;

import org.apache.commons.lang3.StringUtils;

public class Paladin extends CasterMelee {
	
	//Holy Strike 
	//Crusader Strike
	//Judgement of Command
	//Seal of Command
	public int blockedCnt=0;
    public void incrementBlockedCnt() {
        this.blockedCnt++;
    }	
	public int dmgAmountBlocked=0;
    public void addDmgAmountBlocked(int dmgAmountBlockedNew) {
        this.dmgAmountBlocked +=dmgAmountBlockedNew;;
    }	
	public int dmgAmountCompleteBeforeBlock=0;
    public void addDmgAmountCompleteBeforeBlock(int dmgAmountCompleteBeforeBlockNew) {
        this.dmgAmountCompleteBeforeBlock +=dmgAmountCompleteBeforeBlockNew;;
    }	
	public int highestBlock=0;
	public String highestBlockSource = "";
    public void updateHighestBlockAmount(int amount, String target) {
        if (amount > this.highestBlock) {
            this.highestBlock = amount;
            this.highestBlockSource = target;
        }
    }	
    
	
	
	
	public int cleanse = 0;
	public void incrementCleanse() {
		this.cleanse++;
	}
	
	public int redemption = 0;
	public void incrementRedemption() {
		this.redemption++;
	}
	
	public int holyStrikeHit=0;
	public void incrementHolyStrikeHit() {
		this.holyStrikeHit++;
	}
	public int holyStrikeCrit=0;
	public void incrementHolyStrikeCrit() {
		this.holyStrikeCrit++;
	}
	
	int highestHolyStrike = 0;
	String highestHolyStrikeTarget ="";
    public void updateHighestHolyStrikeAmount(int amount, String target) {
        if (amount > this.highestHolyStrike) {
            this.highestHolyStrike = amount;
            this.highestHolyStrikeTarget = target;
        }
    }	
    
	
	public int crusaderStrikeHit=0;
	public void incrementCrusaderStrikeHit() {
		this.crusaderStrikeHit++;
	}
	public int crusaderStrikeCrit=0;
	public void incrementCrusaderStrikeCrit() {
		this.crusaderStrikeCrit++;
	}
	int highestCrusaderStrike = 0;
	String highestCrusaderStrikeTarget ="";
    public void updateHighestCrusaderStrikeAmount(int amount, String target) {
        if (amount > this.highestCrusaderStrike) {
            this.highestCrusaderStrike = amount;
            this.highestCrusaderStrikeTarget = target;
        }
    }	
    
	public int flashOfLightHit=0;
	public void incrementflashOfLightHit() {
		this.flashOfLightHit++;
	}
	public int flashOfLightCrit=0;
	public void incrementflashOfLightCrit() {
		this.flashOfLightCrit++;
	}
	int highestFlashOfLight = 0;
	String highestFlashOfLightTarget ="";
    public void updatehighestFlashOfLightAmount(int amount, String target) {
        if (amount > this.highestFlashOfLight) {
            this.highestFlashOfLight = amount;
            this.highestFlashOfLightTarget = target;
        }
    }	

	public int holyShockHit=0;
	public void incrementholyShockHit() {
		this.holyShockHit++;
	}
	public int holyShockCrit=0;
	public void incrementholyShockCrit() {
		this.holyShockCrit++;
	}
	int highestHolyShock = 0;
	String highestHolyShockTarget ="";
    public void updatehighestHolyShockAmount(int amount, String target) {
        if (amount > this.highestHolyShock) {
            this.highestHolyShock = amount;
            this.highestHolyShockTarget = target;
        }
    }	  
    
	public int holyLightHit=0;
	public void incrementholyLightHit() {
		this.holyLightHit++;
	}
	public int holyLightCrit=0;
	public void incrementholyLightCrit() {
		this.holyLightCrit++;
	}
	int highestHolyLight = 0;
	String highestHolyLightTarget ="";
    public void updatehighestHolyLightAmount(int amount, String target) {
        if (amount > this.highestHolyLight) {
            this.highestHolyLight = amount;
            this.highestHolyLightTarget = target;
        }
    }	    
	
	public int judgementOfCommandHit=0;
	public void incrementJudgementOfCommandHit() {
		this.judgementOfCommandHit++;
	}
	public int judgementOfCommandCrit=0;
	public void incrementJudgementOfCommandCrit() {
		this.judgementOfCommandCrit++;
	}
	int highestJudgementOfCommand = 0;
	String highestJudgementOfCommandTarget ="";
    public void updateHighestJudgementOfCommandAmount(int amount, String target) {
        if (amount > this.highestJudgementOfCommand) {
            this.highestJudgementOfCommand = amount;
            this.highestJudgementOfCommandTarget = target;
        }
    }	
	
	public int sealOfCommandHit=0;
	public void incrementSealOfCommandHit() {
		this.sealOfCommandHit++;
	}
	public int sealOfCommandCrit=0;
	public void incrementSealOfCommandCrit() {
		this.sealOfCommandCrit++;
	}
	int highestSealOfCommand = 0;
	String highestSealOfCommandTarget ="";
    public void updateHighestSealOfCommandAmount(int amount, String target) {
        if (amount > this.highestSealOfCommand) {
            this.highestSealOfCommand = amount;
            this.highestSealOfCommandTarget = target;
        }
    }	
	public int exorcismHit=0;
	public void incrementExorcismHit() {
		this.exorcismHit++;
	}
	public int exorcismCrit=0;
	public void incrementExorcismCrit() {
		this.exorcismCrit++;
	}
	int highestExorcism = 0;
	String highestExorcismTarget ="";
    public void updateHighestExorcismAmount(int amount, String target) {
        if (amount > this.highestExorcism) {
            this.highestExorcism = amount;
            this.highestExorcismTarget = target;
        }
    }	
    
	public int getHighestHolyStrike() {
		return highestHolyStrike;
	}


	public String getHighestHolyStrikeTarget() {
		return StringUtils.abbreviate(highestHolyStrikeTarget,15);
	}


	public int getHighestCrusaderStrike() {
		return highestCrusaderStrike;
	}


	public String getHighestCrusaderStrikeTarget() {
		return StringUtils.abbreviate(highestCrusaderStrikeTarget,15);
	}


	public int getHighestJudgementOfCommand() {
		return highestJudgementOfCommand;
	}


	public String getHighestJudgementOfCommandTarget() {
		return StringUtils.abbreviate(highestJudgementOfCommandTarget,15);
	}



	public int getHighestSealOfCommand() {
		return highestSealOfCommand;
	}


	public String getHighestSealOfCommandTarget() {
		return StringUtils.abbreviate(highestSealOfCommandTarget,15);
	}


	public void setHighestHolyStrike(int highestHolyStrike) {
		this.highestHolyStrike = highestHolyStrike;
	}


	public void setHighestHolyStrikeTarget(String highestHolyStrikeTarget) {
		this.highestHolyStrikeTarget = highestHolyStrikeTarget;
	}



	public void setHighestCrusaderStrike(int highestCrusaderStrike) {
		this.highestCrusaderStrike = highestCrusaderStrike;
	}


	public void setHighestCrusaderStrikeTarget(String highestCrusaderStrikeTarget) {
		this.highestCrusaderStrikeTarget = highestCrusaderStrikeTarget;
	}



	public void setHighestJudgementOfCommand(int highestJudgementOfCommand) {
		this.highestJudgementOfCommand = highestJudgementOfCommand;
	}


	public void setHighestJudgementOfCommandTarget(String highestJudgementOfCommandTarget) {
		this.highestJudgementOfCommandTarget = highestJudgementOfCommandTarget;
	}



	public void setHighestSealOfCommand(int highestSealOfCommand) {
		this.highestSealOfCommand = highestSealOfCommand;
	}


	public void setHighestSealOfCommandTarget(String highestSealOfCommandTarget) {
		this.highestSealOfCommandTarget = highestSealOfCommandTarget;
	}



	public int getHolyStrikeHit() {
		return holyStrikeHit;
	}



	public int getHolyStrikeCrit() {
		return holyStrikeCrit;
	}



	public int getCrusaderStrikeHit() {
		return crusaderStrikeHit;
	}



	public int getCrusaderStrikeCrit() {
		return crusaderStrikeCrit;
	}



	public int getJudgementOfCommandHit() {
		return judgementOfCommandHit;
	}



	public int getJudgementOfCommandCrit() {
		return judgementOfCommandCrit;
	}



	public int getSealOfCommandHit() {
		return sealOfCommandHit;
	}



	public int getSealOfCommandCrit() {
		return sealOfCommandCrit;
	}



	public int getExorcismHit() {
		return exorcismHit;
	}



	public int getExorcismCrit() {
		return exorcismCrit;
	}



	public int getHighestExorcism() {
		return highestExorcism;
	}



	public String getHighestExorcismTarget() {
		return StringUtils.abbreviate(highestExorcismTarget,15);
	}



	public void setHolyStrikeHit(int holyStrikeHit) {
		this.holyStrikeHit = holyStrikeHit;
	}



	public void setHolyStrikeCrit(int holyStrikeCrit) {
		this.holyStrikeCrit = holyStrikeCrit;
	}



	public void setCrusaderStrikeHit(int crusaderStrikeHit) {
		this.crusaderStrikeHit = crusaderStrikeHit;
	}



	public void setCrusaderStrikeCrit(int crusaderStrikeCrit) {
		this.crusaderStrikeCrit = crusaderStrikeCrit;
	}



	public void setJudgementOfCommandHit(int judgementOfCommandHit) {
		this.judgementOfCommandHit = judgementOfCommandHit;
	}



	public void setJudgementOfCommandCrit(int judgementOfCommandCrit) {
		this.judgementOfCommandCrit = judgementOfCommandCrit;
	}



	public void setSealOfCommandHit(int sealOfCommandHit) {
		this.sealOfCommandHit = sealOfCommandHit;
	}



	public void setSealOfCommandCrit(int sealOfCommandCrit) {
		this.sealOfCommandCrit = sealOfCommandCrit;
	}



	public void setExorcismHit(int exorcismHit) {
		this.exorcismHit = exorcismHit;
	}



	public void setExorcismCrit(int exorcismCrit) {
		this.exorcismCrit = exorcismCrit;
	}



	public void setHighestExorcism(int highestExorcism) {
		this.highestExorcism = highestExorcism;
	}



	public void setHighestExorcismTarget(String highestExorcismTarget) {
		this.highestExorcismTarget = highestExorcismTarget;
	}

	public int getCleanse() {
		return cleanse;
	}

	public void setCleanse(int cleanse) {
		this.cleanse = cleanse;
	}

	public int getRedemption() {
		return redemption;
	}

	public void setRedemption(int redemption) {
		this.redemption = redemption;
	}

	public int getFlashOfLightHit() {
		return flashOfLightHit;
	}

	public int getFlashOfLightCrit() {
		return flashOfLightCrit;
	}

	public int getHighestFlashOfLight() {
		return highestFlashOfLight;
	}

	public String getHighestFlashOfLightTarget() {
		return StringUtils.abbreviate(highestFlashOfLightTarget,15);
	}

	public int getHolyShockHit() {
		return holyShockHit;
	}

	public int getHolyShockCrit() {
		return holyShockCrit;
	}

	public int getHighestHolyShock() {
		return highestHolyShock;
	}

	public String getHighestHolyShockTarget() {
		return StringUtils.abbreviate(highestHolyShockTarget,15);
	}

	public int getHolyLightHit() {
		return holyLightHit;
	}

	public int getHolyLightCrit() {
		return holyLightCrit;
	}

	public int getHighestHolyLight() {
		return highestHolyLight;
	}

	public String getHighestHolyLightTarget() {
		return StringUtils.abbreviate(highestHolyLightTarget, 15);
	}

	public void setFlashOfLightHit(int flashOfLightHit) {
		this.flashOfLightHit = flashOfLightHit;
	}

	public void setFlashOfLightCrit(int flashOfLightCrit) {
		this.flashOfLightCrit = flashOfLightCrit;
	}

	public void setHighestFlashOfLight(int highestFlashOfLight) {
		this.highestFlashOfLight = highestFlashOfLight;
	}

	public void setHighestFlashOfLightTarget(String highestFlashOfLightTarget) {
		this.highestFlashOfLightTarget = highestFlashOfLightTarget;
	}

	public void setHolyShockHit(int holyShockHit) {
		this.holyShockHit = holyShockHit;
	}

	public void setHolyShockCrit(int holyShockCrit) {
		this.holyShockCrit = holyShockCrit;
	}

	public void setHighestHolyShock(int highestHolyShock) {
		this.highestHolyShock = highestHolyShock;
	}

	public void setHighestHolyShockTarget(String highestHolyShockTarget) {
		this.highestHolyShockTarget = highestHolyShockTarget;
	}

	public void setHolyLightHit(int holyLightHit) {
		this.holyLightHit = holyLightHit;
	}

	public void setHolyLightCrit(int holyLightCrit) {
		this.holyLightCrit = holyLightCrit;
	}

	public void setHighestHolyLight(int highestHolyLight) {
		this.highestHolyLight = highestHolyLight;
	}

	public void setHighestHolyLightTarget(String highestHolyLightTarget) {
		this.highestHolyLightTarget = highestHolyLightTarget;
	}

	public int getBlockedCnt() {
		return blockedCnt;
	}

	public int getDmgAmountBlocked() {
		return dmgAmountBlocked;
	}

	public int getDmgAmountCompleteBeforeBlock() {
		return dmgAmountCompleteBeforeBlock;
	}

	public int getHighestBlock() {
		return highestBlock;
	}

	public String getHighestBlockSource() {
		return highestBlockSource;
	}

	public void setBlockedCnt(int blockedCnt) {
		this.blockedCnt = blockedCnt;
	}

	public void setDmgAmountBlocked(int dmgAmountBlocked) {
		this.dmgAmountBlocked = dmgAmountBlocked;
	}

	public void setDmgAmountCompleteBeforeBlock(int dmgAmountCompleteBeforeBlock) {
		this.dmgAmountCompleteBeforeBlock = dmgAmountCompleteBeforeBlock;
	}

	public void setHighestBlock(int highestBlock) {
		this.highestBlock = highestBlock;
	}

	public void setHighestBlockSource(String highestBlockSource) {
		this.highestBlockSource = highestBlockSource;
	}
	
	

}
