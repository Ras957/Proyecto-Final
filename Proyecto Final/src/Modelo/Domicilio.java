/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Domicilio {
    private String domicilio;
    
    public Domicilio(String d){
        this.domicilio=d;
    }
    
    public void setDomicilio(String d){
        this.domicilio=d;
    }
    
    public String getDomicilio(){
        return this.domicilio;
    }
}
