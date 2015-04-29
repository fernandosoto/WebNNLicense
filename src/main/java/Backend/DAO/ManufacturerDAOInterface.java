package Backend.DAO;

import Backend.Manufacturer;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-24.
 */
public interface ManufacturerDAOInterface {

    public void addManufacturer(Manufacturer m);

    public Manufacturer searchManufacturerById(long id);

    public Manufacturer searchManufacturerByName(String name);

    public void setDataSource(DataSource dataSource);

}
