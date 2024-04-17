/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobRequerimiento;


/**
 *
 * @author RC433838
 */
@Stateless(mappedName = "ejbRequerimiento")
public class SessionRequerimiento implements INegCobRequerimiento {
    
    FactoryDao ofDao = new FactoryDao();    

    @Override
    public String removerRequerimiento(CobRequerimientoCartas oRquerimiento) throws Exception {
        String Rspta;
        Rspta = ofDao.getRequerimiento().removerRequerimiento(oRquerimiento);
        return Rspta;
    }
    
    @Override
    public String cambiaEstadoReq(CobRequerimientoCartas oRquerimiento) throws Exception {
        String Rspta;
        Rspta = ofDao.getRequerimiento().cambiaEstadoReq(oRquerimiento);
        return Rspta;
    }    

    @Override
    public String addRequerimiento(CobRequerimientoCriterios oCriterio) throws Exception {
        return ofDao.getRequerimiento().addRequerimiento(oCriterio);
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRquerimiento) throws Exception {
        
        return ofDao.getRequerimiento().allRequerimientos(oRquerimiento);
    }

    @Override
    public List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterios) throws Exception {
        return ofDao.getRequerimiento().findCriterios(oCriterios);
    }
    
    @Override
    public List<CobRequerimientoCriterios> findReqSugerido() throws Exception {
        return ofDao.getRequerimiento().findReqSugerido();
    }    

    @Override
    public List<MovimientoCartas> findCartas(MaeInversion oInversion) throws Exception {
        return ofDao.getRequerimiento().findCartas(oInversion);
    }


    
    
}
