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

    void deleteLicense(License l, String userName);

    List<License> searchLicenseByUser(String name);

    List<License> searchLicenseByPurchase(Purchase p);

    License searchLicenseById(Long id);

    public void editLicense(License lic, String userName);

    void setDataSource(DataSource dataSource);
}
