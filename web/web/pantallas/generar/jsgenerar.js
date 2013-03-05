
function inicio() {      
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}



function resetFactors(){
	for(i=1; i<13 ; i++)
	 document.getElementById("i_"+i).value='';
}
function filtrar(){
	oTableCsoSrvFact.fnDeleteRow( 0 );
}
function openfactSrv(id){
	idcso=id;
	factura = new Factura(id);
	oTableSrvFact.fnDeleteRow( 0 );	
	oTableSrvFact.fnAdjustColumnSizing();
	setTimeout(function(){oTableSrvFact.fnAdjustColumnSizing();},500);	
	ompleSelectHomonimes(id);
	resetFactors();
}

function openDialogFactores(){
	$("#factura_factors").dialog("open");
}

function closeDialogFactores(){
	$("#factura_factors").dialog("close");
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
		alert(initErrorParams.txtnumericerror);
	}
} 

function guardarFactors(){

	var frm =  $("#GenerarFacturasForm")[0];

	if(confirm(initErrorParams.txtConfirmChangeFc)){			
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
		frm.code.value=factura.factHomonima;	
		closeDialogFactores();
	}
}

function ompleSelectHomonimes(id){
	Ext.Ajax.request({
		   url: '/'+initParams.txtcontext+'/AjaxSelectFacturesHomonimesAction.do',
		   success: function ( result, request ) {
			  
               var jsonData = Ext.util.JSON.decode(result.responseText);
               $("#selfacturesHomonimes option").remove();	
               $.each(jsonData.aaData, function(i, item) {             	   
            	 
            	   $("#selfacturesHomonimes").append('<option value='+item.descripcio+'>'+item.descripcio+'</option>');
               });    
               $("#selfacturesHomonimes option:first").attr('selected','selected');  	 	
		   },	
		   headers: {
		       'my-header': 'id'
		   },
		   params: { id: id }
		});
}

function loadFacturaHomonima(codeFactHomonima){
	if(factura!=null){
		resetFactors();
		factura.setFactHomonima(codeFactHomonima);
		oTableSrvFact.fnDeleteRow( 0 );	
	}	
}

function goToInicio(){
	window.location.href="/"+initParams.txtcontext+"/portal.do";
	waiting();	
}

function saveSrv(check){

	if(check.checked==true){
		//afegim servei a la factura
		var srv = new Srv(check.id);
		if(factura!=null){
			factura.setSrv(srv);
		}		
	}else{
		//teiem  servei de la factura
		if(factura!=null){
			factura.deleteSrv(check.id);
		}
	}
	
}
function saveAllChecksToFacturaObj(){

	$(oTableSrvFact.fnSettings().aoData).each(function (i,item){	
		
		var trs =$("#facsrv_res tbody").find("tr");
		var tr = trs[i];
		var input = tr.cells.item(0);
		var realcheck = input.firstChild;
		
		
		if (realcheck.checked==true){

			var srv = new Srv(realcheck.id);
			if(factura!=null){
				factura.setSrv(srv);
			}	
		}
	});
}

function saveFactServer(){
	
	var frm = $("#GenerarFacturasForm")[0];
	frm.idCso.value=factura.cso;
	if(factura.nSrv==0){
		alert(initErrorParams.txtnoserveis);
		return;
	}
	frm.serveis.value=factura.arraySrv.toString();
	frm.code.value=factura.factHomonima;
	any = $("#f_any").val();
	mes = $("#f_mes").val();
	frm.month.value=mes;
	frm.year.value=any;	
	frm.submit();
	resetFactors();
	waiting();

}

///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtempty){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtempty = txtempty;
}

var initErrorParams=null ;
function InitErrorParams(txterrorHomonima,txtnumericerror,txtConfirmChangeFc, txtnoserveis,txterrorsesio){		
		this.txterrorHomonima=txterrorHomonima;	
		this.txtnumericerror=txtnumericerror;
		this.txtConfirmChangeFc=txtConfirmChangeFc;
		this.txtnoserveis=txtnoserveis;
		this.txterrorsesio=txterrorsesio;
}

