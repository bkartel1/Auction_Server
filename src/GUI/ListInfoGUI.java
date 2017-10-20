package GUI;

import BidInfoData.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * Created by Namila on 10/20/2017.
 */
public class ListInfoGUI extends JFrame {
    private BidHistory bidHistory;
    private DefaultTableModel defaultTableModel;
    private JTable table;

    private String symbol;
    private String[] columns = {"Client", "Bid Value", "Date"};


    public ListInfoGUI(BidHistory bidHistory, String symbol){
        this.bidHistory=bidHistory;
        this.symbol=symbol;
        setPreferredSize(new Dimension(600, 400));
        setSize(new Dimension(600, 400));
        setResizable(false);
        init();
    }

    public void init(){
        defaultTableModel=new DefaultTableModel(columns,0);
        table=new JTable(defaultTableModel);
        table.setBounds(0, 0, 800, 400);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setTitle("Bid History View "+symbol.toUpperCase() );
        addSymbolItems();
        revalidate();
        setVisible(true);
         Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                addSymbolItems();
            }
        }, 0, 500);

    }

    public void addSymbolItems(){
        defaultTableModel.getDataVector().removeAllElements();
        ArrayList dataList=bidHistory.getSymbolData(symbol);
       if(dataList!=null) {
           for (int i = 0; i < dataList.size(); i++) {
               BidInfo bidInfo = (BidInfo) dataList.get(i);
               String[] values = {bidInfo.getClientInfo(), String.valueOf(bidInfo.getBidValue()), bidInfo.getDate().toString()};
               defaultTableModel.addRow(values);
           }
       }
    }


}
