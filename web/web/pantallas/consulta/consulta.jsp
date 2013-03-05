<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra" /></title>		
	<!-- css -->	
	<link rel="stylesheet" href="/${initParam.context}/web/css/consulta.min.css" type="text/css" media="screen" />	  		    			    	
</head>

<body onload="inicio();" >
	
  
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/consulta/pestanas.jsp" />
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/consulta/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key="txt.consulta.title.rejilla"/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/consulta/rejilla.jsp" />
    								<!-- end   :: rejilla de datos -->
    						</div>  
							<br></br>
    						<!-- begin :: navegacion, siguiente pagina -->
    						<logic:notEmpty name="paginacion" >
    								<c:import context="/ParticipadasIntosWeb" url="/web/pantallas/utils/navegacion.jsp" >
      									<c:param name="num.actual.pagina" value="${paginacion.pagina}" />
      									<c:param name="num.ultima.pagina" value="${paginacion.numPaginas}" />
      									<c:param name="txt.alt.primera"  >
           									<bean:message key="btn.primero"/>
      									</c:param>
      									<c:param name="txt.btn.primera"  >
           									<bean:message key="btn.primero"/>
      									</c:param>
      									<c:param name="txt.alt.anterior"  >
           									<bean:message key="btn.anterior"/>
      									</c:param>
      									<c:param name="txt.btn.anterior"  >
           									<bean:message key="btn.anterior"/>
      									</c:param>
      									<c:param name="txt.alt.siguiente"  >
           									<bean:message key="btn.siguiente"/>
      									</c:param>
      									<c:param name="txt.btn.siguiente"  >
           									<bean:message key="btn.siguiente"/>
      									</c:param>
      									<c:param name="txt.alt.ultima"  >
           									<bean:message key="btn.ultima"/>
      									</c:param>
      									<c:param name="txt.btn.ultima"  >
           									<bean:message key="btn.ultima"/>
      									</c:param>

      									<c:param name="js.primera" value="irPag(1);" />
      									<c:param name="js.anterior" value="irPag(${paginacion.pagina-1});" />
      									<c:param name="js.goToPage" value="irPag(pag);" />
      									<c:param name="js.siguiente" value="irPag(${paginacion.pagina+1});" />
      									<c:param name="js.ultima" value="irPag(${paginacion.numPaginas});" />
    								</c:import>
    						</logic:notEmpty>
    						<!-- end   :: navegacion, siguiente pagina --> 
    						</fieldset>	
				</div>
    </div>
   </div>
   </div>
   	<!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
			<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<!-- end   :: formulario-->

<!-- css -->	
    <!-- Hoja de estilos del calendario -->
	<link rel="stylesheet" type="text/css" media="all" href="/ParticipadasIntosWeb/web/css/calendari/calendar-blau.css" title="win2k-cold-1" />	
	<!--scripts-->
 
   
   <script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
   
   <script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
   
   
   <script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
   <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   
   <!-- script src="/${initParam.context}/web/pantallas/consulta/jsconsulta.js" type="text/javascript"></script -->
   <script src="/${initParam.context}/web/pantallas/consulta/jsConsulta.min.js" type="text/javascript"></script>
          	   	
   <script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>	  
   
   <!-- librería principal del calendario -->  
   <script src="/${initParam.context}/web/js/calendar/calendari.min.js" type="text/javascript"></script>
<script language="javascript">

		var initParams = new InitParams("<bean:message key='errors.date'/>","<bean:message key='errors.form.double'/>","${initParam.context}");			   	

			Idioma_user = ${user.idiomaInt};

			if (Idioma_user==0)
				{
						Idioma_user="ca_ES";
				} else {
						Idioma_user="es_ES";
				}
			
		
		

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "datadesde",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llençadorData",          // el id del botón que lanzará el calendario
        locale 		   		:    Idioma_user
    });
    Calendar.setup({
        inputField    	:    "datahasta",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llençadorData2",          // el id del botón que lanzará el calendario
        locale 		   		:    Idioma_user
    });
//---------------------------------------------------------------------------------------------------------------------
</script>  
<c:if test="${requestScope.reload=='yes'}">
		<script language="javascript">
			$(document).ready(function() {
				doFiltro();
			});
		</script>
	</c:if>
	<c:if test="${requestScope.reload=='no'}">
		<script language="javascript">
			$(document).ready(function() {
				initForm();
				doFiltro();
			});
		</script>
	</c:if>


</body>
</html:html>