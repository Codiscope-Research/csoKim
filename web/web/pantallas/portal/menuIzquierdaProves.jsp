<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>


	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.menu.title1"/> 
  	</h3>
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0; width: 400px; "  >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
              							<a href="JavaScript:goTo('/${initParam.context}/PreBusquedaGestionFacturasAction.do')">
                							<bean:message key="txt.menu.title1.1"/>
              							</a>
            						</li>          
          						</ul>
        					</div>
      					</td>
      					<c:if test="${user.perfil=='gdscusa'}">
      					<td style="border: 0 0 0 0">
          					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
              							<a href="JavaScript:goTo('/${initParam.context}/ControlAction.do')">
                							<bean:message key="txt.menu.title1.2"/>
              							</a>
            						</li>          
          						</ul>
        					</div>
      					</td>
      					</c:if>
    					</tr>
    					<tr>
      				
      				</tr>
    				</table>
		</div>
		
<c:if test="${user.perfil=='gdscusa'}">
	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.menu.title2"/>
  	</h3>
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0;" width="200px" >
        					<div class="llistat-links-contingut">
          						<ul style="text-align: top;" >
            						<li align="top" style="font-size: 0.8em;text-align: top; align: top;">
            							
	              							<a href="JavaScript:goTo('/${initParam.context}/PreGenerarFacturaAction.do')">
	                							<bean:message key="txt.menu.title2.1"/>
	                						</a>
                						             							              						
            						</li>  
            				
            					      
          						</ul>
        					</div>
      					</td>
      					<logic:notEmpty name="newdata" >
      						<logic:equal name="newdata" value="true">
		      					<td style="border: 0 0 0 0; text-align: top">
		      						<img src="\ParticipadasIntosWeb\web\img\icons\grey\warning.png" width="20px" height="20px" align="bottom" /> &nbsp; 
		      						<bean:message key="txt.menu.alert"/>	      					
		      					</td>
	      					</logic:equal>
      					</logic:notEmpty>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					
      					</td>
    					</tr>
    					<tr>      				
      				</tr>
    				</table>
		</div>
		
	</div>
		
		<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.menu.title3"/>
  	</h3>
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0;" >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
            					
              							<a href="JavaScript:goTo('/${initParam.context}/FacturacionPartidasAction.do')">
                							<bean:message key="txt.menu.title3.1"/>
              							</a>
              						             						          							                			
            						</li>          
          						</ul>
        					</div>
      					</td>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					
      					</td>
    					</tr>    					
    					<tr>      					
      				</tr>
    				</table>
		</div>
	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.menu.title4"/>
  	</h3>
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0; width: 400px;" >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
            						
	              							<a href="JavaScript:goTo('/${initParam.context}/PartesAction.do')">
	                 							<bean:message key="txt.menu.title4.1"/>                 							
	              							</a>
              						
            						</li>          
          						</ul>
        					</div>
      					</td>
      				<td style="border: 0 0 0 0">
          					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
            							
	              							<a href="JavaScript:goTo('/${initParam.context}/PreExcelPagadesAction.do')">
	                							<bean:message key="txt.menu.title4.2"/>
	              							</a>
              							
              							
            						</li>          
          						</ul>
        					</div>
      					</td>
    					</tr>    					
    					<tr>      					
      				</tr>
    				</table>
		</div>
	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		 <bean:message key="txt.manteniments.menu.serv" />
  	</h3>
 
  						<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0;" >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
            					
              							<a href="JavaScript:goTo('/${initParam.context}/admin/MantenimentServAction.do')">
                							<bean:message key="txt.manteniments.menu.serv.info"/>
              							</a>
              						             						          							                			
            						</li>          
          						</ul>
        					</div>
      					</td>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					
      					</td>
    					</tr>    					
    					<tr>      					
      				</tr>
    				</table>
		</div>
	<div class="contenidor menu">
  	<h3 style="font-size: 0.8em;" class="titol-contenidor">
   		<bean:message key="txt.menu.title5"/>
  	</h3>
  					<table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
    					<tr>
      					<td style="border: 0 0 0 0;" >
        					<div class="llistat-links-contingut">
          						<ul>
            						<li style="font-size: 0.8em;">
            							
              							<a href="JavaScript:goTo('/${initParam.context}/PortalAdminAction.do')">
                							<bean:message key="txt.menu.title5.1"/>
              							</a>
              							
              							
            						</li>          
          						</ul>
        					</div>
      					</td>
      					<td style="border: 0 0 0 0" width="10%" rowspan="2">
          					
      					</td>
    					</tr>
    					<tr>      					
      				</tr>
    				</table>
		</div>
	
	</div>
		
		</c:if>