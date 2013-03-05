<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<c:if test="${user.centro_con != 5147}">
	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		Gestión Facturas
  	</h3>
		<logic:notEmpty name="user" >      
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0;" >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
              							<a href="JavaScript:goTo('/${initParam.context}/PreBusquedaGestionServiciosAction.do')">
                							Gestión de facturas generadas
              							</a>
            						</li>          
          						</ul>
        					</div>
      					</td>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					
      					</td>
    					</tr>
    					<tr>
      					<%-- <td style="border: 0 0 0 0;" >
								<c:if test="${BLOC2=='S'}">
        					<div class="llistat-links-contingut">
          					<ul>
            						<li style="font-size: 0.8em;">
            							<a href="JavaScript:goTo('/${initParam.context}/consulta/PreConsultaAction.do')">
                							<bean:message key="txt.portal.gestio.txtOpcion1.2"/>
                						</a>
            						</li>          
          					</ul>
        					</div>
    						</c:if>
      					</td> --%>
      				</tr>
    				</table>
		</logic:notEmpty>
		</div>
		
		<c:if test="${user.perfil eq perfil_administrador}">		
	<div class="contenidor menu">          		
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.portal.administracio.txtTit1"/>
  	</h3>
		<logic:notEmpty name="user" >      
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" > 				
    					<tr>
      					<td style="border: 0 0 0 0;" >
        					<div class="llistat-links-contingut">
          					<ul>
            						<li style="font-size: 0.8em;">
            							<a href="JavaScript:goTo('/${initParam.context}/PortalAdministacionAction.do')">
                							<bean:message key="txt.portal.administracio.txtTit1"/>
                						</a>
            						</li>          
          					</ul>
        					</div>
      					</td>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					<img src="/ParticipadasIntosWeb/web/img/icons/admin/64/gear_edit.png" />
      					</td>
      				</tr>
    				</table>
		</logic:notEmpty>
    </c:if>
	</div>
</c:if>
