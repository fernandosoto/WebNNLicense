package Backend;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 * Created by Fernando on 2015-04-21.
 */
public class MySQL implements DBCommunication {
    Connection con = null;

    public MySQL(String server) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(server);
    }

    @Override
    public void addLicense(License lic) {

    }

    @Override
    public int addPurchase(Purchase pur) throws Exception{
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES('"
                    + pur.getManufacturerId() + "', '" + pur.getProductName() + "', '"
                    + pur.getType() + "', '" + pur.getDistributorId() + "', '" + pur.getFreeText() + "');";
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }

    @Override
    public License getLicense(int pk) {
        return null;
    }

    @Override
    public Purchase getPurchase(int pk) {
        return null;
    }
}
