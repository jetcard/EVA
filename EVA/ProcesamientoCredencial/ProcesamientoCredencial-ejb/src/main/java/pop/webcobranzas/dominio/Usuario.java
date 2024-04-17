/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dominio;

import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class Usuario {

    private String usuarioId;
    private String contrasenia;
    private String contraseniaB;
    private int numLlamada;
    // persona
    private int perId;
    private String perNom;
    private String perApePat;
    private String perApeMat;
    // telefono
    private Telefono telefono;
    // correo
    private Correo correo;
    // rol
    private Rol rol;
    // menu
    private List<Menu> menu;//PARA MOSTRAR OPCIONES EN MENU
    //
    private boolean estado;
    private String mensaje;
    private String bcontrasenia;
    private boolean eliminarSeguimiento;

    private List<Menu> opcionesPermisosmenu; //QUE OPCIONES TOTALES TIENE EL USUARIO, NO NECESARIAMENTE DEBE MOSTRAR EN MENU

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getNumLlamada() {
        return numLlamada;
    }

    public void setNumLlamada(int numLlamada) {
        this.numLlamada = numLlamada;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getPerNom() {
        return perNom;
    }

    public void setPerNom(String perNom) {
        this.perNom = perNom;
    }

    public String getPerApePat() {
        return perApePat;
    }

    public void setPerApePat(String perApePat) {
        this.perApePat = perApePat;
    }

    public String getPerApeMat() {
        return perApeMat;
    }

    public void setPerApeMat(String perApeMat) {
        this.perApeMat = perApeMat;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getBcontrasenia() {
        return bcontrasenia;
    }

    public void setBcontrasenia(String bcontrasenia) {
        this.bcontrasenia = bcontrasenia;
    }

    public String getContraseniaB() {
        return contraseniaB;
    }

    public void setContraseniaB(String contraseniaB) {
        this.contraseniaB = contraseniaB;
    }

    /**
     * @return the opcionesPermisosmenu
     */
    public List<Menu> getOpcionesPermisosmenu() {
        return opcionesPermisosmenu;
    }

    /**
     * @param opcionesPermisosmenu the opcionesPermisosmenu to set
     */
    public void setOpcionesPermisosmenu(List<Menu> opcionesPermisosmenu) {
        this.opcionesPermisosmenu = opcionesPermisosmenu;
    }

   /**
     * @return the eliminarSeguimiento
     */
    public boolean isEliminarSeguimiento() {
        return eliminarSeguimiento;
    }

    /**
     * @param eliminarSeguimiento the eliminarSeguimiento to set
     */
    public void setEliminarSeguimiento(boolean eliminarSeguimiento) {
        this.eliminarSeguimiento = eliminarSeguimiento;
    }    

}
