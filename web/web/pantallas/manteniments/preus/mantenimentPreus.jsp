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
    <c:import url="/web/pantallas/manteniments/preus/pestanas.jsp" />
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/manteniments/preus/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key='txt.preu.title'/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/manteniments/preus/rejilla.jsp" />
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
		table td .medium{
		vertical-align: middle !important;
		width: 50px;
		}
	</style>
	<c:import url="/web/pantallas/includes/initContext.jsp"/>	    			    			
  
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	
   	<!-- script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/preus/jmanteniments.js"></script -->
   	<script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/preus/jmanteniments.js"></script>
   	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>	    
	<script type="text/javascript">
		initParams = new InitParams("<bean:message key='txt.confirm.save.dialog'/>","<bean:message key='txt.error.preu.nodefinit'/>","<bean:message key='txt.error.preu.noafegir'/>","<bean:message key='txt.error.preu.tramnople'/>","<bean:message key='txt.error.preu.tramnoborrar'/>","<bean:message key='txt.preu.tram'/>","<bean:message key='txt.preu.tram.desde'/>","<bean:message key='txt.preu.tram.hasta'/>","<bean:message key='txt.preu.tram.precio'/>","<bean:message key='txt.reg.saved'/>","<bean:message key='txt.manteniment.preu.compulsory.select.cso'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='txt.preus.input.empty'/>","${initParam.context}");
		
		$("#insertOfPriceDIV").hide();
		
	</script>
</body>
</html:html>