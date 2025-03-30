package helper.diagrams;

import java.awt.BasicStroke;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import helper.Raids.Boss;

public class DebuffTimelineChart {
//    public static void main(String[] args) throws ParseException {
//    	ChartPanel panel = new ChartPanel(createChart());
//    	panel.setPreferredSize(new java.awt.Dimension(1200, 300)); 
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Debuff Timeline");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.add(panel);
//            frame.pack();
//            frame.setVisible(true); 
//        });
//    }

    public static JFreeChart createChart(Boss boss) {
    	
    	
        TimesForLineChart tfc = new TimesForLineChart(boss.getFirstHitTime(), boss.getDiedTime());
        tfc.calculateFaerieFire(boss.getFaerieFireAppliedist(), boss.getFaerieFireFadesList());
        tfc.calculateSunderArmor(boss.getSunderArmorAppliedist(), boss.getSunderArmorFadesList());
        tfc.calcCurseOfElements(boss.getCurseOfElementsAppliedList(), boss.getCurseOfElementsFadedList());
        tfc.calcCurseOfRecklessness(boss.getCurseOfRecklessnessAppliedList(), boss.getCurseOfRecklessnessFadedList());
        tfc.calcCurseOfShadows(boss.getCurseOfShadowsAppliedList(), boss.getCurseOfShadowsFadedList());
        tfc.calcNightfall(boss.getNightFallAppliedList(), boss.getNightFallFadedList());
        
//        System.out.println("Applied: "+boss.getFaerieFireAppliedist());
//        System.out.println("Applied: "+boss.getFaerieFireFadesList());
        
    	
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(createSeries("Faerie Fire", tfc.getFaerieFireAppliedTimes(), tfc.getFaerieFireFadedTimes(), 6));
        dataset.addSeries(createSeries("Sunder Armor", tfc.getSunderArmorAppliedTimes(), tfc.getSunderArmorFadedTimes(), 5));
        dataset.addSeries(createSeries("Curse of Elements", tfc.getCurseOfElementsAppliedTimes(), tfc.getCurseOfElementsFadedTimes(), 4));
        dataset.addSeries(createSeries("Curse of Reck", tfc.getCurseOfRecklessnessAppliedTimes(), tfc.getCurseOfRecklessnessFadedTimes(), 3));
        dataset.addSeries(createSeries("Curse of Shadows", tfc.getCurseOfShdowsAppliedTimes(), tfc.getCurseOfShdowsFadedTimes(), 2));
        dataset.addSeries(createSeries("Nightfall", tfc.getNightFallAppliedTimes(), tfc.getNightFallFadedTimes(), 1));
        dataset.addSeries(createSeries("Boss lifetime", new String[]{boss.getFirstHitTimeOnly()}, new String[]{boss.getDiedTime()}, 0));

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", "", "", dataset, false, true, false); // No legend

        XYPlot plot = (XYPlot) chart.getPlot(); 
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Set custom Y-axis labels
        String[] labels = {"Boss Lifetime", "Nightfall", "Curse of Shadows", "Curse of Reck", "Curse of Elements", "Sunder Armor", "Faerie Fire"};
        SymbolAxis yAxis = new SymbolAxis("", labels);
        yAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 9));
        yAxis.setGridBandsVisible(false); // Optional: Hide background bands
        plot.setRangeAxis(yAxis); // Set custom axis

        // Increase line thickness
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(3.0f)); // Thicker lines
        }

        plot.setRenderer(renderer);
        DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
        domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss.SSS"));
        ValueAxis rangeAxis = plot.getRangeAxis();
        
        rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 9));
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 10)); 

//        String bossName = boss.getName().replaceAll(" ", "");
//        File file = new File("C:\\Games\\twmoa_1172\\twmoa_1172\\Logs\\results\\"+bossName+".png");
//        try {
//            ChartUtils.saveChartAsPNG(file, chart, 1200, 600);
//            System.out.println("PNG gespeichert: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }        
//        
//        
        return chart;
    }


    private static TimeSeries createSeries(String name, String[] startTimes, String[] endTimes, double yValue) {
        TimeSeries series = new TimeSeries(name);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

        try {
            for (int i = 0; i < startTimes.length; i++) {
                Date start = sdf.parse(startTimes[i]);

                // Falls bereits ein Eintrag für diese Zeit existiert, erhöhe um 1 Millisekunde
                while (series.getValue(new Millisecond(start)) != null) {
                    start = new Date(start.getTime() + 1);  // Verschiebe um 1 Millisekunde
                }

                series.add(new Millisecond(start), yValue);
            }

            for (int i = 0; i < endTimes.length; i++) {
                Date end = sdf.parse(endTimes[i]);

                // Falls bereits ein Eintrag für diese Zeit existiert, erhöhe um 1 Millisekunde
                while (series.getValue(new Millisecond(end)) != null) {
                    end = new Date(end.getTime() + 1);  // Verschiebe um 1 Millisekunde
                }

                series.add(new Millisecond(end), yValue);

                // Eine Millisekunde nach dem Endzeitpunkt eine Lücke setzen
                Date gap = new Date(end.getTime() + 1);
                series.add(new Millisecond(gap), null);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return series;
    }
    

    

}
