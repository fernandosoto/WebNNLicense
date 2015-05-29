package Backend;

import java.sql.Date;

/**
 * Created by Fernando on 2015-04-21.
 */
public class License
{
    private long licenseId;
    private String user;
    private String serialKey;
    private long purchaseId;
    private Date expireDate;
    private Date deleteDate;
    private String deletedBy;

    /**Empty License constructor.
     */
    public License(){

    }

    /**Constructor for License. With deleted inputs.
     *
     * @param licenseId
     * @param user
     * @param serialKey
     * @param purchaseId
     * @param expireDate
     * @param deleteDate
     * @param deletedBy
     */
    public License(long licenseId, String user, String serialKey, long purchaseId, Date expireDate, Date deleteDate,
                   String deletedBy) {
        this.licenseId = licenseId;
        this.user = user;
        this.serialKey = serialKey;
        this.purchaseId = purchaseId;
        this.expireDate = expireDate;
        this.deleteDate = deleteDate;
        this.deletedBy = deletedBy;
    }

    /**Constructor for License
     *
     * @param licenseId
     * @param user
     * @param serialKey
     * @param purchaseId
     * @param expireDate
     */
    public License(long licenseId, String user, String serialKey, long purchaseId, Date expireDate) {
        this.licenseId = licenseId;
        this.user = user;
        this.serialKey = serialKey;
        this.purchaseId = purchaseId;
        this.expireDate = expireDate;
    }

    /**Returns License id.
     * @return
     */
    public long getLicenseId() {
        return licenseId;
    }

    /**Takes a long and set License id with it.
     * @param licenseId
     */
    public void setLicenseId(long licenseId) {
        this.licenseId = licenseId;
    }

    /**Returns user who is assigned to this License.
     * @return
     */
    public String getUser() {
        return user;
    }

    /**Takes a String and set License user with it.
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**Returns the serial key associated with this License.
     * @return
     */
    public String getSerialKey() {
        return serialKey;
    }

    /**Takes a String and set the serial key with it.
     * @param serialKey
     */
    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    /**Returns the id of the Purchase this License belongs to.
     * @return
     */
    public long getPurchaseId() {
        return purchaseId;
    }

    /**Takes a long and set the Purchase id with it.
     * @param purchaseId
     */
    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**Returns this License's expire date.
     * @return
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**Takes a Date object and set the expire date to it.
     * @param expireDate
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**Returns the date this License was deleted.
     * @return
     */
    public Date getDeleteDate() {return deleteDate; }

    /**Takes a date and set the delete Date to it.
     * @param deleteDate
     */
    public void setDeleteDate(Date deleteDate) { this.deleteDate = deleteDate; }

    /**Returns who deleted this License.
     * @return
     */
    public String getDeletedBy() { return deletedBy; }

    /**Takes a String and set who deleted this License to it.
     * @param deletedBy
     */
    public void setDeletedBy(String deletedBy) { this.deletedBy = deletedBy; }

    /**Compares this License with the input Object. Returns true if they are equal and false if they are not.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        License license = (License) o;

        if (licenseId != license.licenseId) return false;
        if (purchaseId != license.purchaseId) return false;
        if (user != null ? !user.equals(license.user) : license.user != null) return false;
        if (serialKey != null ? !serialKey.equals(license.serialKey) : license.serialKey != null) return false;
        if (expireDate != null ? !expireDate.equals(license.expireDate) : license.expireDate != null) return false;
        if (deleteDate != null ? !deleteDate.equals(license.deleteDate) : license.deleteDate != null) return false;
        return !(deletedBy != null ? !deletedBy.equals(license.deletedBy) : license.deletedBy != null);

    }
}
