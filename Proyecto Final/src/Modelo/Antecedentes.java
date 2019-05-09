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
public class Antecedentes {
    private String antecedentes;
    
    public Antecedentes(String antecedente){
        this.antecedentes=antecedente;
    }
    
    public void setAntecedentes(String antecedente){
        this.antecedentes=antecedente;
    }
    
    public String getAntecedentes(){
        return this.antecedentes;
    }
}
