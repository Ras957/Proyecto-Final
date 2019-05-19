/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Samuel Osuna Alcaide
 */
public class Estructura {

    private static Conexion myConexion;

    public static ArrayList<Sospechoso> listarSospechosos() throws SQLException, IOException {
        ArrayList<Sospechoso> sospechosos = new ArrayList<>();
       
        String lineaSQL;
        
        Statement sentencia;

        lineaSQL = "Select sos.nombre, sos.apellidos, te.telefono, te.tipo, co.email, di.direccion, he.descripcion hechos, an.descripcion antecedentes, ve.matricula"
                + "  from sospechoso sos, telefono te, correos co, direccion di, hechos he, antecedentes an, vehiculo ve, acompanya aco"
                + "  where sos.Id=ve.id_Sospechoso"
                + "  and sos.Id=te.id_Sospechoso "
                + "  and sos.Id=co.id_Sospechoso"
                + "  and sos.id=di.id_Sospechoso"
                + "  and sos.id=he.id_Sospechoso"
                + "  and sos.id=an.id_Sospechoso"
                + "  and sos.id=aco.id_Sospechoso1;";

        
        sentencia = myConexion.getConexion().createStatement();
        
        ResultSet rs =sentencia.executeQuery(lineaSQL);
        while(rs.next()){
            int id=rs.getInt("id_sospechoso");
            HashSet<Correo> correos = new HashSet<>();
            HashSet<Direccion> direcciones = new HashSet<>();
            HashSet<Telefono> telefonos = new HashSet<>();
            HashSet<Sospechoso> sospecho = new HashSet<>();
            HashSet<Matricula> matriculas = new HashSet<>();
            HashSet<Antecedentes> antecedentes = new HashSet<>();
            HashSet<Hechos> hechos = new HashSet<>();
            HashSet<Foto> fotos = new HashSet<>();
            
        
            rs.next();

            do{
                if(rs.getInt("id_sospechoso")!=id){
                    rs.previous();
                    Sospechoso nuevo=new Sospechoso(rs.getString("nombre"),rs.getString("apellidos") ,
                    new ArrayList<>(correos),new ArrayList<>(direcciones),new ArrayList<>(telefonos),
                    new ArrayList<>(sospecho),new ArrayList<>(matriculas),new ArrayList<>(antecedentes),
                    new ArrayList<>(hechos),new ArrayList<>(fotos));
                    
                    sospechosos.add(nuevo);
                }else{
                    //hay que recorrer las filas pertenecientes al mismo sujeto creando los arraylist correspondientes.
                    correos.add(new Correo(rs.getString("email")));
                    direcciones.add(new Direccion(rs.getString("direccion")));
                    telefonos.add(new Telefono(rs.getInt("telefono"),rs.getString("tipo")));
                    matriculas.add(new Matricula(rs.getString("matricula")));
                    antecedentes.add(new Antecedentes(rs.getString("antecedentes")));
                    hechos.add(new Hechos(rs.getString("hechos")));
                }
            }while(rs.next());



        }
        return sospechosos;

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

        
        PreparedStatement preparedStmt = myConexion.getConexion().prepareStatement(lineaSQL);
        
        sentencia = myConexion.getConexion().createStatement();
       
			 preparedStmt.setInt(1, sos.getCodigo());
			 preparedStmt.setInt(2, sos.getCodigo());
			 preparedStmt.setInt(3, sos.getCodigo());
			 preparedStmt.setInt(4, sos.getCodigo());
			 preparedStmt.setInt(5, sos.getCodigo());
                           
                         preparedStmt.execute();
        
        ResultSet rs =sentencia.executeQuery(lineaSQL);
        while(rs.next()){
            int id=rs.getInt("id_sospechoso");
            HashSet<Correo> correos = new HashSet<>();
            HashSet<Direccion> direcciones = new HashSet<>();
            HashSet<Telefono> telefonos = new HashSet<>();
            HashSet<Sospechoso> sospecho = new HashSet<>();
            HashSet<Matricula> matriculas = new HashSet<>();
            HashSet<Antecedentes> antecedentes = new HashSet<>();
            HashSet<Hechos> hechos = new HashSet<>();
            HashSet<Foto> fotos = new HashSet<>();
            
        
            rs.next();

            do{
                if(rs.getInt("id_sospechoso")!=id){
                    rs.previous();
                    Sospechoso nuevo=new Sospechoso(rs.getString("nombre"),rs.getString("apellidos") ,
                    new ArrayList<>(correos),new ArrayList<>(direcciones),new ArrayList<>(telefonos),
                    new ArrayList<>(sospecho),new ArrayList<>(matriculas),new ArrayList<>(antecedentes),
                    new ArrayList<>(hechos),new ArrayList<>(fotos));
                    
                    sospechosos.add(nuevo);
                }else{
                    //hay que recorrer las filas pertenecientes al mismo sujeto creando los arraylist correspondientes.
                    correos.add(new Correo(rs.getString("email")));
                    direcciones.add(new Direccion(rs.getString("direccion")));
                    telefonos.add(new Telefono(rs.getInt("telefono"),rs.getString("tipo")));
                    matriculas.add(new Matricula(rs.getString("matricula")));
                    antecedentes.add(new Antecedentes(rs.getString("antecedentes")));
                    hechos.add(new Hechos(rs.getString("hechos")));
                }
            }while(rs.next());

        
        
        }
        return sospechosos;
    }

    public static boolean generarEstructura() {
        boolean generada = true;
        String lineaSQL;
        Statement sentencia;
        try {
            lineaSQL = "CREATE TABLE IF NOT EXISTS SOSPECHOSOS"
                    + " (Id                char(9) PRIMARY KEY,"
                    + " nombre             varchar(20),"
                    + " primerApellido     varchar(20),"
                    + " segundoApellido     varchar(20))";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "CREATE TABLE IF NOT EXISTS ACOMPANYA"
                    + " (Id_Sospechoso1                int(8) PRIMARY KEY,"
                    + " Id_Sospechoso             int(8),"
                    + " foreign key (id_Sospechoso1) references Sospechoso(Id) on delete cascade,"
                    + " foreign key (id_Sospechoso2) references Sospechoso(Id) on delete cascade";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Telefono("
                    + "    Telefono int(10),"
                    + "    Tipo varchar(20),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade);";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Antecedentes("
                    + "    Descripcion varchar(200),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ");";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Correos("
                    + "    email char(50),"
                    + "    id_Sospechoso int(8),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ");";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Direccion("
                    + "    Direccion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ");";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Hechos("
                    + "    Descripcion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ");";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

            lineaSQL = "Create table Fotos("
                    + "    Descripcion varchar(200),"
                    + "    id int(8) AUTO_INCREMENT,"
                    + "    imagen mediumblob,"
                    + "    id_Sospechoso int(8),"
                    + "    Primary key(id),"
                    + "    foreign key (id_Sospechoso) references Sospechoso(Id) on delete cascade"
                    + ");";

            sentencia = myConexion.getConexion().createStatement();
            sentencia.executeUpdate(lineaSQL);

        } catch (SQLException se) {
            generada = false;
            se.printStackTrace();
        }
        return generada;
    }
}
