package huckster.cabinet.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import huckster.cabinet.model.Order;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Marina on 06.10.16.
 */
@XmlRootElement(name="ROWSET")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@XmlAccessorType(XmlAccessType.FIELD)
public class Orders {
    @XmlElement(name = "ROW")
    @JsonProperty("orders")
    private List<Order> list;

    public Orders() {};

    public Orders(List<Order> list) {
        this.list = list;
    }
}
