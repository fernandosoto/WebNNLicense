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

    public DeletedPurchase(long purchaseId, String manufacturerName, String productName, String type, String distributorName,
                           String freeText, long upgradeFrom, String createdBy, Date createdDate, long id, String deletedBy,
                           Date deletedDate){

        super(purchaseId, manufacturerName, productName, type, distributorName,
                freeText, upgradeFrom, createdBy, createdDate);
        this.id = id;
        this.deletedBy = deletedBy;
        this.deletedDate = deletedDate;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
