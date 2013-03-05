//--------------------------------------------------
//Funciones trim
//--------------------------------------------------
function rtrim ( s ){
	return s.replace( /\s*$/, "" );
}
function ltrim ( s ){
	return s.replace( /^\s*/, "" );
}
function trim ( s ){
	return rtrim(ltrim(s));
}
function redondear(num,posiciones)
{
   return (Math.round(num*posiciones)/posiciones);
}
function convFloat(theNum)
{
  //CONDICIONS
  if (theNum.indexOf(",")!=-1)
  {
    //vol dir que te coma com a decimal
    theNum= theNum.replace(',' , '.');
  }
  return (theNum);
}
function isFloat(theNum) { 
	var numDec = 2;

	var theLen = theNum.length;
	var dotPos = theNum.indexOf(","); 
	var thePos = theLen - (dotPos+1);
	
	//CONDICIONS
	if (theNum.indexOf(".")!=-1) {
		//vol dir que tenim un punt com a decimal
		//return false;
	} else if (theNum.indexOf(",")!=-1){
		//vol dir que te coma com a decimal
		theNum= theNum.replace(',' , '.');
	}
	
	var isNum = (isNaN(theNum)) ? false:true; 
	if (dotPos!=-1) {
		//Hi ha decimals, s'ha de mirar si es supera el màxim
		var isAt = (thePos <= numDec) ? true:false; 
	} else {
		//No hi ha decimals
		var isAt=true;
	}
	var isOne = (theNum.indexOf(",",dotPos+1) == -1) ? true:false; 

	if((isNum) && (isAt) && (isOne)) { 
		return true; 
	}
	else { 
		return false; 
	} 
} 
/* Devuelve 1 si el error está en el dia, 2 si en el mes y 0 si correcto*/
function isDate(dia,mes,any,idioma,__sgEtiqueta)
{
  var __Catala=0;
  var __Castella=0;
   if (mes < 1 || mes > 12) { // check mes range
      switch(idioma){
                      case __Catala:
                              alert(__sgEtiqueta+": El mes ha d'estar entre els valors 1 i 12.");
                              break;
                      case __Castella:
                              alert(__sgEtiqueta+": El mes debe de estar entre los valores 1 y 12");
                              break;
              }
              return 2;
  }

  if (dia < 1 || dia > 31) {
      switch(idioma){
                      case __Catala:
                              alert(__sgEtiqueta+": El dia ha d'estar entre els valors 1 i 31.");
                              break;
                      case __Castella:
                              alert(__sgEtiqueta+": El dia debe de estar entre los valores 1 y 31");
                              break;
              }
              return 1;
  }

  if ((mes==4 || mes==6 || mes==9 || mes==11) && dia==31) {
      switch(idioma){
                      case __Catala:
                              alert(__sgEtiqueta+": Aquest mes no té 31 dies.");
                              break;
                      case __Castella:
                              alert(__sgEtiqueta+": Este mes no tiene 31 dias");
                              break;
              }

      return 2;
  }

  if (mes == 2) { // check for february 29th
      var isleap = (any % 4 == 0 && (any % 100 != 0 || any % 400 == 0));
      if (dia > 29 || (dia==29 && !isleap)) {
          switch(idioma){
                              case __Catala:
                                      alert(__sgEtiqueta+": Aquest any no es de traspas.");
                                      break;
                              case __Castella:
                                      alert(__sgEtiqueta+": Este año no es bisiesto.");
                                      break;
                      }
          return 1;
      }
  }
  return 0; 

}
//Pasa al siguiente con los saltos que se indican
function pasarSiguienteMasCampos(objeto,maxsize,saltos)
{
    //alert(event.keyCode);
    if (objeto.value.length==maxsize && ((event.keyCode>=48 && event.keyCode<=57) ||
                                         (event.keyCode>=96 && event.keyCode<=105)))
    {
        //voy al siguiente campo
        var i=0;
        while (i<=document.forms[0].length && document.forms[0][i]!=objeto) i++;
        document.forms[0][i+saltos].focus();
        //event.returnValue = false;
    }

}
// Pasa al siguiente caja de texto cuando el que está ahora está lleno
function pasarSiguiente(objeto,maxsize)
{
    pasarSiguienteMasCampos(objeto,maxsize,1);
}

