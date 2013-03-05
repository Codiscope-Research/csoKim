<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<div id="insertOfPriceDIV" >
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key='txt.preu.title.rejilla'/>
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			 
       	  </td>
       
         </tr>
         <tr>
         </tr>
    </table>

      <small>
      <b><bean:message key='txt.preu.op.type'/>:&nbsp;&nbsp;</b>
      <b>&nbsp;<bean:message key='txt.preu.fix'/></b>  <input type="radio" name="preu" id="preuF" onclick="changePreuOpenDiv('preuVariable',this.value);" class="preu" value="preufix" >
      <b>&nbsp;<bean:message key='txt.preu.variable'/></b> <input type="radio"  name="preu" id="preuV" onclick="changePreuOpenDiv('preufix',this.value);" class="preu" value="preuVariable" >
      <b>&nbsp;<bean:message key='txt.preu.fix.mensual'/></b> <input type="radio"  name="preu" id="preuR" onclick="changePreuOpenDiv('preuMensual',this.value);" class="preu" value="preuMensual" >
       <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <bean:message key='manteniments.checkbos.tots.cso'/></b> <input type="checkbox"  name="totscso" id="totscso" class="preu"  >&nbsp;&nbsp;
      <br><br>
      
      <div id="preufix" >
	       <bean:message key='txt.preu.op'/>&nbsp;(&euro;)&nbsp;(<bean:message key="txt.pdf.senseiva"/>): <input type="text" value="" id="preufixVal" onblur="preuFix(this.value)" > <br><br>
	      <html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="savePreu()" styleClass="boto"  > 
	                 							<bean:message key='txt.btn.guardar'/>
	        </html:button> 
	     
      </div>
      <div id="preuVariable"  >
	     <br><br>
	      <table class="noborder medium"  style="width: 150px;" >
		      <tr>
			      <td>
			      <input type="button" onclick="addTram()" value="<bean:message key='manteniments.add.row'/>">
			      </td> 		      			    
			      <td>
			      <input type="button" onclick="deleteTram()"  value="<bean:message key='manteniments.delete.row'/>"> 
			      </td>
		      </tr>
	      </table>
	      <br><br>
	       <b><bean:message key='txt.preu.op.variable'/>&nbsp;(<bean:message key="txt.pdf.senseiva"/>)</b>
	       <br>
	      <table id="table_trams">
		     
	      </table>
	      <br><br>
	      <html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="savePreu()" styleClass="boto"  > 
	                 							<bean:message key='txt.btn.guardar'/>
	        </html:button> 
      </div>   	  
       <div id="preuMensual" >
	       <bean:message key='txt.preu.fix.mensual.info'/>&nbsp;(&euro;): <input type="text" value="" id="preufixMen" onblur="saveMen(this.value)" > <br><br>
	      <html:button altKey="txt.btn.filtrar" property="btnFiltrar" onclick="savePreuMensual()" styleClass="boto"  > 
	                 							<bean:message key='txt.btn.guardar'/>
	        </html:button> 
	     
      </div>
         	
    </small>
</div>