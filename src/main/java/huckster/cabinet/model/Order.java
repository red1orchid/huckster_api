package huckster.cabinet.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marina on 06.10.16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    @XmlElement(name = "ID")
    private int id;
    @XmlElement(name = "PHONE")
    private String phone;
    @XmlElement(name = "CLIENT_ID")
    private String clientId;
    @XmlElement(name = "CTIME")
    private String creationTime;
    @XmlElement(name = "TOTAL_PRICE")
    private float totalPrice;
    @XmlElement(name = "UTM_MEDIUM")
    private String utmMedium;
    @XmlElement(name = "UTM_SOURCE")
    private String utmSource;
    @XmlElement(name = "UTM_CAMPAIGN")
    private String utmCampaign;
    @XmlElement(name = "REFERRER")
    private String referrer;
    @XmlElement(name = "CITY")
    private String city;
    @XmlElement(name = "IS_MOBILE")
    private int isMobile;
    @XmlElementWrapper(name = "ITEMS")
    @XmlElement(name = "ORDER_ITEM")
    private List<Item> items = new ArrayList<>();

    public Order() {};

    public Order(int id, String phone, String clientId, String creationTime, float totalPrice, String utmMedium, String utmSource, String utmCampaign, String referrer, String city, int isMobile) {
        this.id = id;
        this.phone = phone;
        this.clientId = clientId;
        this.creationTime = creationTime;
        this.totalPrice = totalPrice;
        this.utmMedium = utmMedium;
        this.utmSource = utmSource;
        this.utmCampaign = utmCampaign;
        this.referrer = referrer;
        this.city = city;
        this.isMobile = isMobile;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
