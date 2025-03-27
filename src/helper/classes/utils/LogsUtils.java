package helper.classes.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LogsUtils {
    private static final DateTimeFormatter LOG_DATE_FORMATTER = DateTimeFormat.forPattern("MM/dd HH:mm:ss.SSS");	
	
    private static DateTime extractTimeFromLog(String logline) {
        try {
            String[] parts = logline.split("\\s+", 3); // Trenne nach Leerzeichen (max. 3 Teile)
            String dateTimeString = parts[0] + " " + parts[1]; // "3/21 19:41:39.083"
            return LOG_DATE_FORMATTER.parseDateTime(dateTimeString);
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen der Log-Zeile: " + logline);
            return new DateTime(0); // Falls Fehler, setze auf sehr frühes Datum
        }
    }
    
    public static ArrayList<String> getDateTomeSortedLogs(ArrayList<String> logs) {
    	Collections.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String log1, String log2) {
                DateTime time1 = extractTimeFromLog(log1);
                DateTime time2 = extractTimeFromLog(log2);
                return time1.compareTo(time2); // Sortiere nach Zeit aufsteigend
            }
        });
    	return logs;
    }

}
