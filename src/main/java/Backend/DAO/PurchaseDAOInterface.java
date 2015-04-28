package Backend.DAO;

import Backend.Purchase;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public interface PurchaseDAOInterface {
    void addPurchase(Purchase pur, String userName);

    void deletePurchase(Purchase pur, String userName);

    Purchase searchPurchaseById(long id);

    List<Purchase> searchPurchaseByName(String name);

    List<Purchase> searchPurchaseByDistributorName(String name);

    List<Purchase> searchPurchaseByManufacturerName(String name);

    List<Purchase> searchPurchaseByType(String type);

    void setDataSource(DataSource dataSource);
}
