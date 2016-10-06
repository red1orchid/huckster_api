package huckster.cabinet.model;

/**
 * Created by PerevalovaMA on 17.06.2016.
 */
public class Company {
    private int id;
    private String name;
    private String currency;

    public Company(int id, String name, String currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}
