<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key='txt.partidas.title.filtro'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch" style="text-decoration: none ">
     			<bean:message key='txt.partidas.title.filtro.second'/>
  		</span>
  		<html:form  styleId="BusquedaGestionServiciosForm" action="/PreBusquedaGestionServiciosAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      				
      					<tr>
       							<td><b><bean:message key='txt.partida'/></b></td>
         						<td>
										<html:select property="f_cso"  onchange="reloadTables(this.value);" styleId="partidasel" errorStyleClass="error" style="width: 325px;" >
											<html:option value="">
										
											</html:option>
											<logic:notEmpty name="listaPartidas" >  
                 					    		<html:options collection="listaPartidas" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty>
										
                						</html:select>
           						</td>               								       							 
       				   </tr>      				             				      			        			       						
       					
        					<tr>
         				
        					</tr>
      				</table>
  				</fieldset>        
  		</html:form>
			
	</div>
</div> 
  