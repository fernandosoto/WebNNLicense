package Backend.DAO;

import Backend.Manufacturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    @Transactional
    public void addManufacturer(final Manufacturer m) {
//        String sql = "INSERT INTO MANUFACTURER(MANUFACTURER_NAME, FREE_TEXT) VALUES('" + m.getName() + "', '" + m.getFreeText() + "');";
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO MANUFACTURER(MANUFACTURER_NAME, FREE_TEXT) VALUES(?, ?)");
                ps.setString(1, m.getName());
                ps.setString(2, m.getFreeText());
                return ps;
            }
        });
    }

    @Override
    public Manufacturer searchManufacturerById(final long id) {
//        String sql = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_ID = " + id + ";";
        return db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_ID = ?");
                ps.setLong(1, id);
                return ps;
            }
        }, new RowMapper<Manufacturer>() {
            @Override
            public Manufacturer mapRow(ResultSet rs, int i) throws SQLException {
                return new Manufacturer(rs.getLong("MANUFACTURER_ID"), rs.getString("MANUFACTURER_NAME"), rs.getString("FREE_TEXT"));
            }
        }).get(0);
    }

    @Override
    public List<Manufacturer> searchManufacturerByName(final String name) {
        String sql = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE '" + name + "%';";
        return db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE ?");
                ps.setString(1, name+"%");
                return ps;
            }
        }, new RowMapper<Manufacturer>() {
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
