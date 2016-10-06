package huckster.cabinet.controller;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */

import huckster.cabinet.dto.DataTable;
import huckster.cabinet.dto.Orders;
import huckster.cabinet.model.Order;
import huckster.cabinet.model.OrderItems;
import huckster.cabinet.repository.OrdersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {
    private OrdersDao dao = new OrdersDao();
    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping("/site/orders")
    public DataTable getOrderItems(@RequestParam(value = "dateFrom", required = false) String startDate,
                                   @RequestParam(value = "dateTo", required = false) String endDate) {
        int companyId = Auth.getCompanyId("token");

        //TODO: check null parameters?
        List<OrderItems> data = new ArrayList<>();
        try {
            data = dao.getOrderItems(companyId, Date.valueOf(startDate), Date.valueOf(endDate));
        } catch (SQLException e) {
            //TODO: some message?
            LOG.error("Failed to load orders for company " + companyId, e);
        }

        return new DataTable<>(data);
    }

    @RequestMapping("/orders")
    public Orders getOrders(@RequestParam(value = "token") String token,
                            @RequestParam(value = "hours") int hours) throws SQLException {
        return new Orders(dao.getOrders(Auth.getCompanyIdForOrders(token), hours));
    }
}
