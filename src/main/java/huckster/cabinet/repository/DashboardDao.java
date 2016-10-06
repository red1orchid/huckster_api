package huckster.cabinet.repository;

import huckster.cabinet.model.StatisticDataType;
import huckster.cabinet.model.TwoLineChart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import static huckster.cabinet.model.StatisticDataType.*;

/**
 * Created by PerevalovaMA on 04.08.2016.
 */
public class DashboardDao extends DbDao {
    private static final HashMap<Integer, StatisticDataType> statisticPanelTypes = new HashMap<>();

    static {
        statisticPanelTypes.put(4, CONVERSION);
        statisticPanelTypes.put(5, INCOME);
        statisticPanelTypes.put(6, ORDERS);
        statisticPanelTypes.put(7, COVERING);
    }

    public EnumMap<StatisticDataType, String> getStatisticRates(int companyId, String period) throws SQLException {
        String sql = " SELECT report_id, " +
                "             CASE" +
                "               WHEN report_id IN (4, 7) THEN to_char(value, 'fm9999999990.00')" +
                "               ELSE value" +
                "             END AS value" +
                "   FROM reports_data" +
                "  WHERE report_id IN (4, 5, 6, 7)" +
                "    AND company_id = ?" +
                "    AND decode(interval, 'ddd', 'day', 'iw', 'week', interval) = ?" +
                "    AND metric = 'curr'";

        EnumMap<StatisticDataType, String> rates = new EnumMap<>(StatisticDataType.class);

        execute(sql, null, (rs) -> {
            rates.put(statisticPanelTypes.get(rs.getInt("report_id")), rs.getString("value"));
        }, companyId, period);
        return rates;
    }

    public EnumMap<StatisticDataType, String> getStatisticPercents(int companyId, String period) throws SQLException {
        String sql = "SELECT r.report_id, (CASE WHEN sign(r.value-rr.value) = 1 THEN '+' END) || " +
                "                       trim(to_char(round((r.value-rr.value)/decode(rr.value,0,1,rr.value)*100, 2), '9999990.99')) AS value" +
                "  FROM reports_data r" +
                " INNER JOIN reports_data rr" +
                "    ON rr.report_id = r.report_id" +
                "   AND rr.company_id = r.company_id " +
                "   AND rr.interval = r.interval " +
                " WHERE r.report_id IN (4, 5, 6, 7) " +
                "   AND r.company_id = ? " +
                "   AND r.interval = decode(?, 'day', 'ddd', 'week', 'iw', 'month') " +
                "   AND r.metric = 'curr' " +
                "   AND rr.metric = 'last'";

        EnumMap<StatisticDataType, String> percents = new EnumMap<>(StatisticDataType.class);

        execute(sql, null, (rs) -> {
            percents.put(statisticPanelTypes.get(rs.getInt("report_id")), rs.getString("value"));
        }, companyId, period);
        return percents;
    }

    public List<TwoLineChart> getChartData(int companyId, String period) throws SQLException {
        HashMap<Integer, StatisticDataType> statisticPanelTypes = new HashMap<>();
        statisticPanelTypes.put(1, INCOME);
        statisticPanelTypes.put(2, ORDERS);
        statisticPanelTypes.put(3, CONVERSION);

        String sql = "SELECT * " +
                "FROM (" +
                "  SELECT report_id, metric, period, CASE report_id" +
                "                                            WHEN 3 THEN value*1000" +
                "                                            ELSE to_number(value)" +
                "                                         END AS value" +
                "  FROM reports_data" +
                "  WHERE report_id IN (1, 2, 3)" +
                "        AND company_id = ?" +
                "        AND interval = ?)" +
                "  PIVOT (" +
                "    sum(value)" +
                "    FOR metric IN ('curr' AS curr, 'last' AS last))" +
                "ORDER BY report_id";

        List<TwoLineChart> chartData = new ArrayList<>();

        execute(sql, null, (rs) -> {
            chartData.add(new TwoLineChart(statisticPanelTypes.get(rs.getInt("report_id")), rs.getString("period"), rs.getInt("curr"), rs.getInt("last")));
        }, companyId, period);
        return chartData;
    }
}
