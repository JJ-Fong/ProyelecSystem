/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.dbmanagement;

/**
 *
 * @author Work
 */
public class dateManager {
    
    public dateManager(){}
    
    public boolean checkSintax(String date) {
        boolean flag = true;
        if(date.length() == 10){
            try {
                int d,m,y; 
                char s1, s2; 
                d = Integer.parseInt(date.substring(0,2)); 
                m = Integer.parseInt(date.substring(3,5)); 
                y = Integer.parseInt(date.substring(6,10));
                s1 = date.charAt(2);
                s2 = date.charAt(5); 
                if (((s1 != '-')&&(s1 != '/'))||((s2 != '-')&&(s2 != '/'))) {
                    flag = false; 
                }
            } catch (Exception e) {
                flag = false; 
            }
        } else {
            flag = false; 
        }
        return flag; 
    }
    
    public boolean defensive(String date){
        boolean flag = true;
        flag = checkSintax(date); 
        if (flag) {
            int d = Integer.parseInt(date.substring(0,2)); 
            int m = Integer.parseInt(date.substring(3,5)); 
            int y = Integer.parseInt(date.substring(6,10));
            if ((d > 31) || (m > 12)) {
                flag = false; 
            }
            switch (m) {
                case (1): if (d > 31) flag = false;  
                break;
                case (3): if (d > 31) flag = false;  
                break;
                case (4): if (d > 30) flag = false;  
                break;
                case (5): if (d > 31) flag = false;  
                break;
                case (6): if (d > 30) flag = false;  
                break;
                case (7): if (d > 31) flag = false;  
                break;
                case (8): if (d > 31) flag = false;  
                break;
                case (9): if (d > 30) flag = false;  
                break;
                case (10): if (d > 31) flag = false;  
                break;
                case (11): if (d > 30) flag = false;  
                break;
                case (12): if (d > 31) flag = false;  
                break;
            }
            if (m == 2) {
                if (aBisiesto(y)) {
                    if ( d > 29 ) flag = false; 
                } else {
                    if ( d > 28 ) flag = false;
                }
            }
        }
        return flag; 
    }
    
    public boolean aBisiesto(int year) {
        boolean flag = false; 
        int cuatro = year % 4; 
        int cien = year % 100; 
        int ccientos = year % 400; 
        boolean c1 = (cuatro == 0); 
        boolean c2 = (cien == 0); 
        boolean c3 = (ccientos == 0); 
        int bin = 0; 
        if (c1) bin += 4; 
        if (c2) bin += 2; 
        if (c3) bin += 1; 
        switch(bin) {
            case (0): flag = false; break; 
            case (1): flag = true; break; 
            case (2): flag = false; break; 
            case (3): flag = true; break; 
            case (4): flag = true; break; 
            case (5): flag = true; break; 
            case (6): flag = false; break; 
            case (7): flag = true; break; 
        }
        return flag; 
    }
    
    public int[] byPart(String date) {
        int[] fecha = new int[] {0,0,0}; 
        if (defensive(date)) {
            fecha[0] = Integer.parseInt(date.substring(0,2)); 
            fecha[1] = Integer.parseInt(date.substring(3,5)); 
            fecha[2] = Integer.parseInt(date.substring(6,10));
        }
        return fecha; 
    }
    
    public int dateToDays(String date) {
        int days = 0;
        int[] daysByMonth = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
        int[] daysByBMonth = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};
        int[] year; 
        if (defensive(date)) {
            int d = Integer.parseInt(date.substring(0,2)); 
            int m = Integer.parseInt(date.substring(3,5)); 
            int y = Integer.parseInt(date.substring(6,10));
            
            if (aBisiesto(y)) {
                year = daysByBMonth; 
            } else {
                year = daysByMonth; 
            }
            
            days += d; 
            days += ((y-1) * 365); 
            
            for ( int i = 0; i < ( m - 1 ); i++ ) {
                days += year[i]; 
            }
        } else {
            days = -1 ;
        }
        return days; 
    }
    
    public int compare(String date1, String date2){
        int flag = 0; 
        if (defensive(date1)&&defensive(date2)) {
            int days1 = dateToDays(date1); 
            int days2 = dateToDays(date2); 
            if (days1 > days2) {
                flag = -1; 
            }
            if (days1 < days2) {
                flag = 1; 
            }
        }
        return flag; 
    }
    
    public int diff(String date1, String date2) {
        int days = 0; 
        int d = compare (date1,date2); 
        if (d == 1) {
            days = dateToDays(date2) - dateToDays(date1); 
        }
        if (d == -1) {
            days = dateToDays(date1) - dateToDays(date2); 
        }
        
        return days; 
    }
    
}
