/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dao;

import oracle.jdbc.OracleConnection;

import pop.webcobranzas.iface.IAlertaPrejudicialDao;
import pop.webcobranzas.iface.ICobCdrDao;
import pop.webcobranzas.iface.ICobCompromisoDao;
import pop.webcobranzas.iface.ICobCronogramaMetaAsesorDao;
import pop.webcobranzas.iface.ICobCronogramaMetaCabeceraDao;
import pop.webcobranzas.iface.ICobCronogramaMetaDetalleDao;
import pop.webcobranzas.iface.ICobCronogramaMetaResumenDao;
import pop.webcobranzas.iface.ICobLlamadasDao;
import pop.webcobranzas.iface.ICobMaeSeguimientoDao;
import pop.webcobranzas.iface.ICobSeguimientoDao;
import pop.webcobranzas.iface.ICobTablasDao;
import pop.webcobranzas.iface.IConexionDao;
import pop.webcobranzas.iface.ILaftPersonaDao;
import pop.webcobranzas.iface.ILegalDao;
import pop.webcobranzas.iface.IMaeBancoDao;
import pop.webcobranzas.iface.IMaeCargoDao;
import pop.webcobranzas.iface.IMaeCronogramaDao;
import pop.webcobranzas.iface.IMaeCuotaPagoDao;
import pop.webcobranzas.iface.IMaeDashboardDao;
import pop.webcobranzas.iface.IMaeDepositoDao;
import pop.webcobranzas.iface.IMaeDireccionDao;
import pop.webcobranzas.iface.IMaeEmailDao;
import pop.webcobranzas.iface.IMaeEstadoCuentaDao;
import pop.webcobranzas.iface.IMaeFondoDao;
import pop.webcobranzas.iface.IMaeHipotecaDao;
import pop.webcobranzas.iface.IMaeInmuebleDao;
import pop.webcobranzas.iface.IMaeInversionDoa;
import pop.webcobranzas.iface.IMaeNotificacionDao;
import pop.webcobranzas.iface.IMaePersonaDao;
import pop.webcobranzas.iface.IMaeSesionDao;
import pop.webcobranzas.iface.IMaeTelefonoDao;
import pop.webcobranzas.iface.IMaeTipoCambioDao;
import pop.webcobranzas.iface.IMaeUbigeoDao;
import pop.webcobranzas.iface.ITabArchivoDao;
import pop.webcobranzas.iface.ITabDocumentosDao;
import pop.webcobranzas.iface.ITabDocumentosDetDao;
import pop.webcobranzas.iface.ITabTipoDocumentoDao;
import pop.webcobranzas.iface.ITabTipoDocumentoEstadoDao;
import pop.webcobranzas.iface.IMaeAsesorDao;
import pop.webcobranzas.iface.ICobCronogramaMetaAvanceDao;
import pop.webcobranzas.iface.ICobRequerimientoCartasDao;
import pop.webcobranzas.iface.IGenerarDemandaDao;
import pop.webcobranzas.iface.ILegalAsesorDao;
import pop.webcobranzas.iface.ILegalGastosJudicialesDao;
import pop.webcobranzas.iface.ILegEtapasDao;
import pop.webcobranzas.iface.ILegTablasDao;
import pop.webcobranzas.iface.ILegTiposGastosJudiDao;

