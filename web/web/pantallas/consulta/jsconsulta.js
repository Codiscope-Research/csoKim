function onlyDouble(value,name){
	  var n=value.split(".");
	  if(n.length==1){
		  value=value+".00";
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('input[id='+name+']').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('input[id='+name+']').css('border', 'solid 1px red');
		alert(initParams.txterrrordouble);
	}
} 

function inicio() {  	
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
	var frm = $("#BusquedaGestionFacturasForm")[0];
}

function initForm(){
	
	$("#f_cso option:first").attr('selected','selected'); 
	$("#f_any option:first").attr('selected','selected');
	$("#f_mes option:first").attr('selected','selected');
	$("#f_estado option:first").attr('selected','selected');
	$("#datadesde").val("");
	$("#datahasta").val("");
	$("#f_impdesde").val("");
	$("#f_imphasta").val("");
	  	  	
}

///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txterrorDate,txterrrordouble,txtcontext){		
		this.txterrorDate=txterrorDate;
		this.txterrrordouble=txterrrordouble;
		this.txtcontext = txtcontext;
}

function checkdateConsulta(id){
    var input = document.getElementById(id);
    input.className="text";
    if(input.value=='')return true;
    var validformat=/^\d{1,2}-\d{1,2}-\d{4}$/; //Basic check for format validity
    if (!validformat.test(input.value)) {
         input.className="error";
         input.focus();
         input.select();
         alert(initParams.txterrorDate);
         return false;           
    }else{ //Detailed check for valid date ranges
        var monthfield=input.value.split("-")[1];
        var dayfield=input.value.split("-")[0];
        var yearfield=input.value.split("-")[2];
        var dayobj = new Date(yearfield, monthfield-1, dayfield);
        if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
         input.className="error";
         input.focus();
         input.select();
         alert(initParams.txterrorDate);
         return false;           
        }
    }
    return true;
}



function goToDetall(id){
	window.location.href="GestioFacturaAction.do?id="+id;
	waiting();	
}

function doOrderBy(orderBy) {
	var frm = $("#BusquedaGestionFacturasForm")[0];
	frm.action="/"+initParams.txtcontext+"/BusquedaGestionFacturasAction.do";
	frm.pagina.value="1";
	frm.order_by.value=orderBy;
	frm.submit();
	waiting();
}

function irPag(pag) {
	var frm = $("#BusquedaGestionFacturasForm")[0];
	frm.action="/"+initParams.txtcontext+"/BusquedaGestionFacturasAction.do";
	frm.pagina.value=pag;
	frm.submit();
	waiting();
}

function generaExcel(){
	var frm = $("#BusquedaGestionFacturasForm")[0];
	frm.action="/"+initParams.txtcontext+"/GeneraFacturasExcelAction.do";
	frm.submit();
}


function doFiltro() {
	var frm = $("#BusquedaGestionFacturasForm")[0];
	frm.action="/"+initParams.txtcontext+"/BusquedaGestionFacturasAction.do";
	frm.pagina.value=1;
	var ok = false;
      
   ok = validateBusquedaGestionFacturasForm(frm);

   if (!ok) {
      return;
   }
   frm.submit();
	waiting();
}



