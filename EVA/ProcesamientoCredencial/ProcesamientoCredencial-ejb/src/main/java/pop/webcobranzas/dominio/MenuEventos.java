/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dominio;

/**
 *
 * @author RC433838
 */
public class MenuEventos {
    
    private int cmenuId;
    private int cmenu;
    private String menuA;
    private String menuAUrl;
    
    private Menu menu;    

    /**
     * @return the cmenuId
     */
    public int getCmenuId() {
        return cmenuId;
    }

    /**
     * @param cmenuId the cmenuId to set
     */
    public void setCmenuId(int cmenuId) {
        this.cmenuId = cmenuId;
    }

    /**
     * @return the cmenu
     */
    public int getCmenu() {
        return cmenu;
    }

    /**
     * @param cmenu the cmenu to set
     */
    public void setCmenu(int cmenu) {
        this.cmenu = cmenu;
    }

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
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    
}
