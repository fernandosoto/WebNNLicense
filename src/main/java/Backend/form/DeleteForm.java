package Backend.form;

import Backend.License;
import Backend.LicenseKey;
import Backend.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-05-18.
 */
public class DeleteForm {

    private String radioButtonSelect;
    private Purchase purchase;
    private License license;
    private String date;
    private List<LicenseKey> licenseKeys=new ArrayList<LicenseKey>();

    /**
     * Return a String representation of date YYYY-MM-DD.
     * @return String of date YYYY-MM-DD.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date with a String representation of Date YYYY-MM-DD.
     * @param date String of Date YYYY-MM-DD.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns a purchase object.
     * @return purchase object.
     */
    public Purchase getPurchase() {
        return purchase;
    }

    /**
     * Set a new purchase in deleteForm
     * @param purchase new Purchase
     */
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    /**
     * Return value of radiobutton in String format.
     * @return value of radiobutton in String.
     */
    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    /**
     * Sets the value of radiobutton in String format.
     * @param radioButtonSelect String format.
     */
    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    /**
     * Returns License object
     * @return License.
     */
    public License getLicense() {
        return license;
    }

    /**
     * Set a new license object.
     * @param license The new license.
     */
    public void setLicense(License license) {
        this.license = license;
    }

    /**
     * Returns Information contained in List of licenses in String format.
     * @param licenses List of Licenses
     * @return String of license information.
     */
    public List<String> LicenseDetailToString(List<License> licenses){
        List<String> licenseDetails = new ArrayList<String>();
        licenseDetails.clear();
        for(License l : licenses){
            licenseDetails.add(l.getSerialKey() +" User: " + l.getUser() + " Expire date: "+ l.getExpireDate());
        }
        return licenseDetails;
    }

    /**
     * Empties licenseKeys list.
     */
    public void clearLicenseKeys(){
        licenseKeys.clear();
    }


    /**
     * Creates a list of LicenseKeys from a List of Licenses.
     * @param licenses List of Licenses.
     * @return licenseKeys.
     */
    public List<LicenseKey> getLicenseKeyList(List<License> licenses){
        clearLicenseKeys();
        for(License l : licenses) {
            licenseKeys.add(new LicenseKey(l.getLicenseId(),l.getSerialKey(),l.getExpireDate(),l.getUser(),l.getPurchaseId(),l.getDeleteDate(),l.getDeletedBy()));
            //Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy
        }
        return licenseKeys;
    }
}
