package Backend.DAO;

import Backend.DeletedPurchase;
import Backend.Purchase;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public interface PurchaseDAOInterface {
    long addPurchase(Purchase pur, String userName, long distrId, long manuId)throws Exception;

    void deletePurchase(Purchase pur, String userName);

    Purchase searchPurchaseById(long id);

    List<Purchase> searchPurchaseByName(String name) ;

    List<Purchase> searchPurchaseByDistributorName(String name);

    List<Purchase> searchPurchaseByManufacturerName(String name);

    List<Purchase> searchPurchaseByType(String type);

    List<Purchase> searchAllPurchases();

    List<DeletedPurchase> searchDeletedPurchases();

    DeletedPurchase searchDeletedPurchaseById(long id);

    void editPurchase(Purchase pur, String userName, long manufacturerId, long distributorId);

   // void setDataSource(DataSource dataSource);
}
