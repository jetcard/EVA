/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcripto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafxcripto.util.ControlMethod;
import javafxcripto.util.Regla;
import javafxcripto.util.WindowSea;

/**
 *
 * @author Jyoverar
 */
public class EncriptarController extends AppController implements Initializable {

    @FXML
    private Button btnStart;

    @FXML
    private TextField tfdCondA;
    @FXML
    private TextField tfdCondB;
    @FXML
    private TextField tfdCondC;
    @FXML
    private TextField tfdCondD;

    @FXML
    private TextField tfdCondAID;
    @FXML
    private TextField tfdCondBID;
    @FXML
    private TextField tfdCondCID;
    @FXML
    private TextField tfdCondDID;

    @FXML
    private TextField tfdCondNumA;
    @FXML
    private TextField tfdCondNumB;
    @FXML
    private TextField tfdCondNumC;
    @FXML
    private TextField tfdCondNumD;

    @FXML
    private TextField tfdCripA;

    @FXML
    private TextField tfdCripB;

    @FXML
    private TextField tfdCripC;

    @FXML
    private TextArea tarTexto;

    @FXML
    private TextArea tarTextoEncrip;

    Map<Integer, String> datosA = new HashMap<Integer, String>();
    Map<Integer, String> datosB = new HashMap<Integer, String>();
    Map<Integer, String> datosC = new HashMap<Integer, String>();

    // reglas
    List<Regla> reglas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfdCondAID.textProperty().addListener(ControlMethod.onlyNumeric("[\\+,\\-,-]", tfdCondAID, 1));
        tfdCondBID.textProperty().addListener(ControlMethod.onlyNumeric("[\\+,\\-,-]", tfdCondBID, 1));
        tfdCondCID.textProperty().addListener(ControlMethod.onlyNumeric("[\\+,\\-,-]", tfdCondCID, 1));
        tfdCondDID.textProperty().addListener(ControlMethod.onlyNumeric("[\\+,\\-,-]", tfdCondDID, 1));

        tfdCondNumA.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", tfdCondNumA, 2));
        tfdCondNumB.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", tfdCondNumB, 2));
        tfdCondNumC.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", tfdCondNumC, 2));
        tfdCondNumD.textProperty().addListener(ControlMethod.onlyNumeric("[0-9]", tfdCondNumD, 2));

        tfdCondA.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCondA, 1));
        tfdCondB.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCondB, 1));
        tfdCondC.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCondC, 1));
        tfdCondD.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCondD, 1));

        tfdCripA.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCripA, 26));
        tfdCripB.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCripB, 26));
        tfdCripC.textProperty().addListener(ControlMethod.onlyNumeric("[A-Z]", tfdCripC, 120));

        tarTexto.setWrapText(true);

