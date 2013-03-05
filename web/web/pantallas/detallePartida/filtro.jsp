<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<script type="text/javascript">
</script>	
<div id="divHeading" class="capcalera">
	<h1 class="capcalera">
		<img style="vertical-align:middle;" border="0" src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/zoom.png" width="24px" height="24px" >
		&nbsp;<%= request.getAttribute("nomPartida").toString() %>	
	</h1>
	<div  id="div_filtro" class="filtres filtres-oberts" > 
  		<span class="switch ocultoPrint" style="text-decoration: none ">
     			<bean:message key="txt.partida.detall.chart"/>
  		</span>
  		<html:form  styleId="BusquedaGestionFacturasForm" action="/BusquedaGestionFacturasAction" method="post" styleClass="vertical" >
  				<fieldset id="fieldset_filtro">
      				<div id="container">
    
					</div>
  				</fieldset>        
  		</html:form>
			
	</div>
</div> 
  