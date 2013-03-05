<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="crearFact" >
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.generar.table2.title"/> <br>
         	        <bean:message key="txt.midificar.title.estado"/>:<%= request.getAttribute("factestat") %>	
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			   
       	  </td>
       	  <td width="20px" class="dre" style="height: 20px;vertical-align:top">       					
       	
       		</td>
         </tr>
         <tr>
         </tr>
    </table>
     <br>
     <a href="#" onclick="openDialogFactores()"  style="text-align: left;" >
			<bean:message key="txt.modificar.factors.correccio"/>: <img src='/ParticipadasIntosWeb/web/img/luna-blue-icons/png/24x24/edit.png' style='margin-left:5px; vertical-align:middle;' alt='<bean:message key="txt.modificar.factors.correccio"/>'  />
	  </a>
	<br>
	<br>		
   <div align="center"  style=" width: 688px; text-align: center; margin-left: 145px;" >
    
      <small>
   	   <table id="facsrv_res" class="selecciom dataTable" style="width:700px;">
       <thead>
              <tr>
                  <th class="firstheader" ><bean:message key="txt.generar.table2.th1" /></th>                  
                  <th><bean:message key="txt.generar.table2.th2"/></th>                                  
                  <th><bean:message key="txt.generar.table2.th3"/></th>                  
                                 
              </tr>

            </thead>
       <tbody>
              
       </tbody>
       </table>
       <br><br>
       		
     <div>
     <table class="noborder">
     <tr><td>
       	<html:button altKey="txt.btn.filtrar" property="btnFiltrar"   styleClass="boto" onclick="goToInicio();" > 
                 							<bean:message key="txt.generar.but.salir"/>
              						</html:button> 
      </td><td align="right" >
       	<html:button altKey="txt.btn.filtrar" property="btnFiltrar" styleClass="boto" onclick="saveFactServer()"  > 
        									<bean:message key="txt.modif.but.guardar"/>
              						</html:button>
	  </td></tr> 
      </table>
      </div>  
    </small>
</div>
</div>