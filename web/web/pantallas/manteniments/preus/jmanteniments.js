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

///////////////////////////////////
//variables per textos en locale
function InitParams(txtdialog,txtnodefinit,txtnoafegir,txttramnople,txttramnoborrar,txttram,txtdesde,txthasta,txtpreu, txtregSaved,txtconfirmTotsCso,txterrorsesio,txtempty, txtcontext){
	
	this.txtdialog=txtdialog;
	this.txtnodefinit=txtnodefinit;
	this.txtnoafegir=txtnoafegir;
	this.txttramnople=txttramnople;
	this.txttramnoborrar=txttramnoborrar;
	this.txttram=txttram;
	this.txtdesde=txtdesde;
	this.txthasta=txthasta;
	this.txtpreu=txtpreu;	
	this.txtregSaved=txtregSaved;
	this.txtconfirmTotsCso=txtconfirmTotsCso;
	this.txterrorsesio=txterrorsesio;
	this.txtempty=txtempty;
	this.txtcontext = txtcontext;
}
var preuObj=null;
$(document).ready(function() {
	$("#preufix").hide();
	$("#preuVariable").hide();
	$("#preuMensual").hide();
});

function getPreu(){
		
	//inicialitzem el preu
	var cso =$("#f_cso option:selected").val();
	var srv =$("#f_srv option:selected").val();	
	
	//amaguem els dos divs de preu fix o variable
	$("#preufix").hide();
	$("#preuVariable").hide();
	$("#preuMensual").hide();
	
	if(cso=='' || srv=='' || srv=='0' || cso=='0'){
		alert(initParams.txtconfirmTotsCso);
		return;
	}
	waiting();
	var data = "action=getPreu&preuCSO="+cso+"&preuSRV="+srv;
	$.ajax({
		  type: "POST",
		  url: '/'+initParams.txtcontext+'/AjaxLoadDataTableAction.do',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json!=null && json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else if(json!=null){
					var table=document.getElementById("table_trams");	
					//borrem rows de la taula si n'hi havia
					$("#insertOfPriceDIV").show("slow");
					while(table.rows.length!=0){
						table.deleteRow();
					}		
					if(json.id!=null && json.id!="null"){
						//Creem la taula de preu i creem el preu
						preuObj= new Preu(json.id,cso, srv);						
						preuObj.created=true;
						
						var tipo = json.tipo;
						
							
						  $.each(json.aaData, function(i, item) { 
							  	
							  if(json.aaData.length==i+1){
								  var tram = new Tram(item.desde, '', item.preu);
								  preuObj.setTram(tram);
								  var lasttram = preuObj.getLastTram();						
								  var row =table.insertRow();
								  var cell1=row.insertCell(0);		
								  cell1.innerHTML=lasttram;
								  document.getElementById("hasta_"+parseInt(preuObj.ntrams-1)).value="";
								  document.getElementById("hasta_"+parseInt(preuObj.ntrams-1)).disabled="disabled";
								  document.getElementById("hasta_"+parseInt(preuObj.ntrams-1)).style.background="#CCCCCC";
								  document.getElementById("img_"+parseInt(preuObj.ntrams-1)).src="/ParticipadasIntosWeb/web/img/nuevaFila.gif";
							  }else{
							  	var tram = new Tram(item.desde, item.hasta, item.preu);
								preuObj.setTram(tram);
								var lasttram = preuObj.getLastTram();						
								var row =table.insertRow();
								var cell1=row.insertCell(0);		
								cell1.innerHTML=lasttram;
								 $("#div_"+(parseInt(preuObj.ntrams-1))).hide();
							  }
							
							});		
						  
						  //Si té un tram és fix i sino variable.
						
						  if(preuObj.ntrams==1 && tipo =='0'){
								$('#preuF').attr('checked','checked'); 
								$('#preufixVal').val(preuObj.miArray[0].preu);
								$("#preufix").show("slow");
						  }else if (preuObj.ntrams>1 && tipo =='1'){
								$('#preuV').attr('checked','checked'); 
								$("#preuVariable").show("slow");
						  }else if (tipo=='2'){
							  	$('#preuR').attr('checked','checked'); 
								$('#preufixMen').val(preuObj.miArray[0].preu);
								$("#preuMensual").show("slow");
						  }
						  
						  preuObj.deleteTram();
					}else{
					
						//No hi ha registre
						preuObj= new Preu("",cso, srv);
						preuObj.created=true;							
						var tr = preuObj.getFirstTram();	
						var row =table.insertRow();
						var cell1=row.insertCell(0);		
						cell1.innerHTML=tr;
						
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

function changePreuOpenDiv(divToClose,divToOpen){

	$("#preufix").hide("slow");
	$("#preuVariable").hide("slow");
	$("#preuMensual").hide("slow");

	$("#"+divToOpen).show("slow");
}


//objecte tram
function Tram(desde, hasta, preu){
	this.desde=desde;
	this.hasta=hasta;
	this.preu=preu;
	Tram.prototype.created=false;
}

function changeTram(id, value){
	var idObj = id.split("_");
	if(preuObj!=null){
		if(preuObj.ntrams==idObj[1]){
			//l'ultim tram no cal update ja es guarda mes endavant
			return;
		}else{
			//Fem un update del tram ja creat
			numtram = idObj[1];
			if(idObj[0]=="desde"){
				preuObj.miArray[numtram].desde=value;
			}else if (idObj[0]=="hasta"){
				preuObj.miArray[numtram].hasta=value;	
			}else if (idObj[0]=="preu"){				
				preuObj.miArray[numtram].preu=value;
			}
		}
	}
}

function saveMen(value){
	if(preuObj!=null){
		preuObj.setMensual(value);
	}else{
		var cso =$("#f_cso option:selected").val();
		var srv =$("#f_srv option:selected").val();	
		window.preuObj= new Preu("",cso,srv);
	}
}

function savePreuMensual(){
	savePreu();
}

function savePreu(){
	//guardem el preu el preu
	//mirem que l'ultim rang del tram sigui infinit
	if(preuObj.toString()==''){
		alert(initParams.txtempty);
		return;
	}
	 var where_to= confirm(initParams.txtdialog);
    	  if (where_to== false)
    	  {
    		    return;
    	  }
    	  else {
    		  waiting();
    	  }
    	  var type="&type=";
	if(preuObj!=null &&($('#preuV').is(':checked') || $('#preuF').is(':checked')|| $('#preuR').is(':checked'))){
		//alert($('#preuV').is(':checked'));
		
	if($('#preuV').is(':checked')){
		
		saveLastTram();
	}

	if($('#preuF').is(':checked')){
		type=type+"F";
	}else if($('#preuV').is(':checked')){
		type=type+"V";
	}else{
		type=type+"M";
	}
	
	var totscso =$('#totscso').is(':checked');

	var data = "action=savePreu&preuCSO="+preuObj.cso+"&preuSRV="+preuObj.srv+"&array="+preuObj.toString()+"&totscso="+totscso+type;

	
	
	$.ajax({
		  type: "POST",
		  url: '/'+initParams.txtcontext+'/AjaxLoadDataTableAction.do',
		  dataType: 'json',
		  data: data,
		  success: function(json){		
			  if(json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
     				alert(initParams.txtregSaved);
     				getPreu();
     			}
			hideWaiting();
		  },
		  error: function(e){ 
			$("#errorsajaxlabel").text(initParams.txterrorsesio);
    		$("#errorsajax").show();
    		hideWaiting();
		  }
		});	
	}else{
		alert(initParams.txtnodefinit);
		hideWaiting();
	}
}
function saveLastTram(){
	//Guardem el tram i creem un tram a preu
	if(preuObj!=null){
		var rownum = preuObj.ntrams;
		var desde = $("#desde_"+rownum).val();
		var hasta = $("#hasta_"+rownum).val();
		var preu = $("#preu_"+rownum).val();
		if(desde==''|| preu==''){
			alert(initParams.txtnoafegir);
			return;
		}
		var tram = new Tram(desde, hasta,preu);
		preuObj.setTram(tram);		
	}else{
		alert(initParams.txtnoafegir);
	}
}

function addTram(){
	//Guardem el tram i creem un tram a preu
	if(preuObj!=null){
		
		var rownum = preuObj.ntrams;
		var desde = $("#desde_"+rownum).val();
		var hasta = $("#hasta_"+rownum).val();
		var preu = $("#preu_"+rownum).val();
		if(desde=='' || hasta=='' || preu==''){
			alert(initParams.txttramnople);
			return;
		}
		var tram = new Tram(desde, hasta,preu);
		preuObj.setTram(tram);
		
		var newtram = preuObj.getNewTram();
		var table=document.getElementById("table_trams");
		var row =table.insertRow();
		var cell1=row.insertCell(0);		
		cell1.innerHTML=newtram;	
	}else{
		alert(initParams.txtnoafegir);
	}
}
function deleteTram(){
	
	if(preuObj!=null){		
		
		var table=document.getElementById("table_trams");
		if(preuObj.ntrams!=0){
		table.deleteRow();
		//alert(preuObj.toString());
		preuObj.deleteTram();
		//alert(preuObj.ntrams);
		}else{
			alert(initParams.txttramnoborrar);
		}
			
	}else{
		alert(initParams.txttramnoborrar);
	}
}


function preuFix(value){
	//El preu fix és igual que el variable però amb un sol tram
	//alert("blur");
	if(preuObj!=null && value!=''){
		preuObj.miArray=  new Array();
		preuObj.ntrams=0;
		//alert("blur"+preuObj.miArray.length);
		var tram = new Tram(0, "",value);
		preuObj.setTram(tram);
		//alert("blur"+preuObj.miArray.length);
	}
}

function disablehasta(idhasta,img){
	
	if(document.getElementById(idhasta).disabled==true){
		
		document.getElementById(idhasta).disabled="";	
		document.getElementById(idhasta).style.background="#FFFFFF";
		document.getElementById(img).src="/ParticipadasIntosWeb/web/img/borraFila.gif";
	}else{
		document.getElementById(idhasta).value="";
		document.getElementById(idhasta).disabled="disabled";
		document.getElementById(idhasta).style.background="#CCCCCC";
		document.getElementById(img).src="/ParticipadasIntosWeb/web/img/nuevaFila.gif";
	}
}
//Objecte preu
function Preu(id, cso, srv){ 
	   
	   Preu.prototype.created=false;	
	   Preu.prototype.ntrams=0;
	   this.id=id;
	   this.mensual=0;
	   this.cso= cso;
	   this.srv = srv;
	   this.miArray = new Array();	   			
	   this.setTram= function(tram){
				 this.miArray[this.ntrams]=tram;
				 this.ntrams += 1;
	   };
	   this.getNewTram = function (){
		   if(this.ntrams>1){
			  // alert(this.ntrams);
			   $("#div_"+(this.ntrams-1)).hide();			   
		   }
		   return  initParams.txttram+" "+parseInt(this.ntrams+1)+": "+initParams.txtdesde+" <input id='desde_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='"+this.miArray[this.ntrams-1].hasta+"' > "+initParams.txthasta+" <input id='hasta_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='' ><div id=\"div_"+this.ntrams+"\" style=\"width: 20px; float: right; position: relative; right: 480px; top: 5px;\" ><a href=\"#\" onclick=\"disablehasta('hasta_"+this.ntrams+"','img_"+this.ntrams+"')\" > <img id='img_"+this.ntrams+"' src=\"/ParticipadasIntosWeb/web/img/borraFila.gif\" style=\"vertical-align:middle\"></a></div> &nbsp;&nbsp; "+initParams.txtpreu+"&nbsp;(&euro;): <input id='preu_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='' >";
	   };
	   this.getFirstTram = function (){
		   return  initParams.txttram+" "+parseInt(this.ntrams+1)+": "+initParams.txtdesde+" <input id='desde_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='0' > "+initParams.txthasta+" <input id='hasta_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='' > &nbsp;&nbsp; "+initParams.txtpreu+"&nbsp;(&euro;): <input id='preu_"+this.ntrams+"' onblur='changeTram(this.id,this.value)' type='text' value='' >";
	   };
	   this.deleteTram= function(){
		   this.miArray = this.miArray.slice(0,this.ntrams-1);
			 this.ntrams -=1;
			 $("#div_"+(this.ntrams)).show();	
	  };	
	  Preu.prototype.toString = function(){
		   stringPreu="";
		   $.each(this.miArray, function(i, item) {
			   if (typeof item.desde === "undefined"){}else{
			   stringPreu += ""+item.desde+","+item.hasta+","+item.preu+"-next-";
			   }
   		    });	   
		   if(this.mensual!=0){
			   stringPreu=this.mensual;
		   }
		   return stringPreu;	
	  };
	  this.getLastTram = function (){		  	
		  return  initParams.txttram+" "+parseInt(this.ntrams)+": "+initParams.txtdesde+" <input id='desde_"+parseInt(this.ntrams-1)+"' onblur='changeTram(this.id,this.value)' type='text' value='"+this.miArray[this.ntrams-1].desde+"' > "+initParams.txthasta+" <input id='hasta_"+parseInt(this.ntrams-1)+"' onblur='changeTram(this.id,this.value)' type='text' value='"+this.miArray[this.ntrams-1].hasta+"' ><div id=\"div_"+parseInt(this.ntrams-1)+"\" style=\"width: 20px; float: right; position: relative; right: 480px; top: 5px;\" ><a href=\"#\" onclick=\"disablehasta('hasta_"+parseInt(this.ntrams-1)+"','img_"+parseInt(this.ntrams-1)+"')\" > <img id='img_"+parseInt(this.ntrams-1)+"' src=\"/ParticipadasIntosWeb/web/img/borraFila.gif\" style=\"vertical-align:middle\"></a></div> &nbsp;&nbsp; "+initParams.txtpreu+"&nbsp;(&euro;): <input id='preu_"+parseInt(this.ntrams-1)+"' onblur='changeTram(this.id,this.value)' type='text' value='"+this.miArray[this.ntrams-1].preu+"' >";
	   };
	  this.setMensual= function(val){
		  this.mensual=val;
		  this.miArray = new Array();
		  this.ntrams=0;
	  };
} 

$("#errorsajax").hide();