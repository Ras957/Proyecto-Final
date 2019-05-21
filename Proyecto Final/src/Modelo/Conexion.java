/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Conexion {

    private Connection miConexion;
    private String host; //Host que contiene la BBDD
    private String bbdd; //Nombre de la BBDD
    private String login; //Login 
    private String password; //Password
    private boolean estado = false;//Estado de la conexión

    public Conexion(String host, String bbdd, String login, String password) 
	{
		this.host=host;
		this.bbdd=bbdd;
		this.login=login;
		this.password=password;		
	}
    
    public Conexion() throws ParserConfigurationException, SAXException, IOException {
        File file=new File("bbdd.xml");
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        if (doc.hasChildNodes()) {
            NodeList nodeList = doc
                    .getDocumentElement()
                    .getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    switch (node.getNodeName()) {
                        case "host":
                            this.host = node.getTextContent();
                            break;
                        case "nombre":
                            this.bbdd = node.getTextContent();
                            break;
                        case "login":
                            this.login = node.getTextContent();
                            break;
                        case "password":
                            this.password = node.getTextContent();
                            break;
                    }
                }
            }
        }
    }
    
    public boolean conectar() throws Exception
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
         // Setup the connection with the DB
         
		//conexion nomral
		
		//miConexion= DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.bbdd+"?user="+this.login+"&password="+this.password);
		
		//conexión completa para evitar errores de sincronizacion con el servidor
		miConexion= DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.bbdd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user= "+this.login+"&password=S"+this.password);
		//miConexion = DriverManager.getConnection("jdbc:mysql://localhost/policia", "root", "");
        
		this.estado=true;
		} 
		catch (Exception e) {
			e.printStackTrace();
           }
         
		return this.estado;
	}
	
	/*Método: getConexion()
	Tipo: Connection
	Parámetros: ninguno
	Devuelve: Connection
	Funcionalidad: Devuelve la conexión a la base de datos para poder realizar sentencias
	contra la misma */
	
	public Connection getConexion()
	{
		return miConexion;
	}
	
	/*Método: getEstado()
	Tipo: boolean
	Parámetros: ninguno
	Devuelve: boolean
	Funcionalidad: Devuelve si la conexión está establecida o no*/
	
	public boolean getEstado()
	{
		return estado;
		
	}
	
	/*Método: cerrarConexion()
	Tipo: boolean
	Parámetros: ninguno
	Devuelve: boolean
	Funcionalidad: Devuelve true si ha cerrado la conexión a la BBDD y false en caso de que
	este cierre no se haya podido llevar a cabo*/
	
	public boolean cerrarConexion() throws Exception
	{
		boolean seCerro=false;
		try
		{
			this.miConexion.close();
			seCerro=true;
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		return seCerro;
		
	}
	
	

}//fin de clase




