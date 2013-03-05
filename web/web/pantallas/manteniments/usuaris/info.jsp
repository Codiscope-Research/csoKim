<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>


    	
  	<table class="noborder">
  		<tr>
    			<td><b><p><span class="editar"><bean:message key="manteniments.opcio3.titol"/></span></p></b></td>
    			<td>
    					<div id="titol-pantalla">
    					<h2><span class="actiu"></span></h2>
    					<logic:notEmpty name="user" >      
    						<p><bean:write name="user" property="numEmp" /> - <bean:message key="txt.oficina"/>: <bean:write name="user" property="centro_con" /> </p>    
    					</logic:notEmpty>    
  					</div>
  				</td>
  		</tr>
		</table>