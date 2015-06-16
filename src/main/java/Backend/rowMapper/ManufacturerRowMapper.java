package Backend.rowMapper;

import Backend.Manufacturer;
import Backend.DAO.ManufacturerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Created by Isak on 2015-05-15.
 */
@Component
public class ManufacturerRowMapper implements RowMapper {
    public final static String MANUFACTURER_ID = "MANUFACTURER_ID";
    public final static String MANUFACTURER_NAME = "MANUFACTURER_NAME";
    public final static String FREE_TEXT = "FREE_TEXT";

    /**
     * Creates a Manufacturer object from a row of the resultset.
     * @param rs The ResultSet to map
     * @param i Number for the current row.
     * @return Manufacturer object for the current row.
     * @throws SQLException
     */
    public Manufacturer mapRow(ResultSet rs, int i) throws SQLException {
        return new Manufacturer(rs.getLong(MANUFACTURER_ID),
                rs.getString(MANUFACTURER_NAME),
                rs.getString(FREE_TEXT));
    }

}
