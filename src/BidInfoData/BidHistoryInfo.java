package BidInfoData;

import Client.ClientInfo;

import java.util.ArrayList;

/**
 * Created by Namila on 10/6/2017.
 */
public class BidHistoryInfo {
    private ArrayList bidlist;

    public BidHistoryInfo(){
        bidlist=new ArrayList();
    }

    public void setbidHistoryList(ClientInfo clientInfo,double bidvalue){
        bidlist.add(new BidInfo(clientInfo,bidvalue));
    }

    public ArrayList getArraylist(){

        return bidlist;
    }

}
