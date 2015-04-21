package Backend;

/**
 * Created by Fernando on 2015-04-21.
 */
public class MySQL implements DBCommunication
{
    @Override
    public void addLicense(License lic) {
        
    }

    @Override
    public int addPurchase(Purchase pur) {
        return 0;
    }

    @Override
    public License getLicense(int pk) {
        return null;
    }

    @Override
    public Purchase getPurchase(int pk) {
        return null;
    }
}
