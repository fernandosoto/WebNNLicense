package Backend;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * Created by Fernando on 2015-04-21.
 */
@Service
public class MySQL implements DBCommunication {

    public MySQL(String server) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    }

    public MySQL(){}

    private JdbcTemplate db;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.db = jdbcTemplate;
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
