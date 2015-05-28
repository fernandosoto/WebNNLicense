package Backend.form;

import Backend.Manufacturer;

import java.util.List;

/**
 * Created by Thomas on 2015-05-15.
 */
public class ManufacturerForm {

    private String radioButtonSelect;
    private Manufacturer manufacturer;



    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    public Manufacturer getManufacturerById(List<Manufacturer> manufacturers, long id) {

        for(Manufacturer m: manufacturers) {
            if(m.getId() == id) {
                return m;
            }
        }

        return null;
    }


}
