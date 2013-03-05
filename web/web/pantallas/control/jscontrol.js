
function inicio() {      
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}
function filtrar(){
	oTableContrFact.fnDeleteRow( 0 );
}

function generaExcel() {
	var frm = $("#GestionFacturasGeneraExcelForm")[0];
  frm.idCso.value=dadesDialog.id;
  frm.month.value=dadesDialog.month;
  frm.year.value=dadesDialog.year;
  frm.submit();
	waiting();
}

///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txterrorsesio,txtNodata){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txterrorsesio=txterrorsesio;
		this.txtNodata=txtNodata;
}
var dadesDialog= null;
function DadesDialog (idCso, month, year){
	this.id=idCso;
	this.month=month;
	this.year=year;
}

var initParams=null ;
function InitParams(txtcontext){				
		this.txtcontext = txtcontext;
}

//inicialitza les dades de la taula del dialog
dadesDialog= new DadesDialog ("0", "0", "0");

//recarrega la taula del dialog
function loadTable(){
	oTableContrSrvFact.fnDeleteRow( 0 );
}


var oTableContrFact=null;
var oTableContrSrvFact=null;
$(document).ready(function() {
	//Taula del dialog popup
	oTableContrSrvFact=  $('#fac_result').dataTable( {
		"iDisplayLength": 12,
		 "aoColumns" : [
		                  {"mDataProp":"servei",sWidth: '30%'},
		                  {"mDataProp":"factura" },
		                  {"mDataProp":"volum","sClass": "centerNum" },
		                  {"mDataProp":"preu","sClass": "centerNum" },			                			                
		                  {"mDataProp":"estatFactura", sWidth: '30%'}
		            ],
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
		"sScrollY": "125px",
		"bAutoWidth": false,
	    "bScrollCollapse": true,
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxTableCtrlSrvAction.do',
		"fnServerData": function( sUrl, aoData, fnCallback) {   
			aoData.push({"name":"idcso", "value":dadesDialog.id});
			aoData.push({"name":"idany", "value":dadesDialog.year});
			aoData.push({"name":"idmes", "value":dadesDialog.month});			
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
       			}
       			
       			$("#fac_result_paginate").hide();
        	},
        	"error":function(e){ 
        		
		  		
        		$("#errorsajaxlabel").text(initTableParams.txterrorsesio);
        		$("#errorsajax").show();
        		hideWaiting();
        	}
    	} );
	}
} );

	
	oTableContrFact =$('#ctrl_result').dataTable( {
			"iDisplayLength": 12,
			 "aoColumns" : [
			                  {"mDataProp":"cso", sWidth: '130px'},
			                  {"mDataProp":"ene", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"feb", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"mar", sWidth: '40px',"bSortable": false, "sClass": "centerBola" },
			                  {"mDataProp":"abr", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"mai", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"jun", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"jul", sWidth: '40px',"bSortable": false,"sClass": "centerBola" },
			                  {"mDataProp":"ago", sWidth: '40px',"bSortable": false,"sClass": "centerBola"  },
			                  {"mDataProp":"set", sWidth: '40px',"bSortable": false,"sClass": "centerBola"  },
			                  {"mDataProp":"oct", sWidth: '40px',"bSortable": false,"sClass": "centerBola"  },
			                  {"mDataProp":"nov", sWidth: '40px',"bSortable": false,"sClass": "centerBola"  },
			                  {"mDataProp":"des", sWidth: '40px',"bSortable": false,"sClass": "centerBola"  }
			            ],
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
			"sScrollY": "300",		    
			"sScrollX": "140",	
		    "bScrollCollapse": true,
    		"bProcessing": false,
    		"bServerSide": true,
    		 "fnInitComplete": function() {
    			 oTableContrFact.fnAdjustColumnSizing();
		         },

    		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxTableCtrlAction.do',
    		"fnServerData": function( sUrl, aoData, fnCallback) {      			
    			waiting();
    			var idcso =$("#f_cso").val();
    			var idany =$("#f_any").val();
    			aoData.push({"name":"idcso", "value":idcso});
    			aoData.push({"name":"idany", "value":idany});
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
	           			$("#iTotalRecords").html(json.iTotalRecords);            		       		
	            		fnCallback(json);
           			}
           			$("#ctrl_result_paginate").hide();
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













