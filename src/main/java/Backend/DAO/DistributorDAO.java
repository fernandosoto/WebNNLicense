package Backend.DAO;

import Backend.Distributor;
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
 * Created by Isak on 2015-04-27.
 */
public class DistributorDAO implements DistributorDAOInterface {
    private DataSource dataSource;
    private JdbcTemplate db;

    @Override
    @Transactional
    public void addDistributor(final Distributor d) {
        db.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO DISTRIBUTOR(DISTRIBUTOR_NAME , FREE_TEXT) VALUES(?, ?)");
                ps.setString(1, d.getName());
                ps.setString(2, d.getFreeText());
                return ps;
            }
        });
    }

    @Override
    public Distributor searchDistributorById(final long id) {
        return db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_ID = ?");
                ps.setLong(1, id);
                return ps;
            }
        }, new RowMapper<Distributor>() {
            @Override
            public Distributor mapRow(ResultSet rs, int i) throws SQLException {
                return new Distributor(rs.getLong("DISTRIBUTOR_ID"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"));
            }
        }).get(0);
    }

    @Override
    public List<Distributor> searchDistributorByName(final String name) {
        return db.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_NAME LIKE ?");
                ps.setString(1, name+"%");
                return ps;
            }
        }, new RowMapper<Distributor>() {
            @Override
            public Distributor mapRow(ResultSet rs, int i) throws SQLException {
                return new Distributor(rs.getLong("DISTRIBUTOR_ID"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"));
            }
        });
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }
}
