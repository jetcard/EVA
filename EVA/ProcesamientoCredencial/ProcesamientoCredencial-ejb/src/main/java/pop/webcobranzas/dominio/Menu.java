/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dominio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class Menu {

    private int cmenuId;
    private int cmenu;
    private int cmenuPadre;
    private String menuA;
    private String menuAUrl;
    private String menuAE;
    private String simbolo;
    private List<Menu> oMenuList = new ArrayList<Menu>();
    private List<Menu> oMenuList2 = new ArrayList<Menu>();
    private boolean btn1;
    private boolean btn2;
    private boolean btn3;
    private boolean btn4;
    private boolean btn5;
    private boolean btn6;
    private boolean btn7;
    private boolean btn8;
    private boolean btn9;
    private boolean btn10;
    
   public Menu(){
       this.listaEventos = new LinkedList<>();
   }    
    
    private List<MenuEventos> listaEventos = new ArrayList<MenuEventos>();
    

    public boolean isBtn1() {
        return btn1;
    }

    public void setBtn1(boolean btn1) {
        this.btn1 = btn1;
    }

    public boolean isBtn2() {
        return btn2;
    }

    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
    }

    public boolean isBtn3() {
        return btn3;
    }

    public void setBtn3(boolean btn3) {
        this.btn3 = btn3;
    }

    public boolean isBtn4() {
        return btn4;
    }

    public void setBtn4(boolean btn4) {
        this.btn4 = btn4;
    }

    public boolean isBtn5() {
        return btn5;
    }

    public void setBtn5(boolean btn5) {
        this.btn5 = btn5;
    }

    public boolean isBtn6() {
        return btn6;
    }

    public void setBtn6(boolean btn6) {
        this.btn6 = btn6;
    }

    public boolean isBtn7() {
        return btn7;
    }

    public void setBtn7(boolean btn7) {
        this.btn7 = btn7;
    }

    public boolean isBtn8() {
        return btn8;
    }

    public void setBtn8(boolean btn8) {
        this.btn8 = btn8;
    }

    public boolean isBtn9() {
        return btn9;
    }

    public void setBtn9(boolean btn9) {
        this.btn9 = btn9;
    }

    public boolean isBtn10() {
        return btn10;
    }

    public void setBtn10(boolean btn10) {
        this.btn10 = btn10;
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

    public String getMenuA() {
        return menuA;
    }

    public void setMenuA(String menuA) {
        this.menuA = menuA;
    }

    public String getMenuAUrl() {
        return menuAUrl;
    }

    public void setMenuAUrl(String menuAUrl) {
        this.menuAUrl = menuAUrl;
    }

    public String getMenuAE() {
        return menuAE;
    }

    public void setMenuAE(String menuAE) {
        this.menuAE = menuAE;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public List<Menu> getoMenuList() {
        return oMenuList;
    }

    public void setoMenuList(List<Menu> oMenuList) {
        this.oMenuList = oMenuList;
    }

    /**
     * @return the listaEventos
     */
    public List<MenuEventos> getListaEventos() {
        return listaEventos;
    }

    /**
     * @param listaEventos the listaEventos to set
     */
    public void setListaEventos(List<MenuEventos> listaEventos) {
        this.listaEventos = listaEventos;
    }

    /**
     * @return the oMenuList2
     */
    public List<Menu> getoMenuList2() {
        return oMenuList2;
    }

    /**
     * @param oMenuList2 the oMenuList2 to set
     */
    public void setoMenuList2(List<Menu> oMenuList2) {
        this.oMenuList2 = oMenuList2;
    }

}
