package helper.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import helper.classes.utils.Constants;

public class BossBarChart extends JFrame {

    private static final long serialVersionUID = 1L;
    JFreeChart chart = null;
    public BossBarChart(String title, ArrayList<PlayerForBar> players) {
        super(title);

        // Datensatz erstellen
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (PlayerForBar playerForBar : players) {
            dataset.addValue(playerForBar.getDmg(), "", playerForBar.getPlayerName());
		}

        // Diagramm erstellen (horizontal)
        chart = ChartFactory.createBarChart( 
                "",  // Diagrammtitel
                "",                  // Kategorien-Achse
                "",                  // Werte-Achse
                dataset,
                PlotOrientation.HORIZONTAL,
                false,                      // Legende deaktivieren
                true,
                true
        );

        // Diagramm horizontal drehen
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        Color myColor = Color.decode(Constants.WARRIORCOLOR);

        // Benutzerdefinierten Renderer erstellen, der jedem Balken eine andere Farbe gibt
        BarRenderer renderer = new BarRenderer() {
            // Farben, die du verwenden möchtest:
            private final Paint[] colors = getColors(players);

            @Override
            public Paint getItemPaint(int row, int column) {
                // column repräsentiert hier den Index der Kategorie (Balken)
                return colors[column % colors.length];
            }
        };

        // Setze den benutzerdefinierten Renderer in den Plot
        plot.setRenderer(renderer);

        // Chart in ein Panel einbetten
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1200, 600));
        setContentPane(chartPanel);
        
        
//        System.out.println("BILD Base64 "+getBase64FromChart(chart));
//        
//        File file = new File("C:\\Games\\twmoa_1172\\twmoa_1172\\Logs\\results\\"+title+".png");
//        try {
//            ChartUtils.saveChartAsPNG(file, chart, 1200, 600);
//            System.out.println("PNG gespeichert: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }        
    }
    
    
    public String getBase64FromChart() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            // PNG-Daten in den ByteArrayOutputStream schreiben
            ChartUtils.writeChartAsPNG(byteArrayOutputStream, chart, 1200, 600);

            // Base64-kodieren und zurückgeben
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
//	public static final String DRUIDCOLOR = "#FF7D0A";
//	public static final String HUNTERCOLOR = "#A9D271";
//	public static final String PALADINCOLOR = "#F58CBA";
//	public static final String MAGECOLOR = "#40C7EB";
//	public static final String PRIESTCOLOR = "#FFFFFF";
//	public static final String SHAMANCOLOR = "#0070DE";
//	public static final String ROGUECOLOR = "#FFF569";
//	public static final String WARLOCKCOLOR = "#8787ED";
//	public static final String WARRIORCOLOR = "#C79C6E";
//
//    
    public Paint[] getColors(ArrayList<PlayerForBar> pfbList) {
    	Paint[] paintArray = new Paint[pfbList.size()];
    	int i=0;
    	for (PlayerForBar pfb : pfbList) {
        	if(pfb.getPlayerClass().equals(Constants.WARLOCK)) {
        		paintArray[i] = Color.decode(Constants.WARLOCKCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.WARRIOR)) {
        		paintArray[i] = Color.decode(Constants.WARRIORCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.PRIEST)) {
        		paintArray[i] = Color.decode(Constants.PRIESTCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.PALADIN)) {
        		paintArray[i] = Color.decode(Constants.PALADINCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.SHAMAN)) {
        		paintArray[i] = Color.decode(Constants.SHAMANCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.HUNTER)) {
        		paintArray[i] = Color.decode(Constants.HUNTERCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.DRUID)) {
        		paintArray[i] = Color.decode(Constants.DRUIDCOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.MAGE)) {
        		paintArray[i] = Color.decode(Constants.MAGECOLOR);
        	}
        	if(pfb.getPlayerClass().equals(Constants.ROGUE)) {
        		paintArray[i] = Color.decode(Constants.ROGUECOLOR);
        	}
        	i++;
		}
    	
    	return paintArray;
    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            HorizontalBarChartExample example = new HorizontalBarChartExample("Horizontal Bar Chart Example");
//            example.setSize(800, 600);
//            example.setLocationRelativeTo(null);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });
//    }
}
