/**
 * Created by Namila on 9/29/2017.
 */
public class StockDB {
    private String securityName,marketCategory,testIssue,financialStatus,roundLot;
    private int size;
    private float price;

    public StockDB(String securityName,float price){
        this.securityName=securityName;
        this.price=price;
    }

    public String getSecurityName() {
        return securityName;
    }


    public String getMarketCategory() {
        return marketCategory;
    }

    public String getTestIssue() {
        return testIssue;
    }

    public String getFinancialStatus() {
        return financialStatus;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
