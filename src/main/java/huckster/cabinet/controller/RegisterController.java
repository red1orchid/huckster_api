package huckster.cabinet.controller;

import huckster.cabinet.model.NewCompany;
import huckster.cabinet.repository.CompanyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by PerevalovaMA on 07.10.2016.
 */
@RestController
public class RegisterController {
    private CompanyDao companyDao = new CompanyDao();
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value="/site/reg", method= RequestMethod.POST)
    public String registerCompany(@RequestBody NewCompany company, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        if (!ipAddress.equals("87.236.20.94")) {
            throw new UnauthorizedException();
        } else {
            try {
                return companyDao.registerCompany(company);
            } catch (SQLException e) {
                LOG.error("Error of new company registration", e);
                throw new InternalErrorException();
            }
        }
    }
}
