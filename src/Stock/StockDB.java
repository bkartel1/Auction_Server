package Stock;

/**
 * Created by Namila on 9/29/2017.
 */
public class StockDB {
    private String securityName;
    private double price;

    public StockDB(String securityName, double price){
        this.securityName=securityName;
        this.price=price;
    }

    public String getSecurityName() {
        return securityName;
    }





    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
