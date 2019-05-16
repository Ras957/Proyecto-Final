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
 * @author Samuel Osuna Alcaide
 */
public class Estructura {
    private static Conexion myConexion;
    public static String listarSospechosos() throws SQLException
	{
		String devuelve = null;
		//Cadena donde irán las sentencias sql de creación de tablas
		String lineaSQL;
		//Objeto de tipo Statement
		Statement sentencia;
		
		
			lineaSQL = "Select sos.nombre, sos.apellidos, te.telefono, co.email, di.direccion, he.descripcion, an.descripcion, ve.matricula"
            + "  from sospechoso sos, telefono te, correos co, direccion di, hechos he, antecedentes an, vehiculo ve, acompanya aco"
            + "   where sos.Id=ve.id_Sospechoso"
            + "   and sos.Id=te.id_Sospechoso "
            + "   and sos.Id=co.id_Sospechoso"
            + "   and sos.id=di.id_Sospechoso"
            + "   and sos.id=he.id_Sospechoso"
            + "   and sos.id=an.id_Sospechoso"
            + "   and sos.id=aco.id_Sospechoso1";
          

			//conectamos la sentencia a la base de datos
			sentencia = myConexion.getConexion().createStatement();
			//ejecutamos la sentencia;
			sentencia.executeUpdate(lineaSQL);
			
			//HAY QUE REPETIR ESTA OPERACIÓN PARA CADA UNA DE LAS TABLAS
			
			
			
	
		
		return devuelve;
		
	}
    
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
            
            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);
        }catch(SQLException se){
            generada=false;
			se.printStackTrace();
        }
        return generada;
    }
}



