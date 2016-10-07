package huckster.cabinet.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 07.10.2016.
 */
public class NewCompany {
    private String name;
    private String phone;
    private String email;
    private String promocode;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPromocode() {
        return promocode;
    }
}
