/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jhon
 */
public class MaeMenu implements Serializable{
    
    static final long serialVersionUID = 1L;
    private int cmenuId;
    private int cmenu;
    private int cmenuPadre;
    private String menuA;
    private String menuAUrl;
    private String menuAE;
    private String simbolo;
    private List<MaeMenu> oMenuList = new ArrayList<MaeMenu>();
    

    /**
     * @return the menuA
     */
    public String getMenuA() {
        return menuA;
    }

    /**
     * @param menuA the menuA to set
     */
    public void setMenuA(String menuA) {
        this.menuA = menuA;
    }

    /**
     * @return the menuAUrl
     */
    public String getMenuAUrl() {
        return menuAUrl;
    }

    /**
     * @param menuAUrl the menuAUrl to set
     */
    public void setMenuAUrl(String menuAUrl) {
        this.menuAUrl = menuAUrl;
    }

    /**
     * @return the menuAE
     */
    public String getMenuAE() {
        return menuAE;
    }

    /**
     * @param menuAE the menuAE to set
     */
    public void setMenuAE(String menuAE) {
        this.menuAE = menuAE;
    }

    /**
     * @return the oMenuList
     */
    public List<MaeMenu> getoMenuList() {
        return oMenuList;
    }

    /**
     * @param oMenuList the oMenuList to set
     */
    public void setoMenuList(List<MaeMenu> oMenuList) {
        this.oMenuList = oMenuList;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getCmenuId() {
        return cmenuId;
    }

    public void setCmenuId(int cmenuId) {
        this.cmenuId = cmenuId;
    }

    public int getCmenu() {
        return cmenu;
    }

    public void setCmenu(int cmenu) {
        this.cmenu = cmenu;
    }

    public int getCmenuPadre() {
        return cmenuPadre;
    }

    public void setCmenuPadre(int cmenuPadre) {
        this.cmenuPadre = cmenuPadre;
    }
    
}
