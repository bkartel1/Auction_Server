package Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Namila on 9/29/2017.
 */
public class StockStore {
    private static HashMap stocklist;
    private String[] headerfile;
    private StockDB stockDB;

    public StockStore(String filename)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String header = br.readLine();
            if (header.equals(null)) throw new IOException("no data in the file");
            headerfile = header.split(",");

            String[] values;
            stocklist = new HashMap<String, StockDB>();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                values = line.split(",");
                stocklist.put(values[0], new StockDB(values[1],Double.parseDouble(values[values.length-1])));
            }
        }catch (IOException e) {
            System.out.println("Error in reading data" + e);
        }

    }

    public void setStockStore(HashMap map){
        this.stocklist=map;
    }

    public boolean searchKeyAvailable(String key) throws NullPointerException{
        stockDB =(StockDB)stocklist.get(key);
        if(stockDB.getSecurityName().equals(null))return false;
        else return true;
    }

    public double getPrice(String key){
        stockDB =(StockDB)stocklist.get(key);
        return stockDB.getPrice();
    }

    public void setPrice(String key,double value){
        stockDB =(StockDB)stocklist.get(key);
        stockDB.setPrice(value);
        stocklist.put(key, stockDB);
    }

    public String getSecurityName(String key){
        stockDB =(StockDB)stocklist.get(key);
        return stockDB.getSecurityName();
    }

    public synchronized HashMap getStocklist() {
        return stocklist;
    }
}
