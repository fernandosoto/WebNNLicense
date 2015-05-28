package Backend.form;

import Backend.License;
import Backend.Purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-05-06.
 */
public class SearchForm {
    private Purchase purchase;
    private String radioButtonSelect;
    private String deletedBy, deletedDate;
    private String searchUserName;
    private List<LicenseToUser> licenseToUser=new ArrayList<LicenseToUser>();


    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public List<String> LicenseDetailToString(List<License> licenses){
        List<String> licenseDetails = new ArrayList<String>();
        for(License l : licenses){
            licenseDetails.add(l.getSerialKey() +" User: " + l.getUser() + " , Expire date: "+ l.getExpireDate());
        }
        return licenseDetails;
    }

    public List<LicenseToUser> getLicenseToUser() {
        return licenseToUser;
    }

    public void setLicenseToUser(List<LicenseToUser> licenseToUser) {
        this.licenseToUser = licenseToUser;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getSearchUserName() {
        return searchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }


    public class LicenseToUser{
        private String label;
        private String serialKey, productName, expireDate;

        public LicenseToUser(String serialKey, String productName, String expireDate){
            this.serialKey=serialKey;
            this.productName=productName;
            this.expireDate=expireDate;
            label = "Serial key: "+getSerialKey()+" ,  Product name: "+getProductName()+" , Expire date: "+getExpireDate();
        }

        public String getSerialKey() {return serialKey;}

        public void setSerialKey(String serialKey) {this.serialKey = serialKey;}

        public String getProductName() {return productName;}

        public void setProductName(String productName) {this.productName = productName;}

        public String getExpireDate() {return expireDate;}

        public void setExpireDate(String expireDate) {this.expireDate = expireDate;}

        public String getLabel() {return label;}

        public void setLabel(String label) {this.label = label;}

    }

    public void clearLicenseKeyToList(){
        licenseToUser.clear();
    }

    public void setLicenseKeyToList(Purchase p, License l){
        licenseToUser.add(new LicenseToUser(l.getSerialKey(),p.getProductName(),l.getExpireDate().toString()));
    }


}
