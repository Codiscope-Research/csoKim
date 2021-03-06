<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key="aplicatiu.titol.finestra" /></title>
    <link rel="stylesheet" href="/${initParam.context}/web/css/modificar.min.css" type="text/css"   media="screen" />
</head>

<body>	
<html:form  styleId="factorsForm" action="/GuardarFactorsFacturaAction" method="post" styleClass="vertical" >	
			<html:hidden property="ene" />
			<html:hidden property="feb" />
			<html:hidden property="mar" />
			<html:hidden property="abr" />
			<html:hidden property="mai" />
			<html:hidden property="jun" />
			<html:hidden property="jul" />
			<html:hidden property="ago" />
			<html:hidden property="set" />
			<html:hidden property="oct" />
			<html:hidden property="nov" />
			<html:hidden property="des" />
			<html:hidden property="code" />
		</html:form> 
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/titolCSO.jsp" />
    <c:import url="/web/pantallas/modificar/pestanas.jsp" />
    <!-- end   :: pestanyas -->
 <c:import url="/web/pantallas/utils/error.jsp" />
  <div id="errorsajax">
		<label style="color: red" id="errorsajaxlabel" ></label>
	</div>
  	<html:form  styleId="ModificarFacturasForm" action="/ModificaFacturaAction" method="post" >
       			<html:hidden name="ModificarFacturasForm"  property="idFactura"/>
       			<html:hidden name="ModificarFacturasForm" property="serveis"/>       			       	
 	</html:form>  
    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;	<bean:message key="txt.modificar.title"/> &nbsp;N�m. &nbsp; <%= request.getAttribute("idfactura") %>			
	</h1>
	</div>
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key="txt.modificar.titles.part1"/>
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px; height: 370px;" >
    								
    								<c:import url="/web/pantallas/modificar/rejillaFactura.jsp" />
    				
    						</div>  
						</fieldset>
				</div>
				<br></br>
				
    </div>
    
    		
 
		<input type="hidden" id="idfacturaXXX" value="<%= request.getAttribute("idfactura").toString() %>" />
   </div>
 
    <div id="factura_factors" class="filtres filtres-oberts" title="">
		<span class="switchSinImagen"
			style="text-align: right; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
		<bean:message key='txt.but.factores' /></span>
		<fieldset id="fieldset_filtro">
			
						<table  class="selecciom dataTable" id="factor_tbl" >
						<thead>
							<tr>
								<th class="firstheader"  ><bean:message key='txt.manteniment.partida.dialog.table.fc.th1' /></th>
								<th class="firstheader"  ><bean:message key='txt.manteniment.partida.dialog.table.fc.th2' /></th>
							</tr>
						</thead>
						<tbody>
							<tr id="m_1">
								<td><bean:message key="txt.consulta.select.mes1" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_1" value="" /></td>
							</tr>
							<tr id="m_2" >
								<td><bean:message key="txt.consulta.select.mes2" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_2" value="" /></td>
							</tr>
							<tr id="m_3" >
								<td><bean:message key="txt.consulta.select.mes3" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_3" value="" /></td>
							</tr>
							<tr id="m_4" >
								<td><bean:message key="txt.consulta.select.mes4" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_4" value="" /></td>
							</tr>
							<tr id="m_5" >
								<td><bean:message key="txt.consulta.select.mes5" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_5" value="" /></td>
							</tr>
							<tr id="m_6" >
								<td><bean:message key="txt.consulta.select.mes6" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_6" value="" /></td>
							</tr>
							<tr id="m_7" >
								<td><bean:message key="txt.consulta.select.mes7" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_7" value="" /></td>
							</tr>
							<tr id="m_8" >
								<td><bean:message key="txt.consulta.select.mes8" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_8" value="" /></td>
							</tr>
							<tr id="m_9" >
								<td><bean:message key="txt.consulta.select.mes9" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_9" value="" /></td>
							</tr>
							<tr id="m_10" >
								<td><bean:message key="txt.consulta.select.mes10" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_10" value="" /></td>
							</tr>
							<tr id="m_11" >
								<td><bean:message key="txt.consulta.select.mes11" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_11" value="" /> </td>
							</tr>
							<tr id="m_12" >
								<td><bean:message key="txt.consulta.select.mes12" /></td>
								<td><input type="text" onblur="onlyDouble(this.value,this.id);" id="i_12" value="" /></td>
							</tr>
						</tbody>
						</table>
					<table>
				       <tr>
							<td align="left" >
							<html:button  altKey="txt.btn.aceptar"
								property="btnAceptar" styleClass="boto"
								onclick="closeDialogFactores();">
								<bean:message key='txt.gestio.dialog.close' />
							</html:button>
							</td>
							<td class="dre">
							<html:button altKey="txt.btn.aceptar"
								property="btnAceptar" styleClass="boto"
								onclick="guardarFactors();">
								<bean:message key='txt.btn.guardar' />
							</html:button>
							</td>
				     </tr>
			    </table>
		</fieldset>
	</div>
	</div>
	<c:import url="/web/pantallas/includes/initContext.jsp"/>
	<!-- link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/Intos.css"  type="text/css" media="screen" />    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/cso/pestanesODM.css" type="text/css" media="screen" />    
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
	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	
   	
   	<script src="/${initParam.context}/web/pantallas/modificar/jsmodificar.js" type="text/javascript"></script >
   	<!-- script src="/${initParam.context}/web/pantallas/modificar/jsModificar.min.js" type="text/javascript"></script--> 
   	    	       	   	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>    
   	
	<script language="javascript">

		var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='datatables.empty'/>");	
		var initErrorParams = new InitErrorParams("<bean:message key='txt.error.serveis.homonims'/>","<bean:message key='errors.form.double'/>","<bean:message key='txt.confirma.canvi.fc'/>","<bean:message key='txt.error.no.serveis.factura'/>","<bean:message  key='txt.error.connect.session'/>","<bean:message  key='txt.empty.factura'/>");
		var initParams = new InitParams("${initParam.context}");
	</script>	

</body>
  
</html:html>