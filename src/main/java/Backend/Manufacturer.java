package Backend;

import java.io.Serializable;

/**
 * Created by Isak on 2015-04-22.
*/
public class Manufacturer implements Serializable {
    private long id;
    private String name;
    private String freeText;


    public Manufacturer(){

    }
    public Manufacturer(long id, String name, String freeText){
        this.id = id;
        this.name = name;
        this.freeText = freeText;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText){
        this.freeText = freeText;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private static final long serialVersionUID=245367867532234234L;

}
