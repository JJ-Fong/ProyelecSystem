/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.entities;

/**
 *
 * @author Javier Fong
 */
public class Worker {
    int id;
    String nombre;
    String dpi;
    String banco;
    String cuenta;
    double sueldo; 

    public Worker() {
    }

    public Worker(int id, String nombre, String dpi, String banco, String cuenta, double sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.dpi = dpi;
        this.banco = banco;
        this.cuenta = cuenta;
        this.sueldo = sueldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    
}
