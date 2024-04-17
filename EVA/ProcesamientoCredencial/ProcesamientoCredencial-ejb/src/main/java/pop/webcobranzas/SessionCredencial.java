/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dominio.Correo;
import pop.webcobranzas.dominio.Menu;
import pop.webcobranzas.dominio.Rol;
import pop.webcobranzas.dominio.Telefono;
import pop.webcobranzas.dominio.Usuario;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import pop.webcobranzas.dominio.MenuEventos;
/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCredencial")
public class SessionCredencial {
    public boolean validarUsuario(Usuario oUsuario) throws Exception {
        System.out.println("     <i> validarUsuario ");
        boolean rpta = false;

        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_USUARIO.SP_VERI_USUARIO(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());

        String contrasenia = oUsuario.getContrasenia().trim();
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(contrasenia.getBytes());
        
        //System.out.println("     ["+oUsuario.getUsuarioId().trim()+"] " + " CONTRASEÑA = " + oUsuario.getContrasenia().trim());
        System.out.println("     ["+oUsuario.getUsuarioId().trim()+"] " + " SHA3-512 = " + Hex.toHexString(digest));

        ocs.setString(2, Hex.toHexString(digest));
        ocs.registerOutParameter(3, OracleTypes.INTEGER);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO.SP_VERI_USUARIO.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO.SP_VERI_USUARIO.fin:"+diferencia);
        
        
        if ((int) ocs.getObject(3) > 0) {
            rpta = true;
        }
        cn.close();
        System.out.println("     <f> validarUsuario " + rpta);
        return rpta;
    }

    public Usuario obtenerUsuario(Usuario oUsuario) throws Exception {
        System.out.println("   <i> obtenerUsuario ");
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(false);
        newUsuario.setEstado(validarUsuario(newUsuario));
        if (newUsuario.isEstado()) {
            OracleConnection cn = null;
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            String sp = "{call PKG_USUARIO.SP_BUS_USUARIO(?,?,?,?,?)}";
            OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
            ocs.setString(1, oUsuario.getUsuarioId().trim());
            String contrasenia = oUsuario.getContrasenia().trim();
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(contrasenia.getBytes());
            ocs.setString(2, Hex.toHexString(digest));
            ocs.registerOutParameter(3, OracleTypes.CURSOR);
            ocs.registerOutParameter(4, OracleTypes.VARCHAR);
            ocs.registerOutParameter(5, OracleTypes.VARCHAR);
            
            java.util.Date dini = new java.util.Date();
            System.out.println("PKG_USUARIO.SP_BUS_USUARIO.ini");
            ocs.execute();
            java.util.Date dfin = new java.util.Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("PKG_USUARIO.SP_BUS_USUARIO.fin:"+diferencia);
            
            OracleResultSet rs = (OracleResultSet) ocs.getObject(3);
            
            while (rs.next()) {
                newUsuario.setUsuarioId(rs.getString("C_USUARIO_ID"));
                newUsuario.setNumLlamada(rs.getInt("N_LLAMADA"));
                newUsuario.setPerId(rs.getInt("C_PERSONA_ID"));
                newUsuario.setPerNom(rs.getString("D_NOMBRES"));
                newUsuario.setPerApePat(rs.getString("D_APE_PAT"));
                newUsuario.setPerApeMat(rs.getString("D_APE_MAT"));
                newUsuario.setBcontrasenia(rs.getString("B_CONTRASENIA"));
                Telefono newTelefono = new Telefono();
                newTelefono.setTelId(rs.getInt("C_TELEFONO_ID"));
                newTelefono.setTelTipo(rs.getString("C_TIPO_TEL"));
                newTelefono.setTelNumero(rs.getString("A_NUMERO"));
                newUsuario.setTelefono(newTelefono);
                Correo newEmail = new Correo();
                newEmail.setCorId(rs.getInt("C_EMAIL_ID"));
                newEmail.setCorTipo(rs.getString("C_TIPO_EMAIL_ID"));
                newEmail.setCorDesc(rs.getString("D_EMAIL"));
                newUsuario.setCorreo(newEmail);
                Rol newRol = new Rol();
                newRol.setRolId(rs.getInt("C_ROL_ID"));
                newRol.setRolNombre(rs.getString("D_NOMBRE"));
                newRol.setRolUsuarioId(rs.getInt("C_USUARIO_ROL_ID"));
                newUsuario.setRol(newRol);
                List<Menu> maeMenus = obtenerMenu(oUsuario);
                newUsuario.setMenu(maeMenus);
                List<Menu> maeOpcionesPermisosMenus= obtenerOpcionesPermisosMenu(oUsuario);
                newUsuario.setOpcionesPermisosmenu(maeOpcionesPermisosMenus);
            }
            newUsuario.setMensaje("ok.");
            cn.close();
            //setear eliminar seguimiento
            newUsuario.setEliminarSeguimiento(permisoControl(newUsuario.getUsuarioId().trim(),"62"));//62:ES ID DEL CONTROL DE SEQUIMIENTO
            
        } else {
            newUsuario.setMensaje("Error al ingresar los datos, por favor verificar.");
        }
        System.out.println("   <f> obtenerUsuario ");
        return newUsuario;
    }

