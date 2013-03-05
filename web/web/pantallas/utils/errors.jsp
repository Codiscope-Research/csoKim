<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<center class="ocultoPrint" >
						<div id="div_INFO_WARN_ERROR" style="width:100%;text-align:left;margin-top:15px<c:if test="${user.acces eq  'gdsusers'}">;display:none</c:if>">
		        		<html:messages  message="true"   property="INFO" id="message" header="login.messages.header.sin.scroll" footer="login.messages.footer" >
		            		<p id="mensaje" class="missatge-info">        
		              		<bean:write name="message" />
		            		</p>
		        		</html:messages>
		
		        		<html:messages  message="true"  property="WARNING" id="message" header="login.messages.header.sin.scroll" footer="login.messages.footer" >
		            		<p id="mensaje" class="missatge-warning">        
		              		<bean:write name="message" />
		            		</p>
		        		</html:messages>
		        
		        		<html:errors  header="login.errors.header.sin.scroll" footer="login.errors.footer" prefix="login.errors.prefix" suffix="login.errors.suffix" />
						</div>
						<div id="errorsajax">
							<label style="color: red" id="errorsajaxlabel" ></label>
						</div>
				</center>
    
