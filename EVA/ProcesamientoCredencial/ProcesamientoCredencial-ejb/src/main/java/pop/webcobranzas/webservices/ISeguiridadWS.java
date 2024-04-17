/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Jyoverar
 */
@WebService
public interface ISeguiridadWS {

    @WebMethod
    public boolean authTest();
}
