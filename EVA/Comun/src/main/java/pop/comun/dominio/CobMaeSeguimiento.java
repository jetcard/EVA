/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.ArrayList;


/**
 *
 * @author Jyoverar
 */
public class CobMaeSeguimiento extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cmaeSeguimientoId;
    private MaeInversion maeInversion = new MaeInversion();
    private String eestadoId;
    
    private ArrayList<CobSeguimiento> cobSeguimientoList;
    
    public CobMaeSeguimiento() {
    }

    public int getCmaeSeguimientoId() {
        return cmaeSeguimientoId;
    }

    public void setCmaeSeguimientoId(int cmaeSeguimientoId) {
        this.cmaeSeguimientoId = cmaeSeguimientoId;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public ArrayList<CobSeguimiento> getCobSeguimientoList() {
        return cobSeguimientoList;
    }

    public void setCobSeguimientoList(ArrayList<CobSeguimiento> cobSeguimientoList) {
        this.cobSeguimientoList = cobSeguimientoList;
    }
    
}
