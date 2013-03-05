<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>	
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" >
		&nbsp;<bean:message key="txt.consulta.title.facturas"/>			
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch ocultoPrint" style="text-decoration: none ">
     			<bean:message key="txt.datos.para.filtrar"/>
  		</span>
  		<html:form  styleId="BusquedaGestionFacturasForm" action="/BusquedaGestionFacturasAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      				
      					<tr>
       							<td><b><bean:message key="txt.consulta.form.cso"/> </b></td>
         						<td colspan="3" >
										<html:select property="f_cso" styleId="f_cso" errorStyleClass="error" style="width: 350px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>										
											<logic:notEmpty name="csos" >  
                 								<html:options collection="csos" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty> 
										
                						</html:select>
           						</td>
           						<td><b><bean:message key="txt.consulta.form.ano"/> </b></td>
         						<td>
										<html:select property="f_any" styleId="f_any" errorStyleClass="error" style="width: 125px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>
											<logic:notEmpty name="years" >
												<html:options collection="years" property="id" labelProperty="descripcio"  />
											</logic:notEmpty>
										
                						</html:select>
           						</td>         
           						<td><b><bean:message key="txt.consulta.form.mes"/></b></td>
         						<td>
										<html:select property="f_mes" styleId="f_mes" errorStyleClass="error" style="width: 125px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>
											<html:option value="1">
												<bean:message key="txt.consulta.select.mes1" />
											</html:option>
											<html:option value="2">
												<bean:message key="txt.consulta.select.mes2" />
											</html:option>
											<html:option value="3">
												<bean:message key="txt.consulta.select.mes3" />
											</html:option>
											<html:option value="4">
												<bean:message key="txt.consulta.select.mes4" />
											</html:option>
											<html:option value="5">
												<bean:message key="txt.consulta.select.mes5" />
											</html:option>
											<html:option value="6">
												<bean:message key="txt.consulta.select.mes6" />
											</html:option>
											<html:option value="7">
												<bean:message key="txt.consulta.select.mes7" />
											</html:option>
											<html:option value="8">
												<bean:message key="txt.consulta.select.mes8" />
											</html:option>
											<html:option value="9">
												<bean:message key="txt.consulta.select.mes9" />
											</html:option>
											<html:option value="10">
												<bean:message key="txt.consulta.select.mes10" />
											</html:option>
											<html:option value="11">
												<bean:message key="txt.consulta.select.mes11" />
											</html:option>
											<html:option value="12">
												<bean:message key="txt.consulta.select.mes12" />
											</html:option>
                						</html:select>
           						</td>      
       								
       								   
       						</tr>
      				        <tr>
       							<td><b><bean:message key="txt.consulta.form.estado"/> </b></td>
         						<td colspan="3" >
										<html:select property="f_estado" styleId="f_estado" errorStyleClass="error" style="width: 350px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>
											<logic:notEmpty name="estats" >
												<html:options collection="estats" property="id" labelProperty="descripcio"  />
											</logic:notEmpty>
										
                						</html:select>
           						</td> 
           						<td><b><bean:message key="txt.consulta.form.fechaini"/> </b></td>
         							<td>
         								<html:text property="f_fechaFacDesde" styleId="datadesde" maxlength="10" size="12" styleClass="text" errorStyleClass="error" style="text-transform:uppercase" onblur="checkdateConsulta(this.id)"   /> <div style="margin-top: -18px;margin-left: 110px"><img  src="/ParticipadasIntosWeb/web/img/icons/admin/16/calendar_full.png" id="llençadorData" ></div>
             					</td >     
       								<td><b><bean:message key="txt.consulta.form.fechafin"/> </b></td>
         							<td>
         								<html:text property="f_fechaFacHasta"  styleId="datahasta" maxlength="10" size="12" styleClass="text" errorStyleClass="error" style="text-transform:uppercase" onblur="checkdateConsulta(this.id)"   /> <div style="margin-top: -18px;margin-left: 110px"><img  src="/ParticipadasIntosWeb/web/img/icons/admin/16/calendar_full.png" id="llençadorData2" ></div>
           						</td>           
           						
       								       							
       						</tr>      				      			        				      					
       						<tr>
       							<td><b><bean:message key="txt.consulta.form.importe"/> &nbsp;(&euro;)</b></td>
         						<td>
										<html:text property="f_impdesde" styleId="f_impdesde"  maxlength="14" size="17" styleClass="text" onblur="onlyDouble(this.value,this.id);"   />
           						</td>        
       								
       							<td><b><bean:message key="txt.consulta.form.hasta"/> </b></td>
         						<td>
									<html:text property="f_imphasta" styleId="f_imphasta"  maxlength="14" size="18" styleClass="text"  onblur="onlyDouble(this.value,this.id);"  />
           						</td>  
           						<td colspan="6" align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" styleClass="boto" onclick="doFiltro();"  > 
                 							<bean:message key="txt.btn.filtrar"/>
              						</html:button> 
         						</td>
       						</tr>
        					
      				</table>
  				</fieldset>    
  				<html:hidden name="BusquedaGestionFacturasForm" property="order_by" />
  				<html:hidden name="BusquedaGestionFacturasForm" property="pagina" />    
  		</html:form>			
	</div>
</div> 
<html:javascript formName="BusquedaGestionFacturasForm" dynamicJavascript="true" staticJavascript="false"/>
 
  