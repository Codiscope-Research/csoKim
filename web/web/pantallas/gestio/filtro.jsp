<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<div id="div_opciones" style="margin-top: 5px;">
	<fieldset class="caixa-formulari etiqueta-mitjana">

		<center>
			<table class="noborder"
				style="width: 97%; position: relative; top: -5px; margin-bottom: 8px;">
				<tr>
					
					<td style="width: 230px"><img
						src="/${initParam.context}/web/img/estats/${urlimage}"
						width="490px"></td>
					<td>
						<table class="seleccioHist">
							<thead>
								<tr>
									<td class="borderBottom" style="width: 160px;"><b> <bean:message
												key="txt.gestio.ultim.estat" /></b></td>
									<td class="borderLeft borderBottom" style="width: 30px;">
										<b> <bean:message key="txt.gestio.ultima.data" /></b>
									</td>
								</tr>
							</thead>
							
							<logic:notEmpty name="estats">
								<logic:iterate id="oo" collection="${estats}" indexId="numFila">
									
										<tr>
											<td>
												<c:if test="${frm.fulltimeHistoricEstat[oo.id-1].dateOfChange!=null}">
													<img src="/${initParam.context}/web/img/validacionOk.png" width="16px">
												</c:if>
												<bean:write name="oo" property="descripcio" />
											</td>
											<td>												
											<c:if test="${frm.fulltimeHistoricEstat[oo.id-1].dateOfChange!=null}">
													<fmt:formatDate pattern="dd-MM-yyyy"
															value="${frm.fulltimeHistoricEstat[oo.id-1].dateOfChange}" />
											</c:if>
											
											</td>
										</tr>
									
								</logic:iterate>
							</logic:notEmpty>																																		
						</table>
					</td>
				</tr>
			</table>
		</center>
	</fieldset>
</div>
</tbody>
</table>
</div>
<br>
<br>
<div class="contenidor menu all-width-print"
	style="background: rgb(245, 247, 249); padding-bottom: 10px;">
	<table class="noborder"
		style="width: auto; margin-top: 10px; margin-bottom: 10px; margin-left: 5px; margin-right: 5px;">
		<tbody>
			<tr>
				<td style="width: 8%;"><b> <bean:message
							key="txt.gestio.idfactura" />:
				</b></td>
				<td style="width: 20%;"><input type="text" name="idTarea"
					value="${frm.code}" readonly="readonly" id="idfact"
					style="width: 80%;" class="info"></td>
				<td style="width: 11%;"><b> <bean:message
							key="txt.gestio.data.gene" />:
				</b></td>
				<td style="width: 10%;"><input type="text" name="fechaLimite"
					maxlength="10"
					value="<fmt:formatDate  pattern="dd-MM-yyyy" value="${frm.fentrada}" />"
					style="width: 80%;" readonly="readonly" id="fechaLimite"
					class="info"></td>
				<td style="width: 5%;"><b> <bean:message
							key="txt.gestio.cso" />:
				</b></td>
				<td style="width: 20%;"><input type="text" name="fechaLimite"
					maxlength="10" value="${frm.nomCso}" readonly="readonly" id="idCSO"
					style="width: 250px;" class="info"></td>
			</tr>
	</table>
	<table class="noborder">
		<tr>
			<td style="width: 10%;"><b> <bean:message
						key="txt.gestio.servicios" />:
			</b></td>
			<td style="width: 78%;">
				<table class="selecciom dataTable" id="srv_fact" style="width: 111%">
					<thead>
						<tr>
							<th class="firstheader"><bean:message
									key="txt.gestio.servicios" /></th>
							<th><bean:message key="txt.gestio.volumetria" /></th>
							<th><bean:message key="txt.gestio.importe.Op" /></th>
							<th><bean:message key="txt.gestio.importe" />&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<tr>
							<th>TOTAL</th>
							<th></th>
							<th></th>
							<th>${frm.importe} &#8364;</th>
						</tr>
					</tfoot>
				</table>
			</td>

		</tr>
		<tr>
		</tr>
		<tr>
			<td><b> <bean:message key="txt.gestio.observaciones" /></b></td>
			<logic:equal name="user_cso" value="gdscusa">
			<td colspan="3"><textarea rows="3" id="observ_fact" cols="96" onkeyup="maxlengthText(this,400)"  >${frm.descripcio}</textarea>			
			</td>
			</logic:equal>
			<logic:notEqual name="user_cso" value="gdscusa">
			<td colspan="3"><textarea rows="3" id="observ_fact" cols="96"  onclick="blur();" onkeyup="maxlengthText(this,400)" class="info"  >${frm.descripcio}</textarea>			
			</td>
			</logic:notEqual>
		</tr>
		<tr>

			<div id="div_inc">
				<td><b> <bean:message key="txt.gestio.incidencias" /></b></td>
				<td colspan="3"><textarea rows="3" onclick="blur();" class="info" id="inc_fact"
						cols="96">${frm.incidencia}</textarea></td>
			</div>

		</tr>
		</tbody>
	</table>
	<table>
		<logic:notEmpty name="names" >
			<logic:iterate name="names" id="pdf">
				<tr>
					<td  >
						<a href="/${initParam.context}/DownloadPDF.do?name=${pdf.descripcio}" id="${pdf.id}_td"  >${pdf.descripcio}.pdf</a>&nbsp; <a href="#" onclick="deletePDF(this.id,this,'${pdf.id}_td');" id="name=${pdf.descripcio}&idFactura=${pdf.id}" ><img src="/${initParam.context}/web/img/delete.png" /></a>
					</td>
				</tr>	
			</logic:iterate>		
						
		</logic:notEmpty>
		
	</table>
	<script type="text/javascript">
	function deletePDF(data,self,aa){
		$(self).css("visibility","hidden");
			$(self).css("display","none");    
		var data =data;
      	$.ajax({
      		  type: "POST",
      		  url: '/${initParam.context}/DeletePDF.do',
      		  dataType: 'json',
      		  data: data,
      		  success: function(json){	
      		
           			$("#"+aa).css("visibility","hidden");
           			$("#"+aa).css("display","none");           			
           					
      		  },
      		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
        								$("#errorsajax").show();  		
      		  					}
      		});	
	}
	</script>
