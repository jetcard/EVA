/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeCargo;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICargoServ;
import pop.webcobranzas.iface.ICuotaPagoServ;
import pop.webcobranzas.iface.ISeguimientoServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.servicio.CargoServ;
import pop.webcobranzas.servicio.CuotaPagoServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.servicio.SeguimientoServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.UbigeoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped 
public class InversionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // clases
    private MaeInversion maeInversion = new MaeInversion();
    private MaeFondo maeFondo = new MaeFondo();
    private MaePersona maePersona = new MaePersona();
    private MaeInmueble maeInmueble = new MaeInmueble();
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    private MaeCuotaPago cuotaPago = new MaeCuotaPago();
    private MaeCuotaPago cuotaPagoOtros = new MaeCuotaPago();
    private MaeCuotaPago cuotaPagoFechaCorte = new MaeCuotaPago();
    
    private CobTablas cobTablasAccion = new CobTablas();
    private CobTablas cobTablasFamilia = new CobTablas();
    
    // para enviar a grabar B
    private CobTablas cobTablasAccionB = new CobTablas();
    private CobTablas cobTablasFamiliaB = new CobTablas();
    //private CobSeguimientoDet cobSeguimientoDet = new CobSeguimientoDet();
    // calculos
    private String cuoPendiente = "0.00";
    private Date fechaCorte;
    private String cuoPendienteFC = "0.00";
    private String cuoPendienteOtros = "0.00";
    private String cuoPendienteTotal = "0.00";
    private String cuoPendienteDepMes = "0.00";
    // listas
    private List<MaeInversion> maeInversionList;
    private List<MaeUbigeo> ubigeoProv;
    private List<MaeUbigeo> ubigeoDist;
    private List<MaeCuotaPago> maeCuotaPagoList;
    private List<CobTablas> cobTablasAccionList;
    private List<CobTablas> cobTablasFamiliaList;

    private Number montoCuota;
    // servicios
    private InversionServ inversionServ = new InversionServ();
    private IUbigeoServ iUbigeoServ = new UbigeoServ();
    private ICuotaPagoServ cuotaPagoServ = new CuotaPagoServ();
    private ICargoServ cargoServ = new CargoServ();
    private ITablasServ tablasServ = new TablasServ();
    private ISeguimientoServ seguimientoServ = new SeguimientoServ();
    
    private MaeInversion maeInversionCd = new MaeInversion();
    private MaeCargo maeCargo = new MaeCargo();
    private CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
    
    
    private boolean contactado;

    /**
     * Creates a new instance of InversionBean
     */
    public InversionBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);
    }

    public void buscarDeudores() {
        try {

            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            //System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            //System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeInversionList = getInversionServ().listarDeudor(maeInversion);
            //System.out.println("pop.webcobranzas.bean.InversionBean.<init>()");
            //System.out.println("   Lista de Depositos = " + maeInversionList.size());

        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.InversionBean.buscarDeudores()");
        }
    }

    public void obtenerCabDet(MaeInversion oInversion) {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" InversionBean - obtenerCabDet - " + 
                    oInversion.getcMaeInversionId() + " - " + oInversion.getCInversion().trim() );
            
            maeInversionCd.setMaeFondo(oInversion.getMaeFondo());
            maeInversionCd.setcMaeInversionId(oInversion.getcMaeInversionId());
            maeInversionCd.setcTipoInv(oInversion.getcTipoInv());
            maeInversionCd.setCInversion(oInversion.getCInversion());
            maeInversionCd.setcPersonaId(oInversion.getcPersonaId());
            maeInversionCd.setNCuotasAtrasadas(oInversion.getNCuotasAtrasadas());
            maeInversionCd.setcInversionId(oInversion.getcInversionId());

            MaeCuotaPago oMaeCuotaPago = new MaeCuotaPago();
            oMaeCuotaPago.setMaeInversion(maeInversionCd);
            maeCuotaPagoList = getCuotaPagoServ().listarCuotaPago(oMaeCuotaPago);

            maeCargo.setMaeInversion(maeInversionCd);
            maeCargo = getCargoServ().calcularCargoAtrasado(maeCargo);
            setCuoPendienteOtros(maeCargo.getIPendiente().toString());
            
            cobMaeSeguimiento.setMaeInversion(maeInversionCd);
            cobMaeSeguimiento= getSeguimientoServ().listarSeguimiento(cobMaeSeguimiento);

            //System.out.println("   Lista de maeCuotaPagoList = " + maeCuotaPagoList.size());
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.InversionBean.obtenerCabDet()");
        }
    }

    public void listarProvincia() {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarProvincia()");
            ubigeoProv = getiUbigeoServ().listarProvincia(maeUbigeoP);
            //System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {
        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarDistrito()");
            ubigeoDist = getiUbigeoServ().listarDistrito(maeUbigeoP);
            //System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void calcularCuoPagFut(AjaxBehaviorEvent event) {
        try {
            //this.setCuoPendienteFC("1552.00");
            //System.out.println("pop.webcobranzas.bean.InversionBean.calcularCuoPagFut()2");
            
            System.out.println("["+SessionUtils.getUserName()+"] "+" InversionBean - calcularCuoPagFut - " + 
                    maeInversionCd.getcMaeInversionId() + " - " + fechaCorte.toString() );

            if (fechaCorte != null) {
                //System.out.println(fechaCorte.toString());
                //System.out.println("            con fecha ");
                cuotaPagoFechaCorte.setMaeInversion(maeInversionCd);
                cuotaPagoFechaCorte.setfIniBusq(fechaCorte);
                cuotaPagoFechaCorte.setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(cuotaPagoFechaCorte).getiPendiente());
                this.setCuoPendienteFC(cuotaPagoFechaCorte.getiPendiente().toString());
            } else {
                //System.out.println("            sin fecha ");
                this.setCuoPendienteFC("0.00");
            }

            //System.out.println(" Fecha de corte " + getCuotaPagoFechaCorte().getfIniBusq().toString() + " - " + cuotaPagoFechaCorte.getiPendiente());

        } catch (Exception e) {
        }
    }

    public void calularTotal(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.calularTotal()");
            float totalVar;
            totalVar= (Float)maeCargo.getIPendiente() + Float.parseFloat(cuoPendiente);
            setCuoPendienteTotal(String.valueOf(totalVar));
            //System.out.println(Float.parseFloat(cuoPendiente));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setCuoPendienteTotal("0.00");
        }
    }

    public void listarAccion(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarAccion()");
            //ubigeoDist = getiUbigeoServ().listarDistrito(maeUbigeoP);
            if (contactado){
                cobTablasAccion.setCtablaId("0602");
            }
            else{
                cobTablasAccion.setCtablaId("0603");
            }
            cobTablasFamilia.setCtablaId("0604");
            cobTablasAccionList = getTablasServ().listarTablas(cobTablasAccion);
            cobTablasFamiliaList = getTablasServ().listarTablas(cobTablasFamilia);
            //System.out.println("    estado " + contactado);
            //System.out.println("    cobTablasList -> " + cobTablasAccionList.size());
        } catch (Exception e) {
        }
    }
    
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public IUbigeoServ getiUbigeoServ() {
        return iUbigeoServ;
    }

    public void setiUbigeoServ(IUbigeoServ iUbigeoServ) {
        this.iUbigeoServ = iUbigeoServ;
    }

    public List<MaeUbigeo> getUbigeoProv() {
        return ubigeoProv;
    }

    public void setUbigeoProv(List<MaeUbigeo> ubigeoProv) {
        this.ubigeoProv = ubigeoProv;
    }

    public List<MaeUbigeo> getUbigeoDist() {
        return ubigeoDist;
    }

    public void setUbigeoDist(List<MaeUbigeo> ubigeoDist) {
        this.ubigeoDist = ubigeoDist;
    }

    public MaeUbigeo getMaeUbigeoP() {
        return maeUbigeoP;
    }

    public void setMaeUbigeoP(MaeUbigeo maeUbigeoP) {
        this.maeUbigeoP = maeUbigeoP;
    }

    public MaeUbigeo getMaeUbigeoD() {
        return maeUbigeoD;
    }

    public void setMaeUbigeoD(MaeUbigeo maeUbigeoD) {
        this.maeUbigeoD = maeUbigeoD;
    }

    public MaeInversion getMaeInversionCd() {
        return maeInversionCd;
    }

    public void setMaeInversionCd(MaeInversion maeInversionCd) {
        this.maeInversionCd = maeInversionCd;
    }

    public List<MaeCuotaPago> getMaeCuotaPagoList() {
        return maeCuotaPagoList;
    }

    public void setMaeCuotaPagoList(List<MaeCuotaPago> maeCuotaPagoList) {
        this.maeCuotaPagoList = maeCuotaPagoList;
    }

    public ICuotaPagoServ getCuotaPagoServ() {
        return cuotaPagoServ;
    }

    public void setCuotaPagoServ(ICuotaPagoServ cuotaPagoServ) {
        this.cuotaPagoServ = cuotaPagoServ;
    }

    public Number getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Number montoCuota) {
        this.montoCuota = montoCuota;
    }

    public MaeCuotaPago getCuotaPago() {
        return cuotaPago;
    }

    public void setCuotaPago(MaeCuotaPago cuotaPago) {
        this.cuotaPago = cuotaPago;
    }

    public String getCuoPendiente() {
        return cuoPendiente;
    }

    public void setCuoPendiente(String cuoPendiente) {
        this.cuoPendiente = cuoPendiente;
    }

    public MaeCuotaPago getCuotaPagoOtros() {
        return cuotaPagoOtros;
    }

    public void setCuotaPagoOtros(MaeCuotaPago cuotaPagoOtros) {
        this.cuotaPagoOtros = cuotaPagoOtros;
    }

    public MaeCuotaPago getCuotaPagoFechaCorte() {
        return cuotaPagoFechaCorte;
    }

    public void setCuotaPagoFechaCorte(MaeCuotaPago cuotaPagoFechaCorte) {
        this.cuotaPagoFechaCorte = cuotaPagoFechaCorte;
    }

    public String getCuoPendienteFC() {
        return cuoPendienteFC;
    }

    public void setCuoPendienteFC(String cuoPendienteFC) {
        this.cuoPendienteFC = cuoPendienteFC;
    }

    public String getCuoPendienteOtros() {
        return cuoPendienteOtros;
    }

    public void setCuoPendienteOtros(String cuoPendienteOtros) {
        this.cuoPendienteOtros = cuoPendienteOtros;
    }

    public String getCuoPendienteDepMes() {
        return cuoPendienteDepMes;
    }

    public void setCuoPendienteDepMes(String cuoPendienteDepMes) {
        this.cuoPendienteDepMes = cuoPendienteDepMes;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public ICargoServ getCargoServ() {
        return cargoServ;
    }

    public void setCargoServ(ICargoServ cargoServ) {
        this.cargoServ = cargoServ;
    }

    public MaeCargo getMaeCargo() {
        return maeCargo;
    }

    public void setMaeCargo(MaeCargo maeCargo) {
        this.maeCargo = maeCargo;
    }

    public String getCuoPendienteTotal() {
        return cuoPendienteTotal;
    }

    public void setCuoPendienteTotal(String cuoPendienteTotal) {
        this.cuoPendienteTotal = cuoPendienteTotal;
    }
    
    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public boolean isContactado() {
        return contactado;
    }

    public void setContactado(boolean contactado) {
        this.contactado = contactado;
    }

    public CobTablas getCobTablasAccion() {
        return cobTablasAccion;
    }

    public void setCobTablasAccion(CobTablas cobTablasAccion) {
        this.cobTablasAccion = cobTablasAccion;
    }

    public CobTablas getCobTablasFamilia() {
        return cobTablasFamilia;
    }

    public void setCobTablasFamilia(CobTablas cobTablasFamilia) {
        this.cobTablasFamilia = cobTablasFamilia;
    }

    public List<CobTablas> getCobTablasAccionList() {
        return cobTablasAccionList;
    }

    public void setCobTablasAccionList(List<CobTablas> cobTablasAccionList) {
        this.cobTablasAccionList = cobTablasAccionList;
    }

    public List<CobTablas> getCobTablasFamiliaList() {
        return cobTablasFamiliaList;
    }

    public void setCobTablasFamiliaList(List<CobTablas> cobTablasFamiliaList) {
        this.cobTablasFamiliaList = cobTablasFamiliaList;
    }

    public CobTablas getCobTablasAccionB() {
        return cobTablasAccionB;
    }

    public void setCobTablasAccionB(CobTablas cobTablasAccionB) {
        this.cobTablasAccionB = cobTablasAccionB;
    }

    public CobTablas getCobTablasFamiliaB() {
        return cobTablasFamiliaB;
    }

    public void setCobTablasFamiliaB(CobTablas cobTablasFamiliaB) {
        this.cobTablasFamiliaB = cobTablasFamiliaB;
    }

    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public ISeguimientoServ getSeguimientoServ() {
        return seguimientoServ;
    }

    public void setSeguimientoServ(ISeguimientoServ seguimientoServ) {
        this.seguimientoServ = seguimientoServ;
    }
 
    
    
}

