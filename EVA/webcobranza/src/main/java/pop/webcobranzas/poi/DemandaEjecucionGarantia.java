/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.poi;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import pop.comun.dominio.LegGeneraDemanda;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

/**
 *
 * @author PR154357
 */
public class DemandaEjecucionGarantia extends Comunes{

    private List<LegGeneraDemanda> listaGenerarDemanda;
    private XWPFDocument document;

    String cuaderno = "Cuaderno: Principal";
    String escrito = "Escrito: N° 01";
    String demanda = "DEMANDA EJECUCIÓN DE GARANTÍA";
    String dirigidoa = "SEÑOR JUEZ DEL JUZGADO ESPECIALIZADO EN LO COMERCIAL DE LIMA:";
    String datafondo = "";
    String[] iiia={"Nuestra pretensión se viabiliza en lo dispuesto por el artículo 720º y siguientes del Código Procesal Civil."};
    String[] iiib={"Sustentamos nuestra pretensión en lo dispuesto por los artículos 1097º, 1098°, 1099º y siguientes, así como el 1219º inciso 1) del Código civil; y en el artículo 240° y siguientes de la Ley 27287 “Ley de Títulos Valores”."};
    String[] iiic={"se dispuso la creación de la Subespecialidad Comercial dentro de la especialidad Civil, quienes podrán conocer las siguientes pretensiones, que a la letra dice:"};
    String[] iiid={"Los juzgados de la Subespecialidad Comercial conocen:"};
    String[] iiie={"Las pretensiones referidas a la Ley de Títulos Valores y en general las acciones cambiarias, causales y de enriquecimiento sin causa derivadas de títulos valores y los procesos ejecutivos y de ejecución de garantías (…)"};
    String ivtexto="De conformidad con lo dispuesto por los artículos 424º y 720º del Código Procesal Civil, ofrecemos como medios probatorios los siguientes:";
    String iv1="Documento que contiene la garantía:";
    String iv2="Escritura Pública de Constitución Unilateral de Gravamen Hipotecario de Primer Rango, con la finalidad de que se Emita un Título de Crédito Hipotecario Negociable.";
    String iv3="Estado de cuenta de saldo deudor:";
    String iv4="Carta notarial de requerimiento de pago:";
    String iv5="Certificado de gravamen:";
    String[] iv5a={"Constituyendo el bien a rematarse de naturaleza registral, se adjunta el certificado de gravamen del inmueble otorgado en garantía, expedido por el Registro de Propiedad Inmueble de Lima ","(ANEXO 1-K)."};
    String otrosidigo1="PRIMER OTROSÍ DECIMOS:";
    String otrosidigo1contenido=" Que, sin perjuicio de lo expuesto en el principal, hacemos presente a su Despacho los pagos que se hubieran efectuado con cargo a esta obligación, así como los que se realicen, tendrán que aplicarse conforme a lo señalado en lo prescrito en el artículo 1257º del Código Civil (aplicar el pago primero a los intereses, luego gastos y finalmente capital).";
    String otrosidigo2="SEGUNDO OTROSÍ DECIMOS:";
    String[] otrosidigo2contenido={" Conforme lo dispuesto en el Art. 80 del Código Procesal Civil, delego facultades de representación al que se refiere el Art. 74° del acotado cuerpo de leyes, a la ",
        "Dra. DENISSE ADRIANA INES VEGA FARRO",
        " con ",
        "REG. CAL N° 65958,",
        " al ",
        "Dra. VERA LUCIA PAPUICO MINO",//"Dr. MIGUEL ALBERTO ANTONIO MENDIETA SANCHEZ", //26 JUN 2024 Se reemplaza según Req
        " con ",//" con ", //26 JUN 2024 Se reemplaza según Req
        "REG. CAL N° 95940,",//"REG. CAL N° 75947,", //26 JUN 2024 Se reemplaza según Req
        " a la ",
        "Dra. YANET VICTORIO COLLAZOS",
        " con ",
        "REG. CAL N° 50944,",
        " a la ",
        "Dra. NEYLI MONDRAGON IZQUIERDO",
        " con ",
        "REG. CAL N° 84522,",
        " al ",
        "Dr. JHON RICARDO SALAZAR SALGUEDO",
        " con ",
        "REG. CAL N° 82036",
        " y al ",
        "Dr. HISAEL JHAZEEL HURTADO BAYONA",
        " con ",
        "REG. CAL N° 90674",
        " declarando estar instruida en la representación que otorgo y sus alcances, señalando como Domicilio Procesal en la Casilla N° 22756 ubicada en los Juzgados y Salas Civiles Sub Especialidad Comercial de Lima."};
    String otrosidigo3="TERCER OTROSÍ DECIMOS:";
    //String otrosidigo4="CUARTO OTROSÍ DECIMOS:";
    //String[] otrosidigo4contenido = {" Por convenir al derecho de la entidad recurrente autorizamos a través del presente escrito a la Srta. ","VERA LUCIA PAPUICO MINO",", identificado con DNI N°71029015, para que de conformidad con el artículo 138° Código Procesal Civil procedan a practicar desgloses, recabar anexos, títulos u otros documentos cuya devolución pudiera ordenarse, retirar exhortos, cédulas, oficios, copias certificadas, certificados de consignación, entre otros y en general realizar cualquiera otra gestión respecto de la cual fuere suficiente la presente."};
    //String otrosidigo5="QUINTO OTROSÍ DECIMOS:";
    String otrosidigo5="CUARTO OTROSÍ DECIMOS:";
    String[] otrosidigo5contenido = {" Que, se adjunta un juego adicional del escrito de demanda y anexos para que se ","notifique a los ocupantes del inmueble objeto de ejecución."};
    String otrosidigo51="POR TANTO:";
    String[] otrosidigo52={"A Usted Señor Juez",", solicitamos admitir la presente demanda conforme su naturaleza y consecuentemente ordenar a los DEMANDADOS el pago de la obligación, bajo apercibimiento de proceder al remate del inmueble hipotecado."};
    String[] anexo1={"1.   Copia de Registro Único de Contribuyentes de ", "EL FONDO. (ANEXO 1-A)."};
    String[] anexo2={"2.   Copia simple del DNI de los representantes. ", "(ANEXO 1-B)."};
    String[] anexo3={"3.   Copia Legalizada de la Vigencia de Poder de los Apoderados Judiciales", " (ANEXO 1-C)."};
    String[] anexo4={"4.   Copia Legalizada del Acta de Junta de fecha 07/09/2011.", " (ANEXO 1-D)."};
    String[] anexo5={"5.   Copia Legalizada del Poder Especial para liquidar operaciones", "(ANEXO 1-E)."};
    String[] anexo6={"6.   Original del Título de Crédito Hipotecario Negociable.", " (ANEXO 1-F)."};
    String[] anexo7={"7.   Testimonio de la Escritura Pública.", " (ANEXO 1-G)."};
    String[] anexo8={"8.   Cronograma de pagos.", " (ANEXO 1-H)."};
    String[] anexo9={"9.   Estado de cuenta de saldo deudor.", " (ANEXO 1-I)."};
    String[] anexo10={"10.   Carta Notarial.", "(ANEXO 1-J)."};
    String[] anexo11={"11.   Certificado de Gravamen.", "(ANEXO 1-K)."};
    String[] anexo12={"12.   CRI (Certificado Registral Inmobiliario).", " (ANEXO 1-L)."};
    String[] anexo13={"13.   Papeletas de Habilitación de los Abogados.", " (ANEXO 1-LL)."};
    String[] anexo14={"14.   Tasación actualizada del bien inmueble", " (ANEXO 1-M)."};
    String[] anexo15={"15.   Tasa Judicial por Ofrecimiento de pruebas.", " (ANEXO 1-N)."};
    String[] anexo16={"16.   Aranceles por Derecho de Notificación judicial.", " (ANEXO 1-Ñ)."};   
   
