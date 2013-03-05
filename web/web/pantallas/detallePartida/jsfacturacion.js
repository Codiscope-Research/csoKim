
function inicio() {      
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}

(function() {
	
	
    Ext.apply(Ext.util.Format,{
        eurMoney : function(v) {
            v = (Math.round((v-0)*100))/100;
            v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
            v = String(v);
            var ps = v.split('.'),
                whole = ps[0],
                sub = ps[1] ? '.'+ ps[1] : '.00',
                r = /(\d+)(\d{3})/;
            while (r.test(whole)) {
                whole = whole.replace(r, '$1' + ',' + '$2');
            }
            v = whole + sub;
            if (v.charAt(0) == '-') {
                return '-€' + v.substr(1);
            }
            
            var s = v.split('.');
            s[0] = s[0].replace(',','.');
            
            v=s[0]+","+s[1];                       
            
            return   v+ "€";
        }
    });
})();

Ext.chart.Chart.CHART_URL = '/ParticipadasIntosWeb/extjs/resources/charts.swf';

Ext.onReady(function(){
	
    var store = new Ext.data.JsonStore({
        fields: ['year', 'importpactat', 'importestimat', 'importconsumit']
    });
    store.loadData(myData);
    new Ext.Panel({
        width: 900,
        height: 200,
        renderTo: 'container',
        title: '',
        items: {
            xtype: 'barchart',
            store: store,
            yField: 'year',
            xAxis: new Ext.chart.NumericAxis({
                stackingEnabled: true,                
                labelRenderer: Ext.util.Format.eurMoney
            }),
            series: [{         
                xField: 'importpactat',
                displayName: initGraficParams.txtpactat
            },{
                xField: 'importestimat',
                displayName: initGraficParams.txtestimate
            },{
                xField: 'importconsumit',
                displayName: initGraficParams.txtconsumit
            }]
        }
    });
});

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
		this.txtNodata = txtNodata;
}

var initGraficParams=null ;
function InitGraficParams(txtpactat,txtestimate,txtconsumit){		
		this.txtpactat=txtpactat;		
		this.txtestimate=txtestimate;
		this.txtconsumit=txtconsumit;
		
}