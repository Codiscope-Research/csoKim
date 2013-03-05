<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.consulta.title.rejilla"/>
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			    <b style="font-size:0.9em;color:black"><bean:message key="txt.total.registros"/>:
       			    	<c:if test="${ empty paginacion}">
       			    		0
       			    	</c:if> 
       			    	<fmt:formatNumber value="${paginacion.filas}" pattern="#,###"/>
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
       <div id="scr" style="height:308px; overflow-y: scroll;">
   	   <table class="selecciom dataTable"  id="fac_result" style="width:100%">
       <thead style="position:absolute;  top: expression(document.getElementById('scr').scrollTop-1); z-index: 12; " >              
                  <th class="cen firstheader" style="border-top:#a5acb2 1px solid; font-weight: bold; width: 97px; "  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[0]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[0]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th1.rejilla"/>
                    
                     </a>
                  </th>
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 120px;"  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[1]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[1]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th2.rejilla"/>
                    
                     </a>
                  </th>
                      <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 88px; "  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[2]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[2]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th3.rejilla"/>
                    
                     </a>
                  </th>
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 136px;"  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[3]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[3]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th4.rejilla"/>
                    
                     </a>
                  </th>
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 248px;"  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[4]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[4]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th5.rejilla"/>
                    
                     </a>
                  </th>            
                   <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 137px; "  >
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[5]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[5]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[5]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[5]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_ASC[5]}">
                     <logic:notEqual name="BusquedaGestionFacturasForm" property="order_by" value="${OrderBy_DESC[5]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[5]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                    <bean:message key="txt.consulta.th6.rejilla"/>
                    
                     </a>
                  </th>                  
                  
              
            </thead>
       <tbody> 
       <tr><td colspan="5" height="14px;" ></td></tr>               
<logic:notEmpty name="paginacion" >
	<logic:iterate id="oo" collection="${paginacion.todaPagina}" indexId="numFila" >
	
	  <bean:define id="esPar" value="${numFila%2}" />
	  <logic:equal name="esPar" value="0" >
	    <tr class='parell' style="cursor:hand"  onclick="javascript:goToDetall('<bean:write name="oo" property="id" />')"   >
	  </logic:equal>
	  <logic:notEqual name="esPar" value="0" >
	    <tr style="cursor:hand;"   onclick="javascript:goToDetall('<bean:write name="oo" property="id" />')" >
	  </logic:notEqual>
	
	    			<td class="esq intercell" style="border-left:#dcdcdc 2px solid;width: 50px;">
	      				<bean:write name="oo" property="nomCso" />
	    			</td>
	    			<td class="esq intercell" style="width:70px" >
	    				${mesos[oo.month-1]}	      				 
	    			</td>    
	    			<td class="cen intercell" style="width:50px" >
	      				<bean:write name="oo" property="year" />  
	    			</td>			 		
	    			<td class="dre intercell"  style="width:80px" >      				
	      				<fmt:formatNumber value="${oo.importe}" pattern="##,###,##0.00"/>
	    			</td>
	    			<td class="esq intercell"  style="width:150px" >
	     	 				<bean:write name="oo" property="nomEstat" />
	    			</td>
	    				<td class="cen intercell"  style="width:80px" >
	    				<fmt:formatDate  pattern="dd-MM-yyyy" value="${oo.fentrada}" />	     	 				
	    			</td>   
	
	    </tr>
	</logic:iterate>
</logic:notEmpty>
       </tbody>
       </table>
     </div>
        
    </small>
</div>
   
   


   
   
    

    

   
