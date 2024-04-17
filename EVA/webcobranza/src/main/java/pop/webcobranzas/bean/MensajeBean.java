/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.ITelefonoServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.ifacemsj.IMensajeTextoServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.TelefonoServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.serviciomsj.MensajeTextoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class MensajeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // imnueble (busq por provincia/distrito)
    private MaeInmueble maeInmueble = new MaeInmueble();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    // inversion busqueda
    private MaeInversion maeInversion = new MaeInversion();

    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de inversiones 
    private List<MaeTelefono> maeTelefonoList;
    // lista de fondos
    private List<MaeFondo> maeFondoList;

    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de telefono
    private ITelefonoServ telefonoServ = new TelefonoServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de msj
    private IMensajeTextoServ mensajeTextoServ = new MensajeTextoServ();

    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    private boolean sendAll = false;

    public MensajeBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);
    }

    public void listarProvincia() {
        try {
            ubigeoProv = getUbigeoServ().listarProvincia(maeUbigeoP);
        } catch (Exception e) {

        }
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
        } catch (Exception e) {

        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
        } catch (Exception e) {
        }
    }

    public void buscarTelefonos() {
        try {

            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);
//            List<MaeTelefono> maeTelefonoListTemp = getTelefonoServ().listarTelfonoInver(maeInversion);
            maeTelefonoList = getTelefonoServ().listarTelefonoEnvioMsj(maeInversion);//new ArrayList<>();

//            for (MaeTelefono maeTelefono : maeTelefonoListTemp) {
//                if (maeTelefono.getANumero() != null) {     
//                    if (maeTelefono.getANumero().trim().length() == 9) {
//                        maeTelefonoList.add(maeTelefono);
//                    }
//                }
//            }
            System.out.println("cantidad de telefonos " + maeTelefonoList.size());
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.InversionBean.buscarDeudores()");
        }
    }

    public void enviarMensajes() {
        showMsg = true;
        List<MaeTelefono> maeTelefonoSendList = new ArrayList<>();
        try {
            for (MaeTelefono maeTelefono : maeTelefonoList) {
                System.out.println(" demo --- " + maeTelefono.isBenvio() + " - " + maeTelefono.getANumero());
                if (maeTelefono.isBenvio()) {
                    maeTelefonoSendList.add(maeTelefono);
                }
            }
            maeTelefonoSendList = mensajeTextoServ.insertar(maeTelefonoSendList);

            tipoMsj = "success";
            mensaje = "Mensajes de Texto enviados";
        } catch (Exception ex) {
            tipoMsj = "error";
            mensaje = "Error de Mensajes de Texto";
            Logger.getLogger(MensajeBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        maeTelefonoList.clear();
        maeTelefonoList = maeTelefonoSendList;

    }

    public void checkAll(AjaxBehaviorEvent event) {
        try {
            System.out.println("pop.webcobranzas.bean.MensajeBean.checkAll()---------------");
            if (sendAll) {
                if (!maeTelefonoList.isEmpty()) {
                    for (MaeTelefono maeTelefono : maeTelefonoList) {
                        maeTelefono.setBenvio(true);
                    }
                }
            } else {
                if (!maeTelefonoList.isEmpty()) {
                    for (MaeTelefono maeTelefono : maeTelefonoList) {
                        maeTelefono.setBenvio(false);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
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

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
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

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
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

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
    }

    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    public ITelefonoServ getTelefonoServ() {
        return telefonoServ;
    }

    public void setTelefonoServ(ITelefonoServ telefonoServ) {
        this.telefonoServ = telefonoServ;
    }

    public List<MaeTelefono> getMaeTelefonoList() {
        return maeTelefonoList;
    }

    public void setMaeTelefonoList(List<MaeTelefono> maeTelefonoList) {
        this.maeTelefonoList = maeTelefonoList;
    }

    public IMensajeTextoServ getMensajeTextoServ() {
        return mensajeTextoServ;
    }

    public void setMensajeTextoServ(IMensajeTextoServ mensajeTextoServ) {
        this.mensajeTextoServ = mensajeTextoServ;
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }

    public boolean isSendAll() {
        return sendAll;
    }

    public void setSendAll(boolean sendAll) {
        this.sendAll = sendAll;
    }

}
