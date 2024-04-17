/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author RC433838
 */
public class CobRequerimientoCartas extends Base{
 
    protected String reqTipo;
    protected Integer reqNumero;
    protected String fondoId;
    protected String dvalor_bv;
    protected Date reqEmision;
    protected Date reqEmision2;    
    protected Date reqEnvio;
    protected Date reqEnvio2;    
    protected Date reqRecepcion;
    protected Date reqRecepcion2;    
    protected String tipoCarta;
    protected String reqEstado;
    protected String reqComentario;
    
    private CobTablas carta;
    private CobTablas estado;
    private MaeInversion inversion;
    private MaeFondo fondo;
    private CobMaeSeguimiento maeSeguimiento;
    
    private List<CobRequerimientoCriterios> criterios;
    private List<MovimientoCartas> cartasNotariales;
    private List<MaeEstadoCuenta> maeEstadoCuentaList;
    
    private boolean sel=false;
    private String sinTipoCarta;
    

    /**
     * @return the reqTipo
     */
    public String getReqTipo() {
        return reqTipo;
    }

    /**
     * @param reqTipo the reqTipo to set
     */
    public void setReqTipo(String reqTipo) {
        this.reqTipo = reqTipo;
    }

    /**
     * @return the reqNumero
     */
    public Integer getReqNumero() {
        return reqNumero;
    }

    /**
     * @param reqNumero the reqNumero to set
     */
    public void setReqNumero(Integer reqNumero) {
        this.reqNumero = reqNumero;
    }

    /**
     * @return the fondoId
     */
    public String getFondoId() {
        return fondoId;
    }

    /**
     * @param fondoId the fondoId to set
     */
    public void setFondoId(String fondoId) {
        this.fondoId = fondoId;
    }

    /**
     * @return the dvalor_bv
     */
    public String getDvalor_bv() {
        return dvalor_bv;
    }

    /**
     * @param dvalor_bv the dvalor_bv to set
     */
    public void setDvalor_bv(String dvalor_bv) {
        this.dvalor_bv = dvalor_bv;
    }


    /**
     * @return the tipoCarta
     */
    public String getTipoCarta() {
        return tipoCarta;
    }

    /**
     * @param tipoCarta the tipoCarta to set
     */
    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    /**
     * @return the reqEstado
     */
    public String getReqEstado() {
        return reqEstado;
    }

    /**
     * @param reqEstado the reqEstado to set
     */
    public void setReqEstado(String reqEstado) {
        this.reqEstado = reqEstado;
    }

    /**
     * @return the reqComentario
     */
    public String getReqComentario() {
        return reqComentario;
    }

    /**
     * @param reqComentario the reqComentario to set
     */
    public void setReqComentario(String reqComentario) {
        this.reqComentario = reqComentario;
    }

    /**
     * @return the carta
     */
    public CobTablas getCarta() {
        return carta;
    }

    /**
     * @param carta the carta to set
     */
    public void setCarta(CobTablas carta) {
        this.carta = carta;
    }

    /**
     * @return the estado
     */
    public CobTablas getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(CobTablas estado) {
        this.estado = estado;
    }

    /**
     * @return the inversion
     */
    public MaeInversion getInversion() {
        return inversion;
    }

    /**
     * @param inversion the inversion to set
     */
    public void setInversion(MaeInversion inversion) {
        this.inversion = inversion;
    }

    /**
     * @return the fondo
     */
    public MaeFondo getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(MaeFondo fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the reqEmision
     */
    public Date getReqEmision() {
        return reqEmision;
    }

    /**
     * @param reqEmision the reqEmision to set
     */
    public void setReqEmision(Date reqEmision) {
        this.reqEmision = reqEmision;
    }

    /**
     * @return the reqEnvio
     */
    public Date getReqEnvio() {
        return reqEnvio;
    }

    /**
     * @param reqEnvio the reqEnvio to set
     */
    public void setReqEnvio(Date reqEnvio) {
        this.reqEnvio = reqEnvio;
    }

    /**
     * @return the reqRecepcion
     */
    public Date getReqRecepcion() {
        return reqRecepcion;
    }

    /**
     * @param reqRecepcion the reqRecepcion to set
     */
    public void setReqRecepcion(Date reqRecepcion) {
        this.reqRecepcion = reqRecepcion;
    }

    /**
     * @return the reqEmision2
     */
    public Date getReqEmision2() {
        return reqEmision2;
    }

    /**
     * @param reqEmision2 the reqEmision2 to set
     */
    public void setReqEmision2(Date reqEmision2) {
        this.reqEmision2 = reqEmision2;
    }

    /**
     * @return the reqEnvio2
     */
    public Date getReqEnvio2() {
        return reqEnvio2;
    }

    /**
     * @param reqEnvio2 the reqEnvio2 to set
     */
    public void setReqEnvio2(Date reqEnvio2) {
        this.reqEnvio2 = reqEnvio2;
    }

    /**
     * @return the reqRecepcion2
     */
    public Date getReqRecepcion2() {
        return reqRecepcion2;
    }

    /**
     * @param reqRecepcion2 the reqRecepcion2 to set
     */
    public void setReqRecepcion2(Date reqRecepcion2) {
        this.reqRecepcion2 = reqRecepcion2;
    }

   
    /**
     * @return the criterios
     */
    public List<CobRequerimientoCriterios> getCriterios() {
        return criterios;
    }

    /**
     * @param criterios the criterios to set
     */
    public void setCriterios(List<CobRequerimientoCriterios> criterios) {
        this.criterios = criterios;
    }

    /**
     * @return the sel
     */
    public boolean isSel() {
        return sel;
    }

    /**
     * @param sel the sel to set
     */
    public void setSel(boolean sel) {
        this.sel = sel;
    }

    /**
     * @return the maeSeguimiento
     */
    public CobMaeSeguimiento getMaeSeguimiento() {
        return maeSeguimiento;
    }

    /**
     * @param maeSeguimiento the maeSeguimiento to set
     */
    public void setMaeSeguimiento(CobMaeSeguimiento maeSeguimiento) {
        this.maeSeguimiento = maeSeguimiento;
    }

    /**
     * @return the cartasNotariales
     */
    public List<MovimientoCartas> getCartasNotariales() {
        return cartasNotariales;
    }

    /**
     * @param cartasNotariales the cartasNotariales to set
     */
    public void setCartasNotariales(List<MovimientoCartas> cartasNotariales) {
        this.cartasNotariales = cartasNotariales;
    }

    /**
     * @return the maeEstadoCuentaList
     */
    public List<MaeEstadoCuenta> getMaeEstadoCuentaList() {
        return maeEstadoCuentaList;
    }

    /**
     * @param maeEstadoCuentaList the maeEstadoCuentaList to set
     */
    public void setMaeEstadoCuentaList(List<MaeEstadoCuenta> maeEstadoCuentaList) {
        this.maeEstadoCuentaList = maeEstadoCuentaList;
    }

    /**
     * @return the sinTipoCarta
     */
    public String getSinTipoCarta() {
        return sinTipoCarta;
    }

    /**
     * @param sinTipoCarta the sinTipoCarta to set
     */
    public void setSinTipoCarta(String sinTipoCarta) {
        this.sinTipoCarta = sinTipoCarta;
    }

       
	
}
