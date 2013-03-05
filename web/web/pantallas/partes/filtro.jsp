<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key="txt.nocon.title.jsp" />			
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch" style="text-decoration: none ">
     			<bean:message key="txt.nocon.title.filtro" />
  		</span>
  		<html:form  styleId="BusquedaNoConformidadesForm" action="/PartesAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
	
        					<tr>       
        						<td><b><bean:message key="txt.nocon.form.fact" />: </b></td>
         							<td>
         								<html:text property="f_idfact" onblur="onlyEntero(this.value,this.name)"></html:text>
             						</td >          					         						     
       								           							         
       						</tr>
       						<tr><td>&nbsp;</td></tr>
       						<tr>   
       							<td><b><bean:message key="txt.nocon.form.cso" />:</b></td>
         							<td>
         							<html:select property="f_cso" styleId="f_cso" errorStyleClass="error" style="width: 305px;" >
         								<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>										
											<logic:notEmpty name="csos" >  
                 								<html:options collection="csos" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty> 
                 					</html:select>
             					</td>     
        						<td><b><bean:message key="txt.nocon.form.any" />: </b></td>
         							<td>
         								<html:select property="f_any" errorStyleClass="error" style="width: 125px;">
         									<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>	
											<logic:notEmpty name="years" >  
                 								<html:options collection="years" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty>              
                				     	</html:select>
                 						                            
                				     	
             					</td >          					         						     
       								<td><b><bean:message key="txt.nocon.form.mes" />:</b></td>
         							<td>
         								<html:select property="f_mes" errorStyleClass="error" style="width: 125px;">
         									<html:option value="">
												<bean:message key="consulta.gestServ.todos" />
											</html:option>	
         									<html:option value="0"> 
												<bean:message key="txt.consulta.select.mes1" />
											</html:option>
										
											<html:option value="1">
												<bean:message key="txt.consulta.select.mes2" />
											</html:option>
											<html:option value="2">
												<bean:message key="txt.consulta.select.mes3" />
											</html:option>
											<html:option value="3">
												<bean:message key="txt.consulta.select.mes4" />
											</html:option>
											<html:option value="4">
												<bean:message key="txt.consulta.select.mes5" />
											</html:option>
											<html:option value="5">
												<bean:message key="txt.consulta.select.mes6" />
											</html:option>
											<html:option value="6">
												<bean:message key="txt.consulta.select.mes7" />
											</html:option>
											<html:option value="7">
												<bean:message key="txt.consulta.select.mes8" />
											</html:option>
											<html:option value="8">
												<bean:message key="txt.consulta.select.mes9" />
											</html:option>
											<html:option value="9">
												<bean:message key="txt.consulta.select.mes10" />
											</html:option>
											<html:option value="10">
												<bean:message key="txt.consulta.select.mes11" />
											</html:option>
											<html:option value="11">
												<bean:message key="txt.consulta.select.mes12" />
											</html:option>
                 						                            
                				     	</html:select>
                 						                            
                				     	
             					</td >     
       							         
       						
         						<td  align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="buscaPartesNoConfirmats()" styleClass="boto"  > 
                 							<bean:message key="txt.nocon.form.but.buscar" />
              						</html:button> 
         						</td>
        					</tr>
      				</table>
  				</fieldset> 
  				<html:hidden name="BusquedaNoConformidadesForm" property="order_by" />
  				<html:hidden name="BusquedaNoConformidadesForm" property="pagina" />             					
  				<html:hidden name="BusquedaNoConformidadesForm" property="rpp" />  
  		</html:form>			
	</div>
</div> 
<html:javascript formName="BusquedaNoConformidadesForm" dynamicJavascript="true" staticJavascript="false"/>