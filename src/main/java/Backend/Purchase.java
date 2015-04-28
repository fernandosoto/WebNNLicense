package Backend;

import java.sql.Date;

/**
 * Created by Fernando on 2015-04-21.
 */
public class Purchase
{
    private long purchaseId;
    private String manufacturerName;
    private String productName;
    private String type;
    private String distributorName;
    private String freeText;
    private long upgradeFrom;
    private String createdBy;
    private Date createdDate;
    private Date deletedDate;
    private String deletedBy;


    public Purchase()
    {
    }

    public Purchase(long purchaseId, String manufacturerName, String productName, String type,
                    String distributorName, String freeText, long upgradeFrom, String createdBy,
                    Date createdDate, Date deletedDate, String deletedBy) {
        this.purchaseId = purchaseId;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.type = type;
        this.distributorName = distributorName;
        this.freeText = freeText;
        this.upgradeFrom = upgradeFrom;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
        this.deletedBy = deletedBy;
    }

    public Purchase(long purchaseId, String manufacturerName, String productName, String type, String distributorName,
                    String freeText, long upgradeFrom, String createdBy, Date createdDate) {
        this.purchaseId = purchaseId;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.type = type;
        this.distributorName = distributorName;
        this.freeText = freeText;
        this.upgradeFrom = upgradeFrom;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        deletedDate = null;
        deletedBy = null;

    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerId) {
        this.manufacturerName = manufacturerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public String getCreatedBy() {return createdBy; }

    public void setCreatedBy(String createdBy) {this.createdBy = createdBy; }

    public Date getCreatedDate() {return createdDate; }

    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate; }

    public Date getDeletedDate() {return deletedDate; }

    public void setDeletedDate(Date deletedDate) {this.deletedDate = deletedDate; }

    public String getDeletedBy() {return deletedBy; }

    public void setDeletedBy(String deletedBy) {this.deletedBy = deletedBy; }

    public void setDistributorName(String distributorId) {
        this.distributorName = distributorId;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public long getUpgradeFrom() { return upgradeFrom; }

    public void setUpgradeFrom(long upgradeFrom) { this.upgradeFrom = upgradeFrom; }
}
