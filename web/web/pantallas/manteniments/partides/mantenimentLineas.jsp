<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
    <title><bean:message key='aplicatiu.titol'/></title>
    <link rel="stylesheet" href="/${initParam.context}/web/css/consulta.min.css" type="text/css" media="screen" />
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
</head>
<body onload="inicio();">	
 
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">

    <!-- begin :: pestanyas -->
    <c:import url="/web/pantallas/manteniments/partides/titolCSO.jsp" />
    <c:import url="/web/pantallas/manteniments/partides/pestanas.jsp" />
    <c:import url="/web/pantallas/utils/errors.jsp" />
    <!-- end   :: pestanyas -->

    <div id="div_body" align="left">
    
    	<!-- begin :: filtro para ListCercaContractesAction  -->
    		<c:import url="/web/pantallas/manteniments/partides/filtro.jsp" />
    		<!-- end   :: filtro para ListCercaContractesAction -->
    		
				<div id="div_rejilla" class="filtres filtres-oberts">
  					<span class="switch" style="text-decoration: none "   >  <!-- visibles -->
     						<bean:message key='txt.partida.title'/> 
  					</span>
  					<fieldset  id="fieldset_rejilla">    
    						<div style="padding-top:0 px;padding-bottom:30 px" >
    								<!-- begin :: rejilla de datos -->
    								<c:import url="/web/pantallas/manteniments/partides/rejilla.jsp" />
    								<!-- end   :: rejilla de datos -->
    						</div>  
						</fieldset>
				</div>
    </div>
    
    		
 
		
   </div>
   </div>
   <!-- Pantalla emergent per factors de creixement jquery.dialog -->
	<div id="dialog_factors" class="filtres filtres-oberts" title="">
		<script type="text/javascript">
		</script>
		<span class="switchSinImagen"
			style="text-align: right; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
		<bean:message key='txt.but.factores' /></span>
		<fieldset id="fieldset_filtro">
			<table class="noborder">
				<tr>
				<td>				
					<select  id="f_any" style="width: 125px;" onchange="changeYearOfFactors(this.value)" >																		
											
												<logic:iterate id="year" indexId="i" name="years"  >													
													<option value="${year.id}" >${year.id}</option>
												</logic:iterate>  
                 								
                 							 
										
                	</select>
				</td>
				</tr>
				<tr>
					<td colspan="2" >
						<table  class="selecciom dataTable" id="factor_tbl" >
						<thead>
						<tr>
						<th class="firstheader"  ><bean:message key='txt.manteniment.partida.dialog.table.fc.th1' /></th>
						<th class="firstheader"  ><bean:message key='txt.manteniment.partida.dialog.table.fc.th2' /></th>
						</tr>
						</thead>
						<tbody>
						<tr id="m_1">
						<td><bean:message key="txt.consulta.select.mes1" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_1" value="" /></td>
						</tr>
						<tr id="m_2" >
						<td><bean:message key="txt.consulta.select.mes2" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_2" value="" /></td>
						</tr>
						<tr id="m_3" >
						<td><bean:message key="txt.consulta.select.mes3" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_3" value="" /></td>
						</tr>
						<tr id="m_4" >
						<td><bean:message key="txt.consulta.select.mes4" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_4" value="" /></td>
						</tr>
						<tr id="m_5" >
						<td><bean:message key="txt.consulta.select.mes5" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_5" value="" /></td>
						</tr>
						<tr id="m_6" >
						<td><bean:message key="txt.consulta.select.mes6" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_6" value="" /></td>
						</tr>
						<tr id="m_7" >
						<td><bean:message key="txt.consulta.select.mes7" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_7" value="" /></td>
						</tr>
						<tr id="m_8" >
						<td><bean:message key="txt.consulta.select.mes8" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_8" value="" /></td>
						</tr>
						<tr id="m_9" >
						<td><bean:message key="txt.consulta.select.mes9" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_9" value="" /></td>
						</tr>
						<tr id="m_10" >
						<td><bean:message key="txt.consulta.select.mes10" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_10" value="" /></td>
						</tr>
						<tr id="m_11" >
						<td><bean:message key="txt.consulta.select.mes11" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_11" value="" /> </td>
						</tr>
						<tr id="m_12" >
						<td><bean:message key="txt.consulta.select.mes12" />:</td>
						<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="i_12" value="" /></td>
						</tr>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
				<td><bean:message key="txt.partides.dialog.import.pactat" />&nbsp;(&euro;):</td>
				<td><input type="text" onblur="onlyDouble(this.value,this.id)" id="importpactat" value="" /></td>
				</tr>
				<tr>
					<td align="left" ><html:button  altKey="txt.btn.aceptar"
						property="btnAceptar" styleClass="boto"
						onclick="closeDialogFactores();">
						<bean:message key='txt.gestio.dialog.close' />
					</html:button></td>
					<td class="dre"><html:button altKey="txt.btn.aceptar"
						property="btnAceptar" styleClass="boto"
						onclick="guardarFactors();">
						<bean:message key='txt.btn.guardar' />
					</html:button></td>
				</tr>
			</table>
		</fieldset>
	
	</div>
		<html:form  styleId="factorsForm" action="/GuardaFactorsAction" method="post" styleClass="vertical" >
			<html:hidden property="year" />
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
			<html:hidden property="idPartida" />
			<html:hidden property="importpactat" />
		</html:form>
	<!-- Pantalla emergent per factors de creixement jquery.dialog -->
   	<!-- begin :: formulario-->
			<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
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
				    			    			
				    			    			
  <c:import url="/web/pantallas/includes/initContext.jsp"/>
	<script type="text/javascript" src="/${initParam.context}/web/js/jquery/jqueryCSOAll.min.js"></script>
	<script src="/${initParam.context}/web/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
	
	
   
   	
   	<script src="/${initParam.context}/web/js/participadas/jsparticipadas.min.js" type="text/javascript"></script>    
    <script type="text/javascript" src="/${initParam.context}/web/js/jsaplicatiu.min.js"></script>
   	   	
   	        	
	<script language="javascript" src="/${initParam.context}/StaticJavascript.do"></script>	    

   	
	<script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/partides/jmanteniments.js"></script>
	<!-- script language="javascript" src="/${initParam.context}/web/pantallas/manteniments/partides/jsPartida.min.js"></script-->
		
	<script type="text/javascript">
	var initParams = new InitParams("<bean:message key='txt.partida.nomde'/>","<bean:message key='txt.partida.create'/>","<bean:message key='txt.partida.creada'/>","<bean:message key='txt.partida.updated'/>","<bean:message key='txt.partida.borrada'/>","<bean:message key='txt.partida.creapartida'/>","<bean:message key='txt.but.factores'/>","<bean:message key='errors.form.double'/>","<bean:message key='txt.manteniment.partida.confirm.delete.partida'/>","${initParam.context}");
	
	var initTableParams = new InitTableParams("<bean:message key='datatables.paginate.last'/>","<bean:message key='datatables.paginate.next'/>","<bean:message  key='datatables.paginate.previous'/>","<bean:message key='datatables.paginate.first'/>","<bean:message  key='datatables.loading'/>","<bean:message  key='datatables.empty'/>","<bean:message  key='txt.error.connect.session'/>");
	
		$("#dialog_factors").dialog({
			autoOpen : false,
			modal : true,
			autoSize : true,
			position : 'center',
			height : 540,
			width : 500,
			resizable : 'false',
			open : function(event, ui) {
				$(".ui-dialog-titlebar").hide();
				data = "idpartida="+partida.getId();
				$.ajax({
					  type: "POST",
					  url: '/${initParam.context}/AjaxLoadFactorsCrecimientoAction.do',
					  dataType: 'json',
					  data: data,
					  success: function(json){				 
						  $.each(json.aaData, function(i, item) { 
							document.getElementById("i_"+item.month).value=item.factor;		
						  });
						  document.getElementById("importpactat").value= json.importe;
						  hideWaiting();
					  },
					  error: function(e){ 
							$("#errorsajaxlabel").text("<bean:message  key='txt.error.connect.session'/>");
			        		$("#errorsajax").show();
					  		hideWaiting();
					  					}
					});	
			}
		}).parent().find('.ui-dialog-titlebar-close').hide();
	</script>	
</body>
</html:html>