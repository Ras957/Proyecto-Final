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
public class Telefono {
        int Telefono;
        String Tipo;
        
        public Telefono(int Telefono){
		this.Telefono=Telefono;
		this.Tipo="";
	}
        
        public Telefono(int Telefono, String Tipo){
		this.Telefono=Telefono;
		this.Tipo=Tipo;
	}

        public int getTelefono(){
		return this.Telefono;

	}
        public String getTipo(){
		return this.Tipo;

	}
        public void setTelefono(int Telefono){
		this.Telefono=Telefono;

	}
        public void setTipo(String Tipo){
		this.Tipo=Tipo;

	}
        
}
