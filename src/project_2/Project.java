package project_2;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.api.tree.BreakTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Murphy
 */

public class Project extends javax.swing.JFrame {
public static int flight_number = 2582;
public static int combocounter = 5;
public ArrayList<String> days= new ArrayList<String>();
public String daysstr;
public ArrayList<Flight> flights = new ArrayList<Flight>();
public ArrayList<Capital> capitals = new ArrayList<Capital>();
Capital istanbul = new Capital("Istanbul");
Capital madrid = new Capital("Madrid");
Capital buenosaires = new Capital("Buenos Aires");
Capital newyork = new Capital("New York");
Capital santiago = new Capital("Santiago");
public ArrayList<String> cities = new ArrayList<String>();
public static int flightsIndex = 1;
public static Calendar systemtime = Calendar.getInstance();
public static Calendar dcalendar = Calendar.getInstance();
public static Calendar acalendar = Calendar.getInstance();
public int d_hour_h;
public int d_hour_m;
public int a_hour_h;
public int a_hour_m;
public Thread permissioncheck = new Thread(new Runnable() {
    @Override
    public void run() {
        while(true){
            for(Flight tmp : flights){
                      if(tmp.getStat().equals("AskingPermission")){
                          warning2.setText(tmp.getFlightNumber()+"  Asking for permission !");
                          try {
                              Thread.sleep(900);
                          } catch (InterruptedException ex) {
                              Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      }
                  }
        }
    }
});
 /*permissioncheck = new Thread(new Runnable() {
           @Override
           public void run() {
              while(true){
                  for(Flight tmp : flights){
                      if(tmp.getStat().equals("AskingPermission")){
                          warning2.setText(warning.getText()+"\n"+tmp.getFlightNumber()+" Asking for Permission !");
                          
                      }
                  }
              }
           }
    });
    permissioncheck.start();
*/
public void startFlights(){
    for(Flight temp:flights){
        temp.start();
    }
}
public void refleshtable(){
    DefaultTableModel model = (DefaultTableModel)table_fly.getModel();
    int i = 0;
    for(Flight temp:flights){
        model.setValueAt(temp.getStat(), i, 3);
        i++;
    }
    
    
}
public void addcities(){
       capitals.add(madrid);
       capitals.add(istanbul);
       capitals.add(buenosaires);
       capitals.add(newyork);
       capitals.add(santiago);
    }

        
    /**
     * Creates new form Project
     */

     public Calendar setCalendar(int h,int m){
         Calendar tmp = Calendar.getInstance();
         tmp.set(Calendar.HOUR_OF_DAY,h);
         tmp.set(Calendar.MINUTE, m);
         return tmp;
         
     }
     
     
    public Project() {
      
        addcities();
        flight_number+=12;
        Flight f2 = new Flight(flight_number, "THY", "Monday",istanbul.getName(), buenosaires.getName(),setCalendar(00, 20),setCalendar(00, 25));
        flight_number+=12;
        flights.add(f2);
        Flight f1 = new Flight(flight_number, "THY", "Saturday", madrid.getName(), santiago.getName(),setCalendar(00, 10), setCalendar(00, 15));
        flight_number+=12;
        flights.add(f1);
        Flight f3 = new Flight(flight_number, "Lufthansa", "Friday",santiago.getName(), istanbul.getName(), setCalendar(00, 05),setCalendar(00, 15));
        flight_number+=12;
        flights.add(f3);
        Flight f4 = new Flight(flight_number, "Emirates", "Saturday", madrid.getName(), santiago.getName(), setCalendar(00, 10),setCalendar(00, 15));
        flight_number+=12;
        flights.add(f4);
        Flight f5 = new Flight(flight_number, "Luxair", "Wednesday", istanbul.getName(), madrid.getName(), setCalendar(00, 25), setCalendar(00, 40));
        flight_number+=12;
        flights.add(f5);
        Flight f6 = new Flight(flight_number, "Iberia", "Thursday", buenosaires.getName(), santiago.getName(),setCalendar(00, 40), setCalendar(01, 15));
        flight_number+=12;
        flights.add(f6);
        Flight f7 = new Flight(flight_number, "KLM", "Monday", buenosaires.getName(), newyork.getName(), setCalendar(00, 30), setCalendar(00, 50));
        flight_number+=12;
        
        flights.add(f7);
       dcalendar.set(Calendar.HOUR_OF_DAY, 00);
       dcalendar.set(Calendar.MINUTE, 20);
       acalendar.set(Calendar.HOUR_OF_DAY, 00);
       acalendar.set(Calendar.MINUTE, 55);
        Flight f8 = new Flight(flight_number,"Emirates","Monday",santiago.getName(),buenosaires.getName(),dcalendar,acalendar);
        flight_number+=12;
        flights.add(f8);
        Flight f9 = new Flight(flight_number, "KLM", "Saturday", newyork.getName(), istanbul.getName(), setCalendar(00 , 05), setCalendar(00, 30));
        flight_number+=12;
        flights.add(f9);
        Flight f10 = new Flight(flight_number, "Luxair", "Sunday", madrid.getName(), santiago.getName(), setCalendar(00, 35),setCalendar(01, 05));
        flight_number+=12;
        flights.add(f10);
        
       
        
       
        for(Capital index:capitals){
            cities.add(index.getName());
        }
        permissioncheck.start();
        startFlights();
        initComponents();
          DefaultTableModel model  = (DefaultTableModel)table.getModel();
           for(int i = 0;i<flights.size();i++){
            Object[] array = {flights.get(i).getFlightNumber(),flights.get(i).getAirlines(),flights.get(i).getDays(),flights.get(i).getFrom(),flights.get(i).getTo(),(String)(flights.get(i).getD_time().get(Calendar.HOUR)+":"+flights.get(i).getD_time().get(Calendar.MINUTE)),(String)(flights.get(i).getA_time().get(Calendar.HOUR)+":"+flights.get(i).getA_time().get(Calendar.MINUTE))};
            model.addRow(array);
           }
            FileWriter writer=null; 
          
    try {
        writer = new FileWriter("flights.txt");
        flightsIndex=1;
        for(int j = 0 ;j<flights.size();j++){
            writer.write(flightsIndex+"----"+flights.get(j).getFlightNumber() + "," + flights.get(j).getAirlines() + "," + flights.get(j).getDays() + "," + flights.get(j).getFrom()+
                    ","+flights.get(j).getTo()+","+(String)(flights.get(j).getD_time().get(Calendar.HOUR)+":"+flights.get(j).getD_time().get(Calendar.MINUTE))+","+(String)(flights.get(j).getA_time().get(Calendar.HOUR)+":"+flights.get(j).getA_time().get(Calendar.MINUTE))+"\n");
            flightsIndex++;
        }
        writer.close();
    } catch (IOException ex) {
        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        DefaultTableModel model2  = (DefaultTableModel)table_fly.getModel();
           for(int i = 0;i<flights.size();i++){
               
            Object[] array = {flights.get(i).getFlightNumber(),flights.get(i).getFrom(),flights.get(i).getTo(),flights.get(i).getStat(),(String)(flights.get(i).getD_time().get(Calendar.HOUR_OF_DAY)+":"+flights.get(i).getD_time().get(Calendar.MINUTE)),(String)(flights.get(i).getA_time().get(Calendar.HOUR_OF_DAY)+":"+flights.get(i).getA_time().get(Calendar.MINUTE))};
            model2.addRow(array);
           }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">   
    
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jDialog1 = new javax.swing.JDialog();
        tab = new javax.swing.JTabbedPane();
        tab_1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        from = new javax.swing.JTextField();
        to = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        d_hour = new javax.swing.JComboBox<>();
        d_min = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        a_hour = new javax.swing.JComboBox<>();
        a_min = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        airlines = new javax.swing.JTextField();
        add_button = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        edit_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        mon_button = new javax.swing.JRadioButton();
        tue_button = new javax.swing.JRadioButton();
        wed_button = new javax.swing.JRadioButton();
        thurs_button = new javax.swing.JRadioButton();
        fri_button = new javax.swing.JRadioButton();
        sat_button = new javax.swing.JRadioButton();
        sun_button = new javax.swing.JRadioButton();
        delet_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        addCapital = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        capitals_combo = new javax.swing.JComboBox<>();
        addCapitalButton = new javax.swing.JButton();
        deleteCapitalButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tab_2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_fly = new javax.swing.JTable();
        permission = new javax.swing.JButton();
        cancelFlight = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        delayText = new javax.swing.JTextField();
        delayButton = new javax.swing.JButton();
        warning2 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        systemtimeshow = new javax.swing.JLabel();
        getSystemTime = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flight App");
        setLocation(new java.awt.Point(850, 200));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Flight Options.v2");

        jLabel2.setText("From : ");

        jLabel3.setText("To      : ");

        from.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fromMouseClicked(evt);
            }
        });

