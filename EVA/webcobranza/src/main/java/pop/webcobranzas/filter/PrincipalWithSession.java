/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.filter;

import java.security.Principal;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Jyoverar
 */
public class PrincipalWithSession implements Principal {

    private final HttpSession session;

    public PrincipalWithSession(HttpSession session) {
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    @Override
    public String getName() {
        return "JYOVERA"; // whatever is appropriate for your app, e.g., user ID
    }

}
