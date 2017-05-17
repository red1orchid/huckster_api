package huckster.cabinet.controller;

import huckster.cabinet.repository.CompanyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */
class Auth {
    private static final Logger LOG = LoggerFactory.getLogger(Auth.class);
    private static Map<String, Integer> orderTokens;
    private static CompanyDao dao = new CompanyDao();

    static {
        try {
            orderTokens = dao.getOrderTokens();
        } catch (SQLException e) {
            LOG.error("Failed to get company tokens", e);
            throw new InternalErrorException();
        }
    }

    static int getCompanyIdForOrders(String token) {
        Integer companyId = orderTokens.get(token);
        if (companyId != null) {
            return companyId;
        } else {
            throw new UnauthorizedException();
        }
    }

    static int getCompanyId(String token) {
        return orderTokens.get(token);
    }
}
