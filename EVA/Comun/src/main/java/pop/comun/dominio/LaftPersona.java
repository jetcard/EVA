/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class LaftPersona extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cLaftPersonaId;
    private Date fRegistro;
    private String aNroDocumento;
    private String cEntidadId;
    private String DApePat;
    private String DApeMat;
    private String DNombres;
    private String cSexoId;
    private String dObservaciones;

    public int getcLaftPersonaId() {
        return cLaftPersonaId;
    }

    public void setcLaftPersonaId(int cLaftPersonaId) {
        this.cLaftPersonaId = cLaftPersonaId;
    }

    public Date getfRegistro() {
        return fRegistro;
    }

    public void setfRegistro(Date fRegistro) {
        this.fRegistro = fRegistro;
    }

    public String getaNroDocumento() {
        return aNroDocumento;
    }

    public void setaNroDocumento(String aNroDocumento) {
        this.aNroDocumento = aNroDocumento;
    }

    public String getcEntidadId() {
        return cEntidadId;
    }

    public void setcEntidadId(String cEntidadId) {
        this.cEntidadId = cEntidadId;
    }

    

    public String getcSexoId() {
        return cSexoId;
    }

    public void setcSexoId(String cSexoId) {
        this.cSexoId = cSexoId;
    }

    public String getdObservaciones() {
        return dObservaciones;
    }

    public void setdObservaciones(String dObservaciones) {
        this.dObservaciones = dObservaciones;
    }

    /**
     * @return the DApePat
     */
    public String getDApePat() {
        return DApePat;
    }

    /**
     * @param DApePat the DApePat to set
     */
    public void setDApePat(String DApePat) {
        this.DApePat = DApePat;
    }

    /**
     * @return the DApeMat
     */
    public String getDApeMat() {
        return DApeMat;
    }

    /**
     * @param DApeMat the DApeMat to set
     */
    public void setDApeMat(String DApeMat) {
        this.DApeMat = DApeMat;
    }

    /**
     * @return the DNombres
     */
    public String getDNombres() {
        return DNombres;
    }

    /**
     * @param DNombres the DNombres to set
     */
    public void setDNombres(String DNombres) {
        this.DNombres = DNombres;
    }
    
    
    
    
}
