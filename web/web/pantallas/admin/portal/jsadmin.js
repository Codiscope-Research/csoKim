function inicio() {

	$("#divCargando").hide();
	$("#divCargandoImg").hide();


	window.setTimeout(function () { //Timeout pq sembla k en ocasions hi ha algun lio de threads que deixa la primera taula visible quan no ho hauria d'estar ...

		var taulaOrigen = getCookie("taulaOrigen");

		if(typeof taulaOrigen != "undefined" && taulaOrigen.length!=0)
			toggle(taulaOrigen);

	}, 100);

 }//end inicio

function goTo(action){
  var frm=document.getElementById('frmAdmin');
  frm.action=action;
  frm.submit();
	waiting();
}

function toggle(id){
	$(".taula").hide();
	$("#"+id).toggle();

	//var frm=document.getElementById('frmAdmin');
	//frm.taulaOrigen.value=id;
	setCookie("taulaOrigen", id, 1);

}



function getCookie(c_name)
{
	var i,x,y,ARRcookies=document.cookie.split(";");
	for (i=0;i<ARRcookies.length;i++)
	{
	  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
	  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
	  x=x.replace(/^\s+|\s+$/g,"");
	  if (x==c_name)
	    {
	    return unescape(y);
	    }
	}
}

function setCookie(c_name,value,exdays)
{
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
	document.cookie=c_name + "=" + c_value;
}