</div>
<input type="hidden" id="facturaId" value="${frm.id}" />
<html:form styleId="GestionFacturasForm" action="/FacturaPDFAction"
	method="post">
	<html:hidden property="id" />
</html:form>
<html:form styleId="GestionFacturasChangeStatForm"
	action="/ChangeStatFacturaAction" method="post">
	<html:hidden property="id" />
	<html:hidden property="newStat" />
	<html:hidden property="oldStat" value="${frm.idEstat}" />
	<html:hidden property="incidencia" />
</html:form>
<html:form styleId="SaveFacturasForm" action="/FacturaSaveAction"
	method="post">
	<html:hidden property="observaciones" />
	<html:hidden property="id" />
</html:form>
<html:form styleId="EditFacturaForm" action="/EditFacturaAction"
	method="post">
	<html:hidden property="id" value="${frm.id}" />
</html:form>
<logic:equal name="user_cso" value="gdscusa">
	<table class="noborder"
		style="width: 100%; margin-top: -15px; padding: 0px;">
		<tr>
			<td class="esq"
				style="height: 50px; width: 60px; padding-left: 0px; font-size-adjust: none;">
				<input type="button" name="btnSalir"
				value="<bean:message key="txt.gestio.button.salir"/>"
				onclick=" volverBusqueda();" style="width: 60px;"
				class="boto  mybut">
			</td>
			<logic:equal name="frm" property="idEstat" value="2">
				<td style="width: 400px;"></td>


				<td class="dre" style="padding-right: 0px;"><input
					type="button" name="btnNoConforme"
					value="<bean:message key="txt.gestio.button.inc"/>"
					onclick="doIncVal();" style="width: 80px;"
					class="boto  mybut"></td>

			</logic:equal>
			<logic:equal name="frm" property="idEstat" value="3">
				<td style="width: 650px;"></td>

			</logic:equal>
			<logic:equal name="frm" property="idEstat" value="4">
				<td style="width: 450px;"></td>

				<td class="dre" style="padding-right: 0px;"><input
					type="button" name="btnNoConforme"
					value="<bean:message key="txt.gestio.button.inc"/>"
					onclick="doIncFact();" style="width: 80px;"
					class="boto  mybut"></td>
				<td class="dre" style="padding-right: 0px; width: 90px;"><input
					type="button" name="btnFin"
					value="<bean:message key="txt.gestio.button.fin"/>"
					onclick="doFin();" style="width: 80px;"
					class="boto  mybut"></td>
			</logic:equal>

			<logic:equal name="frm" property="idEstat" value="5">
				<td style="width: 450px;"></td>

				<td class="dre" style="padding-right: 0px;"><input
					type="button" name="btnNoConforme"
					value="<bean:message key="txt.gestio.button.resolver"/>"
					onclick="doResoltNoConforme();" style="width: 80px;"
					class="boto  mybut"></td>

				<script type="text/javascript">
					$("#div_inc").show();
				</script>
			</logic:equal>
			<logic:equal name="frm" property="idEstat" value="8">
				<td style="width: 850px;"></td>
				<td class="dre" style="padding-right: 0px; width: 100px;"><input
					type="button" name="btnDesar"
					value="<bean:message key="txt.gestio.button.resolver"/>"
					onclick="doResoltIncValid();" style="width: 100px;"
					class="boto  mybut"></td>
				<script type="text/javascript">
					$("#div_inc").show();
				</script>
			</logic:equal>
			<logic:equal name="frm" property="idEstat" value="9">
				<td style="width: 850px;"></td>
				<td class="dre" style="padding-right: 0px; width: 100px;"><input
					type="button" name="btnDesar"
					value="<bean:message key="txt.gestio.button.resolver"/>"
					onclick="doResoltI2();" style="width: 100px;"
					class="boto  mybut"></td>
				<script type="text/javascript">
					$("#div_inc").show();
				</script>
			</logic:equal>
			<logic:notEqual name="frm" property="idEstat" value="6">

				<td class="dre" style="padding-right: 0px; width: 90px;"><input
					type="button" name="btnDesar"
					value="<bean:message key="txt.gestio.button.guardar"/>"
					onclick="doDesar();" style="width: 180px;"
					class="boto mybut"></td>
			</logic:notEqual>
			<logic:equal name="frm" property="idEstat" value="6">
				<td style="width: 450px;"></td>
				<td class="dre" style="padding-right: 0px; width: 100px;"></td>
			</logic:equal>
		</tr>
	</table>
