<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<script type="text/javascript">
</script>
	
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<bean:message key="txt.excelpagades.filtre"/>		
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch ocultoPrint" style="text-decoration: none ">
     			<bean:message key="txt.datos.para.filtrar"/>
  		</span>
  		<html:form  styleId="GeneraExcelFacturesPagadesAction" action="/GeneraFacturasPagadasExcelAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<table class="noborder" style="width:auto" >
      				
      					<tr>		
       								<td><b><bean:message key="txt.control.any"/>: </b></td>
         						<td>
										<html:select property="year" styleId="f_any" errorStyleClass="error" style="width: 125px;" >																		
											<logic:notEmpty name="years" >  
                 								<html:options collection="years" property="id" labelProperty="descripcio"  />
                 							</logic:notEmpty> 
										
                						</html:select>
           						</td>   
       					
         						<td colspan="6" align="right">
              						<html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="generaExcel();" styleClass="boto"  > 
                 							<bean:message key="txt.btn.generar.excel"/>
              						</html:button> 
         						</td>
        					</tr>
      				</table>
  				</fieldset>        
  		</html:form>
			
	</div>
</div> 
  