/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.admin;

import backEnd.dbmanagement.DBManager;
import backEnd.entities.User;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Javier Fong
 */
public class userAdmin {
    DBManager dbm; 
    Statement stm; 
    
    public userAdmin() {
        dbm = new DBManager();
        dbm.conectar();
        stm = dbm.getStm();
    }
    
    public ArrayList getAllUsers(){
        ArrayList list = new ArrayList(); 
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                User usr = new User(); 
                usr.setId(Integer.parseInt(rs.getString(1)));
                usr.setUsername(rs.getString(2));
                usr.setPassword(rs.getString(3));
                usr.setAdmin(Integer.parseInt(rs.getString(4)));
                list.add(usr);
            }
        } catch (Exception e){
            System.out.println("Users data unreachable");
        }
        return list; 
    }
    
    public boolean verify(int id,String password){
        boolean flag = true; 
        try {
            ResultSet rs = stm.executeQuery("SELECT password FROM user WHERE userid = \""+String.valueOf(id)+"\"");
            if (rs.next()) {
                String pw = rs.getString(1);
                flag = password.equals(pw); 
            }
        } catch (Exception e){
            System.out.println("Users data unreachable");
        }
        return flag;
    }
    
    public User searchUser(int id){
        User usr = new User(); 
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM user WHERE userid = \""+String.valueOf(id)+"\"");
            if (rs.next()) {
                usr.setId(id);
                usr.setUsername(rs.getString(2));
                usr.setPassword(rs.getString(3));
                usr.setAdmin(Integer.parseInt(rs.getString(4)));
            }
        } catch (Exception e){
            System.out.println("Users data unreachable");
            usr = null;
        }
        return usr; 
    }
}
