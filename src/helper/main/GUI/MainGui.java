package helper.main.GUI;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import helper.classes.utils.General;
import helper.classes.utils.Raid;
import helper.classes.utils.RogueUtils;
import helper.classes.utils.WarlockUtils;
import helper.classes.utils.WarriorUtils;
import helper.classes.utils.besonderes.BarovUtils;

import org.eclipse.wb.swt.SWTResourceManager;

public class MainGui {

	protected Shell shlTwowLoganalyzer;
	private Text choosedFile;
	private Text fromTime;
	private Text toTime;
	private Text bossByNameText;
	private ArrayList<String> fileAsArrayList = null;
	private String raidName = "";
	private boolean isAq40Raid = false;
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
		shlTwowLoganalyzer.setText("TWOW LogAnalyzer v0.0.5 (by Klarasprudel)");
		

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
					fileAsArrayList = FileUtils.getFileAsArrayList(choosenFileInclPath);
					playersHtml = HTMLUtils.getAllPlayers(fileAsArrayList);
					raidName = Raid.getRaid(fileAsArrayList);
					isAq40Raid = Raid.isAQ40Raid(raidName);
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
		
		btnDruid.setEnabled(false);
		btnDruid.setText("Druid");
		btnDruid.setBounds(198, 146, 58, 16);
		
		brnPaladin.setEnabled(false);
		brnPaladin.setText("Paladin");
		brnPaladin.setBounds(198, 168, 58, 16);
		
		btnMage.setEnabled(false);
		btnMage.setText("Mage");
		btnMage.setBounds(198, 190, 58, 16);
		
		btnShaman.setEnabled(false);
		btnShaman.setText("Shaman");
		btnShaman.setBounds(263, 146, 64, 16);
		
		btnPriest.setEnabled(false);
		btnPriest.setText("Priest");
		btnPriest.setBounds(263, 168, 64, 16);
		
		btnHunter.setEnabled(false);
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
				String aq40Stuff = "";
				int i = 0;
				int j=1;
				String processBar = "";
				if(isAq40Raid) {
					aq40Stuff+=BarovUtils.doAQChecksTogether(fileAsArrayList);
				}
				
				int tenPercentLogLines = (fileAsArrayList.size() / 100) *11;
				for (String string : fileAsArrayList) {
					if(btnWarrior.getSelection()) {
						WarriorUtils.findEntryForWarrior(string);
						warriors = WarriorUtils.getWarriors(); 
					}
					if(btnWarlock.getSelection()) {
						WarlockUtils.findEntryForWarlock(string);						
						warlocks = WarlockUtils.getWarlocks(); 
					}
					if(btnRogue.getSelection()) {
						RogueUtils.findEntryForRogue(string);
						rogues = RogueUtils.getRogues(); 
					}										
					if(i%tenPercentLogLines==0) {
						processBar="..."+j+"0%...";
						lblInvalidInputData.setText(processBar);
						j++;
					}
					i++;
				}
				String classAbs = "<div style='font-size: 20; font-weight: bold;' >=> Class specific analyze</div>";
				String br = "<br><br>";
				HTMLUtils.writeFile(HTMLUtils.getAsHTMLString(playersHtml+classAbs+warriors+br+warlocks+br+rogues+br+aq40Stuff, raidName, date, startTime, endTime), true);
				boolean ftpLognSuccess = false;
				try {
					if(btnGeneratePublicLink.getSelection()) {
						lblInvalidInputData.setText("...i will open your browser...to public url");
						String uniqueFileName = UUID.randomUUID().toString()+".html";
						ftpLognSuccess = FtpUpload.fileUpload(passwordField.getText(), HTMLUtils.getTmpFileNameInclPath(),uniqueFileName);
						if(ftpLognSuccess) {
							String resulturl = "https://klarasprudel.atwebpages.com/"+uniqueFileName;
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
		if(btnWarrior.getSelection() || btnRogue.getSelection() || btnWarlock.getSelection()) {
			classValid = true;
		}
		if(classValid && validTimes) {
			return true;
		}
		return false;
	}
}
