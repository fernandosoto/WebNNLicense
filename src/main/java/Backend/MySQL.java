package Backend;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public void addPurchase(Purchase pur) throws Exception{
        String sql = "INSERT INTO PURCHASE(MANUFACTURER_ID, PRODUCT_NAME, LICENSE_TYPE, DISTRIBUTOR_ID, FREE_TEXT) VALUES('"
                + pur.getManufacturerId() + "', '" + pur.getProductName() + "', '"
                + pur.getType() + "', '" + pur.getDistributorId() + "', '" + pur.getFreeText() + "');";
        db.update(sql);
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