        to.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toMouseClicked(evt);
            }
        });

        jLabel4.setText("Departure Time : ");

        d_hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        d_min.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        jLabel5.setText("Arrive Time        :");

        a_hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        a_min.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        jLabel6.setText("AirLines  :");

        airlines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                airlinesMouseClicked(evt);
            }
        });
        airlines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airlinesActionPerformed(evt);
            }
        });

        add_button.setText("Add");
        add_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_buttonMouseClicked(evt);
            }
        });

        warning.setForeground(new java.awt.Color(102, 0, 0));

        edit_button.setText("Edit");
        edit_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_buttonMouseClicked(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight Number", "Airlines", "Weekdays", "From", "To", "Departure", "Arrive"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("WeekDays");

        mon_button.setText("Monday");
        mon_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mon_buttonActionPerformed(evt);
            }
        });

        tue_button.setText("Tuesday");
        tue_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tue_buttonActionPerformed(evt);
            }
        });

        wed_button.setText("Wednesday");
        wed_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wed_buttonActionPerformed(evt);
            }
        });

        thurs_button.setText("Thursday");
        thurs_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thurs_buttonActionPerformed(evt);
            }
        });

        fri_button.setText("Friday");
        fri_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fri_buttonActionPerformed(evt);
            }
        });

        sat_button.setText("Saturday");
        sat_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sat_buttonActionPerformed(evt);
            }
        });

        sun_button.setText("Sunday");
        sun_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sun_buttonActionPerformed(evt);
            }
        });

        delet_button.setText("Delete");
        delet_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delet_buttonMouseClicked(evt);
            }
        });

        jLabel9.setText("Add Capital       : ");

        addCapital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCapitalMouseClicked(evt);
            }
        });

        jLabel10.setText("Delete Capital  :");

        capitals_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Istanbul", "Buenos Aires", "New York", "Madrid", "Santiago" }));
        capitals_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capitals_comboActionPerformed(evt);
            }
        });

        addCapitalButton.setText("Add Capital");
        addCapitalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCapitalButtonActionPerformed(evt);
            }
        });

        deleteCapitalButton.setText("Delete Capital");
        deleteCapitalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteCapitalButtonMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Capital Options");

        javax.swing.GroupLayout tab_1Layout = new javax.swing.GroupLayout(tab_1);
        tab_1.setLayout(tab_1Layout);
        tab_1Layout.setHorizontalGroup(
            tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_1Layout.createSequentialGroup()
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(43, 51, Short.MAX_VALUE)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(from))
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(airlines, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel8))
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_1Layout.createSequentialGroup()
                                        .addComponent(fri_button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sat_button))
                                    .addGroup(tab_1Layout.createSequentialGroup()
                                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mon_button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(wed_button))
                                        .addGap(18, 18, 18)
                                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tue_button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(thurs_button))))))
                        .addGap(57, 57, 57)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(a_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(a_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(d_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(d_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab_1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_1Layout.createSequentialGroup()
                                .addComponent(sun_button)
                                .addGap(150, 150, 150)))
                        .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
            .addGroup(tab_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addCapital)
                    .addComponent(capitals_combo, 0, 143, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addCapitalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteCapitalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(tab_1Layout.createSequentialGroup()
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(delet_button, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(355, 355, 355))
        );
        tab_1Layout.setVerticalGroup(
            tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(d_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(d_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(a_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_button, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(airlines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mon_button)
                            .addComponent(tue_button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wed_button)
                            .addComponent(thurs_button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sat_button)
                            .addComponent(fri_button))))
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab_1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edit_button)
                        .addGap(4, 4, 4))
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(sun_button)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(16, 16, 16)))
                .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_1Layout.createSequentialGroup()
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(addCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCapitalButton))
                        .addGap(18, 18, 18)
                        .addGroup(tab_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(capitals_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteCapitalButton)))
                    .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delet_button)
                .addGap(19, 19, 19))
        );

        tab.addTab("Edit Unit", tab_1);

        tab_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_2MouseClicked(evt);
            }
        });

        table_fly.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight Number", "From", "To", "Situation", "Departure", "Arrive"
            }
        ));
        table_fly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_flyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_fly);

        permission.setText("Give Permission");
        permission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                permissionMouseClicked(evt);
            }
        });
        permission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permissionActionPerformed(evt);
            }
        });

        cancelFlight.setText("Cancel Flight");
        cancelFlight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelFlightMouseClicked(evt);
            }
        });

        jLabel12.setText("Delay Time  (min) : ");

        delayText.setMinimumSize(new java.awt.Dimension(15, 53));
        delayText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayTextActionPerformed(evt);
            }
        });

        delayButton.setText("Set Delay");
        delayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delayButtonMouseClicked(evt);
            }
        });

        warning2.setForeground(new java.awt.Color(102, 0, 0));

        startButton.setText("Start");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Pause");

        stopButton.setText("Stop");
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });

        jButton4.setText("Resume");

        jLabel13.setText("System Time   :   ");

        jLabel15.setText("Enter a System Time  : ");

        jLabel14.setText("In a Form : XX:XX");

        javax.swing.GroupLayout tab_2Layout = new javax.swing.GroupLayout(tab_2);
        tab_2.setLayout(tab_2Layout);
        tab_2Layout.setHorizontalGroup(
            tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_2Layout.createSequentialGroup()
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_2Layout.createSequentialGroup()
                        .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab_2Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(permission, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addComponent(delayText, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tab_2Layout.createSequentialGroup()
                                        .addComponent(delayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(93, 93, 93)
                                        .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tab_2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGap(138, 138, 138)
                                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(getSystemTime, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                        .addGap(6, 6, 6))
                                    .addGroup(tab_2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(98, 98, 98)
                                        .addComponent(systemtimeshow, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(tab_2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        tab_2Layout.setVerticalGroup(
            tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(delayText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(delayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(permission, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getSystemTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(systemtimeshow, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tab.addTab("Control Unit", tab_2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void formMouseClicked(java.awt.event.MouseEvent evt) {                                  
        
    }                                 

    private void tue_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        
    }                                          

    private void mon_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        
    }                                          

    private void add_buttonMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        if(from.getText().trim().equals("")){
            warning.setText("Please Enter a 'From' City");

        }else if(to.getText().trim().equals("")){
            warning.setText("Please Enter a 'To' City");
        }else if(airlines.getText().trim().equals("")){
            warning.setText("Please Enter a 'Airlines' Company");
            
        }else{
            int check_from = 0;
            int check_to = 0;
            for(int i = 0;i<combocounter;i++){
                if(capitals_combo.getItemAt(i).equals(from.getText())){
                    check_from=1;
                }
            }
            if(check_from==0){
                warning.setText("Unvalid 'From' City.");
               
            }
            for(int i = 0;i<combocounter;i++){
                if(capitals_combo.getItemAt(i).equals(to.getText())){
                    check_to = 1;
                }
            }
            if(check_to==0){
                warning.setText(warning.getText()+"\nUnvalid 'to' City.");
            }
            
            if(check_from==1&&check_to==1){
                FileWriter writer=null;
                try {
                    if(mon_button.isSelected()){
                        days.add("Monday");
                    }else{
                        days.remove("Monday");
                    }       if(tue_button.isSelected()){
                        days.add("Tuesday");
                    }else{
                        days.remove("Tuesday");
                    }       if(wed_button.isSelected()){
                        days.add("Wednesday");
                    }else{
                        days.remove("Wednesday");
                    }       if(thurs_button.isSelected()){
                        days.add("Thursday");
                    }else{
                        days.remove("Thursday");
                    }       if(fri_button.isSelected()){
                        days.add("Friday");
                    }else{
                        days.remove("Friday");
                    }       if(sat_button.isSelected()){
                        days.add("Saturday");
                    }else{
                        days.remove("Saturday");
                    }       if(sun_button.isSelected()){
                        days.add("Sunday");
                    }else{
                        days.remove("Sunday");
                    }       String list="";
                    if(days.size()<1){
                        for(String index:days){
                            list+=(index+", ");
                        }
                    }else{
                        list=days.toString();
                    }       DefaultTableModel model  = (DefaultTableModel)table.getModel();
                    flight_number+=12;
                    d_hour_h =(int) d_hour.getSelectedItem();
                    d_hour_m = (int)d_min.getSelectedItem();
                    a_hour_h = (int)a_hour.getSelectedItem();
                    a_hour_m = (int)a_min.getSelectedItem();
                    Calendar addCalendard = Calendar.getInstance();
                    Calendar addCalendara = Calendar.getInstance();
                    addCalendard.set(Calendar.HOUR_OF_DAY, d_hour_h);
                    addCalendard.set(Calendar.MINUTE, d_hour_m);
                    addCalendara.set(Calendar.HOUR_OF_DAY, a_hour_h);
                    addCalendara.set(Calendar.MINUTE, a_hour_m);
                            
                    Flight route = new Flight(flight_number,airlines.getText(),list,from.getText(),to.getText(),addCalendard,addCalendara);
                    writer = new FileWriter("flights.txt",true);
                    writer.write(flightsIndex+"----"+route.getFlightNumber()+","+route.getAirlines()+","+route.getDays()+","+route.getFrom()+","+route.getTo()+","+route.getD_time()+","+route.getA_time()+"\n");
                    flightsIndex++;
                    flights.add(route);
                    System.out.println("\n");
                    Object[] array = {(flight_number),airlines.getText(),list,from.getText(),to.getText(),(String)(addCalendard.get(Calendar.HOUR_OF_DAY)+":"+addCalendard.get(Calendar.MINUTE)),(String)(addCalendara.get(Calendar.HOUR_OF_DAY)+":"+addCalendara.get(Calendar.MINUTE))};
                    model.addRow(array);
                    for(Flight temp:flights) {
                        System.out.println(temp.getFlightNumber()+" "+temp.getAirlines()+" "+temp.getDays()+" "+temp.getFrom()+" "+temp.getTo()+" "+temp.getD_time()+" "+temp.getA_time());
                    }   days.clear();
                    from.setText("");
                    to.setText("");
                    addCapital.setText("");
                    warning.setText("Flight added successfully.");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            }
            }
         DefaultTableModel model2  = (DefaultTableModel)table_fly.getModel();
           for(int i = 0;i<flights.size();i++){
               
            Object[] array = {flights.get(i).getFlightNumber(),flights.get(i).getFrom(),flights.get(i).getTo(),flights.get(i).getStat(),(String)(flights.get(i).getD_time().get(Calendar.HOUR_OF_DAY)+":"+flights.get(i).getD_time().get(Calendar.MINUTE)),(String)(flights.get(i).getA_time().get(Calendar.HOUR_OF_DAY)+":"+flights.get(i).getA_time().get(Calendar.MINUTE))};
            model2.addRow(array);
           }
    }                                       

    private void airlinesActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void airlinesMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:,
        airlines.setText("");
    }                                     

    private void toMouseClicked(java.awt.event.MouseEvent evt) {                                
        // TODO add your handling code here:
        to.setText("");
    }                               

    private void fromMouseClicked(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        from.setText("");
    }                                 

    private void wed_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
       
    }                                          

    private void thurs_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        
    }                                            

    private void fri_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        
    }                                          

    private void sat_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
       
    }                                          

    private void sun_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        
    }                                          

    private void edit_buttonMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        DefaultTableModel model  = (DefaultTableModel)table.getModel();
        warning.setText("");
        int selected_row = table.getSelectedRow();
        if(selected_row== -1){
            warning.setText("Please Select a row to edit");
            
        }
        model.setValueAt(from.getText(),selected_row,3);
        model.setValueAt(to.getText(),selected_row,4);
        model.setValueAt(airlines.getText(),selected_row,1);
        Object d_hour_change = d_hour.getSelectedItem().toString()+"."+d_min.getSelectedItem().toString();
        Object a_hour_change = a_hour.getSelectedItem().toString()+"."+a_min.getSelectedItem().toString();
        model.setValueAt(d_hour_change,selected_row,5);
        model.setValueAt(a_hour_change,selected_row,6);
       
         if(mon_button.isSelected()){
                days.add("Monday");
            }else{
                days.remove("Monday");
            }
            if(tue_button.isSelected()){
                days.add("Tuesday");
            }else{
                days.remove("Tuesday");
            }
            if(wed_button.isSelected()){
                days.add("Wednesday");
            }else{
                days.remove("Wednesday");
            }
            if(thurs_button.isSelected()){
                days.add("Thursday");
            }else{
                days.remove("Thursday");
            }
            if(fri_button.isSelected()){
                days.add("Friday");
            }else{
                days.remove("Friday");
            }
            if(sat_button.isSelected()){
                days.add("Saturday");
            }else{
                days.remove("Saturday");
            }
            if(sun_button.isSelected()){
                days.add("Sunday");
            }else{
                days.remove("Sunday");
            }
           
            String list="";
            if(days.size()<1){
            for(String index:days){
                list+=(index+", ");
            }
            }else{
                list=days.toString();
            }
            model.setValueAt(list,selected_row,2);
            days.clear();
             for(Flight temp:flights){
            if(temp.getFlightNumber()==(Integer)model.getValueAt(selected_row,0)){
                temp.setAirlines(model.getValueAt(selected_row,1).toString());
                temp.setDays(model.getValueAt(selected_row,2).toString());
                temp.setFrom(model.getValueAt(selected_row,3).toString());
                temp.setTo(model.getValueAt(selected_row,4).toString());
                temp.setD_time((Calendar)model.getValueAt(selected_row,5));
                temp.setA_time((Calendar)model.getValueAt(selected_row,6));
                break;
            }
          
        }
        FileWriter writer=null; 
    try {
        writer = new FileWriter("flights.txt");
        for(int i = 0 ;i<flights.size();i++){
            writer.write(flightsIndex+"----"+flights.get(i).getFlightNumber() + "," + flights.get(i).getAirlines() + "," + flights.get(i).getDays() + "," + flights.get(i).getFrom()+
                    ","+flights.get(i).getTo()+","+flights.get(i).getD_time()+","+flights.get(i).getA_time()+"\n");
            flightsIndex++;
        }  
        writer.close();
    } catch (IOException ex) {
        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
    }
        
                
        warning.setText("Flight Infos has been changed.");
    }                                        

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {                                   
        int selected_row = table.getSelectedRow();
        DefaultTableModel model  = (DefaultTableModel)table.getModel();
        airlines.setText(model.getValueAt(selected_row,1).toString());
        from.setText(model.getValueAt(selected_row,3).toString());
        to.setText(model.getValueAt(selected_row,4).toString());
        if(mon_button.isSelected()){
            mon_button.doClick();
        }
        if(tue_button.isSelected()){
            tue_button.doClick();
        }
        if(wed_button.isSelected()){
            wed_button.doClick();
        }
        if(thurs_button.isSelected()){
            thurs_button.doClick();
        }
        if(fri_button.isSelected()){
            fri_button.doClick();
        }
        if(sat_button.isSelected()){
            sat_button.doClick();
        }
        if(sun_button.isSelected()){
            sun_button.doClick();
        }
       
    }                                  

    private void delet_buttonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        DefaultTableModel model  = (DefaultTableModel)table.getModel();
         int selected_row = table.getSelectedRow();
         model.removeRow(selected_row);
         for(Flight temp:flights){
             if(temp.getFlightNumber()==(int)table.getValueAt(selected_row,0)){
                 flights.remove(temp);
                 break;
             }
         }
         for(Flight index:flights){
             System.out.println(index.getFlightNumber()+" "+index.getAirlines()+" "+index.getDays()+" "+index.getFrom()+" "+index.getTo()+" "+index.getD_time()+" "+index.getA_time());
         }
    FileWriter writer=null; 
    try {
        writer = new FileWriter("flights.txt");
        for(int i = 0 ;i<flights.size();i++){
            writer.write(flights.get(i).getFlightNumber() + "," + flights.get(i).getAirlines() + "," + flights.get(i).getDays() + "," + flights.get(i).getFrom()+
                    ","+flights.get(i).getTo()+","+flights.get(i).getD_time()+","+flights.get(i).getA_time()+"\n");
        }  
        writer.close();
    } catch (IOException ex) {
        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    warning.setText("Flight has been deleted.");
    }                                         

    private void capitals_comboActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void addCapitalButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       int check =0;
        for(int i = 0;i<combocounter;i++){
           if(capitals_combo.getItemAt(i).equals(addCapital.getText())){
               check =1;
               warning.setText("This Capital is already Exist.");
               break;
           }
       }
        if(check==0){
        capitals_combo.addItem(addCapital.getText());
        warning.setText("City added successfully.");
        addCapital.setText("");
        combocounter++;
        }
    }                                                

    private void deleteCapitalButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                 
      capitals_combo.removeItem(capitals_combo.getSelectedItem());
      combocounter--;
    }                                                

    private void addCapitalMouseClicked(java.awt.event.MouseEvent evt) {                                        
       addCapital.setText("");
    }                                       

    private void delayTextActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    }                                         

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {                                 
        
        
    }                                

    private void cancelFlightMouseClicked(java.awt.event.MouseEvent evt) {                                          
        DefaultTableModel model = (DefaultTableModel)table_fly.getModel();
        int selected = table_fly.getSelectedRow();
        if(model.getValueAt(selected,3).toString().equals("onGround")){
            for(Flight temp:flights){
                if(temp.getFlightNumber()==(int)model.getValueAt(selected,0)){
                    temp.setStat("Cancelled.");
                    model.setValueAt(temp.getStat(),selected,3);
                    warning2.setText("Flight has been cancelled.");
                    break;
                }
            
            
            }
            
            
            
        }else if(model.getValueAt(selected,3).toString().equals("Cancelled.")){
            warning2.setText("Flight already cancelled.");
        
        }else{
            warning2.setText("Flight already started.");
        }
    }                                         

    private void table_flyMouseClicked(java.awt.event.MouseEvent evt) {                                       
warning2.setText("");        // TODO add your handling code here:
    }                                      

    private void tab_2MouseClicked(java.awt.event.MouseEvent evt) {                                   
warning2.setText("");        // TODO add your handling code here:
    }                                  
    
   public boolean startvalue=true;
   public Thread clock;
   
    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {                                         
        String[] value = getSystemTime.getText().split(":");
       int hour = Integer.parseInt(value[0]);
       int min = Integer.parseInt(value[1]);
       systemtime.set(Calendar.HOUR_OF_DAY, hour);
       systemtime.set(Calendar.MINUTE, min);
       systemtimeshow.setText(systemtime.get(Calendar.HOUR_OF_DAY)+":"+systemtime.get(Calendar.MINUTE));
      
       if(startvalue==true){
       clock = new Thread(new Runnable(){
            @Override
            public void run() {
              while(true){
                  systemtime.add(Calendar.MINUTE, 1);
                  refleshtable();
                  systemtimeshow.setText((String)(systemtime.get(Calendar.HOUR_OF_DAY)+":"+systemtime.get(Calendar.MINUTE)));
                  try {
                      
                      Thread.sleep(1000);
                      
                  } catch (InterruptedException ex) {
                      Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
            }
           
       });
       clock.start();
       startvalue = false;
       }
       
    }                                        

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void delayButtonMouseClicked(java.awt.event.MouseEvent evt) {                                         
        DefaultTableModel model = (DefaultTableModel)table_fly.getModel();
        int select = table_fly.getSelectedRow();
        int delay = Integer.parseInt(delayText.getText());
        for(Flight tmp:flights){
            if(tmp.getFlightNumber()==(Integer)model.getValueAt(select, 0)){
                tmp.d_time.add(Calendar.MINUTE, delay);
                tmp.a_time.add(Calendar.MINUTE, delay);
                model.setValueAt((String)(flights.get(select).getD_time().get(Calendar.HOUR_OF_DAY)+":"+tmp.getD_time().get(Calendar.MINUTE)), select, 4);
                model.setValueAt((String)(flights.get(select).getA_time().get(Calendar.HOUR_OF_DAY)+":"+tmp.getA_time().get(Calendar.MINUTE)), select, 5);
                
        int i = 0;
        
            
            
       
                
                
                
                
                
                
                
            }
        }
        
    }                                        

    private void permissionActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void permissionMouseClicked(java.awt.event.MouseEvent evt) {                                        
       DefaultTableModel model = (DefaultTableModel)table_fly.getModel();
       int selected = table_fly.getSelectedRow();
          for(Flight tmp:flights){
            if(tmp.getFlightNumber()==(Integer)model.getValueAt(selected, 0)){
            tmp.setStat("onGround");
            warning2.setText("");
            model.setValueAt(tmp.getStat(), selected, 3);
            }
        }
    }                                       
    
    private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {                                        
        if(startvalue==false){
            clock.stop();
            
            startvalue=true;
        }
    }                                       
   
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Project().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> a_hour;
    private javax.swing.JComboBox<String> a_min;
    private javax.swing.JTextField addCapital;
    private javax.swing.JButton addCapitalButton;
    private javax.swing.JButton add_button;
    private javax.swing.JTextField airlines;
    private javax.swing.JButton cancelFlight;
    private javax.swing.JComboBox<String> capitals_combo;
    private javax.swing.JComboBox<String> d_hour;
    private javax.swing.JComboBox<String> d_min;
    private javax.swing.JButton delayButton;
    private javax.swing.JTextField delayText;
    private javax.swing.JButton delet_button;
    private javax.swing.JButton deleteCapitalButton;
    private javax.swing.JButton edit_button;
    private javax.swing.JRadioButton fri_button;
    private javax.swing.JTextField from;
    private javax.swing.JTextField getSystemTime;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton mon_button;
    private javax.swing.JButton permission;
    private javax.swing.JRadioButton sat_button;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JRadioButton sun_button;
    private javax.swing.JLabel systemtimeshow;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tab_1;
    private javax.swing.JPanel tab_2;
    private javax.swing.JTable table;
    private javax.swing.JTable table_fly;
    private javax.swing.JRadioButton thurs_button;
    private javax.swing.JTextField to;
    private javax.swing.JRadioButton tue_button;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel warning2;
    private javax.swing.JRadioButton wed_button;
    // End of variables declaration                   
}

