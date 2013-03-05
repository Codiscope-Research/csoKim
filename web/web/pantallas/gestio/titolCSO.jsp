<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
<div style="margin-bottom:10px">
    <table border="0" cellspacing="0" cellpadding="0" bgcolor="#000000" >
        <tr>
            <td align="left" style="vertical-align:middle"">
                <img src="/ParticipadasIntosWeb/web/img/portal/GDSC_negatiu_color.jpg" />
            </td>
            <td  align="center" style="vertical-align:middle;width:400px"">
            		<img src="/${initParam.context}/web/img/titulocso.png" style="margin-left: 70px;" align="left"/>
            			<span class="versionapp" style="position:relative;top:22px;left:-70px">${version}</span>            		
            </td>
            <td align="right" style="vertical-align:middle;width:200px">
            	<div style="margin:-5px -5px -5px -10px;">
	            	<table style="border: 0 0 0 0;border-color:#000000;" cellspacing="0" cellpadding="0" bgcolor="#000000">
	            		<tr >
	            			<td align="center" class="usuariopagina" style="vertical-align:bottom;border: 0 0 0 0;border-color:#000000;">${user.numEmp}-${user.nomApell} </td>
	            			<td align="right" rowspan="2" style="vertical-align:top;border: 0 0 0 0;border-color:#000000;"><img  src="/ParticipadasIntosWeb/web/img/portal/usuario.png" /></td>
	            		</tr>
	            		<tr>
	            			<td align="center" class="cierreSesion" style="border: 0 0 0 0;border-color:#000000;"><a href="#" onclick="javascript:cierraSesion()"><bean:message key="txt.cerrar.sesion"/></a></td>
	            		</tr>
	            	</table>
            	</div>
            </td>
        </tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" bgcolor="rgb(221,221,221)"  style="vertical-align:middle; height: 50px;">
   
        <tr>
        	<td>
        													         				     
							   <a class="avispdf" style="margin-left: 10px" href="#" onclick="creaPDF()" > <small>	<img src="/ParticipadasIntosWeb/web/img/pdf_gris.png" width="25px" ></small> </a>   
              				
        	</td>
        	<td>
        		<B>&nbsp;</B>  
        	</td>       
        	<td align="right" >
        		<logic:equal  name="user_cso"  value="gdscusa">	
        		 <logic:equal  name="frm" property="idEstat" value="2">
        		 	<a href="#" style="margin-right: 45px" onclick="doEditar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/edit.png">        			
        			</a>
        		 	<a href="#" style="margin-right: 65px"  onclick="doEnviar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" title="<bean:message key="txt.gestio.button.enviar.info"/>" src="/${initParam.context}/web/img/state_change.png">        			
        			</a>
        			
        			<a href="#" style="margin-right: 10px"  onclick="doCancel();"  >
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>
        				
  		        </logic:equal>	
  		         <logic:equal  name="frm" property="idEstat" value="3">	
        			<a href="#" style="margin-right: 10px; " onclick="doCancel();">
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>
  		        </logic:equal>	
        		<logic:equal  name="frm" property="idEstat" value="4">	
        			<a href="#" style="margin-right: 10px;" onclick="doCancel();">
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>
  		        </logic:equal>	
  		        <logic:equal  name="frm" property="idEstat" value="5">
  		        	<a href="#" style="margin-right: 45px" onclick="doEditar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/edit.png">        			
        			</a>
  		        	<a href="#" style="margin-right: 65px" onclick="doEnviar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" title="<bean:message key="txt.gestio.button.enviar.info"/>" src="/${initParam.context}/web/img/state_change.png">        			
        			</a>
        				
        			<a href="#" style="margin-right: 10px" onclick="doCancel();">
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>        	
        			 			
  		        </logic:equal>	
  		         <logic:equal  name="frm" property="idEstat" value="8">
        		 	<a href="#" style="margin-right: 45px" onclick="doEditar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/edit.png">        			
        			</a>
        		 	<a href="#" style="margin-right: 65px" onclick="doEnviar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" title="<bean:message key="txt.gestio.button.enviar.info"/>" src="/${initParam.context}/web/img/state_change.png">        			
        			</a>
        			<a href="#" style="margin-right: 10px"  onclick="doCancel();"  >
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>
        				
  		        </logic:equal>	  
  		            <logic:equal  name="frm" property="idEstat" value="9">
  		        	<a href="#" style="margin-right: 35px" onclick="doEditar();">
        				<img alt="<bean:message key="txt.gestio.button.enviar"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/edit.png">        			
        			</a>  		       
        				
        			<a href="#" style="margin-right: 10px" onclick="doCancel();">
        				<img alt="<bean:message key="txt.gestio.button.cancel"/>" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/24x24/delete.png">        			
        			</a>        	
        			 			
  		        </logic:equal> 
  		     </logic:equal>	     	
        	</td>    
        </tr>
        <tr>
        <td>
        		 <a class="avispdf" href="#" onclick="creaPDF()" > <bean:message key="txt.gestio.pdf.doc"/> </a>
        	</td>
        	<td>
        		<B>&nbsp;</B>  
        	</td>       
        	<td align="right" >
        		<logic:equal  name="user_cso"  value="gdscusa">	
        		 <logic:equal  name="frm" property="idEstat" value="2">
        		 <a href="#" onclick="doEditar();">
  		        		<bean:message key="txt.gestio.button.edit"/>        
  		        	</a>
  		        	&nbsp;&nbsp;
        		 	<a href="#" onclick="doEnviar();">
        		 <bean:message key="txt.gestio.button.enviar"/>     			
        			</a>
        			&nbsp;		
        			<a href="#" onclick="doCancel();">
        		<bean:message key="txt.gestio.button.cancel"/>       			
        			</a>
        			
  		        </logic:equal>	
  		         <logic:equal  name="frm" property="idEstat" value="3">	
  		         <a href="#" onclick="doCancel();">
        			<bean:message key="txt.gestio.button.cancel"/>        			
        			</a>
  		        </logic:equal>	
        		<logic:equal  name="frm" property="idEstat" value="4">	
        		<a href="#" onclick="doCancel();">
        			<bean:message key="txt.gestio.button.cancel"/>
        			</a>
  		        </logic:equal>	
  		        <logic:equal  name="frm" property="idEstat" value="5">
  		       		<a href="#" onclick="doEditar();">
  		        		<bean:message key="txt.gestio.button.edit"/>        
  		        	</a>
  		        	&nbsp;&nbsp;
  		        	<a href="#" onclick="doEnviar();">
  		        		<bean:message key="txt.gestio.button.enviar"/>        
  		        	</a>			
        			&nbsp;
	        		<a href="#" onclick="doCancel();">
	        				<bean:message key="txt.gestio.button.cancel"/>    
	        		</a>    			
        			    			        			
  		        </logic:equal>	
  		          <logic:equal  name="frm" property="idEstat" value="8">
  		       		<a href="#" onclick="doEditar();">
  		        		<bean:message key="txt.gestio.button.edit"/>        
  		        	</a>
  		        	&nbsp;&nbsp;
  		        	<a href="#" onclick="doEnviar();">
  		        		<bean:message key="txt.gestio.button.enviar"/>        
  		        	</a>		
        			&nbsp;
	        		<a href="#" onclick="doCancel();">
	        				<bean:message key="txt.gestio.button.cancel"/>    
	        		</a>    			
        			    			        			
  		        </logic:equal>	
  		         <logic:equal  name="frm" property="idEstat" value="9">
  		       		<a href="#" onclick="doEditar();">
  		        		<bean:message key="txt.gestio.button.edit"/>        
  		        	</a>
  		        	&nbsp;&nbsp;
  		        
	        		<a href="#" onclick="doCancel();">
	        				<bean:message key="txt.gestio.button.cancel"/>    
	        		</a>    			
	        			    			        			
  		        </logic:equal>	   
  		     </logic:equal>	     	
        	</td>    
        </tr>
    </table>
    <html:form styleId="loginForm" action="/gdsusers">
    	<html:hidden property="f_pass" value=""/>
    	<html:hidden property="f_user" />
    </html:form>
</div>
    <script language="JavaScript">
      function cierraSesion()
      {
    		var frm = $("#loginForm")[0];
    		frm.submit();
    	  	
      }  
  </script>
