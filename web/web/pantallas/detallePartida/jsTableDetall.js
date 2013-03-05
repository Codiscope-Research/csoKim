function reloadTableDetall(){
	oTablePartidas.fnDeleteRow( 0 );
}

function generaExcel() {
  var frm = $("#PartidaGeneraExcelForm")[0];  
  frm.idpartida.value=$("#idpartida").val();
  frm.year.value=$("#f_any").val();
  frm.submit();
}

$(document).ready(function() {
	
	oTablePartidas =$('#detall_partida_result').dataTable( {
			"iDisplayLength": 200,
			 "aoColumns" : [
			                  {"mDataProp":"servei", sWidth: '130px'},
			                  {"mDataProp":"ene", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"feb", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"mar", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"abr", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"mai", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"jun", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"jul", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"ago", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"set", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"oct", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"nov", sWidth: '50px',"sClass": "centerNum"},
			                  {"mDataProp":"des", sWidth: '50px',"sClass": "centerNum"}
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
			"sScrollX": "540",	
		    "bScrollCollapse": true,
    		"bProcessing": false,
    		"bServerSide": true,
    		 "fnInitComplete": function() {
    			 oTablePartidas.fnAdjustColumnSizing();
		         },

    		"sAjaxSource": '/'+window.context+'/AjaxTableDetallPartidaAction.do',
    		"fnServerData": function( sUrl, aoData, fnCallback) {      			
    			waiting();    			
    			var idany =$("#f_any").val(); 
    			var idpartida= $("#idpartida").val();
    			aoData.push({"name":"idany", "value":idany});
    			aoData.push({"name":"idpartida", "value":idpartida});
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
           				$("#detall_partida_result_paginate").hide();
           				fnCallback(json);
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