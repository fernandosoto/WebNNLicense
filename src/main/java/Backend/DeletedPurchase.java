package Backend;

import Backend.DAO.PurchaseDAOInterface;

import java.sql.Date;

/**
 * Created by Anna on 2015-05-18.
 */
public class DeletedPurchase extends Purchase{
    private long id;
    private String deletedBy;
    private Date deletedDate;
    private long deletedPurchaseId;

    public DeletedPurchase(long id, String deletedBy, Date deletedDate){
        this.id = id;
        this.deletedBy = deletedBy;
        this.deletedDate = deletedDate;
        deletedPurchaseId = super.getPurchaseId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getDeletedBy() {
        return deletedBy;
    }

    @Override
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public Date getDeletedDate() {
        return deletedDate;
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public long getDeletedPurchaseId() {
        return deletedPurchaseId;
    }

    public void setDeletedPurchaseId() {
        this.deletedPurchaseId = super.getPurchaseId();
    }
}
