/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.*; 
import java.io.File;
import java.io.IOException; 
import java.io.OutputStream; 
import java.io.Writer; 

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
        public void codify(String nombre, String ruta) throws FileNotFoundException, IOException{
        
            File file = new File("inicial.jpg"); 
            BufferedInputStream bufferis = new BufferedInputStream(new 
            FileInputStream(file)); 
            int bytes = (int) file.length(); 
            byte[] buffer = new byte[bytes]; 
            int readBytes = bufferis.read(buffer); 
            bufferis.close(); 
            String encodedString = Base64.encode(buffer); 
          
        }
    
    }
