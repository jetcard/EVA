/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.iface.ITipoCambioServ;
import pop.webcobranzas.servicio.DepositoServ;
import pop.webcobranzas.servicio.TipoCambioServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class BancoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FileUploadBean fileUploadBean;
    // lista de depositos a mostrar
    private List<MaeDeposito> maeDepositoList = new ArrayList<>();
    // lista de depositos a grabar
    private List<MaeDeposito> maeDepositoListG = new ArrayList<>();
    // lista de tipo de cambio 
    private List<MaeTipoCambio> maeTipoCambioList = new ArrayList<>();
    //
    private MaeBanco maeBanco;
    private MaeBancoCuenta bancoCuenta;
    private MaeTipoCambio maeTipoCambio;
    private String moneda;
    private String tipoCuenta;
    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    private IDepositoServ depositoServ = new DepositoServ();
    private ITipoCambioServ tipoCambioServ = new TipoCambioServ();

    public BancoBean() {
        maeBanco = new MaeBanco();
        bancoCuenta = new MaeBancoCuenta();
        maeTipoCambio = new MaeTipoCambio();
    }

    public void cargarArchivo() {
        showMsg = false;
        maeDepositoList.clear();
        
        System.out.println("["+SessionUtils.getUserName()+"] "+" BancoBean - cargarArchivo");

        maeDepositoList = fileUploadBean.upload();
        try {
            
            maeDepositoList = depositoServ.listarDepositosBank(maeDepositoList);
            if (maeDepositoList.size() > 0) {
                maeBanco = maeDepositoList.get(0).getMaeBancoCuenta().getMaeBanco();
                bancoCuenta = maeDepositoList.get(0).getMaeBancoCuenta();
                moneda = fileUploadBean.getMoneda();
                tipoCuenta = fileUploadBean.getTipoCuenta();
                // tipo de cambio 
                maeTipoCambio.setfCambio(maeDepositoList.get(0).getFBcoDeposito());
                maeTipoCambioList = tipoCambioServ.listarTipoCambio(maeTipoCambio);
                if (maeTipoCambioList.size() > 0) {
                    maeTipoCambio = maeTipoCambioList.get(0);
                } else {
                    maeTipoCambio.setnTipoCambioCom(0.00);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BancoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void grabarDepositos() {
        showMsg = true;
        try {
            //System.out.println("pop.webcobranzas.bean.BancoBean.grabarDepositos() --> " + maeDepositoList.size());
            System.out.println("["+SessionUtils.getUserName()+"] "+" BancoBean - grabarDepositos");
            maeDepositoListG.clear();
            for (MaeDeposito maeDeposito : maeDepositoList) {
                if (maeDeposito.getBencontrado().equals("0")) {
                    maeDeposito.setcDocDepositoId("0002");
                    maeDeposito.setISaldo(maeDeposito.getIBcoDepositado());
                    maeDeposito.seteEstado("01");
                    maeDeposito.setcUsuarioAdd(SessionUtils.getUserName());
                    maeDepositoListG.add(maeDeposito);
                }
            }
            // grabar los depositos
            maeDepositoList = depositoServ.insertar(maeDepositoListG);
            // validar depositos
            maeDepositoList = depositoServ.listarDepositosBank(maeDepositoList);
            tipoMsj = "success";
            mensaje = "Depositos Cargados";
        } catch (Exception e) {
            tipoMsj = "error";
            mensaje = "Error al cargar Depositos";
        }
    }

    public FileUploadBean getFileUploadBean() {
        return fileUploadBean;
    }

    public void setFileUploadBean(FileUploadBean fileUploadBean) {
        this.fileUploadBean = fileUploadBean;
    }

    public List<MaeDeposito> getMaeDepositoList() {
        return maeDepositoList;
    }

    public void setMaeDepositoList(List<MaeDeposito> maeDepositoList) {
        this.maeDepositoList = maeDepositoList;
    }

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

    public List<MaeDeposito> getMaeDepositoListG() {
        return maeDepositoListG;
    }

    public void setMaeDepositoListG(List<MaeDeposito> maeDepositoListG) {
        this.maeDepositoListG = maeDepositoListG;
    }

    public MaeBanco getMaeBanco() {
        return maeBanco;
    }

    public void setMaeBanco(MaeBanco maeBanco) {
        this.maeBanco = maeBanco;
    }

    public MaeBancoCuenta getBancoCuenta() {
        return bancoCuenta;
    }

    public void setBancoCuenta(MaeBancoCuenta bancoCuenta) {
        this.bancoCuenta = bancoCuenta;
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

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }

    public MaeTipoCambio getMaeTipoCambio() {
        return maeTipoCambio;
    }

    public void setMaeTipoCambio(MaeTipoCambio maeTipoCambio) {
        this.maeTipoCambio = maeTipoCambio;
    }

    public ITipoCambioServ getTipoCambioServ() {
        return tipoCambioServ;
    }

    public void setTipoCambioServ(ITipoCambioServ tipoCambioServ) {
        this.tipoCambioServ = tipoCambioServ;
    }

    public List<MaeTipoCambio> getMaeTipoCambioList() {
        return maeTipoCambioList;
    }

    public void setMaeTipoCambioList(List<MaeTipoCambio> maeTipoCambioList) {
        this.maeTipoCambioList = maeTipoCambioList;
    }

}
