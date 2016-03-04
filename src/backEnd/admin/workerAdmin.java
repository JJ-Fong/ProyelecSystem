/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.admin;

import backEnd.dbmanagement.DBManager;
import backEnd.entities.Worker;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Javier Fong
 */
public class workerAdmin {
    DBManager dbm;
    Statement stm; 
    
    public workerAdmin(){
        dbm = new  DBManager(); 
        dbm.conectar();
        stm = dbm.getStm();
    }
    
    public ArrayList getAllWorkers() {
        ArrayList list = new ArrayList(); 
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM planilla");
            while (rs.next()) {
                Worker wrk = new Worker();
                wrk.setId(Integer.parseInt(rs.getString(1)));
                wrk.setNombre(rs.getString(2));
                wrk.setDpi(rs.getString(3));
                wrk.setBanco(rs.getString(4));
                wrk.setCuenta(rs.getString(5));
                wrk.setSueldo(Double.parseDouble(rs.getString(6)));
                list.add(wrk);
            }
        } catch (Exception e) {
            System.out.println("Worker info unreachable");
        }
        return list; 
    }
    
    public boolean existByName(String name){
        boolean flag = false;
        try { 
            ResultSet rs = stm.executeQuery("SELECT * FROM planilla WHERE nombre = \""+name+"\"");
            if (rs.next()) {
                flag = true; 
            }
        } catch (Exception e) {
            System.out.println("Error in  workerAdmin.existByName"); 
        }
        return flag; 
    }
    
    public void deleteWorker(int id) {
        dbm.borrar("planilla", "colabid", String.valueOf(id));
    }
    
    public void newWorker(Worker wrk){
        String values = "null,\""+wrk.getNombre()+"\",\""+wrk.getDpi()+"\",\""+wrk.getBanco()+"\",\""+wrk.getCuenta()+"\",\""+wrk.getSueldo()+"\"";
        dbm.insertar("planilla", values);
    }
    
    public void modWorker(Worker wrk){
        String values = "colabid = "+wrk.getId()+",nombre =\""+wrk.getNombre()+"\",dpi = \""+wrk.getDpi()+"\",banco = \""+wrk.getBanco()+"\",cuenta = \""+wrk.getCuenta()+"\",sueldo = \""+wrk.getSueldo()+"\"";
        try { 
            stm.executeUpdate("UPDATE planilla SET "+values+" WHERE colabid = \""+wrk.getId()+"\"");
        } catch (Exception e) {
            System.out.println("Modification not made");
        }
    }
}