function pasarSiguienteSimple (objeto) {
  if (objeto.value.length==objeto.getAttribute('maxlength') && ((event.keyCode>=48 && event.keyCode<=57) ||
                                         (event.keyCode>=96 && event.keyCode<=105))){
    var frm = objeto.form;
    var elems = frm.elements;
    for (i=0; i<elems.length; i++) {
      var elem = elems[i];
      if (elem==objeto) {
        var nextElem=elems[i+1];
        //si pot rebre el focus, li donem el focus
        if (nextElem.focus) nextElem.focus();
      }
    }
  }
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
    correcto=correcto && check_cuenta_entidad(oficina,cuenta); //valida los dos últimos dígitos de la cuenta en caso de ser entidad=2100
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

  var correcto=oficina.length==4 &&cuenta.length==10;
  correcto=correcto && !isNaN(oficina) && !isNaN(cuenta);

  if (correcto)
  {
      sumatorio = 0;
      Arraypesos = new Array(47, 29, 13, 7, 41, 31, 23, 17, 19, 7, 11, 13);
      cadena_check = repiteCaracter("0",4-oficina.length)+oficina + repiteCaracter("0",10-cuenta.length)+cuenta;
      for (contador=0;contador<Arraypesos.length;contador++)
      {
        var elem=cadena_check.substring(contador, contador+1);
        sumatorio = sumatorio + parseInt(elem,10) * Arraypesos[contador];
      }
      digito = sumatorio % 100;  // Sumamos antes por si la suma supera 99
  }
  correcto=correcto && digito==parseInt(cuenta.substring(8,10),10);

  return (correcto);

}

//Función que comprueba que la direccion de correo esté bien formada
function compruebaEmail(correo)
{
  var pos=correo.indexOf("@");
  var incorrecto= (pos==-1 || pos==0 || pos==correo.length-1 || (pos!=-1 && (correo.indexOf("@",pos+1)!=-1)) ||
      correo.indexOf(".")==-1) //miro que sólo haya una @
  pos=correo.indexOf(" ");
  incorrecto=incorrecto || pos>=0;
  return (!incorrecto);
}


//Función que devuelve un string con un carácter repetido n veces
function repiteCaracter(caracter,veces)
{
  var cadena="";
  for (var i=1;i<=veces;i++)
    cadena=cadena+caracter;
  return (cadena);
}
//me da la diferencia de dias entre dos fechas
// se supone que la 2 es posterior a la 1
function difDias(dia1,mes1,any1,dia2,mes2,any2)
{
  var fecha1=new Date(any1,mes1-1,dia1);
  var fecha2=new Date(any2,mes2-1,dia2);
  var restamili=fecha2.getTime()-fecha1.getTime();
  return (restamili/(1000*60*60*24));
  
}
function abrirVentana(url)
{
    var alto=650;
    var ancho=900;

    if (document.all)
        var xMax = screen.width, yMax = screen.height
    else
        if (document.layers)
            var xMax = window.outerWidth, yMax = window.outerHeight;
    	else
            var xMax = 640, yMax=480;
    var xOffset = (xMax - ancho)/2;
    var yOffset = ((yMax - alto)/2);
    yOffset=30;
    w=window.open(url,'Index','width='+ancho+',height='+alto+',screenX='+xOffset+',screenY='+yOffset+',top='+yOffset+',left='+xOffset+',scrollbars=yes,resizable=yes');
    w.focus();
}

