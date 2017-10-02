import java.io.*;
import java.net.Socket;
import java.util.HashMap;


/**
 * Created by Namila on 10/2/2017.
 */
public class ComServer extends Thread {

    private Socket connection;

    private int state;
    private static int s0=0,s1=1;
    private String symbol,name;
    private ClientInfo clientInfo;
    private StockStore stockStore;
    private HashMap storeHash;

    public ComServer(Socket connection, ClientInfo clientInfo,StockStore stockStore){
        this.connection=connection;
        this.state=s0;
        this.clientInfo=clientInfo;
        this.stockStore=stockStore;
        this.storeHash=stockStore.getStocklist();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            PrintWriter writer=new PrintWriter(new OutputStreamWriter(this.connection.getOutputStream()));
            String line;

            for(line=reader.readLine();line!=null && !line.equals("quit");line=reader.readLine()){
                if(state==s0){
                    if(clientInfo.getClientname().isEmpty()){
                        writer.print("Enter your name: ");
                        clientInfo=new ClientInfo(line);
                        state=s1;
                    }

                }
                if(state==s1){

                }


            }

        }
        catch (IOException e){
            System.out.println(e);
        }


    }



}
