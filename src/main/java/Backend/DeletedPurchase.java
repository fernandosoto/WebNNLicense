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

    /**
     * Constructur that uses the constructor of the superclass of Purchase
     * @param purchaseId id of Purchase
     * @param manufacturerName Name of manufacturer of purchase
     * @param productName Name of product of purchase.
     * @param type Type of purchase.
     * @param distributorName Name of distributor of Purchase.
     * @param freeText freeText of purchase.
     * @param upgradeFrom id from Old Purchase if this is an Upgraded purchase.
     * @param createdBy Name of the user that created this Purchase.
     * @param createdDate Date of the creation of this purchase.
     * @param id Id of the DeletedPurchase
     * @param deletedBy Name of the user that deleted this purchase
     * @param deletedDate Date of delete of this purchase
     */
    public DeletedPurchase(long purchaseId, String manufacturerName, String productName, String type, String distributorName,
                           String freeText, long upgradeFrom, String createdBy, Date createdDate, long id, String deletedBy,
                           Date deletedDate){

        super(purchaseId, manufacturerName, productName, type, distributorName,
                freeText, upgradeFrom, createdBy, createdDate);
        this.id = id;
        this.deletedBy = deletedBy;
        this.deletedDate = deletedDate;
    }

    /**
     * Sets the DeletedPurchase Id
     * @param id The new DeletedPurchase Id
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * Return the id of DeletedPurchase.
     * @return id of DeletedPurchase.
     */
    public long getId(){
        return id;
    }

    /**
     * Return the Name of the user that deleted the purchase.
     * @return Name of User that deleted purchase.
     */
    public String getDeletedBy() {
        return deletedBy;
    }

    /**
     * Sets the name of the user that deleted the purchase.
     * @param deletedBy The new Name of the user that deleted the purchase.
     */
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    /**
     * Return date of delete of the purchase
     * @return Date of the delete of purchase
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * Set date of delete of the purchase
     * @param deletedDate Date of the delete of purchase.
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