</logic:equal>
<logic:equal name="user_cso" value="cso">
	<script type="text/javascript">
		$("#observ_fact").addClass("info");
	</script>
	<table class="noborder"
		style="width: 100%; margin-top: -15px; padding: 0px;">
		<tr>
			<td class="esq" style="height: 50px; width: 60px; padding-left: 0px;">
				<input type="button" name="btnSalir"
				value="<bean:message key="txt.gestio.button.salir"/>"
				onclick="volverBusqueda();" style="width: 60px;"
				class="boto  mybut">
			</td>

			<logic:equal name="frm" property="idEstat" value="3">
				<td class="dre" style="padding-right: 0px; width: 750px;"><input
					type="button" name="btnConforme"
					value="<bean:message key="txt.gestio.button.conforme"/>"
					onclick="doConforme();" style="width: 80px;"
					class="boto  mybut"></td>
				<td class="dre" style="padding-right: 0px;"><input
					type="button" name="btnNoConforme"
					value="<bean:message key="txt.gestio.button.noconforme"/>"
					onclick="doNoConforme();" style="width: 100px;"
					class="boto  mybut"></td>
			</logic:equal>
		</tr>
	</table>
</logic:equal>
</div>
</div>
<!-- Pantalla emergent per client jquery.dialog -->
<div id="dialog_inc" class="filtres filtres-oberts" title="">

	<span class="switchSinImagen "  style="text-align: left; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
	<c:if test="${frm.idEstat eq 3}">
		${estats[3].descripcio}
	</c:if>
	<c:if test="${frm.idEstat ne 3}">
		${estats[frm.idEstat-1].descripcio}
	</c:if> 
	</span>
	<fieldset id="fieldset_filtro">
		<table class="noborder">
			<tr height="20px;" >
				<td colspan="4" ></td>
			</tr>
			<tr>
				<td colspan="4" ></td>
			</tr>
			<tr>
				<td style="width: 150px; "><bean:message
						key="txt.gestio.dialog.inc.motiu" />:</td>
				<td colspan="3"><select property="sel_inc" id="sel_inc"
					style="width: 200px; " onchange="setInc(this.value)">
						<logic:notEmpty name="inc">
						<option value=""></option>
							<logic:iterate id="oo" collection="${inc}" indexId="numFila">
								<option value="${oo.id}">${oo.descripcio}</option>
							</logic:iterate>
						</logic:notEmpty>
						<option value="">
							<bean:message key="txt.gestio.dialog.select.altre" />
						</option>
				</select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="inc_text">
						<textarea rows="3" id="inc_motiu" cols="60"></textarea>
					</div>
				</td>
			</tr>
			<tr>

				<td class="dre" style="width: 30px; text-align: left;"><html:button
						altKey="txt.btn.aceptar" property="btnAceptar"
						styleClass="boto" onclick="closeDialogInc();">
						<bean:message key='txt.gestio.dialog.close' />
					</html:button></td>

				<td colspan="2">&nbsp;</td>
				<td class="dre" style="width: 30px; text-align: right;"><html:button
						altKey="txt.btn.aceptar" property="btnAceptar"
						styleClass="boto" onclick="saveIncidencia();">
						<bean:message key='txt.gestio.dialog.ok' />
					</html:button></td>
			</tr>
		</table>
	</fieldset>

</div>
<!-- Pantalla emergent per trams jquery.dialog -->

<div id="dialog_tram" class="filtres filtres-oberts"
	title="<bean:message key="txt.gestio.title.dialog.tram"/>">
	<span class="switchSinImagen"
		style="text-align: right; text-decoration: none; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;">
		<bean:message key="txt.gestio.title.dialog.tram" />
	</span>
	<fieldset id="fieldset_filtro">
		<table class="noborder">
			<tr>
				<td colspan="3" id="td_tram"></td>
			</tr>
			<tr>
				<td class="dre"><html:button altKey="txt.btn.aceptar"
						property="btnAceptar" styleClass="boto"
						onclick="closeDialog();">
						<bean:message key='txt.gestio.dialog.close' />
					</html:button></td>
			</tr>
		</table>
	</fieldset>
	<br>


</div>
