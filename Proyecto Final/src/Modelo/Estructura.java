/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Estructura {
    public static Conexion miConexion;
    
    public static boolean generarEstructura()
	{
		boolean generada=true;
		String lineaSQL;
		Statement sentencia;
        try{
            lineaSQL = "CREATE TABLE IF NOT EXISTS SOSPECHOSOS"
            + " (dni                char(9) PRIMARY KEY,"
            + " (nombre             varchar(20),"
            + " (primerApellido     varchar(20),"
            + " (segundoApellido     varchar(20))";
            
            sentencia = miConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);
        }catch(SQLException se){
            generada=false;
			se.printStackTrace();
        }
        return generada;
    }
}
