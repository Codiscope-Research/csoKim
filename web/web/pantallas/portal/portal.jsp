<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
    <meta http-equiv="imagetoolbar" content="no">

    <META http-equiv="Pragma" content="no-cache">
    <META http-equiv="Cache-Control" content="no-cache">
    <META http-equiv="Expires" content="-1">
    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css" media="screen" />
    <!--[if IE]>
      <link href="/ParticipadasIntosWeb/web/css/components-ie.css" rel="stylesheet" media="screen" type="text/css" />
    <![endif]-->

    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />   
    <link rel="stylesheet" href="/${initParam.context}/web/css/aplicatiu.css" type="text/css"   media="screen" />   
    <!--[if IE]>
      <link href="/ParticipadasIntosWeb/web/css/portal-ie.css" rel="stylesheet" media="screen" type="text/css" />
    <![endif]-->	

    <script src="/ParticipadasIntosWeb/web/js/3rd-party-j.js" type="text/javascript"></script>    	
    <script src="/ParticipadasIntosWeb/web/js/tf7-j.js" type="text/javascript"></script>    
    
   	<script src="/${initParam.context}/web/js/jsaplicatiu.js" type="text/javascript"></script> 
    <script src="/${initParam.context}/web/pantallas/portal/jsportal.js" type="text/javascript"></script>
    
    
    <script language="JavaScript">  
      var __Catala=0;
      var __Castella=1;
      var txtCentroNoAutorizado = "<bean:message key="error.centro.noautorizado"/>";
      function mirarSiAutorizado(direc,idioma)
      {
        var aplicatiu = '<bean:write name="${aplicatiu}" />';
         
        if (!eval(direc))
        {
          alert(txtCentroNoAutorizado);
        }
        return (eval(direc));
      }  
  </script>
    <title> <bean:message key="txt.portal.titulo.aplicacion"/></title>
  </head>
  
  <body onload="inicio();">
    <center>  	
    <div id="div_pantalla" style="width:950px" alig="left">
    <c:import url="/web/pantallas/titolCSO.jsp" />


    <div style="margin-top: -10px;">
      <fieldset id="caixa" class="caixa-formulari">
        <legend style="font-size: 0.8em;" align="center">GESTIÓN CENTRALIZADA DE FACTURAS DE LOS CSO</legend>
        <table cellpadding="0" cellspacing="0" border=0 style="border: 0 0 0 0;" >
          <tr>
            <td style="border: 0 0 0 0" width="25%">
              <c:import url="/web/pantallas/portal/menuIzquierdaProves.jsp" />
            </td>
            <td style="display:none;border: 0 0 0 0" width="30%">
              
            </td>
          </tr>
        </table>
      </fieldset>
    </div>
    
    <form method="post" id="frmPortal" action="">
    
	    <!-- begin : opciones iniciales en el filtro -->
	    <input type="hidden" name="f_centroEntra"  />          
	    <!-- end   : opciones iniciales en el filtro -->
	
	    <!-- begin : campos mantenidos en hidden para debugar su valor -->
	    <input type="hidden" name="PERMISO"   /> 
	    <!-- ebd   : campos mantenidos en hidden para debugar su valor -->
	    
	    <!-- begin : control de logs -->
	    <input type="hidden" name="t_grabarlog"    value="true"  />
	    <input type="hidden" name="t_usuario"      value="<bean:write name="user" property="nomEmp" /> <bean:write name="user" property="cog1Emp" />"  />
	    <input type="hidden" name="t_matricula"    value="<bean:write name="user" property="numEmp" />"  />
	    <input type="hidden" name="t_centrocon"    value="<bean:write name="user" property="ENumCen" />"  />
	    <input type="hidden" name="t_centroentra"  />          
	    <input type="hidden" name="t_tipoconsulta">          
	    <input type="hidden" name="ENumCen">          
	    <!-- end   : control de logs -->
	    
    </form> 
    <!-- begin :: formulario-->
	<c:import url="/web/pantallas/utils/waiting.jsp"/>
	<!-- end   :: formulario-->
		         
    </div>
    </center>

  </body>
</html>

