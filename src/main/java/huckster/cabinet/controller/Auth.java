package huckster.cabinet.controller;

import huckster.cabinet.repository.CompanyInfoDao;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */
abstract class Auth {
    private static Map<String, Integer> orderTokens;
    private static CompanyInfoDao dao = new CompanyInfoDao();

    static {
        try {
            orderTokens = dao.getOrderTokens();
        } catch (SQLException e) {
            throw new ServiceUnavailableException();
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
        //auth
        return 1;
    }
}
