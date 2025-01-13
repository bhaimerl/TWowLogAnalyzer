package helper.classes.utils;

import java.text.SimpleDateFormat;

public class Constants { 
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss.SSS");
	public static SimpleDateFormat onlyTime = new SimpleDateFormat("HH:mm:ss.SSS");
	
	
	public static final String VERSION = "v0.2.7";
	public static final String LASTCHANGE="Parries added, Nighfall calculation, new Class Paladin, openClose entries, click opener/closer";
	
	
	public static final String DRUIDCOLOR = "#FF7D0A";
	public static final String HUNTERCOLOR = "#A9D271";
	public static final String PALADINCOLOR = "#F58CBA";
	public static final String MAGECOLOR = "#40C7EB";
	public static final String PRIESTCOLOR = "#FFFFFF";
	public static final String SHAMANCOLOR = "#0070DE";
	public static final String ROGUECOLOR = "#FFF569";
	public static final String WARLOCKCOLOR = "#8787ED";
	public static final String WARRIORCOLOR = "#C79C6E";
	
	public static final String DRUID = "DRUID";
	public static final String HUNTER = "HUNTER";
	public static final String PALADIN = "PALADIN";
	public static final String MAGE = "MAGE";
	public static final String PRIEST = "PRIEST";
	public static final String SHAMAN = "SHAMAN";
	public static final String ROGUE = "ROGUE";
	public static final String WARLOCK = "WARLOCK";
	public static final String WARRIOR = "WARRIOR";
	
	
	
	//general
	public static final String gains ="gains";
	
	//AbilitiesCasterCommon
	public static final String manFromVampirismTouch="Vampiric Touch.";
	public static final String manaFromJudgement="Judgement of Wisdom.";
	public static final String manaFromBOW="Greater Blessing of Wisdom.";
	
	
	//Warlock
	public static final String shadowBoltHit ="Shadow Bolt hits";
	public static final String shadowBoltCrit ="Shadow Bolt crits";	
	public static final String soulFirehit="Soul Fire hits ";
	public static final String soulFireCrit="Soul Fire crits ";
	public static final String searingPainhit="Searing Pain hits ";
	public static final String searingPainCrit="Searing Pain crits ";
	public static final String immolateHit="Immolate hits ";
	public static final String immolateCrit="Immolate crits ";
	public static final String conflagrateHit="Conflagrate hits ";
	public static final String conflagrateCrit="Conflagrate crits ";
	
	//Curses
	public static final String coR ="casts Curse of Recklessness"; 
	public static final String coE ="casts Curse of the Elements";
	public static final String coS ="casts Curse of Shadow";
	
	
	
	//Mage
	public static final String fireBlastCrits = "Fire Blast crits";
	public static final String fireBlastHits = "Fire Blast hits";	
	public static final String arcaneRuptureCrits = "Arcane Rupture crits";
	public static final String arcaneRuptureHits = "Arcane Rupture hits";
	public static final String fireBallCrits = "Fireball crits";
	public static final String fireBallHits = "Fireball hits";
	public static final String arcaneSurgeCrits = "Arcane Surge crits";
	public static final String arcaneSurgeHits = "Arcane Surge hits";	
	public static final String arcaneMissleCrits = "Arcane Missiles crits";
	public static final String arcaneMissleHits = "Arcane Missiles hits";
	public static final String pyroBlastCrits = "Pyroblast crits";
	public static final String pyroBlastHits = "Pyroblast hits";
	public static final String scorchCrits = "Scorch crits";
	public static final String scorchHits = "Scorch hits";	
	//DIspells
	//mage?
	public static final String removeLesserCurse = "casts Remove Lesser Curse";
	
	
	//commonMelee
	public static final String windfury="gains 1 extra attack through Windfury";
	public static final String crusader= "gains Holy Strength";
	public static final String flametongue="Flametongue Attack";
	
	//Warror
	public static final String sunder= "casts Sunder";
	public static final String deathWish="is afflicted by Death Wish";
	public static final String wrath= "Unbridled Wrath";
	public static final String flurry= "gains Flurry";
	public static final String enrage="gains Enrage";	
	public static final String executeHit="'s Execute hits ";
	public static final String executeCrit="'s Execute crits ";
	public static final String bloodthirstCrit = "'s Bloodthirst crits ";
	public static final String bloodthirstHit = "'s Bloodthirst hits ";	
	public static final String concussionBlow = "'s Concussion Blow ";	
	public static final String shieldSlam  = "'s Shield Slam ";
	
	
	//rogue
	public static final String sliceAndDice="gains Slice and Dice";
	public static final String bladeFlurry="gains Blade Flurry";
	public static final String backStabHits="Backstab hits";
	public static final String backStabcrits="Backstab crits";
	public static final String eviscerateHits="Eviscerate hits";
	public static final String eviscerateCrits="Eviscerate crits";
	public static final String sinisterStrikeHits="Sinister Strike hits";
	public static final String sinisterStrikeCrits="Sinister Strike crits";
	
