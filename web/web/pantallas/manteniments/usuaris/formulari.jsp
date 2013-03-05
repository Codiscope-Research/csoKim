<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

<!-- Hoja de estilos del calendario -->
	<link rel="stylesheet" type="text/css" media="all" href="/ParticipadasIntosWeb/web/css/calendari/calendar-blau.css" title="win2k-cold-1" />
	<!-- librería principal del calendario -->  
	<script type="text/javascript" src="/ParticipadasIntosWeb/web/js/calendari/calendar.js"></script>
	<!-- librería para cargar el lenguaje deseado -->  
	<script type="text/javascript" src="/ParticipadasIntosWeb/web/js/calendari/calendar-cat.js"></script>
	<script type="text/javascript" src="/ParticipadasIntosWeb/web/js/calendari/calendar-es.js"></script>
	<script type="text/javascript" src="/ParticipadasIntosWeb/web/js/calendari/calendar-idioma.js"></script>		
	<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
	<script type="text/javascript" src="/ParticipadasIntosWeb/web/js/calendari/calendar-setup.js"></script>
<bean:define id="user" name="user" type="es.intos.util.Usuario" scope="session" />

<div  id="div_filtro" class="filtres filtres-oberts" style="margin-top:5px" onKeyPress="return checkSubmit(event)" >  
  <html:form  styleId="GestioUsuarisForm" action="/admin/GestioUsuarisAction" method="post" styleClass="vertical" >
      <table class="noborder" style="width:99%;background-color:rgb(221,221,221);border:#a5acb2 2px solid;"> 
      		<tr>
          		<td style="width:20%">
          			<bean:message key="manteniments.usuari"/>
          		</td>
          		<td>
            			<html:text property="f_matricula" readonly="true" maxlength="10" size="12" errorStyleClass="requiredError" styleClass="info" />
            	</td>      
          	</tr>
          	<tr>
          		<td>
          				<bean:message key="manteniments.nomusuari"/>
          		</td>
          		<td>
            			<html:text property="f_nom" readonly="true" maxlength="30" size="35" 	errorStyleClass="requiredError" styleClass="info" style="text-transform:uppercase;"/>
            	</td>
          	</tr>
          	<tr>
            	<td>
          				<bean:message key="manteniments.pcognom"/>	
          		</td>
          		<td>
            			<html:text property="f_pcognom" readonly="true" maxlength="30" size="35" errorStyleClass="requiredError" styleClass="info" style="text-transform:uppercase;"/>
			        </td>
          	</tr>
          	<tr>
            	<td>
          				<bean:message key="manteniments.scognom"/>	
          		</td>
          		<td>
            			<html:text property="f_scognom" readonly="true" maxlength="30" size="35" errorStyleClass="requiredError" styleClass="info" style="text-transform:uppercase;"/>
			        </td>
          	</tr>
          	<tr>
          		<td>
          				<bean:message key="manteniments.perfil"/>
          				</td>
          		<td>
					<html:select property="f_perfil" errorStyleClass="error" >
             			<html:option value=""><bean:message key="consulta.Estat.E*"/></html:option>
             			<logic:notEmpty name="listPerfils" >  
             				<html:options collection="listPerfils" property="idPerfil" labelProperty="nomPerfil"  />
             			</logic:notEmpty>                             
            		</html:select>
              </td>
          </tr>		       		
       </table>
      <table  class="noborder" style="width:100%;height:10px;margin:0px"> 
      		<tr>
              	<td align="left" >
              		<html:button altKey="txt.btn.salir" property="btnSalir" styleClass="boto ocultoPrint" onclick="javascript:goTo('/${contexte}/portal.do')" style="width:80px;"> 
                 		<bean:message key="txt.btn.salir"/> 
              		</html:button> 
              	</td>       		
             	<td align="left" width="80%">
             		<html:button altKey="txt.btn.nuevo" property="btnNou" styleClass="boto ocultoPrint" onclick="doNou();" style="width:80px;display:none"> 
                		<bean:message key="txt.btn.nuevo"/>
             		</html:button> 
             	</td>
      					<td align="right">
             		<html:button altKey="txt.btn.insert" property="btnAceptar" styleClass="boto ocultoPrint" onclick="doAceptar();" style="width:80px;"> 
                		<bean:message key="txt.btn.aceptar"/>
             		</html:button> 
             	</td>
         	</tr>
  		</table>
	 <html:hidden property="pagina" />
	 <html:hidden property="rpp" />
	 <html:hidden property="order_by"  />      
   <html:hidden property="operacion"   />
   <html:hidden property="idTransaccion" value="${user.idTransaccion}"/>
   
  </html:form>
</div>
<html:javascript formName="GestioUsuarisForm"  dynamicJavascript="true" staticJavascript="false"/>   
                 
                         