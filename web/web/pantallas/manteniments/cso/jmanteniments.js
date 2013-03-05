function inicio() {  
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
	mensajes();
	var frm = $("#AltaForm")[0];
}


function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
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

function onlyDouble(value,name){
	  var n=value.split(".");
	  if(n.length==1){
		  value=value+".00";
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('#'+name).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+name).css('border', 'solid 1px red');
		alert(initParams.txterrordouble);
	}
}  
///////////////////////////////////
//variables per textos en locale
function InitParams(txterrorsessio,txterrorservrec,txterrornrecurs,txterrorpreu,txtborrada,txtcreapartida,txtbutFactors, txterrordouble,txtconfirmdeletepartida, txtcontext){		
	
	this.txterrorsessio=txterrorsessio;
	this.txterrorservrec=txterrorservrec;
	this.txterrornrecurs=txterrornrecurs;
	this.txterrorpreu=txterrorpreu;
	this.txtborrada=txtborrada;
	this.txtcreapartida=txtcreapartida;	
	this.txtbutFactors=txtbutFactors;
	this.txterrordouble= txterrordouble;
	this.txtconfirmdeletepartida= txtconfirmdeletepartida;
	this.txtcontext = txtcontext;
}
function openDiv(val){

	if(val=='recurs'){
		$("#recurs_div").show('slow');
	}else{
		$("#recurs_div").hide('slow');
	}
	
}

function saveCso(){
	//borrem de la BBDD
	waiting();
	var id = $("#f_cso").val();
	if(id=='undefined')return;
	var data = "action=saveCso&idCSO="+id;
	if($("#iva").is(':checked')){
		data=data+"&iva=true";
	}else{
		data=data+"&iva=false";
	}
	if($("#igic").is(':checked')){
		data=data+"&igic=true";
	}else{
		data=data+"&igic=false";
	}
	var descuento = $("#descuento").val();
	if(descuento=='undefined' || descuento==''){
		data=data+"&descuento=0.0";
	}else{
		data=data+"&descuento="+descuento;
	}
	
	var impuesto = $("#impuesto").val();
	if(impuesto=='undefined' || impuesto==''){
		data=data+"&impuesto=0.0";
	}else{
		data=data+"&impuesto="+impuesto;
	}
	
	if($("#servicioRecurso").val()==''){
		
		$('#servicioRecurso').css('border', 'solid 1px red');
		alert(initParams.txterrorservrec);
		
		return;
	}else if($("#servicioRecurso").val()=='servei'){		
		$('#servicioRecurso').css('border', 'solid 1px rgb(135,155,179)');
		data=data+"&facturacio=servei";
	}else if($("#servicioRecurso").val()=='recurs'){
		$('#servicioRecurso').css('border', 'solid 1px rgb(135,155,179)');
		data=data+"&facturacio=recurs";
		var nrecurs = $("#nrecursos").val();
		var preuunitari=$("#preurecursos").val();
		if(nrecurs=='undefined' || nrecurs==''){
			$('#nrecursos').css('border', 'solid 1px red');
			alert(initParams.txterrornrecurs);
			return;
		}else{
			$('#nrecursos').css('border', 'solid 1px rgb(135,155,179)');
			data=data+"&nrecursos="+nrecurs;
		}
		if(preuunitari=='undefined' || preuunitari==''){
			$('#preurecursos').css('border', 'solid 1px red');
			alert(initParams.txterrorpreu);
			return;
		}else{
			$('#preurecursos').css('border', 'solid 1px rgb(135,155,179)');
			data=data+"&preuunitari="+preuunitari;
		}
	}
	
	
	$.ajax({
		  type: "POST",
		  url: '/'+initParams.txtcontext+'/admin/AjaxGetCsoAction.do',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{				
     				if(json.OK!=null){
     					alert(json.OK);
     				}     				
     			}
			  hideWaiting();
		  },
		  error: function(e){
			  	$("#errorsajaxlabel").text(initParams.txterrorsessio);
      			$("#errorsajax").show();
      			hideWaiting();
		  }
		});	
}

function getCso(){
	
	//borrem de la BBDD
	waiting();
		var id = $("#f_cso").val();
		if(id=='undefined')return;
		var data = "action=getCso&idCSO="+id;
		$.ajax({
			  type: "POST",
			  url: '/'+initParams.txtcontext+'/admin/AjaxGetCsoAction.do',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json.error!=null){
         				$("#errorsajaxlabel").text(json.error);
         				$("#errorsajax").show();
         			}else{
						 if(json.descripcio!=null){
							 $("#nameCSO").text(json.descripcio);
							 $("#descripcio").val(json.descripcio);
						 }
						 
						 if(json.iva!=null && json.iva==true){
							 $("#iva").attr("checked","checked");
						 }else{
							 $("#iva").attr("checked","");
						 }
						 
						 if(json.igic!=null && json.igic==true){
							 $("#igic").attr("checked","checked");
						 }else{
							 $("#igic").attr("checked","");
						 }
						 
						 if(json.descuento!=null){
							 $("#descuento").val(json.descuento);
						 }else{
							 $("#descuento").val(0.0);
						 }
						 if(json.impuesto!=null){
							 $("#impuesto").val(json.impuesto);
						 }else{
							 $("#impuesto").val(0.0);
						 }
						 if(json.servicioRecurso!=null){
							 $("#servicioRecurso option[value='"+json.servicioRecurso+"']").attr('selected','selected');
							 if(json.servicioRecurso=='recurs'){
								 $("#recurs_div").show('slow');
							 }
							   
						 }else{
							 $("#servicioRecurso option:first").attr('selected','selected');
							 $("#recurs_div").hide('slow');
						 }
					 
         			}
				  hideWaiting();
			  },
			  error: function(e){
				  	$("#errorsajaxlabel").text(initParams.txterrorsessio);
          			$("#errorsajax").show();
          			hideWaiting();
			  }
			});	
}

$("#recurs_div").hide();