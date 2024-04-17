/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;

/**
 *
 * @author Jyoverar
 */
public interface IMaeBancoDao {

    Integer insert(MaeBanco oMaeBanco);

    void update(MaeBanco oMaeBanco);

    void delete(MaeBanco oMaeBanco);

    ArrayList<MaeBanco> fetchAll(MaeBanco oMaeBanco);

    ArrayList<MaeBancoCuenta> fetchAllCuenta(MaeBancoCuenta oMaeBancoCuenta);
}
