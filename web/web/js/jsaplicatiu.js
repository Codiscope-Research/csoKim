


function waiting()
{   
	var myWidth;
	var myHeight;
	
	if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) { 
	
	//IE 6+ in 'standards compliant mode' 
	
	myWidth = document.documentElement.clientWidth; 
	myHeight = document.documentElement.clientHeight; 
	
	} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) { 
	
	//IE 4 compatible 
	
	myWidth = document.body.clientWidth; 
	myHeight = document.body.clientHeight; 
	
	}
	var height=$("#div_pantalla").height();
	if(height>myHeight)
		myHeight=height;
	$("#divCargando").width( myWidth );
	$("#divCargando").height( myHeight );
	$("#divCargando").show();
	$("#divCargandoImg").css( "left",(myWidth/2)-100);
	$("#divCargandoImg").show();
}
function hideWaiting()
{   
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}

//===================================================================================
//BEGIN : CODIGO AJAX DEPARTAMENTS SEGONS CODI AREA
//===================================================================================

var http_request;
var resultado=null;  // respuesta del servidor

function getParamsCCC(area) {
  var params = 'area='+area;
  return params;
}

function hacerPeticion(codtipologia) {
  http_request = new ActiveXObject("Microsoft.XMLHTTP");
  //http_request = new ActiveXObject("Msxml2.XMLHTTP");
  http_request.onreadystatechange = recibir;
  
  http_request.open('GET', '/Gdsactivos/odm/AjaxAreaDepartamentAction.do', false); //bloqueante
  http_request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

  params=getParamsCCC(codtipologia);
  
  resultado=null;
  http_request.send(params);
  
}

function recibir() {
	
	if (http_request.readyState == 4) { // todo va bien, respuesta recibida
	    
	    if (http_request.status == 200) { // http OK	        
	        
	        var str = http_request.responseText;
	        try { 
	          resultado=eval("(" + str+ ")");
	        }
	        catch (e) {
	        	
	        }
	    }	
	}
}

function AJAX_areadep(areaId) { 
try {
 hacerPeticion(areaId);
}catch (e) {
 //alert(e.description); //borra
 return null;
}
return resultado;

}

//===================================================================================
//END : CODIGO AJAX DEPARTAMENTS SEGONS CODI AREA
//===================================================================================