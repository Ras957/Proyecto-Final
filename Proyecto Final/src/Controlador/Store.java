/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author danie
 */
public class Store implements Serializable {

    public HashMap<String, String[]> stockedItems = new HashMap<>();

    public Store() {
    }

    public Store(String[] codes) {
        String[] ddefault = {""};
        for (String code : codes) {
            addItem(code, ddefault);
        }
    }

    public boolean addItem(String code, String[] value) {

        if (stockedItems.containsKey(code)) {
            stockedItems.replace(code, value);
        } else {
            stockedItems.put(code, value);
        }

        return true;

    }

    public boolean removeItem(String code) {

        if (stockedItems.containsKey(code)) {

            stockedItems.remove(code);

            return true;

        }

        return false;

    }

    public boolean findItem(String code) {

        return stockedItems.containsKey(code);

    }
    public String toString(){
       StringBuilder s=new StringBuilder();
       stockedItems.forEach((k,v)->{
           s.append("key: "+k+" values: ");
           for(String value:v){
               s.append(value+" | ");
           }
           s.append("\n");
       });
       return s.toString();
    }

}
