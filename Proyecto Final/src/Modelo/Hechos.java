/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Samuel Osuna Alcaide
 */
public class Hechos {
    private String hechos;
    
    public Hechos(String hecho){
        this.hechos=hecho;
    }
    
    public void setHechos(String hecho){
        this.hechos=hecho;
    }
    
    public String getHechos(){
        return this.hechos;
    }
}
