<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<html:html lang="true"  >
<head>
    <title><bean:message key="aplicatiu.titol.finestra"/></title>
    
   
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-ie.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/css_principal.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/${initParam.context}/web/css/loginerr.css" type="text/css"   media="screen" />   
    <link rel="stylesheet" href="/${initParam.context}/web/css/aplicatiu.css" type="text/css"   media="screen" />   
    
		<script src="/ParticipadasIntosWeb/web/js/3rd-party-j.js" type="text/javascript"></script> 
   	<script src="/${initParam.context}/web/js/jsaplicatiu.js" type="text/javascript"></script> 
   	<script src="/${initParam.context}/web/pantallas/portal/gdsusers/jsgdsusers.js" type="text/javascript"></script> 
</head>
<body onload="inicio()">

    <div id="div_pantalla">
    <table width="950px" align="center" border="0" cellspacing="0" cellpadding="0" class="noborder">
            <tr>
                <td style="width: 100%">                
										<c:import url="/web/pantallas/portal/gdsusers/login.jsp"></c:import>
                </td>
            </tr>
        </table>
        
				<!-- begin :: waiting-->
					<c:import url="/web/pantallas/utils/waiting.jsp"/>
				<!-- end   :: waiting-->
    </div>
    
</body>
</html:html>
