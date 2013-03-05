<%@ page import="org.apache.struts.Globals" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html>
    <head>
        <title><bean:message key="error.titol.finestra"/></title>
        <html:base/>
    
        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components.css" type="text/css"   media="screen" />
        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-personalitzats.css" type="text/css"   media="screen" />
        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/estructuraGuia.css" type="text/css"   media="screen" />
        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/components-ie.css"  type="text/css"  media="screen" />
        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/Intos.css"  type="text/css" media="screen" />    

        <link rel="stylesheet" href="/ParticipadasIntosWeb/web/css/portal.css" type="text/css"   media="screen" />   
        <!--[if IE]>
          <link href="/ParticipadasIntosWeb/web/css/portal-ie.css" rel="stylesheet" media="screen" type="text/css" />
        <![endif]-->	
<%--        <link rel="stylesheet" href="/SegurSS/web/css/segurss.css" type="text/css"   media="screen" />   
--%>
        <script src="/ParticipadasIntosWeb/web/js/3rd-party-j.js" type="text/javascript"></script>
        <script src="/ParticipadasIntosWeb/web/js/tf7-j.js" type="text/javascript"></script>              
    </head>


    <body>

    
<%-- begin : linea azul principal --%>
<br>
	<div class="contenidorportal menuportal" style="margin-top:50px;margin-bottom:40px;">
		<h3 class="titol-contenidor"><center><bean:message key="error.titol.pantalla"/></center> </h3>
	</div>
	<script languaje="JavaScript">
		$("div.contenidorportal", "div").append('<div class="se"><\/div><div class="sd"><\/div><div class="ie"><\/div><div class="id"><\/div>');
	</script>
<%-- end : linea azul principal --%>



	<div class="component">
		<p class="error-proces"><bean:message key="error.webapp.exceptio"/></p>
	</div>

	<%--<jsp:useBean id="error" class="java.lang.Throwable" scope="request"></jsp:useBean>
	<div class="component">
		<p class="error-proces"><B><c:out value="<%=error.toString().substring(error.toString().indexOf(":")+1) %>"/></B></p>
	</div>--%>
    

<%--    
    <logic:present name="<%=Globals.EXCEPTION_KEY%>">
        <p><bean:write name="<%=Globals.EXCEPTION_KEY%>"
                       property="message"/></p>
    </logic:present>
--%>

    
           <html:errors  header="login.errors.header" footer="login.errors.footer" prefix="login.errors.prefix" suffix="login.errors.suffix" />


		<div class="llistat-links">
                   <ul>
                        <li><a href="javascript:history.go(-1);" > <bean:message key="error.alink.volver"/> </a></li>
                   </ul>
                </div>
           
<%-- begin : linea azul final --%>
	<div style="margin-top:25px;">
		<h3 class="subtitol-pagina">
<bean:message key="error.msg.volver"/>		
		</h3>
	</div>
<%-- end : linea azul final --%>           
           
    </body>
</html:html>