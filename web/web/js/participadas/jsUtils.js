var txtCIFLongINC       = '';
var txtCIFFormINC       = '';
var txtCIF_INC          = '';
var txtCampoObligatorio = '';
var txtIncompleto       = '';
var txtMalCCC           = '';
var txtMailIncorrecto   = '';
var txtModCCCNoVal      = '';
var txtNoNifCaixa       = '';
var txtDataIncorrecta='';
var txtDataAnterior = '';
var txtIntIncorrecto = '';
var txtIntAnterior   = '';
var txtDataNoFuture = '';
var txtDataFuture = '';
var txtCuentaINC    = '';

var pruebas             = true;

function buildMessage(str, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9) {
  for (var i=0;i<10;i++) {
    eval("if (arg"+i+"==null) arg"+i+"='';");
    eval("arg"+i+"= ''+arg"+i+";");
    eval("arg"+i+"=arg"+i+".replace(/:/,'');");
    eval("arg"+i+"=trim(arg"+i+");");
    str = eval("str.replace(/<"+i+">/,arg"+i+");");
  }
  return str;
}

function isSpaceChar(crt) {
  return (crt=='\t'
          ||
          crt=='\n' 	
          ||
          crt=='\f'
          ||
          crt=='\r'
          ||
          crt==' '
          );
}//end isSpaceChar
function leftTrim(sString)
{
		while (  isSpaceChar(sString.substring(0,1)) )
		{
		sString = sString.substring(1, sString.length);
		}
		return sString;
}//end leftTrim
function rightTrim(sString)
{
		while (isSpaceChar(sString.substring(sString.length-1, sString.length)))
		{
		sString = sString.substring(0,sString.length-1);
		}
		return sString;
}//end rightTrim
function trim(sString) {
		sString=leftTrim(sString);
		sString=rightTrim(sString);
		return sString;
}//end trim
function validarNif2(nif) 
{
	var temp=nif.toUpperCase();
	var cadenadni="TRWAGMYFPDXBNJZSQVHLCKE";						
 
	if (temp!==''){

		if ((!/^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$/.test(temp) && !/^[T]{1}[A-Z0-9]{8}$/.test(temp)) && !/^[0-9]{8}[A-Z]{1}$/.test(temp))
		{
			return false;
		}
 

		if (/^[0-9]{8}[A-Z]{1}$/.test(temp))
		{
			posicion = nif.substring(8,0) % 23;
			letra = cadenadni.charAt(posicion);
			var letradni=temp.charAt(8);
			if (letra == letradni)
			{
				//return 1;
				return true;
			}
			else
			{
				//return -1;
				return false;
			}
		}

		suma = parseInt(nif.charAt(2))+parseInt(nif.charAt(4))+parseInt(nif.charAt(6));
		for (i = 1; i < 8; i += 2)
		{
			temp1 = 2 * parseInt(nif.charAt(i));
			temp1 += '';
			temp1 = temp1.substring(0,1);
			temp2 = 2 * parseInt(nif.charAt(i));
			temp2 += '';
			temp2 = temp2.substring(1,2);
			if (temp2 == '')
			{
				temp2 = '0';
			}
 
			suma += (parseInt(temp1) + parseInt(temp2));
		}
		suma += '';
		n = 10 - parseInt(suma.substring(suma.length-1, suma.length));
 
		//comprobacion de NIFs especiales (se calculan como CIFs)
		if (/^[KLM]{1}/.test(temp))
		{
			if (a[8] == String.fromCharCode(64 + n))
			{
				//return 1;
				return true;
			}
			else
			{
				//return -1;
				return false;
			}
		}
 
		//comprobacion de CIFs
		if (/^[ABCDEFGHJNPQRSUVW]{1}/.test(temp))
		{
			temp = n + '';
			if (nif.charAt(8) == String.fromCharCode(64 + n) || nif.charAt(8) == parseInt(temp.substring(temp.length-1, temp.length)))
			{
				//return 2;
				return true;
			}
			else
			{
				//return -2;
				return false;
			}
		}
 
		//comprobacion de NIEs
		//T
		if (/^[T]{1}/.test(temp))
		{					
			if (/[A-Z0-9]{8}$/.test(temp))
			{
				//return 3;
				return true;
			}
			else
			{
				//return -3;
				return false;
			}
		}
 
		//XYZ
		if (/^[XYZ]{1}/.test(temp))
		{					
			pos = str_replace(['X', 'Y', 'Z'], ['0','1','2'], temp).substring(0, 8) % 23;
			if (nif.charAt(8) == cadenadni.substring(pos, pos + 1))
			{
				//return 3;
				return true;
			}
			else
			{
				//return -3;
				return false;
			}
		}
	}
 
	return 0;
}
function str_replace(search, replace, subject) {			
	var f = search, r = replace, s = subject;
	var ra = r instanceof Array, sa = s instanceof Array, f = [].concat(f), r = [].concat(r), i = (s = [].concat(s)).length;
 
	while (j = 0, i--) {
		if (s[i]) {
			while (s[i] = s[i].split(f[j]).join(ra ? r[j] || "" : r[0]), ++j in f){};
		}
	};		 
	return sa ? s : s[0];
}


