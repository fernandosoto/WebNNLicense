package Backend;

import Backend.DAO.DistributorDAOInterface;
import Backend.DAO.LicenseDAOInterface;
import Backend.DAO.ManufacturerDAOInterface;
import Backend.DAO.PurchaseDAOInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-04-30.
 */
public class DeletedForm {

    private List<Purchase> purchases = new ArrayList<Purchase>();
    private List<License> licenses = new ArrayList<License>();
    private List<String> radioButtonAlternatives = new ArrayList<String>();
    private String radioButtonSelect;


    public List<String> getRadioButtonAlternatives() {
        return radioButtonAlternatives;
    }

    public void setRadioButtonAlternatives(List<String> radioButtonAlternatives) {
        this.radioButtonAlternatives = radioButtonAlternatives;
    }

    public void setRadioButtonAlternatives(String alt) {
        this.radioButtonAlternatives.add(alt);
    }

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

}