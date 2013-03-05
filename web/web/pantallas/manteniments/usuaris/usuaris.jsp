<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
  <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/Intos.css"  type="text/css" media="screen" />    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />      
    <link rel="stylesheet" href="/${initParam.context}/web/css/aplicatiu.css" type="text/css"   media="screen" />    
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/demo_table.css" type="text/css"   media="screen" /> 
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/pestanesODM.css"	type="text/css" media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/IntosPrint.css"  type="text/css" media="print" />
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
				    			    			
    <script src="/${initParam.context}/web/js/jquery/jquery-1.4.2.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.core.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.widget.js" type="text/javascript"></script>	
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.tabs.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.mouse.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.draggable.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.droppable.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.sortable.js" type="text/javascript"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.ui.dialog.js" type="text/javascript"></script>  
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jquery.ui.button.js"></script>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jquery.ui.position.js"></script>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jquery.effects.core.js"></script>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jquery.bgiframe-2.1.1.js"></script>
    <script src="/ParticipadasIntosWeb/web/js/jsUtils.js" type="text/javascript"></script>      
   	<script src="/ParticipadasIntosWeb/web/js/ModuloFunciones.js" type="text/javascript"></script>
   	<script src="/ParticipadasIntosWeb/web/js/ParticipadasFunciones.js" type="text/javascript"></script>        	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>
   	<script src="/ParticipadasIntosWeb/web/js/ParticipadasFunciones.js" type="text/javascript"></script>
   	<script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.js"></script>	
	<script src="/${initParam.context}/web/pantallas/manteniments/usuaris/jsusuaris.js" type="text/javascript"></script>    
</head>

<body  onload="inicio()">
    <center>  	
    <div id="div_pantalla" style="width:950px" alig="left" >
		<script language="javascript">

			txtConfirmUpdate  = "<bean:message key='manteniments.update.confirm'/>";
			txtSelectRegistre  = '<bean:message key="manteniments.selectGraella"/>';
			
		</script>
    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/manteniments/usuaris/pestanas.jsp" />    
    <!-- end   :: pestanyas -->
    
    <div id="div_body" >
				<center class="ocultoPrint" >
						<div id="div_INFO_WARN_ERROR" style="width:100%;text-align:left;margin-top:15px<c:if test="${user.acces eq  'gdsusers'}">;display:none</c:if>">
		        		<html:messages  message="true"   property="INFO" id="message" header="login.messages.header.sin.scroll" footer="login.messages.footer" >
		            		<p id="mensaje" class="missatge-info">        
		              		<bean:write name="message" />
		            		</p>
		        		</html:messages>
		
		        		<html:messages  message="true"  property="WARNING" id="message" header="login.messages.header.sin.scroll" footer="login.messages.footer" >
		            		<p id="mensaje" class="missatge-warning">        
		              		<bean:write name="message" />
		            		</p>
		        		</html:messages>
		        
		        		<html:errors  header="login.errors.header.sin.scroll" footer="login.errors.footer" prefix="login.errors.prefix" suffix="login.errors.suffix" />
						</div>
				</center>
  
				<logic:notEmpty name="paginacion" >
						<bean:define id="paginacion" name="paginacion" />
				</logic:notEmpty>

				<div id="div_rejilla" class="filtres filtres-oberts" style="width:800px;align:center;border:#a5acb2 1px solid;margin-bottom:0px">
    						    
    						<div style="padding-top:0px;padding-bottom:15px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/manteniments/usuaris/rejilla.jsp" />
    								<!-- end   :: rejilla de datos -->
    						</div>
  
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
			<!-- begin :: rejilla de formulari -->
    		<c:import url="/web/pantallas/manteniments/usuaris/formulari.jsp" />
    		<!-- end   :: rejilla de formulari -->
				</div>
		</div>
    <form method="post" id="frmAdmin" action="">
    <input type="hidden" name="f_centroEntra"  />        
    </form>      
	    								<!-- begin :: formulario-->
	    									<c:import url="/web/pantallas/utils/waiting.jsp"/>
	    								<!-- end   :: formulario-->  
    </div>
    </center>
</body>
</html:html>