    public XWPFDocument generateWordXWPFDocument(List<LegGeneraDemanda> _listaGenerarDemanda) {
        this.document= new XWPFDocument();
        String FONDO_DESCRIPCION="";
        String FONDO_RUC="";
        String DATAINVERSION="";
        String DATAMESES="";
        String DATACUOTASMENSUALES="";
        String DATADIRECCION="";
        String DATANOMBRESAPELLIDOS="";
        String DATAFEMISIONYVENCIMIENTO="";
        String DATANCUOTA="";
        String F_ESCRITURA="";
        String F_DE_FECHA="";
        String F_EMITIDO="";
        String FTCHN="";
        String STCHN="";        
        String ASIENTO1="";
        String ASIENTO2="";
        String PARTIDA="";
        String F_CN="";
        String PAGO="";
        String FECHA_CORTE="";//28/08/2019
        String VALOR_DOLARES="";
        String FECHA_ACTA="";
        String FECHA_DEMANDA="";
        String MONTO_A_DEMANDAR="";
        String TCHN="";
        String FONDO_PIE="";
        this.listaGenerarDemanda = _listaGenerarDemanda;
        for(LegGeneraDemanda legGeneraDemanda:listaGenerarDemanda){
            FONDO_DESCRIPCION = legGeneraDemanda.getFondoDescrip();
            switch (legGeneraDemanda.getFondoId()) {
                case "0001":
                    FONDO_RUC = "20544837835";
                    System.out.println("FONDO CAPITAL EMPRENDEDOR - FONDO DE INVERSION. RUC 20544837835.");
                    FONDO_PIE = "FCE";
                    break;
                case "0002":
                    FONDO_RUC = "20516816733";
                    FONDO_PIE = "FP1RM";
                    System.out.println("FONDO POPULAR 1 - RENTA MIXTA, FONDO DE INVERSION PRIVADO. RUC 20516816733.");
                    break;
                case "0003":
                    FONDO_RUC = "20519075581";
                    FONDO_PIE = "FM";
                    System.out.println("FONDO MYPE - TCHN, FONDO DE INVERSION PRIVADO. RUC 20519075581.");
                    break;                    
                default:
                    System.out.println("Opción por defecto");
            }
            DATAINVERSION = legGeneraDemanda.getmValNomTitulo();
            DATAMESES = legGeneraDemanda.getnPlazoMeses();
            DATACUOTASMENSUALES = legGeneraDemanda.getmCuota();
            DATADIRECCION = legGeneraDemanda.getDireccion();
            DATANOMBRESAPELLIDOS = legGeneraDemanda.getNombresDNI();
            DATAFEMISIONYVENCIMIENTO = legGeneraDemanda.getCuotasEmisionYVencimiento();
            DATANCUOTA = legGeneraDemanda.getnCuota();           
            F_ESCRITURA=legGeneraDemanda.getfEscritura();
            F_DE_FECHA=legGeneraDemanda.getDeFecha();
            F_EMITIDO=legGeneraDemanda.getEmitidoEl();
            FTCHN=legGeneraDemanda.getfTchn();
            STCHN=legGeneraDemanda.getsTchn();
            FECHA_ACTA="07/09/2011";
            ASIENTO1=legGeneraDemanda.getsAshipo();
            ASIENTO2=legGeneraDemanda.getsAsexptchn();
            PARTIDA=legGeneraDemanda.getPartida();
            PAGO=legGeneraDemanda.getPago();
            FECHA_CORTE=legGeneraDemanda.getmCorte();
            VALOR_DOLARES=legGeneraDemanda.getmValorDolRealiza();
            F_CN=legGeneraDemanda.getfCartasNotariales();
            FECHA_DEMANDA = legGeneraDemanda.getFechaDemanda();
            MONTO_A_DEMANDAR=legGeneraDemanda.getMontoADemandarLargo();
            TCHN=legGeneraDemanda.getTchn();
        }
        
        String[] textoPopular = {
            FONDO_DESCRIPCION,//texto[0]
            ", ",//texto[1]
            " con RUC N° ",//texto[2]
            FONDO_RUC,//texto[3]
            " (en adelante ",//texto[4]
            "EL FONDO",//texto[5]
            "), administrado por ",//texto[6]
            "POPULAR S.A. SOCIEDAD ADMINISTRADORA DE FONDOS DE INVERSIÓN, ",//texto[7]
            "con Registro Único de Contribuyentes N.º 20512726411, debidamente representado por sus Apoderados Judiciales los Sres.  ",//texto[8]
            "DENISSE ADRIANA INÉS VEGA FARRO, ",//texto[9]
            " identificada con DNI N.º 45443332, ",//texto[10]
            ///"MIGUEL ALBERTO ANTONIO MENDIETA SANCHEZ",//26 JUN 2024 Se elinina según Req //texto[11]
            ///" con DNI N° 72394662, ",//26 JUN 2024 Se elimina según Req ////texto[12]
            "YANET VICTORIO COLLAZOS",//texto[13]
            " con DNI N° 42480673, ",//texto[14]
            "NEYLI MONDRAGON IZQUIERDO",//texto[15]
            " identificada con DNI N° 71079919, ",//texto[16]
            "HISAEL JHAZEEL HURTADO BAYONA",//texto[17]
            " con DNI N° 44678313 y ",//texto[18]
            "JHON RICARDO SALAZAR SALGUEDO ",//texto[19]
            "con DNI N° 47352594 según poder que adjuntamos; todos ellos con domicilio real en Av. Nicolás de Piérola N.º 938 - Oficina N.º 302, Distrito, Provincia y Departamento de Lima, con domicilio procesal en la Casilla N° ",
            "22756",//texto[21]
            " ubicada en los Juzgados y Salas Civiles Sub Especialidad Comercial de Lima y con Casilla Electrónica N° ",//texto[22]
            "76101",//texto[23]
            ", con correo electrónico ",//texto[24]
            "oper.legal.psafi@gmail.com",//texto[25]
            " y celular N° 960894127 a Ud. respetuosamente decimos a Ud. respetuosamente decimos:"//texto[26]
        };

        String[] viaProcedimental={"POPULAR SAFI",
            " en representación de ",
            "EL FONDO",
            " interpone ",
            "DEMANDA DE EJECUCIÓN DE LA GARANTIA HIPOTECARIA",
            " en los seguidos con ",
            DATANOMBRESAPELLIDOS,
            ", a quien se le deberá notificar en ",
            DATADIRECCION,
            ", para que satisfaga a favor de EL FONDO las siguientes pretensiones:"};

        String i  = "I.     VIA PROCEDIMENTAL, RELACION JURIDICA PROCESAL Y PETITORIO. -";
        String ii = "II.    FUNDAMENTOS DE HECHO. -";
        String iii= "III.   FUNDAMENTOS DE DERECHO. -";
        String iv = "IV.    MEDIOS PROBATORIOS. -";
        String anexos="ANEXOS DE LA DEMANDA. - ";

        String[] itextoa={"a) El pago de ",
            MONTO_A_DEMANDAR,
            ", por concepto de ",
            FECHA_DEMANDA,
            ", proveniente de la Escritura Pública de Constitución Unilateral de Gravamen Hipotecario de Primer Rango con la finalidad de que se emita un "
            + "Título de Crédito Hipotecario Negociable (en adelante TCHN) y el Otorgamiento de Poder Especial e Irrevocable, escritura de fecha "
            +F_ESCRITURA
            +" (en adelante LA ESCRITURA); bajo apercibimiento de rematar la garantía real contenida en el referido título valor (TCHN), "
            + "cuyo detalle consta en el punto 2 de los fundamentos de hecho."};
        String[] itextob={"b) El monto correspondiente a la liquidación de ",
            "intereses moratorios y compensatorios,",
            " comisiones y gastos de cobranza, devengados y los que se devenguen con posterioridad hasta la total cancelación de la deuda puesta a cobro, así como los"
            + " costos y costas"," del presente proceso."};
        String[] itextoc={"c) La presente demanda se tramita a través de la ",
            "VIA PROCEDIMENTAL",
            " del ",
            "PROCESO ÚNICO DE EJECUCIÓN."};


        String[] iitextoa={"1. El(La) demandado(a) solicitó un crédito de ",DATAINVERSION," que se comprometió a pagar mediante "+DATAMESES+" cuotas mensuales de "+DATACUOTASMENSUALES+" venciendo "+DATAFEMISIONYVENCIMIENTO+", tal y como consta de los literales A) y B) de la cláusula adicional segunda."};
        String[] iitextob={"2.  Con la finalidad de garantizar el crédito, el(la) demandado(a) solicitó a los Registros Públicos la emisión de un Título de Crédito Hipotecario Negociable, expedido con el Número "+F_DE_FECHA+", otorgando los demandados poder especial a favor de PRESTACLUB para recibir y endosar el TCHN (cláusula adicional primera de LA ESCRITURA), cuidando los requisitos y requerimientos establecidos por la Ley y las indicaciones estipuladas en la referida cláusula adicional primera y su aclaración inserta al final de LA ESCRITURA. Mediante este acto ",
                           "se constituyó Primera y Exclusiva Hipoteca a favor de la recurrente",
                           " sobre el inmueble de propiedad de la demandada conformado por el Inmueble ubicado en: ",
                           DATADIRECCION,
                           " del Registro de Propiedad Inmueble – Zona Registral IX. (en adelante EL INMUEBLE). A la fecha el TCHN se encuentra ",
                           "debidamente endosado a favor de EL FONDO, titular legítimo del crédito y de la garantía hipotecaria."};
        String[] iitextob2={"La Constitución Unilateral de Gravamen se encuentra inscrita en el Asiento No. "+ASIENTO1+" y la Emisión del Título de Crédito Hipotecario Negociable correspondiente a la Hipoteca antes referida corre inscrita en el Asiento No. "+ASIENTO2+" de la Partida "+PARTIDA+" del Registro de la Propiedad Inmueble de la Zona Registral No. IX - Sede Lima."};
        String[] iitextob3 = {"3. Señor Juez, el Título de Crédito Hipotecario Negociable (TCHN), es un Título Valor que facilita la obtención de financiamiento mediante la garantía hipotecaria que representa, y como tal debe cumplir con ciertos requisitos generales y esenciales. En ese sentido, el Dr. Ricardo Beaumont Callirgos, en sus “Comentarios a la Nueva Ley de Títulos Valores” manifiesta:"};

        String iitextocnota1="“(...), estamos ante un título valor causal y no abstracto. Inicialmente es sólo un título valor que representa el derecho real de garantía hipotecaria, constituida por el propietario de bien gravado, a cuya orden es emitido. Posteriormente, podrá representar en modo adicional también el crédito que ha sido garantizado con dicha hipoteca representada por el título. Como es fácil observar se trata de un valor muy similar al warrant, el que igualmente al inicio solo representa la prenda. Sólo con su endoso dicha garantía se vinculará a un crédito; crédito que en el caso del Título de Crédito Hipotecario Negociable –TCHN – será también y siempre representado por este mismo título valor, en modo tal que solo a partir de entonces (primer endoso), representa dos derechos: el crédito que origina su primer endoso, más la hipoteca que garantiza dicho crédito.";
        String iitextocnota2="La emisión de este título valor está a cargo del Registro Público en el que el bien hipotecado está inscrito, lo que ocurrirá solo a petición expresa de su propietario, manifestando formalmente y mediante el otorgamiento de una escritura pública para ese efecto; ello en aplicación del principio rogatorio que es propio del derecho registral. Es decir, su creación y emisión constituye un acto solemne y formal, al exigirse siempre la escritura pública para su emisión.” (“Comentarios a la Nueva Ley de Títulos Valores”, págs.: 254 y 255).";


        String[] iitextob4 = {"4. El artículo 241 de la Ley de Títulos valores establece como contenido del Título de Crédito Hipotecario Negociable los siguientes:"};
        String iitextoda="a. La denominación de Título de Crédito Hipotecario Negociable y el número que le corresponde;";
        String iitextodb="b. El lugar y fecha de su emisión;";
        String iitextodc="c. El nombre y número de documento oficial de identidad del propietario que constituye el gravamen hipotecario, a cuya orden se expide el título;";
        String iitextodd="d. La descripción resumida del bien afectado con el gravamen hipotecario, según aparece de la inscripción registral;";
        String iitextode="e. El monto de la valorización que será el importe hasta por el cual se constituye el gravamen hipotecario, con indicación del nombre del perito y de su registro o colegiatura respectiva;";
        String iitextodf="f. La fecha de la escritura pública, nombre del Notario y demás datos de la inscripción registral de la hipoteca;";
        String iitextodg="g. El nombre y firma del registrador con indicación de la oficina registral correspondiente.";
        String iitextod2="Como se puede apreciar, el título valor (TCHN) que se adjunta a la presente demanda cumple con todos los requisitos establecidos en el artículo antes mencionado, por lo tanto no puede cuestionarse la validez del mismo.";


        String[] iitextob5 = {"5. Lo cierto es, señor Juez, que, ","llegadas las fechas de vencimiento de las cuotas mensuales, los demandados sólo cumplieron con el pago de "+PAGO+" de las cuotas establecidas"," por lo que se le curso Carta Notarial de fecha "+F_CN+", debidamente diligenciada, mediante la cual se requiere el pago de la deuda y se comunica que, de no cumplir con el pago de lo adeudado, se procederá a la ejecución judicial del bien otorgado en garantía."};
        String[] iitextob6 = {"6. Para ejecutar la garantía contenida en el TCHN por medio de la vía judicial, el artículo 243° de la Ley de Títulos Valores establece:"};

        String iitextof1="Artículo 243°.- Ejecución.";
        String iitextof2="Articulo 243.1.";
        String iitextof3="“(...) Sin embargo, el tenedor podrá optar por solicitar la ejecución judicial de la hipoteca, conforme al Código Procesal Civil”.";

        String iitextof4="Al respecto, el Dr. Ricardo Beumont Callirgos, en su libro: “Comentarios a la Ley de Títulos Valores” comenta, respecto a este artículo, lo siguiente.";
        String iitextof5="“No obstante que el tenedor del TCHN tiene facultad de disponer y exigir la venta del bien hipotecado en forma directa sin intervención alguna de autoridad judicial, tiene la potestad de hacerlo por la vía judicial, a través de un proceso de ejecución de garantías que prevé el CPC en su art. 720 y siguientes. (...)” (Op. Cit. Págs.: 666)";
        String iitextof6="En ese sentido el artículo 720º del Código Procesal Civil, establece para el proceso de ejecución de garantías lo siguiente:";

        String iitextof7="Art. 720.- Procedencia. - ";
        String iitextof8="“Procede la Ejecución de garantías reales, siempre que su constitución cumpla con las formalidades que la ley prescribe y la obligación garantizada se encuentre contenida en el mismo documento o en cualquier otro título ejecutivo.";
        String iitextof9="El ejecutante anexará a su demanda el documento que contiene la garantía y el estado de cuenta de saldo deudor.";
        String iitextof10="Si el bien fuere inmueble, debe presentarse documento que contenga tasación comercial actualizada realizada por dos ingenieros y/o arquitectos colegiados, según corresponda, con sus firmas legalizadas”";

        String iitextof11="Teniendo estos tres artículos concordados, se tiene lo siguiente:";
        String iitextof12="- El Título de Crédito Hipotecario Negociable, cumple con todos los requisitos establecidos por el artículo 240° de la Ley 27287 “Ley de Títulos Valores”";
        String iitextof13="- La garantía contenida en el TCHN se debe ejecutar en la vía procedimental establecida en el artículo 243° de la “Ley de Títulos Valores”, es decir, por medio del Proceso de Ejecución de Garantías.";
        String iitextof14="- Estando a lo dispuesto por el artículo 720° del Código Procesal Civil, la demanda cumple con todos los requisitos de admisibilidad y procedencia, ya que la deuda está contenida en un Estado de Cuenta de Saldo Deudor y el documento que contiene la garantía que se pretende ejecutar es el Título de Crédito Hipotecario Negociable.";


        String[] iitextob7 = {"7. Ponemos en conocimiento de vuestro Despacho que la hipoteca contenida en el TCHN queda perfeccionada con el primer endoso, siendo la recurrente la actual tenedora del título valor, es decir, el inmueble ha sido afectado por el propietario, asegurando el cumplimiento de una obligación determinada o determinable y que el gravamen cumple con todos los requisitos establecidos en el artículo 1099° del Código Civil."};
        String[] iitextob8 = {"8. De conformidad con el Artículo 720° del Código Procesal Civil y siendo que han transcurrido más de dos años desde la constitución de la garantía hipotecaria de fecha "
                +FTCHN
                //FECHA_CORTE
                +", en donde se valoriza convencionalmente el inmueble dado en garantía, cumplimos con presentar documento que contiene la tasación comercial actualizada del inmueble otorgado en garantía, el cual ha sido valorizado en la suma de US$ "+VALOR_DOLARES+" valor resultante de la tasación realizada por Perito Valuador debidamente inscrito."};
        String[] iitextob9 = {"9. En virtud de lo expuesto, queda establecido que la deuda puesta a cobro en el presente proceso no ha sido cancelada a la fecha, por lo que se recurre a vuestro Despacho para solicitar la ejecución de la hipoteca."};
        String[] iv1a = {"Cumplimos con adjuntar el Original del Título de Crédito Hipotecario Negociable N.º "+F_EMITIDO+" ","(ANEXO 1-F)."};
        //String[] iv1a = {"Cumplimos con adjuntar el Original del Título de Crédito Hipotecario Negociable N.º "+STCHN+" emitido el "+F_ESCRITURA+" ","(ANEXO 1-F)."};
        String[] iv2a = {"Cumplimos con adjuntar el Testimonio de la Escritura Pública de fecha "
                //+FECHA_CORTE
                +F_ESCRITURA
                +" en la cual consta la solicitud de la demandada (propietaria del inmueble a ejecutar), dirigida al registro de la Propiedad Inmueble de la Zona Registral No. IX - Sede Lima a efectos de que esta emita un Título de Crédito Hipotecario Negociable, para lo cual se constituye un gravamen hipotecario de primer y exclusivo rango ","(ANEXO 1-G)."};
        String[] iv3a = {"Anexamos el estado de cuenta de saldo deudor al "+FECHA_DEMANDA+" ","(ANEXO 1-I)."};
        String[] iv4a = {"Carta Notarial de requerimiento de pago de fecha "+F_CN+", dirigida a los demandados ","(ANEXO 1-J)."};
        String[] otrosidigo3contenido={" Que, de conformidad con la Ley de Mercado de Valores D. Leg. 861 y la Ley de Fondos de Inversión y sus Sociedades Administradoras D. Leg. 862, el denominado ",
            FONDO_DESCRIPCION,", se encuentra administrado por ","POPULAR S.A. SOCIEDAD ADMINISTRADORA DE FONDOS DE INVERSION - POPULAR SAFI,",
            " en consecuencia, la representación corresponde a esta última quien tiene personería jurídica debidamente inscrita en los Registros Públicos."};
        String otrosidigo32="Para acreditar lo expuesto, cumplo con adjuntar lo siguiente:";
        String[] otrosidigo33={"-   Copia Legalizada del Acta de fecha "+FECHA_ACTA+" mediante el cual POPULAR S.A. SOCIEDAD ADMINISTRADORA DE FONDOS DE INVERSIÓN crea ",
                               FONDO_DESCRIPCION,", conforme a ley."
                               };
        String[] otrosidigo34={"-   Copia Legalizada de la Vigencia de Poder de POPULAR S.A. SOCIEDAD ADMINISTRADORA DE FONDOS DE INVERSIÓN, donde mediante Sesión de Directorio, se acordó otorgar poderes a "
                + "DENISSE ADRIANA INÉS VEGA FARRO, "
                //+ "MIGUEL ALBERTO ANTONIO MENDIETA SANCHEZ, "//26 JUN 2024 Se elimina según Req
                + "YANET VICTORIO COLLAZOS, "
                + "NEYLI MONDRAGON IZQUIERDO, "
                + "HISAEL JHAZEEL HURTADO BAYONA Y "
                + "JHON RICARDO SALAZAR SALGUEDO a fin de representar a POPULAR SAFI y/o los fondos que administra. ("+FONDO_DESCRIPCION+"). "};
        
        LocalDateTime ldt = null;
        DateTimeFormatter esDateFormatLargo =  null;
        try{
            DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ld = LocalDate.parse((FECHA_DEMANDA).substring(FECHA_DEMANDA.length()-10, FECHA_DEMANDA.length()), DATEFORMATTER);
            ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
            esDateFormatLargo = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy").withLocale(new Locale("es", "ES"));
        }catch(NullPointerException npe){
        }
        String ciudadFecha="Lima, " + ldt.format(esDateFormatLargo).toString()+".";        
        try {

            // Establecer los márgenes de la página
            CTPageMar pageMar = document.getDocument().getBody().addNewSectPr().addNewPgMar();
            pageMar.setTop(BigInteger.valueOf((long) (0.98 * 1440)));
            pageMar.setBottom(BigInteger.valueOf((long) (0.98 * 1440)));
            pageMar.setLeft(BigInteger.valueOf((long) (1.18 * 1440)));
            pageMar.setRight(BigInteger.valueOf((long) (1.18 * 1440)));

            XWPFStyles styles = document.createStyles();
            CTFonts fonts = CTFonts.Factory.newInstance();
            fonts.setAscii("Arial");
            fonts.setHAnsi("Arial");
            styles.setDefaultFonts(fonts);
           
            XWPFParagraph sumillaParagraph = document.createParagraph();
            sumillaParagraph.setAlignment(ParagraphAlignment.LEFT);

            // Configurar interlineado de 1.5 líneas para la sumilla
            sumillaParagraph.setSpacingBeforeLines(430);

            // Establecer la posición inicial a 3.5 pulgadas (en unidades TWIP)
            int indentationFirstLine = (int) (3.5 * 1440);
            sumillaParagraph.setIndentFromLeft(indentationFirstLine);

            // Agregar texto a la sumilla con formato en negrita y saltos de línea
            XWPFRun sumillaRun = sumillaParagraph.createRun();
            sumillaRun.setBold(true);
            sumillaRun.setText(cuaderno);
            sumillaRun.addBreak();
            sumillaRun.setText(escrito);
            sumillaRun.addBreak();
            sumillaRun.setText(demanda);

            XWPFParagraph fourthLineParagraph = document.createParagraph();
            fourthLineParagraph.setAlignment(ParagraphAlignment.LEFT);

            fourthLineParagraph.setSpacingBeforeLines(75);

            XWPFRun fourthLineRun = fourthLineParagraph.createRun();
            fourthLineRun.setBold(true);
            fourthLineRun.setText(dirigidoa);
            fourthLineRun.setFontSize(10);
            fourthLineParagraph.setSpacingAfterLines(120);

            //fifthLineParagraph(document, texto);
            lineParagraphCapitalNuevo(document, textoPopular);
            titulo(document,i);
            agregarParrafoLetra(document, viaProcedimental);
            agregarParrafoLetraFrancesa(document, itextoa);
            agregarParrafoLetraFrancesa(document, itextob);
            agregarParrafoLetraFrancesa3(document, itextoc);


            titulo(document,ii);
            agregarParrafoLetraFrancesa(document, iitextoa);
            agregarSubparrafoCombinado(document, iitextob);
            agregarParrafo(document, iitextob2);            
            agregarParrafoLetraFrancesa2(document, iitextob3);
            //agregarEspacioEnBlanco(document);
            agregarParrafoEspecial(document, iitextocnota1);
            agregarEspacioEnBlanco(document);
            agregarParrafoEspecial(document, iitextocnota2);
            agregarEspacioEnBlanco(document);
           
            agregarParrafoLetraFrancesa(document, iitextob4);
           
            parrafoVignetasLetras(document, iitextoda);
            parrafoVignetasLetras(document, iitextodb);
            parrafoVignetasLetras(document, iitextodc);
            parrafoVignetasLetras(document, iitextodd);
            parrafoVignetasLetras(document, iitextode);
            parrafoVignetasLetras(document, iitextodf);
            parrafoVignetasLetras(document, iitextodg);
               
            agregarParrafo2(document, iitextod2);
            agregarParrafoLetraFrancesa(document, iitextob5);
            agregarParrafoLetraFrancesa2(document, iitextob6);
           
            //agregarEspacioEnBlanco(document);
            agregarParrafoEspecial(document, iitextof1);            
            agregarParrafoEspecial(document, iitextof2);
            agregarParrafoEspecial(document, iitextof3);
            agregarEspacioEnBlanco(document);
            agregarParrafo3(document, iitextof4);
            //agregarEspacioEnBlanco(document);
            agregarParrafoEspecial(document, iitextof5);
            agregarEspacioEnBlanco(document);
            agregarParrafo3(document, iitextof6);
            //agregarEspacioEnBlanco(document);
            agregarParrafoEspecial(document, iitextof7);
            agregarParrafoEspecial(document, iitextof8);
            agregarParrafoEspecial(document, iitextof9);
            agregarParrafoEspecial(document, iitextof10);
            agregarEspacioEnBlanco(document);
            agregarParrafo2(document, iitextof11);
            parrafoVignetasCorto(document, iitextof12);
            parrafoVignetasCorto(document, iitextof13);
            parrafoVignetasCorto(document, iitextof14);

            agregarParrafoLetraFrancesa(document, iitextob7);
            agregarParrafoLetraFrancesa(document, iitextob8);
            agregarParrafoLetraFrancesaSeguido3(document, iitextob9);
            titulo(document, iii);    
            subparrfosFrancesaUnderline(document, "1. Fundamentos de Derecho Procesal");
            agregarSubparrafos(document, iiia);
            subparrfosFrancesaUnderline(document, "2. Fundamentos de Derecho Material.");
            agregarSubparrafos(document, iiib);
            //agregarSubparrfosFrancesaIV(document, "3. Resolución Administrativa N.º 006-2004-SP-CS");
            subparrfosFrancesaUnderline(document, "3. Resolución Administrativa N.º 006-2004-SP-CS");
            agregarSubparrafosSeguido(document, iiic);
            //agregarSubparrfosFrancesaIV(document, "4. Los juzgados de la Subespecialidad Comercial conocen:");
            agregarSubparrafosSeguido(document, iiid);
            agregarSubparrafosSeguido3(document, iiie);

            titulo(document, iv);
            agregarParrafo(document, "De conformidad con lo dispuesto por los artículos 424º y 720º del Código Procesal Civil, ofrecemos como medios probatorios los siguientes:");
            // Crear subpárrafos para IV. MEDIOS PROBATORIOS. -
            agregarSubparrfosFrancesaIV(document, "1.   \tDocumento que contiene la garantía:");
            agregarSubparrafos(document, iv1a);
            agregarSubparrfosFrancesaIV(document, "2.   \tEscritura Pública de Constitución Unilateral de Gravamen Hipotecario de Primer Rango, con la finalidad de que se Emita un Título de Crédito Hipotecario Negociable.");
            agregarSubparrafos(document, iv2a);
            agregarSubparrfosFrancesaIV(document, "3.   \tEstado de cuenta de saldo deudor:");
            agregarSubparrafos(document, iv3a);
            agregarSubparrfosFrancesaIV(document, "4.   \tCarta notarial de requerimiento de pago:");
            agregarSubparrafos(document, iv4a);
            agregarSubparrfosFrancesaIV(document, "5.   \tCertificado de gravamen:");
            agregarSubparrafos(document, iv5a);

            otrosidigo(document, otrosidigo1, otrosidigo1contenido);
            otrosidigoBold(document, otrosidigo2, otrosidigo2contenido);
            otrosidigoBold2(document, otrosidigo3, otrosidigo3contenido);
           
            parrafoDesdeCero(document, otrosidigo32);
            parrafoVignetas(document, otrosidigo33);
            parrafoVignetas(document, otrosidigo34);

            //26 JUN 2024 se elimina el cuarto otrosí decimos
            //otrosidigoBold(document, otrosidigo4, otrosidigo4contenido);
            otrosidigo5(document, otrosidigo5, otrosidigo5contenido);
           
            porTantoBold(document, otrosidigo51);
            porTanto(document, otrosidigo52);
            titulo(document, anexos);
            anexos(document, anexo1);
            anexos(document, anexo2);
            anexos(document, anexo3);
            anexos(document, anexo4);
            anexos(document, anexo5);
            anexos(document, anexo6);
            anexos(document, anexo7);
            anexos(document, anexo8);
            anexos(document, anexo9);
            anexos(document, anexo10);
            anexos(document, anexo11);
            anexos(document, anexo12);
            anexos(document, anexo13);
            anexos(document, anexo14);
            anexos(document, anexo15);                
            anexos(document, anexo16);              
               
            lugarYFecha(document, ciudadFecha);
               
            XWPFTable table = document.createTable(2, 2);
            tableSetBorders(table, STBorder.BABY_PACIFIER, 0, 0, "FFFFFF");              
            setTableAlign(table, STJc.DISTRIBUTE);
               
            int defaultColWidth = 1 * 1600 * 6;
            int[] columnWidths = new int[]{
                defaultColWidth * 1 / 2,//0
                defaultColWidth * 1 / 2//1
            };
            // Establecer ancho de las columnas
            setColumnWidth(table, 0, 0, columnWidths[0]);
            setColumnWidth(table, 0, 1, columnWidths[1]);
               
            agregarFirma1(table, "DENISSE ADRIANA I. VEGA FARRO", "ABOGADA", "REG. C.A.L. N° 65958");
            agregarFirma2(table, "DENISSE ADRIANA I. VEGA FARRO", "APODERADA", "");
            agregarFirma1(table, "MIGUEL A.A. MENDIETA SANCHEZ", "ABOGADO", "REG. C.A.L. N° 75947");
            agregarFirma2(table, "MIGUEL A.A. MENDIETA SANCHEZ", "APODERADO", "");    
            agregarFirma1(table, "YANET VICTORIO COLLAZOS", "ABOGADA", "REG. C.A.L. N° 50944");
            agregarFirma2(table, "YANET VICTORIO COLLAZOS", "APODERADA", "");
            agregarFirma1(table, "NEYLI MONDRAGON IZQUIERDO", "ABOGADA", "REG. C.A.L. N° 84522");
            agregarFirma2(table, "NEYLI MONDRAGON IZQUIERDO", "APODERADA", "");                
            agregarFirma1(table, "HISAEL JHAZEEL HURTADO BAYONA", "ABOGADO", "REG. C.A.L. N° 90674");
            agregarFirma2(table, "HISAEL JHAZEEL HURTADO BAYONA", "APODERADO", "");
            agregarFirma1(table, "JHON RICARDO SALAZAR SALGUEDO", "ABOGADO", "REG. C.A.L. N° 82036");
            agregarFirma2(table, "JHON RICARDO SALAZAR SALGUEDO", "APODERADO", "");
            agregarFirmaCentro(table, "VERA LUCIA PAPUICO MINO", "ABOGADA", "REG. C.A.L N° 95940");
            mergeCellsHorizontal(table, 1, 0, 1);

            configurarInterlineado(document);
            
            // Pie de página
            XWPFFooter pieDePagina = document.createFooter(HeaderFooterType.DEFAULT);
            XWPFParagraph parrafoPieDePagina = pieDePagina.createParagraph();
            XWPFRun run = parrafoPieDePagina.createRun();
            String TCHNFP1 = TCHN.replaceAll("^TCHN", "");
            run.setText(TCHNFP1+"–"+FONDO_PIE);

           
            System.out.println("Documento Word creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
   
}