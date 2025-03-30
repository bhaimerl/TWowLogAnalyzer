package helper.diagrams;

import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import helper.Raids.Boss;

public class EventLineChart {

//	public static void main(String[] args) {
//		generate();
//	}
	
    public static void generateDebuffCharts(Boss boss) {
        
     JFreeChart chart = createChart(createDataset(boss), boss);
        
      //System.out.println("BILD Base64 "+getBase64FromChart(chart));
      
      File file = new File("C:\\Games\\twmoa_1172\\twmoa_1172\\Logs\\results\\blub.png");
      try {
          ChartUtils.saveChartAsPNG(file, chart, 1200, 600);
          System.out.println("PNG gespeichert: " + file.getAbsolutePath());
      } catch (IOException e) {
          e.printStackTrace();
      }        
        
    }

    private static JFreeChart createChart(XYDataset dataset, Boss boss) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
        		boss.getName(),
                "Time",
                "",
                dataset,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainAxis(new DateAxis("Time"));

        // Y-Achse ohne Beschriftung & Ticks
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarksVisible(false);
        yAxis.setVisible(false);  // Achse selbst ausblenden
        plot.setRangeAxis(yAxis);

        // Linienrenderer mit dickeren Strichen
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRenderer(renderer);

        // Linienstärke für alle Serien auf 3.0f setzen
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(5.0f));
        }

        return chart;
    }

    private static XYDataset createDataset(Boss boss) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        
        
         TimesForLineChart tfc = new TimesForLineChart(boss.getFirstHitTime(), boss.getDiedTime());
         tfc.calculateFaerieFire(boss.getFaerieFireAppliedist(), boss.getFaerieFireFadesList());
         tfc.calcCurseOfElements(boss.getCurseOfElementsAppliedList(), boss.getCurseOfElementsFadedList());
         tfc.calcCurseOfRecklessness(boss.getCurseOfRecklessnessAppliedList(), boss.getCurseOfRecklessnessFadedList());
        
        

       
       
         int index = 0; // Initialwert für den Index

         if (tfc.getFaerieFireAppliedTimes().length > 0 && tfc.getFaerieFireFadedTimes().length > 0) {
             addEvent(dataset, "Faerie Fire", ++index, 
                      tfc.getFaerieFireAppliedTimes(),
                      tfc.getFaerieFireFadedTimes());
         }

//         if (tfc.getCurseOfRecklessnessAppliedTimes().length > 0 && tfc.getCurseOfRecklessnessFadedTimes().length > 0) {
//             addEvent(dataset, "CoR", ++index, 
//                      tfc.getCurseOfRecklessnessAppliedTimes(),
//                      tfc.getCurseOfRecklessnessFadedTimes());
//         }

//         if (tfc.getCurseOfElementsAppliedTimes().length > 0 && tfc.getCurseOfElementsFadedTimes().length > 0) {
//             addEvent(dataset, "CoE", ++index, 
//                      tfc.getCurseOfElementsAppliedTimes(),
//                      tfc.getCurseOfElementsFadedTimes());
//         }

         
        return dataset;
    }
    
	private static void addEvent(TimeSeriesCollection dataset, String name, int yValue, String[] startTimes,
			String[] endTimes) {
// Format mit Millisekunden unterstützen und ohne Datum
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		TimeSeries series = new TimeSeries(name);

		for (int i = 0; i < startTimes.length; i++) {
			try {
// Start- und Endzeit parsen
				Date start = sdf.parse(startTimes[i]);
				Date end = sdf.parse(endTimes[i]);

// Wenn der Startwert noch nicht vorhanden ist, füge ihn hinzu oder aktualisiere
				try {
					series.addOrUpdate(new Second(start), yValue); // Startpunkt (verwende addOrUpdate)
					System.out.println(name + " Ich trage ein (start): " + start);
				} catch (Exception e) {
					System.out.println("Eintrag schon vorhanden: " + start + " erhöhe um 1 Millisekunde");
					Date startPlus1Millis = new Date(start.getTime() + 1); // 1 Millisekunde hinzufügen
					try {
						series.addOrUpdate(new Second(startPlus1Millis), yValue); // Startpunkt
						System.out.println(name + " Ich trage ein (start + 1ms): " + startPlus1Millis);
					} catch (Exception e2) {
						startPlus1Millis = new Date(startPlus1Millis.getTime() + 1); // Weiter 1 Millisekunde hinzufügen
						System.out.println(name + " Ich trage ein (start + 2ms): " + startPlus1Millis);
						series.addOrUpdate(new Second(startPlus1Millis), yValue); // Startpunkt
					}
				}

// Endzeit hinzufügen
				series.addOrUpdate(new Second(end), yValue); // Endpunkt
				System.out.println(name + " Ich trage ein (end): " + end);

// Eine Millisekunde nach dem Endzeitpunkt eine Lücke setzen
				Date oneMillisAfterEnd = new Date(end.getTime() + 1);
				series.addOrUpdate(new Second(oneMillisAfterEnd), null);
				System.out.println(name + " Ich trage ein (lücke): " + oneMillisAfterEnd);

// Falls es noch ein weiteres Intervall gibt, davor ebenfalls eine Lücke setzen
				if (i < startTimes.length - 1) {
					Date nextStart = sdf.parse(startTimes[i + 1]);
					Date oneMillisBeforeNextStart = new Date(nextStart.getTime() - 1);
					series.addOrUpdate(new Second(oneMillisBeforeNextStart), null);
					System.out.println(name + " Ich trage ein (lücke2): " + oneMillisBeforeNextStart);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		dataset.addSeries(series); // Serie dem Dataset hinzufügen
	}
 

}
