package BidInfoData;

import Client.ClientInfo;

import java.util.Date;

/**
 * Created by Namila on 10/6/2017.
 */
public class BidInfo {
    private ClientInfo clientInfo;
    private double bidValue;
    private Date date;

    public BidInfo(ClientInfo clientInfo,double bidvalue){
        this.clientInfo=clientInfo;
        this.bidValue=bidvalue;
        this.date=new Date();
    }

    public String getClientInfo() {
        return clientInfo.getClientname();
    }

    public double getBidValue() {
        return bidValue;
    }

    public Date getDate() {
        return date;
    }
}
