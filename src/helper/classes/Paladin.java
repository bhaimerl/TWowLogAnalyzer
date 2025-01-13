package helper.classes;

public class Paladin extends CasterMelee {
	
	//Holy Strike 
	//Crusader Strike
	//Judgement of Command
	//Seal of Command
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
		return highestHolyStrikeTarget;
	}


	public int getHighestCrusaderStrike() {
		return highestCrusaderStrike;
	}


	public String getHighestCrusaderStrikeTarget() {
		return highestCrusaderStrikeTarget;
	}


	public int getHighestJudgementOfCommand() {
		return highestJudgementOfCommand;
	}


	public String getHighestJudgementOfCommandTarget() {
		return highestJudgementOfCommandTarget;
	}



	public int getHighestSealOfCommand() {
		return highestSealOfCommand;
	}


	public String getHighestSealOfCommandTarget() {
		return highestSealOfCommandTarget;
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
		return highestExorcismTarget;
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
	
	

}
