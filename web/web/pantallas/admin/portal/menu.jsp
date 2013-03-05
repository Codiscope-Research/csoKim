<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

		<div id="divHeading" class="capcalera" align="left" style="margin-bottom:0px">
		<h1 class="capcalera"><img style="vertical-align:middle;" border="0" src="/${initParam.context}/web/img/admin.png" width="24px" height="24px" >&nbsp;<bean:message key="txt.portal.administracio.txtTit1.nobr"/></h1>
		</div>		

		<div class="panel menu" style="position:relative;top:-12px;" align="left">
			
			<h3 class="titol-contenidor" style="position: relative;top:-19px; " onclick="toggle('taula1')">        
	        		<bean:message key="txt.manteniments.menu.title" />
	      	</h3>
	      	<table cellpadding="0" cellspacing="0" border=0 id="taula1" class="taula" style="border:0 0 0 0;width:100%">
      			<tr>
 
      					<td style="border: 0 0 0 0;width:20%; visibility: hidden; display: none;" align="center">
      						<ul>
     									<li style="font-size: 0.8em;list-style: none;">
         									<a href="JavaScript:goTo('/${initParam.context}/admin/PreGestioUsuarisAction.do')">
        											<img src="/ParticipadasIntosWeb/web/img/icons/admin/64/data_edit.png" >
        											<br/>
        											<bean:message key="txt.portal.administracio.txtOpcion1.1"/>
      										</a>
     									</li> 
									</ul>
      					</td>
      					<td style="border: 0 0 0 0;width:20%" align="center">
      						<ul>
     									<li style="font-size: 0.8em;list-style: none;">
      											<a href="JavaScript:goTo('/${initParam.context}/admin/MantenimentPreusAction.do')">
         												<img src="/ParticipadasIntosWeb/web/img/icons/admin/64/money2_edit.png" >
         												<br/>
         												<bean:message key="txt.manteniments.menu.preus" />
       											</a>
       											</li> 
									</ul>
      					</td>
      					
		                <td style="border: 0 0 0 0;width:20%" align="center">
		                	<ul>
     									<li style="font-size: 0.8em;list-style: none;">
		                            <a href="JavaScript:goTo('/${initParam.context}/admin/MantenimentLineasAction.do')">
		                                <img src="/ParticipadasIntosWeb/web/img/icons/admin/64/relArees.png" >
		                                <br/>
		                                <bean:message key="txt.manteniments.menu.partidas" />
		                            </a>
		                            </li> 
									</ul>
		                </td>		               
		                		 					
      			
      			 		<td style="border: 0 0 0 0;width:20%" align="center">
		                	<ul>
     									<li style="font-size: 0.8em;list-style: none;">
		                            <a href="JavaScript:goTo('/${initParam.context}/admin/MantenimentImportsPactatsAction.do')">
		                                <img src="/ParticipadasIntosWeb/web/img/icons/admin/64/prevision_presupuesto.png" >
		                                <br/>
		                                <bean:message key="txt.manteniments.menu.importspactats" />
		                            </a>
		                            </li> 
									</ul>
		                </td>
		                <td style="border: 0 0 0 0;width:20%" align="center">
		                	<ul>
     									<li style="font-size: 0.8em;list-style: none;">
		                            <a href="JavaScript:goTo('/${initParam.context}/admin/GestioCSOAction.do')">
		                                <img src="/ParticipadasIntosWeb/web/img/icons/admin/64/pawn_blue_edit.png" >
		                                <br/>
		                                <bean:message key="txt.cso.title" />
		                            </a>
		                            </li> 
									</ul>
		                </td>
		                 <td style="border: 0 0 0 0;width:10%" align="center">
		                	<ul>
     									<li style="font-size: 0.8em;list-style: none;">		                           
		                            </li> 
									</ul>
		                </td>
      			</tr>
      			      	</table>         	   	      	      	      

</div>
