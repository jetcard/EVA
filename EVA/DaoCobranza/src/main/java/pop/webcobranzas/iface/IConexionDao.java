/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

/**
 *
 * @author Jyoverar
 */
public interface IConexionDao {

    void ConexionOpen();

    void ConexionClose();

    void AddTransaction();

    void SaveChanges();

    void CancelChanges();
}
