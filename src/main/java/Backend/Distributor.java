package Backend;

/**
 * Created by Isak on 2015-04-22.
 */
public class Distributor {
    private long id;
    private String name;
    private String freeText;

    public Distributor(long id, String name, String freeText){
        this.id = id;
        this.name = name;
        this.freeText = freeText;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
