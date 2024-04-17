/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Jyoverar
 */
public class demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        // TODO code application logic here
        URI server = new URI("ws://192.168.70.101:8989");
        SmsGatewayClient client = new SmsGatewayClient(server);

        client.connectBlocking();

        JsonObject object = new JsonObject();
//        JsonArray numeros = new JsonArray();
//        numeros.add("993962375");
        object.add("to", new JsonPrimitive("993962375"));
        object.add("message", new JsonPrimitive("Vamos con feeeeeeee "));

        Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println("json " + json);
        client.send(json);

        client.closeBlocking();
    }

}
