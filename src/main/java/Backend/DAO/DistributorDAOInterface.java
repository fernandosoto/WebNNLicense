package Backend.DAO;

import Backend.Distributor;
import Backend.Manufacturer;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-27.
 */
public interface DistributorDAOInterface {

    void addDistributor(Distributor m);

    Distributor searchDistributorById(long id);

    List<Distributor> searchDistributorByName(String name);

    void setDataSource(DataSource dataSource);
}
