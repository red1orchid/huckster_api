package huckster.cabinet.model;

/**
 * Created by PerevalovaMA on 17.06.2016.
 */
public class TwoLineChartEntity {
    private StatisticDataType type;
    private String period;
    private int value1;
    private int value2;

    public TwoLineChartEntity(StatisticDataType type, String period, int value1, int value2) {
        this.type = type;
        this.period = period;
        this.value1 = value1;
        this.value2 = value2;
    }

    public StatisticDataType getType() {
        return type;
    }

    public String getPeriod() {
        return period;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }


}
