<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.facturacion.rejilla.title"/>
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			    <b style="font-size:0.9em;color:black"><bean:message key="txt.total.registros"/>: 
       			    	<label id="iTotalRecords" ></label>	
       			    </b>
       	  </td>
       	  <td width="20px" class="dre" style="height: 20px;vertical-align:top">       					
       			<div id="btnExcel">       			
       			</div>
       		</td>
         </tr>
         <tr>
         </tr>
    </table>
   <div>
      <small>
   	   <table class="selecciom dataTable"  id="partidas_result" style="width:100%">
       <thead>
              <tr>              
                  <th class="firstheader" ><a href="#" ><bean:message key="txt.facturacion.table.partida" />&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</a></th>                  
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
        <br>
        <br>
        <table class="selecciom dataTable"  id="facpart_result" style="width:70%">
       <thead>
              <tr>
                  <th class="firstheader" ><a href="#"  ><bean:message key="txt.facturacion.table.second.partida" />&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</a></th>                  
                  <th><a href="#" ><bean:message key="txt.facturacion.table.second.facturacion" /></a></th>                  
                  <th><a href="#" ><bean:message key="txt.facturacion.table.second.facturacion.estimada" /></a></th>
  				  <th><a href="#" ><bean:message key="txt.facturacion.table.second.facturacion.consumida" /></a></th>                 
                  <th><a href="#" ><bean:message key="txt.facturacion.table.second.rentabilidad" /></a></th>                                    
              </tr>
       </thead>
       <tbody>
       		
       </tbody>
       </table>
        
    </small>
</div>
   
   


   
   
    

    

   
