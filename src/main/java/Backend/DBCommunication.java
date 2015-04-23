package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 2015-04-21.
 */
public interface DBCommunication
{
    void addLicense(License lic);

    void addPurchase(Purchase pur);

    void addManufacturer(Manufacturer m);

    void addDistributor(Distributor d);

    License getLicense(int pk);

    //List<Purchase> searchPurchaseById(int pk);

    List<Purchase> searchPurchaseByName(String name);
}
