package Backend.DAO;

import Backend.License;
import Backend.Purchase;
import Backend.rowMapper.DeletedLicenseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DeletedLicenseRowMapper deletedLicenseRowMapper;

    @Override
    @Transactional
    public void addLicense(final License l) {
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_LICENSE);
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
                PreparedStatement ps = connection.prepareStatement(SQL_DELETE_LICENSE);
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, l.getLicenseId());
                return ps;
            }
        });
    }

    public List<License> searchDeletedLicenses(){
//        List<License> l = db.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_DELETED_LICENSE);
//                return ps;
//            }
//        }, new RowMapper<License>() {
//            @Override
//            public License mapRow(ResultSet rs, int i) throws SQLException {
//                return new License(rs.getLong("LICENSE_ID"), rs.getString("LICENSE_USER"), rs.getString("LICENSE_KEY"),
//                        rs.getLong("L_PURCHASE_ID"), rs.getDate("EXPIRE_DATE"), rs.getDate("DELETED_DATE"),
//                        rs.getString("DELETED_BY"));
//            }
//        });
        List<License> l = db.query(SQL_SEARCH_DELETED_LICENSE, deletedLicenseRowMapper);
        return l;
    }

    @Override
    public List<License> searchLicenseByUser(final String name) {
        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_LICENSE_BY_USER);
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
        List<License> l = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_LICENSE_BY_PURCHASE);
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
                PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_LICENSE_BY_ID);
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
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_MODIFY_TABLE);
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
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_LICENSE_KEY);
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

    public static final String SQL_ADD_LICENSE = "INSERT INTO LICENSE_KEY(LICENSE_USER, SERIAL_KEY, PURCHASE_ID, EXPIRE_DATE) VALUES(?, ?, ?, ?)";
    public static final String SQL_DELETE_LICENSE = "INSERT INTO DELETED_LICENSE(DELETED_BY, DELETED_DATE, D_LICENSE_KEY_ID) VALUES (?, ?, ?)";
    public static final String SQL_SEARCH_DELETED_LICENSE = "SELECT L.*, DL.DELETED_BY, DL.DELETED_DATE, DL.DELETED_LICENSE_ID FROM  LICENSE_KEY L, DELETED_LICENSE DL WHERE DL.D_LICENSE_KEY_ID = L.LICENSE_KEY_ID";
    public static final String SQL_SEARCH_LICENSE_BY_USER = "SELECT * FROM LICENSE_KEY L WHERE LICENSE_USER LIKE ? AND L.LICENSE_KEY_ID != DL.LICENSE_KEY_ID";
    public static final String SQL_SEARCH_LICENSE_BY_PURCHASE = "SELECT * FROM LICENSE_KEY WHERE PURCHASE_ID = ? AND LICENSE_KEY_ID NOT IN (SELECT D_LICENSE_KEY_ID from DELETED_LICENSE )";
    public static final String SQL_SEARCH_LICENSE_BY_ID = "SELECT * FROM LICENSE_KEY WHERE LICENSE_KEY_ID = ? AND LICENSE_KEY_ID NOT IN(SELECT D_LICENSE_KEY_ID FROM DELETED_LICENSE)";
    public static final String SQL_INSERT_INTO_MODIFY_TABLE = "INSERT INTO MODIFY(MODIFIED_BY, MODIFY_DATE, LICENS_KEY_ID, FREE_TEXT) VALUES(?, ?, ?, ?)";
    public static final String SQL_UPDATE_LICENSE_KEY = "UPDATE LICENSE_KEY SET LICENSE_USER = ?"+
            ", SERIAL_KEY = ? ,EXPIRE_DATE = ? WHERE LICENSE_KEY_ID = ?";
}
