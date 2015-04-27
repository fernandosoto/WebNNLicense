package Backend.DAO;

import Backend.License;
import Backend.Purchase;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public interface LicenseDAOInterface {
    
    void addLicense(License l);

    List<License> searchLicenseByUser(String name);

    List<License> searchLicenseByPurchase(Purchase p);

    License searchLicenseById(Long id);

    void setDataSource(DataSource dataSource);
}
