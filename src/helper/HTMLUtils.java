package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HTMLUtils {
	
	
	public static String getTmpFileNameInclPath() {
		String fileName ="";
		try {
			fileName = File.createTempFile("temp-file", "tmp").getParent();
			fileName = fileName+"\\TwowAnalyzerResult.html";
			fileName = fileName.replace("\\", "/");
		} catch (IOException e) {
			//error
		}
		return fileName;
	}

	public static void writeFile(String str, boolean tmpFile) {
		String fileName ="";
		if(tmpFile) {
			fileName = getTmpFileNameInclPath();
		} else {
			fileName = "C:/development/spielerei/result.html";
		}
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.ISO_8859_1));
		    writer.write(str);
		    writer.close();		
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static String getAsHTMLString(String given) {
		StringBuffer strBuf = new StringBuffer();
	 	strBuf.append("<html>");
		strBuf.append("<head>");
		strBuf.append("<style>");
		strBuf.append("table, th, td {");
		strBuf.append("  border: 1px solid black;");
		strBuf.append("}");
		strBuf.append("</style>");
		strBuf.append("</head>");
		strBuf.append("<body>");		
		strBuf.append(given);
		strBuf.append("</body>");		
		strBuf.append("</html>");		
		return strBuf.toString();
	}

}