function validarCIFCaixa(texto)
{
  var nif = trim(texto.toUpperCase());
  if (pruebas==false && nif=="G58899998")
  {
    alert(txtNoNifCaixa);
    return (false);
  }
  return (validarCIF(nif));
}

    function validarCIF( nif){
     

  var LetrasYnumeros="ABCDEFGJUVHNWPQRS0123456789TRWAGMYFPDXBNJZSQVHLCKE";
  var Letras10 = "ABCDEFGHIJ";
  var Pares = 0;
  var Impares = 0;
  nif = trim(nif);
  if (nif.substring(0,1)=="T")
        return(true);
  if (nif.length != 9) 
  {
    if (nif.length==10)
    {
        if (nif.substring(0,1)=="X" && nif.substring(1,2)=="0")
            nif=nif.substring(0,1)+nif.substring(2,10);
        else {
            alert(txtCIFLongINC);
            return (false);
        }            
    }
    else {
        alert(txtCIFLongINC);
        return (false);
    }
  }
  if (isNaN(nif.substring(1,8))) {
    alert(txtCIFFormINC);
    return(false);
  }

  Juridicas = false;
  Nacional = false;
  Extranjero = false;
  Estatal = false;
  Locales = false;
  Fantiguo=false;
  // Personas Fisicas
  Fisicas = false;
  FisicasDni = false;
  FisicasSinDni = false;
  FisicasNoRes = false;
  FisicasExtran = false;

  //If (InStr(1, Mid(LetrasYnumeros, 1, 11), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 18, 10), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(0,11).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(17,27).indexOf(nif.substring(8,9))>=0)
  {
    Nacional = true;
    Juridicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 1, 13), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(Letras10, 1, 10), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(0,13).indexOf(nif.substring(0,1))>=0 && Letras10.substring(0,10).indexOf(nif.substring(8,9))>=0)
  {
    Extranjero = true;
    Juridicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 17, 1), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(Letras10, 1, 10), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(16,17).indexOf(nif.substring(0,1))>=0 && Letras10.substring(0,10).indexOf(nif.substring(8,9))>=0)
  {
    Estatal = true;
    Juridicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 14, 3), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(Letras10, 1, 10), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(13,16).indexOf(nif.substring(0,1))>=0 && Letras10.substring(0,10).indexOf(nif.substring(8,9))>=0)
  {
    Locales = true;
    Juridicas = true;
  }



  //If (InStr(1, Mid(LetrasYnumeros, 18, 10), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 28, 23), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(17,27).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    FisicasDni = true;
    Fisicas = true;
  }

  //If (InStr(1, Mid(LetrasYnumeros, 49, 1), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 28, 23), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(48,49).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    FisicasSinDni = true;
    Fisicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 47, 1), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 28, 23), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(46,47).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    FisicasNoRes = true;
    Fisicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 38, 1), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 28, 23), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(37,38).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    FisicasExtran = true;
    Fisicas = true;
  }
  //If (InStr(1, Mid(LetrasYnumeros, 33, 1), Mid(nif, 1, 1))) > 0 And (InStr(1, Mid(LetrasYnumeros, 28, 23), Mid(nif, 9, 1))) > 0 Then
  if (LetrasYnumeros.substring(32,33).indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    FisicasExtran = true;
    Fisicas = true;
  }

  //  Nuevas letras Fisicas extranjeros (la X se ha acabado y han asignado mas letras) que empiezan por Y,Z y el noveno caracter debe ser una letra
  if ("YZ".indexOf(nif.substring(0,1))>=0 && LetrasYnumeros.substring(27,50).indexOf(nif.substring(8,9))>=0)
  {
    if (nif.substring(0,1)=="Y")
    {
      if (nif.substring(1,5)=="2100")
      {
        nif="0"+nif.substring(1,nif.length); //le ponemos un cero en la primera posición
      }
      else
      {
        nif="1"+nif.substring(1,nif.length); 
      }
    }
    else
    {
      nif="2"+nif.substring(1,nif.length); 
    }
    FisicasExtran = true;
    Fisicas = true;
    if (isNaN(trim(nif.substring(0,8))))
    {
       alert(txtCIF_INC);
       return(false);
    }
    var producto=parseInt(nif.substring(0,8),10) % 23;
    var digito=producto+28;
    if (LetrasYnumeros.substring(digito-1,digito) == nif.substring(8,9))
      return (true);
    alert(txtCIF_INC);  
    return (false);
  }


  if (Juridicas)
  {
    for(var i = 2;i<=8;i=i+2)
    {
        Digito = parseInt(nif.substring(i-1, i),10);
        Digito = Digito * 2;
        if (Digito >= 10) 
        {
           Dig1 = parseInt(Digito / 10,10);
           Dig2 = Digito % 10;
           Digito = Dig1 + Dig2;
        }
        Impares = Impares + Digito;
    }
    for (var i = 3;i<=8;i=i+2)
    {
        Digito = parseInt(nif.substring(i-1,i),10);
        Pares = Pares + Digito;
    }
    producto = Pares + Impares;
    Resto = producto % 10;
    Digito = 10 - Resto;
    if (Nacional)
    {
       if (Digito == 10)
          Digito = 0;
    }
    else
    {
       if (Digito == 0)
          Digito = 10;
    }
    if (Nacional)
    {
       if (Digito ==parseInt(trim(nif.substring(8,9)),10))
           return(true);
       else {
          alert(txtCIF_INC);
          return (false);
       }
    }

    // Resto
    if (Letras10.substring(Digito-1,Digito)==nif.substring(8,9))
       return (true);
    alert(txtCIF_INC);
    return (false);
  }
  if (Fisicas)
  {
    if (FisicasDni)
    {
       if (isNaN(trim(nif.substring(0,8)))) {
        alert(txtCIF_INC);
        return (false);
       }
       producto = parseInt(trim(nif.substring(0,8)),10) % 23;
    }
    else
    {
       if (isNaN(trim(nif.substring(1,8)))) {
          alert(txtCIF_INC);
          return (false);
       }
       producto = parseInt(trim(nif.substring(1,8)),10) % 23;
    }
    Digito = producto + 28;
    if (LetrasYnumeros.substring(Digito-1,Digito) == nif.substring(8,9))
      return (true);
    alert(txtCIF_INC);
    return (false);
  }
  alert(txtCIF_INC);
  return (false);
    } 

