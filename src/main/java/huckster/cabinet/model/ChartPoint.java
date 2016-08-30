package huckster.cabinet.model;

/**
 * Created by Perevalova Marina on 13.05.2016.
 */
public class ChartPoint {
    private String x;
    private int y;

    ChartPoint(String x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
