package Backend.DAO;

import Backend.Purchase;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Isak on 2015-04-23.
 */
public interface PurchaseDAOInterface {
    public void addPurchase(Purchase pur);

    public Purchase searchPurchaseById(long id);

    public List<Purchase> searchPurchaseByName(String name);

    public List<Purchase> searchPurchaseByDistributorName(String name);

    public List<Purchase> searchPurchaseByManufacturerName(String name);

    public List<Purchase> searchPurchaseByType(String type);

    public void setDataSource(DataSource dataSource);
}
