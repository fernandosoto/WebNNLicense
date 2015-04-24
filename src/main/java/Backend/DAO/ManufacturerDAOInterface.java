package Backend.DAO;

import Backend.Manufacturer;

/**
 * Created by Isak on 2015-04-24.
 */
public interface ManufacturerDAOInterface {

    public void addManufacturer(Manufacturer m);

    public Manufacturer getManufacturerById(long id);

    public Manufacturer getManufacturerByName(String name);


}
