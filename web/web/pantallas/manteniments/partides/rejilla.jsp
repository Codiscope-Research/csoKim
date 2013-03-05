<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <table class="noborder"  style="background-color:rgb(192,192,192)">
         <tr>
          <td colspan="3" >
         	     <h1 class="capcalera" style="display:inline;padding:0px">
         	        <img src="/ParticipadasIntosWeb/web/img/luna-grey-icons/png/24x24/puzzle.png" style="vertical-align:middle">
         	        &nbsp;<bean:message key='txt.partida.title.rejilla'/> 
         	     </h1>
    	  </td>
       	  <td width="200px" class="dre" style="height: 20px;vertical-align:top;">
       			 
       	  </td>
       
         </tr>
         <tr>
         </tr>
    </table>
   <div style="height: 300px;"  >
   <span id="namePartida" > <h3></h3></span>
   <br>
   <br>
      <small>
      <div style="width: 350px; position: absolute; left:100px; top: 100px;  " >
        <table class="selecciom dataTable"  id="srv_partida" style="width:100%">
       <thead>
              <tr>                              
                  <th class="firstheader" ><a><bean:message key='txt.partida.srv'/></a></th>                                      
              </tr>
       </thead>
       <tbody>
             
       </tbody>
       </table>
       </div>
        
        <div style="width: 350px; position: absolute; left: 480px; top: 100px;  " >
        <table class="selecciom dataTable"  id="srv_disp" style="width:100%">
       <thead>
              <tr>                              
                  <th class="firstheader" ><a><bean:message key='txt.partida.srv.disponibles'/></a></th>                                    
              </tr>

            </thead>
       <tbody>             
       </tbody>
       </table>
       </div>
               	         
    </small>
</div>
