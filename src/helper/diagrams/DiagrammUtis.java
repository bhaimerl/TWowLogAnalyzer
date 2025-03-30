package helper.diagrams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

public class DiagrammUtis {

	
    public static String getBase64FromChart(JFreeChart chart, int width, int height) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            // PNG-Daten in den ByteArrayOutputStream schreiben
            ChartUtils.writeChartAsPNG(byteArrayOutputStream, chart, width, height);

            // Base64-kodieren und zurückgeben
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
