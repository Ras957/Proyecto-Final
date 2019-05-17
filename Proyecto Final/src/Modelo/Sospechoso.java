/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Sospechoso {
    private String nombre;
    private String apellidos;
    private List<Correo> correos;
    private List<Direccion> direcciones;
    private List<Telefono> telefonos;
    private List<Sospechoso> acompañantes;
    private List<Matricula> matriculas;
    private List<Antecedentes> antecedentes;
    private List<Hechos> hechos;
    private List<Foto> fotos;
    
    
    
    public Sospechoso(String nombre, String apellidos, List<Correo> correos, List<Direccion> direcciones, 
            List<Telefono> telefonos, List<Sospechoso> acompañantes, List<Matricula> matriculas, 
            List<Antecedentes> antecedentes, List<Hechos> hechos, List<Foto> fotos){
        this.correos = correos;
        this.direcciones = direcciones;
        this.telefonos = telefonos;
        this.acompañantes = acompañantes;
        this.matriculas = matriculas;
        this.antecedentes = antecedentes;
        this.hechos = hechos;
        this.fotos = fotos;
        this.nombre = nombre;
        this.apellidos = apellidos;
        
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the correos
     */
    public List<Correo> getCorreos() {
        return correos;
    }

    /**
     * @param correos the correos to set
     */
    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    /**
     * @return the direcciones
     */
    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    /**
     * @param direcciones the direcciones to set
     */
    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    /**
     * @return the telefonos
     */
    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    /**
     * @param telefonos the telefonos to set
     */
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * @return the acompañantes
     */
    public List<Sospechoso> getAcompañantes() {
        return acompañantes;
    }

    /**
     * @param acompañantes the acompañantes to set
     */
    public void setAcompañantes(List<Sospechoso> acompañantes) {
        this.acompañantes = acompañantes;
    }

    /**
     * @return the matriculas
     */
    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the matriculas to set
     */
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * @return the antecedentes
     */
    public List<Antecedentes> getAntecedentes() {
        return antecedentes;
    }

    /**
     * @param antecedentes the antecedentes to set
     */
    public void setAntecedentes(List<Antecedentes> antecedentes) {
        this.antecedentes = antecedentes;
    }

    /**
     * @return the hechos
     */
    public List<Hechos> getHechos() {
        return hechos;
    }

    /**
     * @param hechos the hechos to set
     */
    public void setHechos(List<Hechos> hechos) {
        this.hechos = hechos;
    }

    /**
     * @return the fotos
     */
    public List<Foto> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
}
