package huckster.cabinet.controller;

import huckster.cabinet.dto.ChartData;
import huckster.cabinet.model.StatisticDataEntity;
import huckster.cabinet.model.StatisticDataType;
import huckster.cabinet.model.TwoLineChart;
import huckster.cabinet.repository.DashboardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by PerevalovaMA on 30.08.2016.
 */
@RestController
public class DashboardController {
    private DashboardDao dao = new DashboardDao();
    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping("/dashboard")
    public EnumMap<StatisticDataType, StatisticDataEntity> getStatisticData(@RequestParam(value = "period") String period) {
        int companyId = Auth.getCompanyId("token");

        //TODO: check null parameters?

        EnumMap<StatisticDataType, StatisticDataEntity> panelData = new EnumMap<>(StatisticDataType.class);
        //default
        for (StatisticDataType type : StatisticDataType.values()) {
            panelData.put(type, new StatisticDataEntity());
        }
        try {
            EnumMap<StatisticDataType, String> rates = dao.getStatisticRates(companyId, period);
            EnumMap<StatisticDataType, String> percents = dao.getStatisticPercents(companyId, period);
            EnumMap<StatisticDataType, ChartData> charts = getCharts(companyId, period);

            for (StatisticDataType type : StatisticDataType.values()) {
                StatisticDataEntity data = panelData.get(type);
                data.setRate(rates.get(type));
                data.setPercent(percents.get(type));
                data.setChart(charts.get(type));
            }
        } catch (SQLException e) {
            LOG.error("Failed to load statistic data for company " + companyId, e);
        }

        return panelData;
    }

    private EnumMap<StatisticDataType, ChartData> getCharts(int companyId, String period) throws SQLException {
        List<TwoLineChart> chartRawData = dao.getChartData(companyId, period);
        EnumMap<StatisticDataType, ChartData> charts = ChartData.makeData(chartRawData, ".current", ".last");
        charts.forEach((k, v) -> v.setProperties("time", "linear", 0));

        return charts;
    }
}
