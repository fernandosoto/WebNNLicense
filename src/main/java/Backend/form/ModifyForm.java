package Backend.form;

import Backend.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Thomas on 2015-05-12.
 */
public class ModifyForm {

    private String radioButtonSelect;
    private Purchase purchase;
    private List<LicenseKey> licenseKeys=new ArrayList<LicenseKey>();
    private License license;
    private String date;
    private String expireDate;
    private Manufacturer manufacturer;
    private String newSerialKeys;
    private String keySeparator;
    private Distributor distributor;

    /**Returns the new serial keys.
     * @return
     */
    public String getNewSerialKeys() {
        return newSerialKeys;
    }

    /**Takes a string and set it as the new serial keys.
     * @param newSerialKeys
     */
    public void setNewSerialKeys(String newSerialKeys) {
        this.newSerialKeys = newSerialKeys;
    }

    /**Returns the separator for the serial keys String.
     * @return
     */
    public String getKeySeparator() {
        return keySeparator;
    }

    /**Takes a String and set it as the separator for the serial keys String.
     * @param keySeparator
     */
    public void setKeySeparator(String keySeparator) {
        this.keySeparator = keySeparator;
    }

    /**Returns the new Distributor selected.
     * @return
     */
    public Distributor getDistributor() {
        return distributor;
    }

    /**Takes a Distributor and set it as the new Distributor selected.
     * @param distributor
     */
    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    /**Returns the new Manufacturer selected.
     * @return
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**Takes a Manufacturer and set it as the new Manufacturer selected.
     * @param manufacturer
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**Returns the expire date for the new serials.
     * @return
     */
    public String getExpireDate() {return expireDate;}

    /**Takes a String and set is as the expire date for the new serials.
     * @param expireDate
     */
    public void setExpireDate(String expireDate) {this.expireDate = expireDate;}

    /**Returns the modified License.
     * @return
     */
    public License getLicense() {
        return license;
    }

    /**Set the modified License.
     * @param license
     */
    public void setLicense(License license) {
        this.license = license;
    }

    /**Returns the date of the modification.
     * @return
     */
    public String getDate() {
        return date;
    }

    /**Set the date of the modification.
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**Returns the selected radio button.
     * @return
     */
    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    /**Set the selected radio button.
     * @param radioButtonSelect
     */
    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    /**Returns the modified Purchase.
     * @return
     */
    public Purchase getPurchase() {
        return purchase;
    }

    /**Set the modified Purchase.
     * @param purchase
     */
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    /**Takes a List of Licenses and returns a List of LicenseKeys.
     * @param licenses
     * @return
     */
    public List<LicenseKey> getLicenseKeyList(List<License> licenses){
        clearLicenseKeys();
        for(License l : licenses) {
            licenseKeys.add(new LicenseKey(l.getLicenseId(),l.getSerialKey(),l.getExpireDate(),l.getUser(),l.getPurchaseId(),l.getDeleteDate(),l.getDeletedBy()));
            //Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy
        }
        return licenseKeys;
    }

    /**Clears the List of LicenseKeys
     */
    public void clearLicenseKeys(){
        licenseKeys.clear();
    }

    /**Takes a String with the date and returns a Date object.
     * @param strDate
     * @return
     */
    public Date getDateFromString(String strDate){
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = (Integer.parseInt(strDate.substring(5,7)) - 1); // months: 0(jan), 11(dec)
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    /**Returns the serial keys String as a List of Licenses.
     * @return
     */
    public List<License> getSerialKeysWithSeparatedLicenses(){

        List<License> licenses = new ArrayList<License>();
        licenses.clear();
        int year = Integer.parseInt(getExpireDate().substring(0, 4));
        int month = (Integer.parseInt(getExpireDate().substring(5, 7)) - 1); // months: 0(jan), 11(dec)
        int day = Integer.parseInt(getExpireDate().substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = new Date(calendar.getTimeInMillis());

        if(keySeparator.equals(".")){
            keySeparator="\\.";
        }

        String[] splitSerialKeys = getNewSerialKeys().split(keySeparator);


        for(int i=0;i<splitSerialKeys.length;++i){
            licenses.add(new License(0,User.getLoggedInUser(), splitSerialKeys[i],0, date));
        }
        return licenses;
    }
}
