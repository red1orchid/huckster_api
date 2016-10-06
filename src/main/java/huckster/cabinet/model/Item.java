package huckster.cabinet.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Marina on 06.10.16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Item {
    @XmlElement(name = "ORDERS_HEADERS_ID")
    private int orderId;
    @XmlElement(name = "OFFER_ID")
    private int offerId;
    @XmlElement(name = "BASE_PRICE")
    private float basePrice;
    @XmlElement(name = "DISCOUNT")
    private int discount;
    @XmlElement(name = "END_PRICE")
    private float endPrice;
    @XmlElement(name = "NAME")
    private String name;

    public Item() {}

    public Item(int orderId, int offerId, float basePrice, int discount, float endPrice, String name) {
        this.orderId = orderId;
        this.offerId = offerId;
        this.basePrice = basePrice;
        this.discount = discount;
        this.endPrice = endPrice;
        this.name = name;
    }
}
