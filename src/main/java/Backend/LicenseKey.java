package Backend;

import java.sql.Date;

/**
 * Created by Thomas on 2015-05-18.
 */
public class LicenseKey extends License{
    private String label;

    /**Constructor for LicenseKey.
     * @param licenseId
     * @param serialKey
     * @param expireDate
     * @param user
     * @param purchaseId
     * @param deleteDate
     * @param deletedBy
     */
    public LicenseKey(Long licenseId, String serialKey, Date expireDate, String user, long purchaseId,Date deleteDate,String deletedBy){
        super(licenseId, user, serialKey, purchaseId, expireDate, deleteDate, deletedBy);

        label = licenseToString();
    }

    /**Returns this LicenseKey label.
     * @return
     */
    public String getLabel() {return label;}

    /**Takes a String and set it as this LicenseKey new label.
     * @param label
     */
    public void setLabel(String label) {this.label = label;}

    /**Returns a String with information about this LicenseKey.
     * Informations is the serial key, expire date and the user it's assigned to.
     * @return
     */
    private String licenseToString(){
        return String.format("%-35s %s %-30s", "Serial key: "+super.getSerialKey(),"Expire date: "+super.getExpireDate(),"User: "+super.getUser());
    }
}
