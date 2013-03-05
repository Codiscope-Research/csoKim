<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp; <bean:message key="txt.rejilla.No.conformidades"/>
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
       				<a href="#" onclick="generaExcelPartes();" ><img src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" /></a>    			
       			</div>
       		</td>
         </tr>
         <tr>
         </tr>
    </table>
   <div>
      <small>
        <div style="height:308px; overflow-y: scroll;">
   	   <table class="selecciom dataTable"  id="fac_result" style="width:100%">
       <thead style="position:absolute;" >
              <tr>
                  <th class="cen firstheader" style="border-top:#a5acb2 1px solid; font-weight: bold; width: 191px; " >
                	  <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[0]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[0]}">
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[0]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  
                  
                  <bean:message key="txt.serv.rejilla.th1"/>                  
                  </th>                  
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 140px;" >
					  <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[1]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[1]}">
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[1]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
				  <bean:message key="txt.serv.rejilla.th2"/>
				  </th>                  
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold;width: 73px;" >
					
					 <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[3]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[3]}">
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[3]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
					<bean:message key="txt.serv.rejilla.th3"/>
				  </th>                  
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 100px;" >
                   <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[2]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[2]}">
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[2]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  <bean:message key="txt.serv.rejilla.th4"/>
                  </th>                  
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold; width: 126px;" >
                   <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_DESC[4]}')" class="ascendent" > 
                     </logic:equal>
                     <logic:equal name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" class="descendent" >
                     </logic:equal>              
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_ASC[4]}">
                     <logic:notEqual name="BusquedaNoConformidadesForm" property="order_by" value="${OrderBy_DESC[4]}">
                     <a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" >  
                     </logic:notEqual>
                     </logic:notEqual>
                  <bean:message key="txt.serv.rejilla.th5"/>
                  </th>                  
                  <th class="cen" style="border-top:#a5acb2 1px solid;  border-left: #ffffff 2px solid; font-weight: bold;width: 197px;">           
				  	<bean:message key="txt.serv.rejilla.th6"/>
				  </th>                                                                   
              </tr>

            </thead>
       <tbody>
        <tr><td colspan="6" height="14px;" ></td></tr>   
        <logic:notEmpty name="paginacion" >
			<logic:iterate id="oo" collection="${paginacion.todaPagina}" indexId="numFila" >
			
			  <bean:define id="esPar" value="${numFila%2}" />
			  <logic:equal name="esPar" value="0" >
			  
			    	<tr class='parell' style="cursor:hand"   >			
			   
			  </logic:equal>
			  <logic:notEqual name="esPar" value="0" >
			  
			    	<tr style="cursor:hand;"  >
			  
			
			   
			  </logic:notEqual>
					  	<c:if test="${oo.nNoConformitats !='0' }">   
			    			<td class="esq intercell" style="border-left:#dcdcdc 2px solid;width: 50px;"  onclick="javascript:goToDetall('<bean:write name="oo" property="idFactura" />')">
			      				<bean:write name="oo" property="descripcio" />
			    			</td>
			    			<td class="esq intercell" style="width:100px"  onclick="javascript:goToDetall('<bean:write name="oo" property="idFactura" />')" >
			      				<bean:write name="oo" property="cso" />  
			    			</td>	
			    			<td class="cen intercell" style="width:50px"  onclick="javascript:goToDetall('<bean:write name="oo" property="idFactura" />')" >
			      				<bean:write name="oo" property="year" />  
			    			</td>
			    			<td class="esq intercell" style="width:70px"  onclick="javascript:goToDetall('<bean:write name="oo" property="idFactura" />')" >
			    				${mesos[oo.month-1]}	      				 
			    			</td>    			    						 					    		
			    			<td class="dre intercell"  style="width:90px"  onclick="javascript:goToDetall('<bean:write name="oo" property="idFactura" />')" >
			     	 				<bean:write name="oo" property="nNoConformitats" />
			    			</td>
			    			<td class="esq intercell"  style="width:140px" >		
			    			  		<a href="#"  id="id_div_<bean:write name="oo" property="idFactura" />" onclick="openMotius('div_<bean:write name="oo" property="idFactura" />');" ><bean:message key='view.motius'/></a>			
					    			<div class="closed" id="div_<bean:write name="oo" property="idFactura" />">
					    				<table  style="border: 1px;" >
					    				<tr>
							    			<logic:iterate id="inc" name="oo" property="incidencia" indexId="ii" >
							    				- ${inc}	<br>					    			
							    			</logic:iterate>
						    			</tr>
						    			</table>
						    			<br>
					    			</div>
					    			<script type="text/javascript">
					    			function openMotius(id){
					    				if($("#"+id).hasClass("opened")){
					    					$("#id_"+id).text("<bean:message key='view.motius'/>");
					    					$("#"+id).hide();
					    					$("#"+id).removeClass("opened");
					    				}else{
					    					$("#id_"+id).text("  <bean:message key='close.motius'/>");
					    					$("#"+id).addClass("opened");
					    					$("#"+id).show();
					    				}
					    			}
					    			
					    			$("#div_"+<bean:write name="oo" property="idFactura" />).hide();
					    			</script>
					    	
			    			</td>
			    		</c:if>   
			    		 <c:if test="${oo.nNoConformitats =='0' }">   
			    			<td class="esq intercell" style="border-left:#dcdcdc 2px solid;width: 50px;">
			      				<bean:write name="oo" property="idFactura" />
			    			</td>
			    			<td class="esq intercell" style="width:50px" >
			      				<bean:write name="oo" property="cso" />  
			    			</td>	
			    			<td class="esq intercell" style="width:50px" >
			      				<bean:write name="oo" property="year" />  
			    			</td>
			    			<td class="esq intercell" style="width:70px" >
			    				${mesos[oo.month]}	      				 
			    			</td>    			    						 					    		
			    			<td class="esq intercell"  style="width:50px" >
			     	 				<bean:write name="oo" property="nNoConformitats" />
			    			</td>
			    			<td class="esq intercell"  style="width:180px" >		
			    				
			    			</td>
			    		</c:if> 
			
			    </tr>
			</logic:iterate>
		  </logic:notEmpty>
       </tbody>
       </table>
        </div>
    </small>
</div>