package pop.webcobranzas.bean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MovimientoCartas;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICuotaPagoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.IRequerimientoServ;
import pop.webcobranzas.iface.ISeguimientoServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.properties.ManageProperties;
import pop.webcobranzas.servicio.CuotaPagoServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.RequerimientoServ;
import pop.webcobranzas.servicio.SeguimientoServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.util.MailSender;

/**
 *
 * @author RC433838
 */
@Named
@ViewScoped
public class RequerimientoBean extends CobRequerimientoCartas implements Serializable {

    /**
     * @return the clienteDeudorBean
     */
    public ClienteDeudorBean getClienteDeudorBean() {
        return clienteDeudorBean;
    }

    /**
     * @param clienteDeudorBean the clienteDeudorBean to set
     */
    public void setClienteDeudorBean(ClienteDeudorBean clienteDeudorBean) {
        this.clienteDeudorBean = clienteDeudorBean;
    }

    private IFondoServ fondoServ = new FondoServ();
    private ITablasServ tablasServ = new TablasServ();
    private IRequerimientoServ requeriminetoServ = new RequerimientoServ();    
    private ICuotaPagoServ cuotaPagoServ = new CuotaPagoServ();    
    private ISeguimientoServ seguimientoServ = new SeguimientoServ();    

    private MaeInversion maeInversion = new MaeInversion();

    private MaeFondo maeFondo = new MaeFondo();
    private List<MaeFondo> maeFondoList; 

    private CobTablas cobTablasEstados = new CobTablas();  
    private List<CobTablas> listaEstados;
    
    private List<CobTablas> listaTipoRequerimiento;    
    private List<CobTablas> listaTipoRequerimiento2;
    
    private List<MovimientoCartas> cartasList = new ArrayList<>();
    
    private MaePersona maePersona = new MaePersona();
    private List<CobRequerimientoCartas> requerimientoList;
    
    //private CobMaeSeguimiento maeSeguimiento;
     
    
    private Boolean load = false;
    private String errorNewMod = "";
    private String pagePrev;
    private String pageNext;
    private Integer sinSugerencia=0;
    
    
    @Inject
    private EstadoCuentaBean estadoCuentaBean;   
    
    @Inject
    private SaldoDeudorBean saldoDeudorBean;   
    
    @Inject
    private ClienteDeudorBean clienteDeudorBean;
    
    private ManageProperties properties; 
    
    // cuotaPago realizar formulas con fecha
    private MaeCuotaPago cuotaPagoFechaCorte = new MaeCuotaPago();
    
    private Boolean checkAll = false;
    
    private Boolean detallado = true;
       
    
    
    public RequerimientoBean(){
          properties = new ManageProperties();
          properties.getProperties();      
    }
 
    public void initTipoCons(LoginBean login, String title, String prev, String next){
            
        try {
     
            login.setPageTitle(title);
            setPagePrev(prev);
            setPageNext(next);            
            
        } catch (Exception e) {

        }
      
    }
    
    public void postLoad(){
        
        if(!load){
            listarSugerido();
            load = true;
        }
        
    }
    
    public void listarFondos() {
        System.out.println("RequerimientoBean().listarFondos");
        try {
          
            if (getMaeFondoList() == null) {
                setMaeFondoList(getFondoServ().listarFondos(new MaeFondo()));
            }
            System.out.println("cantidad de Fondos -> " + getMaeFondoList().size());
        } catch (Exception e) {

        }
    }

    public void listarEstados(String defecto){
            
        try {
            
            getCobTablasEstados().setCtablaId("0623");
            getCobTablasEstados().setCtablaDetId(null);
            setListaEstados(getTablasServ().listarTablas(getCobTablasEstados()));
            setReqEstado(defecto);
            
        } catch (Exception e) {

        }
      
    }  
    
    public void listarTipoReque(){
        
        
        try {
            
            getCobTablasEstados().setCtablaId("0622");
            getCobTablasEstados().setCtablaDetId(null);
            setListaTipoRequerimiento(getTablasServ().listarTablas(getCobTablasEstados()));
            
        } catch (Exception e) {

        }
        
        
        try {
            List<CobTablas> lista = new ArrayList<CobTablas>();
            
            getCobTablasEstados().setCtablaId("0622");
            getCobTablasEstados().setCtablaDetId(null);
            
            
            for(CobTablas ct: listaTipoRequerimiento){

                if(!ct.getCtablaDetId().equals("1000") && !ct.getCtablaDetId().equals("2000"))
                    lista.add(ct);                
                
            }
            
            setListaTipoRequerimiento2(lista);
            
        } catch (Exception e) {
            System.out.println("listarTipoReque() --> " + e.getMessage());
        }
        
            

      
    }     
    
