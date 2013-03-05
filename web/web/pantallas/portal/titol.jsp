<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>


    <div class="contenidorportal menuportal" style="margin-top=10px;" align="left">
      <h3 class="titol-contenidor">
        <center>
          <bean:message key="aplicatiu.titol"/>
        </center> 
      </h3>
    </div>
    <script languaje="JavaScript">
      $("div.contenidorportal", "div").append('<div class="se"><\/div><div class="sd"><\/div><div class="ie"><\/div><div class="id"><\/div>');
    </script>
    
    <div class="component usuario" align="left" style="margin-top:10px">
     <bean:write name="user" property="numEmp" /> - Oficina: <bean:write name="user" property="ENumCen"/>
      <!--<bean:write name="user" property="nomCognomsNumCentre" />-->
    </div>
    <div style="margin-left:0px;margin-top:-35px;width:100%;
            <logic:equal  name="modOficina" value="1">
display:none;
            </logic:equal>
            <logic:notEqual  name="modOficina" value="1">
display:block;
            </logic:notEqual>            
">
      <form id ="formCentro" name="frmCentro" class="vertical" method="post">
        <fieldset class="etiqueta-petita">
          <p align="center">
            <label style="font-size: 0.8em;" for="titularitat"><b>
               <bean:message key="txt.portal.inputs.centro"/>
            </b></label>
            <input style="font-size: 0.8em;" name="tfOfi"  type="text" size="5" maxlength="5" value="<bean:write name="user" property="ENumCen"/>">                              
          </p>
        </fieldset>
      </form>
    </div>