    private List<Menu> obtenerMenu(Usuario oUsuario) throws Exception {
        List<Menu> menuList = new ArrayList<>();
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_USUARIO.SP_BUS_USU_MENU(?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(2, OracleTypes.CURSOR);
        ocs.registerOutParameter(3, OracleTypes.VARCHAR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO.SP_BUS_USU_MENU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO.SP_BUS_USU_MENU:"+diferencia);
        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(2);

        List<Menu> menuListEvaluar = new ArrayList<>();
        
        while (rs.next()) {
            Menu newMenu = new Menu();
            newMenu.setCmenuId(rs.getInt("C_MENU_ID"));
            newMenu.setCmenu(rs.getInt("C_MENU"));
            newMenu.setCmenuPadre(rs.getInt("C_MENU_PADRE"));
            newMenu.setMenuA(rs.getString("D_NOMBRE"));
            newMenu.setMenuAUrl(rs.getString("D_URL"));
            newMenu.setSimbolo(rs.getString("D_SIMBOLO"));
            newMenu.setMenuAE("none");
            newMenu.setBtn1(rs.getInt("BTN01") == 1 ? true : false);
            newMenu.setBtn2(rs.getInt("BTN02") == 1 ? true : false);
            newMenu.setBtn3(rs.getInt("BTN03") == 1 ? true : false);
            newMenu.setBtn4(rs.getInt("BTN04") == 1 ? true : false);
            newMenu.setBtn5(rs.getInt("BTN05") == 1 ? true : false);
            newMenu.setBtn6(rs.getInt("BTN06") == 1 ? true : false);
            newMenu.setBtn7(rs.getInt("BTN07") == 1 ? true : false);
            newMenu.setBtn8(rs.getInt("BTN08") == 1 ? true : false);
            newMenu.setBtn9(rs.getInt("BTN09") == 1 ? true : false);
            newMenu.setBtn10(rs.getInt("BTN10") == 1 ? true : false);
            menuListEvaluar.add(newMenu);
        }

        for (Menu oMaeMenu : menuListEvaluar) {
            if (oMaeMenu.getCmenuPadre() == 0) {
                Menu newMenu = oMaeMenu;
                newMenu.setoMenuList(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                newMenu.setoMenuList2(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                menuList.add(newMenu);
            }
        }
        
        cn.close();
        return menuList;
    }

    private List<Menu> buscaHijos(List<Menu> list, int padre) {
        List<Menu> menuListEvaluar = new ArrayList<>();
        for (Menu maeMenu : list) {
            
            if (maeMenu.getCmenuPadre() == padre) {
                menuListEvaluar.add(maeMenu);
            }
        }
        return menuListEvaluar;
    }
    /*
    private List<Menu> buscaHijos2(List<Menu> list, int padre) {
        List<Menu> menuListEvaluar = new ArrayList<>();
        List<Menu> menuListEvaluar2 = new ArrayList<>();
     
        for (Menu maeMenu : list) {
            if (maeMenu.getCmenuPadre() == padre) {
                menuListEvaluar.add(maeMenu);
            }
        }
        int x=0;
      
        for (Menu omaeMenu : menuListEvaluar) {             
            if (omaeMenu.getCmenuPadre() == padre ) {
                
                Menu rnewMenu = omaeMenu;
                
                for (Menu maeMenu : list) {
                    
                  // System.out.println("maeMenu.getCmenuPadre() == rnewMenu.getCmenu() "+ maeMenu.getCmenuPadre() +"=="+ rnewMenu.getCmenu());
                   if (maeMenu.getCmenuPadre() == rnewMenu.getCmenu()  ) {                       
                       //System.out.println("buscaHijos2 menuListEvaluar2.add(maeMenu)" );                       
                         menuListEvaluar2.add(maeMenu);
                     }
                   
                    x=x+1;
                    
                 }
              
              }
        }
        
        System.out.println("filasssss buscaHijos2.menuListEvaluar2 "+menuListEvaluar.size());
        
        return menuListEvaluar;
    
    }
    */
      

    public Usuario cambiarUsuario(Usuario oUsuario) throws Exception {
        System.out.println("   <i> cambiarContrasenia ");
        boolean rpta = false;
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(false);
        newUsuario.setEstado(validarUsuario(newUsuario));
        if (newUsuario.isEstado()) {
            OracleConnection cn = null;
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            String sp = "{call PKG_USUARIO.SP_UPD_USUARIO_CONTRA(?,?,?,?)}";
            OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
            ocs.setString(1, oUsuario.getUsuarioId().trim());

            String contrasenia = oUsuario.getContraseniaB().trim();
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(contrasenia.getBytes());
            System.out.println("   ["+oUsuario.getUsuarioId().trim()+"] " + " SHA3-512 = " + Hex.toHexString(digest));
            ocs.setString(2, Hex.toHexString(digest));
            ocs.registerOutParameter(3, OracleTypes.VARCHAR);
            ocs.registerOutParameter(4, OracleTypes.VARCHAR);
            
            java.util.Date dini = new java.util.Date();
            System.out.println("PKG_USUARIO.SP_UPD_USUARIO_CONTRA.ini");
            ocs.execute();
            java.util.Date dfin = new java.util.Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("PKG_USUARIO.SP_UPD_USUARIO_CONTRA.fin:"+diferencia);
            
            if (ocs.getString(3).equals("0")){
                rpta = true;
            }
            newUsuario.setEstado(rpta);
        }
                
        System.out.println("   <f> cambiarContrasenia ");
        
        return newUsuario;
    }

    //LISTARA TODAS LAS OPCIONES QUE TIENE PERMISO UN USUARIO, NO NECESARIAMENTE TIENEN QUE MOSTRARSE 
    //EN EL MENU DEL EVA
    private List<Menu> obtenerOpcionesPermisosMenu(Usuario oUsuario) throws Exception {
        List<Menu> menuList = new ArrayList<>();
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_USUARIO.SP_OPCS_PERMIS_USU(?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(2, OracleTypes.CURSOR);
        ocs.registerOutParameter(3, OracleTypes.VARCHAR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO.SP_OPCS_PERMIS_USU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO.SP_OPCS_PERMIS_USU.fin:"+diferencia);

        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(2);

        List<Menu> menuListEvaluar = new ArrayList<>();
        while (rs.next()) {
            Menu newMenu = new Menu();
            newMenu.setCmenuId(rs.getInt("C_MENU_ID"));
            newMenu.setCmenu(rs.getInt("C_MENU"));
            newMenu.setCmenuPadre(rs.getInt("C_MENU_PADRE"));
            newMenu.setMenuA(rs.getString("D_NOMBRE"));
            newMenu.setMenuAUrl(rs.getString("D_URL"));
            newMenu.setSimbolo(rs.getString("D_SIMBOLO"));
            newMenu.setMenuAE("none");
            newMenu.setBtn1(rs.getInt("BTN01") == 1 ? true : false);
            newMenu.setBtn2(rs.getInt("BTN02") == 1 ? true : false);
            newMenu.setBtn3(rs.getInt("BTN03") == 1 ? true : false);
            newMenu.setBtn4(rs.getInt("BTN04") == 1 ? true : false);
            newMenu.setBtn5(rs.getInt("BTN05") == 1 ? true : false);
            newMenu.setBtn6(rs.getInt("BTN06") == 1 ? true : false);
            newMenu.setBtn7(rs.getInt("BTN07") == 1 ? true : false);
            newMenu.setBtn8(rs.getInt("BTN08") == 1 ? true : false);
            newMenu.setBtn9(rs.getInt("BTN09") == 1 ? true : false);
            newMenu.setBtn10(rs.getInt("BTN10") == 1 ? true : false);
            menuListEvaluar.add(newMenu);
            
        }

        for (Menu oMaeMenu : menuListEvaluar) {
            if (oMaeMenu.getCmenuPadre() == 0) {
                Menu newMenu = oMaeMenu;
                newMenu.setoMenuList(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                newMenu.setoMenuList2(buscaHijos(menuListEvaluar, newMenu.getCmenu()));
                menuList.add(newMenu);
            }
        }
   
        cn.close();
        
        obtenerMenuEventos(oUsuario, menuList);
        
 
        
        return menuList;
    }
    
    private void obtenerMenuEventos(Usuario oUsuario, List<Menu> listaMenu) throws Exception {
        
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        
        String sp = "{call PKG_USUARIO.SP_EVENTOS_MENU(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setInt(1, 3);
        ocs.setString(2, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(3, OracleTypes.CURSOR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO_2.SP_EVENTOS_MENU.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO_2.SP_EVENTOS_MENU.fin: "+diferencia);

        
        OracleResultSet rs = (OracleResultSet) ocs.getObject(3);

       
        while (rs.next()) {
            
            System.out.println("eventos1: "+rs.getInt("C_MENU_ID"));
            
            for( Menu padre : listaMenu){
                for( Menu oMenu : padre.getoMenuList2()){
                    if (oMenu.getCmenuId() == rs.getInt("C_MENU_ID")){

                        MenuEventos eventos = new MenuEventos();                 
                        eventos.setCmenuId(rs.getInt("C_MENU_ID"));
                        eventos.setCmenu(rs.getInt("C_MENU"));
                        eventos.setMenuA(rs.getString("D_NOMBRE"));
                        eventos.setMenuAUrl(rs.getString("D_URL"));


                        oMenu.getListaEventos().add(eventos);
                        break;
                    }
                }
            }
        }
     
        
        cn.close();
        
        System.out.println("eventos2: fin");
        
    }        
    
    public Usuario solicitarCodigo(Usuario oUsuario) throws Exception {
        System.out.println("SessionCredencial.solicitarCodigo");
        boolean rpta = false;
        System.out.println("SessionCredencial.UsuarioId:"+oUsuario.getUsuarioId().trim());
        System.out.println(oUsuario);
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(true);
        
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_OLVIDOCLAVE.SP_GET_USUARIO(?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());
        ocs.registerOutParameter(2, OracleTypes.CURSOR);
        ocs.registerOutParameter(3, OracleTypes.VARCHAR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);

        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_OLVIDOCLAVE.SP_GET_USUARIO.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_OLVIDOCLAVE.SP_GET_USUARIO.fin:"+diferencia);

        ResultSet rset = (ResultSet)ocs.getObject(2);
        rset.next();
        String sEmail="";
        try{
            sEmail = rset.getString("D_EMAIL");
            rpta = true;
        }
        catch(Exception e)
        {
            rpta = false;
        }
              
        String res ="";
        if(rpta)
        {
            System.out.println("email:"+sEmail);
            String[] arrToEmail = new String[1]; 
            arrToEmail[0]=sEmail;

            //GENERAR CODIGO ALFANUMERICO
            String sRandom=getRandomString();
            System.out.println("sRandom:"+sRandom); 

            //REGISTRAR CODIGO
            String sp2 = "{call PKG_OLVIDOCLAVE.SP_INS_CODIGO(?,?,?,?)}";
            OracleCallableStatement ocs2 = (OracleCallableStatement) cn.prepareCall(sp2);
            ocs2.setString(1, sRandom.trim());
            System.out.println("random:"+sRandom.trim()); 
            ocs2.setString(2,  oUsuario.getUsuarioId().trim());
            System.out.println("Usuario:"+oUsuario.getUsuarioId().trim()); 
            ocs2.registerOutParameter(3, OracleTypes.VARCHAR);
            ocs2.registerOutParameter(4, OracleTypes.VARCHAR);
            ocs2.execute();
            res = ocs2.getString(3);        
            System.out.println("res:"+res); 
            String error=ocs2.getString(4);    
            if(error!=null)
            {
                System.out.println(error); 
            }
            if (res.equals("0"))
            {
                //ENVIAR CODIGO GENERADO AL CORREO DEL USUARIO
                sendFromGMail("servicios@popular-safi.com", "bh7NhW7nnrmWgs2V", arrToEmail, "EVA-CODIGO GENERADO-CAMBIO DE CONTRASEÑA", sRandom);
                System.out.println("sendFromGMail"); 
                rpta = true;
            }
            else
            {
                rpta = false;
            }            
        }
        newUsuario.setEstado(rpta);
       System.out.println("fin solicitarCodigo ");
        
        return newUsuario;
    }
    
    private String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 16) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) 
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");//OK
        props.put("mail.transport.protocol", "smtp");//OK
        props.put("mail.smtp.starttls.enable", "true");//OK
        props.put("mail.smtp.port", "587");//OK

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try 
        {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                    toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport.send(message,from, pass);
            //Transport transport = session.getTransport("smtp");
            //transport.connect("smtp.gmail.com", from, pass);
            //transport.sendMessage(message, message.getAllRecipients());
            //transport.close();
        }
        catch (AddressException ae) {
           System.out.println(ae);
        }
        catch (MessagingException me) {
                System.out.println(me);
        }
    }      
    
    public Usuario validarCodigo(Usuario oUsuario) throws Exception {
        
        System.out.println("validarCodigo");
        boolean rpta = false;
        System.out.println("validarCodigo.UsuarioId:"+oUsuario.getUsuarioId().trim());
        System.out.println("validarCodigo.codigo:"+oUsuario.getBcontrasenia().trim());
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(true);
        
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_OLVIDOCLAVE.SP_VALIDAR_CODIGO(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());
        ocs.setString(2, oUsuario.getBcontrasenia().trim());
        ocs.registerOutParameter(3, OracleTypes.CURSOR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_OLVIDOCLAVE.SP_VALIDAR_CODIGO.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_OLVIDOCLAVE.SP_VALIDAR_CODIGO.fin:"+diferencia);

        ResultSet rset = (ResultSet)ocs.getObject(3);
        rset.next();
        String sUsuario="";
        try{
            sUsuario = rset.getString("C_USUARIO_ID");
            System.out.println("OK:"+sUsuario);
            rpta = true;
        }
        catch(Exception e)
        {
            rpta = false;
        }
        newUsuario.setEstado(rpta);
       System.out.println("fin validarCodigo ");
        
        return newUsuario;
    }
    
    public Usuario resetearClave(Usuario oUsuario) throws Exception {
        System.out.println("ini resetearClave");
        boolean rpta = false;
        Usuario newUsuario = oUsuario;
        newUsuario.setEstado(false);
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_USUARIO.SP_UPD_USUARIO_CONTRA(?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        ocs.setString(1, oUsuario.getUsuarioId().trim());
        String contrasenia = oUsuario.getContraseniaB().trim();
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(contrasenia.getBytes());
        ocs.setString(2, Hex.toHexString(digest));
        ocs.registerOutParameter(3, OracleTypes.VARCHAR);
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);

        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO.SP_UPD_USUARIO_CONTRA.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO.SP_UPD_USUARIO_CONTRA.fin:"+diferencia);

        if (ocs.getString(3).equals("0")){
            System.out.println("resetearClave OK");
            rpta = true;
        }
        newUsuario.setEstado(rpta);
                
        System.out.println("fin resetearClave");
        
        return newUsuario;
    }    
    
    public boolean permisoControl(String  usuario, String idcontrol) throws Exception {
        System.out.println("     <i> permisoControl ");
        boolean rpta = false;

        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        String sp = "{call PKG_USUARIO.SP_PERMIS_CONTROL(?,?,?,?,?)}";
        OracleCallableStatement ocs = (OracleCallableStatement) cn.prepareCall(sp);
        System.out.println("usuario:"+usuario.trim());
        ocs.setString(1, usuario.trim());
        System.out.println("idcontrol:"+idcontrol.trim());
        ocs.setInt(2, Integer.parseInt(idcontrol));
        ocs.registerOutParameter(3, OracleTypes.CURSOR );
        ocs.registerOutParameter(4, OracleTypes.VARCHAR);
        ocs.registerOutParameter(5, OracleTypes.VARCHAR);
        
        java.util.Date dini = new java.util.Date();
        System.out.println("PKG_USUARIO.SP_PERMIS_CONTROL.ini");
        ocs.execute();
        java.util.Date dfin = new java.util.Date();
        long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
        System.out.println("PKG_USUARIO.SP_PERMIS_CONTROL.fin:"+diferencia);
        
        try{
            OracleResultSet rs = (OracleResultSet) ocs.getObject(3);
            rs.next();
            if(rs.getInt("E_EJECUCION")==1)
            {
                rpta=true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        cn.close();
        System.out.println("     <f> permisoControl:" + rpta);
        return rpta;
    }    
    

    
}