function validaTextObligatorios(listaIds) {
  for (var i=0;i<listaIds.length;i++) {
    var input = document.getElementById(listaIds[i]);
    if (null!=input) {
        if (trim(input.value)=="") {//error :: no hay datos 
           input.className="error";
           var span = document.getElementById('SPAN_'+listaIds[i]);
           var nombreCampo='';
           if (null!=span) { 
               nombreCampo = "\""+span.innerHTML+"\"";
           }
           var msg = buildMessage(txtCampoObligatorio, nombreCampo);
           alert(msg);
           input.focus();
           return false;
           
        }else {//bien :: hay datos
          input.className="text";
        }
     }//no existe id
  }//end for ids
  return true;
}

function validaTextFull( INPUT_TEXT) {
           if (INPUT_TEXT.value.length!=INPUT_TEXT.maxLength) { 
               alert(buildMessage(txtIncompleto, INPUT_TEXT.maxLength));  
               INPUT_TEXT.focus();
               INPUT_TEXT.select();
               return false;
           }
           return true;
}
function validaCCC(num,prefix)
{
  for (var i=0;i<num;i++) 
	{
     var E2_ENTID = document.getElementById(prefix+'_ENTID_'+i);
     var E2_OFICI = document.getElementById(prefix+'_OFICI_'+i);
     var E2_DC    = document.getElementById(prefix+'_DC_'+i);
     var E2_CUENT = document.getElementById(prefix+'_CUENT_'+i);
		 var correcta=compruebaCuenta(E2_ENTID.value,E2_OFICI.value,E2_DC.value,E2_CUENT.value);
		 if (!correcta)
		 {
       alert(txtMalCCC);
			 E2_DC.className="error";                 
			 E2_DC.focus();
			 E2_DC.select();
			 return false;
		 }
		 else if (E2_CUENT.value.substring(0,2)=="65")
		 {
			 alert(txtModCCCNoVal);
			 E2_CUENT.className="error";                 
			 E2_CUENT.focus();
			 E2_CUENT.select();
			 return false;
		 }
		 else
		 {
				E2_DC.className="text";
				E2_CUENT.className="text";                  
		 }
	}
  return true;
}
// Valida que la cuenta corriente sea correcta en cuanto a digits de control
function compruebaCuenta(entidad,oficina,dg,cuenta)
{
  var correcto=entidad.length==4 && oficina.length==4 && dg.length==2 && cuenta.length==10;
  correcto=correcto && !isNaN(entidad) && !isNaN(oficina) && !isNaN(dg) && !isNaN(cuenta);
  correcto=correcto && check_oficina_ccc(entidad,oficina,dg); //valida el primer dígito de los dos dígitos de control
  correcto=correcto && check_cuenta_ccc(cuenta,dg); //valida el segundo dígito de los dos dígitos de control
  if (entidad=="2100")
  {
    if (cuenta.substring(0,2)!= 62) {
       correcto=correcto && check_cuenta_entidad(oficina,cuenta); //valida los dos últimos dígitos de la cuenta en caso de ser entidad=2100
    }
  }
  return (correcto);
}

