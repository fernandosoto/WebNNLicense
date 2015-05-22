package Backend.DAO;

import Backend.ContextListener;
import Backend.Manufacturer;
import Backend.rowMapper.ManufacturerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public final static String SQL_SEARCH_BY_MANUFACTURER_ID = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER_ID = ?";
    public final static String SQL_SEARCH_BY_MANUFACTURER_NAME = "SELECT * FROM MANUFACTURER WHERE MANUFACTURER.MANUFACTURER_NAME LIKE ?";
    public static final String SQL_UPDATE_MANUFACTURER = "UPDATE MANUFACTURER SET MANUFACTURER_NAME = ?, FREE_TEXT = ? WHERE MANUFACTURER_ID = ?";

    @Override
    @Transactional
    public void addManufacturer(final Manufacturer m) throws IllegalArgumentException{
        if (m == null){
            ContextListener.log.error("Manufacturer cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
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
    public Manufacturer searchManufacturerById(final long id) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if (id == 0){
            ContextListener.log.error("id must be higher then 0, id is : " + id, new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        try {
            return (Manufacturer) db.query(SQL_SEARCH_BY_MANUFACTURER_ID, mRowMapper, id).get(0);
        } catch (DataAccessException e) {
            ContextListener.log.error("DataAccessException, probably database connection error.", e);
            throw e;
        } catch (NullPointerException e){
            ContextListener.log.error("searchManufacturerById returned empty list with id : " + id, e);
            throw e;
        }
    }

    @Override
    public List<Manufacturer> searchManufacturerByName(String name) throws IllegalArgumentException{
        if (name == null){
            ContextListener.log.error("name cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        return db.query(SQL_SEARCH_BY_MANUFACTURER_NAME, mRowMapper, name + "%");
    }

    @Override
    public void editManufacturer(final Manufacturer manuf) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if (manuf == null){
            ContextListener.log.error("Manufacturer cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

        final Manufacturer oldManuf = searchManufacturerById(manuf.getId());

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_MANUFACTURER);
                ps.setString(1, manuf.getName());
                ps.setString(2, manuf.getFreeText());
                ps.setLong(3, oldManuf.getId());
                return ps;
            }
        });
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
