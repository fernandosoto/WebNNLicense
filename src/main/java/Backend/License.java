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

    public License(){

    }

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

    public License(long licenseId, String user, String serialKey, long purchaseId, Date expireDate) {
        this.licenseId = licenseId;
        this.user = user;
        this.serialKey = serialKey;
        this.purchaseId = purchaseId;
        this.expireDate = expireDate;
    }

    public long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(long licenseId) {
        this.licenseId = licenseId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSerialKey() {
        return serialKey;
    }

    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getDeleteDate() {return deleteDate; }

    public void setDeleteDate(Date deleteDate) { this.deleteDate = deleteDate; }

    public String getDeletedBy() { return deletedBy; }

    public void setDeletedBy(String deletedBy) { this.deletedBy = deletedBy; }

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
