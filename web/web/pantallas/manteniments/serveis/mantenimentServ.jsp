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

    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/manteniments/serveis/pestanas.jsp" />
	<c:import url="/web/pantallas/utils/errors.jsp" />

    <div id="div_body" align="left">
    
 
    		<c:import url="/web/pantallas/manteniments/serveis/filtro.jsp" />
    		
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  
     						<bean:message key='txt.serv.title'/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    							
    								<c:import url="/web/pantallas/manteniments/serveis/rejilla.jsp" />
    								<c:
    						</div>  
    						<br>    				
						</fieldset>
							
				</div>
    </div>
    <c:if test="">
    	
    </c:if>
    		
 
		
   </div>
   </div>
   	<!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
	
	<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>

   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	
   	<!-- script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/serveis/jsserveis.js"></script -->
   	<script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/serveis/jsServeis.min.js"></script>
   	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script> 
	<c:if test="${paginacion==null}"  >
		<script type="text/javascript">
		$("#divScroll").hide();
		</script>
	</c:if>
	<script type="text/javascript">
		var initParams = new InitParams("${initParam.context}");
	</script>
</body>
</html:html>