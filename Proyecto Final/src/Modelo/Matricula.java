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
public class Matricula {
    private String codigo;
    
    public Matricula(String cod){
        this.codigo=cod;
    }
    
    public void setCodigo(String cod){
        this.codigo=cod;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
   }
