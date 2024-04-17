/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.LegEtapa;
import pop.comun.dominio.LegDemanTchn;
import pop.comun.dominio.LegGastoJudicial;
import pop.comun.dominio.LegOtroProceso;
import pop.comun.dominio.LegSeguiTchn;
import pop.comun.dominio.LegSgtoOtroProceso;
import pop.comun.dominio.LegTabla;
import pop.comun.dominio.LegalAsesor;
import pop.comun.dominio.LegalTchn;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeEstadoLegal;
import pop.comun.dominio.MaeMes;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegEstadoCuenta;
import pop.webcobranzas.negocio.INegLegal;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbLegal")
public class SessionLegal implements INegLegal {

    FactoryDao ofDao = new FactoryDao();

     @Override
    public List<LegalTchn> listarTchnLegalMODY(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchAllTLegalMody(oLegalTchn);
        return legalTchnList;
    }
    
     @Override
    public List<LegalTchn> listarTchnLegal(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchAllTLegal(oLegalTchn);
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn> listarTchnLegalRepo(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchAllTLegalRepo(oLegalTchn);
        return legalTchnList;
    }
    
     @Override
    public List<LegalTchn> listarEtapaTchnLegal(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchEtapaAllTLegal(oLegalTchn);
        return legalTchnList;
    }
    
      @Override
    public List<LegalTchn> listarEtapaTchnMODY(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchEtapaAllTMODY(oLegalTchn);
        return legalTchnList;
    }
    
      @Override
    public List<LegSeguiTchn> listarSeguiTchnLegal(LegSeguiTchn olegSeguiTchn) throws Exception {
        List<LegSeguiTchn> LegSeguiTchnList = null;

        LegSeguiTchnList = ofDao.getLegal().fetchSeguiAllTLegal(olegSeguiTchn);
        return LegSeguiTchnList;
    }
    
      @Override
    public List<LegSeguiTchn> listarSeguiTchnLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        List<LegSeguiTchn> LegSeguiTchnList = null;

        LegSeguiTchnList = ofDao.getLegal().fetchSeguiAllTLegalMODY(olegSeguiTchn);
        return LegSeguiTchnList;
    }
    
     @Override
    public List<MaeEstadoLegal> listarEstadoTchnLegal(String IdEtapa) throws Exception {
        List<MaeEstadoLegal> MaeEstadoLegalList = null;

        MaeEstadoLegalList = ofDao.getLegal().fetchEstadoAllTLegal(IdEtapa);
        
        return MaeEstadoLegalList;
    }
    
    @Override
    public List<MaeEstadoLegal> listarEstadoTchnLegalMODY() throws Exception {
        List<MaeEstadoLegal> MaeEstadoLegalList = null;

        MaeEstadoLegalList = ofDao.getLegal().fetchEstadoAllTLegalMODY();
        
        return MaeEstadoLegalList;
    }

  
     @Override
    public List<MaeEstadoLegal> listarEspecialTchnLegal(String codigo, String codjuz) throws Exception {
        List<MaeEstadoLegal> MaeEstadoLegalList = null;

        MaeEstadoLegalList = ofDao.getLegal().fetchEspecialAllTLegal(codigo, codjuz);
        
        return MaeEstadoLegalList;
    }
    
    @Override
    public Integer insertarDemanda(LegalTchn oLegalTchn) throws Exception {
        int rep=0;
        
        rep = ofDao.getLegal().insertDemanda(oLegalTchn);
        return rep;
    }
    
    
    @Override
    public Integer insertarDemandaMODY(LegalTchn oLegalTchn) throws Exception {
        int rep=0;
        
        rep = ofDao.getLegal().insertDemandaMODY(oLegalTchn);
        return rep;
    }
    
