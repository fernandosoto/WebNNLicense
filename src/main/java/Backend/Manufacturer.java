package Backend;

import java.io.Serializable;

/**
 * Created by Isak on 2015-04-22.
*/
public class Manufacturer {
    private long id;
    private String name;
    private String freeText;

    /**Empty Manufacturer constructor
     */
    public Manufacturer(){

    }

    /**Manufacturer constructor, takes the Manufacturer id, name and free text as inputs.
     * @param id
     * @param name
     * @param freeText
     */
    public Manufacturer(long id, String name, String freeText){
        this.id = id;
        this.name = name;
        this.freeText = freeText;
    }

    /**Returns this Manufacturers id.
     * @return
     */
    public long getId() { return id; }

    /**Takes a long and set it as a new id for this Manufacturer.
     * @param id
     */
    public void setId(long id) { this.id = id; }

    /**Returns the free text field.
     * @return
     */
    public String getFreeText() {
        return freeText;
    }

    /**Takes a String and set it as the new freeText.
     * @param freeText
     */
    public void setFreeText(String freeText){
        this.freeText = freeText;
    }

    /**Returns the name of this Manufacturer.
     * @return
     */
    public String getName(){
        return name;
    }

    /**Takes a String and set it as the new name for this Manufacturer.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**Compares this Manufacturer with the input Object. Returns true if they are equal and false if they are not.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ((Manufacturer)obj).id == id;
    }
}
