package GUI;

import Stock.StockStore;
import BidInfoData.BidHistory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.TimerTask;


/**
 * Created by Namila on 10/3/2017.
 */
public class StocklistGUI extends JFrame {
    private StockStore stockStore;
    private final int updateInterval = 500; // Milliseconds
    private String[] columns = {"Symbol", "Security Name", "Price"};
    private String[] ssnFilter = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
    private JTable table;
    private DefaultTableModel defaultTableModel;

    private BidHistory bidHistory;


    public StocklistGUI(StockStore stockStore, BidHistory bidHistory){
        setTitle("Stock Manager - Server");
        this.bidHistory=bidHistory;
        this.stockStore=stockStore;
        setPreferredSize(new Dimension(800, 400));
        setSize(new Dimension(800, 400));
        init();



    }

    public void init() {
        defaultTableModel = new DefaultTableModel(columns, 0);
        table = new JTable(defaultTableModel);
        table.setBounds(0, 0, 800, 400);
        table.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(table);
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(jScrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        // end of info panel

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               additems();
            }
        }, 0, 500);


        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {

                        String symbol=(String)table.getModel().getValueAt(table.rowAtPoint(e.getPoint()),0);

                        new ListInfoGUI(bidHistory,symbol);

                    }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }



    public void additems(){
    defaultTableModel.getDataVector().removeAllElements();
       for(int i=0;i<ssnFilter.length;i++) {

          String []valeus={ssnFilter[i],stockStore.getSecurityName(ssnFilter[i]),String.valueOf(stockStore.getPrice(ssnFilter[i]))};
          defaultTableModel.addRow(valeus);
       }

    }
}
