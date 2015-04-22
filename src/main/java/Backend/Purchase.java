package Backend;

/**
 * Created by Fernando on 2015-04-21.
 */
public class Purchase
{
    private long purchaseId;
    private long manufacturerId;
    private String productName;
    private String type;
    private long distributorId;
    private String freeText;

    public Purchase()
    {
    }

    public Purchase(long purchaseId, long manufacturerId, String productName, String type, long distributorId, String freeText) {
        this.purchaseId = purchaseId;
        this.manufacturerId = manufacturerId;
        this.productName = productName;
        this.type = type;
        this.distributorId = distributorId;
        this.freeText = freeText;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(long distributorId) {
        this.distributorId = distributorId;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
