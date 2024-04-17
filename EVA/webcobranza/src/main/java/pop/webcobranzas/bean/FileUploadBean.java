/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.TabArchivo;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.servicio.DepositoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class FileUploadBean {
    private Part file;
    private String text;
    private List<MaeDeposito> maeDepositoPreList = new ArrayList<>();
    private MaeBanco maeBanco;
    private MaeFondo maeFondo;
    private MaeBancoCuenta bancoCuenta;

    private String cuenta;
    private String moneda;
    private String tipoCuenta;

    private String fileName;
    private String fileType;
    private long fileSize;

    private List<Part> files;

    private IDepositoServ depositoServ = new DepositoServ();

    public FileUploadBean() {
        maeFondo = new MaeFondo();
        maeBanco = new MaeBanco();
        bancoCuenta = new MaeBancoCuenta();
        bancoCuenta.setMaeBanco(maeBanco);
        bancoCuenta.setMaeFondo(maeFondo);
    }

    public List<MaeDeposito> upload() {
        int contador = 0;
        maeDepositoPreList.clear();
        String ruta;
        String uuid;

        System.out.println("[" + SessionUtils.getUserName() + "] " + " FileUploadBean - upload");

        if (null != file) {
            try {
                System.out.println("[" + SessionUtils.getUserName() + "] " + " FileUploadBean - upload - " + file.getSubmittedFileName() + "  - " + file.getContentType() + " - " + file.getSize());

                // datos del archivo
                fileName = file.getSubmittedFileName();
                fileType = file.getContentType();
                fileSize = file.getSize();
                InputStream is = file.getInputStream();
                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                uuid = file.getSubmittedFileName() + "_" + UUID.randomUUID().toString();
                ruta = "C:\\webcobranzas\\files\\deposits\\" + localDate.getYear()
                        + "\\" + localDate.getMonthValue()
                        + "\\" + localDate.getDayOfMonth()
                        + "\\" + uuid;

                File fileO = new File(ruta);
                fileO.getParentFile().mkdirs();
                file.write(ruta);

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                // depende del banco
                if (fileName.contains("HIST_MOVEMENTS")) {
                    // bcp historico
                    while ((text = reader.readLine()) != null) {
                        text = text.replace("\"", "");
                        //System.out.println("texto plano --> " + text);
                        String[] items = text.split("\\|");
                        List<String> itemList = Arrays.asList(items);
                        //System.out.println("texto plano  detalle --> " + itemList.get(0));
                        contador++;
                        // cuenta
                        if (contador == 1) {
                            cuenta = itemList.get(1);
                            int x = cuenta.indexOf(" ", 0);
                            bancoCuenta.setDnroCuenta(cuenta.substring(0, x));
                        }
                        // moneda
                        if (contador == 2) {
                            moneda = itemList.get(1);
                            if (moneda.equals("Soles")) {
                                bancoCuenta.setCtipoMoneda("0001");
                            } else {
                                bancoCuenta.setCtipoMoneda("0002");
                            }
                        }
                        // tipo cuenta
                        if (contador == 3) {
                            tipoCuenta = itemList.get(1);
                            if (tipoCuenta.equals("Corriente")) {
                                bancoCuenta.setCtipoCuenta("0002");
                            }
                        }
                        if (contador >= 6) {
                            //System.out.println("texto plano  detalle --> " + itemList.size());
                            MaeDeposito deposito = new MaeDeposito();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                deposito.setFBcoDeposito(formatter.parse(itemList.get(0)));
                            } catch (ParseException ex) {
                                //Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex);
                            }
                            deposito.setDBcoDescripcion(itemList.get(2));
                            deposito.setIBcoDepositado(Double.parseDouble(itemList.get(3).replace(",", "")));
                            deposito.setIBcoSaldo(Double.parseDouble(itemList.get(4).replace(",", "")));
                            deposito.setdBcoSucursal(itemList.get(5));
                            deposito.setDBcoNoperacion(itemList.get(6));
                            deposito.setDbcoUsuario(itemList.get(8));
                            deposito.setDbcoUtc(itemList.get(9));
                            deposito.setMaeBancoCuenta(bancoCuenta);
                            //deposito.setDbcoReferenciaB(itemList.get(10));
                            maeDepositoPreList.add(deposito);
                        }
                    }
                } else if (fileName.contains("relacion_pago")) {
                    // bbva
                    String flg = "1";
                    while ((text = reader.readLine()) != null) {
                        text = text.replace("\"", "");
                        //System.out.println("texto plano --> " + text);
                        contador++;
                        if (contador == 8) {
                            // cuenta
                            cuenta = text.substring(78, 98);
                            bancoCuenta.setDnroCuenta(cuenta);
                            // moneda
                            moneda = text.substring(52, 55);
                            if (moneda.equals("PEN")) {
                                bancoCuenta.setCtipoMoneda("0001");
                            } else {
                                bancoCuenta.setCtipoMoneda("0002");
                            }
                        }

                        if (contador >= 12 && text.length() > 2 && flg.equals("1")) {
                            //System.out.println("texto plano  detalle --> " + text);
                            MaeDeposito deposito = new MaeDeposito();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                            try {
                                deposito.setFBcoDeposito(formatter.parse(text.substring(212, 220)));
                            } catch (ParseException ex) {
                                //Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex);
                            }
                            deposito.setDBcoDescripcion(text.substring(41, 49) + " " + text.substring(67, 107));
                            deposito.setIBcoDepositado(Double.parseDouble(text.substring(134, 160).trim().replace(",", "")));
                            //deposito.setIBcoSaldo(Double.parseDouble(itemList.get(4).replace(",", "")));
                            deposito.setdBcoSucursal(text.substring(160, 164));
                            deposito.setDBcoNoperacion(text.substring(186, 192));
                            deposito.setDbcoUsuario(text.substring(264, 266));
                            deposito.setDbcoUtc(text.substring(290, 292));
                            deposito.setMaeBancoCuenta(bancoCuenta);
                            //deposito.setDbcoReferenciaB(itemList.get(10));
                            maeDepositoPreList.add(deposito);
                        }
                        if (contador >= 12 && text.length() <= 2) {
                            flg = "0";
                        }
                    }
                } else if (fileName.contains("DAY_MOVEMENT")) {
                    // bcp diario
                    while ((text = reader.readLine()) != null ) {
                        text = text.replace("\"", "");
                        //System.out.println("texto plano --> " + text);
                        String[] items = text.split("\\|");
                        List<String> itemList = Arrays.asList(items);
                        System.out.println("texto plano  detalle --> " + itemList.get(0));
                        contador++;
                        // cuenta
                        if (contador == 1) {
                            cuenta = itemList.get(1);
                            int x = cuenta.indexOf(" ", 0);
                            bancoCuenta.setDnroCuenta(cuenta.substring(0, x));
                        }
                        // moneda
                        if (contador == 2) {
                            moneda = itemList.get(1);
                            if (moneda.equals("Soles")) {
                                bancoCuenta.setCtipoMoneda("0001");
                            } else {
                                bancoCuenta.setCtipoMoneda("0002");
                            }
                        }
                        // tipo cuenta
                        if (contador == 3) {
                            tipoCuenta = itemList.get(1);
                            if (tipoCuenta.equals("Corriente")) {
                                bancoCuenta.setCtipoCuenta("0002");
                            }
                        }
                        if (contador >= 9) {
                            System.out.println("texto plano  detalle --> " + itemList.size());
                            MaeDeposito deposito = new MaeDeposito();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                deposito.setFBcoDeposito(formatter.parse(itemList.get(0)));
                            } catch (ParseException ex) {
                                //Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(ex);
                            }
                            deposito.setDBcoDescripcion(itemList.get(2));
                            deposito.setIBcoDepositado(Double.parseDouble(itemList.get(3).replace(",", "")));
                            //deposito.setIBcoSaldo(Double.parseDouble(itemList.get(4).replace(",", "")));
                            deposito.setdBcoSucursal(itemList.get(4));
                            deposito.setDBcoNoperacion(itemList.get(5));
                            if (itemList.size()>=7) {
                                deposito.setDbcoUsuario(itemList.get(6));
                            }

                            //deposito.setDbcoUtc(itemList.get(9));
                            deposito.setMaeBancoCuenta(bancoCuenta);
                            //deposito.setDbcoReferenciaB(itemList.get(10));
                            maeDepositoPreList.add(deposito);
                        }
                        System.out.println("texto plano ffff --> " + text);
                    }
                }

                reader.close();
            } catch (IOException ex) {
            }
        }
        return maeDepositoPreList;
    }

    public List<TabArchivo> cargarArchivos() {

        List<TabArchivo> bs = new ArrayList<>();

        try {
            if (files != null) {
                for (Part ofile : files) {
                    TabArchivo archivo = new TabArchivo();
                    archivo.setDnombreArc(ofile.getSubmittedFileName());
                    System.out.println("    archivo --> " + ofile.getSubmittedFileName() + "  - " + ofile.getContentType() + " - " + ofile.getSize());
                    archivo.setDdatos(getBytes(ofile.getInputStream()));
                    bs.add(archivo);
                }
            }
        } catch (Exception e) {
        }
        return bs;
    }

    public byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1) {
                bos.write(buf, 0, len);
            }
            buf = bos.toByteArray();
        }
        return buf;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public MaeBancoCuenta getBancoCuenta() {
        return bancoCuenta;
    }

    public void setBancoCuenta(MaeBancoCuenta bancoCuenta) {
        this.bancoCuenta = bancoCuenta;
    }

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

    public List<MaeDeposito> getMaeDepositoPreList() {
        return maeDepositoPreList;
    }

    public void setMaeDepositoPreList(List<MaeDeposito> maeDepositoPreList) {
        this.maeDepositoPreList = maeDepositoPreList;
    }

    public MaeBanco getMaeBanco() {
        return maeBanco;
    }

    public void setMaeBanco(MaeBanco maeBanco) {
        this.maeBanco = maeBanco;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public List<Part> getFiles() {
        return files;
    }

    public void setFiles(List<Part> files) {
        this.files = files;
    }

}
