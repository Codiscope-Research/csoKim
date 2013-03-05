<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="crearFact" >
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key="txt.generar.table2.title"/>
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
     <bean:message key="txt.generar.select.factures.homonimes"/>: 
      <select id="selfacturesHomonimes" onchange="loadFacturaHomonima(this.value)" style="width: 200px;" >     			 
      </select>
    &nbsp;
    <div id="buteditfactors">
      <a href="#" onclick="openDialogFactores()" >
  		    <label for="editfacts"  ><bean:message key="txt.generar.factor.correccion"/></label>
			<img src='/ParticipadasIntosWeb/web/img/luna-blue-icons/png/24x24/edit.png' id="editfacts" style='margin-left:5px; vertical-align:middle' alt='"+initParams.txtbutFactors+"' />
	  </a>
	</div>
      <br>
   <div align="center"  style=" width: 713px; text-align: center; margin-left: 100px; margin-top: 10px;" >
      <small>   
   	   <table id="facsrv_res" class="selecciom dataTable" style="width:880px;">
       <thead>
              <tr>
                  <th class="firstheader" ><bean:message key="txt.generar.table2.th1"/></th>                  
                  <th><bean:message key="txt.generar.table2.th2"/></th>                                  
                  <th><bean:message key="txt.generar.table2.th3"/></th>                  
                                 
              </tr>

            </thead>
       <tbody>
              
       </tbody>
       </table>
       <br><br>
       		
     <div>
     <table class="noborder" >
     <tr><td>
       	<html:button altKey="txt.btn.filtrar" property="btnFiltrar"   styleClass="boto" onclick="goToInicio();" > 
                 							<bean:message key="txt.generar.but.salir"/>
              						</html:button> 
      </td><td align="right" >
       	<html:button  styleId="crearfacturabut"  altKey="txt.btn.filtrar" property="btnFiltrar" styleClass="boto" onclick="saveFactServer()"  > 
        									<bean:message key="txt.generar.but.crear"/>
             </html:button>
	  </td></tr> 
	  <tr>
	  <td colspan="2" ><bean:message key="txt.generar.srv.nota"/></td>
	  </tr>
	  <tr>
	  <td colspan="2" ><bean:message key="txt.generar.factor.correccion.info"/></td>
	  </tr>	  
      </table>
      </div>  
    </small>
</div>
</div>

    

   
   
    

    

   
