
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

function formatNumber_onblur(number) {
   number.value=toNumber(formatNumber(number.value));
}


function replaceNumber(number) {
	number.value=formatNumber(number.value);
}

function formatNumber(num) {
	if(num){
		num=num.replace(/\./g,"");
		num=num.replace(/\,/g,".");
	}
	return num;
}

//METODE QUE DONA FORMAT A LES UNITATS DE MILERS DEL CAMP LÍMIT PROPOSAT EN LA CARREGA
function formatNumber_load(number) {
	var num=number.value;
	if(num){
    	number.value=toNumber(num);
	}
}

function returnPres(object,event){
	var car=String.fromCharCode(event.keyCode);
	var regx = /[0-9\.,]/;
	var result=regx.test(car);
	if(event.keyCode==46)
		event.keyCode=44;
	return result;
}


function toNumber(num){
	  num+='';
	  var splitStr = num.split('.');
	  var splitLeft = splitStr[0];
	  var splitRight = splitStr.length > 1 ? ','+ splitStr[1] : '';
	  var regx = /(\d+)(\d{3})/;
	  while (regx.test(splitLeft)) {
		  splitLeft = splitLeft.replace(regx, '$1' + '.' + '$2');
	  }
	  return splitLeft+splitRight;
}

function redondeo2decimales(numero)
{
	var original=parseFloat(numero);
	var result=Math.round(original*100)/100 ;
	return result;
}

function spanSwitch(){
	$("div.filtres > span.switch").click(function() {
        $("fieldset", this.parentNode).toggle();
        $(this).toggleClass("visibles");
        var visible = $.className.has(this, "visibles");
        if (visible) {
                var form = $(this.parentNode).find("form")[0];
                var elements = form.elements;
                if (null!=elements) {
                for (var i = 0; i < elements.length; i++) {
                        switch (elements[i].tagName.toLowerCase()) {
                        case "input":
                        case "select":
                        case "textarea":
                              try {
                                elements[i].focus();
                              }catch(err) {

                              } 
                                return;
                        default:
                                // continua iteracion
                        }
                }//end for
                }//end if null
        }
	});
}

