package helper.classes;

import org.apache.commons.lang3.StringUtils;

public class Shaman extends CasterMelee { 
	
	
	public int chainHeal = 0;
	public void incrementChainHeal() {
		this.chainHeal++;
	}
	int highestChainHeal = 0;
	String highestChainTarget ="";
    public void updateHighestChainHealAmount(int amount, String target) {
        if (amount > this.highestChainHeal) {
            this.highestChainHeal = amount;
            this.highestChainTarget = target;
        }
    }
	
	public int lesserHealingWave = 0;
	public void incrementLesserHealingWave() {
		this.lesserHealingWave++;
	}
	int highestLesserHealingWave = 0;
	String highestLesserHealingWaveTarget ="";
    public void updateHighestLesserHealingWaveAmount(int amount, String target) {
        if (amount > this.highestLesserHealingWave) {
            this.highestLesserHealingWave = amount;
            this.highestLesserHealingWaveTarget = target;
        }
    }
    
	public int healingWave = 0;
	public void incrementHealingWave() {
		this.healingWave++;
	}
	int highestHealingWave = 0;
	String highestHealingWaveTarget ="";
    public void updateHighestHealingWaveAmount(int amount, String target) {
        if (amount > this.highestHealingWave) {
            this.highestHealingWave = amount;
            this.highestHealingWaveTarget = target;
        }
    }

	public int chainLightening = 0;
	public void incrementChainLightening() {
		this.chainLightening++;
	}
	int highestChainLightening = 0;
	String highestChainLighteningTarget ="";
    public void updateHighestChainLighteningAmount(int amount, String target) {
        if (amount > this.highestChainLightening) {
            this.highestChainLightening = amount;
            this.highestChainLighteningTarget = target;
        }
    }
    
    
	
	
	public int lightningBolt = 0;
	public void incrementLightningBolt() {
		this.lightningBolt++;
	}
	int highestLighningBolt= 0;
	String highestLighningBoltTarget ="";
    public void updateHighestLighningBoltAmount(int amount, String target) {
        if (amount > this.highestLighningBolt) {
            this.highestLighningBolt = amount;
            this.highestLighningBoltTarget = target;
        }
    }
	
	

	public int fireNova = 0;
	public void incrementFireNova() {
		this.fireNova++;
	}
	int highestFireNova= 0;
	String highestFireNovaTarget ="";
    public void updateHighestFireNovaAmount(int amount, String target) {
        if (amount > this.highestFireNova) {
            this.highestFireNova = amount;
            this.highestFireNovaTarget = target;
        }
    }
	

	public int frostShock = 0;
	public void incrementFrostShock() {
		this.frostShock++;
	}
	int highestFrostShock= 0;
	String highestFrostShockTarget ="";
    public void updateHighestFrostShockAmount(int amount, String target) {
        if (amount > this.highestFrostShock) {
            this.highestFrostShock = amount;
            this.highestFrostShockTarget = target;
        }
    }
	

	public int lightningStrike = 0;
	public void incrementLightningStrike() {
		this.lightningStrike++;
	}
	int highestLigningStrike= 0;
	String highestLigningStrikeTarget ="";
    public void updateHighestLigningStrikeAmount(int amount, String target) {
        if (amount > this.highestLigningStrike) {
            this.highestLigningStrike = amount;
            this.highestLigningStrikeTarget = target;
        }
    }
	
	
	public int getChainHeal() {
		return chainHeal;
	}
	public int getLesserHealingWave() {
		return lesserHealingWave;
	}
	public int getLightningBolt() {
		return lightningBolt;
	}
	public int getFireNova() {
		return fireNova;
	}
	public int getFrostShock() {
		return frostShock;
	}
	public int getLightningStrike() {
		return lightningStrike;
	}
	public void setChainHeal(int chainHeal) {
		this.chainHeal = chainHeal;
	}
	public void setLesserHealingWave(int lesserHealingWave) {
		this.lesserHealingWave = lesserHealingWave;
	}
	public void setLightningBolt(int lightningBolt) {
		this.lightningBolt = lightningBolt;
	}
	public void setFireNova(int fireNova) {
		this.fireNova = fireNova;
	}
	public void setFrostShock(int frostShock) {
		this.frostShock = frostShock;
	}
	public void setLightningStrike(int lightningStrike) {
		this.lightningStrike = lightningStrike;
	}


