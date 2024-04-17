/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.archivo;

import javax.ejb.Remote;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.TabArchivo;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface IArcDocumento {

    byte[] prePlazo24H(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    byte[] preJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    byte[] preProtesto(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    byte[] preUltAviso(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    byte[] preNegExtJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    byte[] preNegPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    byte[] preInvNegociacion(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    TabArchivo genPlazo24H(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    TabArchivo genProtesto(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    TabArchivo genPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    TabArchivo genUltAviso(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    TabArchivo genNegExtJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    TabArchivo genNegPreJudicial(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;
    
    TabArchivo genInvNegociacion(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    byte[] imprimirArchivo(TabArchivo tabArchivo) throws Exception;

    TabArchivo guardarArchivo(MaeInversion maeInversion, TabArchivo archivo) throws Exception;

    byte[] preCN(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

    TabArchivo genCN(MaeInversion maeInversion, MaeReporte maeReporte) throws Exception;

}
