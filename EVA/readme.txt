20221104: v2.7 Adicionar el estado extrajudicial en la opción eecc tchn de forma manual. 
20220926: v2.6 Reporte recorrido diario asesor
1. OPCIÓN COBRANZA-->DEUDORES (PARA TODOS)
   a. Estandarización de los contactos y tipificaciones, definidos en el documento funcional
   b. Habilitar la opción de registrar compromisos más de una vez al día.
2. OPCIÓN REPORTES-->REPORTE DIARIO X RECORRIDO (PARA ZDELACRUZ Y MMOREANO)
    Este reporte permitirá  ver el recorrido total de los códigos tchns asignados a los asesores en  el mes vigente (de acuerdo a las metas cargadas en el primer día hábil del período a trabajar).
3. OPCIÓN DEUDORES-->EXPORTAR DALDO DEUDOR.
   Se adiciona en el pdf el campo fecha de desembolso
   
20220617: v2.5 Mejora en el registro de los telefonos, borrado logico

20220525: v.249 Mejora de telefonos-SE SUBIO PERO NO ESTA EN PRODUCCION POR QUE TIENE UNA OBSERVACION

20211222: v2.48 FIX DE LIBRERIA LOG4J

20211025: v2.47 reporte Activo judicial solcitado por Denisse Vega para el Modulo Legal

20211015: 2.46 Modulo Legal - Registro de otros procesos judiciales

20210927: v2.45 Registros de gastos judiciales en Modulo legal

20210909: v2.44.2 FIX de consulta de etapas

20210908: v2.44 Implementar nuevos filtros en Modulo legal -> Consulta por seguimiento :
                o	Apellidos o nombres
		o	Exp. N
		o	Asesor
20210615: v2.43 implementacion de eliminar seguimiento
•	Que se registre la eliminación en la tabla de auditoria.
•	Que solo se muestra dicha opción de eliminación para los perfiles de usuarios configurados.
•	Que muestre un mensaje de confirmación de eliminación.

20210601: v2.42 Nueva opcion "Olvido su contraseña"

20210503: v2.41 FIX para que funcione el reporte de llamadas

20210325: v2.40 FIX para que pueda descargar en excel, saldos deudores de aquellos casos que no se podía(tasa moratoria variable)

20210308: v2.39 Lee cadena conexion DB desde archivo de configuracion, esta listo para contingencia

20210104: V2.38	Version que corrige las demoras de consulta de codigo, dni, busqueda por filtro

20201210: v2.37 Se cambia la cadena de conexion a la base de datos, ahora es la DB que esta en AWS

20201209: v2.36 Fixes
	   1.- la pestaña resumen no actualizaba cuando eran varios tchn
 	   2.- el reporte de operacions 
           3.- el reporte de recaudacion

20201207: v2.35 Proyecto de moneda dolares

20201127: v2.34 Cambio del DashboardLegal

20201026: v2.33 Cambio funcionalidad de busqueda en saldo deudor para que sea en dos tiempos, se activo 
                mensaje de registro de seguimiento

20201016: v2.32 - mejora en eva al consultar tchn
		- incidencia de calcular pago
		- exoneracion de mora

20201007: v2.31 Proyecto TM Variable

20200522: v2.30 Cambio por la Implementación de nueva metodología de cálculo de intereses

20200224: v2.29 FIX de documentos codigos endosados y Rectificacion documentos (asistentes)

20200213: v2.28 Rectificar en los documentos, los nombres de los asistentes de acuerdo a las bases asignadas.

20200109: v2.27 Modificacion de la clase MetasCobranzaBean para que el combo listaanios muestre hasta año 2025

20200107: v2.26 Modificacion de la cadena 01/01/2020 en todo el proyecto y se cambia por 01/01/2025

20191227: v2.25 Modificacion en reporte de saldo deudor con interes compensatorio consolidado (se elimina el 85 y 15) SIS0155: Mejora de Reporte de Saldos Deudores Cobranzasv1

20191129: v2.24 Incluye modificaciones del proyecto SIS-0145: Valorización de códigos judiciales (P145).

20191030: Migracion de aplicacion web EVA desde el servidor 192.168.70.129 hacia el servidor 192.168.70.5 

20191024: Se ha habilitado la búsqueda por DNI propietario ( consulta - EVA)

20191016: Reporte de Avance de Recaudación según el formato proporcionado por el área de Operaciones y fix de clase LegalBean para 
	  modulo legal.

20191014: Fix del reporte de seguimiento de legal que al consultar se está quedando pegado la consulta 

20191011: Se corregido errores en el dashboard debido al formato de fecha

20191010: Se ha incorporado en el dashboard los siguientes adicionales:
	  	1.- IMPULSO POR FONDO
		2.- IMPULSO POR DESEMPEÑO DE ASESOR
		3.- TOP DE VISITAS REALIZADAS POR FONDO.
	   Se ha incorporado la libreria para el modulo de auditoria, tabla auditoria, procedure, etc.

