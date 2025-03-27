package helper;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	
//	public static ArrayList<String> getFileAsArrayList(String fileNameInclusivePath) {
//		ArrayList<String> fileCnt1 = null;
//		ArrayList<String> fileCnt2 = null;
//		long startTime1 = System.nanoTime();
//		fileCnt1 = getFileAsArrayListAlt(fileNameInclusivePath);
//		long endTime1 = System.nanoTime();
//	    long startTime2 = System.nanoTime();
//	    fileCnt2 = getFileAsArrayListNeu(fileNameInclusivePath);
//		long endTime2 = System.nanoTime();
//	    double durationInMilliseconds1 = (endTime1 - startTime1) / 1_000.0;
//	    double durationInMilliseconds2 = (endTime2 - startTime2) / 1_000.0;
//		System.out.println( "getFileAsArrayList()  ("+fileCnt1.size()+" vs "+fileCnt2.size()+") alt vs neu: "+durationInMilliseconds1+"  vs. "+durationInMilliseconds2);
//		return fileCnt2;
//	}		
	
	
    public static ArrayList<String> getFileAsArrayList(String fileNameInclusivePath) {
        try {
            // Alle Zeilen auf einmal laden (schnell für kleine/mittlere Dateien)
            List<String> lines = Files.readAllLines(Path.of(fileNameInclusivePath));
            return new ArrayList<>(lines); // Direkte Konvertierung in ArrayList
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Falls Fehler, leere Liste zurückgeben
        }
    }
	
	
//	public static ArrayList<String> getFileAsArrayListAlt(String fileNameInclusivePath) {
//		ArrayList<String> fileCnt = new ArrayList<>();
//		BufferedReader bufferedReader = null;
//	    //Der Pfad zur Textdatei
//	    String filePath = fileNameInclusivePath;
//	    File file = new File(filePath);
//	    try {
//	        //Der BufferedReader erwartet einen FileReader. 
//	        //Diesen kann man im Konstruktoraufruf erzeugen.
//	        bufferedReader = new BufferedReader(new FileReader(file));
//	      String line;
//	      //null wird bei EOF oder Fehler zurueckgegeben
//	      while (null != (line = bufferedReader.readLine())) {
//	    	if(line!=null && line.length()>1) {
//		        fileCnt.add(line);
//	    	}
//	      }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	      if (null != bufferedReader) {
//	        try {
//	          bufferedReader.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	      }
//	    } 
//		return fileCnt;
//	}
	
//	public static String writeToFile(FileEntity fen, String directory) {
//		String result = fen.getFileName();
//		File file = new File(directory+"\\"+result); //Datei, in die geschrieben werden soll
//		try {
//		   BufferedWriter writer = new BufferedWriter(new FileWriter(file)); //Erzeugen eines effizienten Writers für Textdateien
//		   for (String str : fen.fileCnt) {
//			writer.write(str+"\n");
//		   }
//		   writer.close();
//		}
//		catch(IOException ioe) {
//		   System.err.println(ioe);
//		}
//		return result;
//	}
	
	public static File[] listFiles(String directoy, String allowedEnding) {
		File dir = new File(directoy);
		FileFilter fileFilter = new FileFilter(){
	         public boolean accept(File dir) {          
	            if (dir.isFile() && dir.getAbsolutePath().indexOf(".gci.trc")>=0) {
	               return true;
	            } else {
	               return false;
	            }
	         }
	      };  
	      File[] list = dir.listFiles(fileFilter);
	      return list;
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}	
	
	
	 public static void saveEntriesToFile(ArrayList<ArrayList<String>> entries) {
	        String baseFileName ="C:\\DT\\Spielerei\\";
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseFileName+"result.txt"))) {
	            for (int i = 0; i < entries.size(); i++) {
	                ArrayList<String> currentList = entries.get(i);

	                // Schreibe alle Zeilen des aktuellen Arrays
	                for (String line : currentList) {
	                    writer.write(line);
	                    writer.newLine(); // Zeilenumbruch
	                }

	                // Füge zwei Leerzeilen hinzu, außer nach dem letzten Array
	                if (i < entries.size() - 1) {
	                    writer.newLine();
	                    writer.newLine();
	                }
	            }
	            System.out.println("Datei gespeichert: " + baseFileName);
	        } catch (IOException e) {
	            System.err.println("Fehler beim Speichern der Datei: " + baseFileName);
	            e.printStackTrace();
	        }
	    }	
//	
//	public static void saveEntriesToFiles(ArrayList<ArrayList<String>> entries) {
//		for (int i = 0; i < entries.size(); i++) {
//            String fileName = baseFileName + "_" + (i + 1) + ".txt"; // Name der Datei z. B. "output_1.txt"
//            ArrayList<String> currentList = entries.get(i);
//
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//                for (String line : currentList) {
//                    writer.write(line);
//                    writer.newLine(); // Zeilenumbruch
//                }
//                System.out.println("Datei gespeichert: " + fileName);
//            } catch (IOException e) {
//                System.err.println("Fehler beim Speichern der Datei: " + fileName);
//                e.printStackTrace();
//            }
//        }
//    }
//	
	

}
