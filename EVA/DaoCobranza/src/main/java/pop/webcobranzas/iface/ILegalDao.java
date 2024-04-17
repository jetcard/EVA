/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import java.util.List;
import pop.comun.dominio.LegGastoJudicial;
import pop.comun.dominio.LegOtroProceso;
import pop.comun.dominio.LegSeguiTchn;
import pop.comun.dominio.LegSgtoOtroProceso;
import pop.comun.dominio.LegalTchn;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeEstadoLegal;
import pop.comun.dominio.MaeMes;

/**
 *
 * @author Jyoverar
 */
public interface ILegalDao {
    
    ArrayList<LegalTchn> fetchAllTLegal(LegalTchn oLegalTchn);
    ArrayList<LegalTchn> fetchAllTLegalRepo(LegalTchn oLegalTchn);
    ArrayList<LegalTchn> fetchEtapaAllTLegal(LegalTchn oLegalTchn);
    ArrayList<LegSeguiTchn> fetchSeguiAllTLegal(LegSeguiTchn olegSeguiTchn);
    ArrayList<MaeEstadoLegal> fetchEstadoAllTLegal(String idEtapa);
    ArrayList<MaeEstadoLegal> fetchEspecialAllTLegal(String codigo,String codjuz);
    Integer insertDemanda(LegalTchn oLegalTchn);
    Integer insertSegDemanda(LegSeguiTchn olegSeguiTchn);
    ArrayList<LegSeguiTchn> fetchModificarSeguiAllTLegal(LegSeguiTchn olegSeguiTchn);
    Integer fetchCerrarEtapaSegDemanda(LegSeguiTchn olegSeguiTchn);
    ArrayList<LegalTchn> listarTchnLegalAsesor(LegalTchn oLegalTchn);
    ArrayList<LegalTchn> listarActivoJudicial(LegalTchn oLegalTchn);
    
    ArrayList<LegalTchn> fetchListaSeguidores(LegalTchn oLegalTchn);
    Integer insertDemandaMODY(LegalTchn oLegalTchn);
    ArrayList<LegalTchn> fetchAllTLegalMody(LegalTchn oLegalTchn);
    ArrayList<LegSeguiTchn> fetchModificarSeguiAllTMODY(LegSeguiTchn olegSeguiTchn);
    ArrayList<MaeEstadoLegal> fetchEstadoAllTLegalMODY();
    ArrayList<LegSeguiTchn> fetchSeguiAllTLegalMODY(LegSeguiTchn olegSeguiTchn);
    ArrayList<LegalTchn> fetchEtapaAllTMODY(LegalTchn oLegalTchn);
    Integer insertSegDemandaMODY(LegSeguiTchn olegSeguiTchn);
    Integer eliminarMODY(LegSeguiTchn olegSeguiTchn);
    
    ArrayList<List<Object>>cargarDashboardActivo(String LsUsuario);
    ArrayList<List<Object>>cargarDashboardCancelado(String LsUsuario);
    ArrayList<List<Object>>cargarDashboardEtapaxFondo();
    ArrayList<LegalTchn> lista_Dashboard_etapaxfondo();
    ArrayList<LegalTchn> lista_Dashboard_etapaDesem();
    ArrayList<LegalTchn> lista_Dashboard_AseCal();
    ArrayList<LegalTchn> lista_Dashboard_x_asesor();
    ArrayList<LegalTchn> lista_Dashboard_fondoDese();
    ArrayList<List<Object>>cargarDashboardEtapaxDesem();
    
    ArrayList<List<Object>>cargarDashboardFondoxIndi();
    ArrayList<List<Object>>cargarDashboardAsesorxIndi();
    
    ArrayList<MaeMes> fetchListarMes();
    ArrayList<MaeAnio> fetchListarAnio();
    ArrayList<LegalTchn> fetchLegalImpxfondo(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_TopVisiCap(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_TopVisiPop(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_TopVisiMyp(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_TopVisiPrh(String Mes, String Anio);
    ArrayList<List<Object>> fetchDashboard_Impxfondo();
    ArrayList<LegalTchn> fetchlista_ImpTopneg(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_xAseEtapa(String Mes, String Anio);
    ArrayList<LegalTchn> fetchLista_xAseIndicador(String Mes, String Anio);
    ArrayList<List<Object>> fetchDashboard_xAseIndicador();
    Integer insertarGastoJudicial(LegGastoJudicial olegGastoJudi);
    Integer modificarGastoJudicial(LegGastoJudicial olegGastoJudi);
    ArrayList<LegalTchn> fetchAllTLegalForGastos(LegalTchn oLegalTchn);
    ArrayList<LegGastoJudicial> fetchAllConsultaGastos(LegGastoJudicial oLegGastoJudicial);
    
    ArrayList<LegOtroProceso> fetchAllOtroProceso(LegOtroProceso oLegOtroProceso);
    Integer insertarOtroProceso(LegOtroProceso oLegOtroProceso);
    Integer modificarOtroProceso(LegOtroProceso oLegOtroProceso);
    ArrayList<LegSgtoOtroProceso> fetchAllSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso);
    Integer insertarSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso);
    Integer modificarSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso);
    ArrayList<LegSgtoOtroProceso> fetchAllConsultaSgtoOtroProceso(LegSgtoOtroProceso oLegSgtoOtroProceso);
}
