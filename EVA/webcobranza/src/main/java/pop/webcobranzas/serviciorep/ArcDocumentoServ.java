/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;

import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.TabArchivo;
import pop.webcobranzas.archivo.IArcDocumento;
import pop.webcobranzas.ifacerep.IArcDocumentoServ;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class ArcDocumentoServ implements IArcDocumentoServ {

    IArcDocumento iArcDocumentoEJB;

    @Override
    public byte[] prePlazo24H(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.prePlazo24H(maeInversion, maeReporte);
    }

    @Override
    public byte[] preJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preJudicial(maeInversion, maeReporte);
    }

    @Override
    public byte[] preProtesto(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preProtesto(maeInversion, maeReporte);
    }
    
    @Override
    public byte[] preInvNegociacion(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preInvNegociacion(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genPlazo24H(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genPlazo24H(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genProtesto(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genProtesto(maeInversion, maeReporte);
    }
    
    @Override
    public TabArchivo genPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genPreJudicial(maeInversion, maeReporte);
    }
    
    @Override
    public TabArchivo genInvNegociacion(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genInvNegociacion(maeInversion, maeReporte);
    }

    @Override
    public byte[] imprimirArchivo(TabArchivo tabArchivo) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.imprimirArchivo(tabArchivo);
    }

    @Override
    public TabArchivo guardarArchivo(MaeInversion maeInversion, TabArchivo archivo) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.guardarArchivo(maeInversion, archivo);
    }

    @Override
    public byte[] preCN(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preCN(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genCN(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genCN(maeInversion, maeReporte);
    }

    @Override
    public byte[] preUltAviso(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preUltAviso(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genUltAviso(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
       iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genUltAviso(maeInversion, maeReporte);
    }

    @Override
    public byte[] preNegExtJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preNegExtJudicial(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genNegExtJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genNegExtJudicial(maeInversion, maeReporte);
    }

    @Override
    public byte[] preNegPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.preNegPreJudicial(maeInversion, maeReporte);
    }

    @Override
    public TabArchivo genNegPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception {
        iArcDocumentoEJB = (IArcDocumento) Utilidades.getEJBRemoteRep("SessionArcDocumento", IArcDocumento.class.getName());
        return iArcDocumentoEJB.genNegPreJudicial(maeInversion, maeReporte);
    }

}
