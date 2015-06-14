package Backend.form;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Backend.Distributor;
import Backend.License;
import Backend.Manufacturer;
import Backend.Purchase;

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

    /**Returns the Distributor of the new Purchase
     * @return
     */
    public Distributor getDistributor() {
        return distributor;
    }

    /**Set the Distributor of the new Purchase.
     * @param distributor
     */
    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    /**Returns the Manufacturer of the new Purchase.
     * @return
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**Set the Manufacturer of the new Purchase.
     * @param manufacturer
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**Returns a List of License created from the serial keys String with the separator.
     * @return
     */
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

    /**Returns the String with all serial keys.
     * @return
     */
    public String getSerialKeys() {
        return serialKeys;
    }

    /**Set the String of serial keys.
     * @param serialKeys
     */
    public void setSerialKeys(String serialKeys) {
        this.serialKeys = serialKeys;
    }

    /**Returns a String with the date.
     * @return
     */
    public String getDate() {
        return date;
    }

    /**Takes a String representing the date and set it.
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**Returns the Purchase.
     * @return
     */
    public Purchase getPurchases() {
        return purchases;
    }

    /**Takes a Purchase object and set it.
     * @param purchases
     */
    public void setPurchases(Purchase purchases) {
        this.purchases = purchases;
    }

    /**Returns a List of Licenses.
     * @return
     */
    public List<License> getLicenses() {
        return licenses;
    }

    /**Takes a List of Licenses and set it.
     * @param licenses
     */
    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    /**Returns the key separator.
     * @return
     */
    public String getKeySeparator() {
        return keySeparator;
    }

    /**Takes a String and set it as key separator.
     * @param keySeparator
     */
    public void setKeySeparator(String keySeparator) {
        this.keySeparator = keySeparator;
    }

}
