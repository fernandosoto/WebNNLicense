package Backend.form;

import Backend.Manufacturer;

import java.util.List;

/**
 * Created by Thomas on 2015-05-15.
 */
public class ManufacturerForm {

    private String radioButtonSelect;
    private Manufacturer manufacturer;


    /**
     * Returns manufacturer object.
     * @return manufacturer.
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer object to the input.
     * @param manufacturer The new manufacturer object.
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Return value of radiobutton in String format.
     * Used by Spring.
     * @return Value of radiobutton in String.
     */
    public String getRadioButtonSelect() {
        return radioButtonSelect;
    }

    /**
     * Sets the value of the radiobutton with a String.
     * Used by Spring.
     * @param radioButtonSelect String of radiobutton value.
     */
    public void setRadioButtonSelect(String radioButtonSelect) {
        this.radioButtonSelect = radioButtonSelect;
    }

    /**
     * Returns a manufacturer from a list of manufacturers with a Long.
     * @param manufacturers List of Manufacturers.
     * @param id The id of the target manufacturer.
     * @return Target Manufacturer if found else null.
     */
    public Manufacturer getManufacturerById(List<Manufacturer> manufacturers, long id) {

        for(Manufacturer m: manufacturers) {
            if(m.getId() == id) {
                return m;
            }
        }

        return null;
    }


}
