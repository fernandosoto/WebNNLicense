package Backend;

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

    Purchase getPurchase(int pk);
}
