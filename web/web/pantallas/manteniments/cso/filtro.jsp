<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key='txt.pestana.title.manteniment.cso'/>			
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
										<select id="f_cso" style="width: 325px;" >
											<option value="">
												<bean:message key="consulta.gestServ.todos" />
											</option>										
											<logic:notEmpty name="csos" >  
                 								<c:forEach items="${csos}" var="cso" >
                 									<option value="${cso.id}" >${cso.descripcio}</option>
                 								</c:forEach>
                 							</logic:notEmpty> 
										
                						</select>
           						</td>               							   					
         						<td  align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="getCso()" styleClass="boto"  > 
                 							<bean:message key='txt.preus.filtro.ok'/>
              						</html:button> 
         						</td>
        					</tr>
      				</table>
  				</fieldset>  
  		</html:form>        		
	</div>
</div> 
  