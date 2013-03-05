<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
<!-- css -->	
<link rel="stylesheet" href="/${initParam.context}/web/css/detallPartida.min.css" type="text/css"   media="screen" />
</head>

<body onload="inicio()" >	
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/detallePartida/pestanas.jsp" />
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/detallePartida/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key="txt.facturacion.title"/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/detallePartida/rejilla.jsp" />
    								<!-- end   :: rejilla de datos -->
    						</div>  
						</fieldset>
				</div>
    </div>		
   </div>
   </div>
    <!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
			<input type="hidden" id="idpartida" value="<%= request.getAttribute("idpartida").toString() %>" />
	<!-- end   :: formulario-->
<style>
.centerNum {
	text-align: right;
}
</style>

<!-- scripts -->
<c:import url="/web/pantallas/includes/initContext.jsp"/>
<script src="/ParticipadasIntosWeb/web/js/3rd-party-j.js" type="text/javascript"></script>
<script type="text/javascript" src="/ParticipadasIntosWeb/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/ParticipadasIntosWeb/extjs/ext-all-debug.js"></script>
        				    			    			
<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
<script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	 
         	  	
<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>
   

<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
<!-- script src="/${initParam.context}/web/pantallas/detallePartida/jsfacturacion.js" type="text/javascript"></script-->  
<!-- script src="/${initParam.context}/web/pantallas/detallePartida/jsTableDetall.js" type="text/javascript"></script-->
<script src="/${initParam.context}/web/pantallas/detallePartida/jsDetallePartida.min.js" type="text/javascript"></script>

<script language="javascript">
		var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='datatables.empty'/>");
		var myData = <%= request.getAttribute("data") %>;
		var initParams = new InitParams("${initParam.context}");
		var initGraficParams= new InitGraficParams("<bean:message key='txt.facturacion.table.second.facturacion'/>","<bean:message key='txt.facturacion.table.second.facturacion.estimada'/>","<bean:message key='txt.facturacion.table.second.facturacion.consumida'/>");					
</script>
	
</body>
</html:html>