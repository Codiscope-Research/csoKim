<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
 <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
         
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key='txt.serv.title.rejilla'/> 
         	     </h1>
    	  </td>
       	  <td width="20px" class="dre" style="height: 20px;vertical-align:middle;">       					
       			<div id="btnExcel">   
       				<a href="#" onclick="generaExcelServeis();" ><img src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" /></a>    			
       			</div>
       		</td>	
       
         </tr>
        
    </table>
	<div id="divScroll"  style="border-top:#a5acb2 1px solid;border-bottom:#a5acb2 1px solid; height:300px; width:100%;overflow-y:scroll;" >
   

      <small>
    
      
        <table class="selecciom dataTable"  id="srv_partida" style="width:100%, overflow: scroll; border:#a5acb2 0px solid">
       <thead style="position: fixed;" >
              <tr style="height: 15px;" >                              
                  <th class="firstheader"  style="width: 340px;" >
	                   <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[0]}">
	                     <a href="javascript:doOrderBy('${OrderBy_DESC[0]}')" class="ascendent" > 
	                     </logic:equal>
	                     <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[0]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" class="descendent" >
	                     </logic:equal>              
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[0]}">
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[0]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" >  
	                     </logic:notEqual>
	                     </logic:notEqual>
	                     
	                  <bean:message key='txt.serv.actual'/>
                  </th>                                      
                  <th style="width: 338px;"  >
                  	 <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[1]}">
	                     <a href="javascript:doOrderBy('${OrderBy_DESC[1]}')" class="ascendent" > 
	                     </logic:equal>
	                     <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[1]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" class="descendent" >
	                     </logic:equal>              
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[1]}">
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[1]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" >  
	                     </logic:notEqual>
	                     </logic:notEqual>
	                     
                      <bean:message key='txt.serv.ant'/>
                   </th>
                   <th style="width: 193px;"  >
                   <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[2]}">
	                     <a href="javascript:doOrderBy('${OrderBy_DESC[2]}')" class="ascendent" > 
	                     </logic:equal>
	                     <logic:equal name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[2]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" class="descendent" >
	                     </logic:equal>              
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_ASC[2]}">
	                     <logic:notEqual name="BusquedaComparativaServeisForm" property="order_by" value="${OrderBy_DESC[2]}">
	                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" >  
	                     </logic:notEqual>
	                     </logic:notEqual>
	                     
                   <bean:message key='txt.serv.compartiva'/>
                   </th>
              </tr>
       </thead>
       <tbody>
		    <logic:notEmpty name="paginacion" >
		  
				<logic:iterate id="oo" collection="${paginacion.todaPagina}" indexId="numFila" >
				
				  <bean:define id="esPar" value="${numFila%2}" />
				  <logic:equal name="esPar" value="0" >
				    <tr class='parell' style=" height: 15px;"    >
				  </logic:equal>
				  <logic:notEqual name="esPar" value="0" >
				    <tr style="height: 15px;"  >
				  </logic:notEqual>				
				    			<td class="esq intercell" style="border-left:#dcdcdc 2px solid;width: 340px;">
				      				<bean:write name="oo" property="serveiActual" />
				    			</td>
				    			<td class="esq intercell" style="width:338px" >
				    				<bean:write name="oo" property="serveiAnterior" />   				 
				    			</td>    
				    			<td class="esq intercell" style="width: 193px;" >
				      				<bean:write name="oo" property="estat" />  
				    			</td>			 						    							
				    </tr>
				</logic:iterate>
			</logic:notEmpty>
       </tbody>
       </table>         	         
    </small>
</div>