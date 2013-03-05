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

function openDialogFactores(){
	
	$("#dialog_factors").dialog("open");
	
}

function closeDialogFactores(){
	
	$("#dialog_factors").dialog("close");
	
}
 
function changeYearOfFactors(year){
	data = "idpartida="+partida.getId()+"&year="+year;
	$.ajax({
		  type: "POST",
		  url: '/'+initParams.txtcontext+'/AjaxLoadFactorsCrecimientoAction.do',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					  $.each(json.aaData, function(i, item) { 
							document.getElementById("i_"+item.month).value=item.factor;		
						  });
						  document.getElementById("importpactat").value= json.importe;
     			}
				  hideWaiting();
		  },
		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
  								$("#errorsajax").show();
  								hideWaiting();
		  					}
		});	
}

function guardarFactors(){

	var frm = $("#factorsForm")[0];
	frm.year.value = document.getElementById("f_any").value;
	frm.ene.value =document.getElementById("i_1").value;
	frm.feb.value =document.getElementById("i_2").value;
	frm.mar.value =document.getElementById("i_3").value;
	frm.abr.value =document.getElementById("i_4").value;
	frm.mai.value =document.getElementById("i_5").value;
	frm.jun.value =document.getElementById("i_6").value;
	frm.jul.value =document.getElementById("i_7").value;
	frm.ago.value =document.getElementById("i_8").value;
	frm.set.value =document.getElementById("i_9").value;
	frm.oct.value =document.getElementById("i_10").value;
	frm.nov.value =document.getElementById("i_11").value;
	frm.des.value =document.getElementById("i_12").value;
	frm.idPartida.value= partida.getId();
	frm.importpactat.value = document.getElementById("importpactat").value;
	frm.submit();
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
///////////////////////////////////

function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtNodata,txterrorsesio){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtNodata= txtNodata;
		this.txterrorsesio=txterrorsesio;
}
//variables per textos en locale
function InitParams(txtnomde,txtcreate,txtcreada,txtupdated,txtborrada,txtcreapartida,txtbutFactors, txterrordouble,txtconfirmdeletepartida, txtcontext){		
	
	this.txtnomde=txtnomde;
	this.txtcreate=txtcreate;
	this.txtcreada=txtcreada;
	this.txtupdated=txtupdated;
	this.txtborrada=txtborrada;
	this.txtcreapartida=txtcreapartida;	
	this.txtbutFactors=txtbutFactors;
	this.txterrordouble= txterrordouble;
	this.txtconfirmdeletepartida= txtconfirmdeletepartida;
	this.txtcontext = txtcontext;
}

var partida=null;
function reloadTables(id){
		
	var name = $("#partidasel option:selected").text();	 
	$("#namePartida").html(" <h3>"+name+" <a href='#' onclick='deletePartida("+id+")'> " +
			"<img src='/ParticipadasIntosWeb/web/img/icons/grey/delete.png' style='vertical-align:middle'></a>" +
			"<a href=\"#\" onclick=\"openDialogFactores()\" >" +
			"<img src='/ParticipadasIntosWeb/web/img/luna-blue-icons/png/24x24/edit.png' style='margin-left:5px; vertical-align:middle' alt='"+initParams.txtbutFactors+"' /></a></h3> ");
	//Creem la partida
	partida= new Partida(name,id);
	partida.created=true;
	
	//Recarreguem les taules
	oTableServ.fnDeleteRow( 0 ); 
	oTableServDisp.fnDeleteRow( 0 );
	
}
function deletePartida(id){
	
	//borrem de la BBDD
	var where_to= confirm(initParams.txtconfirmdeletepartida);
	if(where_to==false){
		return;
	}
	waiting();
	if(partida!=null){
		data = "action=deletePartida&idpartida="+partida.getId();
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
					  partida=null;
					  $("#namePartida").html("");
				      //Recarreguem les taules
					  oTableServ.fnDeleteRow( 0 ); 
					  oTableServDisp.fnDeleteRow( 0 );
					  $("#partidasel option:selected").text("");
					  $("#partidasel option:selected").remove();
					  alert(initParams.txtborrada);
         			}
				  hideWaiting();
			  },
			  error: function(e){
				  	$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
          			$("#errorsajax").show();
          			hideWaiting();
			  }
			});	
	}else{
		alert(initParams.txtcreapartida);
		hideWaiting();
	}
	//borrem la partida obj javascript


}



