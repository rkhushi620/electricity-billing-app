package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho,searchMonthCho;
    JTable table;
    JButton search,print,close;


    deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);


        JLabel searchMeter=new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);


        searchMeterCho=new Choice();
        searchMeterCho.setBounds(180,20,150,20);
        add(searchMeterCho);




        try {
            database c=new database();
            ResultSet resultSet=c.statement.executeQuery("select * from bill");

            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));

            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }


        JLabel searchMonth =new JLabel("Search By Name");
        searchMonth.setBounds(400,20,100,20);
        this.add(searchMonth);


        this.searchMonthCho=new Choice();
        this.searchMonthCho.add("January");
        this.searchMonthCho.add("February");
        this.searchMonthCho.add("March");
        this.searchMonthCho.add("April");
        this.searchMonthCho.add("May");
        this.searchMonthCho.add("June");
        this.searchMonthCho.add("July");
        this.searchMonthCho.add("August");
        this.searchMonthCho.add("September");
        this.searchMonthCho.add("October");
        this.searchMonthCho.add("November");
        this.searchMonthCho.add("December");



        this.searchMonthCho.setBounds(520,20,150,20);
        this.add(searchMonthCho);


        this.table=new JTable();
        try{
            database c=new database();
            ResultSet resultSet= c.statement.executeQuery("select * from bill");
            this.table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e)
        {
            e.printStackTrace();
        }
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        search = new JButton("Search");
        search.setForeground(Color.BLACK);
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setForeground(Color.BLACK);
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close= new JButton("Close");
        close.setForeground(Color.BLACK);
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);








        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String query_search="select * from bill where meter_no='"+searchMeterCho.getSelectedItem()+"'and month='"+searchMonthCho.getSelectedItem()+"'";
            try {
                database c=new database();
                ResultSet resultSet=c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E)
            {
                E.printStackTrace();
            }
        }else if(e.getSource()==print){
            try{
                table.print();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new deposit_details();
    }
}
