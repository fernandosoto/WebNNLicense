package Backend.rowMapper;

import Backend.License;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anna on 2015-05-15.
 */
public class LicenseRowMapper implements RowMapper {
    public static final String LICENSE_ID = "LICENSE_KEY_ID";
    public static final String LICENSE_USER = "LICENSE_USER";
    public static final String LICENSE_KEY = "SERIAL_KEY";
    public static final String L_PURCHASE_ID= "PURCHASE_ID";
    public static final String EXPIRE_DATE = "EXPIRE_DATE";


    /**
     * Creates a License object from a row of the ResultSet.
     * @param rs The ResultSet to map
     * @param i The number of the current row.
     * @return License object for the current row.
     * @throws SQLException
     */
    public License mapRow(ResultSet rs, int i) throws SQLException {
        return new License(rs.getLong(LICENSE_ID),
                rs.getString(LICENSE_USER),
                rs.getString(LICENSE_KEY),
                rs.getLong(L_PURCHASE_ID),
                rs.getDate(EXPIRE_DATE));
    }
}
