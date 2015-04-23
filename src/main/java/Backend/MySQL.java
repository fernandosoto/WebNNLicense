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
    public void addDistributor(Distributor diri) {

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
