<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key='aplicatiu.titol'/></title>
	<!-- end   :: formulario-->
	<link rel="stylesheet" href="/${initParam.context}/web/css/consulta.min.css" type="text/css" media="screen" />
</head>
<body onload="inicio();">	
 
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/manteniments/importPactat/pestanas.jsp" />
    <!-- end   :: pestanyas -->

    <div id="div_body" align="left">
    
    <c:import url="/web/pantallas/utils/errors.jsp" />
    <c:import url="/web/pantallas/manteniments/importPactat/filtro.jsp" />
			
    </div>
    
    		
 
		
   </div>
   	<!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
				    			    			
    
	
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
    
   	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	        	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>
   		
	<!--  script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/importPactat/jmanteniments.js"></script-->
	<script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/importPactat/jsManteniments.min.js"></script>
	
	<script language="javascript">

		var initParams = new InitParams("<bean:message key='errors.form.double'/>","<bean:message key='txt.partides.import.pactat.comfirm.save'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='txt.importpactat.input.empty'/>","${initParam.context}");

	</script>
	
</body>
</html:html>