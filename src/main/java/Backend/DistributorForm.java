package Backend;

import java.util.List;

/**
 * Created by Thomas on 2015-05-15.
 */
public class DistributorForm {

    private String radioButtonSelect;
    private Distributor distributor;

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public Distributor getManufacturerById(List<Distributor> distributors, long id) {

        for(Distributor d: distributors) {
            if(d.getId() == id) {
                return d;
            }
        }

        return null;
    }


}
