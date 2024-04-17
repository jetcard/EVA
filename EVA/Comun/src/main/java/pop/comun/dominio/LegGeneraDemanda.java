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
 * @author PR154357
 */
public class LegGeneraDemanda extends Base{  
    private String fondoId;
    private String fondoDescrip;
    private String nombresDNI;
    private List<String> nombresDNILista;
    private String apePat;
    private String apeMat;
    private String nroDocumento;
    private String direccion;
    private String distrito;
    private String provincia;
    private String mValNomTitulo;
    private String nPlazoMeses;
    private String mCuota;
    private String cuotasEmisionYVencimiento;
    private List<String> cuotasEmisionYVencimientoLista;
    private String nCuota;
    private List<String> cuotasLista;
    private String fTchn;    
    private String sTchn;
    private String sAshipo;
    private String sAsexptchn;
    private String partida;
    private String pago;
    private String fCartasNotariales;
    private List<String> cartasNotarialesLista;
    private String mValorDolRealiza;
    private String mCorte;
    private String fechaDemanda;
    private String montoADemandar;
    private String montoADemandarLargo;
    private String valorComercial;
    private String usuario;
    private byte[] archivoWord;
    private String nombreArchivoWord;
    private String flagEliminado;
    private String usuarioEliminador;
    private String fechaEliminacion;
    private boolean selected = false;
                        
    private MaeInversion maeInversion;                    
    private String cInmueble;                    
                
    private String iInversion;
    private String meses;

    private String direccionNotificacion;
    private String ubi1;
    private String ubi2;

    private MaeFondo fondo;
    
    private MaeHipoteca maeHipoteca;
    private MaeInmueble maeInmueble;
    private MaePersona maePersona;
    private MaeEstadoCuenta maeEstadoCuenta;
    private Number nroCuotasAtrasadas;
    private Date fechaUltDeposito;
    private Number totalDeposito;
    private String cancelado;
    private String ampliado;
    private String refinanciado;
    private String judicial;
    private String transfAmpl;    
    private String transfrefin;        
    private String transfendosado;   
    private String ejudicial;
    
    private String simbolo;
    
    private String tchn;
    
    private String idHistorico;
    private String fechaDemandaHis;
    private String montoADemandarHis;
    private String fondoHis;
    private String tchnHis;
    private String clienteHis;
    private String cInmuebleHis;
    private String usuarioHis;
    private String fechaCreacion;
    private String fEscritura;
    private String deFecha;
    private String emitidoEl;


    public String getFondoId() {
        return fondoId;
    }

    public void setFondoId(String fondoId) {
        this.fondoId = fondoId;
    }

    public String getFondoDescrip() {
        return fondoDescrip;
    }

    public void setFondoDescrip(String fondoDescrip) {
        this.fondoDescrip = fondoDescrip;
    }

    public String getNombresDNI() {
        return nombresDNI;
    }

    public void setNombresDNI(String nombresDNI) {
        this.nombresDNI = nombresDNI;
    }

    public List<String> getNombresDNILista() {
        return nombresDNILista;
    }

