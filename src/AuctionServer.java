
import BidInfoData.BidHistory;
import Stock.StockStore;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Namila on 10/2/2017.
 */
public class AuctionServer {
    private static final int PORT=2000;

    private static int socketport;
    private static StockStore stockStore;
    private ComServer comServer;
    private ServerSocket serverSocket;
    private static BidHistory bidHistory;

    public AuctionServer(int port) throws IOException{
        serverSocket=new ServerSocket(port);
        socketport=port;

    }

    private void loopServer(StockStore stockStore)throws IOException, InterruptedException{
        while(true){
            //thread run
            Socket socket=serverSocket.accept();
            comServer=new ComServer(socket,stockStore,bidHistory);
            comServer.start();
            comServer.sleep(1);

        }

    }

    public static void main(String[] args) {
       try{
           //starting server
           AuctionServer auctionServer=new AuctionServer(PORT);
           stockStore=new StockStore("stocks.csv");
            bidHistory=new BidHistory();
           auctionServer.loopServer(stockStore);
       }catch (IOException e){
           System.out.println(e);
       }catch (InterruptedException e){
           System.out.println(e);
       }

    }



}
