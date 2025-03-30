package helper.diagrams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimesForLineChart {
	
	private String startTime;
	private String endTime;
	
	
	public TimesForLineChart(String start, String end) {
		this.startTime = start;
		this.endTime = end;
	}
	
	private  String[] nightFallAppliedTimes;
	private  String[] nightFallFadedTimes;
	private  String[] sunderArmorAppliedTimes;
	private  String[] exposeArmorAppliedTimes;
	private  String[] curseOfElementsAppliedTimes;
	private  String[] curseOfShdowsAppliedTimes;
	private  String[] curseOfRecklessnessAppliedTimes;
	private  String[] faerieFireAppliedTimes;
	private  String[] sunderArmorFadedTimes;
	private  String[] exposeArmorFadedTimes;
	private  String[] curseOfElementsFadedTimes;
	private  String[] curseOfShdowsFadedTimes;
	private  String[] curseOfRecklessnessFadedTimes;
	private  String[] faerieFireFadedTimes;
	
	public String[] getCurseOfElementsAppliedTimes() {
		return curseOfElementsAppliedTimes;
	}
	public String[] getCurseOfElementsFadedTimes() {
		return curseOfElementsFadedTimes;
	}
		
	
    public void calculateFaerieFire(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        faerieFireAppliedTimes = result[0];
        faerieFireFadedTimes = result[1];
    }

	
    public void calcCurseOfElements(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        curseOfElementsAppliedTimes = result[0];
        curseOfElementsFadedTimes = result[1];
    }

    public void calcCurseOfShadows(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        curseOfShdowsAppliedTimes = result[0];
        curseOfShdowsFadedTimes = result[1];
    }

    public void calcCurseOfRecklessness(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        curseOfRecklessnessAppliedTimes = result[0];
        curseOfRecklessnessFadedTimes = result[1];
    }

    public void calculateSunderArmor(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        sunderArmorAppliedTimes = result[0];
        sunderArmorFadedTimes = result[1];
    }

    public void calcExposeArmor(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        exposeArmorAppliedTimes = result[0];
        exposeArmorFadedTimes = result[1];
    }

    public void calcNightfall(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        String[][] result = calculateEffect(givenAppliedTimes, givenFadedTimes);
        nightFallAppliedTimes = result[0];
        nightFallFadedTimes = result[1];
    }

    
    public String[][] calculateEffect(ArrayList<String> givenAppliedTimes, ArrayList<String> givenFadedTimes) {
        // Setzt die Applied-Times aus der Liste
        String[] appliedTarget = givenAppliedTimes.toArray(new String[0]);

        // Falls givenFadedTimes leer ist, nur endTime zurückgeben
        String[] fadedTarget;
        if (givenFadedTimes.isEmpty()) {
            fadedTarget = new String[]{endTime};
        } else {
            // Füge endTime als letzten Eintrag hinzu
            givenFadedTimes.add(endTime);

            // Überprüfen, ob ein Eintrag in appliedTarget in fadedTarget existiert und diesen ggf. um 1 ms verschieben
            for (int i = 0; i < givenFadedTimes.size(); i++) {
                String fadedTime = givenFadedTimes.get(i);
                
                // Solange die fadedTime in appliedTimes vorkommt, verschieben
                for (int j = 0; j < givenAppliedTimes.size(); j++) {
                    String appliedTime = givenAppliedTimes.get(j);

                    // Vergleiche die Zeiten auf der Sekundebene
                    try {
                        if (appliedTime.substring(0, 8).equals(fadedTime.substring(0, 8))) {
                            // Wenn sie übereinstimmen, entferne den Eintrag aus fadedTimes
                            givenFadedTimes.remove(i);
                            i--; // Decrement to recheck the current index after removal
                            break; // Wenn der Eintrag entfernt wurde, abbrechen
                        }
                    }catch(Exception e) {System.out.println("cant check debuff fadet time");}
                }
            }

            // FadedTimes wird in Array umgewandelt
            fadedTarget = givenFadedTimes.toArray(new String[0]);
        }

        // Geben Sie die AppliedTimes und die geänderten FadedTimes als 2D-Array zurück
        return new String[][]{givenAppliedTimes.toArray(new String[0]), fadedTarget};
    }

    private String adjustTimeByMilliseconds(String time, int milliseconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        try {
            Date date = sdf.parse(time);
            long adjustedTime = date.getTime() + milliseconds;
            return sdf.format(new Date(adjustedTime));
        } catch (ParseException e) {
            e.printStackTrace();
            return time;  // Rückgabe der ursprünglichen Zeit im Fehlerfall
        }
    }



    
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String[] getNightFallAppliedTimes() {
		return nightFallAppliedTimes;
	}
	public String[] getNightFallFadedTimes() {
		return nightFallFadedTimes;
	}
	public String[] getSunderArmorAppliedTimes() {
		return sunderArmorAppliedTimes;
	}
	public String[] getExposeArmorAppliedTimes() {
		return exposeArmorAppliedTimes;
	}
	public String[] getCurseOfShdowsAppliedTimes() {
		return curseOfShdowsAppliedTimes;
	}
	public String[] getCurseOfRecklessnessAppliedTimes() {
		return curseOfRecklessnessAppliedTimes;
	}
	public String[] getFaerieFireAppliedTimes() {
		return faerieFireAppliedTimes;
	}
	public String[] getSunderArmorFadedTimes() {
		return sunderArmorFadedTimes;
	}
	public String[] getExposeArmorFadedTimes() {
		return exposeArmorFadedTimes;
	}
	public String[] getCurseOfShdowsFadedTimes() {
		return curseOfShdowsFadedTimes;
	}
	public String[] getCurseOfRecklessnessFadedTimes() {
		return curseOfRecklessnessFadedTimes;
	}
	public String[] getFaerieFireFadedTimes() {
		return faerieFireFadedTimes;
	}
    
    
    
    

    
    
    
    
	
	
	
	
}
