package Backend.DAO;

import Backend.License;
import Backend.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
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
    @Transactional
    public void editLicense(License lic, String userName){
        License oldLic = searchLicenseById(lic.getLicenseId());

        StringBuilder sb = new StringBuilder();

        if (!oldLic.getUser().equals(lic.getUser())){
            sb.append("License User Changed: \"" + oldLic.getUser() + "\" to \"" + lic.getUser() + "\" |");
        }
        if (!oldLic.getSerialKey().equals(lic.getSerialKey())){
            sb.append("Serial Key Changed: \"" + oldLic.getSerialKey() + "\" to \"" + lic.getSerialKey() + "\" |");
        }
        if (oldLic.getExpireDate().compareTo(lic.getExpireDate()) != 0){
            sb.append("Expire Date Changed: \"" + oldLic.getExpireDate().toString() + "\" to \"" + lic.getExpireDate().toString() + "\" |");
        }

        String sql = "INSERT INTO MODIFY(MODIFIED_BY, MODIFIED_DATE, LICENSE_KEY_ID, FREE_TEXT) VALUES('" + userName + "', "
                + new Date(System.currentTimeMillis()) + ", " + lic.getLicenseId() + ", '" + sb.toString() + "');"
                + "UPDATE LICENSE_KEY SET LICENSE_USER = " + lic.getUser() + ", SERIAL_KEY = " + lic.getSerialKey()
                + "EXPIRE_DATE = " + lic.getExpireDate() + " WHERE LICENSE_KEY_ID = " + oldLic.getLicenseId() + ";";
        db.update(sql);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);

    }
}
