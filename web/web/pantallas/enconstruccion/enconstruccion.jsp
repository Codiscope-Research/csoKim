<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>

<html:html lang="true"  >
<head>
    <title>En construccion</title>

    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-ie.css"  type="text/css"  media="screen" />
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/Intos.css"  type="text/css" media="screen" />    
    
    <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />   
    <!--[if IE]>
      <link href="/ParticipadasIntosWeb/web/css/portal-ie.css" rel="stylesheet" media="screen" type="text/css" />
    <![endif]-->	
    
    
    <script src="/ParticipadasIntosWeb/web/js/3rd-party-j.js" type="text/javascript"></script>

<%--    comento esta libreria pq ralentiza mucho la pagina (la tabla tiene bastantes inputs)
    <script src="/ParticipadasIntosWeb/web/js/tf7-j.js" type="text/javascript"></script>       
--%>

    <script src="/ParticipadasIntosWeb/web/js/jsUtils.js" type="text/javascript"></script>      
        






</head>


<body   >

	<div style="margin-top:100px"  class="component">
		<div class="centrador">
			<div id="confirmacio">
				<p class="titol">En construcci�n</p>
				<form class="vertical" method="post" action="#" accept-charset="utf-8">
					<p>El sitio al que intenta acceder, actualmete se encuentra en construccion o est� bajo los efectos de una reforma.</p>
					<p class="missatge-avis">Imposible realizar petici�n, p�gina en construcci�n.</p>
					<p>La url solicitada se encuentra alojada en nuestros servidores pero actualmente no est� disponible.</p>
				</form>
			</div>
		</div>
	</div>

</body>
</html:html>