/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.File;
/**
 *
 * @author Zuly
 */
    public class Foto {
      File Foto;
        public Foto(File Foto){
		this.Foto=Foto;
        }
        public File getFoto(){
		return this.Foto;

	}
        public void setTelefono(File Foto){
		this.Foto=Foto;

	}
    }
