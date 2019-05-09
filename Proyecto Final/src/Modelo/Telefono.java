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
        String Telefono;
        boolean Tipo;
        public Telefono(String Telefono, boolean Tipo){
		this.Telefono=Telefono;
		this.Tipo=Tipo;
	}

        public String getTelefono(){
		return this.Telefono;

	}
        public boolean getTipo(){
		return this.Tipo;

	}
        public void setTelefono(String Telefono){
		this.Telefono=Telefono;

	}
        public void setTipo(boolean Tipo){
		this.Tipo=Tipo;

	}
        
}