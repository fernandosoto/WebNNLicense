package Backend.rowMapper;

import Backend.License;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anna on 2015-05-15.
 */
public class LicenseRowMapper implements RowMapper {
    public static final String LICENSE_ID = "LICENSE_ID";
    public static final String LICENSE_USER = "LICENSE_USER";
    public static final String LICENSE_KEY = "LICENSE_KEY";
    public static final String L_PURCHASE_ID= "L_PURCHASE_ID";
    public static final String EXPIRE_DATE = "EXPIRE_DATE";
    public static final String DELETED_DATE = "DELETED_DATE";
    public static final String DELETED_BY = "DELETED_BY";

    public License mapRow(ResultSet rs, int i) throws SQLException {
        return new License(rs.getLong(LICENSE_ID),
                rs.getString(LICENSE_USER),
                rs.getString(LICENSE_KEY),
                rs.getLong(L_PURCHASE_ID),
                rs.getDate(EXPIRE_DATE),
                rs.getDate(DELETED_DATE),
                rs.getString(DELETED_BY));
    }
}
