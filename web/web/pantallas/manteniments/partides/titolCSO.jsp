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
        		<B>&nbsp;</B>  
        	</td>
        	<td>
        		<B>&nbsp;</B>  
        	</td>               
        	 <td rowspan="2" align="right" style="vertical-align:middle;">
            	<a href="#" onclick="newPartida()" >
            		<img style="width: 30px;" src="/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/32x32/add.png" width="30px">
					<label style="font-size: 10px;display: block;">
						<bean:message  key="txt.butt.alta"/>
					</label>
				</a>
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