	public int getHighestChainHeal() {
		return highestChainHeal;
	}


	public String getHighestChainTarget() {
		return StringUtils.abbreviate(highestChainTarget,15);
	}


	public int getHighestHealingWave() {
		return highestHealingWave;
	}


	public String getHighestHealingWaveTarget() {
		return StringUtils.abbreviate(highestHealingWaveTarget,15);
	}


	public int getHighestLighningBolt() {
		return highestLighningBolt;
	}


	public String getHighestLighningBoltTarget() {
		return StringUtils.abbreviate(highestLighningBoltTarget,15);
	}


	public int getHighestFireNova() {
		return highestFireNova;
	}


	public String getHighestFireNovaTarget() {
		return StringUtils.abbreviate(highestFireNovaTarget,15);
	}


	public int getHighestFrostShock() {
		return highestFrostShock;
	}


	public String getHighestFrostShockTarget() {
		return StringUtils.abbreviate(highestFrostShockTarget,15);
	}


	public int getHighestLigningStrike() {
		return highestLigningStrike;
	}


	public String getHighestLigningStrikeTarget() {
		return StringUtils.abbreviate(highestLigningStrikeTarget,15);
	}


	public void setHighestChainHeal(int highestChainHeal) {
		this.highestChainHeal = highestChainHeal;
	}


	public void setHighestChainTarget(String highestChainTarget) {
		this.highestChainTarget = highestChainTarget;
	}


	public void setHighestHealingWave(int highestHealingWave) {
		this.highestHealingWave = highestHealingWave;
	}


	public void setHighestHealingWaveTarget(String highestHealingWaveTarget) {
		this.highestHealingWaveTarget = highestHealingWaveTarget;
	}


	public void setHighestLighningBolt(int highestLighningBolt) {
		this.highestLighningBolt = highestLighningBolt;
	}


	public void setHighestLighningBoltTarget(String highestLighningBoltTarget) {
		this.highestLighningBoltTarget = highestLighningBoltTarget;
	}


	public void setHighestFireNova(int highestFireNova) {
		this.highestFireNova = highestFireNova;
	}


	public void setHighestFireNovaTarget(String highestFireNovaTarget) {
		this.highestFireNovaTarget = highestFireNovaTarget;
	}


	public void setHighestFrostShock(int highestFrostShock) {
		this.highestFrostShock = highestFrostShock;
	}


	public void setHighestFrostShockTarget(String highestFrostShockTarget) {
		this.highestFrostShockTarget = highestFrostShockTarget;
	}


	public void setHighestLigningStrike(int highestLigningStrike) {
		this.highestLigningStrike = highestLigningStrike;
	}


	public void setHighestLigningStrikeTarget(String highestLigningStrikeTarget) {
		this.highestLigningStrikeTarget = highestLigningStrikeTarget;
	}


	public int getHighestLesserHealingWave() {
		return highestLesserHealingWave;
	}


	public String getHighestLesserHealingWaveTarget() {
		return StringUtils.abbreviate(highestLesserHealingWaveTarget,15);
	}


	public int getHealingWave() {
		return healingWave;
	}


	public void setHighestLesserHealingWave(int highestLesserHealingWave) {
		this.highestLesserHealingWave = highestLesserHealingWave;
	}


	public void setHighestLesserHealingWaveTarget(String highestLesserHealingWaveTarget) {
		this.highestLesserHealingWaveTarget = highestLesserHealingWaveTarget;
	}


	public void setHealingWave(int healingWave) {
		this.healingWave = healingWave;
	}


	public int getChainLightening() {
		return chainLightening;
	}


	public int getHighestChainLightening() {
		return highestChainLightening;
	}


	public String getHighestChainLighteningTarget() {
		return StringUtils.abbreviate(highestChainLighteningTarget,15);
	}


	public void setChainLightening(int chainLightening) {
		this.chainLightening = chainLightening;
	}


	public void setHighestChainLightening(int highestChainLightening) {
		this.highestChainLightening = highestChainLightening;
	}


	public void setHighestChainLighteningTarget(String highestChainLighteningTarget) {
		this.highestChainLighteningTarget = highestChainLighteningTarget;
	}
	
	
}
