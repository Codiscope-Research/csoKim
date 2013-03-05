<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra" /></title>
	<!-- css -->
	<link rel="stylesheet" href="/${initParam.context}/web/css/excel.min.css" type="text/css"   media="screen" />

</head>


<body onload="inicio()" >	
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/excelPagades/pestanas.jsp" />    
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
    	
    		<c:import url="/web/pantallas/excelPagades/filtro.jsp" />
    		
    </div>
   </div>
   </div>

    <!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
			    			    	
    <!-- script -->				    			   		
   <c:import url="/web/pantallas/includes/initContext.jsp"/>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	<!-- script src="/${initParam.context}/web/pantallas/excelPagades/jsexcel.js" type="text/javascript"></script --> 
	<script src="/${initParam.context}/web/pantallas/excelPagades/jsExcel.min.js" type="text/javascript"></script> 
	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	    	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>   	
</body>
</html:html>