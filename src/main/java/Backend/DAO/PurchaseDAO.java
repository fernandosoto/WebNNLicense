package Backend.DAO;

import Backend.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
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

    public void addPurchase(Purchase pur) {
        String sql = "INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES('"
                + pur.getManufacturerName() + "', '" + pur.getProductName() + "', '"
                + pur.getType() + "', '" + pur.getDistributorName() + "', '" + pur.getFreeText() + "');";
        db.update(sql);
    }

    public List<Purchase> searchPurchaseByName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.PRODUCT_NAME LIKE '" + name + "%';";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), rs.getLong("ORIGINAL_PURCHASE_ID"));
            }
        });

        for(Purchase purchase : p){
            System.out.println(purchase.getProductName());
        }
        return p;
    }

    public List<Purchase> searchPurchaseByDistributorName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND D.DISTRIBUTOR_NAME LIKE '" + name + "%';";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), rs.getLong("ORIGINAL_PURCHASE_ID"));
            }
        });

        for(Purchase purchase : p){
            System.out.println(purchase.getProductName());
        }
        return p;
    }

    public List<Purchase> searchPurchaseByManufacturerName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND M.MANUFACTURER_NAME LIKE '" + name + "%';";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), rs.getLong("ORIGINAL_PURCHASE_ID"));
            }
        });

        for(Purchase purchase : p){
            System.out.println(purchase.getProductName());
        }
        return p;
    }

    public List<Purchase> searchPurchaseByType(String type) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME, U.ORIGINAL_PURCHASE_ID" +
                " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D, UPGRADED_PURCHASE U" +
                " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                " AND P.LICENSE_TYPE LIKE '" + type + "%';";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"), rs.getLong("ORIGINAL_PURCHASE_ID"));
            }
        });

        for(Purchase purchase : p){
            System.out.println(purchase.getProductName());
        }
        return p;
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
