package Backend;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public List<String> LicenseDetailToString(List<License> licenses){
        List<String> licenseDetails = new ArrayList<String>();
        licenseDetails.clear();
        for(License l : licenses){
            licenseDetails.add(l.getSerialKey() +" User: " + l.getUser() + " Expire date: "+ l.getExpireDate());
        }
        return licenseDetails;
    }

    public void clearLicenseKeys(){
        licenseKeys.clear();
    }

    public License getLicenseFromId(Long id){
        for(LicenseKey l : licenseKeys){
            if(id==l.getLicenseId()){
                return l;
            }
        }
        return null;
    }

    public List<LicenseKey> getLicenseKeyList(List<License> licenses){
        clearLicenseKeys();
        for(License l : licenses) {
            licenseKeys.add(new LicenseKey(l.getLicenseId(),l.getSerialKey(),l.getExpireDate(),l.getUser(),l.getPurchaseId(),l.getDeleteDate(),l.getDeletedBy()));
            //Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy
        }
        return licenseKeys;
    }
}
