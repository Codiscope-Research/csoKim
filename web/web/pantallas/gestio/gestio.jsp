<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
	<!-- css --> 
 	<link rel="stylesheet" href="/${initParam.context}/web/css/gestio.min.css" type="text/css" media="screen" />
</head>

<body  onload="inicio()">

<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/gestio/titolCSO.jsp" />
    <c:import url="/web/pantallas/gestio/pestanas.jsp" />
  	<c:import url="/web/pantallas/utils/errors.jsp" />
    <!-- end   :: pestanyas -->

    <div id="div_body" align="left">
 
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/gestio/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->    						
   </div>
   </div>
   </div>
    <!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
			<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<!-- end   :: formulario-->
		<!-- link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/Intos.css"  type="text/css" media="screen" />    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/pestanesODM.css" type="text/css" media="screen" />    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />      
    <link rel="stylesheet" href="/${initParam.context}/web/css/aplicatiu.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/demo_table.css" type="text/css"   media="screen" />  
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/jquery.ui.dialog.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/IntosPrint.css"  type="text/css" media="print" /-->
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	
	<style>
		.mybut {
			font: 12px Verdana, Tahoma, Geneva, sans-serif;
		}
		
		.centerNum {
			text-align: right;
		}
		
		div.filtres span.switchSinImagen {
			color: rgb(103, 103, 103);
			background-color: rgb(255, 255, 255);
			font-weight: bold;
			left: 16px;
			padding: 0 30px 0 7px;
			position: absolute;
			text-decoration: underline;
			top: -0.7em;
			margin-bottom: 10px;
		}
	</style>
	<!-- script -->			    			    			
    
	
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
	<!-- script src="/${initParam.context}/web/pantallas/gestio/jsgestio.js" type="text/javascript"></script -->
	<script src="/${initParam.context}/web/pantallas/gestio/jsGestio.min.js" type="text/javascript"></script>
   	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	   	       	   	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>    
   	<script language="javascript">

		var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='datatables.empty'/>");
		
		var initconfirmParams = new InitconfirmParams("<bean:message key='txt.gestio.javascript.confirm.enviar'/>","<bean:message key='txt.gestio.javascript.confirm.guardar'/>","<bean:message  key='txt.gestio.javascript.confirm.cancel'/>","<bean:message key='txt.gestio.javascript.confirm.inc'/>","<bean:message  key='txt.gestio.javascript.confirm.noconforme'/>","<bean:message  key='txt.gestio.javascript.confirm.conforme'/>","<bean:message  key='txt.gestio.javascript.confirm.fin'/>","<bean:message  key='txt.gestio.javascript.confirm.padaga'/>");
		var initParams = new InitParams("${initParam.context}");
		
		var initErrorParams = new InitErrorParams("<bean:message key='errors.form.maxlenght'/>");

		$("#inc_text").hide();
		$("#div_inc").hide();
		function opentext(check) {
			if (check) {
				$("#inc_text").show('slow');
				$("#sel_inc option:last").attr('selected', 'selected');
				$("#inc_motiu").text('');
			} else {
				$("#inc_text").hide('slow');
			}
		}
		function setInc(value) {
			if (value == "") {
				opentext(true);
			} else {
				opentext(false);
				$("#inc_motiu").text(value);
				$("#inc_text").hide('slow');
			}

		}
		
		$("#dialog_inc").dialog({
			autoOpen : false,
			modal : true,
			autoSize : true,
			position : 'center',
			height : 240,
			width : 500,
			resizable : 'false',
			open : function(event, ui) {
				$(".ui-dialog-titlebar").hide();
			}
		}).parent().find('.ui-dialog-titlebar-close').hide();

		$("#dialog_tram").dialog({
			autoOpen : false,
			modal : true,
			autoSize : true,
			position : 'top: 300px;',
			height : 'auto',
			width : '400px',
			resizable : true,
			open : function(event, ui) {
				$(".ui-dialog-titlebar").hide();
			}
		}).parent().find('.ui-dialog-titlebar-close').hide();

		function closeDialog() {
			$("#dialog_tram").dialog("close");
		}
		function closeDialogInc() {
			$("#dialog_inc").dialog("close");
		}

		function saveIncidencia() {
			var frm = $("#GestionFacturasChangeStatForm")[0];
			if ($("#inc_motiu").text() == '') {
				alert("debes escoger una incidencia");
				return;
			}
			frm.incidencia.value = $("#inc_motiu").text();
			$("#dialog_inc").dialog("close");
			frm.submit();
		}

		$("#dialog_inc").dialog("close");
		$("#dialog_tram").dialog("close");
	</script>
</body>
</html:html>