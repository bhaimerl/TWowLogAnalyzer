package helper.main.GUI;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import helper.FileUtils;
import helper.Filter;
import helper.FtpUpload;
import helper.HTMLUtils;
import helper.Raids.RaidBossMapping;
import helper.classes.NameClassWrapper;
import helper.classes.utils.BossUtils;
import helper.classes.utils.Constants;
import helper.classes.utils.DruidUtils;
import helper.classes.utils.General;
import helper.classes.utils.HunterUtils;
import helper.classes.utils.LootUtils;
import helper.classes.utils.MageUtils;
import helper.classes.utils.PaladinUtils;
import helper.classes.utils.Players;
import helper.classes.utils.PriestUtils;
import helper.classes.utils.RaidLogInfo;
import helper.classes.utils.RogueUtils;
import helper.classes.utils.ShamanUtils;
import helper.classes.utils.WarlockUtils;
import helper.classes.utils.WarriorUtils;
import helper.classes.utils.besonderes.BarovUtils;
import helper.classes.utils.besonderes.HealCommUtils;
import helper.classes.utils.besonderes.WarlockShadowTranceCheck;

import org.eclipse.wb.swt.SWTResourceManager;

public class MainGui {

	protected Shell shlTwowLoganalyzer;
	private Text choosedFile;
	private Text fromTime;
	private Text toTime;
	private Text bossByNameText;
	private ArrayList<String> fileAsArrayList = null;
	private String raidName = "";
	String playersHtml ="";
	String startTime = "";
	String endTime = "";
	String date = "";

	private boolean validTimes = false;

