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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IPersonaServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.servicio.PersonaServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.UbigeoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class ClienteManBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // parametro externo
    private MaePersona maePersona;
    //
    private MaeTelefono maeTelefono;
    //
    private MaeEmail maeEmail;
    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    //
    private CobTablas cobTablas = new CobTablas();
    //
    private CobTablas cobTablasVia;
    private CobTablas cobTablasZona;

    // lista de personas
    private List<MaePersona> maePersonaList;
    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de telefonos grabar
    private List<MaeTelefono> maeTelefonos;
    // lista de mEmail grabar
    private List<MaeEmail> maeEmails;
    // lista de mEmail grabar
    private List<MaeDireccion> maeDireccions;
    // lista de Tipo
    private List<CobTablas> cobTablasTipoDocList;
    private List<CobTablas> cobTablasSexoList;
    private List<CobTablas> cobTablasEstadoCivilList;
    private List<CobTablas> cobTablasGradoList;
    private List<CobTablas> cobTablasTeleList;
    private List<CobTablas> cobTablasEmailList;
    private List<CobTablas> cobTablasZonaList;
    private List<CobTablas> cobTablasViaList;

    // servicio de persona
    private IPersonaServ personaServ = new PersonaServ();
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();

    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    public ClienteManBean() {
        maePersona = new MaePersona();
        maePersona.setMaeFondo(new MaeFondo());
        maePersona.setCTipoPersona("0001");
        maePersona.setCClasePersona("0001");
        maeTelefono = new MaeTelefono();
        maeEmail = new MaeEmail();
        System.out.println("pop.webcobranzas.bean.ClienteManBean.<init>()");
        try {
            cobTablas.setCtablaId("0003");
            cobTablasTipoDocList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0600");
            cobTablasSexoList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0605");
            cobTablasEstadoCivilList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0500");
            cobTablasGradoList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0501");
            cobTablasTeleList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0512");
            cobTablasEmailList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0606");
            cobTablasZonaList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0607");
            cobTablasViaList = getTablasServ().listarTablas(cobTablas);
            maeTelefonos = new ArrayList<>();
            maeEmails = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ClienteManBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarDatos() {
        try {
            if (maePersona.getCPersonaId() != 0) {
                maePersonaList = getPersonaServ().listarPersona(maePersona);
                System.out.println("Persona " + maePersonaList.size());
                if (!maePersonaList.isEmpty()) {
                    maePersona = maePersonaList.get(0);
                    if (!maePersona.getMaeTelefonoList().isEmpty()) {
                        maeTelefonos = maePersona.getMaeTelefonoList();
                        for (int i = 0; i < maeTelefonos.size(); i++) {
                            if (maeTelefonos.get(i).getBDefault().equals("1")) {
                                maeTelefonos.get(i).setBDefault("true");
                            } else {
                                maeTelefonos.get(i).setBDefault("false");
                            }
                        }
                    }
                    if (!maePersona.getMaeEmailList().isEmpty()) {
                        maeEmails = maePersona.getMaeEmailList();
                        for (int i = 0; i < maeEmails.size(); i++) {
                            if (maeEmails.get(i).getbDefault().equals("1")) {
                                maeEmails.get(i).setbDefault("true");
                            } else {
                                maeEmails.get(i).setbDefault("false");
                            }
                        }
                    }
                    if (!maePersona.getMaeDireccionList().isEmpty()) {
                        maeDireccions = maePersona.getMaeDireccionList();
                        for (int i = 0; i < maeDireccions.size(); i++) {
                            if (maeDireccions.get(i).getBDefault().equals("1")) {
                                maeDireccions.get(i).setBDefault("true");
                            } else {
                                maeDireccions.get(i).setBDefault("false");
                            }
                        }

                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteManBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("pop.webcobranzas.bean.ClienteManBean.cargarDatos()" + maePersona.getCPersonaId());
    }

    public void listarProvincia() {
        try {
            ubigeoProv = getUbigeoServ().listarProvincia(maeUbigeoP);
            System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
            System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void defaultEmail(MaeEmail oMaeEmail) {
        showMsg = false;
        for (int i = 0; i < maeEmails.size(); i++) {
            if (!maeEmails.get(i).equals(oMaeEmail)) {
                maeEmails.get(i).setbDefault("0");
            }
        }
        System.out.println("pop.webcobranzas.bean.ClienteManBean.defaultEmail() -- " + oMaeEmail.getbDefault());
    }

    public void defaultTelef(MaeTelefono oMaeTelefono) {
        showMsg = false;
        for (int i = 0; i < maeTelefonos.size(); i++) {
            if (!maeTelefonos.get(i).equals(oMaeTelefono)) {
                maeTelefonos.get(i).setBDefault("0");
            }
        }
        System.out.println("pop.webcobranzas.bean.ClienteManBean.defaultTelef() -- " + oMaeTelefono.getBDefault());
    }

    public void agregarTel() {
        showMsg = false;
        if (maeTelefono.getCTipoTel().equals("0001")) { // fijo
            if (maeTelefono.getANumero().length() != 7) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de número de teléfono";
            } else {
                MaeTelefono mt = new MaeTelefono();
                mt.setCTipoTel(maeTelefono.getCTipoTel());
                mt.setANumero(maeTelefono.getANumero());
                mt.setNAnexo(maeTelefono.getNAnexo());
                mt.setBDefault("0");
                maeTelefonos.add(mt);
                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);
                showMsg = false;
            }
        } else if (maeTelefono.getCTipoTel().equals("0002")) { // Celular
            if (maeTelefono.getANumero().length() != 9) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de número de celular";
            } else {
                MaeTelefono mt = new MaeTelefono();
                mt.setCTipoTel(maeTelefono.getCTipoTel());
                mt.setANumero(maeTelefono.getANumero());
                mt.setNAnexo(maeTelefono.getNAnexo());
                mt.setBDefault("0");
                maeTelefonos.add(mt);
                showMsg = false;
            }
        } else {
            MaeTelefono mt = new MaeTelefono();
            mt.setCTipoTel(maeTelefono.getCTipoTel());
            mt.setANumero(maeTelefono.getANumero());
            mt.setNAnexo(maeTelefono.getNAnexo());
            mt.setBDefault("0");
            maeTelefonos.add(mt);
            showMsg = false;
        }
    }

    public void borrarTel(MaeTelefono oMaeTelefono) {
        maeTelefonos.remove(oMaeTelefono);
    }

    public void editarTel(MaeTelefono oMaeTelefono) {
        maeTelefono = oMaeTelefono;
        maeTelefonos.remove(oMaeTelefono);
    }

    public void agregarEmail() {
        showMsg = false;
        if (maeEmail.getDemail().length() > 0) {
            Pattern p = Pattern.compile("^(.+)@(.+)$");
            Matcher m = p.matcher(maeEmail.getDemail());
            boolean matchFound = m.matches();
            if (matchFound) {
                MaeEmail me = new MaeEmail();
                me.setCtipoMailId(maeEmail.getCtipoMailId());
                me.setDemail(maeEmail.getDemail());
                me.setbDefault("0");
                maeEmails.add(me);
                maeEmail.setDemail("");
                showMsg = false;
            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de Email";
            }
        } else {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "error de Email";
        }

    }

    public void borrarEmail(MaeEmail oMaeEmail) {
        maeEmails.remove(oMaeEmail);
    }

    public void editarEmail(MaeEmail oMaeEmail) {
        maeEmail = oMaeEmail;
        maeEmails.remove(oMaeEmail);
    }

    public void grabarPersona() {

        int conta = 0;
        int i = 0;
        // validaciones
        if (maeTelefonos.isEmpty()) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se ha agregado Telefonos";
            return;
        }
        /*if (maeEmails.isEmpty()) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se ha agregado Email";
            return;
        }*/
        for (MaeTelefono mt : maeTelefonos) {
            if (mt.getBDefault().equals("true") || mt.getBDefault().equals("1")) {
                conta = 1;
                break;
            }
        }
        if (conta == 0) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se ha agregado un Teléfono por defecto.";
            return;
        }
        conta = 0;
        /*for (MaeEmail mi : maeEmails) {
            if (mi.getbDefault().equals("true")) {
                conta = 1;
                break;
            }
        }
        if (conta == 0) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se ha agregado un Email por defecto.";
            return;
        }*/
        try {
            List<MaeTelefono> maeTelefonosSend = new ArrayList<>();
            for (MaeTelefono mt : maeTelefonos) {
                if (mt.getBDefault().equals("true")) {
                    //maeTelefonos.get(i).setBDefault("1");
                    mt.setBDefault("1");
                } else {
                    mt.setBDefault("0");
                }
                maeTelefonosSend.add(mt);
                i++;
            }
            i = 0;
            List<MaeEmail> maeEmailsSend = new ArrayList<>();
            for (MaeEmail mi : maeEmails) {
                if (mi.getbDefault().equals("true")) {
                    //maeEmails.get(i).setbDefault("1");
                    //break;
                    mi.setbDefault("1");
                } else {
                    mi.setbDefault("0");
                }
                maeEmailsSend.add(mi);
                i++;
            }
            i = 0;
            List<MaeDireccion> maeDireccionsSend = new ArrayList<>();
            if (maeDireccions != null) {
                for (MaeDireccion md : maeDireccions) {
                    if (md.getBDefault().equals("true")) {
                        //maeEmails.get(i).setbDefault("1");
                        //break;
                        md.setBDefault("1");
                    } else {
                        md.setBDefault("0");
                    }
                    maeDireccionsSend.add(md);
                    i++;
                }
            }

            i = 0;

            MaePersona maePersonaSend;
            maePersonaSend = maePersona;
            maePersonaSend.setMaeFondo(new MaeFondo());
            maePersonaSend.setMaeTelefonoList(maeTelefonosSend);
            maePersonaSend.setMaeEmailList(maeEmailsSend);
            maePersonaSend.setMaeDireccionList(maeDireccionsSend);
            maePersonaSend.setcUsuarioAdd(SessionUtils.getUserName());
            maePersonaSend.setcUsuarioMod(SessionUtils.getUserName());

            if (maePersonaSend.geteEstado().equals("true")) {
                maePersonaSend.seteEstado("01");
            } else {
                maePersonaSend.seteEstado("00");
            }

            System.out.println(" maePersona" + maePersona.getDApeMat());

            if (maePersonaSend.getCPersonaId() > 0) {
                // actualizo
                if (getPersonaServ().actualizar(maePersona)) {
                    showMsg = true;
                    tipoMsj = "success";
                    mensaje = "grabar persona";
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se ha grabado a la persona.";
                }
            } else if (maePersonaSend.getCPersonaId() == 0) {
                // nuevo
                if (getPersonaServ().insertar(maePersonaSend) > 0) {
                    showMsg = true;
                    tipoMsj = "success";
                    mensaje = "grabar persona";
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se ha grabado a la persona.";
                }
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se ha grabado a la persona.";
            Logger.getLogger(ClienteManBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public IPersonaServ getPersonaServ() {
        return personaServ;
    }

    public void setPersonaServ(IPersonaServ personaServ) {
        this.personaServ = personaServ;
    }

    public List<MaePersona> getMaePersonaList() {
        return maePersonaList;
    }

    public void setMaePersonaList(List<MaePersona> maePersonaList) {
        this.maePersonaList = maePersonaList;
    }

    public List<CobTablas> getCobTablasTipoDocList() {
        return cobTablasTipoDocList;
    }

    public void setCobTablasTipoDocList(List<CobTablas> cobTablasTipoDocList) {
        this.cobTablasTipoDocList = cobTablasTipoDocList;
    }

    public List<CobTablas> getCobTablasSexoList() {
        return cobTablasSexoList;
    }

    public void setCobTablasSexoList(List<CobTablas> cobTablasSexoList) {
        this.cobTablasSexoList = cobTablasSexoList;
    }

    public List<CobTablas> getCobTablasEstadoCivilList() {
        return cobTablasEstadoCivilList;
    }

    public void setCobTablasEstadoCivilList(List<CobTablas> cobTablasEstadoCivilList) {
        this.cobTablasEstadoCivilList = cobTablasEstadoCivilList;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public CobTablas getCobTablas() {
        return cobTablas;
    }

    public void setCobTablas(CobTablas cobTablas) {
        this.cobTablas = cobTablas;
    }

    public List<CobTablas> getCobTablasGradoList() {
        return cobTablasGradoList;
    }

    public void setCobTablasGradoList(List<CobTablas> cobTablasGradoList) {
        this.cobTablasGradoList = cobTablasGradoList;
    }

    public List<CobTablas> getCobTablasTeleList() {
        return cobTablasTeleList;
    }

    public void setCobTablasTeleList(List<CobTablas> cobTablasTeleList) {
        this.cobTablasTeleList = cobTablasTeleList;
    }

    public MaeTelefono getMaeTelefono() {
        return maeTelefono;
    }

    public void setMaeTelefono(MaeTelefono maeTelefono) {
        this.maeTelefono = maeTelefono;
    }

    public MaeEmail getMaeEmail() {
        return maeEmail;
    }

    public void setMaeEmail(MaeEmail maeEmail) {
        this.maeEmail = maeEmail;
    }

    public List<CobTablas> getCobTablasEmailList() {
        return cobTablasEmailList;
    }

    public void setCobTablasEmailList(List<CobTablas> cobTablasEmailList) {
        this.cobTablasEmailList = cobTablasEmailList;
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

    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
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

    public List<MaeTelefono> getMaeTelefonos() {
        return maeTelefonos;
    }

    public void setMaeTelefonos(List<MaeTelefono> maeTelefonos) {
        this.maeTelefonos = maeTelefonos;
    }

    public List<MaeEmail> getMaeEmails() {
        return maeEmails;
    }

    public void setMaeEmails(List<MaeEmail> maeEmails) {
        this.maeEmails = maeEmails;
    }

    public List<CobTablas> getCobTablasZonaList() {
        return cobTablasZonaList;
    }

    public void setCobTablasZonaList(List<CobTablas> cobTablasZonaList) {
        this.cobTablasZonaList = cobTablasZonaList;
    }

    public List<CobTablas> getCobTablasViaList() {
        return cobTablasViaList;
    }

    public void setCobTablasViaList(List<CobTablas> cobTablasViaList) {
        this.cobTablasViaList = cobTablasViaList;
    }

    public CobTablas getCobTablasVia() {
        return cobTablasVia;
    }

    public void setCobTablasVia(CobTablas cobTablasVia) {
        this.cobTablasVia = cobTablasVia;
    }

    public CobTablas getCobTablasZona() {
        return cobTablasZona;
    }

    public void setCobTablasZona(CobTablas cobTablasZona) {
        this.cobTablasZona = cobTablasZona;
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

    public List<MaeDireccion> getMaeDireccions() {
        return maeDireccions;
    }

    public void setMaeDireccions(List<MaeDireccion> maeDireccions) {
        this.maeDireccions = maeDireccions;
    }

}
