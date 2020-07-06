package project_2;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Projectv1 Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Murphy
 */
public class Flight extends Thread{
    private int flightNumber;
    private String airlines;
    private String days;
    private String from;
    private String to;
    public Calendar d_time = Calendar.getInstance();
    public Calendar a_time = Calendar.getInstance();
    private String stat ="onGround";
    public boolean isDepart = false;
    public boolean isArriv = false;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public void run() {
       while(true){
           
           while(!this.isDepart){
               if((d_time.get(Calendar.HOUR_OF_DAY)==Project.systemtime.get(Calendar.HOUR_OF_DAY))&&(d_time.get(Calendar.MINUTE)==Project.systemtime.get(Calendar.MINUTE))&&this.getStat().equals("onGround")){
                   this.isDepart=true;
                   this.setStat("isFlying");
                   
               }
               try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
           while(!this.isArriv){
               if(a_time.get(Calendar.HOUR_OF_DAY)==Project.systemtime.get(Calendar.HOUR_OF_DAY)&&a_time.get(Calendar.MINUTE)==Project.systemtime.get(Calendar.MINUTE)&&this.stat.equals("isFlying")){
                   this.isArriv=true;
                   this.setStat("AskingPermission");
                   
               }
               try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    }
    
    
    

    public Flight(int flightNumber, String airlines, String days, String from, String to, Calendar d_time, Calendar a_time) {
        this.flightNumber = flightNumber;
        this.airlines = airlines;
        this.days = days;
        this.from = from;
        this.to = to;
        this.d_time = d_time;
        this.a_time = a_time;
    }

    
    
    
    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Calendar getD_time() {
        return d_time;
    }

    public void setD_time(Calendar d_time) {
        this.d_time = d_time;
    }

    public Calendar getA_time() {
        return a_time;
    }

    public void setA_time(Calendar a_time) {
        this.a_time = a_time;
    }

   
    
    
    
    
    
}

