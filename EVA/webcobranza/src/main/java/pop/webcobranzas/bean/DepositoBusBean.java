/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.properties.ManageProperties;
import pop.webcobranzas.servicio.DepositoServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.util.MailSender;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class DepositoBusBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private ManageProperties properties; 
    private MaeDeposito deposito = new MaeDeposito();
    private MaeFondo maeFondo =new MaeFondo();
    private MaeInversion maeInversion = new MaeInversion();
    private MaePersona maePersona = new MaePersona();
    
    private List<MaeDeposito> listDepositos;
    private List<MaeDeposito> listDepo;
    private List<MaeDeposito> listResumen;

   
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    
    
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de Depositos
    private IDepositoServ depositoServ = new DepositoServ();

    
    /**
     * Creates a new instance of DepositoBusBean
     */
    public DepositoBusBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        deposito.setMaeInversion(maeInversion);
        properties = new ManageProperties();
        properties.getProperties();
    }
    
    public String actualizarDepo(){
         try {             
            listDepositos = getDepositoServ().listarDepositos(deposito);
            System.out.println("   Lista de Depositos = " + listDepositos.size());
            
        } catch (Exception e) {
            
        }
         
         return "reporteOperacion";
         
    }
    
    
    public void actualizarnoDepositos(MaeDeposito maeDeposito){
         try {
             int resp;
            System.out.println("["+SessionUtils.getUserName()+"] "+" DepositoBean - actualizarDepositos no identificados "+maeDeposito.getDBcoNoperacion());
            
            resp = getDepositoServ().actualizarnoDepositos(maeDeposito);
            System.out.println(listDepositos.size());
            //System.out.println("   Lista de Depositos = " + listDepositos.size());
        } catch (Exception e) {
            
        }
         
    }
     
    public void eliminarnoDepositos(MaeDeposito maeDeposito){
         try {
            int resp;
            System.out.println("["+SessionUtils.getUserName()+"] "+" DepositoBean - Eliminar depositos no identificados " + maeDeposito.getDBcoNoperacion());
            resp = getDepositoServ().eliminarnoDepositos(maeDeposito);
            System.out.println("["+SessionUtils.getUserName()+"] "+" DepositoBean - LISTAR depositos no identificados " + maeDeposito.getDBcoNoperacion());
            listDepositos = getDepositoServ().listarNoDeposito(maeDeposito);
            //listMaeRecalRes=getDepositoServ().
            //System.out.println("pop.webcobranzas.bean.DemoBean.<init>()");
            //System.out.println("   Lista de Depositos = " + listDepositos.size());
        } catch (Exception e) {
            
        }
         
    }
    
    
    public void  listarNoDeposito(){
         try {
             
             
            listDepositos = getDepositoServ().listarNoDeposito(deposito);
       
       
            //System.out.println("pop.webcobranzas.bean.DemoBean.<init>()");
             
        } catch (Exception e) {
           
        }
         
    }
   
    
    public void  grabarDepositosNI(){
         try {
             
            String resp; 
            MaeDeposito deposito=new MaeDeposito();
            System.out.println(SessionUtils.getUserName());
            deposito.setcUsuarioAdd(SessionUtils.getUserName());
            resp = getDepositoServ().grabarDepositosNI(deposito);
            listResumen=getDepositoServ().fetchAllResumen(deposito);
            StringBuilder body = new StringBuilder();
            if (listResumen.size()>1) 
            {    
                //Mejor forma...
                body.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"");
                body.append("<html>");
                body.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head>");
                body.append("<body>Se envia el estado anterior y actual segun el recalculo"
                        + "  <br>para su regularizacion <br>  <table border 1>");
                body.append("<tr><CENTER><td colspan=8>Ajustes por Recalculo del Sistema</td></CENTER></tr>");
                body.append("<tr><td>  TCHN   </td><td>  Fecha  </td><td> Nro de cuota</td><td> Nro de Deposito</td><td>   Capital  </td><td>   Interes    </td><td>    Mora   </td><td>   Otros   </td></tr>");
                 for(int indice = 0;indice<listResumen.size();indice++)
                {
                        System.out.println(listResumen.get(indice).getpCAPITAL());
                        System.out.println(listResumen.get(indice).getnDeposito());
                        if (listResumen.get(indice).getMaeInversion().getcInversionId().trim().equals("")){
                        body.append("<tr><td colspan=9></td></tr>");           
                        }else{
                        body.append("<tr><td> " +  listResumen.get(indice).getMaeInversion().getcInversionId()+"</td> ");
                        body.append("<td> " +  listResumen.get(indice).getpDPAGO()  +"</td> ");
                        body.append("<td> " +  listResumen.get(indice).getpNCUOTA()  +"</td> ");
                        body.append("<td> " + listResumen.get(indice).getnDeposito()+"</td> ");
                        body.append("<td align='right'> " +  listResumen.get(indice).getpCAPITAL().toString()+"</td> ");
                        body.append("<td align='right'> " +  listResumen.get(indice).getpINTERES().toString() +"</td> ");
                        body.append("<td align='right'> " + listResumen.get(indice).getpMORA().toString()  +"</td> ");
                        body.append("<td align='right'> " +  listResumen.get(indice).getpOtros().toString() +"</td></tr> ");
                        }


                }
                body.append("</table>");
                body.append("<br><br><br><br>");
                body.append("Yul Robles Prieto.<br>" );
                body.append("Popular S.A - Sociedad Administradora de Fondos de Inversión<br>");
                body.append("Telf. +(511) 428- 3820 anexo 518 <br>" );
                body.append("(511) 428- 3821" );
                body.append("Av. Nicolás de Piérola 938 of. 302  Lima 01 <br>" );
                body.append("www.popular-safi.com </body></html>");

                System.out.println(properties.getMail_from()+  properties.getMail_to()  );
                String subject="Envio: Recalculo de Deposito"   ;
                String from=properties.getMail_from();
                String pass=properties.getMail_pwd();
                String[] to=properties.getMail_to();
                MailSender mail1 = new MailSender(body.toString() , subject, from, pass, to);
                Thread t = new Thread(mail1);
                t.start();
                }  
                listDepositos = getDepositoServ().listarNoDeposito(deposito);
            
       
            //System.out.println("pop.webcobranzas.bean.DemoBean.<init>()");
             
        } catch (Exception e) {
           
        }
         
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
        } catch (Exception e) {

        }
    }
    
    /**
     * @return the deposito
     */
    public MaeDeposito getDeposito() {
        return deposito;
    }

    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(MaeDeposito deposito) {
        this.deposito = deposito;
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

    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }
    
    public List<MaeDeposito> getListDepositos() {
        return listDepositos;
    }

    public void setListDepositos(List<MaeDeposito> listDepositos) {
        this.listDepositos = listDepositos;
    }

    public List<MaeDeposito> getListDepo() {	
         return listDepo;	
    }	

     public void setListDepo(List<MaeDeposito> listDepo) {	
        this.listDepo = listDepo;	
    }    
  
    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }
    
    public List<MaeDeposito> getListResumen() {
        return listResumen;
    }

    public void setListResumen(List<MaeDeposito> listResumen) {
        this.listResumen = listResumen;
    }
}

