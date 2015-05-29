package Backend.form;

import Backend.Distributor;

import java.util.List;

/**
 * Created by Thomas on 2015-05-15.
 */
public class DistributorForm {

    private String radioButtonSelect;
    private Distributor distributor;

    /**Returns the Distributor object in this form.
     * @return
     */
    public Distributor getDistributor() {
        return distributor;
    }

    /**Takes a Distributor and set it in this DistributorForm object.
     * @param distributor
     */
    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    /**Returns which radio button was selected.
     * @return
     */
    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    /**Takes a string and use it to set which radiobutton was selected.
     * @param radioButtonSelect
     */
    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    /**Takes a list of Distributors and a id. Returns the Distributor who match the id.
     * @param distributors
     * @param id
     * @return
     */
    public Distributor getDistributorById(List<Distributor> distributors, long id) {

        for(Distributor d: distributors) {
            if(d.getId() == id) {
                return d;
            }
        }

        return null;
    }


}