20190917: Se ha implementado el dashboard para el modulo legal:
	  -	CONSOLIDADO CÓDIGOS JUDICIALES  - Activos y Cancelados
	  -	CONSOLIDADO POR FONDOS - Por etapas y fondo
	  -	DETALLE POR ETAPAS - Por desempeño del Proceso y Por Responsable
	  -	DETALLE POR DESEMPEÑO DE PROCESOS - Por Fondo y Por responsable

20190821: Validaciones adicionales al MODULO DE GESTION DE PROCESOS JUDICIALES e implementacion de registro de correos para facturacion electronica

20190816: Nueva funcionalidad del dashboard de seguimiento

20190808: 1.	Carga de data de Metas para Agosto 2019
	  2.	Modificaciones en los procesos de:
		•	Generación de Metas.
		•	Generación de Resumen (Nuevos factores en días para los fondos).
	  3.	Modificaciones en la pantalla Metas No Judiciales.
	  4.	Fondo Capital ahora son contempla 3 asesores.
	  5.	Lista de asesores para Reporte de Compromisos.

20190807: MODULO DE GESTION DE PROCESOS JUDICIALES PARA EL AREA LEGAL TODAS LAS ETAPAS COMPLETAS

20190801: ERROR EN COBRANZA PROPIETARIOS-CAMBIO REALIZADO POR EDWIN COSTILLA

20190731: NUEVA VERSION DE METAS COBRANZA FASE 2

20190702: MODULO DE GESTION DE PROCESOS JUDICIALES PARA EL AREA LEGAL

20190606: MENSAJES DE AVISO DE CARGA Y DE GRABACION EN seguimientoDoc.xhtml 

20190605: AUMENTO DE LOG PARA DETECTAR ERROR DE  MULTIPART EN seguimientoDoc.xhtml 

20190605: RETORNA LA PAGINA seguimientoDoc.xhtml COMO ESTABA ANTES RETORNANDO EL MULTIPART

20190604: FIX A LA PAGINA seguimientoDoc.xhtml para quitar la propiedad multipart

20190604: LEG-00129-Implementacion de Optimización saldos deudores para Legal 

20190531: Añadiendo funcionalidad adicional a la 1ra fase: 
	  Exportacion de data de Metas por asesor

20190527: Añadiendo funcionalidad adicional a la 1ra fase: 
	  Aprobacion de metas y exportacion de informacion

20190521: 1ra fase de proyecto de automatizacion de metas cobranzas

20190514: Se ha añadido log a cada llamada de los stored procedures para medir tiempos de respuesta

20190513: Se ha añadido el archivo noidentificados.xhtml del cambio que se hizo para la identificacion de depositos, provenientes del servicio de carga automatica 
          este es un fix ya que deberia estar en la presente version.

20190510-2: Se ha actualizado la ip 192.168.70.129 hacia 192.168.70.129

20190510:  Se ha realizado el fix para que funcione correctamente el registro de compromisos y llamadas en seguimiento.

20190507:   cambiar las forma como se cierra las conexiones en todo el proyecto eva
	    nuevo reporte formato de INVITACIÓN NEGOCIACIÓN EXTRAJUDICIAL

20190403.2: Se ha añadido una bandeja para la identificacion de depositos, provenientes del servicio de carga automatica

20190311: Se ha modificado las cuentas de banco

20190109: Es la primera version de REINGENIERIA en produccion a la cual se ha añadido log4j a SeguimientoBean, funcion agregarTel
  	  
	  para poder indentificar el error al ingresar duplicado, se corrigio en el stored procedure y esta es una version estable
          
        de EVA


20181214: Se ha habilitado para que los compromisos(mostrados en tipificaciones) asociados a las llamadas se pueda mostrar 

        en todos los casos, actualmente no muestra los compromisos en todos los casos, se añadio un campo mas LIST_TCHN2 
	   
        para que muestre el tchn en otros casos mas y se ha ampliado el rango de horas del sp 
           SP_BUS_COB_LLAMADAS_TELE 
        para que contemple hasta una hora(PI_F_USUARIO_ADD+(1/24))


20181206: Se ha habilitado la opcion: Reporte->Reporte llamadas->TipiFicaciones, se ha añadido un nuevo estado a las opciones 
          
          de menu para que este activo como
 opcion pero que no se muestre en el menu, esto se usa para notificaciones
	  
        "EVA"."TAB_MENU_ROL_USU"."E_ESTADO" IS '01: USUARIO TIENE PERMISO A OPCION Y LA OPCION DEBE MOSTRARSE EN MENU DE NAVEGACION,
						  
                                                02: USUARIO TIENE PERMISO A OPCION Y LA OPCION NO DEBE MOSTRARSE EN MENU DE NAVEGACION
			                  
                                        OTRO VALOR: USUARIO NO TIENE PERMISO Y LA OPCION NO DEBE MOSTRARSE EN MENU'

20181016: Actualizar Asistentes, Jefatura y Formatos
