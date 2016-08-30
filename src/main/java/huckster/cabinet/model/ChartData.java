package huckster.cabinet.model;

import java.util.*;

/**
 * Created by Perevalova Marina on 13.05.2016.
 */
public class ChartData {
    private String xScale;
    private String yScale;
    private int yMin;
    private ArrayList<ChartLine> main = new ArrayList<>();

    public void setProperties(String xScale, String yScale, int yMin) {
        this.xScale = xScale;
        this.yScale = yScale;
        this.yMin = yMin;
    }

    public static EnumMap<StatisticDataType, ChartData> makeData(List<TwoLineChartEntity> list, String line1Name, String line2Name) {
        EnumMap<StatisticDataType, ChartData> reportsChartData = new EnumMap<>(StatisticDataType.class);

        for (TwoLineChartEntity entity : list) {
            ChartData chartData = reportsChartData.get(entity.getType());
            if (chartData == null) {
                chartData = new ChartData();
                chartData.addLine(new ChartLine(line1Name));
                chartData.addLine(new ChartLine(line2Name));
                reportsChartData.put(entity.getType(), chartData);
            }

            chartData.getLine(0).addPoint(new ChartPoint(entity.getPeriod(), entity.getValue1()));
            chartData.getLine(1).addPoint(new ChartPoint(entity.getPeriod(), entity.getValue2()));
        }

        return reportsChartData;
    }

    public String getxScale() {
        return xScale;
    }

    public String getyScale() {
        return yScale;
    }

    public int getyMin() {
        return yMin;
    }

    public ArrayList<ChartLine> getMain() {
        return main;
    }

    private void addLine(ChartLine l)  {
        main.add(l);
    }

    private ChartLine getLine(int index) {
        return main.get(index);
    }
}
