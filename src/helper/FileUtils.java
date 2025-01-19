package helper;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class FileUtils {
	
	
	public static ArrayList<String> getFileAsArrayList(String fileNameInclusivePath) {
		ArrayList<String> fileCnt = new ArrayList<>();
		BufferedReader bufferedReader = null;
	    //Der Pfad zur Textdatei
	    String filePath = fileNameInclusivePath;
	    File file = new File(filePath);
	    try {
	        //Der BufferedReader erwartet einen FileReader. 
	        //Diesen kann man im Konstruktoraufruf erzeugen.
	        bufferedReader = new BufferedReader(new FileReader(file));
	      String line;
	      //null wird bei EOF oder Fehler zurueckgegeben
	      while (null != (line = bufferedReader.readLine())) {
	    	if(line!=null && line.length()>1) {
		        fileCnt.add(line);
	    	}
	      }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	      if (null != bufferedReader) {
	        try {
	          bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      }
	    } 
		return fileCnt;
	}
	
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
	
	

}
