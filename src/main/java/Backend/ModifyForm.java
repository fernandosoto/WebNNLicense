package Backend;

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

    public String getNewSerialKeys() {
        return newSerialKeys;
    }

    public void setNewSerialKeys(String newSerialKeys) {
        this.newSerialKeys = newSerialKeys;
    }

    public String getKeySeparator() {
        return keySeparator;
    }

    public void setKeySeparator(String keySeparator) {
        this.keySeparator = keySeparator;
    }

    private String keySeparator;

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

    private Distributor distributor;

    public String getExpireDate() {return expireDate;}

    public void setExpireDate(String expireDate) {this.expireDate = expireDate;}

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

    public Date getDateFromString(String strDate){
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = (Integer.parseInt(strDate.substring(5,7)) - 1); // months: 0(jan), 11(dec)
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public List<License> getSerialKeysWithSeparatedLicenses(){

        List<License> licenses = new ArrayList<License>();
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
            licenses.add(new License(0,"Kalle", splitSerialKeys[i],0, date));
        }
        return licenses;
    }
}
