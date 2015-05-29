package Backend;

import java.sql.Date;

/**
 * Created by Anna on 2015-05-18.
 */
public class DeletedLicense extends License{
    private long deletedId;
    private String deletedBy;
    private Date deletedDate;

    /**Constructor for DeletedLicense. Extends License class with information about it's deletion.
     * @param licenseId
     * @param user
     * @param serialKey
     * @param purchaseId
     * @param expireDate
     * @param deletedId
     * @param deletedBy
     * @param deletedDate
     */
    public DeletedLicense(long licenseId, String user, String serialKey, long purchaseId, Date expireDate,
                          long deletedId, String deletedBy, Date deletedDate){
        super(licenseId, user, serialKey, purchaseId, expireDate);
        this.deletedId = deletedId;
        this.deletedBy = deletedBy;
        this.deletedDate = deletedDate;
    }

    /**Returns the date this License object was deleted.
     * @return
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**Takes a date and set it as the new date this License object was deleted.
     * @param deletedDate
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**Returns who deleted this License.
     * @return
     */
    public String getDeletedBy() {
        return deletedBy;
    }

    /**Takes a String and set it as who deleted this License object.
     * @param deletedBy
     */
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    /**Returns the id this deleted License has in the Deleted License table.
     * @return
     */
    public long getDeletedId() {
        return deletedId;
    }

    /**Takes a long and set it as the new id for this Deleted License.
     * @param deletedId
     */
    public void setDeletedId(long deletedId) {
        this.deletedId = deletedId;
    }

    /**Compares this Deleted object with the input Object. Returns true if the deletedId of both match and false if they don't.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return ((DeletedLicense)o).deletedId == deletedId && ((DeletedLicense)o).getPurchaseId() == getPurchaseId();
    }
}
