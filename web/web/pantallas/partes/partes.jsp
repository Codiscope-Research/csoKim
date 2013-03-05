<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
	<link rel="stylesheet" href="/${initParam.context}/web/css/partes.min.css" type="text/css" media="screen" /> 
    <script src="/${initParam.context}/web/js/jquery/jquery-1.4.2.js" type="text/javascript"></script>
</head>
<body onload="inicio()" >	
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/partes/pestanas.jsp" />
  	<c:import url="/web/pantallas/utils/error.jsp" />
    <!-- end   :: pestanyas -->

    <div id="div_body" align="left">
    
    	
    		<c:import url="/web/pantallas/partes/filtro.jsp" />
    		
    		
				
    </div>
    	<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     					<bean:message key="txt.title.rejilla"/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/partes/rejilla.jsp" />
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
    		
 <html:errors property="error" name="error"  />
		
   </div>
   </div>
      <!-- Pantalla emergent per client jquery.dialog -->
 <div id="dialog_cliente" class="filtres filtres-oberts" title="">
 
	<span class="switchSinImagen"
		style="text-align: right; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
		   			<bean:message key="txt.title.dialog.incidencias"/>	
		   	       					
       		
       				<a href="#" onclick="generaExcelIncidencia();" ><img src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" /></a>    			
       		
       		   		
    </span>
 
		  		<fieldset id="fieldset_filtro">
		  		  <table class="noborder" style="margin-top: 30px;" >
		  		  	<tr>
		  		  		<td>
   						 <label id="csoname" style="top: 60px; position: absolute;"  ></label>	
   						 <label id="estat" style="top: 30px; position: absolute; font-weight: bold;" ></label>
  				  		</td>
    				</tr>
    			</table>
    			<br></br>
		  		 <table class="selecciom dataTable"  id="fac_result" style="width:100%">
		  		    <thead>
		  		    <tr>
                 		 <th class="cen firstheader"  >
                  			<bean:message key="txt.nocon.table.dialog.th1"/>	
                 		 </th>
                 		 <th>
                 		 	<bean:message key="txt.nocon.table.dialog.th2"/>	
                 		 </th>
                		  <th>
                  			<bean:message key="txt.nocon.table.dialog.th3"/>	
                  		</th>
                  </tr>
                </thead>
       <tbody>
		  		<label id="incs" style="top: 53px; position: absolute;"  ></label>
		  	</tbody>
		  	</table>
		  		         				<br>
                         				<br>	
                   <html:button
						altKey="txt.btn.aceptar" property="btnAceptar"
						styleClass="boto" onclick="closedia();">
						<bean:message key='txt.control.but.dialog.salir'/>
					</html:button>
					
			</fieldset>
			&nbsp;             		  		               		
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
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/jquery.ui.dialog.css" type="text/css" media="screen" />    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />      
    <link rel="stylesheet" href="/${initParam.context}/web/css/aplicatiu.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/demo_table.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/IntosPrint.css"  type="text/css" media="print" /-->
    
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/> 
   	<style>
		.mybut {
			font: 12px Verdana, Tahoma, Geneva, sans-serif;
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
				    			    			
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
	
	<!-- script src="/${initParam.context}/web/pantallas/partes/jspartes.js" type="text/javascript"></script -->
	<script src="/${initParam.context}/web/pantallas/partes/jsPartes.min.js" type="text/javascript"></script> 
	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	 
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>
   	
   	
   	<script language="javascript">			
		var initErrorParams = new InitErrorParams("<bean:message key='errors.numeric'/>");		   
		var initParams = new InitParams("${initParam.context}");
		   var idfact=null;
		   function closedia(){
			   $("#dialog_cliente").dialog("close");    
		   }
		   function generaExcelIncidencia(){
			   window.location.href="/${initParam.context}/GeneraExcelIncidenciaAction.do?"+"idfactura="+$.ui.dialog.idFact;
		   }
		   //extensio del dialog per incorporar idfact
		   $.extend($.ui.dialog.prototype, {
				setidFact: function(id) {
					 $.ui.dialog.idFact =id;
				}
			});
			$("#dialog_cliente").dialog( { autoOpen: false,
				  modal: true,
				  autoResize: true,
				  position: 'center',
				  draggable: true,
				  height: 'auto',
				  width: 'auto',		
				  open: function(event, ui) { 
					 //carrega la taula del dialog		
					 	$(".ui-dialog-titlebar").hide();
					 $('#dialog_cliente').css('overflow', 'hidden');
					 //crida ajax per obtenir info de la factura
						data = "idfactura="+$.ui.dialog.idFact;
						$.ajax({
							  type: "POST",
							  url: '/${initParam.context}/AjaxCargaInfoNoConfirmadosAction.do',
							  dataType: 'json',
							  data: data,
							  success: function(json){				 
								if(json!=null){
									$("#csoname").html("FACTURA: "+json.idFactura+"_"+json.code);
									$("#estat").html(json.estat);
									var incs="";
									$.each(json.incidencia, function(i,item){
										
										 inc = item.split("_");
										 
									     incs=incs+"<tr><td>"+inc[0]+"</td><td>"+inc[1]+"</td><td>"+inc[2]+"</td></tr>";
									});
									$("#incs").html(incs);
															
								}													  
							  },
							  error: function(e){ alert("error:javascript:"+e);}
							});	
		
				 }
			}).parent().find('.ui-dialog-titlebar-close').hide();
			
			$("#dialog_cliente").dialog("close");

	</script>

</body>
</html:html>