//valida el primer dígito de los dos dígitos de control
function check_oficina_ccc(entidad,oficina,dg)
{
  var lv_total;
  var lv_peso;
  var ii;
  var lv_digit;
  var Pesos;
  var Numero;

  Pesos = "6379058421";
  if (entidad.length==0 || oficina.length==0)
    return (false);
  else
  {
    Numero = repiteCaracter("0",4-entidad.length)+entidad + repiteCaracter("0",4-oficina.length)+oficina;
    lv_total = 0;
    for(ii = 0;ii<=7;ii++)
    {
        lv_digit = Numero.substring(ii, ii+1);
        lv_peso = Pesos.substring((7 - ii), (7 - ii)+1);
        if (lv_peso == 0) lv_peso = 10;
        lv_total = lv_total + (lv_peso * lv_digit);
    }
    lv_total = lv_total % 11;
    lv_total = 11 - lv_total;
    
    if (lv_total == 11) lv_total = 0;
    else if (lv_total == 10) lv_total = 1;
    
    return(lv_total==dg.substring(0,1));
  }
}

//valida el segundo dígito de los dos dígitos de control
function check_cuenta_ccc(cuenta,dg)
{
  var Pesos;
  var lv_total;
  var lv_digit;
  var lv_peso;
  var ii;

  Pesos = "6379058421";
  cuenta = repiteCaracter("0",10-cuenta.length)+cuenta;
  lv_total = 0;
  for (ii=0;ii<=9;ii++)
  {
    lv_digit=cuenta.substring(ii,ii+1);
    lv_peso=Pesos.substring(9-ii,(9-ii)+1);
    if (lv_peso==0) lv_peso=10;
    lv_total=lv_total+(lv_peso*lv_digit);
  }
  lv_total = lv_total % 11;
  lv_total = 11 - lv_total;
  if (lv_total == 11) lv_total = 0;
  else if (lv_total == 10) lv_total = 1;

  return (lv_total==dg.substring(1,2));
}

//valida los dos últimos dígitos de la cuenta en caso de ser entidad=2100
function check_cuenta_entidad(oficina,cuenta)
{
  var sumatorio;
  var cadena_check;
  var Arraypesos;
  var contador;
  var digito;

  sumatorio = 0;
  Arraypesos = new Array(47, 29, 13, 7, 41, 31, 23, 17, 19, 7, 11, 13);
  cadena_check = repiteCaracter("0",4-oficina.length)+oficina + repiteCaracter("0",10-cuenta.length)+cuenta;
  for (contador=0;contador<Arraypesos.length;contador++)
  {
    var elem=cadena_check.substring(contador, contador+1);
    sumatorio = sumatorio + parseInt(elem,10) * Arraypesos[contador];
  }
  digito = sumatorio % 100;  // Sumamos antes por si la suma supera 99

  return (digito==parseInt(cuenta.substring(8,10),10));

}
function validaCIFs( num, prefix) {

   for (var i=0;i<num;i++) 
   {
     var E2_CIF = document.getElementById(prefix+'_CIF_'+i);
     if ( trim(E2_CIF.value)!="" )  
     {
            //if (!validaTextFull(E2_CIF )) return false;
            var cif = ""+E2_CIF.value;
            cif = trim(cif.toUpperCase());
            if (!validarCIF( cif)) {
                    E2_CIF.className="error";
                    E2_CIF.focus();
                    E2_CIF.select();
                    return false;
            }else {
                    E2_CIF.className="text";
            }
     }//end if chequeo
   }
   return true;

}

