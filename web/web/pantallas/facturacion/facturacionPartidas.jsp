<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
	<!--css-->
	<link rel="stylesheet" href="/${initParam.context}/web/css/facturacio.min.css" type="text/css"   media="screen" />
	
</head>

<body onload="inicio()" >	
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/facturacion/pestanas.jsp" />
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/facturacion/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch ocultoPrint" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key="txt.facturacion.title"/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/facturacion/rejilla.jsp" />
    								<!-- end   :: rejilla de datos -->
    						</div>  
						</fieldset>
				</div>
    </div>		
   </div>
   </div>
    <!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
	
	<style>
	.centerNum {
		text-align: right;
	}

	.bg_red { background: red 50% 50% repeat-x; opacity: .30;    top:  0px !important; position:fix;-moz-opacity: 0.70; opacity:.70; filter: alpha(opacity=70);
	} 
	.bg_ye { background: yellow 50% 50% repeat-x; opacity: .30;    top:  0px !important; position:fix;-moz-opacity: 0.70; opacity:.70; filter: alpha(opacity=70);
	}
	</style>				    			    			
			
	<!-- scripts -->				    			    			
	<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
	         	  	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>
	
	<!-- script src="/${initParam.context}/web/pantallas/facturacion/jsfacturacion.js" type="text/javascript"></script -->
	<script src="/${initParam.context}/web/pantallas/facturacion/jsFacturacio.min.js" type="text/javascript"></script>
	 
	<script language="javascript">
			var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='datatables.empty'/>");
			var initParams = new InitParams("${initParam.context}");
	</script>	
</body>
</html:html>