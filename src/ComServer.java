

import BidInfoData.BidHistory;
import GUI.StocklistGUI;
import Stock.StockStore;
import Client.ClientInfo;

import java.io.*;
import java.net.Socket;


/**
 * Created by Namila on 10/2/2017.
 */
public class ComServer extends Thread {

    private Socket connection;

    private int state;
    private static int s0=0,s1=1;
    private String symbol="NO",name;
    private double cost,bidcost;
    private ClientInfo clientInfo;
    private StockStore stockStore;
    private boolean loopval=true;
    private BufferedReader reader;
    private PrintWriter writer;
    private BidHistory bidHistory;
    private StocklistGUI stocklistGUI;

    public ComServer(Socket connection,StockStore stockStore,BidHistory bidHistory){
        this.bidHistory=bidHistory;
        this.connection=connection;
        this.state=s0;//new user mode
        stocklistGUI=new StocklistGUI(stockStore,bidHistory); //creating server stock view
        this.stockStore=stockStore;

        clientInfo =new ClientInfo("NoOne");

        try{
            reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            writer=new PrintWriter(new OutputStreamWriter(this.connection.getOutputStream()));

        }catch (IOException e){
            System.out.println(e);
        }



    }

    @Override
    public void run() {
        try {
            String line=null;

            while(loopval) {

                if (state == s0) {
                    //initiali client is set to noone as defult and change state
                    if (clientInfo.getClientname().equals("NoOne")) {
                        writer.print("Enter Name: \n");
                        writer.flush();
                        line=reader.readLine();
                        if(quit(line))break;
                        name = line;
                        System.out.println(name+" Registered");
                        clientInfo = new ClientInfo(name);
                        writer.print(clientInfo.getClientname()+ " Registered\n");
                        state = s1;

                    }

                }
                    else if (state == s1) {
                        //default symbol is set to NO
                        if (symbol.equals("NO")) {
                            writer.print("Enter Symbol: \n");
                            writer.flush();
                            line=reader.readLine();
                            if(quit(line))break;
                            symbol = line.toUpperCase();
                        } else if (!symbol.isEmpty()) {
                            //when there is valid symbol
                             if(stockStore.searchKeyAvailable(symbol)){
                                cost = stockStore.getPrice(symbol);
                                writer.print(symbol + " Price: " + cost);
                                writer.flush();
                                writer.print("\nEnter Price to bid: ");
                                writer.flush();
                                line=reader.readLine();
                                if(quit(line))break;
                                bidcost = Float.parseFloat(line);
                                stockStore.setPrice(symbol,bidcost);
                                bidHistory.setbidHistory(symbol,clientInfo,bidcost);
                                stocklistGUI.additems(); //adding item to server view
                            } else {
                                 //when there is no symbol this quit
                                writer.print("-1");
                                writer.flush();
                                 this.connection.close();
                                 break;
                            }

                        }


                    }

                    writer.flush();

            }

                this.connection.close();

        }
        catch (IOException e){
            System.out.println(e);
        }



    }
    private boolean quit(String line){
        //checking the line=quit
        if( line.equals("quit") || line.equals(null)){
            loopval=false;
            return true;
        }else return false;

    }


}




