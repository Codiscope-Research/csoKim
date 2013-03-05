<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra" /></title>
     <link rel="stylesheet" href="/${initParam.context}/web/css/control.min.css" type="text/css"   media="screen" />
</head>
<body>	
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
<l
    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/control/pestanas.jsp" />    
    <!-- end   :: pestanyas -->
	<c:import url="/web/pantallas/utils/errors.jsp" />
    <div id="div_body" align="left">
    
  
    		<c:import url="/web/pantallas/control/filtro.jsp" />
    		
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch ocultoPrint" style="text-decoration:none;">
     						<bean:message key='txt.control.title.reixeta'/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/control/rejilla.jsp" />    								
    						</div>  
						</fieldset>
				</div>
    </div>
    
		
   </div>
   </div>
    <!-- Pantalla emergent per client jquery.dialog -->
 <div id="dialog_cliente" class="filtres filtres-oberts" title="">
 
 <span class="switchSinImagen"
		style="text-align: right; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
	<label id="csoname"></label>	<label id="csocolor"></label>
</span>
	<fieldset id="fieldset_filtro">

		  		<table class="noborder" >
		  				<tr><td>&nbsp;</td></tr>
				  		<tr>
		                       				<td><label id="date_srv" ></td>
		                         			<td align="right" ><a href="#" onclick="generaExcel();" ><img src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" /></a></td>	
		               </tr>
		               <tr>                         			
		                       				<td colspan="2" align="center">
		                       				  <table class="selecciom dataTable"  id="fac_result" style="width: 1850px;">
											       <thead>
											              <tr>
											                  <th class="firstheader"><a><bean:message key="txt.control.table.dialog.th.1"/></a></th>                                 
											                  <th><a href="#"><bean:message key="txt.control.table.dialog.th.2"/></a></th>                                                                                                   
											                  <th><a><bean:message key="txt.control.table.dialog.th.3"/></a></th>
											                  <th><a><bean:message key="txt.control.table.dialog.th.4"/>&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</a></th>
											                  <th><a><bean:message key="txt.control.table.dialog.th.5"/></a></th>											                  
											              </tr>
											            </thead>
											       <tbody>
											      
											       </tbody>
											     </table>
		                         			</td>	
		      		  </tr>
		      		            				
				</table>                         				
                         				<br>
                         				<br>		             		  		               		
		 
		  	<html:button altKey="txt.btn.aceptar"
						property="btnAceptar" styleClass="boto"
						onclick="closeDialog();">
						<bean:message key='txt.control.but.dialog.salir'/>
					</html:button>
				</fieldset>  		
</div>  	
    <!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
<html:form styleId="GestionFacturasGeneraExcelForm"
	action="/GeneraExcelServiciosCsoAction" method="post">
	<html:hidden property="idCso" />
	<html:hidden property="month" />
	<html:hidden property="year" />
</html:form>

	<!-- css -->
     <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<style>
	.centerNum {
		text-align: right;
	}
	
	.centerBola {
		text-align: center;
	}
	.mybut {
		font: 12px Verdana, Tahoma, Geneva, sans-serif;
	}
	.ui-widget-content A{
	color:rgb(26,100,164);
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
	<!-- scripts -->	    			    			
	<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSO.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	<!-- script src="/${initParam.context}/web/pantallas/control/jscontrol.js" type="text/javascript"></script --> 
	<script src="/${initParam.context}/web/pantallas/control/jsControl.min.js" type="text/javascript"></script> 
	
    
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script> 	    
   	<script language="javascript">

		var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='datatables.empty'/>" );	

		var initParams = new InitParams("${initParam.context}");		
		function closeDialog(){
			
			 $("#dialog_cliente").dialog("close");     
			 	 dadesDialog= new DadesDialog ("0", "0", "0");
			 	 oTableContrSrvFact.fnDeleteRow( 0 );
			 	 hideWaiting();
		}
		var mesos = ["<bean:message key='txt.consulta.select.mes1'/>","<bean:message key='txt.consulta.select.mes2'/>","<bean:message key='txt.consulta.select.mes3'/>","<bean:message key='txt.consulta.select.mes4'/>","<bean:message key='txt.consulta.select.mes5'/>","<bean:message key='txt.consulta.select.mes6'/>","<bean:message key='txt.consulta.select.mes7'/>","<bean:message key='txt.consulta.select.mes8'/>","<bean:message key='txt.consulta.select.mes9'/>","<bean:message key='txt.consulta.select.mes10'/>","<bean:message key='txt.consulta.select.mes11'/>","<bean:message key='txt.consulta.select.mes12'/>"];
			function openClient(id,month,year,name,color){
				 dadesDialog= new DadesDialog (id, month, year,name);
				 $("#dialog_cliente").dialog("open");
					$("#csoname").html(name);
					$("#date_srv").html(mesos[month-1]+" "+year);
					if(color=='vermell'){
						$("#csocolor").html("&nbsp;&nbsp;&nbsp; <img src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/rojo.png\">");
					}else if(color=='ambar'){
						$("#csocolor").html("&nbsp;&nbsp;&nbsp; <img src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/naranja.png\">");
					}else if (color=='verd'){
						$("#csocolor").html("&nbsp;&nbsp;&nbsp; <img src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/verde.png\">");
					}
			}
			
			$("#dialog_cliente").dialog( { autoOpen: false,
				  modal: true,
				  position: 'center',
				  draggable: true,
				  height: 390,
				  width: 900,		
				 open: function(event, ui) { 
					 //carrega la taula del dialog
					 
					 loadTable();
					 $('#dialog_cliente').css('overflow', 'hidden');
					 $(".ui-dialog-titlebar").hide();
		
				 }
			}).parent().find('.ui-dialog-titlebar-close').hide();
			
			$("#dialog_cliente").dialog("close");

</script>
	
</body>
</html:html>