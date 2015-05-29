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

    /**
     * Empty constructur used by Spring MVC
     */
    public Purchase()
    {
    }

    /**
     * Constucts a Purchase Object with the following parameters.
     * @param purchaseId
     * @param manufacturerName
     * @param productName
     * @param type
     * @param distributorName
     * @param freeText
     * @param createdBy
     * @param createdDate
     */
    public Purchase(long purchaseId, String manufacturerName, String productName, String type,
                    String distributorName, String freeText, String createdBy,
                    Date createdDate) {
        this.purchaseId = purchaseId;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.type = type;
        this.distributorName = distributorName;
        this.freeText = freeText;
        this.createdBy = createdBy;
        this.createdDate = createdDate;

    }

    /**
     * Constructor with the following parameters.
     * @param purchaseId
     * @param manufacturerName
     * @param productName
     * @param type
     * @param distributorName
     * @param freeText
     * @param upgradeFrom
     * @param createdBy
     * @param createdDate
     */
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
    }

    /**
     * Returns the PurchaseID of the Purchase object.
     * @return purchaseId.
     */
    public long getPurchaseId() {
        return purchaseId;
    }

    /**
     * Sets the purchaseId of the purchase object
     * @param purchaseId The new purchaseId
     */
    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * Returns the name of the manufacturer of the Purchase.
     * @return Manufacturer name.
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * Sets the manufacturer name of the purchase
     * @param manufacturerName New manufacturerName.
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * Returns the productName of purchase
     * @return productname
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the productName of purchase
     * @param productName new productName of purchase
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the type of purchase.
     * @return The type of purchase.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the purchase.
     * @param type The new type of purchase
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Return a String that represents the distributorName of purchase.
     * @return Distributor name of purchase.
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * Return a String that represents the name of the creator of the purchase.
     * @return name of the creator.
     */
    public String getCreatedBy() {return createdBy; }

    /**
     * Sets the name of the creator of the purchase.
     * @param createdBy The new name of the Creator.
     */
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy; }

    /**
     * Returns a Date object of when the purchase was created.
     * @return date of creation of purchase.
     */
    public Date getCreatedDate() {return createdDate; }

    /**
     * Sets the date of Creation of the purchase with the help of a date object.
     * @param createdDate The new date of the purchase.
     */
    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate; }

    /**
     * Sets the distributorName of the purchase.
     * @param distributorName The new distributorName of the purchase.
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * Returns the freeText of purchase
     * @return freeText.
     */
    public String getFreeText() {
        return freeText;
    }

    /**
     * Sets the freeText of a purchase
     * @param freeText The new freeText of purchase.
     */
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    /**
     * Return the id of the old Purchase if this is an upgraded purchase
     * @return
     */
    public long getUpgradeFrom() { return upgradeFrom; }

    /**
     * Set the upgradedFrom id of the purchase.
     * @param upgradeFrom
     */
    public void setUpgradeFrom(long upgradeFrom) { this.upgradeFrom = upgradeFrom; }

    /**
     * Compares if this purchase and an other purchase O is equal.
     * This method overrides the equals method of the Object class.
     * @param o Other purchase
     * @return true if both objects equal
     * false if not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (purchaseId != purchase.purchaseId) return false;
        if (upgradeFrom != purchase.upgradeFrom) return false;
        if (manufacturerName != null ? !manufacturerName.equals(purchase.manufacturerName) : purchase.manufacturerName != null)
            return false;
        if (productName != null ? !productName.equals(purchase.productName) : purchase.productName != null)
            return false;
        if (type != null ? !type.equals(purchase.type) : purchase.type != null) return false;
        if (distributorName != null ? !distributorName.equals(purchase.distributorName) : purchase.distributorName != null)
            return false;
        if (freeText != null ? !freeText.equals(purchase.freeText) : purchase.freeText != null) return false;
        if (createdBy != null ? !createdBy.equals(purchase.createdBy) : purchase.createdBy != null) return false;
        return !(createdDate != null ? !createdDate.equals(purchase.createdDate) : purchase.createdDate != null);
    }

}