    @Override
    public Integer insertarSegDemanda(LegSeguiTchn olegSeguiTchn) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().insertSegDemanda(olegSeguiTchn);
        return rep;
    }

    @Override
    public Integer insertarSegDemandaMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().insertSegDemandaMODY(olegSeguiTchn);
        return rep;
    }
    
    @Override
    public Integer eliminarMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().eliminarMODY(olegSeguiTchn);
        return rep;
    }
    

  @Override
    public List<LegSeguiTchn> modificarSegLegal(LegSeguiTchn olegSeguiTchn) throws Exception {
        List<LegSeguiTchn> LegSeguiTchnList = null;

        LegSeguiTchnList = ofDao.getLegal().fetchModificarSeguiAllTLegal(olegSeguiTchn);
        return LegSeguiTchnList;
    }
    
    @Override
    public List<LegSeguiTchn> modificarSegLegalMODY(LegSeguiTchn olegSeguiTchn) throws Exception {
        List<LegSeguiTchn> LegSeguiTchnList = null;

        LegSeguiTchnList = ofDao.getLegal().fetchModificarSeguiAllTMODY(olegSeguiTchn);
        return LegSeguiTchnList;
    }
    @Override
    public Integer CerrarEtapaSegDemanda(LegSeguiTchn olegSeguiTchn) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().fetchCerrarEtapaSegDemanda(olegSeguiTchn);
        return rep;
    }


    @Override
    public List<LegalTchn> ListaSeguidores(LegalTchn oLegalTchn) throws Exception {
         List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchListaSeguidores(oLegalTchn);
        return legalTchnList;
    }

    @Override
    public List<LegalTchn> listarTchnLegalAsesor(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().listarTchnLegalAsesor(oLegalTchn);
        return legalTchnList;
    }
    
    @Override
    public List<List<Object>> cargarDashboardActivo(String LsUsuario) throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardActivo(LsUsuario);
        return legalTchnList;
    }
 
    
    @Override
    public List<List<Object>> cargarDashboardCancelado(String LsUsuario) throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardCancelado(LsUsuario);
        return legalTchnList;
    }
 
    
    @Override
    public List<LegalTchn>  lista_Dashboard_etapaxfondo() throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().lista_Dashboard_etapaxfondo();
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn>  lista_Dashboard_etapaDesem() throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().lista_Dashboard_etapaDesem();
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn>  lista_Dashboard_AseCal() throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().lista_Dashboard_AseCal();
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn>  lista_Dashboard_x_asesor() throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().lista_Dashboard_x_asesor();
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn>  lista_Dashboard_fondoDese() throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().lista_Dashboard_fondoDese();
        return legalTchnList;
    }
    
    
    @Override
    public List<List<Object>> cargarDashboardEtapaxFondo() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardEtapaxFondo();
        return legalTchnList;
    }
    
     @Override
    public List<List<Object>> cargarDashboardEtapaxDesem() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardEtapaxDesem();
        return legalTchnList;
    }
    
    @Override
    public List<List<Object>> cargarDashboardFondoxIndi() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardFondoxIndi();
        return legalTchnList;
    }
    
    @Override
    public List<List<Object>> cargarDashboardAsesorxIndi() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().cargarDashboardAsesorxIndi();
        return legalTchnList;
    }

    @Override
    public List<MaeMes> listar_mes() throws Exception {
        List<MaeMes> maeMesList = null;;
        maeMesList = ofDao.getLegal().fetchListarMes();
        return maeMesList;
    }
    
    @Override
    public List<MaeAnio> listar_anio() throws Exception {
        List<MaeAnio> maeAnioList = null;;
        maeAnioList = ofDao.getLegal().fetchListarAnio();
        return maeAnioList;
    }

    @Override
    public List<LegalTchn> listar_LegalImpxfondo(String Mes, String Anio) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLegalImpxfondo(Mes,Anio);
        return legalTchnList;
    }

    @Override
    public List<LegalTchn> lista_TopVisiCap(String Mes, String Anio) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_TopVisiCap(Mes,Anio);
        return legalTchnList;
    }
    
    
     @Override
    public List<LegalTchn> listarActivoJudicial(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().listarActivoJudicial(oLegalTchn);
        return legalTchnList;
    }


     @Override
    public List<LegalTchn> lista_TopVisiPop(String Mes, String Anio) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_TopVisiPop(Mes,Anio);
        return legalTchnList;
    }
    
      @Override
    public List<LegalTchn> lista_TopVisiMyp(String Mes, String Anio) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_TopVisiMyp(Mes,Anio);
        return legalTchnList;
    }
      @Override
    public List<LegalTchn> lista_TopVisiPrh(String Mes, String Anio) throws Exception {
        List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_TopVisiPrh(Mes,Anio);
        return legalTchnList;
    }
    
    @Override
    public List<List<Object>>  cargarDashboard_Impxfondo() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchDashboard_Impxfondo();
        return legalTchnList;
    }

    @Override
    public List<LegalTchn> lista_ImpTopneg(String Mes, String Anio) throws Exception {
         List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchlista_ImpTopneg(Mes,Anio);
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn> Lista_xAseEtapa(String Mes, String Anio) throws Exception {
         List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_xAseEtapa(Mes,Anio);
        return legalTchnList;
    }
    
    @Override
    public List<LegalTchn> Lista_xAseIndicador(String Mes, String Anio) throws Exception {
         List<LegalTchn> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchLista_xAseIndicador(Mes,Anio);
        return legalTchnList;
    }
    
     @Override
    public List<List<Object>> Dashboard_xAseIndicador() throws Exception {
        List<List<Object>> legalTchnList = null;
        legalTchnList = ofDao.getLegal().fetchDashboard_xAseIndicador();
        return legalTchnList;
    }
    
    @Override
    public List<LegalAsesor> listar_asesores() throws Exception {
        List<LegalAsesor> legalAsesorList = null;
        legalAsesorList = ofDao.getLegalAsesor().fetchAll();
        return legalAsesorList;
    }
    
   @Override
    public List<LegGastoJudicial> listarGastosJudiciales(LegGastoJudicial oLegGastoJudicial) throws Exception {
        List<LegGastoJudicial> legGastoJudicialList = null;
        legGastoJudicialList = ofDao.getLegalGastosJudiciales().fetchAll(oLegGastoJudicial);
        return legGastoJudicialList;
    }
    
    @Override
    public List<LegEtapa> listarEtapas() throws Exception {
        List<LegEtapa> etapaList = null;
        etapaList = ofDao.getEtapas().fetchAll();
        return etapaList;
    }
    
    @Override
    public List<LegTabla> listarTiposGastosJudiciales() throws Exception {
        List<LegTabla> tablaList = null;
        tablaList = ofDao.getTiposGastosJudiciales().fetchAll();
        return tablaList;
    }
    
    @Override
    public Integer insertarGastoJudicial(LegGastoJudicial olegGastoJudi) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().insertarGastoJudicial(olegGastoJudi);
        return rep;
    }
    
    @Override
    public Integer modificarGastoJudicial(LegGastoJudicial olegGastoJudi) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().modificarGastoJudicial(olegGastoJudi);
        return rep;
    }
    
    @Override
    public List<LegalTchn> listarTchnLegalForGastos(LegalTchn oLegalTchn) throws Exception {
        List<LegalTchn> legalTchnList = null;

        legalTchnList = ofDao.getLegal().fetchAllTLegalForGastos(oLegalTchn);
        return legalTchnList;
    }
    
    @Override
    public List<LegGastoJudicial> listarConsultaGastos(LegGastoJudicial oLegGastoJudicial) throws Exception {
        List<LegGastoJudicial> legGastoJudicialList = null;

        legGastoJudicialList = ofDao.getLegal().fetchAllConsultaGastos(oLegGastoJudicial);
        return legGastoJudicialList;
    }
    
    @Override
    public List<LegTabla> listarMateriaOtrosProcesos() throws Exception {
        List<LegTabla> tablaList = null;
        tablaList = ofDao.getLegTabla().fetchAll("0003");//0003: Materia otros procesos
        return tablaList;
    }

    @Override
    public List<LegTabla> listarTipoOtrosProcesos() throws Exception {
        List<LegTabla> tablaList = null;
        tablaList = ofDao.getLegTabla().fetchAll("0004");//0004: Tipo otros procesos
        return tablaList;
    }
    
    @Override
    public List<LegOtroProceso> listarOtrosProcesos(LegOtroProceso oLegOtroProceso) throws Exception {
        List<LegOtroProceso> legalOtroProcesoList = null;
        legalOtroProcesoList = ofDao.getLegal().fetchAllOtroProceso(oLegOtroProceso);
        return legalOtroProcesoList;
    }
    
    @Override
    public Integer insertarOtroProceso(LegOtroProceso oLegOtroProceso) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().insertarOtroProceso(oLegOtroProceso);
        return rep;
    }    
    
    @Override
    public Integer modificarOtroProceso(LegOtroProceso oLegOtroProceso) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().modificarOtroProceso(oLegOtroProceso);
        return rep;
    }
    
    @Override
    public List<LegSgtoOtroProceso> listarSgtoOtrosProcesos(LegSgtoOtroProceso oLegSgtoOtroProceso) throws Exception {
        List<LegSgtoOtroProceso> legalSgtoOtroProcesoList = null;
        legalSgtoOtroProcesoList = ofDao.getLegal().fetchAllSgtoOtroProceso(oLegSgtoOtroProceso);
        return legalSgtoOtroProcesoList;
    }
    
    @Override
    public Integer insertarSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().insertarSgtoOtroProceso(oLegSgtoOtroProceso);
        return rep;
    }    
    
    @Override
    public Integer modificarSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso) throws Exception {
        int rep=0;
        rep = ofDao.getLegal().modificarSgtoOtroProceso(oLegSgtoOtroProceso);
        return rep;
    }      
    
    @Override
    public List<LegSgtoOtroProceso> consultaSgtoOtrosProcesos(LegSgtoOtroProceso oLegSgtoOtroProceso) throws Exception {
        List<LegSgtoOtroProceso> legalSgtoOtroProcesoList = null;
        legalSgtoOtroProcesoList = ofDao.getLegal().fetchAllConsultaSgtoOtroProceso(oLegSgtoOtroProceso);
        return legalSgtoOtroProcesoList;
    }    
}
