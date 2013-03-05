<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
<div style="margin-bottom:10px">
    <table border="0" cellspacing="0" cellpadding="0" bgcolor="#000000" >
        <tr>
            <td align="left" style="vertical-align:middle"">
                <img src="/ParticipadasIntosWeb/web/img/portal/GDSC_negatiu_color.jpg" />
            </td>
            <td  align="center" style="vertical-align:middle;width:450px"">
            		<img src="/Drako/web/img/Titulo.png" align="left"/>
            			<span class="versionapp" style="position:relative;top:22px;left:-20px">${version}</span>            		
            </td>
            <td align="right" style="vertical-align:middle;width:200px">
            	<div style="margin:-5px -10px -5px -10px;">
	            	<table style="border: 0 0 0 0" cellspacing="0" cellpadding="0" bgcolor="#000000" >
	            		<tr>
	            			<td align="center" class="usuariopagina" style="vertical-align:bottom;border: 0 0 0 0">${user.numEmp}-${user.nomApell} </td>
	            			<td align="right" rowspan="2" style="vertical-align:top;border: 0 0 0 0"><img  src="/ParticipadasIntosWeb/web/img/portal/usuario.png" /></td>
	            		</tr>
	            		<tr>
	            			<td align="center" class="cierreSesion" style="border: 0 0 0 0"><a href="#" onclick="javascript:cierraSesion()"><bean:message key="txt.cerrar.sesion"/></a></td>
	            		</tr>
	            	</table>
            	</div>
            </td>
        </tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" bgcolor="rgb(221,221,221)" style="vertical-align:middle">
        <tr>
            <td  align="center" style="vertical-align:middle;height:50px">
            	&nbsp;
            </td>
        </tr>
    </table>
    <html:form styleId="loginForm" action="/gdsusers">
    	<html:hidden property="f_pass" value=""/>
    	<html:hidden property="f_user" />
    </html:form>
    
      <form id ="formCentro" name="frmCentro" class="vertical" method="post">
            <input type="hidden" name="tfOfi"  value="<bean:write name="user" property="ENumCen"/>">
      </form>
</div>
    <script language="JavaScript">
      function cierraSesion()
      {
    		var frm = $("#loginForm")[0];
    		frm.submit();
    	  	
      }  
  </script>
