<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

		<logic:notEmpty name="paginacion" >
				<bean:define id="paginacion" name="paginacion" />
		</logic:notEmpty>


  	<table style="width:100%" class="noborder">
    		<tr style="background-color:rgb(221,221,221);background-image:none;">
       		<td  class="esq" style="height: 30px;vertical-align: middle;color:rgb(95,95,95);font-weight:bold;font-size:14pt;">
       					<bean:message key="manteniments.opcio3.rejilla"/>
       			</td>
       			<td width="100px" class="dre" style="height: 30px;vertical-align: middle">
       					<bean:message key="txt.registros"/>:${paginacion.filas}
       			</td>
       			<td width="20px" class="dre" style="height: 30px;vertical-align: middle;display:none">       					
       					<img  src="/ParticipadasIntosWeb/web/img/icons/excelgray.png" alt="Excel">
       			</td>
       	</tr>
   	</table>
   	<div id="divScroll"  style="border-top:#a5acb2 1px solid;border-bottom:#a5acb2 1px solid"  style="width:100%;height:200px;overflow-y:scroll;" >
    		<small>
        		<table class="seleccio" id="users_cso" style="width:100%;border:#a5acb2 0px solid">
            		<thead>
              			<tr>
                  			<th class="checkVariat headfirst" style="display:none;">
                  	 				&nbsp;
                  			</th>                  			

			                  <th class="cen"  >
			                  		<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[0]}">
			                    			<a href="javascript:doOrderBy('${OrderBy_DESC[0]}')" class="ascendent" > 
			                    	</logic:equal>
			                     	<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[0]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" class="descendent" >
			                     	</logic:equal>              
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[0]}">
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[0]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[0]}')" >  
			                     	</logic:notEqual>
			                     	</logic:notEqual>
			                    			<bean:message key="manteniments.usuari"/>
			                     	</a>
			                  </th>
		                  
			                  <th class="cen" style="border-left: white 2px solid"  >
			                  		<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[1]}">
			                    			<a href="javascript:doOrderBy('${OrderBy_DESC[1]}')" class="ascendent" > 
			                    	</logic:equal>
			                     	<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[1]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" class="descendent" >
			                     	</logic:equal>              
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[1]}">
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[1]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[1]}')" >  
			                     	</logic:notEqual>
			                     	</logic:notEqual>
			                    			<bean:message key="manteniments.nomusuari"/>
			                     	</a>
			                  </th>
			                  
			                  <th class="cen" style="border-left: white 2px solid;"  >
			                  		<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[2]}">
			                    			<a href="javascript:doOrderBy('${OrderBy_DESC[2]}')" class="ascendent" > 
			                    	</logic:equal>
			                     	<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[2]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" class="descendent" >
			                     	</logic:equal>              
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[2]}">
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[2]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[2]}')" >  
			                     	</logic:notEqual>
			                     	</logic:notEqual>
			                    			<bean:message key="manteniments.pcognom"/>
			                     	</a>
			                  </th>       
			                  
			                  <th class="cen" style="border-left: white 2px solid;"  >
			                  		<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[3]}">
			                    			<a href="javascript:doOrderBy('${OrderBy_DESC[3]}')" class="ascendent" > 
			                    	</logic:equal>
			                     	<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[3]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" class="descendent" >
			                     	</logic:equal>              
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[3]}">
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[3]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[3]}')" >  
			                     	</logic:notEqual>
			                     	</logic:notEqual>
			                    			<bean:message key="manteniments.scognom"/>
			                     	</a>
			                  </th>                      
			                  
			                  <th class="cen" style="border-left: white 2px solid;"  >
			                  		<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[4]}">
			                    			<a href="javascript:doOrderBy('${OrderBy_DESC[4]}')" class="ascendent" > 
			                    	</logic:equal>
			                     	<logic:equal name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[4]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" class="descendent" >
			                     	</logic:equal>              
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_ASC[4]}">
			                     	<logic:notEqual name="GestioUsuarisForm" property="order_by" value="${OrderBy_DESC[4]}">
			                     		<a href="javascript:doOrderBy('${OrderBy_ASC[4]}')" >  
			                     	</logic:notEqual>
			                     	</logic:notEqual>
			                    			<bean:message key="manteniments.perfil"/>
			                     	</a>
			                  </th>           
              			</tr>
            		</thead>
								<logic:notEmpty name="paginacion" >
										<logic:iterate id="oo" collection="${paginacion.todaPagina}" indexId="numFila" >
												<bean:define id="esPar" value="${numFila%2}" />
												<logic:equal name="esPar" value="0" >
														<tr class='parell' id="${numFila}" style="cursor:hand" onclick="doSelect('${numFila}','${oo.matricula}','${oo.perfil}')">
												</logic:equal>
												<logic:notEqual name="esPar" value="0" >
														<tr style="cursor:hand" id="${numFila}" onclick="doSelect('${numFila}','${oo.matricula}','${oo.perfil}')">
												</logic:notEqual>
												<td class="esq" >
 														<bean:write name="oo" property="matricula" />
												</td>
												<td class="esq" style="border-left:#dcdcdc 2px solid;">
 														<bean:write name="oo" property="nomUsuari" />
												</td>
												<td class="esq" style="border-left:#dcdcdc 2px solid;">
 														<bean:write name="oo" property="pcognomUsuari" />
												</td>
												<td class="esq" style="border-left:#dcdcdc 2px solid;">
 														<bean:write name="oo" property="scognomUsuari" />
												</td>
				    						<td class="esq" style="border-left:#dcdcdc 2px solid;">
 														<bean:write name="oo" property="perfildesc" />
												</td>
		    								</tr>
		  							</logic:iterate>
								</logic:notEmpty>
         		</table>
      	</small>
   	</div>
   	