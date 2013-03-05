//===================================================================================
//Funciones 
//===================================================================================
function inicio() {
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
	mensajes();

      $('table.seleccio tbody tr').hover(function() {
        $(this).addClass('over');
      }, function() {
        $(this).removeClass('over');
      });
   }//end inicio

function doNou() {
	var frm = $("#GestioUsuarisForm")[0];
	frm.f_matricula.value="";
	frm.f_nom.value="";
	frm.f_pcognom.value="";
	frm.f_scognom.value="";
	frm.f_perfil.value="";
	$(".selected").removeClass("selected");
	$(".error").addClass("required");
	$(".error").removeClass("error");
}

function doSelect(rowid,num,perfil) {
	var frm = $("#GestioUsuarisForm")[0];
   frm.f_matricula.value=num;
   var table = document.getElementById("users_cso");
   var r =parseInt(parseInt(rowid)+1);
   var row = table.rows[r];
   
   frm.f_nom.value=row.cells[1].innerText;
   frm.f_pcognom.value=row.cells[2].innerText;
   frm.f_scognom.value=row.cells[3].innerText;
   frm.f_perfil.value=perfil;
   	$(".selected").removeClass("selected");
	$("#"+rowid).addClass("selected");
	$(".error").addClass("required");
	$(".error").removeClass("error");
}

function doAceptar() {
	var frm = $("#GestioUsuarisForm")[0];
   if(frm.f_matricula.value.length>0){
      	msg=buildMessage(txtConfirmUpdate); 
   }else{
      	alert(buildMessage(txtSelectRegistre));
      	return;
   }
	var ok = validateGestioUsuarisForm(frm);
	if (!ok) {
		return;
	}
	if (confirm(msg)) {		
		frm.operacion.value="";
		frm.submit();
		waiting();
	}
	return;
}


function doOrderBy(orderBy) {
	var frm = $("#GestioUsuarisForm")[0];
  frm.pagina.value="1";
  frm.order_by.value=orderBy;
  frm.operacion.value="CONSULTA";
  frm.submit();
	waiting();
}//doOrderBy

function irPag(pag) {
	var frm = $("#GestioUsuarisForm")[0];
   frm.pagina.value=pag;
   frm.operacion.value="CONSULTA";
   frm.submit();
	waiting();
}//end irPag

function goTo(action){
	var frm = $("#frmAdmin")[0];
	frm.action=action;
	frm.submit();
	waiting();
}


function checkSubmit(e)
{
   if(e && e.keyCode == 13)
   {
	   doAceptar();
   }
}

function mensajes() {
    var arr = document.getElementsByName('mensaje');
    var mensaje = "";
    for( var i = 0; i < arr.length; i++ ) {
      mensaje += arr[i].innerHTML;
      if (i != arr.length-1)
        mensaje += "\n";
    }
    if (mensaje != "")
        alert(mensaje);
}
