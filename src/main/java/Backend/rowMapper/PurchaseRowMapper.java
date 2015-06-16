package Backend.rowMapper;

import Backend.DAO.PurchaseDAO;
import Backend.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Fernando on 2015-05-13.
 */
@Component
public class PurchaseRowMapper implements RowMapper{
    public final static String PURCHASE_ID = "PURCHASE_ID";
    public final static String MANUFACTURER_NAME = "MANUFACTURER_NAME";
    public final static String PRODUCT_NAME = "PRODUCT_NAME";
    public final static String LICENSE_TYPE = "LICENSE_TYPE";
    public final static String DISTRIBUTOR_NAME ="DISTRIBUTOR_NAME";
    public final static String FREE_TEXT = "FREE_TEXT";
    public final static String UPGRADED_FROM = "UPGRADED_FROM";
    public final static String CREATED_BY = "CREATED_BY";
    public final static String CREATED_DATE = "CREATED_DATE";


    /**
     * Creates a purchase object from a row of the resultset.
     * @param rs the ResultSet to map
     * @param i Number of the current row
     * @return Purchase Object for the current row.
     * @throws SQLException
     */
    public Purchase mapRow(ResultSet rs, int i) throws SQLException {
        Purchase purchase = new Purchase(rs.getLong(PURCHASE_ID),
                rs.getString(MANUFACTURER_NAME),
                rs.getString(PRODUCT_NAME),
                rs.getString(LICENSE_TYPE),
                rs.getString(DISTRIBUTOR_NAME),
                rs.getString(FREE_TEXT),
                rs.getString(CREATED_BY),
                rs.getDate(CREATED_DATE));

        return purchase;
    }
}
