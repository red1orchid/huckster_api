package huckster.cabinet.repository;

import huckster.cabinet.model.Item;
import huckster.cabinet.model.Order;
import huckster.cabinet.model.OrderItems;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by PerevalovaMA on 04.08.2016.
 */
public class OrdersDao extends DbDao {
    public List<OrderItems> getOrderItems(int companyId, Date startDate, Date endDate) throws SQLException {
        List<OrderItems> orderItems = new ArrayList<>();
        String sql = "SELECT h.id as order_id," +
                "            h.rule_id," +
                "            t.offer_id," +
                "            f.vendor_code," +
                "            f.name as model," +
                "            t.base_price," +
                "            t.end_price," +
                "            t.discount," +
                "            h.phone," +
                "            h.city," +
                "            to_char(h.ctime, 'DD.MM.YYYY HH24:MI') as ctime," +
                "            h.phrase," +
                "            decode(h.processing_status, 0, 'принят', 1, 'в работе', 2, 'обработан', 3, 'выкуплен', 4, 'отложен', 5, 'отменен') as status_title," +
                "            h.processing_comment," +
                "            h.processing_status" +
                "       FROM orders_header h" +
                "      INNER JOIN orders_items t" +
                "         ON h.id = t.orders_header_id" +
                "       LEFT JOIN offers f" +
                "         ON f.company_id = h.company_id" +
                "        AND f.offer_id = t.offer_id" +
                "      WHERE h.company_id = ?" +
                "        AND trunc(h.ctime) BETWEEN ? AND ?";

        execute(sql, 500, (rs) -> {
            orderItems.add(new OrderItems(rs.getInt("order_id"), rs.getInt("rule_id"), rs.getString("offer_id"), rs.getString("vendor_code"), rs.getString("model"),
                    rs.getDouble("base_price"), rs.getDouble("end_price"), rs.getInt("discount"), rs.getString("phone"), rs.getString("city"), rs.getString("ctime"),
                    rs.getString("phrase"), rs.getInt("processing_status"), rs.getString("status_title"), rs.getString("processing_comment")));
        }, companyId, startDate, endDate);

        return orderItems;
        //  return makeTable(sql, 500, 15, companyId, startDate, endDate);
    }

    public List<Order> getOrders(int companyId, int hours) throws SQLException {
        Map<Integer, Order> orders = new LinkedHashMap<>();
        String sql = "SELECT id," +
                "            phone," +
                "            client_id," +
                "            to_char(ctime, 'DD.MM.YYYY HH24:MI') as ctime," +
                "            total_price," +
                "            utm_medium," +
                "            utm_source," +
                "            utm_campaign," +
                "            referrer," +
                "            city," +
                "            is_mobile" +
                "       FROM orders_header" +
                "      WHERE company_id = ?" +
                "        AND ctime >= sysdate - ?/24" +
                "      ORDER BY id DESC";

        execute(sql, 500, (rs) ->
                        orders.put(rs.getInt("id"), new Order(rs.getInt("id"), rs.getString("phone"), rs.getString("client_id"),
                                rs.getString("ctime"), rs.getFloat("total_price"), rs.getString("utm_medium"), rs.getString("utm_source"),
                                rs.getString("utm_campaign"), rs.getString("referrer"), rs.getString("city"), rs.getInt("is_mobile")))
                , companyId, hours);

        sql = " SELECT orders_header_id as order_id," +
                "      offer_id," +
                "      base_price," +
                "      discount," +
                "      end_price," +
                "      name" +
                " FROM orders_items " +
                "WHERE orders_header_id IN (SELECT id FROM orders_header " +
                "                            WHERE company_id = ?" +
                "                              AND ctime >= SYSDATE - ?/ 24)";

        execute(sql, 500, (rs) ->
                        orders.get(rs.getInt("order_id")).addItem(new Item(rs.getInt("order_id"), rs.getInt("offer_id"),
                                rs.getFloat("base_price"), rs.getInt("discount"), rs.getFloat("end_price"), rs.getString("name")))
                , companyId, hours);

        return new ArrayList<>(orders.values());
    }

    public void updateOrder(int orderId, int status, String comment) throws SQLException {
        String sql = " UPDATE orders_header" +
                "         SET processing_status = ?," +
                "             processing_comment = ?" +
                "       WHERE remote_id = ?";

        executeUpdate(sql, status, comment, orderId);
    }
}
