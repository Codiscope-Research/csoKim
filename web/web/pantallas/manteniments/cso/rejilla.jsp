<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<span id="nameCSO" ><bean:message key='txt.cso.title.rejilla'/> </span>
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			 
       	  </td>
       
         </tr>
         <tr>
         </tr>
    </table>
   <div style="height: 300px;"  >
   <br>
   <br>
      <small>
      <div style="width: 350px; position: absolute; left:100px; top: 100px;  " >
      	<table class="taula noborder" style="font-size: 1.2em;" >
      		<tr>
	      	<td><b><bean:message key='txt.cso.name'/>:</b></td><td colspan="4" ><input type="text" id="descripcio" size="41"  class="info" onfocus="blur()" /></td>
	      	</tr>
	      	<tr>
	      		<td colspan="4" >&nbsp;</td>
	      	<tr>
	      	<tr>
	      		<td colspan="4" >&nbsp;</td>
	      	<tr>
	      	<tr>
	      	<td colspan="3" ><b><bean:message key='txt.cso.iva'/></b>:</td><td><input type="checkbox" id="iva" ></input></td>
	      	<td><b><bean:message key='txt.cso.descuento'/>(%)</b>:</td><td  colspan="2" ><input type="text" id="descuento" size="21"   style="" onblur=" onlyDouble(this.value, this.id)"  /></td>
	      	
	      	</tr>
	      	<tr>
	      	<td colspan="3" ><b><bean:message key='txt.cso.igic'/></b>:</td><td><input type="checkbox" id="igic" ></input></td>
	      	<td><b><bean:message key='txt.cso.impuesto'/>(%)</b>:</td><td colspan="2" ><input type="text" id="impuesto" size="21"  style="" onblur=" onlyDouble(this.value, this.id)"  /></td>
	      	</tr>
	      	<tr>
	      		<td colspan="4" >&nbsp;</td>
	      	<tr>
	      	<tr>
	      	<td colspan="3" ><b><bean:message key='txt.cso.facturacio'/></b>:</td><td colspan="2" >
																		<select id="servicioRecurso" style="width: 125px;" onchange="openDiv(this.value)" >
																			<option value="0">&nbsp;</option>                 										                      
																			<option value="servei"><bean:message key='txt.cso.select.servei'/></option>
																			<option value="recurs"><bean:message key='txt.cso.select.recurs'/></option>
	               														</select>     
	               														 						
	      													  </td>
			<td colspan="3" >
					<div id="recurs_div">
		               														<table class="noborder" >
		               														
		               															<tr>
		               																<td colspan="2" >
		               																	<b><bean:message key='txt.cso.num.recurs'/>:</b>
		               																</td>
		               																<td>
		               																	<input type="text" id="nrecursos" value="" onblur=" onlyDouble(this.value, this.id)" />
		               																</td>
		               															</tr>
		               															<tr>
		               																<td colspan="2" >
		               																	<b><bean:message key='txt.cso.preu.recurs'/>:</b>
		               																</td>
		               																<td>
		               																	<input type="text" id="preurecursos" value="" onblur=" onlyDouble(this.value, this.id)" />
		               																</td>
		               															</tr>
		               														</table>	               															
	               														</div>
			</td>	      												
			</tr>	
			<tr>
				<td></td>
				<td></td>
				<td></td>
				
				<td colspan="5" style="text-align:right;" >
									<html:button style="text-align: right;" altKey="txt.btn.filtrar" property="btnFiltrar" onclick="saveCso()" styleClass="boto" > 
                 							<bean:message key='txt.btn.guardar'/>
              						</html:button> 
				</td>
			</tr>      													  
      	</table>
       </div>
   </div>
               	         
    </small>
</div>
