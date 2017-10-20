package BidInfoData;

import Client.ClientInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Namila on 10/6/2017.
 */
public class BidHistory {


    private HashMap bidHistoryMap;
    private BidHistoryInfo bidHistoryInfo;

    public BidHistory(){
        bidHistoryMap =new HashMap<String,BidHistoryInfo>();


    }

    public void setbidHistory(String symbol, ClientInfo client, double bidValue){
        if(bidHistoryMap.containsKey(symbol)){
            bidHistoryInfo=(BidHistoryInfo) bidHistoryMap.get(symbol);
            bidHistoryInfo.setbidHistoryList(client,bidValue);
            bidHistoryMap.put(symbol,bidHistoryInfo);


        }else{
            bidHistoryInfo=new BidHistoryInfo();
            bidHistoryInfo.setbidHistoryList(client,bidValue);
            bidHistoryMap.put(symbol,bidHistoryInfo);
        }
    }

    public ArrayList getSymbolData(String symbol){
        BidHistoryInfo bidInfo;

        if(bidHistoryMap.containsKey(symbol)){
           bidInfo=(BidHistoryInfo) bidHistoryMap.get(symbol);
            return  bidInfo.getArraylist();
        }
        else return null;
    }
}
