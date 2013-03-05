<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key='txt.serv.title.filtro'/>			
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch" style="text-decoration: none ">
     			<bean:message key='txt.serv.title.filtro.second'/>
  		</span>
  		<html:form  styleId="BusquedaComparativaServeisForm" action="/admin/MantenimentServAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      				
      					<tr>
      							<td colspan="2" width="250px;" > <%= request.getAttribute("month") %> de <%= request.getAttribute("year") %></td>
       							<td><b><bean:message key='txt.serv.filtro.centro.cso'/></b></td>
         						<td colspan="3">
										<html:select property="f_csoActual" styleId="f_csoActual" errorStyleClass="error" style="width: 325px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>										
											<logic:notEmpty name="csos" >  
                 								<html:options collection="csos" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty> 
										
                						</html:select>
           						</td>        
       								<td colspan="2" align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="getCompareServeisTable()" styleClass="boto"  > 
                 							<bean:message key='txt.serv.filtro.ok'/>
              						</html:button>
</td>
       							
       					</tr>      
       					<tr><td>&nbsp;</td></tr>
						<tr>
      							<td colspan="2" width="250px;" ><bean:message key="txt.manteniment.serveis.acomparacon"/> </td>
      							<td><b>	<bean:message key="txt.serv.filtro.any"/>:</b></td>
         							<td>
         								<html:select property="f_any" styleId="f_any" errorStyleClass="error" style="width: 125px;">
										<logic:notEmpty name="years" >  
                 								<html:options collection="years" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty>              
                				     	</html:select>
             					</td >  
      							<td><b>	<bean:message key="txt.serv.filtro.mes"/>:</b></td>
         						<td>
         								<html:select property="f_mes" styleId="f_mes" errorStyleClass="error" style="width: 143px;">         								
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
             					</td >     
       							<td><b><bean:message key='txt.serv.filtro.centro.cso'/></b></td>
         						<td>
										<html:select property="f_cso" styleId="f_cso" errorStyleClass="error" style="width: 325px;" >
											<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>										
											<logic:notEmpty name="csos" >  
                 								<html:options collection="csos" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty> 
										
                						</html:select>
           						</td>        
       								
       							
       						</tr>			             				      			        			       						
       						<tr><td>&nbsp;</td></tr>
        					
      				</table>
  				</fieldset> 
  					<html:hidden name="BusquedaComparativaServeisForm" property="order_by" />
  					<html:hidden name="BusquedaComparativaServeisForm" property="pagina" />         
  		</html:form>

	</div>
</div> 
  