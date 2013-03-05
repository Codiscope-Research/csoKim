 function maxlengthText(obj,mlength){
    		if (obj.getAttribute && obj.value.length>mlength){	
    			$('#'+obj.id).css('border', 'solid 1px red');
    			$('#'+obj.id).text(obj.value.substring(0,mlength));
    			alert(initErrorParams.txterrormaxlenght);
    		}else{
    			$('#'+obj.id).css('border', 'solid 1px rgb(135,155,179)');
    		}    			
    	}
function inicio() {      
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}


function creaPDF(){
	var frm = $("#GestionFacturasForm")[0];
	frm.submit();
}
function volverBusqueda(){
	window.location.href="PreBusquedaGestionFacturasAction.do?reload=yes";
	waiting();	
}
function doEnviar(){
	if(confirm(initconfirmParams.txtenviar)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="3";
		frm.submit();
	}
}

function doEditar(){
	var frm = $("#EditFacturaForm")[0];
	frm.submit();	
}

function doConforme(){
	if(confirm(initconfirmParams.txtconforme)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="4";
		frm.submit();
	}
}

function doFin(){
	if(confirm(initconfirmParams.txtfin)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="6";
		frm.submit();
	}
}

function doNoConforme(){
	if(confirm(initconfirmParams.txtnocomforme)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="5";
		 $("#dialog_inc").dialog("open");
	}
}
function doIncFact(){
	if(confirm(initconfirmParams.txtinc)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="9";
		 $("#dialog_inc").dialog("open");
	}
}
function doIncVal(){
	if(confirm(initconfirmParams.txtinc)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="8";
		 $("#dialog_inc").dialog("open");
	}
}

function doResolt(){
	var frm = $("#GestionFacturasChangeStatForm")[0];
	frm.newStat.value="5";
	frm.submit();
}
function doResoltNoConforme(){
	var frm = $("#GestionFacturasChangeStatForm")[0];
	frm.newStat.value="3";
	frm.submit();
}
function doResoltI2(){
	var frm = $("#GestionFacturasChangeStatForm")[0];
	frm.newStat.value="4";
	frm.submit();
}
function doResoltIncValid(){
	var frm = $("#GestionFacturasChangeStatForm")[0];
	frm.newStat.value="2";
	frm.submit();
}
function doCancel(){
	if(confirm(initconfirmParams.txtcancel)){
		var frm = $("#GestionFacturasChangeStatForm")[0];
		frm.newStat.value="7";
		frm.submit();
	}	
}
function openCloseDivTrams(id){
	$("#td_tram").html($("#sel_"+id).html());
	$("#dialog_tram").dialog("open");
}

function doDesar(){
	if(confirm(initconfirmParams.txtdesar)){
		var frm = $("#SaveFacturasForm")[0];
		frm.observaciones.value =$("#observ_fact").text();
		frm.submit();
	}
	
}
///////////////////////////////////
//variables per textos en locale
var initErrorParams=null ;
function InitErrorParams(txterrormaxlenght){		
		this.txterrormaxlenght=txterrormaxlenght;
}

var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txterrorsesio,txtempty){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txterrorsesio=txterrorsesio;
		this.txtempty=txtempty;
}
var initconfirmParams=null ;
function InitconfirmParams(txtenviar,txtdesar,txtcancel,txtinc,txtnocomforme,txtconforme,txtfin,txtpagada){		
		this.txtenviar=txtenviar;
		this.txtdesar=txtdesar;
		this.txtcancel=txtcancel;
		this.txtinc=txtinc;
		this.txtnocomforme=txtnocomforme;
		this.txtconforme = txtconforme;
		this.txtfin=txtfin;
		this.txtpagada=txtpagada;
}

var initParams=null ;
function InitParams(txtcontext){		
		this.txtcontext = txtcontext;
}


var oTableSrvFact=null;
$(document).ready(function() {
	
	
oTableSrvFact =$('#srv_fact').dataTable( {
			"iDisplayLength": 30,
			 "aoColumns" : [
			                  { sWidth: '130px' },
			                  { sWidth: '120px',"sClass": "centerNum" },
			                  { sWidth: '250px',"sClass": "centerNum" },
			                  { sWidth: '140px',"sClass": "centerNum" }
			            ],
			"sPaginationType": "full_numbers",
			"oLanguage": {
				  "sProcessing": "<img src='/ParticipadasIntosWeb/extjs/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
				  "sEmptyTable": initTableParams.txtempty,
			      "oPaginate": {
			        "sFirst": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
			        "sLast": initTableParams.txtlast+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'>",
			        "sNext": initTableParams.txtnext+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
			        "sPrevious": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
			      }
			    },
			"sScrollY": "100",		    
			"sScrollX": "152",	
		    "bScrollCollapse": true,
    		"bProcessing": false,
    		"bServerSide": true,
    		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxTableFactSrvAction.do',
    		"fnServerData": function( sUrl, aoData, fnCallback) {      			
    			waiting();
    			var idfact =$("#facturaId").val();    			
    			aoData.push({"name":"idfact", "value":idfact});
     		$.ajax( {
            	"url": sUrl,
            	"data": aoData,              
            	"dataType": "json",
            	"cache": false,
           		"success":function(json){  
           			if(json.error!=null){
           				$("#errorsajaxlabel").text(json.error);
                		$("#errorsajax").show();
           			}else{
	            		fnCallback(json);
	            		$("#srv_fact_paginate").hide();
           			}
            		hideWaiting();
            	},
            	"error":function(e){ 
            		$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
            		$("#errorsajax").show();
            		hideWaiting();
            	}
        	} );
    	}
	} );
$("#errorsajax").hide();
} );













