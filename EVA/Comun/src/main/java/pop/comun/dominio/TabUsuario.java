/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class TabUsuario extends Base {

    private static final long serialVersionUID = 1L;
    private MaePersona maePersona;
    private String cusuarioId;
    private String dcontrasenia;
    private int nllamada;

    private List<TabRol> tabRols;
    private List<MaeMenu> maeMenus;

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public String getCusuarioId() {
        return cusuarioId;
    }

    public void setCusuarioId(String cusuarioId) {
        this.cusuarioId = cusuarioId;
    }

    public String getDcontrasenia() {
        return dcontrasenia;
    }

    public void setDcontrasenia(String dcontrasenia) {
        this.dcontrasenia = dcontrasenia;
    }

    public int getNllamada() {
        return nllamada;
    }

    public void setNllamada(int nllamada) {
        this.nllamada = nllamada;
    }

    public List<TabRol> getTabRols() {
        return tabRols;
    }

    public void setTabRols(List<TabRol> tabRols) {
        this.tabRols = tabRols;
    }

    public List<MaeMenu> getMaeMenus() {
        return maeMenus;
    }

    public void setMaeMenus(List<MaeMenu> maeMenus) {
        this.maeMenus = maeMenus;
    }

}
