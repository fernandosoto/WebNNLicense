package Backend;

import java.sql.Date;

/**
 * Created by Anna on 2015-05-18.
 */
public class DeletedLicense extends License{
    private long deletedId;
    private String deletedBy;
    private Date deletedDate;

    public DeletedLicense(long licenseId, String user, String serialKey, long purchaseId, Date expireDate,
                          long deletedId, String deletedBy, Date deletedDate){
        super(licenseId, user, serialKey, purchaseId, expireDate);
        this.deletedId = deletedId;
        this.deletedBy = deletedBy;
        this.deletedDate = deletedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(long deletedId) {
        this.deletedId = deletedId;
    }

    @Override
    public boolean equals(Object o) {
        return ((DeletedLicense)o).deletedId == deletedId && ((DeletedLicense)o).getPurchaseId() == getPurchaseId();
    }
}
