
//===================================================================================
// literales para funciones :: Funciones validacion de password
//===================================================================================


//===================================================================================
//Funciones 
//===================================================================================
function inicio() {      
	hideLoad();
	
}//end inicio

function hideLoad(){
	try{
		$("#divCargando").hide();
		$("#divCargandoImg").hide();
	}catch (e){
		alert(e.description);
	}
}

function checkSubmit(e)
{
   if(e && e.keyCode == 13)
   {
	   doFiltro();
   }
}

function goTo(action){
	var frm = $("#frmPortal")[0];
	frm.action=action;
	frm.submit();
	waiting();
	}
/*function centroCorrecto(idioma)
{
  var centro=document.frmCentro.tfOfi.value;
  if (trim(centro).length==0)
  {
    switch (idioma)
    {
      case 0: alert("Centre per la consulta: camp obligatori");break;
      case 1: alert("Centro para la consulta: campo obligatorio");break;
    }
    document.frmCentro.tfOfi.focus();
    return (false);
  }
  if (isNaN(centro))
  {
    switch (idioma)
    {
      case 0: alert("Centre per la consulta: Ha de ser un valor numèric");break;
      case 1: alert("Centro para la consulta: Debe ser un valor numérico");break;
    }
    document.frmCentro.tfOfi.focus();
    return (false);
  }
  return (true);
}


function goTo(action, permiso,idioma)
{
  var frm=document.getElementById('frmPortal');
  frm.action=action;
  frm.PERMISO.value=permiso;
  frm.t_tipoconsulta.value=permiso;
  frm.f_centroEntra.value=document.frmCentro.tfOfi.value;  //filtro action
  frm.t_centroentra.value=document.frmCentro.tfOfi.value;  //para logs
  frm.submit();
	waiting();
}*/

