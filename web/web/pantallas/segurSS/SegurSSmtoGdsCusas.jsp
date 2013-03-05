<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>


<html>

    <body onload="document.getElementById('frmSigmaMant').submit()" >
    <form method="post" id="frmSigmaMant" action="/Subrogacion/portal.do">
    <!-- begin : opciones iniciales en el filtro -->
    <logic:notEmpty name="user" >  
    <input type="hidden" name="f_centroEntra" value="${user.ENumCen}" />          
    </logic:notEmpty>          
    <!-- end   : opciones iniciales en el filtro -->
  
    <!-- begin : control de logs -->
    <logic:notEmpty name="user" >  
    <input type="hidden" name="t_grabarlog"    value="true"  />
    <input type="hidden" name="t_usuario"      value="${user.nomApell}"  />
    <input type="hidden" name="t_matricula"    value="${user.numEmp}"  />
    <input type="hidden" name="t_centrocon"    value="${user.ENumCen}"  />
    <input type="hidden" name="t_centroentra"  value="${user.ENumCen}" />          
    <input type="hidden" name="t_tipoconsulta" value="GDSSUBROGACION" >
    </logic:notEmpty>      
    <!-- end   : control de logs -->
    </form>      
    </body>
</html>
