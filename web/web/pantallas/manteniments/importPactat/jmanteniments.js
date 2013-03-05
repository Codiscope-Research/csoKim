function inicio() {  
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function onlyDouble(value,name){
	  var n=value.split(".");
	  if(n.length==1){
		  value=value+".00";
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('input[id='+name+']').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('input[id='+name+']').css('border', 'solid 1px red');
		alert(initParams.txterrordouble);
	}
} 

function doSave(){
	var frm = $("#ImportPactatForm")[0];	
	if(frm.importe.value==''){
		alert(initParams.txtempty);
		return;
	}
	 var where_to= confirm(initParams.txtconfirmsave);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
		  waiting();
	  }
	  	 
	  frm.submit();
		waiting();
}

function InitParams(txterrordouble,txtconfirmsave,txterrorsesio,txtempty,txtcontext){		
	this.txterrordouble=txterrordouble;
	this.txtconfirmsave=txtconfirmsave;
	this.txterrorsesio=txterrorsesio;
	this.txtempty= txtempty;
	this.txtcontext = txtcontext;
}

function getImporte(year){
	waiting();
	data = "year="+year;
	$.ajax({
		  type: "POST",
		  url: '/'+initParams.txtcontext+'/AjaxGetImporteAction.do',
		  dataType: 'json',
		  data: data,
		  success: function(json){
				if(json.error!=null){
       				$("#errorsajaxlabel").text(json.error);
            		$("#errorsajax").show();
       			}else{
				  if(json!= null && json.descripcio!=null){
					document.getElementById("importe").value= json.descripcio;			  
				  }
       			}
			  hideWaiting();
		  },
		  error: function(e){ 
				$("#errorsajaxlabel").text(initParams.txterrorsesio);
        		$("#errorsajax").show();
        		hideWaiting();
        		}
		});	
}

$("#errorsajax").hide();