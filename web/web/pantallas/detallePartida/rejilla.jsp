<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<link rel="stylesheet" href="/${initParam.context}/web/css/cso/demo_table.css" type="text/css"   media="screen" />   
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.partida.detall.table"/>
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			    <b style="font-size:0.9em;color:black">
       			   
       			    </b>
       	  </td>
       	  <td width="20px" class="dre" style="height: 20px;vertical-align:middle;">       					
       			<div id="btnExcel">   
       				<a href="#" onclick="generaExcel();" ><img src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" /></a>    			
       			</div>
       		</td>
         </tr>
         <tr>
         </tr>
    </table>
   <div>
      <small>
      <table class="noborder" >
      <tr><td>&nbsp;</td>
      </tr>
      <tr>
      <td><b><bean:message key="txt.facturacion.filtro.ano"/> </b>: &nbsp;<select  id="f_any" errorStyleClass="error" style="width: 125px;"  onchange="reloadTableDetall()" >
											<logic:notEmpty name="years" >  
                 								<logic:iterate id="year" indexId="i" name="years"  >													
													<option value="${year.id}" >${year.id}</option>
												</logic:iterate>                   								
                 							</logic:notEmpty> 
										
                						</select>
           						</td>
	</tr>
	<tr><td>&nbsp;</td>
      </tr>
	<tr height="100px;" >
	<td colspan="2">
	       <table class="selecciom dataTable"  id="detall_partida_result" style="width:90%;height: 30%">
       <thead>
              <tr>              
                  <th class="firstheader" ><a href="#" ><bean:message key="txt.detall.partida.table.th1" />&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</a></th>                  
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes1" /></a></th>                  
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes2" /></a></th>                  
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes3" /></a></th>                  
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes4" /></a></th>                  
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes5" /></a></th>                                                 
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes6" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes7" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes8" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes9" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes10" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes11" /></a></th>
                  <th><a href="#" ><bean:message key="txt.consulta.select.mes12" /></a></th>
              </tr>

            </thead>
       <tbody>
       		
       </tbody>
       </table>    
	
	</td>
	</tr>
	</table>           						 
   	        <br>
        <br>

        
    </small>
</div>
<html:form styleId="PartidaGeneraExcelForm"
	action="/PartidaGeneraExcelAction" method="post">
	<html:hidden property="idpartida" />
	<html:hidden property="year" />
</html:form>
   


   
   
    

    

   