function validaMail(str) {
 return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(str);
}



function validaInputMail( id) {
   var mail = document.getElementById(id);
   if (""!=trim(mail.value)) {
       if (!validaMail(mail.value)) {
         alert(txtMailIncorrecto);
         mail.className="error";
         mail.focus();
         mail.select();
         return false;
       }
    }
     mail.className="text";
     return true;
   
}

function validaInputMails(id) {
  var mail = document.getElementById(id);
  var mails = mail.value;
  var arrayMail = mails.split(',');
  for (var i=0;i<arrayMail.length;i++) {
    if (""!=trim(arrayMail[i])) {
       if (!validaMail(trim(arrayMail[i]))) {
         alert(txtMailIncorrecto+": \""+arrayMail[i]+"\"");
         mail.className="error";
         mail.focus();
         mail.select();
         return false;       
       }
    }
  }//end loop
  mail.className="text";
  return true;
}

function eligeIdioma(idioma, urls, link) {
    var vReturnValue = window.showModalDialog('/GesSTRForm/web/pantallas/utils/triaIdioma.jsp?Idioma='+idioma ,'','dialogHeight:200px;dialogWidth:300px;scroll:no;unadorned:yes;status:no;help:no;');
    
    if (null==vReturnValue) {
         window.event.returnValue = false;
         return;
    }
    link.href=urls[vReturnValue];
    window.event.returnValue = true;
    return;
}
function repiteCaracter(caracter,veces)
{
  var cadena="";
  for (var i=1;i<=veces;i++)
    cadena=cadena+caracter;
  return (cadena);
}

function validaInputIntegerDesdeHasta(desde, hasta) {
  var ok = validaInteger(desde);
  if (!ok) {
      alert(txtIntIncorrecto);
      return false;
  }

  ok = validaInteger(hasta);
  if (!ok) {
      alert(txtIntIncorrecto);
      return false;
  }

  ok = validaInputIntegerBefore(desde, hasta);
  if (!ok) { 
      alert(txtIntAnterior);
      return false;
  }

  return true;

}//end validaInputIntegerDesdeHasta



function validaInteger(id) {
 var txt = document.getElementById(id);
 var ok = /^[0-9]*$/.test(txt.value);

  if (!ok) {
     txt.className="error";
     txt.focus();
     txt.select();
     return false;
  } 
  txt.className="text";
  return true;
}//end validaInteger



function validaInputIntegerBefore(id1, id2) {
  var txt1 = document.getElementById(id1);
  var txt2 = document.getElementById(id2);

  if (""==trim(txt1.value)) {
    txt1.className="text";
    txt2.className="text";
    return true;
  }
  if (""==trim(txt2.value)) {
    txt1.className="text";
    txt2.className="text";
    return true;
  }
  if (trim(txt1.value)!=trim(txt2.value)) {
      var a1 = parseInt(txt1.value, 10);
      var a2 = parseInt(txt2.value, 10);

      var ok = a1 <= a2;
      if (!ok) {
        txt1.className="text";
        txt2.className="error";
        txt2.focus();
        txt2.select();
        return false;
      }
  }
  txt1.className="text";
  txt2.className="text";
  return true;
}

function checkdateNoMessage(value){
        var validformat=/^\d{1,2}-\d{1,2}-\d{4}$/;
        if (!validformat.test(value)) {
             return false;           
        }else{ 
            var monthfield=value.split("-")[1];
            var dayfield=value.split("-")[0];
            var yearfield=value.split("-")[2];
            var dayobj = new Date(yearfield, monthfield-1, dayfield);
            if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
             return false;           
            }
        }
        return true;
}


function checkdate(id){
        var input = document.getElementById(id);
        input.className="text";

        var validformat=/^\d{1,2}-\d{1,2}-\d{4}$/; 
        if (!validformat.test(input.value)) {
             input.className="error";
             input.focus();
             input.select();
             alert(txtDataIncorrecta);
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
             alert(txtDataIncorrecta);
             return false;           
            }
        }
        return true;
}

