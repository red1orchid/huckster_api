package huckster.cabinet.dto;

import java.util.ArrayList;

/**
 * Created by Perevalova Marina on 13.05.2016.
 */
public class ChartLine {
    private String className;
    private ArrayList<ChartPoint> data = new ArrayList<>();

    ChartLine(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<ChartPoint> getData() {
        return data;
    }

    void addPoint(ChartPoint p) {
        data.add(p);
    }
}