	Button btnWarrior = null;
	Button btnRogue = null;
	Button btnWarlock = null;
	Button btnDruid = null;
	Button brnPaladin = null;
	Button btnMage = null;
	Button btnShaman = null;
	Button btnPriest = null;
	Button btnHunter = null;
	private Text passwordField;
	HashMap<String, ArrayList<NameClassWrapper>> allPlayers = new HashMap<>();

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGui window = new MainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTwowLoganalyzer.open();
		shlTwowLoganalyzer.layout();
		while (!shlTwowLoganalyzer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		Display display = Display.getDefault();
		shlTwowLoganalyzer = new Shell();
		shlTwowLoganalyzer.setSize(492, 431);
		shlTwowLoganalyzer.setText("TWOW LogAnalyzer "+Constants.VERSION+" (by Klarasprudel)");
		

		btnWarrior = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnRogue = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnWarlock = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnDruid = new Button(shlTwowLoganalyzer, SWT.CHECK);
		brnPaladin = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnMage = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnShaman = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnPriest = new Button(shlTwowLoganalyzer, SWT.CHECK);
		btnHunter = new Button(shlTwowLoganalyzer, SWT.CHECK);
		
		Label lblInvalidInputData = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblInvalidInputData.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblInvalidInputData.setBounds(108, 324, 299, 15);
		lblInvalidInputData.setText("!Invalid input data, cant calculate anything!");
		lblInvalidInputData.setVisible(false);
		
		
		
		
		Label lblWoLiegtDie = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblWoLiegtDie.setBounds(10, 15, 116, 15);
		lblWoLiegtDie.setText("Path to Log(txt) File");
		
		Button btnNewButton = new Button(shlTwowLoganalyzer, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shlTwowLoganalyzer);
				fileDialog.setFilterExtensions(new String[]{"*.txt"});
		        fileDialog.setFilterNames(new String[]{ "Text File"});				
		        fileDialog.setFilterPath("c:\\"); // Windows specific
			    String choosenFileInclPath = fileDialog.open();
		        File f = new File(choosenFileInclPath);
		        if(f.isFile()) {
		        	General.flushAllGuild();
					fileAsArrayList = FileUtils.getFileAsArrayList(choosenFileInclPath);
					
					//WarlockShadowTranceCheck.getWarlockShadowTranceLogs(fileAsArrayList);
					
					allPlayers = new HashMap<>();
					allPlayers = Players.getAllPlayersSortedByClass(fileAsArrayList);					
					playersHtml = HTMLUtils.getAllPlayers(allPlayers);
					startTime = Filter.getStartTime(fileAsArrayList);
					endTime = Filter.getEndTime(fileAsArrayList);
					date = Filter.getDate(fileAsArrayList);
					if(startTime!=null && endTime!=null) {
						validTimes = true;
						fromTime.setText(startTime);
						toTime.setText(endTime);
				        choosedFile.setText(choosenFileInclPath);
						lblInvalidInputData.setVisible(false);
					} else {
				        choosedFile.setText("!!Invalid File Selected!!");
						System.out.println("Invalid File! "+choosenFileInclPath);
					}
		        }
			    while (!shlTwowLoganalyzer.isDisposed()) {
			      if (!display.readAndDispatch())
			        display.sleep();
			    }
			    shlTwowLoganalyzer.dispose();				
			}
		});
		btnNewButton.setBounds(128, 10, 75, 25);
		btnNewButton.setText("choose");
		
		choosedFile = new Text(shlTwowLoganalyzer, SWT.BORDER);
		choosedFile.setText("           -- nothing selected --");
		choosedFile.setEnabled(false);
		choosedFile.setEditable(false);
		choosedFile.setBounds(211, 12, 196, 21);
		
		Label raidTimeLabel = new Label(shlTwowLoganalyzer, SWT.NONE);
		raidTimeLabel.setText("Relavant Raid Time");
		raidTimeLabel.setBounds(10, 71, 116, 15);
		
		fromTime = new Text(shlTwowLoganalyzer, SWT.BORDER);
		fromTime.setEditable(false);
		fromTime.setText("hh:mm");
		fromTime.setBounds(128, 82, 51, 21);
		
		Label lblFrom = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblFrom.setBounds(133, 61, 35, 15);
		lblFrom.setText("From:");
		
		Label lblTo = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblTo.setText("To:");
		lblTo.setBounds(198, 61, 35, 15);
		
		toTime = new Text(shlTwowLoganalyzer, SWT.BORDER);
		toTime.setEditable(false);
		toTime.setText("hh:mm");
		toTime.setBounds(193, 82, 51, 21);
		
		Label classLabel = new Label(shlTwowLoganalyzer, SWT.NONE);
		classLabel.setText("Select class to show");
		classLabel.setBounds(10, 147, 116, 15);
		
		btnWarrior.setSelection(true);
		btnWarrior.setBounds(128, 146, 64, 16);
		btnWarrior.setText("Warrior");
		
		btnRogue.setSelection(true);
		btnRogue.setText("Rogue");
		btnRogue.setBounds(128, 168, 64, 16);
		
		btnWarlock.setSelection(true);
		btnWarlock.setBounds(128, 190, 64, 16);
		btnWarlock.setText("Warlock");
		
		btnDruid.setSelection(true);
		btnDruid.setText("Druid");
		btnDruid.setBounds(198, 146, 58, 16);
		
		brnPaladin.setSelection(true);
		brnPaladin.setText("Paladin");
		brnPaladin.setBounds(198, 168, 58, 16);
		
		btnMage.setSelection(true);
		btnMage.setText("Mage");
		btnMage.setBounds(198, 190, 58, 16);
		
		btnShaman.setSelection(true);
		btnShaman.setText("Shaman");
		btnShaman.setBounds(263, 146, 64, 16);
		
		btnPriest.setSelection(true);
		btnPriest.setText("Priest");
		btnPriest.setBounds(263, 168, 64, 16);
		
		btnHunter.setSelection(true);
		btnHunter.setText("Hunter");
		btnHunter.setBounds(263, 190, 64, 16);
		
		Label lblSpecificBossOr = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblSpecificBossOr.setText("Specific Boss or all");
		lblSpecificBossOr.setBounds(10, 240, 116, 15);
		
		Button btnBoss = new Button(shlTwowLoganalyzer, SWT.RADIO);
		Button btnAll = new Button(shlTwowLoganalyzer, SWT.RADIO);
		btnBoss.setEnabled(false);
		btnAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnAll.getSelection()) {
					btnBoss.setSelection(false);
					bossByNameText.setEnabled(false);
					bossByNameText.setEditable(false);
					bossByNameText.setText("");
				}
			
			}
			
		});
		btnAll.setSelection(true);
		btnAll.setBounds(128, 240, 35, 16);
		btnAll.setText("All");
		
		btnBoss.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnBoss.getSelection()) {
					btnAll.setSelection(false);
					bossByNameText.setEnabled(true);
					bossByNameText.setEditable(true);
				}
			}
		});
		btnBoss.setBounds(166, 240, 90, 16);
		btnBoss.setText("Boss by name");
		
		bossByNameText = new Text(shlTwowLoganalyzer, SWT.BORDER);
		bossByNameText.setEnabled(false);
		bossByNameText.setEditable(false);
		bossByNameText.setBounds(263, 237, 144, 21);
		
		Button btnGeneratePublicLink = new Button(shlTwowLoganalyzer, SWT.CHECK);

		Button btnStartAnalyze = new Button(shlTwowLoganalyzer, SWT.NONE);
		btnStartAnalyze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(allowCalculation()==false) {
					lblInvalidInputData.setVisible(true); 
					return;
				}
				General.flushAllClasses();
				lblInvalidInputData.setVisible(true);
				lblInvalidInputData.setText("...start calculating...");
				String warriors = ""; 
				String warlocks = "";
				String rogues = ""; 
				String mages = "";
				String aq40Stuff = "";
				String hunters = "";
				String paladins = "";
				String druids = "";
				String priests = "";
				String shamans = "";
				String boss = "";
				String raids = "";
				int i = 0;
				int j=1;
				String processBar = "";
				lblInvalidInputData.setText("reading log...");
		    	ArrayList<String> bossesFromLog = RaidBossMapping.getBossesFromLog(fileAsArrayList);
				lblInvalidInputData.setText("calculate boss stats...");		    	
				BossUtils.calculateBossStats(fileAsArrayList, bossesFromLog);
				boss = BossUtils.getBossStatsHTML();
				Set<String> raidFromBosses = RaidBossMapping.getRaidFromBosses(bossesFromLog);
				for (String string : raidFromBosses) {
					raids+=string+"";
				}
				raids = raids.trim();
				lblInvalidInputData.setText("parse players...");
				int tenPercentLogLines = (fileAsArrayList.size() / 100) *11;
				Date start = General.getStartDate();			
				for (String string : fileAsArrayList) {
					BarovUtils.findEntryForFrostUser(string, allPlayers);
					
					if(btnWarrior.getSelection()) {
						WarriorUtils.findEntryForWarrior(string, allPlayers);
					}
					if(btnWarlock.getSelection()) {
						WarlockUtils.findEntryForWarlock(string, allPlayers);						
					}
					if(btnRogue.getSelection()) {
						RogueUtils.findEntryForRogue(string, allPlayers);
					}
					if(btnMage.getSelection()) {
						MageUtils.findEntryForMage(string, allPlayers);
					}
					if(btnHunter.getSelection()) {
						HunterUtils.findEntryForHunter(string, allPlayers);
					}						
					if(brnPaladin.getSelection()) {
						PaladinUtils.findEntryForPaladin(string, allPlayers);
					}						
					if(btnDruid.getSelection()) {
						DruidUtils.findEntryForDruid(string, allPlayers);
					}						
					if(btnPriest.getSelection()) {
						PriestUtils.findEntryForPriest(string, allPlayers);
					}						
					if(btnShaman.getSelection()) {
						ShamanUtils.findEntryForShaman(string, allPlayers);
					}						
					
					if(i%tenPercentLogLines==0) {
						processBar="..."+j+"0%...";
						lblInvalidInputData.setText(processBar);
						j++;
					}
					i++;
				}
				warriors = WarriorUtils.getWarriors(); 
				warlocks = WarlockUtils.getWarlocksHTML(); 
				rogues = RogueUtils.getRogues(); 
				mages = MageUtils.getMagesHTML(); 
				hunters = HunterUtils.getHunterHTML(); 
				paladins = PaladinUtils.getPaladinHTML();
				druids = DruidUtils.getDruidHTML();
				priests = PriestUtils.getPriestHTML();
				shamans = ShamanUtils.getShamanHTML();
		    	//loot
		    	LootUtils.assignEpicLoot(fileAsArrayList);
				String loot = LootUtils.getLootAsHTML();
				
				if(raids.contains("AQ40")) {
					aq40Stuff+=BarovUtils.doAQChecksTogether(fileAsArrayList);
				}
				
				String classAbs = "<div style='font-size: 20; font-weight: bold;' >=> Class specific analyze</div>"
						+ "<button id=\"toggleColumnButton\">Show Mana generation</button>"
						+ "<button id=\"toggleColumnHighlightsButton\">Show Highlights</button>";
				String br = "<br><br>";
				
				HTMLUtils.writeFile(HTMLUtils.getAsHTMLString(playersHtml+boss+classAbs+warriors+rogues+druids+paladins+priests+mages+hunters+warlocks+shamans+aq40Stuff+loot, raidName, date, startTime, endTime, raids), true);
				String guildBasedFileName = RaidLogInfo.getUNiqueFileName(raids, date, startTime, Players.getMainGuild()+".html");
				boolean ftpLognSuccess = false;
				try {
					if(btnGeneratePublicLink.getSelection()) {
						lblInvalidInputData.setText("...i will open your browser...to public url");
						//String uniqueFileName = UUID.randomUUID().toString()+".html";
						ftpLognSuccess = FtpUpload.fileUpload(passwordField.getText(), Players.getMainGuild(), HTMLUtils.getTmpFileNameInclPath(),guildBasedFileName);
						if(ftpLognSuccess) {
							String resulturl = "http://klarasprudel.atwebpages.com/"+Players.getMainGuild()+"/"+guildBasedFileName;
							System.out.println("URL is: "+resulturl);
							FileUtils.openWebpage(new URI(resulturl));
							//lblInvalidInputData.setText("URL: "+resulturl);
						} else {
							lblInvalidInputData.setText("..password incorrect, will open local");
							FileUtils.openWebpage(new URI("file:///"+HTMLUtils.getTmpFileNameInclPath()));
							//klarasprudel.atwebpages.com/"+fileName						
						}
					} else {
						lblInvalidInputData.setText("...i will open your browser...to local url");
						FileUtils.openWebpage(new URI("file:///"+HTMLUtils.getTmpFileNameInclPath()));
						//klarasprudel.atwebpages.com/"+fileName						
					}
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartAnalyze.setBounds(10, 319, 75, 25);
		btnStartAnalyze.setText("Start");
		
		Label lblGenerateLink = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblGenerateLink.setText("Generate public link");
		lblGenerateLink.setBounds(10, 282, 116, 15);
		Label lblGivenPassword = new Label(shlTwowLoganalyzer, SWT.NONE);
		lblGivenPassword.setVisible(false);

		btnGeneratePublicLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnGeneratePublicLink.getSelection()) {
					lblGivenPassword.setVisible(true);
					passwordField.setVisible(true);
					passwordField.setEnabled(true);
					passwordField.setEditable(true);
				} else {
					lblGivenPassword.setVisible(false);
					passwordField.setVisible(false);
					passwordField.setEnabled(false);
					passwordField.setEditable(false);
					
				}
			}
		});
		btnGeneratePublicLink.setBounds(128, 281, 38, 16);
		btnGeneratePublicLink.setText("Yes");
		
		lblGivenPassword.setText("Password");
		lblGivenPassword.setBounds(186, 282, 58, 15);
		
		passwordField = new Text(shlTwowLoganalyzer, SWT.BORDER);
		passwordField.setVisible(false);
		passwordField.setEnabled(false);
		passwordField.setEditable(false);
		passwordField.setBounds(263, 276, 144, 21);
		

	}
	
	private boolean allowCalculation() {
		//at least one class selected?
		boolean classValid = false;
		if(btnWarrior.getSelection() || btnRogue.getSelection() || btnWarlock.getSelection() || 
				btnMage.getSelection() || btnHunter.getSelection() || brnPaladin.getSelection() || 
				btnDruid.getSelection() || btnPriest.getSelection() || btnShaman.getSelection() ) {
			classValid = true;
		}
		if(classValid && validTimes) {
			return true;
		}
		return false;
	}
}
