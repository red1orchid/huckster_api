package huckster.cabinet.repository;

import huckster.cabinet.model.Company;
import huckster.cabinet.model.NewCompany;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by PerevalovaMA on 04.08.2016.
 */
public class CompanyDao extends DbDao {
    public boolean isUserExists(String username, String password) throws SQLException {
        String sql = "SELECT count(*) AS count FROM auth " +
                "      WHERE upper(user_name) = upper(?) " +
                "        AND password = sys.hash_md5(? || id || upper(user_name))";

        return selectValue(sql, null, (rs) -> rs.getInt("count") > 0, username, password).orElse(false);
    }

    public Optional<Integer> getCompanyId(String username) throws SQLException {
        return selectValue("SELECT company_id " +
                        "     FROM auth " +
                        "    WHERE upper(user_name) = upper(?)"
                , null, (rs) -> rs.getInt("company_id"), username);
    }

    public Optional<Company> getCompanyInfo(int companyId) throws SQLException {
        return selectValue("SELECT name, price_cur " +
                        "     FROM companies " +
                        "    WHERE id = ?", null, (rs) -> new Company(companyId, rs.getString("name"), rs.getString("price_cur"))
                , companyId);
    }

    public Map<String, Integer> getOrderTokens() throws SQLException {
        Map<String, Integer> tokens = new HashMap<>();
        String sql = "SELECT orders_api_token, id " +
                "       FROM companies " +
                "      WHERE orders_api_token IS NOT NULL";
        execute(sql, 100, (rs) -> tokens.put(rs.getString("orders_api_token"), rs.getInt("id")));

        return tokens;
    }

    public String registerCompany(NewCompany company) throws SQLException {
        try (Connection dbConnection = pool.getConnection();
             CallableStatement cs = dbConnection.prepareCall("{? = call INMEMORY.REGISTER(?, ?, ?, ?)}")) {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, company.getName());
            cs.setString(3, company.getPhone());
            cs.setString(4, company.getEmail());
            cs.setString(5, company.getPromocode());
            cs.execute();
            return cs.getString(1);
        }
    }

}
