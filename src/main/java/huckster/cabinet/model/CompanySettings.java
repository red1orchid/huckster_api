package huckster.cabinet.model;

/**
 * Created by PerevalovaMA on 12.08.2016.
 */
public class CompanySettings {
    private int companyId;
    private String yml;
    private String orderEmails;
    private String contactEmails;
    private String yandexKey;
    private int isActive;

    public CompanySettings(int companyId, String yml, String orderEmails, String contactEmails, String yandexKey, int isActive) {
        this.companyId = companyId;
        this.yml = yml;
        this.orderEmails = orderEmails;
        this.contactEmails = contactEmails;
        this.yandexKey = yandexKey;
        this.isActive = isActive;
    }

    public String getYml() {
        return yml;
    }

    public String getOrderEmails() {
        return orderEmails;
    }

    public String getContactEmails() {
        return contactEmails;
    }

    public String getYandexKey() {
        return yandexKey;
    }

    public int getIsActive() {
        return isActive;
    }
}
