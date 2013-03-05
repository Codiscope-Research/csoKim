<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key='aplicatiu.titol'/></title>
    <link rel="stylesheet" href="/${initParam.context}/web/css/consulta.min.css" type="text/css" media="screen" />
</head>
<body onload="inicio();">	
 
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/manteniments/cso/pestanas.jsp" />
    <c:import url="/web/pantallas/utils/errors.jsp" />
    <!-- end   :: pestanyas -->

    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/manteniments/cso/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key='txt.cso.title'/> 
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/manteniments/cso/rejilla.jsp" />
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
	<c:import url="/web/pantallas/includes/initContext.jsp"/>			    			
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSOAll.min.js"></script>
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>	    
	<!-- script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/partides/jmanteniments.js"></script -->
	<script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/cso/jmanteniments.js"></script>
		

	<script type="text/javascript">
		var initParams = new InitParams("<bean:message  key='txt.error.connect.session'/>","<bean:message key='txt.cso.error.escoge.ser.rec'/>",
										"<bean:message key='txt.cso.error.nrecurs'/>","<bean:message key='txt.cso.error.preu.recurs'/>",
										"<bean:message key='txt.partida.borrada'/>","<bean:message key='txt.partida.creapartida'/>",
										"<bean:message key='txt.but.factores'/>","<bean:message key='errors.form.double'/>",
										"<bean:message key='txt.manteniment.partida.confirm.delete.partida'/>","${initParam.context}");
	</script>	
</body>
</html:html>