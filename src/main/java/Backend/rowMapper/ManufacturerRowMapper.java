package Backend.rowMapper;

import Backend.Manufacturer;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Isak on 2015-05-15.
 */
@Component
public class ManufacturerRowMapper implements RowMapper {
    public final static String MANUFACTURER_ID = "MANUFACTURER_ID";
    public final static String MANUFACTURER_NAME = "MANUFACTURER_NAME";
    public final static String FREE_TEXT = "FREE_TEXT";

    public Manufacturer mapRow(ResultSet rs, int i) throws SQLException {
        return new Manufacturer(rs.getLong(MANUFACTURER_ID),
                rs.getString(MANUFACTURER_NAME),
                rs.getString(FREE_TEXT));
    }

}