import pop.webcobranzas.impl.AlertaPrejudicialDao;
import pop.webcobranzas.impl.CobCdrDao;
import pop.webcobranzas.impl.CobCompromisoDao;
import pop.webcobranzas.impl.CobCronogramaMetaAsesorDao;
import pop.webcobranzas.impl.CobCronogramaMetaDetalleDao;
import pop.webcobranzas.impl.CobCronogramaMetaResumenDao;
import pop.webcobranzas.impl.CobCronogramaMetaCabeceraDao;
import pop.webcobranzas.impl.CobLlamadasDao;
import pop.webcobranzas.impl.CobMaeSeguimientoDao;
import pop.webcobranzas.impl.CobSeguimientoDao;
import pop.webcobranzas.impl.CobTablasDao;
import pop.webcobranzas.impl.ConexionDao;
import pop.webcobranzas.impl.ConexionDaoMySql;
import pop.webcobranzas.impl.LaftPersonaDao;
import pop.webcobranzas.impl.LegalDao;
import pop.webcobranzas.impl.MaeBancoDao;
import pop.webcobranzas.impl.MaeCargoDao;
import pop.webcobranzas.impl.MaeCronogramaDao;
import pop.webcobranzas.impl.MaeCuotaPagoDao;
import pop.webcobranzas.impl.MaeDashboardDao;
import pop.webcobranzas.impl.MaeDepositoDao;
import pop.webcobranzas.impl.MaeDireccionDao;
import pop.webcobranzas.impl.MaeEmailDao;
import pop.webcobranzas.impl.MaeEstadoCuentaDao;
import pop.webcobranzas.impl.MaeFondoDao;
import pop.webcobranzas.impl.MaeHipotecaDao;
import pop.webcobranzas.impl.MaeInmuebleDao;
import pop.webcobranzas.impl.MaeInversionDao;
import pop.webcobranzas.impl.MaeNotificacionDao;
import pop.webcobranzas.impl.MaePersonaDao;
import pop.webcobranzas.impl.MaeSesionDao;
import pop.webcobranzas.impl.MaeTelefonoDao;
import pop.webcobranzas.impl.MaeTipoCambioDao;
import pop.webcobranzas.impl.MaeUbigeoDao;
import pop.webcobranzas.impl.TabArchivoDao;
import pop.webcobranzas.impl.TabDocumentosDao;
import pop.webcobranzas.impl.TabDocumentosDetDao;
import pop.webcobranzas.impl.TabTipoDocumentoDao;
import pop.webcobranzas.impl.TabTipoDocumentoEstadoDao;
import pop.webcobranzas.impl.MaeAsesorDao;
import pop.webcobranzas.impl.CobCronogramaMetaAvanceDao;
import pop.webcobranzas.impl.CobRequerimientoCartasDao;
import pop.webcobranzas.impl.GenerarDemandaDao;
import pop.webcobranzas.impl.LegEtapasDao;
import pop.webcobranzas.impl.LegalAsesorDao;
import pop.webcobranzas.impl.LegalGastosJudicialesDao;
import pop.webcobranzas.impl.LegTablasDao;
import pop.webcobranzas.impl.LegTiposGastosJudiDao;

/**
 *
 * @author Jyoverar
 */
public class FactoryDao {
    
    public IConexionDao getConexionDao() {
        return ConexionDao.Instance();
    }    

     public IConexionDao getConexionDaoMySql() {
        return ConexionDaoMySql.Instance();
    }

    public IMaeDepositoDao getMaeDepositoDao() {
        return new MaeDepositoDao();
    }

    public IMaePersonaDao getMaePersonaDao() {
        return new MaePersonaDao();
    }

    public ILaftPersonaDao getLaftPersonaDao() {
        return new LaftPersonaDao();
    }

    public IMaeInversionDoa getMaeInversionDao() {
        return new MaeInversionDao();
    }

    public IMaeEstadoCuentaDao getMaeEstadoCuenta() {
        return new MaeEstadoCuentaDao();
    }

    public IMaeDashboardDao getMaeDashboard() {
        return new MaeDashboardDao();
    }

    public IMaeUbigeoDao getMaeUbigeo() {
        return new MaeUbigeoDao();
    }

    public IMaeCuotaPagoDao getMaeCuotaPago() {
        return new MaeCuotaPagoDao();
    }

    public IMaeCargoDao getMaeCargo() {
        return new MaeCargoDao();
    }

    public ICobTablasDao getCobTablas() {
        return new CobTablasDao();
    }

    public ICobMaeSeguimientoDao getCobMaeSeguimiento() {
        return new CobMaeSeguimientoDao();
    }

