package Backend;

import java.sql.Date;

/**
 * Created by Thomas on 2015-05-18.
 */
public class LicenseKey extends License{
    private String label;

    public LicenseKey(Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy){
        super(licenseId, user, serialKey, purchaseId, expireDate, deleteDate, deletedBy);

        label = licenseToString();
    }

    public String getLabel() {return label;}

    public void setLabel(String label) {this.label = label;}

    private String licenseToString(){
        return String.format("%-35s %s %-30s", "Serial key: "+super.getSerialKey(),"Expire date: "+super.getExpireDate(),"User: "+super.getUser());
    }
}
