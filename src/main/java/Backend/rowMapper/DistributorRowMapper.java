package Backend.rowMapper;

import Backend.Distributor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anna on 2015-05-15.
 */
@Component
public class DistributorRowMapper implements RowMapper {
    public final static String DISTRIBUTOR_ID = "DISTRIBUTOR_ID";
    public final static String DISTRIBUTOR_NAME = "DISTRIBUTOR_NAME";
    public final static String FREE_TEXT = "FREE_TEXT";

    /**
     * Creates a Distributor object from a row of the ResultSet
     * @param rs The ResultSet to map
     * @param i The number of the current row.
     * @return Distributor object for the current row.
     * @throws SQLException
     */
    public Distributor mapRow(ResultSet rs, int i) throws SQLException {
        return new Distributor(rs.getLong(DISTRIBUTOR_ID),
                rs.getString(DISTRIBUTOR_NAME),
                rs.getString(FREE_TEXT));
    }
}