    public IMaeSesionDao getMaeSesion() {
        return new MaeSesionDao();
    }

    public IMaeNotificacionDao getMaeNotificacion() {
        return new MaeNotificacionDao();
    }

    public ICobLlamadasDao getCobLlamada() {
        return new CobLlamadasDao();
    }

    public ICobCompromisoDao getCobCompromiso() {
        return new CobCompromisoDao();
    }

    public IMaeBancoDao getBanco() {
        return new MaeBancoDao();
    }

    public IMaeFondoDao getFondo() {
        return new MaeFondoDao();
    }

    public IMaeTipoCambioDao getTipoCambio() {
        return new MaeTipoCambioDao();
    }

    public IMaeTelefonoDao getTelefono() {
        return new MaeTelefonoDao();
    }

    public IMaeEmailDao getEmail() {
        return new MaeEmailDao();
    }

    public IMaeDireccionDao getDireccion() {
        return new MaeDireccionDao();
    }

    public ICobSeguimientoDao getCobSeguimiento() {
        return new CobSeguimientoDao();
    }

    public IMaeCronogramaDao getCronograma() {
        return new MaeCronogramaDao();
    }

    public ICobCdrDao getCdr() {
        return new CobCdrDao();
    }
    
    public ICobCdrDao getCdr(OracleConnection cnx) {
        return new CobCdrDao(cnx);
    }

    public ITabTipoDocumentoDao getTipoDocumento() {
        return new TabTipoDocumentoDao();
    }

    public ITabTipoDocumentoEstadoDao getTipoDocumentoEstado() {
        return new TabTipoDocumentoEstadoDao();
    }

    public ITabDocumentosDao getDocumentos() {
        return new TabDocumentosDao();
    }

    public ITabDocumentosDetDao getDocumentosDet() {
        return new TabDocumentosDetDao();
    }

    public ITabArchivoDao getArchivo() {
        return new TabArchivoDao();
    }

    public IMaeInmuebleDao getInmueble() {
        return new MaeInmuebleDao();
    }

    public IMaeHipotecaDao getHipoteca() {
        return new MaeHipotecaDao();
    }

    public ICobCronogramaMetaResumenDao getCronogramaMetaResumen() {
        return new CobCronogramaMetaResumenDao();
    }
    
   public ICobCronogramaMetaDetalleDao getCronogramaMetaDetalle() {
        return new CobCronogramaMetaDetalleDao();
    }    
    
    public ICobCronogramaMetaAsesorDao getCronogramaMetaAsesor() {
        return new CobCronogramaMetaAsesorDao();
    }   
    public ICobCronogramaMetaCabeceraDao getCronogramaMetaCabecera() {
        return new CobCronogramaMetaCabeceraDao();
    }  
    
    public ILegalDao getLegal() {
        return new LegalDao();
    }
    
    public ILegalAsesorDao getLegalAsesor() {
        return new LegalAsesorDao();
    }
    
    public IMaeAsesorDao getAsesor() {
        return new MaeAsesorDao();
    }
    
    public ICobCronogramaMetaAvanceDao getCronogramaMetaAvance() {
        return new CobCronogramaMetaAvanceDao();
    }  
    
    public ILegalGastosJudicialesDao getLegalGastosJudiciales() {
        return new LegalGastosJudicialesDao();
    }
    
    public ILegEtapasDao getEtapas() {
        return new LegEtapasDao();
    }    
    
    public ILegTiposGastosJudiDao getTiposGastosJudiciales() {
        return new LegTiposGastosJudiDao();
    }
    
    public ILegTablasDao getLegTabla() {
        return new LegTablasDao();
    }  

    public ICobRequerimientoCartasDao getRequerimiento(){
        return new CobRequerimientoCartasDao();
    }
    
    public IAlertaPrejudicialDao getAlertaPrejudicial(){
        return new AlertaPrejudicialDao();
    }
    
    public IGenerarDemandaDao getGenerarDemanda(){
        return new GenerarDemandaDao();
    }
}
