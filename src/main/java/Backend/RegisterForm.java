package Backend;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Backend.Distributor;

/**
 * Created by Thomas on 2015-04-28.
 */

public class RegisterForm {

    private String serialKeys;
    private String date;
    private List<License> licenses = new ArrayList<License>();
    private String keySeparator;

    private Manufacturer manufacturer;
    private Distributor distributor;
    private Purchase purchases;
    public String name;


    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<License> getSerialKeysWithSeparatedLicenses(){

        int year = Integer.parseInt(getDate().substring(0, 4));
        int month = (Integer.parseInt(getDate().substring(5,7)) - 1); // months: 0(jan), 11(dec)
        int day = Integer.parseInt(getDate().substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = new Date(calendar.getTimeInMillis());

        if(keySeparator.equals(".")){
            keySeparator="\\.";
        }

        String[] splitSerialKeys = getSerialKeys().split(keySeparator);


        for(int i=0;i<splitSerialKeys.length;++i){
            licenses.add(new License(0,"Kalle", splitSerialKeys[i],0, date));
        }
        return licenses;
    }

    public String getSerialKeys() {
        return serialKeys;
    }

    public void setSerialKeys(String serialKeys) {
        this.serialKeys = serialKeys;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Purchase getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchases) {
        this.purchases = purchases;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public String getKeySeparator() {
        return keySeparator;
    }

    public void setKeySeparator(String keySeparator) {
        this.keySeparator = keySeparator;
    }

}
