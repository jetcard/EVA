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
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.iface.IEmailServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.ifacemsj.IMensajeTextoServ;
import pop.webcobranzas.servicio.EmailServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.serviciomsj.MensajeTextoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class EmailBean implements Serializable {

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
    // lista de email 
    private List<MaeEmail> maeEmailList;
    // lista de fondos
    private List<MaeFondo> maeFondoList;

    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de correos
    private IEmailServ emailServ = new EmailServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de msj
    private IMensajeTextoServ mensajeTextoServ = new MensajeTextoServ();

    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    public EmailBean() {
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

    public void buscarCorreos() {
        try {

            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeEmailList = getEmailServ().listarEmailInver(maeInversion);
            System.out.println("cantidad de Email " + maeEmailList.size());
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.InversionBean.buscarDeudores()");
        }
    }

    public void enviarCorreos() {
        showMsg = true;
        List<MaeEmail> maeEmailSendList = new ArrayList<>();
        try {
            for (MaeEmail maeEmail : maeEmailList) {
                System.out.println(" demo --- " + maeEmail.isBenvio() + " - " + maeEmail.getDemail());
                if (maeEmail.isBenvio()) {
                    maeEmailSendList.add(maeEmail);
                }
            }
            maeEmailSendList = emailServ.listarEmailInver(maeInversion);

            tipoMsj = "success";
            mensaje = "Mensajes de Texto enviados";
        } catch (Exception ex) {
            tipoMsj = "error";
            mensaje = "Error de Mensajes de Texto";
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        maeEmailList.clear();
        maeEmailList = maeEmailSendList;

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

    public IEmailServ getEmailServ() {
        return emailServ;
    }

    public void setEmailServ(IEmailServ emailServ) {
        this.emailServ = emailServ;
    }

    public List<MaeEmail> getMaeEmailList() {
        return maeEmailList;
    }

    public void setMaeEmailList(List<MaeEmail> maeEmailList) {
        this.maeEmailList = maeEmailList;
    }

}
