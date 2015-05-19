package Backend.rowMapper;

import Backend.DeletedPurchase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Isak on 2015-05-18.
 */
public class DeletedPurchaseRowMapper implements RowMapper{
    public final static String PURCHASE_ID = "PURCHASE_ID";
    public final static String MANUFACTURER_NAME = "MANUFACTURER_NAME";
    public final static String PRODUCT_NAME = "PRODUCT_NAME";
    public final static String LICENSE_TYPE = "LICENSE_TYPE";
    public final static String DISTRIBUTOR_NAME ="DISTRIBUTOR_NAME";
    public final static String FREE_TEXT = "FREE_TEXT";
    public final static String UPGRADED_FROM = "ORIGINAL_PURCHASE_ID";
    public final static String CREATED_BY = "CREATED_BY";
    public final static String CREATED_DATE = "CREATED_DATE";
    public final static String DELETED_ID = "DELETED_ID";
    public final static String DELETED_BY = "DELETED_BY";
    public final static String DELETED_DATE = "DELETED_DATE";

    public DeletedPurchase mapRow(ResultSet rs, int i) throws SQLException {
        DeletedPurchase deletedPurchase = new DeletedPurchase(rs.getLong(PURCHASE_ID),
                rs.getString(MANUFACTURER_NAME),
                rs.getString(PRODUCT_NAME),
                rs.getString(LICENSE_TYPE),
                rs.getString(DISTRIBUTOR_NAME),
                rs.getString(FREE_TEXT),
                rs.getLong(UPGRADED_FROM),
                rs.getString(CREATED_BY),
                rs.getDate(CREATED_DATE),
                rs.getLong(DELETED_ID),
                rs.getString(DELETED_BY),
                rs.getDate(DELETED_DATE));

        return deletedPurchase;
    }
}
