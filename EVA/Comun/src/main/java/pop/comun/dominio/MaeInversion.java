/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.List;
import pop.comun.dominio.reporte.RepSaldoDeudor;


/**
 *
 * @author Jyoverar
 */
public class MaeInversion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cMaeInversionId;
    private MaeFondo maeFondo;
    private MaeAsesor maeAsesor;
    private String cInversionId;
    private String cTipoInv;
    private String cInversion;
    private String cInversionIdOld;
    private MaePersona cPersonaId;
    private Date fColocacion;
    private Date fEmision;
    private Date fVencimiento;
    private Date finicio;
    private Date ffin;
    private Number iInversion;
    private Number nMeses;
    private Number pTasa;
    private Number nDiasAnio;
    private Number iInteres;
    private Number iMora;
    
    private Number iCuota;
    private Number cInmuebleId;
    private Number nVencimientoDia;
    private Number nCuotasAtrasadas;
    private String corigenId;
    private String ccodigoIdent;
    private String descripcion;
    private String cmoneda;
    private String simbolo;

    private String cFONDO;
    private String cbanco;
    private Number mdesde;
    private Number mhasta;
    private int nIniDiaBusq;
    private int nFinDiaBusq;
    private int ncompromPendi;
    private String eestadoCompromiso;
    private String cgeneraDoc;
    private String cTCHN;
   
    private String cESTADOCRONO;
    private Number nCUOTASPENDXGENERAR;
    private Number nTOTALCUOTAS;
    private Number iCronograma;
    private Number pTasaMor;

    
    private Number nCUOTASPAGADAS;
    private Date dFULTDEPOSITO;
    private Date dFpago;
    
    // flag de cancelado
    private String bcancelado;
    
    private MaeInmueble maeInmueble;

    private List<MaeCronograma> maeCronogramaList;
    private List<MaeDeposito> maeDepositoList;
    private List<MaeCuotaPago> maeCuotaPagoList;
    private List<CobMaeSeguimiento> cobMaeSeguimientoList;
    private List<MaeInversionEstado> maeInversionEstadoList;
    private List<MaeCargo> maeCargoList;
    private List<MaePersona> maePersonaList;
    private List<CobRequerimientoCartas> requerimientoCartasList;
    
    private RepSaldoDeudor repSaldoDeudor;

    private Number pTasa2;
    
    private Number pTcambio;

    private String apellidosnombres;    
    
    private String numeroexpediente;
    
    private String asesorId;
    
    private CobRequerimientoCartas requerimiento;

    public MaeInversion() {
    }

    public Number getnTOTALCUOTAS() {
        return nTOTALCUOTAS;
    }

    public void setnTOTALCUOTAS(Number nTOTALCUOTAS) {
        this.nTOTALCUOTAS = nTOTALCUOTAS;
    }
    
    public boolean getVerificaEstado(){
        boolean rpta = true;
        for(MaeInversionEstado est : maeInversionEstadoList){
            
            if(est.getEEstadoId().equals("0002") ){
                rpta = false;
            }
        }
        
        return rpta;
    }
    

    public String getcTCHN() {
        return cTCHN;
    }

    public void setcTCHN(String cTCHN) {
        this.cTCHN = cTCHN;
    }

    public String getcFONDO() {
        return cFONDO;
    }

    public void setcFONDO(String cFONDO) {
        this.cFONDO = cFONDO;
    }

    public String getcESTADOCRONO() {
        return cESTADOCRONO;
    }

    public void setcESTADOCRONO(String cESTADOCRONO) {
        this.cESTADOCRONO = cESTADOCRONO;
    }

    public Number getnCUOTASPENDXGENERAR() {
        return nCUOTASPENDXGENERAR;
    }

    public void setnCUOTASPENDXGENERAR(Number nCUOTASPENDXGENERAR) {
        this.nCUOTASPENDXGENERAR = nCUOTASPENDXGENERAR;
    }


    public Number getnCUOTASPAGADAS() {
        return nCUOTASPAGADAS;
    }

    public void setnCUOTASPAGADAS(Number nCUOTASPAGADAS) {
        this.nCUOTASPAGADAS = nCUOTASPAGADAS;
    }

    public Date getdFULTDEPOSITO() {
        return dFULTDEPOSITO;
    }

    public void setdFULTDEPOSITO(Date dFULTDEPOSITO) {
        this.dFULTDEPOSITO = dFULTDEPOSITO;
    }

    public Date getdFpago() {
        return dFpago;
    }

    public void setdFpago(Date dFpago) {
        this.dFpago = dFpago;
    }
    
    public String getCInversion() {
        return cInversion;
    }

    public void setCInversion(String cInversion) {
        this.cInversion = cInversion;
    }

    public String getCInversionIdOld() {
        return cInversionIdOld;
    }

    public void setCInversionIdOld(String cInversionIdOld) {
        this.cInversionIdOld = cInversionIdOld;
    }

    public Date getFColocacion() {
        return fColocacion;
    }

    public void setFColocacion(Date fColocacion) {
        this.fColocacion = fColocacion;
    }

    public Date getFEmision() {
        return fEmision;
    }

    public void setFEmision(Date fEmision) {
        this.fEmision = fEmision;
    }

    public Date getFVencimiento() {
        return fVencimiento;
    }

    public void setFVencimiento(Date fVencimiento) {
        this.fVencimiento = fVencimiento;
    }

    public Number getIInversion() {
        return iInversion;
    }

    public void setIInversion(Number iInversion) {
        this.iInversion = iInversion;
    }

    public Number getNMeses() {
        return nMeses;
    }

    public void setNMeses(Number nMeses) {
        this.nMeses = nMeses;
    }

    public Number getPTasa() {
        return pTasa;
    }

    public void setPTasa(Number pTasa) {
        this.pTasa = pTasa;
    }

    public Number getPTasa2() {
        return pTasa2;
    }

    public void setPTasa2(Number pTasa2) {
        this.pTasa2 = pTasa2;
    }
    public Number getNDiasAnio() {
        return nDiasAnio;
    }

    public void setNDiasAnio(Number nDiasAnio) {
        this.nDiasAnio = nDiasAnio;
    }

    public Number getIInteres() {
        return iInteres;
    }

    public void setIInteres(Number iInteres) {
        this.iInteres = iInteres;
    }

    public Number getICuota() {
        return iCuota;
    }

    public void setICuota(Number iCuota) {
        this.iCuota = iCuota;
    }

    public Number getCInmuebleId() {
        return cInmuebleId;
    }

    public void setCInmuebleId(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    public List<MaeCuotaPago> getMaeCuotaPagoList() {
        return maeCuotaPagoList;
    }

    public void setMaeCuotaPagoList(List<MaeCuotaPago> maeCuotaPagoList) {
        this.maeCuotaPagoList = maeCuotaPagoList;
    }

    public List<CobMaeSeguimiento> getCobMaeSeguimientoList() {
        return cobMaeSeguimientoList;
    }

    public void setCobMaeSeguimientoList(List<CobMaeSeguimiento> cobMaeSeguimientoList) {
        this.cobMaeSeguimientoList = cobMaeSeguimientoList;
    }

    public List<MaeCronograma> getMaeCronogramaList() {
        return maeCronogramaList;
    }

    public void setMaeCronogramaList(List<MaeCronograma> maeCronogramaList) {
        this.maeCronogramaList = maeCronogramaList;
    }

    public List<MaeDeposito> getMaeDepositoList() {
        return maeDepositoList;
    }

    public void setMaeDepositoList(List<MaeDeposito> maeDepositoList) {
        this.maeDepositoList = maeDepositoList;
    }

    public List<MaeInversionEstado> getMaeInversionEstadoList() {
        return maeInversionEstadoList;
    }

    public void setMaeInversionEstadoList(List<MaeInversionEstado> maeInversionEstadoList) {
        this.maeInversionEstadoList = maeInversionEstadoList;
    }

    public List<MaeCargo> getMaeCargoList() {
        return maeCargoList;
    }

    public void setMaeCargoList(List<MaeCargo> maeCargoList) {
        this.maeCargoList = maeCargoList;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getcMaeInversionId() != null ? getcMaeInversionId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeInversion)) {
            return false;
        }
        MaeInversion other = (MaeInversion) object;
        if ((this.getcMaeInversionId() == null && other.getcMaeInversionId() != null) || (this.getcMaeInversionId() != null && !this.cMaeInversionId.equals(other.cMaeInversionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeInversion[ maeInversionPK=" + getcMaeInversionId() + " ]";
    }

 
    /**
     * @return the cInversionId
     */
    public String getcInversionId() {
        return cInversionId;
    }

    /**
     * @param cInversionId the cInversionId to set
     */
    public void setcInversionId(String cInversionId) {
        this.cInversionId = cInversionId;
    }


    /**
     * @return the cPersonaId
     */
    public MaePersona getcPersonaId() {
        return cPersonaId;
    }

    /**
     * @param cPersonaId the cPersonaId to set
     */
    public void setcPersonaId(MaePersona cPersonaId) {
        this.cPersonaId = cPersonaId;
    }

    /**
     * @return the maeFondo
     */
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    /**
     * @param maeFondo the maeFondo to set
     */
    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    /**
     * @return the maeFondo
     */
    public MaeAsesor getMaeAsesor() {
        return maeAsesor;
    }

    /**
     * @param maeAsesor the maeFondo to set
     */
    public void setMaeAsesor(MaeAsesor maeAsesor) {
        this.maeAsesor = maeAsesor;
    }
    
    /**
     * @return the cMaeInversionId
     */
    public Number getcMaeInversionId() {
        return cMaeInversionId;
    }

    /**
     * @param cMaeInversionId the cMaeInversionId to set
     */
    public void setcMaeInversionId(Number cMaeInversionId) {
        this.cMaeInversionId = cMaeInversionId;
    }

    /**
     * @return the nVencimientoDia
     */
    public Number getNVencimientoDia() {
        return nVencimientoDia;
    }

    /**
     * @param nVencimientoDia the nVencimientoDia to set
     */
    public void setNVencimientoDia(Number nVencimientoDia) {
        this.nVencimientoDia = nVencimientoDia;
    }

    /**
     * @return the nCuotasAtrasadas
     */
    public Number getNCuotasAtrasadas() {
        return nCuotasAtrasadas;
    }

    /**
     * @param nCuotasAtrasadas the nCuotasAtrasadas to set
     */
    public void setNCuotasAtrasadas(Number nCuotasAtrasadas) {
        this.nCuotasAtrasadas = nCuotasAtrasadas;
    }

    public int getNIniDiaBusq() {
        return nIniDiaBusq;
    }

    public void setNIniDiaBusq(int nIniDiaBusq) {
        this.nIniDiaBusq = nIniDiaBusq;
    }

    public int getNFinDiaBusq() {
        return nFinDiaBusq;
    }

    public void setNFinDiaBusq(int nFinDiaBusq) {
        this.nFinDiaBusq = nFinDiaBusq;
    }

    /**
     * @return the maeInmueble
     */
    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    /**
     * @param maeInmueble the maeInmueble to set
     */
    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public String getcTipoInv() {
        return cTipoInv;
    }

    public void setcTipoInv(String cTipoInv) {
        this.cTipoInv = cTipoInv;
    }

    public String getCorigenId() {
        return corigenId;
    }

    public void setCorigenId(String corigenId) {
        this.corigenId = corigenId;
    }

    public String getCcodigoIdent() {
        return ccodigoIdent;
    }

    public void setCcodigoIdent(String ccodigoIdent) {
        this.ccodigoIdent = ccodigoIdent;
    }

    public String getEestadoCompromiso() {
        return eestadoCompromiso;
    }

    public void setEestadoCompromiso(String eestadoCompromiso) {
        this.eestadoCompromiso = eestadoCompromiso;
    }

    public int getNcompromPendi() {
        return ncompromPendi;
    }

    public void setNcompromPendi(int ncompromPendi) {
        this.ncompromPendi = ncompromPendi;
    }

    public String getBcancelado() {
        return bcancelado;
    }

    public void setBcancelado(String bcancelado) {
        this.bcancelado = bcancelado;
    }

    public List<MaePersona> getMaePersonaList() {
        return maePersonaList;
    }

    public void setMaePersonaList(List<MaePersona> maePersonaList) {
        this.maePersonaList = maePersonaList;
    }

    public String getCgeneraDoc() {
        return cgeneraDoc;
    }

    public void setCgeneraDoc(String cgeneraDoc) {
        this.cgeneraDoc = cgeneraDoc;
    }

    public RepSaldoDeudor getRepSaldoDeudor() {
        return repSaldoDeudor;
    }

    public void setRepSaldoDeudor(RepSaldoDeudor repSaldoDeudor) {
        this.repSaldoDeudor = repSaldoDeudor;
    }

    public Date getFinicio() {
        return finicio;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }   
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
    
     public String getCbanco() {
        return cbanco;
    }

    public void setCbanco(String cbanco) {
        this.cbanco = cbanco;
    }
    
    public Number getMdesde() {
        return mdesde;
    }

    public void setMdesde(Number mdesde) {
        this.mdesde = mdesde;
    }

    public Number getMhasta() {
        return mhasta;
    }

    public void setMhasta(Number mhasta) {
        this.mhasta = mhasta;
    }    
    
     public Number getiCronograma() {
        return iCronograma;
    }

    public void setiCronograma(Number iCronograma) {
        this.iCronograma = iCronograma;
    }

    public Number getpTasaMor() {
        return pTasaMor;
    }

    public void setpTasaMor(Number pTasaMor) {
        this.pTasaMor = pTasaMor;
    }
    
    public Number getiMora() {
        return iMora;
    }

    public void setiMora(Number iMora) {
        this.iMora = iMora;
    }
	
    public String getCmoneda() {
        return cmoneda;
    }

    public void setCmoneda(String cmoneda) {
        this.cmoneda = cmoneda;
    }
    
    
    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    
    public Number getPTcambio() {
        return pTcambio;
    }

    public void setPTcambio(Number pTcambio) {
        this.pTcambio = pTcambio;
    }

    /**
     * @return the apellidosnombres
     */
    public String getApellidosnombres() {
        return apellidosnombres;
    }

    /**
     * @param apellidosnombres the apellidosnombres to set
     */
    public void setApellidosnombres(String apellidosnombres) {
        this.apellidosnombres = apellidosnombres;
    }

   /**
     * @return the numeroexpediente
     */
    public String getNumeroexpediente() {
        return numeroexpediente;
    }

    /**
     * @param numeroexpediente the numeroexpediente to set
     */
    public void setNumeroexpediente(String numeroexpediente) {
        this.numeroexpediente = numeroexpediente;
    }  

    /**
     * @return the asesorId
     */
    public String getAsesorId() {
        return asesorId;
    }

    /**
     * @param asesorId the asesorId to set
     */
    public void setAsesorId(String asesorId) {
        this.asesorId = asesorId;
    }

    /**
     * @return the RequerimientoCartasList
     */
    public List<CobRequerimientoCartas> getRequerimientoCartasList() {
        return requerimientoCartasList;
    }

    /**
     * @param requerimientoCartasList the requerimientoCartasList to set
     */
    public void setRequerimientoCartasList(List<CobRequerimientoCartas> requerimientoCartasList) {
        this.requerimientoCartasList = requerimientoCartasList;
    }

    /**
     * @return the requerimiento
     */
    public CobRequerimientoCartas getRequerimiento() {
        return requerimiento;
    }

    /**
     * @param requerimiento the requerimiento to set
     */
    public void setRequerimiento(CobRequerimientoCartas requerimiento) {
        this.requerimiento = requerimiento;
    }

}
