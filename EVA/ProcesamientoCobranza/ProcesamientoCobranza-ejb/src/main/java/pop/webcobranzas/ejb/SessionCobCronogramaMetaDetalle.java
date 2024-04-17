/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCronogramaMetaDetalle;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.reporte.RepMetaRecaudo;
import pop.comun.dominio.CobCronogramaRecaudoResumen;
/**
 *
 * @author HH38092
 */
@Stateless(mappedName = "CobCronogramaMetaDetalle")
public class SessionCobCronogramaMetaDetalle  implements INegCobCronogramaMetaDetalle {
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actualizarCambiarAsesor(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList) throws Exception {
     //   logger.info("actualizarCambiarAsesor:inicio()");
        
        if(oCobCronogramaMetaDetalleList.size()>0)
        {
            //primero actualiza el detalle con la lista de asesores nuevos
            ofDao.getCronogramaMetaDetalle().updateList(oCobCronogramaMetaDetalleList);
            //HHA: se actualiza el resumen a partir del nuevo detalle de asignaciones de codigos a los asesores
            //tomare unos de los registros del detalle para tomar la cabecera y recalcular y reprocesar
            CobCronogramaMetaResumen oCobCronogramaMetaResumen= new CobCronogramaMetaResumen();
            oCobCronogramaMetaResumen.setN_anio(oCobCronogramaMetaDetalleList.get(0).getN_anio());
            oCobCronogramaMetaResumen.setN_mes(oCobCronogramaMetaDetalleList.get(0).getN_mes());
            oCobCronogramaMetaResumen.setC_fondo_id(oCobCronogramaMetaDetalleList.get(0).getC_fondo_id());
            oCobCronogramaMetaResumen.setC_usuario_mod(oCobCronogramaMetaDetalleList.get(0).getC_usuario_mod());
            oCobCronogramaMetaResumen.setJudicial(oCobCronogramaMetaDetalleList.get(0).getJudicial());
   //         logger.info("actualizarCambiarAsesor:"+oCobCronogramaMetaResumen.toString());
            ofDao.getCronogramaMetaResumen().updatexCambioAsesor(oCobCronogramaMetaResumen);
        }
        
        return;
    }

    @Override
    public void borrar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCronogramaMetaDetalle> buscarCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
       // logger.info("inicio()");
        List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList = null;

        oCobCronogramaMetaDetalleList = ofDao.getCronogramaMetaDetalle().fetchAll(oCobCronogramaMetaDetalle);
		
		return oCobCronogramaMetaDetalleList;
    }
    
    @Override
    public List<CobCronogramaMetaAgrupxFecha> buscarCronogramaMetaAgrupxFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        //logger.info("buscarCronogramaMetaAgrupxFecha()");
        List<CobCronogramaMetaAgrupxFecha> oCobCronogramaMetaAgrupFechaList = null;
        
        oCobCronogramaMetaAgrupFechaList = ofDao.getCronogramaMetaDetalle().fetchAgrupFecha(oCobCronogramaMetaResumen);
        //logger.info("termino fetchAgrupFecha");
        
        return oCobCronogramaMetaAgrupFechaList;
    }    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<CobCronogramaMetaDetalle> buscarRepCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        //logger.info("inicio()");
        List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList = null;

        oCobCronogramaMetaDetalleList = ofDao.getCronogramaMetaDetalle().fetchRepAll(oCobCronogramaMetaDetalle);
		
	return oCobCronogramaMetaDetalleList;
    }
    
    @Override
    public RepMetaCobranza buscarReporteMetaCobranza(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception
    {
        RepMetaCobranza oMetaCobranza  = null;
        
        oMetaCobranza = ofDao.getCronogramaMetaDetalle().buscarReporteMetaCobranza(oCobCronogramaMetaDetalle);
        return oMetaCobranza;
    }
    
    @Override
    public RepMetaRecaudo  buscarReporteMetaRecaudo(CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle) throws Exception
    {
        RepMetaRecaudo oMetaRecaudo = null;
        
        oMetaRecaudo = ofDao.getCronogramaMetaDetalle().buscarReporteMetaRecaudo(oCobCronogramaRecaudoDetalle);
       System.out.println("aca hay 1");
        return oMetaRecaudo;
    }
    
    @Override
    public List<CobCronogramaRecaudoResumen> buscarRepCronogramaRecaudoResumen(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) throws Exception {
        //logger.info("inicio()");
        List<CobCronogramaRecaudoResumen> oCobCronogramaRecaudoResumenList = null;

        oCobCronogramaRecaudoResumenList = ofDao.getCronogramaMetaDetalle().fetchRecAll(oCobCronogramaRecaudoResumen);
		
	return oCobCronogramaRecaudoResumenList;
    }
}