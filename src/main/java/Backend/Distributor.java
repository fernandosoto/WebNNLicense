package Backend;

/**
 * Created by Isak on 2015-04-22.
 */
public class Distributor {


    private long id;
    private String name;
    private String freeText;

    /**Empty Distributor constructor.
     */
    public Distributor()
    {}

    /**Constructor for Distributor, takes id, name and free text for the Distributor.
     * @param id
     * @param name
     * @param freeText
     */
    public Distributor(long id, String name, String freeText){
        this.id = id;
        this.name = name;
        this.freeText = freeText;
    }

    /**Returns this Distributors id.
     * @return
     */
    public long getId() {
        return id;
    }

    /**Takes a long and set it as this Distributors new id.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**Returns this Distributors free text field.
     * @return
     */
    public String getFreeText() {
        return freeText;
    }

    /**Takes a String and set it as this Distributors new free text field.
     * @param freeText
     */
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    /**Returns this Distributors name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**Takes a String and set it as this Distributors new name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Compares this Distributor with the input Object. Returns true if they are equal and false if they are not.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distributor that = (Distributor) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(freeText != null ? !freeText.equals(that.freeText) : that.freeText != null);

    }

}
