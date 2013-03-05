
    function validateNifCaixa(form) {
        
        var focusField = null;
        var i = 0;
        

        var ovalidateNifCaixa = eval('new ' + jcv_retrieveFormName(form) +  '_NifCaixa()');

        for (var x in ovalidateNifCaixa) {

            
            
            
            if (!jcv_verifyArrayElement(x, ovalidateNifCaixa[x])) { //valida que sea un objeto valido, si no es igual
                continue;
            }
            /////////////////////
            //alert('ee');
            

            //var fields = $("input[@name='+"ovalidateNifCaixa[x][0]"+']",form);
            fields = form[ovalidateNifCaixa[x][0]];
            if (fields.length==null) fields = new Array(fields);
            //fields=new Array();
            for (var i=0;i<fields.length;i++) { 
                //var field = form[ovalidateNifCaixa[x][0]];
                var field = fields[i];
                 

                if (!jcv_isFieldPresent(field)) {
                    continue;//de esto que se encargue el required
                }

                if (trim(field.value)=="") {
                   continue;//de esto que se encargue el required
                }

                //Por ahora y para no complicar solo soportaremos text 
                if (   field.type == 'text' ) {
                   var msgs = eval(ovalidateNifCaixa[x][1]);
                   txtCIFLongINC        = msgs[0];
                   txtCIFFormINC        = msgs[1];
                   txtCIF_INC           = msgs[2];
                   txtNoNifCaixa        = msgs[3];

                   //if (!validarCIFCaixa(field.value)) {
                   if (!validarCIF(field.value)) {
                      focusField = field;


                      focusField.focus();
                      focusField.className="error";
                      return false;
                   }
                }//end if hiddens i text comparables
             }
            //////////////////////////
        }//end loop todos validateNifCaixa
        //if (fields.length > 0) {
        //   jcv_handleErrors(fields, focusField);
        //}
        return true;
    }//end validateTwoEqualFields