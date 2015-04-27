package Backend.DAO;

import Backend.Purchase;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
@Service
public class PurchaseDAO implements PurchaseDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Transactional
    public void addPurchase(Purchase pur, String userName) {


        String sql = "INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES('"
                + pur.getManufacturerName() + "', '" + pur.getProductName() + "', '"
                + pur.getType() + "', '" + pur.getDistributorName() + "', '" + pur.getFreeText() + "');"
                + "INSERT INTO CREATOR(CREATED_BY, CREATED_DATE, PURCHASE_ID) VALUES('" + userName + "', " + new Date(System.currentTimeMillis())
                + ", " + pur.getPurchaseId() + ");";
        db.update(sql);
    }

    public Purchase searchPurchaseById(long id) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, C.CREATED_BY, C.CREATED_DATE" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, CREATOR C" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.PURCHASE_ID = " + id + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;";

        return db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        }).get(0);
    }

    public List<Purchase> searchPurchaseByName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.PRODUCT_NAME LIKE '" + name + "%'" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByDistributorName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND D.DISTRIBUTOR_NAME LIKE '" + name + "%'" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByManufacturerName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND M.MANUFACTURER_NAME LIKE '" + name + "%'" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                //Long originalPurchaseID = getUpgradedFrom(rs.getLong("PURCHASE_ID"));
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }

    public List<Purchase> searchPurchaseByType(String type) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.LICENSE_TYPE LIKE '" + type + "%'" + " AND P.PURCHASE_ID = " + " C.PURCHASE_ID;";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), getUpgradedFrom(rs.getLong("PURCHASE_ID")),
                        rs.getString("CREATED_BY"), rs.getDate("CREATED_DATE"));
            }
        });
        return p;
    }



    private long getUpgradedFrom(long newPurchaseId){
        long id;
        try {
            id = db.queryForLong("SELECT ORIGINAL_PURCHASE_ID FROM UPGRADED_PURCHASE WHERE NEW_PURCHASE_ID = " + newPurchaseId + ";");
        } catch (IncorrectResultSizeDataAccessException e){
            return 0;
        }
        return id;

    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