    public void listarRequerimientos() {
        try {
            System.out.println("RequerimientoBean.listarRequerimientos(super)");

            CobRequerimientoCartas oReq = new CobRequerimientoCartas();
            oReq.setFondoId(getFondoId());
            oReq.setReqEstado(getReqEstado());
            oReq.setDvalor_bv(dvalor_bv);
            oReq.setInversion(maeInversion);
            oReq.setReqEnvio(reqEnvio);
            oReq.setReqEnvio2(reqEnvio2);
            oReq.setReqRecepcion(reqRecepcion);
            oReq.setReqRecepcion2(reqRecepcion2);
            oReq.setTipoCarta(tipoCarta);
            
            maeInversion.setcPersonaId(maePersona);
            
            oReq.setInversion(maeInversion);
            
            requerimientoList = requeriminetoServ.allRequerimientos(oReq);
            for(CobRequerimientoCartas car : requerimientoList){
                
                System.out.println("distritos. " + car.getInversion().getMaeInmueble().getDDir1());
                
            }
            
            setCriterios(null);
                        
            System.out.println("   Lista de listarRequerimeintos = " + getRequerimientoList().size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }

    public void listarCriterios(String tipo, int numero) {
        try {
            System.out.println("RequerimientoBean.listarCriterios()");

            CobRequerimientoCriterios oCri = new CobRequerimientoCriterios();
            oCri.setReqTipo(tipo);
            oCri.setReqNumero(numero);
            
            setCriterios(requeriminetoServ.findCriterios(oCri));
            
                        
            System.out.println("   Lista de listarCriterios = " + getCriterios().size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
    
    public void listarSugerido(){
        
        try {
            System.out.println("RequerimientoBean.listarSugerido()");

            CobRequerimientoCartas oReq = new CobRequerimientoCartas();
            oReq.setFondoId(getFondoId());
            
            setCriterios(requeriminetoServ.findReqSugerido());
            
            for(CobRequerimientoCriterios reqCri:getCriterios()){
                
                if(reqCri.getRequerimiento().getTipoCarta() != null){
                
                    for(CobTablas tipReq: listaTipoRequerimiento){

                            if(reqCri.getRequerimiento().getTipoCarta().equals(tipReq.getCtablaDetId())){
                                tipReq.setCampo1(tipReq.getCampo1()+1);
                                break;
                            }
                    }
                
               }else{                    
                    sinSugerencia = sinSugerencia+1;                    
               }                
            }
            
            System.out.println("   Lista de listarRequerimeintos = " + getCriterios().size());
            

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }        
        
    }
    
    public void listarDeudores(){
        setCriterios(null);
        clienteDeudorBean.buscarDeudores();
    }
    
    
    public String grabarRequerimiento1(int tipo){
        try {
            System.out.println("RequerimientoBean.grabarRequerimiento1()");
            String rpta = "";
            
            for(CobRequerimientoCriterios req: getCriterios() ){
                                
                if(req.getRequerimiento().getCarta().getPriori()==tipo){

                    if(req.getRequerimiento().getTipoCarta() != null && !req.getRequerimiento().getTipoCarta().equals("1000") && !req.getRequerimiento().getTipoCarta().equals("2000")){
                        req.getRequerimiento().setcUsuarioAdd(SessionUtils.getUserName());
                        rpta = requeriminetoServ.addRequerimiento(req);                   
                    }                    
                }

                
            }
                                    
            

            return getPagePrev()+"?faces-redirect=true";
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }           
        
        return null;
    }
    
    public String grabarRequerimiento2(){
        try {
            System.out.println("RequerimientoBean.grabarRequerimiento2()");
            String rpta = "";
            
            for (MaeInversion inv: clienteDeudorBean.getMaeInversionList()){
                
                if(inv.getRequerimiento().getTipoCarta() != null){
                    CobRequerimientoCriterios req = new CobRequerimientoCriterios();
                    CobRequerimientoCartas car = new  CobRequerimientoCartas();
                    car.setcUsuarioAdd(SessionUtils.getUserName());
                    car.setTipoCarta(inv.getRequerimiento().getTipoCarta());
                    car.setFondoId(inv.getMaeFondo().getCFondoId());
                    car.setDvalor_bv(inv.getCInversion());

                    req.setRequerimiento(car);

                    rpta = requeriminetoServ.addRequerimiento(req);
              }
              
            }
            
             
                          
            System.out.println(" grabarRequerimiento" + rpta);
            
            return getPagePrev()+"?faces-redirect=true";

        } catch (Exception e) {
             System.out.println(e.getMessage());
        } 
        return null;
    }    

    
    public String acciones(int opcion) {
        
        List<CobRequerimientoCartas> mails = new ArrayList<>();
        List<CobRequerimientoCartas> lisReq = new ArrayList<>();
        
        String url = null;
        
        try {
            switch(opcion) {
              case 1:
                    url = getPageNext()+"?faces-redirect=true";  
                break;
              case 2:
                  
                  for(CobRequerimientoCartas oReq: requerimientoList){
                    
                    if(oReq.isSel() && oReq.getReqEstado().equals("0001")){
                    
                        oReq.setcUsuarioMod(SessionUtils.getUserName());
                        oReq.setReqEstado("0002");
                        requeriminetoServ.cambiaEstadoReq(oReq);
                        
                        
                        lisReq.add(oReq);
                        
                        CobRequerimientoCartas asunto = new CobRequerimientoCartas();
                        
                        asunto.setTipoCarta(oReq.getTipoCarta());
                        asunto.setTipoCarta(oReq.getTipoCarta().replace("0007", "0001").replace("0008", "0001").replace("0009", "0002").replace("0006", "0002"));                        
                        CobTablas tipo = new CobTablas();                        
                        
                        tipo.setDdescripcion(oReq.getCarta().getDdescripcion().replace("RQ1 SIN INSCRIPCIONES", "RQ1 PRIMERA C.NOTARIAL").replace("RQ1 HIPOTECA INSCRITA SIN TCHN", "RQ1 PRIMERA C.NOTARIAL").replace("RQ1 HIPOTECA INSCRITA CON TCHN", "RQ1 PRIMERA C.NOTARIAL").replace("RQ2 SIN INSCRIPCIONES", "RQ2 REITERATIVO C.NOTARIAL").replace("RQ2 HIPOTECA INSCRITA SIN TCHN", "RQ2 REITERATIVO C.NOTARIAL").replace("RQ2 HIPOTECA INSCRITA CON TCHN", "RQ2 REITERATIVO C.NOTARIAL"));                        
                        asunto.setCarta(tipo);
                        

                            
                        if(mails.size()>0){
                            int ubicado=0;
                            for(CobRequerimientoCartas mail: mails){
                                ubicado=ubicado+1;
                                if(mail.getTipoCarta().equals(asunto.getTipoCarta())){
                                    ubicado=0;
                                    break;
                                }
                            }
                            if(ubicado!=0){                                                                                    
                                mails.add(asunto);
                            }
                        } else {                            
                            mails.add(asunto);
                        }
                    }
                    
                  }
                       
                  for(CobRequerimientoCartas mail: mails){
                        mailReq(mail, lisReq);          
                  }                  
                  

                  listarRequerimientos();

                break;
              case 3: 
                            
                break;
              case 4: 
                       
                  for(CobRequerimientoCartas oReq: requerimientoList){
                    
                    if(oReq.isSel() && oReq.getReqEstado().equals("0001")){
                    
                        oReq.setcUsuarioMod(SessionUtils.getUserName());
                        requeriminetoServ.removerRequerimiento(oReq);
                    }
                    
                  }
                  
                  listarRequerimientos();
                  
                break;                
              default:
                // code block
            }
            
        } catch (Exception e) {
            System.err.println("error: "+e.getMessage());
        }
        
     return url;          
    }     
    
    private void mailReq(CobRequerimientoCartas asunto, List<CobRequerimientoCartas> lisReq){
            try{
                StringBuilder body = new StringBuilder();
                
                String subject="EVA: "+asunto.getCarta().getDdescripcion();
                
                
                //Mejor forma...
                body.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"");
                body.append("<html>");
                body.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head>");
                if (asunto.getCarta().getDdescripcion().trim().equals("PROTESTO TCHN")){
                     body.append("<body>Estimados:<br><br>Se genera requerimiento para el envío de protesto tchn, según el formato que corresponda."
                        + "  <br><br>  <table border 1>");
                    
                }else{
                    body.append("<body>Estimados:<br><br>Se genera requerimiento para el envío de cartas notariales, según el formato que corresponda."
                        + "  <br><br>  <table border 1>");
                }
                body.append("<tr><td>Fondo</td><td>Tipo Rq</td><td>Código</td><td>Cliente</td><td>Distrito</td></tr>");
                 for(int indice = 0;indice<lisReq.size();indice++)
                {
                    
                      if(asunto.getTipoCarta().equals(lisReq.get(indice).getTipoCarta().replace("0007", "0001").replace("0008", "0001").replace("0009", "0002").replace("0006", "0002"))){
                        body.append("<tr><td> " +  lisReq.get(indice).getFondo().getDFondo()+"</td> ");
                        body.append("<td> " +  lisReq.get(indice).getCarta().getDdescripcion()  +"</td> ");
                        body.append("<td> " +  lisReq.get(indice).getDvalor_bv()  +"</td> ");
                        body.append("<td> " +  lisReq.get(indice).getInversion().getcPersonaId().getDApePat() + " " +lisReq.get(indice).getInversion().getcPersonaId().getDNombres()+"</td> ");
                        body.append("<td> " + lisReq.get(indice).getInversion().getMaeInmueble().getDDir1()+"</td> </tr> ");
                    }
                                                
                }
                 
                body.append("</table>");
                body.append("<br><br><br><br>");
                body.append("Nota:<br>" );
                body.append("No responder este correo, puesto que el origen es desconocido<br>");
                

                //System.out.println(properties.getMail_from()+  properties.getMail_to()  );
                
                String from=properties.getMail_from();
                String pass=properties.getMail_pwd();
                String[] to=properties.getMail_to();
               
                 //System.out.println("envio de correo: "+e.getMessage());  
               
                MailSender mail1 = new MailSender(body.toString() , subject, from, pass, to);
                Thread t = new Thread(mail1);
                t.start();
                
            } catch(Exception e){
               System.out.println("error de envio de correo: "+e.getMessage());  
            }         
    }
    
    public void calcularCuoPagFutInicial(CobRequerimientoCartas oCarta) {
        try {
            
            if(oCarta.getMaeEstadoCuentaList().size()<1) {
                
                Date fechaCorte = new Date();
                                
                System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - calcularCuoPagFutInicial - " + oCarta.getInversion().getcMaeInversionId() + " - " + oCarta.getInversion().getCInversion() + " - " + fechaCorte);
                            
                getCuotaPagoFechaCorte().setMaeInversion(oCarta.getInversion());
                getCuotaPagoFechaCorte().setfIniBusq(fechaCorte);
                getCuotaPagoFechaCorte().setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(getCuotaPagoFechaCorte()).getiPendiente());
                
                // estado de cuenta
                oCarta.getInversion().setfIniBusq(fechaCorte);
                CobTchn oCobTchn = new CobTchn();
                oCobTchn.setMaeInversion(oCarta.getInversion());
                
                oCobTchn.setcUsuarioAdd(SessionUtils.getUserName());
                
                getEstadoCuentaBean().setMaeInversion(oCarta.getInversion());
                getEstadoCuentaBean().getMaeEstadoCuenta().setfIniBusq(fechaCorte);
                getEstadoCuentaBean().buscarECDetalle(oCobTchn);
                
                oCarta.setMaeEstadoCuentaList(getEstadoCuentaBean().getMaeEstadoCuentaList());
               
            }
            
        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.SeguimientoBean.calcularCuoPagFutInicial() "+e.getMessage());
        }
    }    

    
    public void obtenerSeguimiento(CobRequerimientoCartas oCarta) {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " SeguimientoBean - obtenerSeguimiento - " + 
            oCarta.getMaeSeguimiento().getMaeInversion().getcMaeInversionId() + " - " + oCarta.getMaeSeguimiento().getMaeInversion().getCInversion());
            //oCarta.getSeguimiento().setMaeInversion(oCarta.getInversion());
            //cobMaeSeguimiento.setMaeInversion(maeInversionSeguiDet);
            
            setMaeSeguimiento(getSeguimientoServ().listarSeguimiento(oCarta.getMaeSeguimiento()));
                    
            System.err.println(":::::lista de seguimientos: "+getMaeSeguimiento().getCobSeguimientoList().size());
            
            
        } catch (Exception e) {
            System.err.println(".obtenerSeguimiento(CobRequerimientoCartas oCarta) "+e.getMessage());
        }
    }
    
    public void obtenerCartasNotariales(CobRequerimientoCartas oCarta) {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " RequerimientoBean - obtenerCartasNotariales - " + 
            oCarta.getInversion().getcFONDO() + " - " + oCarta.getInversion().getCInversion());
            //oCarta.getSeguimiento().setMaeInversion(oCarta.getInversion());
            //cobMaeSeguimiento.setMaeInversion(maeInversionSeguiDet);
            cartasList = requeriminetoServ.findCartas(oCarta.getInversion());
            
            System.err.println("lista de cartas notariales: "+cartasList.size());
            
            
        } catch (Exception e) {
            System.err.println(":obtenerCartasNotariales(CobRequerimientoCartas oCarta) "+e.getMessage());
        }
    }    
    
    
    public void selTodos(){
        
        for(CobRequerimientoCartas rq : requerimientoList){  
                        
            rq.setSel(checkAll);
        }
        
    }

    
    /**
     * @return the maeInversion
     */
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    /**
     * @param maeInversion the maeInversion to set
     */
    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    /**
     * @return the fondoServ
     */
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    /**
     * @param fondoServ the fondoServ to set
     */
    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    /**
     * @return the tablasServ
     */
    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    /**
     * @param tablasServ the tablasServ to set
     */
    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    /**
     * @return the maeFondo
     */
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    /**
     * @param maeFondo the maeFondo to set
     */
    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    /**
     * @return the maeFondoList
     */
    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    /**
     * @param maeFondoList the maeFondoList to set
     */
    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    
    /**
     * @return the cobTablasEstados
     */
    public CobTablas getCobTablasEstados() {
        return cobTablasEstados;
    }

    /**
     * @param cobTablasEstados the cobTablasEstados to set
     */
    public void setCobTablasEstados(CobTablas cobTablasEstados) {
        this.cobTablasEstados = cobTablasEstados;
    }

    /**
     * @return the listaEstados
     */
    public List<CobTablas> getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(List<CobTablas> listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the maePersona
     */
    public MaePersona getMaePersona() {
        return maePersona;
    }

    /**
     * @param maePersona the maePersona to set
     */
    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    /**
     * @return the requeriminetoServ
     */
    public IRequerimientoServ getRequeriminetoServ() {
        return requeriminetoServ;
    }

    /**
     * @param requeriminetoServ the requeriminetoServ to set
     */
    public void setRequeriminetoServ(IRequerimientoServ requeriminetoServ) {
        this.requeriminetoServ = requeriminetoServ;
    }

    /**
     * @return the requerimientoList
     */
    public List<CobRequerimientoCartas> getRequerimientoList() {
        return requerimientoList;
    }

    /**
     * @param requerimientoList the requerimientoList to set
     */
    public void setRequerimientoList(List<CobRequerimientoCartas> requerimientoList) {
        this.requerimientoList = requerimientoList;
    }

    /**
     * @return the errorNewMod
     */
    public String getErrorNewMod() {
        return errorNewMod;
    }

    /**
     * @param errorNewMod the errorNewMod to set
     */
    public void setErrorNewMod(String errorNewMod) {
        this.errorNewMod = errorNewMod;
    }

    /**
     * @return the pagePrev
     */
    public String getPagePrev() {
        return pagePrev;
    }

    /**
     * @param pagePrev the pagePrev to set
     */
    public void setPagePrev(String pagePrev) {
        this.pagePrev = pagePrev;
    }

    /**
     * @return the pageNext
     */
    public String getPageNext() {
        return pageNext;
    }

    /**
     * @param pageNext the pageNext to set
     */
    public void setPageNext(String pageNext) {
        this.pageNext = pageNext;
    }

    /**
     * @return the listaTipoRequerimiento
     */
    public List<CobTablas> getListaTipoRequerimiento() {
        return listaTipoRequerimiento;
    }

    /**
     * @param listaTipoRequerimiento the listaTipoRequerimiento to set
     */
    public void setListaTipoRequerimiento(List<CobTablas> listaTipoRequerimiento) {
        this.listaTipoRequerimiento = listaTipoRequerimiento;
    }

    /**
     * @return the estadoCuentaBean
     */
    public EstadoCuentaBean getEstadoCuentaBean() {
        return estadoCuentaBean;
    }

    /**
     * @param estadoCuentaBean the estadoCuentaBean to set
     */
    public void setEstadoCuentaBean(EstadoCuentaBean estadoCuentaBean) {
        this.estadoCuentaBean = estadoCuentaBean;
    }

    /**
     * @return the cuotaPagoFechaCorte
     */
    public MaeCuotaPago getCuotaPagoFechaCorte() {
        return cuotaPagoFechaCorte;
    }

    /**
     * @param cuotaPagoFechaCorte the cuotaPagoFechaCorte to set
     */
    public void setCuotaPagoFechaCorte(MaeCuotaPago cuotaPagoFechaCorte) {
        this.cuotaPagoFechaCorte = cuotaPagoFechaCorte;
    }

    /**
     * @return the cuotaPagoServ
     */
    public ICuotaPagoServ getCuotaPagoServ() {
        return cuotaPagoServ;
    }

    /**
     * @param cuotaPagoServ the cuotaPagoServ to set
     */
    public void setCuotaPagoServ(ICuotaPagoServ cuotaPagoServ) {
        this.cuotaPagoServ = cuotaPagoServ;
    }

    /**
     * @return the seguimientoServ
     */
    public ISeguimientoServ getSeguimientoServ() {
        return seguimientoServ;
    }

    /**
     * @param seguimientoServ the seguimientoServ to set
     */
    public void setSeguimientoServ(ISeguimientoServ seguimientoServ) {
        this.seguimientoServ = seguimientoServ;
    }

    /**
     * @return the maeSeguimiento
     */
    /*
    public CobMaeSeguimiento getMaeSeguimiento() {
        return maeSeguimiento;
    }
    */

    /**
     * @param maeSeguimiento the maeSeguimiento to set
     */
    /*
    public void setMaeSeguimiento(CobMaeSeguimiento maeSeguimiento) {
        this.maeSeguimiento = maeSeguimiento;
    }
    */

    /**
     * @return the cartasList
     */
    public List<MovimientoCartas> getCartasList() {
        return cartasList;
    }

    /**
     * @param cartasList the cartasList to set
     */
    public void setCartasList(List<MovimientoCartas> cartasList) {
        this.cartasList = cartasList;
    }

    /**
     * @return the load
     */
    public Boolean getLoad() {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(Boolean load) {
        this.load = load;
    }

    /**
     * @return the checkAll
     */
    public Boolean getCheckAll() {
        return checkAll;
    }

    /**
     * @param checkAll the checkAll to set
     */
    public void setCheckAll(Boolean checkAll) {
        this.checkAll = checkAll;
    }

    /**
     * @return the sinSugerencia
     */
    public Integer getSinSugerencia() {
        return sinSugerencia;
    }

    /**
     * @param sinSugerencia the sinSugerencia to set
     */
    public void setSinSugerencia(Integer sinSugerencia) {
        this.sinSugerencia = sinSugerencia;
    }

    /**
     * @return the listaTipoRequerimiento2
     */
    public List<CobTablas> getListaTipoRequerimiento2() {
        return listaTipoRequerimiento2;
    }

    /**
     * @param listaTipoRequerimiento2 the listaTipoRequerimiento2 to set
     */
    public void setListaTipoRequerimiento2(List<CobTablas> listaTipoRequerimiento2) {
        this.listaTipoRequerimiento2 = listaTipoRequerimiento2;
    }

    /**
     * @return the detallado
     */
    public Boolean getDetallado() {
        return detallado;
    }

    /**
     * @param detallado the detallado to set
     */
    public void setDetallado(Boolean detallado) {
        this.detallado = detallado;
    }


    
   
}
