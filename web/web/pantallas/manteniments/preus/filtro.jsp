<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key='txt.preus.title.filtro'/>			
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch" style="text-decoration: none ">
     			<bean:message key='txt.preus.title.filtro.second'/>
  		</span>
  		<html:form  styleId="BusquedaGestionServiciosForm" action="/PreBusquedaGestionServiciosAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      				
      					<tr>
       							<td><b><bean:message key='txt.preus.filtro.centro.cso'/></b></td>
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
       								
       							<td><b><bean:message key='txt.preus.filtro'/></b></td>
         						<td colspan="3">
									<html:select property="f_srv" styleId="f_srv" errorStyleClass="error" style="width: 325px;">
										<html:option value="0">
												<bean:message key="consulta.gestServ.todos" />
										</html:option>
										<logic:notEmpty name="srvs" >  
                 								<html:options collection="srvs" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty>
                 						                            
                					</html:select>
           						</td>         						
         						<td  align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="getPreu()" styleClass="boto"  > 
                 							<bean:message key='txt.preus.filtro.ok'/>
              						</html:button> 
         						</td>
        					</tr>
      				</table>
  				</fieldset>        
  		</html:form>

	</div>
</div> 
  