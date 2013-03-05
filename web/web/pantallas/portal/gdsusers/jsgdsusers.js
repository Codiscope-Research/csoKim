
function inicio() {
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
 }//end inicio

function loginInicio(){
	var frm=document.getElementById('LoginForm');
	frm.submit();
	waiting();
}
function checkSubmit(e)
{
   if(e && e.keyCode == 13)
   {
	   loginInicio();
   }
}