	//Hunter
	public static final String autoShotHit="'s Auto Shot hits";
	public static final String autoShotCrit="'s Auto Shot crits";
	public static final String steadyShotHits="'s Steady Shot hits";
	public static final String steadyShotCrits="'s Steady Shot crits";
	public static final String multiShotHits="'s Multi-Shot hits";
	public static final String multiSHotCrits="'s Multi-Shot crits";
	public static final String arcaneShotHits="'s Arcane Shot hits";
	public static final String arcaneShotCrits="'s Arcane Shot crits"; 
	public static final String extraShotHits="'s Extra Shot hits";
	public static final String extraShotCrits="'s Extra Shot crits";
	public static final String aimedShotHits="'s Aimed Shot hits"; 
	public static final String aimedShotCrtis="'s Aimed Shot crits"; 
	public static final String serpentStringGains="gains Serpent Sting";
	public static final String serpentStringAfflicted="afflicted by Serpent Sting";
	
	
	//Paladin
	public static final String holyStrikeHit="'s Holy Strike hits";
	public static final String holyStrikeCrit="'s Holy Strike crits";
	public static final String crusaderStrikeHit="'s Crusader Strike hits";
	public static final String crusaderStrikeCrit="'s Crusader Strike crits";
	public static final String sealOfCommandHit="'s Seal of Command hits";
	public static final String sealOfCommandCrit="'s Seal of Command crits";
	public static final String judgementOfCommandHit="'s Judgement of Command hits";
	public static final String judgementOfCommandCrit="'s Judgement of Command crits";
	public static final String exorcismHit="'s Exorcism hits";
	public static final String exorcismCrit="'s Exorcism crits";
	//DIspells
	//pala
	public static final String cleanse = "casts Cleanse";
	public static final String purify = "casts Purify";
	
	//rezz
	public static final String redemption = "casts Redemption";
	
	
	
	//DRUID
	public static final String healingTouch = "'s Healing Touch";
	public static final String regrowth="'s Regrowth";
	public static final String swipe="'s Swipe";
	public static final String maul="'s Maul";
	public static final String growl="'s Growl";
	public static final String savegeFury="'s Savage Fury";
	public static final String improvedRejuvenation = "casts Improved Rejuvenation"; 
	public static final String improvedRegrowth = "casts Improved Regrowth";
	public static final String faerieFire = "casts Faerie Fire";
	public static final String starFireHits = "'s Starfire hits";
	public static final String starFireCrits ="'s Starfire crits";
	public static final String insectSwarm = "casts Insect Swarm";
	public static final String moonfireHits = "'s Moonfire hits";
	public static final String moonFireCrits = "'s Moonfire crits";
	public static final String wrathHits = "'s Wrath hits"; 
	public static final String wrathCrits = "'s Wrath crits";
	public static final String maulHits = "'s Maul hits";
	public static final String maulCrits = "'s Maul crits";
	public static final String swipeHits = "'s Swipe hits";
	public static final String swipeCrits = "'s Swipe crits";
	public static final String shredHits = "'s Shred hits";
	public static final String shredCrits = "'s Shred crits"; 
	//DIspells
	//druid
	public static final String removeCurse = "casts Remove Curse"; 
	public static final String abolishPoison = "casts Abolish Poison";	
	//rezz
	public static final String rebirth = "casts Rebirth";
	
	
	
	//PRIEST
	public static final String renew="casts Improved Renew";
	public static final String epiphany="gains Epiphany";
	public static final String holyNova="'s Holy Nova";
	public static final String enlighten="gains Enlighten";
	public static final String innervate="gains Innervate (1)";
	public static final String mindFlay="'s Mind Flay";
	public static final String proclaimChampion = "begins to perform Proclaim Champion";
	public static final String flashHealHit="'s Flash Heal heals";
	public static final String flashHealCrit="'s Flash Heal critically heals";
	public static final String greaterHealHit="'s Greater Heal heals";
	public static final String greaterHealCrit="'s Greater Heal critically heals";
	public static final String vampiricEmbrace="'s Vampiric Embrace heals";
	public static final String mindBlastHit ="'s Mind Blast hits";
	public static final String mindBlastCrit ="'s Mind Blast crits";
	//DIspells
	//priest
	public static final String dispelMagic = "casts Dispel Magic";
	//rezz
	public static final String resurrection ="casts Resurrection";
	
	//SHAMAN
	public static final String chainHeal="'s Chain Heal";
	public static final String lesserHealingWave="'s Lesser Healing Wave";
	public static final String lighningBolt="'s Lightning Bolt";
	public static final String fireNova="'s Fire Nova"; 
	public static final String frostShock="'s Frost Shock";
	public static final String lightningStrike="'s Lightning Strike";
	public static final String castWindfuryTotem="casts Windfury Totem.";
	public static final String castsEarthTotem="casts Strength of Earth Totem.";
	public static final String castsManSpringTotem="'casts Mana Spring Totem.";	
	
}