    public void setNombresDNILista(List<String> nombresDNILista) {
        this.nombresDNILista = nombresDNILista;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getmValNomTitulo() {
        return mValNomTitulo;
    }

    public void setmValNomTitulo(String mValNomTitulo) {
        this.mValNomTitulo = mValNomTitulo;
    }

    public String getnPlazoMeses() {
        return nPlazoMeses;
    }

    public void setnPlazoMeses(String nPlazoMeses) {
        this.nPlazoMeses = nPlazoMeses;
    }

    public String getmCuota() {
        return mCuota;
    }

    public void setmCuota(String mCuota) {
        this.mCuota = mCuota;
    }

    public String getCuotasEmisionYVencimiento() {
        return cuotasEmisionYVencimiento;
    }

    public void setCuotasEmisionYVencimiento(String cuotasEmisionYVencimiento) {
        this.cuotasEmisionYVencimiento = cuotasEmisionYVencimiento;
    }

    public List<String> getCuotasEmisionYVencimientoLista() {
        return cuotasEmisionYVencimientoLista;
    }

    public void setCuotasEmisionYVencimientoLista(List<String> cuotasEmisionYVencimientoLista) {
        this.cuotasEmisionYVencimientoLista = cuotasEmisionYVencimientoLista;
    }

    public String getnCuota() {
        return nCuota;
    }

    public void setnCuota(String nCuota) {
        this.nCuota = nCuota;
    }

    public List<String> getCuotasLista() {
        return cuotasLista;
    }

    public void setCuotasLista(List<String> cuotasLista) {
        this.cuotasLista = cuotasLista;
    }

    public String getfTchn() {
        return fTchn;
    }

    public void setfTchn(String fTchn) {
        this.fTchn = fTchn;
    }

    public String getsTchn() {
        return sTchn;
    }

    public void setsTchn(String sTchn) {
        this.sTchn = sTchn;
    }

    public String getsAshipo() {
        return sAshipo;
    }

    public void setsAshipo(String sAshipo) {
        this.sAshipo = sAshipo;
    }

    public String getsAsexptchn() {
        return sAsexptchn;
    }

    public void setsAsexptchn(String sAsexptchn) {
        this.sAsexptchn = sAsexptchn;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getfCartasNotariales() {
        return fCartasNotariales;
    }

    public void setfCartasNotariales(String fCartasNotariales) {
        this.fCartasNotariales = fCartasNotariales;
    }

    public List<String> getCartasNotarialesLista() {
        return cartasNotarialesLista;
    }

    public void setCartasNotarialesLista(List<String> cartasNotarialesLista) {
        this.cartasNotarialesLista = cartasNotarialesLista;
    }

    public String getmValorDolRealiza() {
        return mValorDolRealiza;
    }

    public void setmValorDolRealiza(String mValorDolRealiza) {
        this.mValorDolRealiza = mValorDolRealiza;
    }

    public String getmCorte() {
        return mCorte;
    }

    public void setmCorte(String mCorte) {
        this.mCorte = mCorte;
    }

    public String getFechaDemanda() {
        return fechaDemanda;
    }

    public void setFechaDemanda(String fechaDemanda) {
        this.fechaDemanda = fechaDemanda;
    }

    public String getMontoADemandar() {
        return montoADemandar;
    }

    public void setMontoADemandar(String montoADemandar) {
        this.montoADemandar = montoADemandar;
    }

    public String getMontoADemandarLargo() {
        return montoADemandarLargo;
    }

    public void setMontoADemandarLargo(String montoADemandarLargo) {
        this.montoADemandarLargo = montoADemandarLargo;
    }

    public String getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(String valorComercial) {
        this.valorComercial = valorComercial;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public String getcInmueble() {
        return cInmueble;
    }

    public void setcInmueble(String cInmueble) {
        this.cInmueble = cInmueble;
    }

    public String getiInversion() {
        return iInversion;
    }

    public void setiInversion(String iInversion) {
        this.iInversion = iInversion;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getDireccionNotificacion() {
        return direccionNotificacion;
    }

    public void setDireccionNotificacion(String direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
    }

    public String getUbi1() {
        return ubi1;
    }

    public void setUbi1(String ubi1) {
        this.ubi1 = ubi1;
    }

    public String getUbi2() {
        return ubi2;
    }

    public void setUbi2(String ubi2) {
        this.ubi2 = ubi2;
    }

    public MaeFondo getFondo() {
        return fondo;
    }

    public void setFondo(MaeFondo fondo) {
        this.fondo = fondo;
    }

    public MaeHipoteca getMaeHipoteca() {
        return maeHipoteca;
    }

    public void setMaeHipoteca(MaeHipoteca maeHipoteca) {
        this.maeHipoteca = maeHipoteca;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public MaeEstadoCuenta getMaeEstadoCuenta() {
        return maeEstadoCuenta;
    }

    public void setMaeEstadoCuenta(MaeEstadoCuenta maeEstadoCuenta) {
        this.maeEstadoCuenta = maeEstadoCuenta;
    }

    public Number getNroCuotasAtrasadas() {
        return nroCuotasAtrasadas;
    }

    public void setNroCuotasAtrasadas(Number nroCuotasAtrasadas) {
        this.nroCuotasAtrasadas = nroCuotasAtrasadas;
    }

    public Date getFechaUltDeposito() {
        return fechaUltDeposito;
    }

    public void setFechaUltDeposito(Date fechaUltDeposito) {
        this.fechaUltDeposito = fechaUltDeposito;
    }

    public Number getTotalDeposito() {
        return totalDeposito;
    }

    public void setTotalDeposito(Number totalDeposito) {
        this.totalDeposito = totalDeposito;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getAmpliado() {
        return ampliado;
    }

    public void setAmpliado(String ampliado) {
        this.ampliado = ampliado;
    }

    public String getRefinanciado() {
        return refinanciado;
    }

    public void setRefinanciado(String refinanciado) {
        this.refinanciado = refinanciado;
    }

    public String getJudicial() {
        return judicial;
    }

    public void setJudicial(String judicial) {
        this.judicial = judicial;
    }

    public String getTransfAmpl() {
        return transfAmpl;
    }

    public void setTransfAmpl(String transfAmpl) {
        this.transfAmpl = transfAmpl;
    }

    public String getTransfrefin() {
        return transfrefin;
    }

    public void setTransfrefin(String transfrefin) {
        this.transfrefin = transfrefin;
    }

    public String getTransfendosado() {
        return transfendosado;
    }

    public void setTransfendosado(String transfendosado) {
        this.transfendosado = transfendosado;
    }

    public String getEjudicial() {
        return ejudicial;
    }

    public void setEjudicial(String ejudicial) {
        this.ejudicial = ejudicial;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTchn() {
        return tchn;
    }

    public void setTchn(String tchn) {
        this.tchn = tchn;
    }

    public String getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(String idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getFechaDemandaHis() {
        return fechaDemandaHis;
    }

    public void setFechaDemandaHis(String fechaDemandaHis) {
        this.fechaDemandaHis = fechaDemandaHis;
    }

    public String getMontoADemandarHis() {
        return montoADemandarHis;
    }

    public void setMontoADemandarHis(String montoADemandarHis) {
        this.montoADemandarHis = montoADemandarHis;
    }

    public String getFondoHis() {
        return fondoHis;
    }

    public void setFondoHis(String fondoHis) {
        this.fondoHis = fondoHis;
    }

    public String getTchnHis() {
        return tchnHis;
    }

    public void setTchnHis(String tchnHis) {
        this.tchnHis = tchnHis;
    }

    public String getClienteHis() {
        return clienteHis;
    }

    public void setClienteHis(String clienteHis) {
        this.clienteHis = clienteHis;
    }

    public String getcInmuebleHis() {
        return cInmuebleHis;
    }

    public void setcInmuebleHis(String cInmuebleHis) {
        this.cInmuebleHis = cInmuebleHis;
    }

    public String getUsuarioHis() {
        return usuarioHis;
    }

    public void setUsuarioHis(String usuarioHis) {
        this.usuarioHis = usuarioHis;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public byte[] getArchivoWord() {
        return archivoWord;
    }

    public void setArchivoWord(byte[] archivoWord) {
        this.archivoWord = archivoWord;
    }

    public String getFlagEliminado() {
        return flagEliminado;
    }

    public void setFlagEliminado(String flagEliminado) {
        this.flagEliminado = flagEliminado;
    }

    public String getUsuarioEliminador() {
        return usuarioEliminador;
    }

    public void setUsuarioEliminador(String usuarioEliminador) {
        this.usuarioEliminador = usuarioEliminador;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getNombreArchivoWord() {
        return nombreArchivoWord;
    }

    public void setNombreArchivoWord(String nombreArchivoWord) {
        this.nombreArchivoWord = nombreArchivoWord;
    }
 
    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }    

    public String getfEscritura() {
        return fEscritura;
    }

    public void setfEscritura(String fEscritura) {
        this.fEscritura = fEscritura;
    }

    public String getDeFecha() {
        return deFecha;
    }

    public void setDeFecha(String deFecha) {
        this.deFecha = deFecha;
    }

    public String getEmitidoEl() {
        return emitidoEl;
    }

    public void setEmitidoEl(String emitidoEl) {
        this.emitidoEl = emitidoEl;
    }
}