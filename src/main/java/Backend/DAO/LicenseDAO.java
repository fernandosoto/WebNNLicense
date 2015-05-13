package Backend.DAO;

import Backend.License;
import Backend.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public class LicenseDAO implements LicenseDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Override
    @Transactional
    public void addLicense(final License l) {
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO LICENSE_KEY(LICENSE_USER, SERIAL_KEY, PURCHASE_ID, EXPIRE_DATE) VALUES(?, ?, ?, ?)");
                ps.setString(1, "");
                ps.setString(2, l.getSerialKey());
                ps.setLong(3, l.getPurchaseId());
                ps.setDate(4, l.getExpireDate());
                return ps;
            }
        });
    }

    @Transactional
    @Override
    public void deleteLicense(final License l, final String userName) {
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO DELETED_LICENSE(DELETED_BY, DELETED_DATE, LICENSE_KEY_ID) VALUES (?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, l.getLicenseId());
                return ps;
            }
        });
    }

    public List<License> searchDeletedLicenses(){
        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT L.*, DL.DELETED_BY, DL.DELETED_DATE " +
                        " FROM  LICENSE_KEY L, DELETED_LICENSE DL" +
                        " WHERE DL.LICENSE_KEY_ID = L.LICENSE_KEY_ID");
                return ps;
            }
        }, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"), rs.getDate("DELETED_DATE"),
                        rs.getString("DELETED_BY"));
            }
        });
        return l;
    }

    @Override
    public List<License> searchLicenseByUser(final String name) {
        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM LICENSE_KEY L WHERE LICENSE_USER LIKE ?" +
                        " AND L.LICENSE_KEY_ID != DL.LICENSE_KEY_ID");
                ps.setString(1, name+"%");
                return ps;
            }
        }, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l;
    }

    @Override
    public List<License> searchLicenseByPurchase(final Purchase p) {
//        String sql = "SELECT * FROM LICENSE WHERE L_PURCHASE_ID = " + p.getPurchaseId()
//                + " AND L.LICENSE_KEY_ID != DL.LICENSE_KEY_ID;";

        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * " +
                        "FROM LICENSE_KEY " +
                        "WHERE PURCHASE_ID = ?" +
                        " AND LICENSE_KEY_ID NOT IN (SELECT D_LICENSE_KEY_ID from DELETED_LICENSE )");
                ps.setLong(1, p.getPurchaseId());
                return ps;
            }
        }, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_KEY_ID"), rs.getString("LICENSE_USER"), rs.getString("SERIAL_KEY"),
                        rs.getLong("PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l;
    }

    @Override
    public License searchLicenseById(final Long id) {
        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM LICENSE_KEY WHERE LICENSE_KEY_ID = ?"
                + " AND LICENSE_KEY_ID NOT IN(SELECT D_LICENSE_KEY_ID FROM DELETED_LICENSE)");
                ps.setLong(1, id);
                return ps;
            }
        }, new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int i) throws SQLException {
                return new License(rs.getLong("LICENSE_KEY_ID"), rs.getString("LICENSE_USER"), rs.getString("SERIAL_KEY"),
                        rs.getLong("PURCHASE_ID"), rs.getDate("EXPIRE_DATE"));
            }
        });

        return l.get(0);
    }

    @Override
    @Transactional
    public void editLicense(final License lic, final String userName){
        final License oldLic = searchLicenseById(lic.getLicenseId());

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
        final String s = sb.toString();
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("" +
                        "INSERT INTO MODIFY(MODIFIED_BY, MODIFY_DATE, LICENS_KEY_ID, FREE_TEXT) VALUES(?, ?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, lic.getLicenseId());
                ps.setString(4, s);
                return ps;
            }
        });
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE LICENSE_KEY SET LICENSE_USER = ?"+
                        ", SERIAL_KEY = ? ,EXPIRE_DATE = ? WHERE LICENSE_KEY_ID = ?");
                ps.setString(1, lic.getUser());
                ps.setString(2, lic.getSerialKey());
                ps.setDate(3, lic.getExpireDate());
                ps.setLong(4, oldLic.getLicenseId());
                return ps;
            }
        });
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);

    }
}
