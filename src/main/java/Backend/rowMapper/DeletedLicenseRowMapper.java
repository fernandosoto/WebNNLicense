package Backend.rowMapper;

import Backend.DeletedLicense;
import Backend.License;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anna on 2015-05-18.
 */
public class DeletedLicenseRowMapper implements RowMapper {
    public static final String LICENSE_ID = "LICENSE_ID";
    public static final String LICENSE_USER = "LICENSE_USER";
    public static final String LICENSE_KEY = "LICENSE_KEY";
    public static final String L_PURCHASE_ID= "L_PURCHASE_ID";
    public static final String EXPIRE_DATE = "EXPIRE_DATE";
    public static final String DELETED_ID = "DELETED_ID";
    public static final String DELETED_BY = "DELETED_BY";
    public static final String DELETED_DATE = "DELETED_DATE";


    /**
     * Creates a DeletedLicense object from a ResultSet.
     * @param rs The ResultSet to map.
     * @param i The number of the current row.
     * @return A DeletedLicense object from the current row.
     * @throws SQLException
     */
    public DeletedLicense mapRow(ResultSet rs, int i) throws SQLException {
        return new DeletedLicense(rs.getLong(LICENSE_ID),
                rs.getString(LICENSE_USER),
                rs.getString(LICENSE_KEY),
                rs.getLong(L_PURCHASE_ID),
                rs.getDate(EXPIRE_DATE),
                rs.getLong(DELETED_ID),
                rs.getString(DELETED_BY),
                rs.getDate(DELETED_DATE));
    }
}
