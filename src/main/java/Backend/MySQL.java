package Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/**
 * Created by Fernando on 2015-04-21.
 */
@Service
public class MySQL implements DBCommunication {
    //Connection con = null;
    private DataSource dataSource;
    private JdbcTemplate db;

    public MySQL(String server) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //con = DriverManager.getConnection(server);
    }

    public MySQL(){}

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void addLicense(License lic) {

    }

    @Override
    public void addPurchase(Purchase pur) {
        String sql = "INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES('"
                + pur.getManufacturerId() + "', '" + pur.getProductName() + "', '"
                + pur.getType() + "', '" + pur.getDistributorId() + "', '" + pur.getFreeText() + "');";
        db.update(sql);
    }

    @Override
    public void addManufacturer(Manufacturer m) {
        String sql = "INSERT INTO MANUFACTURER(MANUFACTURER_NAME, FREE_TEXT) VALUES('" + m.getName() + "', '" + m.getFreeText() + "');";
        db.update(sql);
    }

    @Override
    public void addDistributor(Distributor d) {
        String sql = "INSERT INTO DISTRIBUTOR(DISTRIBUTOR_NAME, FREE_TEXT) VALUES('" + d.getName() + "', '" + d.getFreeText() + "');";
       db.update(sql);
    }

    @Override
    public License getLicense(int pk) { return null; }

//    @Override
//    public List<Purchase> searchPurchaseById(int pk) {
//        String sql = "SELECT * FROM PURCHASE WHERE PURCHASE_ID = " + pk + ";";
//        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
//            @Override
//            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
//                    return new Purchase(rs.getLong("PURCHASE_ID"), rs.getLong("MANUFACTURER_ID"), rs.getString("PRODUCT_NAME"),
//                            rs.getString("TYPE") , rs.getLong("DISTRIBUTOR_ID"), rs.getString("FREE_TEXT"));
//            }
//        });
//
//        for(Purchase purchase : p){
//            System.out.println(purchase.getProductName());
//        }
//        return p;
//    }

    @Override
    public List<Purchase> searchPurchaseByName(String name) {
        String sql = "SELECT P.PURCHASE_ID, P.PRODUCT_NAME, P.LICENSE_TYPE, P.FREE_TEXT, D.DISTRIBUTOR_NAME, M.MANUFACTURER_NAME" +
                        " FROM PURCHASE P, MANUFACTURER M, DISTRIBUTOR D" +
                        " WHERE M.MANUFACTURER_ID = P.MANUFACTURER_ID AND D.DISTRIBUTOR_ID = P.DISTRIBUTOR_ID" +
                        " AND P.PRODUCT_NAME LIKE '" + name + "%';";

        List<Purchase> p = db.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int i) throws SQLException {
                return new Purchase(rs.getLong("PURCHASE_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("PRODUCT_NAME"),
                        rs.getString("LICENSE_TYPE") , rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"));
            }
        });

        for(Purchase purchase : p){
            System.out.println(purchase.getProductName());
        }
        return p;
    }

}
