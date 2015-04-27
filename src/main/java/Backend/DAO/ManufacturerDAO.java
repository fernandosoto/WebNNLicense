package Backend.DAO;

import Backend.Manufacturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Isak on 2015-04-24.
 */
public class ManufacturerDAO implements ManufacturerDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Override
    public void addManufacturer(Manufacturer m) {
        String sql = "INSERT INTO MANUFACTURER(MANUFACTURER_NAME, FREE_TEXT) VALUES('" + m.getName() + "', '" + m.getFreeText() + "');";
        db.update(sql);
    }

    @Override
    public Manufacturer searchManufacturerById(long id) {
        String sql = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_ID = " + id + ";";
        return db.query(sql, new RowMapper<Manufacturer>() {
            @Override
            public Manufacturer mapRow(ResultSet rs, int i) throws SQLException {
                return new Manufacturer(rs.getLong("MANUFACTURER_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("FREE_TEXT"));
            }
        }).get(0);
    }

    @Override
    public List<Manufacturer> searchManufacturerByName(String name) {
        String sql = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE '" + name + "%';";
        return db.query(sql, new RowMapper<Manufacturer>() {
            @Override
            public Manufacturer mapRow(ResultSet rs, int i) throws SQLException {
                return new Manufacturer(rs.getLong("MANUFACTURER_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("FREE_TEXT"));
            }
        });
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
