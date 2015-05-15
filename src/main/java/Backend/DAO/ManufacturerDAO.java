package Backend.DAO;

import Backend.Manufacturer;
import Backend.rowMapper.ManufacturerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
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
@Service
public class ManufacturerDAO implements ManufacturerDAOInterface {
    private DataSource dataSource;

    @Autowired
    public JdbcTemplate db;

    @Autowired
    ManufacturerRowMapper mRowMapper;

    public final static String SQL_SEARCH_BY_MANUFACTURER_ID = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_ID = ?";
    public final static String SQL_SEARCH_BY_MANUFACTURER_NAME = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE ?" + "%";

    @Override
    @Transactional
    public void addManufacturer(final Manufacturer m) {

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
        /*return db.query(new PreparedStatementCreator() {
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
        }).get(0);*/

        return (Manufacturer) db.query(SQL_SEARCH_BY_MANUFACTURER_ID, mRowMapper, id).get(0);
    }

    @Override
    public List<Manufacturer> searchManufacturerByName(final String name) {
        /*String sql = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE '" + name + "%';";
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
        });*/

        return db.query(SQL_SEARCH_BY_MANUFACTURER_NAME, mRowMapper, name);
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
