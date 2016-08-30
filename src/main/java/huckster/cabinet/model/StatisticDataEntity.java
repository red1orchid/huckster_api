package huckster.cabinet.model;

/**
 * Created by PerevalovaMA on 30.08.2016.
 */
public class StatisticDataEntity {
    private String rate = "0";
    private String percent = "0.0";
    private ChartData chart = new ChartData();

    public String getRate() {
        return rate;
    }

    public String getPercent() {
        return percent;
    }

    public ChartData getChart() {
        return chart;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setChart(ChartData chart) {
        this.chart = chart;
    }
}
