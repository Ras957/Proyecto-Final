/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.GUI;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class Controler {
    
    public static GUI vista;
    
    public static ArrayList<String[]> cargaConfigracion(){
        File f=new File("data.xml");
        ArrayList<String[]> resultado=new ArrayList<>();
        if(f.exists()){
            resultado=CargarXML.getFields(f);
        }
        
        if(resultado.size()==0){
            String[] defaultcol=new String[3];
            defaultcol[0]="Item";
            resultado.add(defaultcol);
        }
        
        
        return resultado;
        
    }
    
    
    
}
