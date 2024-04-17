/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.msj;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import pop.comun.dominio.MaeTelefono;
import pop.properties.ManageProperties;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbEnviarMsjTexto")
public class SessionMensajeText implements IEnvioMensajeTexto {

    @Override
    public List<MaeTelefono> insertar(List<MaeTelefono> oMaeTelefonos) throws Exception {
        ManageProperties properties=new ManageProperties();
		
		List<MaeTelefono> lista = new ArrayList<>();
        URL url;
        String urlIp = "http://"+properties.getUrl_servidorweb();
        String texto;
        String rtpa;
        for (MaeTelefono maeTelefono : oMaeTelefonos) {
            texto = maeTelefono.getMsj().replace(" ", "+");
            System.out.println(urlIp + "/send.html?smsto=" + maeTelefono.getANumero() + "&smsbody=" + texto + "&smstype=sms");
            url = new URL(urlIp + "/send.html?smsto=" + maeTelefono.getANumero() + "&smsbody=" + texto + "&smstype=sms");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            rtpa = uc.getResponseMessage();
            System.out.println(maeTelefono.getANumero() + " " + texto + " " + rtpa);
            uc.disconnect();
            if (rtpa.trim().equals("ok")) {
                maeTelefono.setMsjEstado("1");
            } else {
                maeTelefono.setMsjEstado("0");
            }
            lista.add(maeTelefono);

        }

        return lista;
    }

}
