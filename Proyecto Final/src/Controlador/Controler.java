/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.GUI;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    public static void guardar() {
        JTable jTable1 = vista.getTable();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        Store myStore = new Store();
        int cols = jTable1.getColumnCount();
        for (int i = 0; i < cols; i++) {
            String namecol = modelo.getColumnName(i);
            String[] data = new String[modelo.getRowCount()];
            for (int j = 0; j < modelo.getRowCount(); j++) {
                data[j] = (String) ((Vector) modelo.getDataVector().elementAt(j)).elementAt(i);
            }
            myStore.addItem(namecol, data);
        }

        

    }

    
    
    
    
}