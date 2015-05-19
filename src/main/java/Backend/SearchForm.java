package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-05-06.
 */
public class SearchForm {
    private Purchase purchase;
    private String radioButtonSelect;

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public List<String> LicenseDetailToString(List<License> licenses){
        List<String> licenseDetails = new ArrayList<String>();
        for(License l : licenses){
            licenseDetails.add(l.getSerialKey() +" User: " + l.getUser() + " Expire date: "+ l.getExpireDate());
        }
        return licenseDetails;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }



}
