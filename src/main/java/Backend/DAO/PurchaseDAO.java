package Backend.DAO;

import Backend.DeletedPurchase;
import Backend.Purchase;
import Backend.rowMapper.DeletedPurchaseRowMapper;
import Backend.rowMapper.PurchaseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    public DataSource dataSource;

    @Autowired
    public JdbcTemplate db;

    @Autowired
    PurchaseRowMapper purchaseRowMapper;

    @Autowired
    DeletedPurchaseRowMapper deletedPurchaseRowMapper;


    public static final String SQL_SEARCH_BY_PRODUCT_NAME = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.PRODUCT_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL ";

    public static final String SQL_SEARCH_ALL_PRODUCT = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D,CREATOR CR " +
            "WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "AND CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "AND P.PURCHASE_ID NOT IN (SELECT DP.D_PURCHASE_ID from DELETED_PURCHASE DP)";

    public static final String SQL_SEARCH_BY_ID = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.PURCHASE_ID = ? " +
            "AND DP.D_PURCHASE_ID IS NULL ";

    public static final String SQL_SEARCH_BY_DISTRIBUTOR = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE D.DISTRIBUTOR_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_BY_MANUFACTURER = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE M.MANUFACTURER_NAME LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_BY_TYPE = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "LEFT OUTER JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "WHERE P.LICENSE_TYPE LIKE ? " +
            "AND DP.D_PURCHASE_ID IS NULL " ;

    public static final String SQL_SEARCH_ALL_DELETED = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, CR.CREATED_BY,CR.CREATED_DATE, UP.ORIGINAL_PURCHASE_ID" +
            ", DP.DELETED_PURCHASE_ID " +
            "FROM PURCHASE P " +
            "JOIN MANUFACTURER M ON M.MANUFACTURER_ID = P.MANUFACTURER_ID " +
            "JOIN DISTRIBUTOR D ON D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID " +
            "JOIN CREATOR CR ON CR.C_PURCHASE_ID = P.PURCHASE_ID " +
            "JOIN DELETED_PURCHASE DP ON DP.D_PURCHASE_ID=P.PURCHASE_ID " +
            "LEFT OUTER JOIN UPGRADED_PURCHASE UP ON UP.NEW_PURCHASE_ID = P.PURCHASE_ID";

    @Transactional
    public long addPurchase(final Purchase pur, final String userName, final long distrId, final long manuId) throws Exception {
        final KeyHolder holder = new GeneratedKeyHolder();
        if(userName == null){
            throw new Exception("You done goofed");
        }

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, manuId);
                ps.setString(2, pur.getProductName());
                ps.setString(3, pur.getType());
                ps.setLong(4, distrId);
                ps.setString(5, pur.getFreeText());
                               //+ manuId + ", '" + pur.getProductName() + "', '"
                                //+ pur.getType() + "', " + distrId + ", '" + pur.getFreeText() + "');";)
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
        return holder.getKey().longValue();// holder.getKey().longValue();
    }

    @Override
    public void deletePurchase(final Purchase pur, final String userName) {

       db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO DELETED_PURCHASE(DELETED_BY, DELETED_DATE, D_PURCHASE_ID) VALUES(?, ?, ?)");
                ps.setString(1, userName);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setLong(3, pur.getPurchaseId());
                return ps;
            }
        });
//        String sql = "INSERT INTO DELETED_PURCHASE(DELETED_BY, DELETED_DATE, PURCHASE_ID) VALUES ('" + userName + "', "
//                + new Date(System.currentTimeMillis()) + ", " + pur.getPurchaseId() + ");";
//        db.update(sql);
    }

    public List<DeletedPurchase> searchDeletedPurchases(){
        return db.query(SQL_SEARCH_ALL_DELETED, deletedPurchaseRowMapper);
    }

    public Purchase searchPurchaseById(final long id) {

        return (Purchase) db.query(SQL_SEARCH_BY_ID,purchaseRowMapper,id).get(0);

    }

    public List<Purchase> searchPurchaseByName(String product_name) throws Exception {
        if(product_name == null)
            throw new Exception("Need a product name!");

        return db.query(SQL_SEARCH_BY_PRODUCT_NAME, purchaseRowMapper,product_name+"%");
    }

    public List<Purchase> searchPurchaseByDistributorName(String name) {

        return db.query(SQL_SEARCH_BY_DISTRIBUTOR,purchaseRowMapper,name+"%");
    }

    public List<Purchase> searchPurchaseByManufacturerName(String name) {

        return db.query(SQL_SEARCH_BY_MANUFACTURER, purchaseRowMapper,name+"%");
    }

    public List<Purchase> searchPurchaseByType(final String type) {

        return db.query(SQL_SEARCH_BY_TYPE, purchaseRowMapper, type+"%");
    }

    public List<Purchase> searchAllPurchases(){
        return db.query(SQL_SEARCH_ALL_PRODUCT, purchaseRowMapper);
    }

    @Override
    public void editPurchase(final Purchase pur, final String userName, final long manufacturerId, final long distributorId) {
        final Purchase oldPur = searchPurchaseById(pur.getPurchaseId());

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE PURCHASE SET PRODUCT_NAME = ?" +
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

    public long getUpgradedFrom(long newPurchaseId) {
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
