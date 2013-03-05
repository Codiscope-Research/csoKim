<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!-- begin :: total documents, total pagines, registres per pagina  -->
 
<SCRIPT language="javascript">

 function goToPage(txt, actual,max) {
           if (13!=event.keyCode) {
             return;
           }
           
           var pag = parseInt(txt.value);
           if (pag<1 || pag>max) {
             alert("<%= request.getParameter("txt.pagina.limite")%>"+max);
//             txt.className="error";
//             txt.focus();
//             txt.select();
             txt.value=actual;
             return;
           }else {
//             txt.className="text";
           }
           
           //var frm = document.getElementById('paginacion');
           //frm.pagina.value=txt.value;
           //frm.submit();
           
           <%= request.getParameter("js.goToPage")%>
 }

</SCRIPT>

<div  class="component">
<div style="float:left"> 
   <strong><c:out value='<%= request.getParameter("txt.actual.pagina")%>'/>:</strong> 
   <span class="span_separa" >
      <input type="text" size="3" class="text" maxlength="5" id="numPag" name="numPag" value="<c:out value='<%= request.getParameter("num.actual.pagina")%>'/>" onkeypress="return /[0-9]/.test(String.fromCharCode(event.keyCode));" onkeyup="goToPage(this, <%= request.getParameter("num.actual.pagina")%>, <%= request.getParameter("num.ultima.pagina")%>)"  />
      de <c:out value='<%= request.getParameter("num.ultima.pagina")%>'/></span>


   <strong><c:out value='<%= request.getParameter("txt.total.registros")%>'/>:</strong> 
   <span class="span_separa" ><c:out value='<%= request.getParameter("num.total.registros") %>'/></span>   
      
   <strong>   <c:out value='<%= request.getParameter("txt.registros.pagina")%>'/>:
   </strong> 
    <span class="span_separa" >
      <select id="select_rpp" name="rpp" onChange="<%= request.getParameter("js.onChange")%>">
<%
  String valoresSelectRpp=request.getParameter("valores.select.rpp");
  if (valoresSelectRpp==null)
  {
%>
                <option value="15" <%if ("15".equals(request.getParameter("num.registros.pagina"))){ %> selected <%}%> >15</option>
                <option value="20" <%if ("20".equals(request.getParameter("num.registros.pagina"))){ %> selected <%}%> >20</option>
                <option value="30" <%if ("30".equals(request.getParameter("num.registros.pagina"))){ %> selected <%}%> >30</option>
                <option value="40" <%if ("40".equals(request.getParameter("num.registros.pagina"))){ %> selected <%}%> >40</option>
<%
  }
  else
  {
    String[] sValoresSelectRpp=valoresSelectRpp.split(",");
    for (int i=0;i<sValoresSelectRpp.length;i++)
    {
      String rpp=sValoresSelectRpp[i];
%>
      <option value="<c:out value="<%=rpp%>"/>" <%if (rpp.equals(request.getParameter("num.registros.pagina"))) {%>selected<%}%>><c:out value="<%=rpp%>"/></option>
<%
    }
  }
%>
      </select> 
    </span>

</div>
</div>
<!-- end   :: total documents, total pagines, registres per pagina  -->