//Objecte partida-> per gestionar la generació d'una partida
function Partida(name,id){ 
	   
	   Partida.prototype.created=false;
	   this.name=name;
	   this.id=id;
	   this.getName=function(){ 
		   return this.name; 
		   };	
	   this.setName=function(name){
		   this.name=name;
		   };
	   this.getId=function(){ 
		   return this.id; 
		   } ;	
	   this.setId=function(id){
		   this.id=id;
		   };
} 



var oTableServ = null;
var oTableServDisp = null;

function newPartida(){
	
	//setegem les dades i recarreguem les taules
	$("#namePartida").html(" <h3>"+initParams.txtnomde+": <input type='text' id='newpartidaname' value='' /><a href='#' onclick='createPartida()'>"+initParams.txtcreate+"</a> </h3>");
	
	//creem una partida buida
	partida= new Partida("","");
	oTableServ.fnDeleteRow( 0 ); 
	oTableServDisp.fnDeleteRow( 0 );
}
function createPartida(){
	waiting();
	var name = $("#newpartidaname").val();
	var data = null;
	if(partida==null || partida.created==false){
	 //Si no esta creada fem un insert	
	 data = "action=createPartida&namepartida="+name;
	}else{
		//si ja esta creada fem un update del nom
		data = "action=updatePartida&namepartida="+name+"&idpartida="+partida.getId();	
	}	
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
					  if(partida!= null && partida.created==false){
						  partida.setName(name);
						  partida.setId(json.id);
						  partida.created=true;
						  $("#namePartida").html(" <h3>"+name+" <a href='#' onclick='deletePartida("+json.id+")'> <img src='/ParticipadasIntosWeb/web/img/icons/grey/delete.png' style='vertical-align:middle'></a>" +
								  "<a href=\"#\" onclick=\"openDialogFactores()\" >" +
								  "<img src='/ParticipadasIntosWeb/web/img/luna-blue-icons/png/24x24/edit.png' style='margin-left:5px; vertical-align:middle' alt='"+initParams.txtbutFactors+"' /></a>"+		
						  		  "</h3>");
						  //Afegim partida creada al select del filtre
						  
						  var sel = document.getElementById("partidasel");
						  var option=document.createElement("option");
						  option.text=name;		
						  option.value=json.id;
						  option.selected="selected";
						  sel.add(option,null);
						  
						//Recarreguem les taules
						oTableServ.fnDeleteRow( 0 ); 
						oTableServDisp.fnDeleteRow( 0 );
						alert(initParams.txtcreada);
						  
					  }else{
						  partida = new  Partida(name,json.id);
						  partida.created=true;
						  alert(initParams.txtupdated);				
					  }
     			}
			  hideWaiting();
		  },
		  error: function(e){
			  	$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
      			$("#errorsajax").show();
      			hideWaiting();
		  }
		});	
}
$(document).ready(function() {
	oTableServ =$('#srv_partida').dataTable( {
				"iDisplayLength": 12,
				"sPaginationType": "full_numbers",
				"oLanguage": {
					  "sProcessing": "<img src='/ParticipadasIntosWeb/extjs/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					  "sEmptyTable": initTableParams.txtNodata,
				      "oPaginate": {
				        "sFirst": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
				        "sLast": initTableParams.txtlast+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'>",
				        "sNext": initTableParams.txtnext+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
				        "sPrevious": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
				      }
				    },
				"sScrollY": "200",
			    "bScrollCollapse": true,
        		"bProcessing": true,
        		"bServerSide": true,
        		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxLoadTableSrvPartidaAction.do',
        		"fnServerData": function( sUrl, aoData, fnCallback) {
        			var idPart = null;
        	    	if(partida!=null && partida.created==true){    	
        	    	    idPart =  partida.getId();	
        	    	}
        	 		aoData.push({"name":"action", "value":"tableSrv"});
        	 		aoData.push({"name":"idpartida", "value":idPart});
        	 	    	
         		$.ajax( {
                	"url": sUrl,
                	"data": aoData,              
                	"dataType": "json",
                	"contentType": "application/json; charset=utf-8",
                	"cache": false,
               		"success":function(json){   
               			if(json.error!=null){
               				$("#errorsajaxlabel").text(json.error);
                    		$("#errorsajax").show();
               			}else{
               				fnCallback(json);
               			}
                	},
                	"error":function(e){ 
                		$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
                		$("#errorsajax").show();
                		hideWaiting();
                		}
            	} );
        	}
    	} );

oTableServDisp =$('#srv_disp').dataTable( {
	"iDisplayLength": 12,
	"sPaginationType": "full_numbers",
	"oLanguage": {
		  "sProcessing": "<img src='/ParticipadasIntosWeb/extjs/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
		  "sEmptyTable": initTableParams.txtNodata,
	      "oPaginate": {
	        "sFirst": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
	        "sLast": initTableParams.txtlast+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'>",
	        "sNext": initTableParams.txtnext+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
	        "sPrevious": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
	      }
	    },
	"sScrollY": "200",
    "bScrollCollapse": true,
	"bProcessing": true,
	"bServerSide": true,
	"sAjaxSource": '/'+initParams.txtcontext+'/AjaxLoadTableSrvDispAction.do',
	"fnServerData": function( sUrl, aoData, fnCallback) {
		var idPart = null;
    	if(partida!=null && partida.created==true){    	
    	    idPart =  partida.getId();	
    	} 	 
 		aoData.push({"name":"action", "value":"tableSrvDisp"});
 		aoData.push({"name":"idpartida", "value":idPart});
 	    	
		$.ajax( {
    	"url": sUrl,
    	"data": aoData,              
    	"dataType": "json",
    	"contentType": "application/json; charset=utf-8",
    	"cache": false,
   		"success":function(json){
   			
   			if(json.error!=null){
   				$("#errorsajaxlabel").text(json.error);
        		$("#errorsajax").show();
   			}else{
   				fnCallback(json);
   			}
    	},
    	"error":function(e){
    		$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
    		$("#errorsajax").show();
    		hideWaiting();}
	} );
}
} );

$( "#srv_partida tbody" ).sortable({
	connectWith: '#srv_disp tbody',
    opacity: 0.6,
    cursor: 'move',
    receive: function(event, ui) { 
    	//revem un element
    	var idPart = null;
    	if(partida!=null && partida.created==true){
    	    idPart =  partida.getId();	
    	}else{
    		alert(initParams.txtcreapartida);
    	}    	 
    	
    	var idsrv = ui.item.context.cells.item(0).firstChild.id;
    	var name = ui.item.context.cells.item(0).firstChild.name;
    
    	var data = "action=addsrv&idpartida="+idPart+"&idsrv="+idsrv+"&namesrv="+name;
    	$.ajax({
  		  type: "POST",
  		  url: '/'+window.context+'/AjaxLoadDataTableAction.do',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			if(json.error!=null){
   				$("#errorsajaxlabel").text(json.error);
        		$("#errorsajax").show();
   			}
  			oTableServ.fnDeleteRow( 0 ); 
  			oTableServDisp.fnDeleteRow( 0 );
  		  },
  		  error: function(e){ 
  			$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
    		$("#errorsajax").show();
    		hideWaiting();}
  		});	
    },
    remove: function(event, ui) {
    	//Hem tret un element
    	var idPart = null;
    	if(partida!=null && partida.created==true){
    	    idPart =  partida.getId();	
    	} 
    	
    	var idsrv = ui.item.context.cells.item(0).firstChild.id;        
    	
    	var data = "action=removesrv&idpartida="+idPart+"&idsrv="+idsrv;
    	$.ajax({
  		  type: "POST",
  		  url: '/'+window.context+'/AjaxLoadDataTableAction.do',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){		
  			if(json.error!=null){
   				$("#errorsajaxlabel").text(json.error);
        		$("#errorsajax").show();
   			}
  			oTableServ.fnDeleteRow( 0 ); 
  			oTableServDisp.fnDeleteRow( 0 );
  		  },
  		  error: function(e){ 
  			  $("#errorsajaxlabel").text(initTableParams.txterrorsesio);
  			  $("#errorsajax").show();
  			  hideWaiting();}
  		});
    }});
$( "#srv_disp tbody" ).sortable({
	connectWith: '#srv_partida tbody',
    opacity: 0.6,
    cursor: 'move',
    receive: function(event, ui) {
    	//revem un element
    	//No cal fer res    	
    },
    remove: function(event, ui) {
    	//Hem tret un element    	
    	//No cal fer res
  		}	
    });

$("#errorsajax").hide();
});

