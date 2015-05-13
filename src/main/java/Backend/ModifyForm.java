package Backend;

import java.sql.Date;
import java.util.ArrayList;
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

    public class LicenseKey extends License{
        private String label;

        public LicenseKey(Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy){
            super(licenseId, user, serialKey, purchaseId, expireDate, deleteDate, deletedBy);

            label = licenseToString();
        }

        public String getLabel() {return label;}

        public void setLabel(String label) {this.label = label;}

        private String licenseToString(){
            return String.format("%-35s %s %-30s", "Serial key: "+super.getSerialKey(),"Expire date: "+super.getExpireDate(),"User: "+super.getUser());
        }
    }


    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<LicenseKey> getLicenseKeyList(List<License> licenses){
        for(License l : licenses) {
            licenseKeys.add(new LicenseKey(l.getLicenseId(),l.getSerialKey(),l.getExpireDate(),l.getUser(),l.getPurchaseId(),l.getDeleteDate(),l.getDeletedBy()));
            //Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy
        }
        return licenseKeys;
    }

    public List<String> LicenseDetailToString(List<License> licenses){
        List<String> licenseDetails = new ArrayList<String>();
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

}
