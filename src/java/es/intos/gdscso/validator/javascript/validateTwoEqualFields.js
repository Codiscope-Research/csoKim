
    function validateTwoEqualFields(form) {
        var isValid = true;
        var focusField = null;
        var i = 0;
        var fields = new Array();

        var oTwoEqualFields = eval('new ' + jcv_retrieveFormName(form) +  '_twoEqualFields()');

        for (var x in oTwoEqualFields) {

            if (!jcv_verifyArrayElement(x, oTwoEqualFields[x])) {
                continue;
            }
            var field = form[oTwoEqualFields[x][0]];
            var secondField = form[oTwoEqualFields[x][2]("secondProperty")];

            if (!jcv_isFieldPresent(field)) {
                continue;//de esto que se encargue el required
            }

            if (!jcv_isFieldPresent(secondField)) {
                continue;//de esto que se encargue el required
            }

            //Por ahora y para no complicar solo soportaremos hidden, text i password


            if ( (field.type == 'hidden' ||  field.type == 'text' || field.type == 'password')
                  &&
                 (secondField.type == 'hidden' ||  secondField.type == 'text' || secondField.type == 'password')
               ) 
            {

               if (field.value!=secondField.value) {
                  if ((i == 0) && (field.type != 'hidden')) {
                        focusField = field;
                  }
                  fields[i++] = oTwoEqualFields[x][1];
                  isValid=false;
               }
            }//end if hiddens i text comparables

        }//end loop todos TwoEqualFields
        if (fields.length > 0) {
           jcv_handleErrors(fields, focusField);
        }
        return isValid;
    }//end validateTwoEqualFields