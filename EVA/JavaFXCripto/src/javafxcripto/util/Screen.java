/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.util;

/**
 *
 * @author Jyoverar
 */
public class Screen {

    private String xmlWindow;
    private String title;
    private Boolean vVersion = true;

    public String getXmlWindow() {
        return xmlWindow;
    }

    public void setXmlWindow(String xmlWindow) {
        this.xmlWindow = xmlWindow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getvVersion() {
        return vVersion;
    }

    public void setvVersion(Boolean vVersion) {
        this.vVersion = vVersion;
    }

    public Screen(String xmlWindow, String title) {
        this.xmlWindow = xmlWindow;
        this.title = title;
    }
    
}
