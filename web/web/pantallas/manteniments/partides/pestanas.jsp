<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
      <ul class="pestanyes">
        <logic:notEmpty name="user" >      
        <logic:notEmpty name="user" property="portal" >      
                <li id="pestanya.ini"><a href="${user.portal}" ><bean:message key="txt.inicio"/></a></li>      
        </logic:notEmpty>        
        </logic:notEmpty>    
        <li id="pestanya.mto" class="" ><a href="/${initParam.context}/PortalAdminAction.do"><bean:message key="txt.portal.administracio.txtTit1"/></a></li>            
       
        <li id="pestanya.mto" class="seleccionat" ><a href="#"><bean:message key='txt.pestana.title.manteniment.partidas'/></a></li>
       
      </ul>