function formatFloat(numeroDec)
{
  var snumeroDec=numeroDec+"";
  //pongo separadores de miles
  var anumeroDec=snumeroDec.split(".");
  var entera=anumeroDec[0];
  var fentera="";
  while (entera.length>0)
  {
    var tresp;
    if (entera.length>3)
    {
      tresp=entera.substring(entera.length-3,entera.length);
      entera=entera.substring(0,entera.length-3);
    }
    else
    {
      tresp=entera;
      entera="";
    }
    if (entera.length>0)
     fentera="."+tresp+fentera;
    else
     fentera=tresp+fentera;
  }
  if (anumeroDec.length==1)
    return(fentera);
  else
    return (fentera+","+anumeroDec[1].substring(0,2));
}
function validarNif(nif)
{
  var LetrasYnumeros="ABCDEFGJUVHNWPQRS0123456789TRWAGMYFPDXBNJZSQVHLCKE";
  var Letras10 = "ABCDEFGHIJ";
  var Pares = 0;
  var Impares = 0;
  nif = trim(nif);
  //Por peticion de SR.XAMMAR SI COMIENZA POR UNA "T" LO CONSIDERO CORRECTO
  if (nif.substring(0,1)=="T")
        return(true);
  if (nif.length != 9) 
  {
    if (nif.length==10)
    {
        if (nif.substring(0,1)=="X" && nif.substring(1,2)=="0")
            nif=nif.substring(0,1)+nif.substring(2,10)
        else
            return (false);
    }
    else
        return (false);
  }
  if (isNaN(nif.substring(1,8)))
    return(false);
  // Personas Juridicas
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
       return(false);
    }
    var producto=parseInt(nif.substring(0,8),10) % 23;
    var digito=producto+28;
    if (LetrasYnumeros.substring(digito-1,digito) == nif.substring(8,9))
      return (true);
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
       else
          return (false);
    }

    // Resto
    if (Letras10.substring(Digito-1,Digito)==nif.substring(8,9))
       return (true);
    return (false);
  }
  if (Fisicas)
  {
    if (FisicasDni)
    {
       if (isNaN(trim(nif.substring(0,8))))
        return (false);
       producto = parseInt(trim(nif.substring(0,8)),10) % 23;
    }
    else
    {
       if (isNaN(trim(nif.substring(1,8))))
          return (false);
       producto = parseInt(trim(nif.substring(1,8)),10) % 23;
    }
    Digito = producto + 28;
    if (LetrasYnumeros.substring(Digito-1,Digito) == nif.substring(8,9))
      return (true);
    return (false);
  }
  return (false);
}
/******  Nueva validación de nif/nie y cif ********/
function validarNif2(nif) 
{
	var temp=nif.toUpperCase();
	var cadenadni="TRWAGMYFPDXBNJZSQVHLCKE";						
 
	if (temp!==''){
		//si no tiene un formato valido devuelve error
		if ((!/^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$/.test(temp) && !/^[T]{1}[A-Z0-9]{8}$/.test(temp)) && !/^[0-9]{8}[A-Z]{1}$/.test(temp))
		{
			return false;
		}
 
		//comprobacion de NIFs estandar
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
 
		//algoritmo para comprobacion de codigos tipo CIF
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
/****** fin validación nif ********/

//Objeto oNumero
function oNumero(numero)
{
//Propiedades 
this.valor = numero || 0
this.dec = -1;
//Métodos 
this.formato = numFormat;
this.ponValor = ponValor;
//Definición de los métodos 
function ponValor(cad)
{
if (cad =='-' || cad=='+') return
if (cad.length ==0) return
if (cad.indexOf('.') >=0)
    this.valor = parseFloat(cad);
else 
    this.valor = parseInt(cad);
} 
function numFormat(dec, miles)
{
var num = this.valor, signo=3, expr;
var cad = ""+this.valor;
var ceros = "", pos, pdec, i;
for (i=0; i < dec; i++)
ceros += '0';
pos = cad.indexOf('.')
if (pos < 0)
    cad = cad+"."+ceros;
else
    {
    pdec = cad.length - pos -1;
    if (pdec <= dec)
        {
        for (i=0; i< (dec-pdec); i++)
            cad += '0';
        }
    else
        {
        num = num*Math.pow(10, dec);
        num = Math.round(num);
        num = num/Math.pow(10, dec);
        cad = new String(num);
        }
    }
pos = cad.indexOf('.')
if (pos < 0) pos = cad.lentgh
if (cad.substr(0,1)=='-' || cad.substr(0,1) == '+') 
       signo = 4;
if (miles && pos > signo)
    do{
        expr = /([+-]?\d)(\d{3}[\.\,]\d*)/
        cad.match(expr)
        cad=cad.replace(expr, RegExp.$1+','+RegExp.$2)
        }
while (cad.indexOf(',') > signo)
    if (dec<0) cad = cad.replace(/\./,'')
cad=cad.replace('.','_');
cad=cad.replace(',','.');
cad=cad.replace('_',',');
return cad;
}
}//Fin del objeto oNumero:

function rellenaCeros(campo,cuantos)
{
  if (trim(campo.value).length>0)
    campo.value=repiteCaracter("0",cuantos-campo.value.length)+campo.value;
}
/*function abrirVentana(url,titulo,alto,ancho)
{
  var xMax = screen.width, yMax = screen.height
  var xOffset = (xMax - 200)/2, yOffset = (yMax - 200)/2;

  var opciones='width='+ancho+',height='+alto+',screenX='+xOffset+',screenY='+yOffset+',top='+yOffset+',left='+xOffset+',scrollbars=yes,resizable=yes';
  window.open(url,titulo);
}*/
function abrirVentana(url)
{
  window.open(url);
}
function validaTelefono(telefono)
{
  var correcto=true;
  correcto=!isNaN(telefono);
  if (trim(telefono).length>0)
  {
    correcto=correcto && trim(telefono).length>=9;
    correcto=correcto && (telefono.substring(0,1)=="9" || telefono.substring(0,1)=="6");
  }
  return (correcto);
}

var __Catala=0;
var __Castella=1;

function fechaCorrecta(dateStr,formato,idiomamisatges) 
{
	return(fechaCorrectaMsg(dateStr,formato,true,idiomamisatges));
}

function fechaCorrectaMsg(dateStr,formato,verMensaje, idiomamisatges) 
{
     var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;

		switch(formato)
		{
			case "dd/mm/aaaa":
				datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
				break;
			case "dd/mm/aa":
				datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/;
				break;
			case "aa/mm/dd":
				datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/;
				break;
			case "0000":
				datePat = /^(\d{4})$/;
				break;
			case "ddmmaa":
				datePat = /^(\d{1,2})(\d{1,2})(\d{1,2})$/;
				break;
			case "dd-mm-aaaa":
				datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
				break;
		}

    var matchArray = dateStr.match(datePat); // is the format ok?
    if (matchArray == null) 
    {
       if (verMensaje==true)
			 {
	        switch(idiomamisatges)
					{
						case __Catala:
							alert(__sgEtiqueta+": Ha d'introduïr un valor amb el format "+formato);
							break;
						case __Castella:
							alert(__sgEtiqueta+": Debe de introducir un valor con el formato "+formato);
							break;
					}
			 }
       return false;
    }
    else
    {
      return (comprobarDiaMesAnyo(matchArray,idiomamisatges,verMensaje));
    }
}

function comprobarDiaMesAnyo(matchArray,idiomamisatges,verMensaje)
{
  var day=matchArray[1];
  var month=matchArray[3];
  var year=matchArray[5];

  if (month < 1 || month > 12) { // check month range
        if (verMensaje)
        {
                switch(idiomamisatges){
			case __Catala:
				alert(__sgEtiqueta+": El mes ha d'estar entre els valors 1 i 12.");
				break;
        		case __Castella:
				alert(__sgEtiqueta+": El mes debe de estar entre los valores 1 y 12");
				break;
		}
        }
		return false;
    }

    if (day < 1 || day > 31) {
      if (verMensaje)
      {
        switch(idiomamisatges){
			case __Catala:
				alert(__sgEtiqueta+": El Dia ha d'estar entre els valors 1 i 31.");
				break;
        		case __Castella:
				alert(__sgEtiqueta+": El Dia debe de estar entre los valores 1 y 31");
				break;
		}
       }
		return false;
    }

    if ((month==4 || month==6 || month==9 || month==11) && day==31) {
      if (verMensaje)
      {
        switch(idiomamisatges){
			case __Catala:
				alert(__sgEtiqueta+": Aquest mes no té 31 dies.");
				break;
        		case __Castella:
				alert(__sgEtiqueta+": Este mes no tiene 31 dias");
				break;
		}
       }
		
        return false;
    }

    if (month == 2) { // check for february 29th
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day==29 && !isleap)) {
          if (verMensaje)
          {
            switch(idiomamisatges){
				case __Catala:
					alert(__sgEtiqueta+": Aquest any no es de traspas.");
					break;
        			case __Castella:
					alert(__sgEtiqueta+": Este año no es bisiesto.");
					break;
			}
            }
            return false;
        }
    }
    return true;
}
//Función que comprueba que la direccion de correo esté bien formada
function compruebaEmail(correo)
{
  if (trim(correo).length>0)
  {
    var pos=correo.indexOf("@");
    var incorrecto= (pos==-1 || pos==0 || pos==correo.length-1 || (pos!=-1 && (correo.indexOf("@",pos+1)!=-1)) ||
        correo.indexOf(".")==-1) //miro que sólo haya una @
    pos=correo.indexOf(" ");
    incorrecto=incorrecto || pos>=0;
    return (!incorrecto);
  }
  else
    return (true);
}
