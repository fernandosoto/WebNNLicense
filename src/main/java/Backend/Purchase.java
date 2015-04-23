package Backend;

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

    public Purchase()
    {
    }

    public Purchase(long purchaseId, String manufacturerName, String productName, String type, String distributorName, String freeText) {
        this.purchaseId = purchaseId;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.type = type;
        this.distributorName = distributorName;
        this.freeText = freeText;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getManufacturerId() {
        return manufacturerName;
    }

    public void setManufacturerId(String manufacturerId) {
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

    public String getDistributorId() {
        return distributorName;
    }

    public void setDistributorId(String distributorId) {
        this.distributorName = distributorId;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