function checkdateNoFuture(id){
   if (checkdate(id)) {
      var input = document.getElementById(id);
      var monthfield=input.value.split("-")[1];
      var dayfield=input.value.split("-")[0];
      var yearfield=input.value.split("-")[2];
      var dayobj = new Date(yearfield, monthfield-1, dayfield);

      var past = dayobj.getTime();
      var now  = new Date().getTime();

      if (now<=past) {
             input.className="error";
             input.focus();
             input.select();
             alert(txtDataNoFuture);
             return false;           
      }
      return true;
      
   }else {
      return false;
   }
}


function checkdateFuture(id){
   if (checkdate(id)) {
      var input = document.getElementById(id);
      var monthfield=input.value.split("-")[1];
      var dayfield=input.value.split("-")[0];
      var yearfield=input.value.split("-")[2];
      var dayobj = new Date(yearfield, monthfield-1, dayfield);

      var future = dayobj.getTime();
      var now  = new Date().getTime();

      if (future<=now) {
             input.className="error";
             input.focus();
             input.select();
             alert(txtDataFuture);
             return false;           
      }
      return true;
      
   }else {
      return false;
   }
}

function validaInputDateDesdeHasta(desde, hasta) {
  var ok = validaInputDateDDMMYY(desde);
  if (!ok) {
      alert(txtDataIncorrecta);
      return ok;
  }
  
  ok = validaInputDateDDMMYY(hasta); 
  if (!ok) { 
      alert(txtDataIncorrecta);
      return ok;
  }
  ok = validaInputDateBeforeDDMMYY(desde, hasta);
  if (!ok) { 
      alert(txtDataAnterior);
      return ok;
  }
  return ok;
}

function validaInputDateDDMMYY(id) {
  var txt = document.getElementById(id);
  
  if (""!=trim(txt.value)) {
      var ok = TF7.validators.date(txt.value);
      if (!ok) {
         txt.className="error";
         txt.focus();
         txt.select();
         return false;
      }
  }
  txt.className="text";
  return true;
}//end validaInputDateDDMMYY

function arrayFromDateStringDDMMYY(str) {
  var t = str.split(TF7.conf.dateSplitterRegexp);
  var v = [];
  v[0] = t[2];
  v[1] = TF7.conf.dateFormat == "dmy" ? t[1] : t[0];
  v[2] = TF7.conf.dateFormat == "dmy" ? t[0] : t[1];
  return v;
}


function validaInputDateBeforeDDMMYY(id1, id2) {
  var txt1 = document.getElementById(id1);
  var txt2 = document.getElementById(id2);

  if (""==trim(txt1.value)) {
    txt1.className="text";
    txt2.className="text";
    return true;
  }
  if (""==trim(txt2.value)) {
    txt1.className="text";
    txt2.className="text";
    return true;
  }
  if (trim(txt1.value)!=trim(txt2.value)) {
      var a1 = arrayFromDateStringDDMMYY(txt1.value);
      var a2 = arrayFromDateStringDDMMYY(txt2.value);

      var ok = TF7.validators.dateBefore(a1, a2);
      if (!ok) {
        txt1.className="text";
        txt2.className="error";
        txt2.focus();
        txt2.select();
        return false;
      }
  }
  txt1.className="text";
  txt2.className="text";
  return true;
}

function abrirVentana(url)
{
  window.open(url);
}



function scorePassword(passwd) {
		var intScore   = 0;
		var strVerdict = "débil";
		

		if (passwd.length<5)                      
                  intScore = (intScore+3);
		else if (passwd.length>4 && passwd.length<8) 
                  intScore = (intScore+6);
		else if (passwd.length>7 && passwd.length<16)
                  intScore = (intScore+12);
		else if (passwd.length>15)                   
                  intScore = (intScore+18);
		
		if (passwd.match(/[a-z]/))                              // [verified] at least one lower case letter
                  intScore = (intScore+1);
		
		if (passwd.match(/[A-Z]/))                              // [verified] at least one upper case letter
                  intScore = (intScore+5);
		
		// NUMBERS
		if (passwd.match(/\d+/))                                 // [verified] at least one number
                  intScore = (intScore+5);
				
		if (passwd.match(/(.*[0-9].*[0-9].*[0-9])/))             // [verified] at least three numbers
                  intScore = (intScore+5);
				
		
		// SPECIAL CHAR
		if (passwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/))            // [verified] at least one special character
                  intScore = (intScore+5);
											 // [verified] at least two special characters
		if (passwd.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/))
                  intScore = (intScore+5);
		
		if (passwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))        // [verified] both upper and lower case
                  intScore = (intScore+2);
		
		if (passwd.match(/([a-zA-Z])/) && passwd.match(/([0-9])/)) // [verified] both letters and numbers
                  intScore = (intScore+2);
			
									// [verified] letters, numbers, and special characters
		if (passwd.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/))
                  intScore = (intScore+2);
		
	
		if(intScore < 16) {
                  return 0;
		}else if (intScore > 15 && intScore < 25) {
                  return 1;
		}else if (intScore > 24 && intScore < 35) {
                  return 2;
		}else if (intScore > 34 && intScore < 45) {
                  return 3;
		}else {
                  return 4;
		}
}
function difDias(dia1,mes1,any1,dia2,mes2,any2)
{
  var fecha1=new Date(any1,mes1-1,dia1);
  var fecha2=new Date(any2,mes2-1,dia2);
  var restamili=fecha2.getTime()-fecha1.getTime();
  var difdias = restamili/(1000*60*60*24);
  return (difdias);
}

