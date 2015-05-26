package Backend.DAO;

import Backend.ContextListener;
import Backend.Distributor;
import Backend.Manufacturer;
import Backend.rowMapper.DistributorRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
 * Created by Isak on 2015-04-27.
 */
public class DistributorDAO implements DistributorDAOInterface {
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate db;

    @Autowired
    DistributorRowMapper dRowMapper;

    public static final String SQL_ADD_DISTRIBUTOR = "INSERT INTO DISTRIBUTOR(DISTRIBUTOR_NAME , FREE_TEXT) VALUES(?, ?)";
    public static final String SQL_SEARCH_DISTRIBUTOR_BY_ID = "SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_ID = ?";
    public static final String SQL_SEARCH_DISTRIBUTOR_BY_NAME = "SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_NAME LIKE ?";
    public static final String SQL_UPDATE_DISTRIBUTOR = "UPDATE DISTRIBUTOR SET DISTRIBUTOR_NAME = ?, FREE_TEXT = ? WHERE DISTRIBUTOR_ID = ?";

    @Override
    @Transactional
    public void addDistributor(final Distributor d) throws IllegalArgumentException{
        if (d == null){
            ContextListener.log.error("Distributor cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_DISTRIBUTOR);
                ps.setString(1, d.getName());
                ps.setString(2, d.getFreeText());
                return ps;
            }
        });
    }

    @Override
    public Distributor searchDistributorById(long id) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if (id == 0){
            ContextListener.log.error("id must be higher then 0, id is : " + id, new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        try {
            return (Distributor) db.query(SQL_SEARCH_DISTRIBUTOR_BY_ID, dRowMapper, id).get(0);
        } catch (DataAccessException e) {
            ContextListener.log.error("DataAccessException, probably database connection error.", e);
            throw e;
        } catch (NullPointerException e){
            ContextListener.log.error("searchManufacturerById returned empty list with id : " + id, e);
            throw e;
        }
    }

    @Override
    public List<Distributor> searchDistributorByName(String name) throws IllegalArgumentException{
        if (name == null){
            ContextListener.log.error("name cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        return db.query(SQL_SEARCH_DISTRIBUTOR_BY_NAME, dRowMapper, name+"%");
    }

    @Override
    public void editDistributor(final Distributor distr) throws IllegalArgumentException, DataAccessException, NullPointerException{
        if (distr == null){
            ContextListener.log.error("Distributor cannot be null.", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        final Distributor oldDistr = searchDistributorById(distr.getId());

        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_DISTRIBUTOR);
                ps.setString(1, distr.getName());
                ps.setString(2, distr.getFreeText());
                ps.setLong(3, oldDistr.getId());
                return ps;
            }
        });
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
