package Backend.DAO;

import Backend.Purchase;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
@Service
public class PurchaseDAO implements PurchaseDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Transactional
    public long addPurchase(final Purchase pur, final String userName, final long distrId, final long manuId) {
        final KeyHolder holder = new GeneratedKeyHolder();

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, manuId);
                ps.setString(2, pur.getProductName());
                ps.setString(3, pur.getType());
                ps.setLong(4, distrId);
                ps.setString(5, pur.getFreeText());
                return ps;
            }
        }, holder);

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO CREATOR(CREATED_BY, CREATED_DATE, C_PURCHASE_ID) VALUES(?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, holder.getKey().longValue());
                return ps;
            }
        });
        return holder.getKey().longValue();
    }

    @Override
    public void deletePurchase(final Purchase pur, final String userName) {
       db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO DELETED_PURCHASE(DELETED_BY, DELETED_DATE, PURCHASE_ID) VALUES(?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, pur.getPurchaseId());
                return ps;
            }
        });
    }

    public List<Purchase> searchDeletedPurchases(){
       List<Purchase> p = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, DP.DELETED_DATE, DP.DELETED_BY, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, DELETED_PURCHASE DP" +
               " WHERE DP.PURCHASE_ID != P.PURCHASE_ID;");
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"), rs.getDate("DELETED_DATE"), rs.getString("DELTED_BY"));
            }
        });
        return p;
    }

    public Purchase searchPurchaseById(final long id) {
       return db.query(new PreparedStatementCreator() {
                @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
                        "FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D,CREATOR CR " +
                        "WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
                        "AND P.PURCHASE_ID = ? " +
                        "AND CR.C_PURCHASE_ID = P.PURCHASE_ID " +
                        "AND P.PURCHASE_ID NOT IN (SELECT DP.D_PURCHASE_ID from DELETED_PURCHASE DP)");
                ps.setLong(1, id);
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        }).get(0);
    }

    public List<Purchase> searchPurchaseByName(final String name) {
        List<Purchase> p = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
                                                "FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D,CREATOR CR " +
                                                "WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
                                                "AND P.PRODUCT_NAME LIKE ? " +
                                                "AND CR.C_PURCHASE_ID = P.PURCHASE_ID " +
                                                "AND P.PURCHASE_ID NOT IN (SELECT DP.D_PURCHASE_ID from DELETED_PURCHASE DP)");
                ps.setString(1, name+"%");
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByDistributorName(final String name) {
         List<Purchase> p = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                        " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U, DELETED_PURCHASE DP" +
                        " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                        " AND D.DISTRIBUTOR_NAME LIKE " + "?" + "%" + " AND DP.PURCHASE_ID != P.PURCHASE_ID" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;");
                ps.setString(1, name);
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByManufacturerName(final String name) {
        List<Purchase> p = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                        " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, DELETED_PURCHASE DP" +
                        " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                        " AND M.MANUFACTURER_NAME LIKE " + "?" + "%" + " AND DP.PURCHASE_ID != P.PURCHASE_ID" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;");
                ps.setString(1, name);
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByType(final String type) {
        List<Purchase> p = db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, DELETED_PURCHASE DP" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.LICENSE_TYPE LIKE " + "?" + "%" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;");
                ps.setString(1, type);
                return ps;
            }
        }, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }
    @Override
    public void editPurchase(final Purchase pur, final String userName, final long manufacturerId, final long distributorId){
        final Purchase oldPur = searchPurchaseById(pur.getPurchaseId());

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE PURCHASE SET PRODUCT_NAME = ?"+
                        ", LICENSE_TYPE = ? ,MANUFACTURER_ID = ?,DISTRIBUTOR_ID = ?, FREE_TEXT = ? " +
                        " WHERE PURCHASE_ID = ?");
                ps.setString(1, pur.getProductName());
                ps.setString(2, pur.getType());
                ps.setLong(3, manufacturerId);
                ps.setLong(4, distributorId);
                ps.setString(5, pur.getFreeText());
                ps.setLong(6, oldPur.getPurchaseId());
                return ps;
            }
        });

    }

    private long getUpgradedFrom(long newPurchaseId) {
        long id;
        try {
            id = db.queryForLong("SELECT ORIGINAL_PURCHASE_ID FROM UPGRADED_PURCHASE WHERE NEW_PURCHASE_ID = " + newPurchaseId + ";");
        } catch (IncorrectResultSizeDataAccessException e) {
            return 0;
        }
        return id;

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