function no_todos(chk, id_IND, id_FORM, pre_id) {
  var todos = document.getElementById(id_IND);
  if (!chk.checked) {
     todos.checked=false;
  }else {  
      var todos_checked = true;
      var frm = document.getElementById(id_FORM);
      for (var i=0;i<frm.elements.length;i++) {
         var ele = frm.elements[i];
         if (null!=ele.name && ele.name.indexOf(pre_id)!=-1 && ele.name!=id_IND ) {
            if (!ele.checked) todos_checked=false;
         }
      }
      todos.checked=todos_checked;
  }
}

function todos(chk, id_FORM, pre_id) {
  var los_otros = false;
  if (chk.checked) {
     los_otros=true;
  }

      var frm = document.getElementById(id_FORM);
      for (var i=0;i<frm.elements.length;i++) {
         var ele = frm.elements[i];
         if (null!=ele.name && ele.name.indexOf(pre_id)!=-1) {
            ele.checked=los_otros;
         }
      } 
}


function checkTodos(id_FORM, pre_id, bool) {
      var frm = document.getElementById(id_FORM);
      for (var i=0;i<frm.elements.length;i++) {
         var ele = frm.elements[i];
         if (null!=ele.name && ele.name.indexOf(pre_id)!=-1) {
            ele.checked=bool;
         }
      } 
}
function controlaComma() {
   if (event.keyCode==46) event.keyCode=44;
   if (event.keyCode==44 && this.value=="") event.returnValue=false;
}




function menorIgualQueZero(str) {
  var num = parseInt(str,10);
  return num<=0;
}

function vacio(str) {
  return trim(str)=="";
}


function aplicaValidaciones(listaIds, funcion, txtMensaje) {
  for (var i=0;i<listaIds.length;i++) {
    var input = document.getElementById(listaIds[i]);
    if (null!=input) {
        if (funcion(input.value)) {//error :: no hay datos 
           input.className="error";
           var span = document.getElementById('SPAN_'+listaIds[i]);
           var nombreCampo='';
           if (null!=span) { 
               nombreCampo = "\""+span.innerHTML+"\"";
           }
           var msg = txtMensaje;
           alert(msg);
           input.focus();
           return false;
           
        }else {//bien :: hay datos
          input.className="text";
        }
     }
  }
  return true;
}




function pad(str,ch,len) { 
      if (null==str) str="";
      var ps='';
      for(var i=0; i<Math.abs(len); i++) {
		  ps+=ch;
      }
      if (len>0) {
        var concat = (str+ps);
        var max = Math.max(str.length, len);
        return concat.substring(0,max);
      }else {
        var concat = (ps+str);
        var ini = concat.length+len;
        var fin = concat.length;
        return concat.substring( ini, fin);
      }
  }
function lPad(str,len,chara)
{
	if (null==str) str="";
    var lpad=str.toString();
   for(i=str.length+1;i<=len;i++)
   {
	   lpad=chara+lpad;
   }
   return lpad;
}

