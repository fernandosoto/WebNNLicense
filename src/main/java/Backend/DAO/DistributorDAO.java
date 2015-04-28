package Backend.DAO;

import Backend.Distributor;
import Backend.Manufacturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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
    public void addDistributor(Distributor d) {
        String sql = "INSERT INTO DISTRIBUTOR(DISTRIBUTOR_NAME , FREE_TEXT) VALUES('" + d.getName() +"', '" + d.getFreeText()
                + "');";
        db.update(sql);
    }

    @Override
    public Distributor searchDistributorById(long id) {
        String sql = "SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_ID = " + id + ";";
        return db.query(sql, new RowMapper<Distributor>() {
            @Override
            public Distributor mapRow(ResultSet rs, int i) throws SQLException {
                return new Distributor(rs.getLong("DISTRIBUTOR_ID"), rs.getString("DISTRIBUTOR_NAME"), rs.getString("FREE_TEXT"));
            }
        }).get(0);
    }

    @Override
    public List<Distributor> searchDistributorByName(String name) {
        String sql = "SELECT * FROM DISTRIBUTOR WHERE DISTRIBUTOR.DISTRIBUTOR_NAME LIKE '" + name + "%';";
        return db.query(sql, new RowMapper<Distributor>() {
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
