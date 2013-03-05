<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<style>
<!--
.divCargando{
	position:absolute;
	left:0px;
	top:0px;
	background-color:#ffffff;
	opacity:0.5;
	filter:alpha(opacity=50);
	z-index:15;
}

.divCargandoImg{
	text-align: center;
	border:#a5acb2 1px solid;
	background-color:#ffffff;
	position:absolute;
	left:500px;
	top:300px;
	width:200px;
	height:40px;
	padding:20px;
	z-index:16;
}

-->
</style>

<div  id="divCargando" class="divCargando">
</div>
<div id="divCargandoImg" class="divCargandoImg">	
  <img src="/ParticipadasIntosWeb/web/img/modelos/grafico-cargador-automatico.gif" style="vertical-align: middle" alt="	<bean:message key='txt.cargando'/>" /> &nbsp;<bean:message key="txt.cargando"/>
</div>