function preCookDate(txtDate) {
                var dateSplitterRegexp = /[\\.|\-|\/|\s+]/;
		var ret = [null, null, null];
		var el = txtDate;
		if (el.value) {
			ret = trim(el.value).split(dateSplitterRegexp);
			if (ret.length == 1) {
				ret = [];
				ret[0] = el.value.substr(0, 2);
				ret[1] = el.value.substr(2, 2);
				ret[2] = el.value.substr(4) || "";
			}
			if (ret.length == 3) {
				switch (ret[2].length) {
					case 2:
                                                var anio = Number(ret[2]);
                                                if (anio>50) {
                                                    ret[2] = 1900 + anio;
                                                }else {
                                                    ret[2] = 2000 + anio;
                                                }				
						break;
					case 4:
						break;
					default:
						ret[2] = ''; 
				}
			} else {
				return ret;
			}
			ret[0] =   pad( trim(ret[0]) , "0", -2) ;
			ret[1] =   pad( trim(ret[1]) , "0", -2) ;
		}
		return ret.reverse();
}

function refactorDate(txtDate) {
   var dateSplitter= "-";
   var value = preCookDate(txtDate);
   value = value.reverse().join(dateSplitter);
   if (checkdateNoMessage(value)) {
       txtDate.value=value;
   }
}

function validarTelefono(telefono)
{
  var incorrecto=telefono.length>0 && telefono.length<9;
  return !incorrecto;
}


function valida_longitud_campo(field,maxlong,message) {
	var tecla, in_value, out_value;

	if (field.value.length > maxlong) {
		in_value = field.value;
		out_value = in_value.substring(0,maxlong-1);
		field.value = out_value;
		alert(message);
		return false;
	}
	return true;
}
function maskNum(mascara,field){
	var mask = "";
	var num = "";
	var ok= false;
	mask = mascara;
	num = field.value;
	
	if (num.length == mask.length) ok = true;
	else if (num.length > mask.length) ok = false;
	else if (num.length < mask.length){
		mask = mask.substring(0, (mask.length - num.length) );
		field.value = mask + num;
		ok = true;		
	}
	
	return ok;
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

function comparaFechas(fecha1,fecha2, separador){	
		fecha1=trim(fecha1);
		fecha2=trim(fecha2);
		
		var f1 = fecha1.split(separador);
		var f2 = fecha2.split(separador);
		
		var year1 = f1[2];
		var month1 = f1[1] ;
		var day1 = f1[0];
		var year2 = f2[2];
		var month2 = f2[1] ;
		var day2 = f2[0];
		if (day1.substring(0,1)=='0') day1 = day1.substring(1,2);
		if (month1.substring(0,1)=='0') month1 = month1.substring(1,2);
		if (day2.substring(0,1)=='0') day2 = day2.substring(1,2);
		if (month2.substring(0,1)=='0') month2 = month2.substring(1,2);		
		
		//new Date(year, month, day, hours, minutes, seconds, milliseconds)
	    var d1 = new Date(parseInt(year1),parseInt(month1)-1,parseInt(day1));
	    var d2 = new Date(parseInt(year2),parseInt(month2)-1,parseInt(day2));	    
	    
	    if (d1.getFullYear() < d2.getFullYear()) return -1;	    	
	    else if (d1.getFullYear() == d2.getFullYear()){
	    	if (d1.getMonth() < d2.getMonth()) return -1;
	    	else if (d1.getMonth() == d2.getMonth()){
	    			if (d1.getDate() < d2.getDate()) return -1;
	    			else 	if (d1.getDate() == d2.getDate()) return 0;	    			
	    	}
	    }
	    return 1;
}

function replaceAll( text, busca, reemplaza ){
	while (text.toString().indexOf(busca) != -1)
	text = text.toString().replace(busca,reemplaza);
	return text;
}

function FormatNumberBy3(num, decpoint, sep) {
    // check for missing parameters and use defaults if so
    if (arguments.length == 2) {
      sep = ".";
    }
    if (arguments.length == 1) {
      sep = ".";
      decpoint = ",";
    }
    // need a string for operations
    num = num.toString();
    // separate the whole number and the fraction if possible
    a = num.split(decpoint);
    x = a[0]; // decimal
    y = a[1]; // fraction
    z = "";


    if (typeof(x) != "undefined") {
      // reverse the digits. regexp works from left to right.
      for (i=x.length-1;i>=0;i--)
        z += x.charAt(i);
      // add seperators. but undo the trailing one, if there
      z = z.replace(/(\d{3})/g, "$1" + sep);
      if (z.slice(-sep.length) == sep)
        z = z.slice(0, -sep.length);
      x = "";
      // reverse again to get back the number
      for (i=z.length-1;i>=0;i--)
        x += z.charAt(i);
      // add the fraction back in, if it was there
      if (typeof(y) != "undefined" && y.length > 0)
        x += decpoint + y;
    }      
    x=x.replace("-.","-");
    x=x.replace("-,","-");
    return x;
  }