//        tarTexto.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                System.out.println("addEventFilter -> " + event.getText());
//                char ar[] = event.getCharacter().toCharArray();
//                char ch = ar[event.getCharacter().toCharArray().length - 1];
//                if( !((event.getCharacter().codePointAt(0)>=65) && (event.getCharacter().codePointAt(0)<=90))){
//                    System.out.println("The char you entered is not a number");
//                    event.consume();
//                }
//            }
//        });
        tarTexto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("addEventFilter -> " + oldValue);
                if (!newValue.matches("[A-Z ]" + "*")) {
                    tarTexto.setText(oldValue);
                }
            }
        });

        tarTextoEncrip.setWrapText(true);
        tarTextoEncrip.setEditable(false);

    }

    @FXML
    private void start() throws IOException {

        if (tarTexto.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION, "Delete " + "Escribir el texto a encriptar", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        datosA.clear();
        datosB.clear();
        datosC.clear();
        //-- reglas
        reglas.clear();
        reglas = cargarReglas();
        //-----------------------------------------------------------------
        // abecedario
        String[] rowsA;
        System.out.println(" 1 .- obtener el abecedario  " + tfdCripA.getText());
        String fil = tfdCripA.getText();
        rowsA = fil.split("");
        int i = 1;
        for (String row : rowsA) {
            datosA.put(i, row);
            i++;
            System.out.println("       abecedario  -> " + row);
        }

        // regleta
        String[] rowsB;
        System.out.println(" 2 .- obtener la regleta  " + tfdCripB.getText());
        String filB = tfdCripB.getText();
        rowsB = filB.split("");
        i = 1;
        for (String row : rowsB) {
            datosB.put(i, row);
            i++;
            System.out.println("       regleta  -> " + row);
        }

        // regleta
        String[] rowsC;
        System.out.println(" 3 .- obtener la llave  " + tfdCripC.getText());
        String filC = tfdCripC.getText();
        rowsC = filC.split("");
        i = 1;
        for (String row : rowsC) {
            datosC.put(i, row);
            i++;
            System.out.println("       llave  -> " + row);
        }

        // texto
        Map<Integer, String> mapTexto = new HashMap<Integer, String>();
        String[] rowTexto;
        System.out.println(" 4 .- texto entrada  " + tarTexto.getText());
        String texto = tarTexto.getText();
        rowTexto = texto.split("");
        i = 1;
        for (String row : rowTexto) {
            mapTexto.put(i, row);
            i++;
            System.out.println("       texto  -> " + row);
        }
        System.out.println(" 5 .- Cantidad de datos");
        System.out.println("       texto A -> " + rowsA.length);
        System.out.println("       texto B -> " + rowsB.length);
        System.out.println("       texto C -> " + rowsC.length);

        int lgtA = rowsA.length;
        int contLgtA = 0;
        int lgtB = rowsB.length;
        int contLgtB = 0;
        int lgtC = rowsC.length; // palabra clave
        int contLgtC = 0;// logitud de la palabra clave
        System.out.println(" 6 .- Encriptando ... ");
        String textoEncrip = "";

        Regla reg = null;

        for (String letra : rowTexto) {
            System.out.println("   letra --> " + letra + " - ascii ->" + letra.codePointAt(0));
            if (letra.codePointAt(0) != 32) {// no es espacio
                // contando el nro de la palabra
                contLgtC++;// +1 a la palabra clave, comienza en cero
                if (contLgtC > lgtC) {
                    contLgtC = 1;
                }
                System.out.println("         contLgtC --> " + contLgtC + " - " + datosC.get(contLgtC));
                int valA = valorLlaveA(letra);// capturando el valor de la letra permitidas
                int valB = valorLlaveB(datosC.get(contLgtC)); // capturando el valor de las letras ordenadas
                int valPintar = 0;
                //
                if ((valB + (valA - 1)) > lgtB) {
                    valPintar = valB + (valA - 1) - lgtB;
                } else {
                    valPintar = valB + (valA - 1);
                }

                if (reg != null) {
                    System.out.println("         regla -------> " + reg.getCondicion() + " - " + reg.getLado() + " - " + reg.getCantidad());
                    if (reg.getLado() > 0) { // derecha
                        // para mover a la derecha se tiene que disminuir
                        if ((valPintar - reg.getCantidad()) > 0) {
                            valPintar = valPintar - reg.getCantidad();
                        } else {
                            valPintar = lgtB - (reg.getCantidad() - valPintar);
                        }

                    } else// izquierda
                    // para mover a la derecha se tiene que aumentar
                     if ((valPintar + (reg.getCantidad())) > lgtB) {
                            valPintar = valPintar + (reg.getCantidad()) - lgtB;
                        } else {
                            valPintar = valPintar + (reg.getCantidad());
                        }
                }

                System.out.println("         valPintar --> " + valPintar + " ->" + datosB.get(valPintar));

                reg = buscarRegla(letra);

                textoEncrip = textoEncrip + datosB.get(valPintar);
            }

        }
        System.out.println(" 6 .- Encriptado  ->  " + textoEncrip);

        System.out.println(" 7 .- pintando texto encriptado ... ");

        tarTextoEncrip.setText(textoEncrip);

    }

    private int valorLlaveA(String valor) {
        int punteroA = 0;
        for (Map.Entry<Integer, String> entry : datosA.entrySet()) {
            if (entry.getValue().equals(valor)) {
                punteroA = entry.getKey();
                break;
            }
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        return punteroA;
    }

    private int valorLlaveB(String valor) {
        int punteroB = 0;
        for (Map.Entry<Integer, String> entry : datosB.entrySet()) {
            if (entry.getValue().equals(valor)) {
                punteroB = entry.getKey();
                break;
            }
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        return punteroB;
    }

    private List<Regla> cargarReglas() {
        List<Regla> rtn = new ArrayList<>();
        // r01
        if (tfdCondA.getText() != null && tfdCondAID.getText() != null && tfdCondNumA.getText() != null
                && !tfdCondA.getText().isEmpty() && !tfdCondAID.getText().isEmpty() && !tfdCondNumA.getText().isEmpty()) {
            Regla r01 = new Regla();
            r01.setCondicion(tfdCondA.getText());
            if (tfdCondAID.getText().equals("+")) {
                r01.setLado(1);
            } else {
                r01.setLado(-1);
            }
            r01.setCantidad(Integer.parseInt(tfdCondNumA.getText()));
            rtn.add(r01);
        }
        // r02
        if (tfdCondB.getText() != null && tfdCondBID.getText() != null && tfdCondNumB.getText() != null
                && !tfdCondB.getText().isEmpty() && !tfdCondBID.getText().isEmpty() && !tfdCondNumB.getText().isEmpty()) {
            Regla r02 = new Regla();
            r02.setCondicion(tfdCondB.getText());
            if (tfdCondBID.getText().equals("+")) {
                r02.setLado(1);
            } else {
                r02.setLado(-1);
            }
            r02.setCantidad(Integer.parseInt(tfdCondNumB.getText()));
            rtn.add(r02);
        }
        // r03
        if (tfdCondC.getText() != null && tfdCondCID.getText() != null && tfdCondNumC.getText() != null
                && !tfdCondC.getText().isEmpty() && !tfdCondCID.getText().isEmpty() && !tfdCondNumC.getText().isEmpty()) {
            Regla r03 = new Regla();
            r03.setCondicion(tfdCondC.getText());
            if (tfdCondCID.getText().equals("+")) {
                r03.setLado(1);
            } else {
                r03.setLado(-1);
            }
            r03.setCantidad(Integer.parseInt(tfdCondNumC.getText()));
            rtn.add(r03);
        }
        // r04
        if (tfdCondD.getText() != null && tfdCondDID.getText() != null && tfdCondNumD.getText() != null
                && !tfdCondD.getText().isEmpty() && !tfdCondDID.getText().isEmpty() && !tfdCondNumD.getText().isEmpty()) {
            Regla r04 = new Regla();
            r04.setCondicion(tfdCondD.getText());
            if (tfdCondDID.getText().equals("+")) {
                r04.setLado(1);
            } else {
                r04.setLado(-1);
            }
            r04.setCantidad(Integer.parseInt(tfdCondNumD.getText()));
            rtn.add(r04);
        }

        return rtn;
    }

    private Regla buscarRegla(String letra) {
        Regla rg = null;

        for (Regla regla : reglas) {
            if (regla.getCondicion().equals(letra)) {
                System.out.println("              Regla  -> " + letra);
                rg = new Regla();
                rg.setCantidad(regla.getCantidad());
                rg.setCondicion(regla.getCondicion());
                rg.setLado(regla.getLado());
                break;
            }
        }
        return rg;
    }

    @FXML
    private void next() throws IOException {
        WindowSea.screenStep++;
        this.openWindows();
    }

}
