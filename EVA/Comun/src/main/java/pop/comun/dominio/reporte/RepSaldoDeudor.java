	/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio.reporte;

import java.util.Date;
import java.util.List;
import pop.comun.dominio.Base;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeInversion;

/**
 *
 * @author Jyoverar
 */
public class RepSaldoDeudor extends Base{

    private static final long serialVersionUID = 1L;
    
    private MaeInversion maeInversion;
    private Date factual;
    private Date ffutura;
    private Date fultCuota;
    private double icapFut;
    private double iicFut;
    private double icapAtra;
    private double iicAtra;
    private double iicAAtra;
    private double isaldoFavor;
    private double iimAtra;
    private double icargCuoAtra;
    private double igastLegalFut;
    private double igastAdmin;
    private double ntotAtra;
    private double ntotFut;
    private double nfavorFondo;
    private double ntotDebe;
    private Integer nDiasPago;
    private Integer nCuotas ;
    private String ls_estadoCrono;
    private double nTasaDia;
    private double nTasa15Dia;
    private double pTcambio;

   
    private String xFLGNew;
    private String ImpresionSD;
    private String ultcuota;
    private Date fproxcuota;

    
    private List<MaeCronograma> maeCronogramaList;
    private List<MaeInversion> maeInversionList;

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public Date getFactual() {
        return factual;
    }

    public void setFactual(Date factual) {
        this.factual = factual;
    }

    public Date getFfutura() {
        return ffutura;
    }

    public void setFfutura(Date ffutura) {
        this.ffutura = ffutura;
    }

    public Date getFultCuota() {
        return fultCuota;
    }

    public void setFultCuota(Date fultCuota) {
        this.fultCuota = fultCuota;
    }

    public double getIcapFut() {
        return icapFut;
    }

    public void setIcapFut(double icapFut) {
        this.icapFut = icapFut;
    }

    public double getIicFut() {
        return iicFut;
    }

    public void setIicFut(double iicFut) {
        this.iicFut = iicFut;
    }

    public double getIcapAtra() {
        return icapAtra;
    }

    public void setIcapAtra(double icapAtra) {
        this.icapAtra = icapAtra;
    }

    public double getIsaldoFavor() {
        return isaldoFavor;
    }

    public void setIsaldoFavor(double isaldoFavor) {
        this.isaldoFavor = isaldoFavor;
    }

    public double getIcargCuoAtra() {
        return icargCuoAtra;
    }

    public void setIcargCuoAtra(double icargCuoAtra) {
        this.icargCuoAtra = icargCuoAtra;
    }

    public double getIgastLegalFut() {
        return igastLegalFut;
    }

    public void setIgastLegalFut(double igastLegalFut) {
        this.igastLegalFut = igastLegalFut;
    }

    public double getNtotFut() {
        return ntotFut;
    }

    public void setNtotFut(double ntotFut) {
        this.ntotFut = ntotFut;
    }

    public double getNtotDebe() {
        return ntotDebe;
    }

    public void setNtotDebe(double ntotDebe) {
        this.ntotDebe = ntotDebe;
    }

    public double getIicAtra() {
        return iicAtra;
    }

    public void setIicAtra(double iicAtra) {
        this.iicAtra = iicAtra;
    }

    public double getIimAtra() {
        return iimAtra;
    }

    public void setIimAtra(double iimAtra) {
        this.iimAtra = iimAtra;
    }

    public double getIgastAdmin() {
        return igastAdmin;
    }

    public void setIgastAdmin(double igastAdmin) {
        this.igastAdmin = igastAdmin;
    }

    public double getNtotAtra() {
        return ntotAtra;
    }

    public void setNtotAtra(double ntotAtra) {
        this.ntotAtra = ntotAtra;
    }

    public double getNfavorFondo() {
        return nfavorFondo;
    }

    public void setNfavorFondo(double nfavorFondo) {
        this.nfavorFondo = nfavorFondo;
    }

    public List<MaeCronograma> getMaeCronogramaList() {
        return maeCronogramaList;
    }

    public void setMaeCronogramaList(List<MaeCronograma> maeCronogramaList) {
        this.maeCronogramaList = maeCronogramaList;
    }
    
    public Integer getnDiasPago() {
        return nDiasPago;
    }

    public void setnDiasPago(Integer nDiasPago) {
        this.nDiasPago = nDiasPago;
    }

    public Integer getnCuotas() {
        return nCuotas;
    }

    public void setnCuotas(Integer nCuotas) {
        this.nCuotas = nCuotas;
    }
    
    public String getLs_estadoCrono() {
        return ls_estadoCrono;
    }

    public void setLs_estadoCrono(String ls_estadoCrono) {
        this.ls_estadoCrono = ls_estadoCrono;
    }
    
    
     public double getnTasaDia() {
        return nTasaDia;
    }

    public void setnTasaDia(double nTasaDia) {
        this.nTasaDia = nTasaDia;
    }

    public double getnTasa15Dia() {
        return nTasa15Dia;
    }

    public void setnTasa15Dia(double nTasa15Dia) {
        this.nTasa15Dia = nTasa15Dia;
    }
    
    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }
    
    public String getxFLGNew() {
        return xFLGNew;
    }

    public void setxFLGNew(String xFLGNew) {
        this.xFLGNew = xFLGNew;
    }
    
    public String getImpresionSD() {
        return ImpresionSD;
    }

    public void setImpresionSD(String ImpresionSD) {
        this.ImpresionSD = ImpresionSD;
    }
    
    public String getUltcuota() {
        return ultcuota;
    }

    public void setUltcuota(String ultcuota) {
        this.ultcuota = ultcuota;
    }
    
    
    public Date getFproxcuota() {
        return fproxcuota;
    }

    public void setFproxcuota(Date fproxcuota) {
        this.fproxcuota = fproxcuota;
    }
    
    public double getIicAAtra() {
        return iicAAtra;
    }

    public void setIicAAtra(double iicAAtra) {
        this.iicAAtra = iicAAtra;
    }
    
     public double getpTcambio() {
        return pTcambio;
    }

    public void setpTcambio(double pTcambio) {
        this.pTcambio = pTcambio;
    }
}
