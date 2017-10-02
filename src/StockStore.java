import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Namila on 9/29/2017.
 */
public class StockStore {
    private HashMap stocklist;
    private String[] headerfile;

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
                System.out.println(Arrays.toString(values));
                stocklist.put(values[0], new StockDB(values[1],Float.parseFloat(values[values.length-1])));
            }
        }catch (IOException e) {
            System.out.println("Error in reading data" + e);
        }

    }

    public HashMap getStocklist() {
        return stocklist;
    }
}
