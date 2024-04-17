/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.util;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Jyoverar
 */
public class ControlMethod {

    private static StringProperty restrict = new SimpleStringProperty();

    public String getRestrict() {
        return restrict.get();
    }

    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }

    public ControlMethod() {
    }

    public static ChangeListener<String> onlyNumeric(String restriccion, TextField texto) {
        restrict.set(restriccion);
        ChangeListener<String> retornar = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (restrict.get() != null && !restrict.get().equals("") && !newValue.matches(restrict.get() + "*")) {
                    texto.setText(oldValue);
                }
            }
        };
        return retornar;
    }

    public static ChangeListener<String> onlyNumeric(String restriccion, TextField texto, int length) {
        //restrict.set(restriccion);
        StringProperty restrictn = new SimpleStringProperty();
        restrictn.set(restriccion);
        ChangeListener<String> result = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //System.err.println("onlyNumeric-->" + newValue);
                if ((restrictn.get() != null && !restrictn.get().equals("") && !newValue.matches(restrictn.get() + "*")) || newValue.length() > length) {
                    texto.setText(oldValue);
                }
            }
        };
        return result;
    }

    public static ChangeListener<String> onlyNumeric(String restriccion, TextArea texto) {
        StringProperty restrictn = new SimpleStringProperty();
        restrictn.set(restriccion);
        ChangeListener<String> retornar = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (restrictn.get() != null && !restrictn.get().equals("") && !newValue.matches(restrictn.get() + "*")) {
                    texto.setText(oldValue);
                }
            }
        };
        return retornar;
    }

}
