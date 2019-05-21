/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samuel Osuna Alcaide
 */
public class Estructura {

    private static Conexion myConexion;

    public static ArrayList<Sospechoso> listarSospechosos() throws SQLException, IOException {
        ArrayList<Sospechoso> sospechosos = new ArrayList<>();
       

        String lineaSQL = "Select sos.id_sospechoso, sos.nombre, sos.apellidos, te.telefono, te.tipo, co.email, di.direccion, he.descripcion hechos, an.descripcion antecedentes, ve.matricula, aco.id_Sospechoso2"
                + "  from sospechoso sos, telefono te, correos co, direccion di, hechos he, antecedentes an, vehiculo ve, acompanya aco"
                + "  where sos.Id=ve.id_Sospechoso"
                + "  and sos.Id=te.id_Sospechoso "
                + "  and sos.Id=co.id_Sospechoso"
                + "  and sos.id=di.id_Sospechoso"
                + "  and sos.id=he.id_Sospechoso"
                + "  and sos.id=an.id_Sospechoso"
                + "  and sos.id=aco.id_Sospechoso1;";

        
        PreparedStatement conexion = myConexion.getConexion().prepareStatement(lineaSQL);
        
        ResultSet rs = conexion.executeQuery();
        
        /*ITERAMOS UNA VEZ*/
        HashSet<Correo> correos=new HashSet<>();
        HashSet<Direccion> direcciones=new HashSet<>();
        HashSet<Telefono> telefonos=new HashSet<>();
        HashSet<Sospechoso> acompanyantes=new HashSet<>();
        HashSet<Matricula> matriculas=new HashSet<>();
        HashSet<Antecedentes> antecedentes=new HashSet<>();
        HashSet<Hechos> hechos=new HashSet<>();
        HashSet<Foto> fotos=new HashSet<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            

            do {
               // System.out.println(rs.getInt("id"));

                if (rs.getInt("id") != id) {
                    System.out.println(rs.getInt("id") + "vs" + id);
                    rs.previous();
                    Sospechoso nuevo = new Sospechoso(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Apellidos"),new ArrayList<> (correos), new ArrayList<> (direcciones), 
                    new ArrayList<> (telefonos), new ArrayList<> (acompanyantes), new ArrayList<> (matriculas), 
                    new ArrayList<> (antecedentes), new ArrayList<> (hechos), new ArrayList<> (fotos));
                    sospechosos.add(nuevo);

                    rs.next();
                    id = rs.getInt("id");
                    correos = new HashSet<>();
                    direcciones=new HashSet<>();
                    telefonos=new HashSet<>();
                    acompanyantes=new HashSet<>();
                    matriculas = new HashSet<>();
                    antecedentes=new HashSet<>();
                    hechos=new HashSet<>();
                    fotos=new HashSet<>();
                    
                    rs.previous();
                } else {
                    //hay que recoorrer las filas pertenecientes al mismo sujeto creando los arraylist correspondientes.
                    correos.add(new Correo(rs.getString("email")));
                    direcciones.add(new Direccion(rs.getString("Direccion")));
                    telefonos.add(new Telefono(rs.getInt("Telefono")));
                    acompanyantes.add(new Sospechoso(rs.getInt("id_Sospechoso2")));
                    matriculas.add(new Matricula(rs.getString("Matricula")));
                    antecedentes.add(new Antecedentes(rs.getString("antecedentes")));
                    hechos.add(new Hechos(rs.getString("hechos")));
                    //fotos.add(new Foto(rs.getString("fotos")));
                }
            } while (rs.next());



        }
            //insert last one  //creo que va antes del return 
            rs.previous();
            Sospechoso nuevo = new Sospechoso(rs.getInt("id"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"), new ArrayList<> (correos), new ArrayList<> (direcciones), 
                    new ArrayList<> (telefonos), new ArrayList<> (acompanyantes), new ArrayList<> (matriculas), 
                    new ArrayList<> (antecedentes), new ArrayList<> (hechos), new ArrayList<> (fotos));
            sospechosos.add(nuevo);
        return sospechosos;

    }
    
    public static void mostrarTabla(DefaultTableModel m, ArrayList<Sospechoso> sos){
        int size=sos.size();
        
        for(int i=0;i<size;i++){
            m.addRow(new Object[]{sos.get(i).getCodigo(),sos.get(i).getNombre(),sos.get(i).getApellidos()});
        }
    }
    