var initParams=null ;
function InitParams(txtcontext){		
		this.txtcontext = txtcontext;
}


var factura = null;

//objecte per guardar la factura
function Factura(idCso){
	this.factHomonima="";
	this.cso=idCso;
	this.arraySrv= new Array();
	this.nSrv=0;
	this.setFactHomonima= function(code){
		this.factHomonima=code;
	};
	this.setSrv= function(srv){
		 this.arraySrv[this.nSrv]=srv;
		 this.nSrv += 1;
	};	
	this.deleteSrv= function(id){
		var arrayBis = this.arraySrv;
		var num=this.nSrv;
		  $.each(this.arraySrv, function(i, item) {
			  if(item.id==id){
				  //borrem
				  arrayBis.splice(i, 1);
				  num= num-1;
				  
			  }
			  
  		   });	
		  this.nSrv=num;
		  this.arraySrv=arrayBis;
	};
}
Array.prototype.toString= function(){
	var str="";
	  $.each(this, function(i, item) {
		  str=str+","+item.id+"";
	  });
	  return str;	
};

//Objecte srv de la factura
function Srv(id){
	this.id=id;
}

var idcso=0;
var oTableCsoSrvFact=null;
var oTableSrvFact=null;
$(document).ready(function() {

	oTableSrvFact = $('#facsrv_res').dataTable( {
		"iDisplayLength": 200,
		 "aoColumns" : [
		                  {"mDataProp":"servei", sWidth: '200px'},
		                  {"mDataProp":"factura", sWidth: '177px'},		                  		                			               
		                  {"mDataProp":"estatFactura", sWidth: '277px', "bSortable": false}
		            ],
		"sPaginationType": "full_numbers",
		"oLanguage": {
			  "sProcessing": "<img src='/ParticipadasIntosWeb/extjs/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
			  "sEmptyTable": initTableParams.txtempty,
		      "oPaginate": {
		        "sFirst": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
		        "sLast": initTableParams.txtlast+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'>",
		        "sNext": initTableParams.txtnext+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
		        "sPrevious": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious,
		        "sEmptyTable": initTableParams.txtempty,
		        "sInfoEmpty": "No entries to show",
		        "sZeroRecords": "No records to display"
		      }
		    },
		"sScrollY": "200px",	
		"sScrollX": "900px",	
	    "bScrollCollapse": false,
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxTableSrvDispAction.do',
		"fnServerData": function( sUrl, aoData, fnCallback) {   
			any = $("#f_any").val();
			mes = $("#f_mes").val();
			if(factura!=null){
				aoData.push({"name":"code", "value":factura.factHomonima});
			}
			aoData.push({"name":"idany", "value":any});
			aoData.push({"name":"idmes", "value":mes});
			aoData.push({"name":"idcso", "value":idcso});		
		$.ajax( {
       	"url": sUrl,
       	"data": aoData,              
       	"dataType": "json",
       	"cache": false,
      		"success":function(json){  
      			if(json.errorAjax!=null){
       				$("#errorsajaxlabel").text(json.errorAjax);
            		$("#errorsajax").show();
       			}else{
		       		fnCallback(json);
		       		if(json.error!=null && json.error=='yes'){
		       			alert(initErrorParams.txterrorHomonima);
		       			$("#crearfacturabut").attr("disabled","disabled");
		       			$("#buteditfactors").hide();
		       		}else if(json.error!=null && json.error=='no'){       		
		       			saveAllChecksToFacturaObj();
		       		}else{
		       			$("#crearfacturabut").attr("disabled","");
		       			$("#buteditfactors").show();
			       		if(json.iTotalRecords!='0'){
			       			$("#crearFact").show('slow');
			       			$("#norecords").html("");	       		
			       		}else{
			       			$("#crearFact").hide();
			       			$("#norecords").html(initTableParams.txtempty);
			       		}
		       		}
       			}
       	},
       	"error":function(e){ 
       		$("#errorsajaxlabel").text(initErrorParams.txterrorsesio);
    		$("#errorsajax").show();
    		hideWaiting();
       	}
   	} );
	}
} ); 
	oTableCsoSrvFact=  $('#csosrv_result').dataTable( {
		"iDisplayLength": 6,
		 "aoColumns" : [
		                  {"mDataProp":"cso", sWidth: '230px'},
		                  {"mDataProp":"tServeis", sWidth: '100px' },
		                  {"mDataProp":"tFactures", sWidth: '100px' },		                			                
		                  {"mDataProp":"estat", sWidth: '100px',"bSortable": false}
		            ],
		"sPaginationType": "full_numbers",
		"oLanguage": {
			  "sProcessing": "<img src='/ParticipadasIntosWeb/extjs/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
			  "sEmptyTable": initTableParams.txtempty,
		      "oPaginate": {
		        "sFirst": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
		        "sLast": initTableParams.txtlast+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'>",
		        "sNext": initTableParams.txtnext+"&nbsp;<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
		        "sPrevious": "<img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious,
		        "sEmptyTable": initTableParams.txtempty
		      }
		    },
		"sScrollY": "120px",		    
		"sScrollX": "550px",	
	    "bScrollCollapse": true,
		"bProcessing": true,
		"bAutoWidth": true,
		"bServerSide": true,
		"sAjaxSource": '/'+initParams.txtcontext+'/AjaxTableCsoSrvAction.do',
		"fnServerData": function( sUrl, aoData, fnCallback) {   
			any = $("#f_any").val();
			mes = $("#f_mes").val();			
			aoData.push({"name":"idany", "value":any});
			aoData.push({"name":"idmes", "value":mes});			
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
        	},
        	"error":function(e){
        		$("#errorsajaxlabel").text(initErrorParams.txterrorsesio);
        		$("#errorsajax").show();
        		hideWaiting();
        	}
    	} );
	}
} );
	
	$("#csosrv_result tbody").click(function(event) {
		$(oTableCsoSrvFact.fnSettings().aoData).each(function (){	
			$(this.nTr).addClass('select_ro');
		});
		$(event.target.parentNode).removeClass('odd');
		$(event.target.parentNode).removeClass('even');
		
		if(event.target.parentNode.firstChild.firstChild!=null ){
			var id=event.target.parentNode.firstChild.firstChild.id;
			$("#"+id).click();
		}else {
			var id=event.target.parentNode.firstChild.id;
			$("#"+id).click();
		}
	});

	$("#factura_factors").dialog({
		autoOpen : false,
		modal : true,
		autoSize : true,
		position : 'center',
		height : 540,
		width : 500,
		resizable : 'false',
		open : function(event, ui) {
			$(".ui-dialog-titlebar").hide();
			data = "codefactura="+factura.factHomonima;
			$.ajax({
				  type: "POST",
				  url: '/'+initParams.txtcontext+'/AjaxLoadFactorsFacturaAction.do',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
						if(json.error!=null){
	           				$("#errorsajaxlabel").text(json.error);
	                		$("#errorsajax").show();
	           			}else{
							 $.each(json.aaData, function(i, item) { 
								 if(document.getElementById("i_"+item.month).value=='')
									 document.getElementById("i_"+item.month).value=item.factor;		
							 });		
	           			}
					  hideWaiting();
				  },
				  error: function(e){ 
					  $("#errorsajaxlabel").text(initErrorParams.txterrorsesio);
					  $("#errorsajax").show();
					  hideWaiting();
				  					}
				});	
		}
	}).parent().find('.ui-dialog-titlebar-close').hide();
	
	$("#errorsajax").hide();
} );













