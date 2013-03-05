<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key='txt.pestana.title.manteniment.importspactats.filtre'/>&nbsp;&nbsp;&nbsp;	
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch" style="text-decoration: none ">
     			
  		</span>
  		<html:form  styleId="ImportPactatForm" action="/admin/SaveImportPactatAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      					<tr>
       							<td><bean:message key='manteniment.import.pactat.any'/>:</td>
         						<td>
										<html:select property="f_any" styleId="f_any" errorStyleClass="error" style="width: 125px;" onchange="getImporte(this.value)">
										<logic:notEmpty name="years" >  
                 								<html:options collection="years" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty>              
                				     	</html:select>
           						</td>
           						<td>&nbsp;&nbsp;&nbsp;<bean:message key='manteniment.import.pactat.import'/>&nbsp;(&euro;):</td>
								<td>	
									<html:text property="importe" styleId="importe" onblur=" onlyDouble(this.value, this.id)" value="<%= request.getAttribute("importe").toString() %>" ></html:text>							
								</td>      
								<td>	<html:button altKey="txt.btn.filtrar" property="btnFiltrar" styleClass="boto" onclick="doSave();"  > 
                 							<bean:message key='txt.btn.guardar' />
              						</html:button> 
													       							 
       				   </tr>      				             				      			        			       						
       					
        			   <tr>
         						
        			   </tr>
      				</table>
  				</fieldset>        
  		</html:form>
			
	</div>
</div> 
  