/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabTipoDocumento;
import pop.comun.dominio.TabTipoDocumentoEstado;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IDocumentosServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.ITipoDocumentoEstadoServ;
import pop.webcobranzas.iface.ITipoDocumentoServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.ifacerep.IArcDocumentoServ;
import pop.webcobranzas.servicio.DocumentosServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.TipoDocumentoEstadoServ;
import pop.webcobranzas.servicio.TipoDocumentoServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.serviciorep.ArcDocumentoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class DocumentoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private DocumentoDetBean documentoDetBean;

    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // imnueble (busq por provincia/distrito)
    private MaeInmueble maeInmueble = new MaeInmueble();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // tipo de documento
    private TabTipoDocumento tabTipoDocumento = new TabTipoDocumento();
    // tipo de documento estado
    private TabTipoDocumentoEstado tabTipoDocumentoEstado = new TabTipoDocumentoEstado();
    // inversion busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    // documento
    private TabDocumentos tabDocumentos = new TabDocumentos();

    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista de tipo de documento
    private List<TabTipoDocumento> tabTipoDocumentos;
    // lista de tipo de documento estado
    private List<TabTipoDocumentoEstado> tabTipoDocumentoEstados;
    // lista de documentos
    private List<TabDocumentos> documentoList;

    // servicios de tipo de documento
    private ITipoDocumentoServ tipoDocumentoServ = new TipoDocumentoServ();
    // servicios de tipo de documento estado
    private ITipoDocumentoEstadoServ tipoDocumentoEstadoServ = new TipoDocumentoEstadoServ();
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de documentos
    private IDocumentosServ documentosServ = new DocumentosServ();
    
    
    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    /**
     * Creates a new instance of DocumentoBean
     */
    public DocumentoBean() {
        try {
            maeInversion.setMaeFondo(maeFondo);
            maeInversion.setcPersonaId(maePersona);
            maeInversion.setMaeInmueble(maeInmueble);
            tabDocumentos.setTabTipoDocumento(tabTipoDocumento);
            tabDocumentos.setMaeInversion(maeInversion);
            //maeTelefonos = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDeudorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarTipoDocumento() {
        try {
            showMsg = false;
            if (tabTipoDocumentos == null) {
                tabTipoDocumentos = getTipoDocumentoServ().listarTipoDocumento(new TabTipoDocumento());
            }
            System.out.println(" cantidad de tabTipoDocumentos -> " + tabTipoDocumentos.size());
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.DocumentoBean.listarTipoDocumento()");
        }
    }

    public void listarProvincia() {
        try {
            if (ubigeoProv == null) {
                ubigeoProv = getUbigeoServ().listarProvincia(maeUbigeoP);
            }
            System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }

    public void listarFondos() {
        try {
            showMsg = false;
            if (maeFondoList == null) {
                maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            }
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            System.out.println("pop.webcobranzas.bean.InversionBean.listarDistrito()");
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
            System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void buscarDocumentos() {
        try {

            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);

            documentoList = getDocumentosServ().listarDocumentos(tabDocumentos);

            System.out.println("   Lista de Documentos = " + documentoList.size());

        } catch (Exception e) {
            System.out.println("e " + e.getMessage());
        }
    }

        
    public ITipoDocumentoServ getTipoDocumentoServ() {
        return tipoDocumentoServ;
    }

    public void setTipoDocumentoServ(ITipoDocumentoServ tipoDocumentoServ) {
        this.tipoDocumentoServ = tipoDocumentoServ;
    }

    public ITipoDocumentoEstadoServ getTipoDocumentoEstadoServ() {
        return tipoDocumentoEstadoServ;
    }

    public void setTipoDocumentoEstadoServ(ITipoDocumentoEstadoServ tipoDocumentoEstadoServ) {
        this.tipoDocumentoEstadoServ = tipoDocumentoEstadoServ;
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

    public TabTipoDocumento getTabTipoDocumento() {
        return tabTipoDocumento;
    }

    public void setTabTipoDocumento(TabTipoDocumento tabTipoDocumento) {
        this.tabTipoDocumento = tabTipoDocumento;
    }

    public TabTipoDocumentoEstado getTabTipoDocumentoEstado() {
        return tabTipoDocumentoEstado;
    }

    public void setTabTipoDocumentoEstado(TabTipoDocumentoEstado tabTipoDocumentoEstado) {
        this.tabTipoDocumentoEstado = tabTipoDocumentoEstado;
    }

    public List<TabTipoDocumento> getTabTipoDocumentos() {
        return tabTipoDocumentos;
    }

    public void setTabTipoDocumentos(List<TabTipoDocumento> tabTipoDocumentos) {
        this.tabTipoDocumentos = tabTipoDocumentos;
    }

    public List<TabTipoDocumentoEstado> getTabTipoDocumentoEstados() {
        return tabTipoDocumentoEstados;
    }

    public void setTabTipoDocumentoEstados(List<TabTipoDocumentoEstado> tabTipoDocumentoEstados) {
        this.tabTipoDocumentoEstados = tabTipoDocumentoEstados;
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

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public IDocumentosServ getDocumentosServ() {
        return documentosServ;
    }

    public void setDocumentosServ(IDocumentosServ documentosServ) {
        this.documentosServ = documentosServ;
    }

    public TabDocumentos getTabDocumentos() {
        return tabDocumentos;
    }

    public void setTabDocumentos(TabDocumentos tabDocumentos) {
        this.tabDocumentos = tabDocumentos;
    }

    public List<TabDocumentos> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<TabDocumentos> documentoList) {
        this.documentoList = documentoList;
    }

    public DocumentoDetBean getDocumentoDetBean() {
        return documentoDetBean;
    }

    public void setDocumentoDetBean(DocumentoDetBean documentoDetBean) {
        this.documentoDetBean = documentoDetBean;
    }


}
