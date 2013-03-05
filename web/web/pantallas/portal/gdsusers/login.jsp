<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
		<html:form  styleId="LoginForm"  action="/gdsusers/loginAction" method="post">
    
    <div class="loginDiv" onKeyPress="return checkSubmit(event)">
        
        <div class="bandaNegra">
            <table class="cajaLogin">
                <tr>
                    <td class="AnchoLabel">
                    	<bean:message key="txt.usuario"/>
                    </td>
                    <td>
                        <html:text  property="f_user" size="16" maxlength="15"/>
                    </td>
                </tr>
                <tr>
                    <td class="AnchoLabel"> 
                    	<bean:message key="txt.pass"/>
                    </td>
                    <td>
                        <html:password name="LoginForm" property="f_pass" size="16" maxlength="15"/>
                    </td>
                </tr>
            </table>
            <div class="buttonLogin" style="right:126px;">
            	<html:button property="aceptar" onclick="loginInicio()" ><bean:message key="txt.btn.inicio"/></html:button>
            </div>
        </div>
        <div class="error">
					<center class="ocultoPrint" >
							<div id="div_INFO_WARN_ERROR" style="width:100%;text-align:left;margin-top:15px;"  >
		        			<html:errors  header="login.errors.header.sin.scroll" footer="login.errors.footer" prefix="login.errors.prefix" suffix="login.errors.suffix" />
							</div>
					</center>
        </div>
    </div>
    <html:hidden property="f_centre" value="5316" />
    </html:form>