    public static String mostrarTelefonos(Sospechoso sos){
       String devolver = null;
       int sizeTelefonos=sos.getTelefonos().size();
       for(int i=0;i<sizeTelefonos;i++){
           int tel=sos.getTelefonos().get(i).getTelefono();
           String cadena=Integer.toString(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    
    public static String mostrarCorreos(Sospechoso sos){
       String devolver = null;
       int sizelist=sos.getCorreos().size();
       for(int i=0;i<sizelist;i++){
           String tel=sos.getCorreos().get(i).getEmail();
           String cadena=(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    
    public static String mostrarMatricula(Sospechoso sos){
       String devolver = null;
       int sizelist=sos.getMatriculas().size();
       for(int i=0;i<sizelist;i++){
           String tel=sos.getMatriculas().get(i).getCodigo();
           String cadena=(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    public static String mostrarDireccion(Sospechoso sos){
       String devolver = null;
       int sizelist=sos.getDirecciones().size();
       for(int i=0;i<sizelist;i++){
           String tel=sos.getDirecciones().get(i).getDomicilio();
           String cadena=(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    public static String mostrarAntecedentes(Sospechoso sos){
       String devolver = null;
       int sizelist=sos.getAntecedentes().size();
       for(int i=0;i<sizelist;i++){
           String tel=sos.getAntecedentes().get(i).getAntecedentes();
           String cadena=(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    public static String mostrarHechos(Sospechoso sos){
       String devolver = null;
       int sizelist=sos.getHechos().size();
       for(int i=0;i<sizelist;i++){
           String tel=sos.getHechos().get(i).getHechos();
           String cadena=(tel);
           devolver+=cadena+"\n";
       }
      return devolver;
    }
    
    public static ArrayList<Sospechoso> buscarSospechosos(Sospechoso sos) throws SQLException {
        ArrayList<Sospechoso> sospechosos = new ArrayList<>();
       
        String lineaSQL;
        
        Statement sentencia;
        
        //Usa el id del sospechoso introducido para buscar coincidencias.
        lineaSQL = "Select sos.nombre, sos.apellidos, te.numero, co.email, di.descripcion, he.descripcion, an.descripcion, ve.matricula"
                + "  from sospechoso sos, telefono te, correos co, direccion di, hechos he, antecedentes an, vehiculo ve"
                + "  where (Select te.numero from telefono te, sospechoso sos where ?=te.id_Sospechoso)=te.numero"
                + "  or (Select co.email from correos co, sospechoso sos where ?=co.id_Sospechoso)=co.email "
                + "  or (Select di.descripcion from direccion di, sospechoso sos where ?=di.id_Sospechoso)=di.descripcion"
                + "  or (Select ve.matricula from vehiculo ve, sospechoso sos where ?=ve.id_Sospechoso)=ve.matricula"
                + "  or ?=aco.Id_Sospechoso2;";

        
        PreparedStatement conexion = myConexion.getConexion().prepareStatement(lineaSQL);
        
        ResultSet rs = conexion.executeQuery();
        
        /*ITERAMOS UNA VEZ*/
        HashSet<Correo> correos=new HashSet<>();
        HashSet<Direccion> direcciones=new HashSet<>();
        HashSet<Telefono> telefonos=new HashSet<>();
        HashSet<Sospechoso> acompanyantes=new HashSet<>();
        HashSet<Matricula> matriculas=new HashSet<>();
        HashSet<Antecedentes> antecedentes=new HashSet<>();
        HashSet<Hechos> hechos=new HashSet<>();
        HashSet<Foto> fotos=new HashSet<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            

            do {
               // System.out.println(rs.getInt("id"));

                if (rs.getInt("id") != id) {
                    System.out.println(rs.getInt("id") + "vs" + id);
                    rs.previous();
                    Sospechoso nuevo = new Sospechoso(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Apellidos"),new ArrayList<> (correos), new ArrayList<> (direcciones), 
                    new ArrayList<> (telefonos), new ArrayList<> (acompanyantes), new ArrayList<> (matriculas), 
                    new ArrayList<> (antecedentes), new ArrayList<> (hechos), new ArrayList<> (fotos));
                    sospechosos.add(nuevo);

                    rs.next();
                    id = rs.getInt("id");
                    correos = new HashSet<>();
                    direcciones=new HashSet<>();
                    telefonos=new HashSet<>();
                    acompanyantes=new HashSet<>();
                    matriculas = new HashSet<>();
                    antecedentes=new HashSet<>();
                    hechos=new HashSet<>();
                    fotos=new HashSet<>();
                    
                    rs.previous();
                } else {
                    //hay que recoorrer las filas pertenecientes al mismo sujeto creando los arraylist correspondientes.
                    correos.add(new Correo(rs.getString("email")));
                    direcciones.add(new Direccion(rs.getString("Direccion")));
                    telefonos.add(new Telefono(rs.getInt("Telefono")));
                    acompanyantes.add(new Sospechoso(rs.getInt("id_Sospechoso2")));
                    matriculas.add(new Matricula(rs.getString("Matricula")));
                    antecedentes.add(new Antecedentes(rs.getString("antecedentes")));
                    hechos.add(new Hechos(rs.getString("hechos")));
                    //fotos.add(new Foto(rs.getString("fotos")));
                }
            } while (rs.next());



        }
            //insert last one  //creo que va antes del return 
            rs.previous();
            Sospechoso nuevo = new Sospechoso(rs.getInt("id"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"), new ArrayList<> (correos), new ArrayList<> (direcciones), 
                    new ArrayList<> (telefonos), new ArrayList<> (acompanyantes), new ArrayList<> (matriculas), 
                    new ArrayList<> (antecedentes), new ArrayList<> (hechos), new ArrayList<> (fotos));
            sospechosos.add(nuevo);
        return sospechosos;
    }

    public static boolean generarEstructura() {
        boolean generada = true;
        String lineaSQL;
        Statement sentencia;
        try {
            lineaSQL = "CREATE TABLE IF NOT EXISTS SOSPECHOSOS"
                    + " (Id                int(9) AUTO_INCREMENT,"
                    + " nombre             varchar(20),"
                    + " primerApellido     varchar(20),"
                    + " segundoApellido     varchar(20),"
                    + " PRIMARY KEY(Id))";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS ACOMPANYA"
                    + " (Id_Sospechoso1           int(8) PRIMARY KEY,"
                    + " Id_Sospechoso             int(8),"
                    + " foreign key (id_Sospechoso1) references Sospechoso(Id) on delete cascade,"
                    + " foreign key (id_Sospechoso2) references Sospechoso(Id) on delete cascade";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Telefono("
                    + "    Telefono int(10),"
                    + "    Tipo varchar(20),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade)";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Antecedentes("
                    + "    Descripcion varchar(200),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ")";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Correos("
                    + "    email char(50),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ")";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Direccion("
                    + "    Direccion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ")";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Hechos("
                    + "    Descripcion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ")";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS Fotos("
                    + "    Descripcion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    imagen mediumblob,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ")";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);
            
            lineaSQL = "CREATE TABLE IF NOT EXISTS Vehiculo("
                    + "    Matricula char(10),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "PRIMARY KEY (id, id_Sospechoso),"
                    + "FOREIGN KEY(ID_Sospechoso) REFERENCES Sospechosos(Id))";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

        } catch (SQLException se) {
            generada = false;
            se.printStackTrace();
        }
        return generada;
    }
    
    public static boolean insertarSospechoso(String nombre, String apellidos) throws SQLException{
        boolean devuelve=false;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "INSERT INTO sospechoso(nombre,apellidos) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, nombre);
			 preparedStmt.setString(2, apellidos);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarVehiculo(String matricula, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "insert into vehiculo (matricula, id_Sospechoso) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, matricula);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarCorreo(String email, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "intert into correo(email,id_Sospechoso) values (?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, email);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarDireccion(String direccion, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "intert into direccion(direccion,id_Sospechoso) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, direccion);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarTelefono(int telefono, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "insert into telefono(telefono,id_Sospechoso) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setInt(1, telefono);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarAntecedentes(String desc, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "insert into antecedentes(descripcion, id_Sospechoso) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, desc);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarHechos(String desc, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "insert into hechos(descripcion,id_Sospechoso) values(?,?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, desc);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static boolean insertarFotos(String imagen, int id) throws SQLException{
        boolean devuelve;
                
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "insert into fotos(imagen, id_sospechoso) values (?, ?);";

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setString(1, imagen);
			 preparedStmt.setInt(2, id);
			 
                           
                         preparedStmt.execute();
                         devuelve=true;
        return devuelve;        
    }
    
    public static int ultimoSospechoso() throws SQLException{
        int ultimoid;
        
        String lineaSQL;
        

        lineaSQL = "Select MAX(Id) AS id from Sospechosos";

        
        PreparedStatement conexion = myConexion.getConexion().prepareStatement(lineaSQL);
        
        ResultSet rs = conexion.executeQuery();
       
			 ultimoid=rs.getInt("id");
          
        return ultimoid;
    }

    public static int borrarSospechoso(int codigo){
        String lineaSQL;
		//Objeto de tipo Statement
		PreparedStatement preparedStmt;
		//Número de filas actualizadas
		int nFilas=0;
		
		
		//Cadena update
		lineaSQL="delete from Sospechosos where codigoSocio = ?";
		try
		{		
		
			//conectamos el objeto preparedStmt a la base de datos
			preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
			
			//agregamos los valores que faltan a la linea SQL
		    preparedStmt.setInt (1, codigo);
		
			// la ejecutamos
			nFilas=preparedStmt.executeUpdate();
		       
			// habría que cerrar la conexion
		}catch(SQLException se)
		{
		
			se.printStackTrace();
		}
		
		return nFilas;
    }
    
    public static Sospechoso buscarSospechosoId(int id) throws SQLException, IOException{
        ArrayList<Sospechoso> sos=new ArrayList<>();
        Sospechoso devolver = null;
        sos=listarSospechosos();
        for(int i=0;i<sos.size();i++){
            if(sos.get(i).getCodigo()==id){
                devolver=sos.get(i);
            }
        }
        return devolver;
    }
    
    public static void insertarTelefono(String telefono, int ultimoSospechoso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
