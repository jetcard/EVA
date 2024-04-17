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
public class MaeNotificacion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cmaeNotificacionId;
    private String cusuarioDeId;
    private String cusuarioPaId;
    private String ctipoId;
    private Date fnotificacion;
    private String dtitulo;
    private String dcuerpo;
    private String benviado;
    private String brecibido;
    private int ncantNotificacion;

    public MaeNotificacion() {
    }

    public Number getCmaeNotificacionId() {
        return cmaeNotificacionId;
    }

    public void setCmaeNotificacionId(Number cmaeNotificacionId) {
        this.cmaeNotificacionId = cmaeNotificacionId;
    }

    public String getCusuarioDeId() {
        return cusuarioDeId;
    }

    public void setCusuarioDeId(String cusuarioDeId) {
        this.cusuarioDeId = cusuarioDeId;
    }

    public String getCusuarioPaId() {
        return cusuarioPaId;
    }

    public void setCusuarioPaId(String cusuarioPaId) {
        this.cusuarioPaId = cusuarioPaId;
    }

    public String getCtipoId() {
        return ctipoId;
    }

    public void setCtipoId(String ctipoId) {
        this.ctipoId = ctipoId;
    }

    public Date getFnotificacion() {
        return fnotificacion;
    }

    public void setFnotificacion(Date fnotificacion) {
        this.fnotificacion = fnotificacion;
    }

    public String getDtitulo() {
        return dtitulo;
    }

    public void setDtitulo(String dtitulo) {
        this.dtitulo = dtitulo;
    }

    public String getDcuerpo() {
        return dcuerpo;
    }

    public void setDcuerpo(String dcuerpo) {
        this.dcuerpo = dcuerpo;
    }

    public String getBenviado() {
        return benviado;
    }

    public void setBenviado(String benviado) {
        this.benviado = benviado;
    }

    public String getBrecibido() {
        return brecibido;
    }

    public void setBrecibido(String brecibido) {
        this.brecibido = brecibido;
    }

    public int getNcantNotificacion() {
        return ncantNotificacion;
    }

    public void setNcantNotificacion(int ncantNotificacion) {
        this.ncantNotificacion = ncantNotificacion;
    }
    
    
    
}
