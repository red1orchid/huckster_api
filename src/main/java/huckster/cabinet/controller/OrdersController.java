package huckster.cabinet.controller;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */

import huckster.cabinet.model.DataTable;
import huckster.cabinet.model.OrderEntity;
import huckster.cabinet.repository.OrdersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController extends AuthController {
    private OrdersDao dao = new OrdersDao();
    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping("/orders")
    public DataTable getOrders(@RequestParam(value = "dateFrom", required = false) String startDate,
                               @RequestParam(value = "dateTo", required = false) String endDate) {
        int companyId = getCompanyId("token");

        //TODO: check null parameters?

        List<OrderEntity> data = new ArrayList<>();
        try {
            data = dao.getOrders(companyId, Date.valueOf(startDate), Date.valueOf(endDate));
        } catch (SQLException e) {
            //TODO: some message?
            LOG.error("Failed to load orders for company " + companyId, e);
        }

        return new DataTable<>(data);
    }
}
