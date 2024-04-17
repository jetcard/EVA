/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class Base implements Serializable{
    
    private String eEstado;
    private String cUsuarioAdd;
    private Date fUsuarioAdd;
    private String cUsuarioMod;
    private Date fUsuarioMod;
        
    private String dDatoBusq;
    private Date fIniBusq;
    private Date fFinBusq;
    private int nIniBusq;
    private int nFinBusq;
    private String cMonedaId;
    private String bgrabado;
    
    private String dusuarioNombres;
    private String dusuarioApellidos;
   

    public String getcMonedaId() {
        return cMonedaId;
    }

    public void setcMonedaId(String cMonedaId) {
        this.cMonedaId = cMonedaId;
    }

    /**
     * @return the eEstado
     */
    public String geteEstado() {
        return eEstado;
    }

    /**
     * @param eEstado the eEstado to set
     */
    public void seteEstado(String eEstado) {
        this.eEstado = eEstado;
    }

    /**
     * @return the cUsuarioAdd
     */
    public String getcUsuarioAdd() {
        return cUsuarioAdd;
    }

    /**
     * @param cUsuarioAdd the cUsuarioAdd to set
     */
    public void setcUsuarioAdd(String cUsuarioAdd) {
        this.cUsuarioAdd = cUsuarioAdd;
    }

    /**
     * @return the fUsuarioAdd
     */
    public Date getfUsuarioAdd() {
        return fUsuarioAdd;
    }

    /**
     * @param fUsuarioAdd the fUsuarioAdd to set
     */
    public void setfUsuarioAdd(Date fUsuarioAdd) {
        this.fUsuarioAdd = fUsuarioAdd;
    }

    /**
     * @return the cUsuarioMod
     */
    public String getcUsuarioMod() {
        return cUsuarioMod;
    }

    /**
     * @param cUsuarioMod the cUsuarioMod to set
     */
    public void setcUsuarioMod(String cUsuarioMod) {
        this.cUsuarioMod = cUsuarioMod;
    }

    /**
     * @return the fUsuarioMod
     */
    public Date getfUsuarioMod() {
        return fUsuarioMod;
    }

    /**
     * @param fUsuarioMod the fUsuarioMod to set
     */
    public void setfUsuarioMod(Date fUsuarioMod) {
        this.fUsuarioMod = fUsuarioMod;
    }

    /**
     * @return the dDatoBusq
     */
    public String getdDatoBusq() {
        return dDatoBusq;
    }

    /**
     * @param dDatoBusq the dDatoBusq to set
     */
    public void setdDatoBusq(String dDatoBusq) {
        this.dDatoBusq = dDatoBusq;
    }

    /**
     * @return the fIniBusq
     */
    public Date getfIniBusq() {
        return fIniBusq;
    }

    /**
     * @param fIniBusq the fIniBusq to set
     */
    public void setfIniBusq(Date fIniBusq) {
        this.fIniBusq = fIniBusq;
    }

    /**
     * @return the fFinBusq
     */
    public Date getfFinBusq() {
        return fFinBusq;
    }

    /**
     * @param fFinBusq the fFinBusq to set
     */
    public void setfFinBusq(Date fFinBusq) {
        this.fFinBusq = fFinBusq;
    }

    /**
     * @return the nIniBusq
     */
    public int getnIniBusq() {
        return nIniBusq;
    }

    /**
     * @param nIniBusq the nIniBusq to set
     */
    public void setnIniBusq(int nIniBusq) {
        this.nIniBusq = nIniBusq;
    }

    /**
     * @return the nFinBusq
     */
    public int getnFinBusq() {
        return nFinBusq;
    }

    /**
     * @param nFinBusq the nFinBusq to set
     */
    public void setnFinBusq(int nFinBusq) {
        this.nFinBusq = nFinBusq;
    }

    public String getBgrabado() {
        return bgrabado;
    }

    public void setBgrabado(String bgrabado) {
        this.bgrabado = bgrabado;
    }

    public String getDusuarioNombres() {
        return dusuarioNombres;
    }

    public void setDusuarioNombres(String dusuarioNombres) {
        this.dusuarioNombres = dusuarioNombres;
    }

    public String getDusuarioApellidos() {
        return dusuarioApellidos;
    }

    public void setDusuarioApellidos(String dusuarioApellidos) {
        this.dusuarioApellidos = dusuarioApellidos;
    }
    
    
}
