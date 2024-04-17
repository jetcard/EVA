/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabUsuario;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegPersona;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbPersona")
public class SessionPersona implements INegPersona {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaePersona> listarPersonaUlt() throws Exception {
        List<MaePersona> oPerList = null;
        MaePersona oMaePersona = new MaePersona();
        
        oPerList = ofDao.getMaePersonaDao().fetchDashboard(oMaePersona);
        return oPerList;
    }

    @Override
    public Integer insertar(MaePersona oMaePersona) throws Exception {
        Integer newID = 0;
       
        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getMaePersonaDao().insert(oMaePersona);
            if (newID > 0) {
                oMaePersona.setCPersonaId(newID);
                //creamos el telefono
                if (!oMaePersona.getMaeTelefonoList().isEmpty()) {
                    for (MaeTelefono maeTelefono : oMaePersona.getMaeTelefonoList()) {
                        maeTelefono.setMaePersona(oMaePersona);
                        int newIdTel = ofDao.getTelefono().insert(maeTelefono);
                        if (newIdTel == 0) {                            
                            throw new Exception("Exception save Telefono");
                        }
                    }
                }
                //creamos los email
                if (!oMaePersona.getMaeEmailList().isEmpty()) {
                    for (MaeEmail maeEmail : oMaePersona.getMaeEmailList()) {
                        maeEmail.setMaePersona(oMaePersona);
                        int newIdEmail = ofDao.getEmail().insert(maeEmail);
                        if (newIdEmail == 0) {                            
                            throw new Exception("Exception save Email");
                        }
                    }
                }
                //creamos la direccion 
                if (!oMaePersona.getMaeDireccionList().isEmpty()) {
                    for (MaeDireccion maeDireccion : oMaePersona.getMaeDireccionList()) {
                        maeDireccion.setMaePersona(oMaePersona);
                        int newIdDireccion = ofDao.getDireccion().insert(maeDireccion);
                        if (newIdDireccion == 0) {
                            throw new Exception("Exception save Direccion");
                        }
                    }
                }
            } else {
                throw new Exception("Exception save Persona");
            }
        } catch (Exception e) {            
            throw e;
        } 
        return newID;
    }

    @Override
    public boolean actualizar(MaePersona oMaePersona) throws Exception {
        //Integer newID = 0;
        boolean rpta = false;
        
        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            rpta = ofDao.getMaePersonaDao().update(oMaePersona);
            if (rpta) {
                //oMaePersona.setCPersonaId(newID);
                // actualizamos los telefono
                if (!oMaePersona.getMaeTelefonoList().isEmpty()) {
                    for (MaeTelefono maeTelefono : oMaePersona.getMaeTelefonoList()) {
                        maeTelefono.setMaePersona(oMaePersona);
                        if (maeTelefono.getCTelefonoId() > 0) {
                            // actualizar
                            boolean rptaTel = ofDao.getTelefono().update(maeTelefono);
                            if (!rptaTel) {
                                rpta = false;
                                throw new Exception("Exception update cell");
                            }
                        } else {
                            // grabar nuevo
                            int newIdTel = ofDao.getTelefono().insert(maeTelefono);
                            if (newIdTel == 0) {
                                rpta = false;
                                throw new Exception("Exception save cell");
                            }
                        }
                    }
                }
                //creamos los email
                if (!oMaePersona.getMaeEmailList().isEmpty()) {
                    for (MaeEmail maeEmail : oMaePersona.getMaeEmailList()) {
                        maeEmail.setMaePersona(oMaePersona);
                        if (maeEmail.getCemailId() > 0) {
                            // actualizar
                            boolean rptaEma = ofDao.getEmail().update(maeEmail);
                            if (!rptaEma) {
                                rpta = false;
                                throw new Exception("Exception update Email");
                            }
                        } else {
                            // grabar nuevo
                            int newIdEmail = ofDao.getEmail().insert(maeEmail);
                            if (newIdEmail == 0) {
                                rpta = false;
                                throw new Exception("Exception save Email");
                            }
                        }
                    }
                }
                //creamos la direccion 
                if (!oMaePersona.getMaeDireccionList().isEmpty()) {
                    for (MaeDireccion maeDireccion : oMaePersona.getMaeDireccionList()) {
                        maeDireccion.setMaePersona(oMaePersona);
                        if (maeDireccion.getCDireccionId() > 0) {
                            // actualizar
                            boolean rptaDir = ofDao.getDireccion().update(maeDireccion);
                            if (!rptaDir) {
                                rpta = false;
                                throw new Exception("Exception update Direction");
                            }
                        } else {
                            int newIdDireccion = ofDao.getDireccion().insert(maeDireccion);
                            if (newIdDireccion == 0) {
                                rpta = false;
                                throw new Exception("Exception save Direction");
                            }
                        }
                    }
                }
            } else {
                rpta = false;
                throw new Exception("Exception update Person");
            }
        } catch (Exception e) {
            rpta = false;
            throw e;        
        }
        return rpta;
    }

    @Override
    public void borrar(MaePersona oMaePersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaePersona> listarPersona(MaePersona oMaePersona) throws Exception {
        List<MaePersona> oPerList = null;
        List<MaePersona> oPerListReturn = new ArrayList<>();
       
        oPerList = ofDao.getMaePersonaDao().fetchAll(oMaePersona);
        for (MaePersona maePersona : oPerList) {
            // obteniendo telefono
            List<MaeTelefono> oTelList = null;
            MaeTelefono maeTel = new MaeTelefono();
            maeTel.setMaePersona(maePersona);
            // buscar telefonos
            oTelList = ofDao.getTelefono().fetchAll(maeTel);
            // agregar telefonos
            maePersona.setMaeTelefonoList(oTelList);

            // obteniendo direccion
            List<MaeDireccion> oDirList = null;
            MaeDireccion maeDir = new MaeDireccion();
            MaeUbigeo maeUbi = new MaeUbigeo();
            maeDir.setMaeUbigeo(maeUbi);
            maeDir.setMaePersona(maePersona);
            // buscar direccion
            oDirList = ofDao.getDireccion().fetchAll(maeDir);
            if (oDirList.size() > 0) {
                int contador = 0;
                for (MaeDireccion maeDireccion : oDirList) {
                    maeUbi.setCUbigeoId(maeDireccion.getMaeUbigeo().getCUbigeoId());
                    // distrito
                    List<MaeUbigeo> maeUbiDistList = new ArrayList<>();
                    maeUbiDistList = ofDao.getMaeUbigeo().fetchAll(maeUbi);
                    if (maeUbiDistList.size() > 0) {
                        MaeUbigeo maeUbiDist = maeUbiDistList.get(0);
                        System.out.println(" ----- aaaa" + maeUbiDist.getDUbigeo());
                        System.out.println(" ----- bbbb" + maeUbiDistList.get(0).getDUbigeo());
                        // provincia
                        maeUbi.setCUbigeoId(maeUbiDist.getCUbigeoPad());
                        List<MaeUbigeo> maeUbiProtList = new ArrayList<>();
                        maeUbiProtList = ofDao.getMaeUbigeo().fetchAll(maeUbi);
                        if (maeUbiProtList.size() > 0) {
                            MaeUbigeo maeUbiProv = maeUbiProtList.get(0);
                            maeUbiDist.setMaeUbigeo(maeUbiProv);
                        }
                        System.out.println(" ----- " + maeUbiDist.getDUbigeo());
                        oDirList.get(contador).setMaeUbigeo(maeUbiDist);
                    }
                    contador++;
                }

            }

            System.out.println("pop.webcobranzas.ejb.SessionPersona.listarPersona()");
            System.out.println(" -------- " + oDirList.get(0).getMaeUbigeo().getDUbigeo());
            System.out.println("pop.webcobranzas.ejb.SessionPersona.listarPersona()");

            // agregar direccion
            maePersona.setMaeDireccionList(oDirList);

            // obteniendo email
            List<MaeEmail> oEmailList = null;
            MaeEmail maeEma = new MaeEmail();
            maeEma.setMaePersona(maePersona);
            // buscar direccion
            oEmailList = ofDao.getEmail().fetchAll(maeEma);
            // agregar direccion
            maePersona.setMaeEmailList(oEmailList);
            oPerListReturn.add(maePersona);
        }
        return oPerListReturn;
    }

    @Override
    public MaePersona buscarPerAsignada(int dia, String fondo, Number InversionID) throws Exception {
        MaePersona oPer = new MaePersona();
        MaePersona oPerReturn = new MaePersona();
        
        oPer = ofDao.getMaePersonaDao().findAssignedPerson(dia, fondo, InversionID);
        
        oPerReturn.setCPersonaId(oPer.getCPersonaId());
        oPerReturn.setDNombres(oPer.getDNombres());
        oPerReturn.setDApePat(oPer.getDApePat());
        oPerReturn.setDApeMat(oPer.getDApeMat());
        
        TabUsuario tabUsuario = new TabUsuario();
        tabUsuario.setCusuarioId(oPer.getTabUsuario().getCusuarioId());
        oPerReturn.setTabUsuario(tabUsuario);
        
        // obteniendo telefono
        List<MaeTelefono> oTelList = null;
        MaeTelefono maeTel = new MaeTelefono();
        maeTel.setMaePersona(oPer);
        // buscar telefonos
        oTelList = ofDao.getTelefono().fetchAll(maeTel);
        // agregar telefonos
        oPerReturn.setMaeTelefonoList(oTelList);

        // obteniendo direccion
        List<MaeDireccion> oDirList = null;
        MaeDireccion maeDir = new MaeDireccion();
        MaeUbigeo maeUbi = new MaeUbigeo();
        maeDir.setMaeUbigeo(maeUbi);
        maeDir.setMaePersona(oPer);
        // buscar direccion
        oDirList = ofDao.getDireccion().fetchAll(maeDir);
        
        // agregar direccion
        oPerReturn.setMaeDireccionList(oDirList);

        // obteniendo email
        List<MaeEmail> oEmailList = null;
        MaeEmail maeEma = new MaeEmail();
        maeEma.setMaePersona(oPer);
        // buscar email
        oEmailList = ofDao.getEmail().fetchAll(maeEma);
        // agregar email
        oPerReturn.setMaeEmailList(oEmailList);
       
        return oPerReturn;
    }

}
