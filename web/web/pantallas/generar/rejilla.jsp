<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.generar.titles.part1"/>
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
   <div align="center" style=" width: 601px; text-align: center; margin-left: 180px;">
      <small>
   	   <table class="selecciom dataTable"  id="csosrv_result" style="width:100%">
       <thead>
              <tr>
                  <th class="firstheader"><a href="#" ><bean:message key="txt.generar.table1.th1"/></a></th>                  
                  <th><a href="#" ><bean:message key="txt.generar.table1.th2"/></a></th>                  
                  <th><a href="#" ><bean:message key="txt.generar.table1.th3"/></a></th>                  
                  <th><a href="#" ><bean:message key="txt.generar.table1.th4"/></a></th>                  
                                 
              </tr>
            </thead>
       <tbody>
            
       </tbody>
       </table>
        
    </small>
</div>
   
   


   
   
    

    

   
