package huckster.cabinet.controller;

import huckster.cabinet.model.DataTable;
import huckster.cabinet.model.DiscountEntity;
import huckster.cabinet.model.RuleEntity;
import huckster.cabinet.repository.WidgetSettingsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PerevalovaMA on 30.08.2016.
 */
@RestController
public class WidgetSettingsController extends AuthController  {
    private WidgetSettingsDao dao = new WidgetSettingsDao();
    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping("/widget_settings/segments/rules")
    public DataTable getRules() {
        int companyId = getCompanyId("token");

        List<RuleEntity> data = new ArrayList<>();
        try {
            data = dao.getRules(companyId);
        } catch (SQLException e) {
            //TODO: return error
            LOG.error("Failed to load rules for company " + companyId, e);
        }

        return new DataTable<>(data);
    }

    @RequestMapping("/widget_settings/segments/lists")
    public Map<String, Object> getLists() {
        int companyId = getCompanyId("token");

        Map<String, Object> map = new HashMap<>();
        List<String> channels = new ArrayList<>();
        List<String> sources = new ArrayList<>();
        Map<Integer, String> devices = new HashMap<>();

        try {
            channels = dao.getChannels(companyId);
            sources = dao.getSources(companyId);
            devices = dao.getDevices();
        } catch (SQLException e) {
            //TODO: return error
            LOG.error("Failed to load lists for company " + companyId, e);
        }

        map.put("sources", sources);
        map.put("channels", channels);
        map.put("channels", devices);

        return map;
    }

    @RequestMapping("/widget_settings/discounts/segments")
    public Map<Integer, String> getSegments() {
        int companyId = getCompanyId("token");

        try {
            return dao.getSegments(companyId);
        } catch (SQLException e) {
            //TODO: return error
            LOG.error("Failed to load segments for company " + companyId, e);
            return null;
        }
    }

    @RequestMapping("/widget_settings/vendor_discounts")
    public List<DiscountEntity> getVendorsDiscounts(@RequestParam(value = "segmentId") int ruleId) {
        int companyId = getCompanyId("token");

        try {
            return dao.getVendorsDiscounts(companyId, ruleId);
        } catch (SQLException e) {
            //TODO: return error + wrong segment id?
            LOG.error("Failed to load vendor_discounts for company " + companyId, e);
            return null;
        }
    }
}
