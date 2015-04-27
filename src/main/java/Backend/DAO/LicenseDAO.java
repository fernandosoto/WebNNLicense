package Backend.DAO;

import Backend.License;
import Backend.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public class LicenseDAO implements LicenseDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Override
    @Transactional
    public void addLicense(License l) {
        String sql = "INSERT INTO LICENSE_KEY(LICENSE_USER, SERIAL_KEY, PURCHASE_ID, EXPIRE_DATE) VALUES '" +
                l.getUser() +"', '" + l.getSerialKey() +"', '" + l.getPurchaseId() + "', '" + l.getExpireDate() +
                "';";
        db.update(sql);
    }

    @Override
    public List<License> searchLicenseByUser(String name) {
        String sql = "SELECT * FROM LICENSE WHERE LICENSE_USER LIKE '" + name + "%';";

        List<License> l = db.query(sql, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l;
    }

    @Override
    public List<License> searchLicenseByPurchase(Purchase p) {
        String sql = "SELECT * FROM LICENSE WHERE L_PURCHASE_ID = " + p.getPurchaseId() + ";";

        List<License> l = db.query(sql, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l;
    }

    @Override
    public License searchLicenseById(Long id) {
        String sql = "SELECT * FROM LICENSE WHERE LICENSE_KEY_ID = " + id + ";";

        List<License> l = db.query(sql, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l.get(0);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);

    }
}
