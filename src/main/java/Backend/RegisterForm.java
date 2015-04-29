package Backend;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-04-28.
 */

public class RegisterForm {

    private String serialKeys;
    private String date;
    private List<License> licenses = new ArrayList<License>();
    private String keySeparator;

    private Purchase purchases;


    public RegisterForm(){
        //Constructor
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
