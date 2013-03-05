/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.ControlTable;
import es.intos.gdscso.on.SrvDispTable;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxTableCtrlAction extends LogadoBaseAction{

	private String					echo			= null;
	private Integer					idcso			= null;
	private Integer					idany			= null;
	private int						lenght			= 0;
	private int						inici			= 0;
	private int						totalRecords	= 0;
	private List<Basic>				csolist			= new ArrayList<Basic>();
	private Vector<ControlTable>	contrTableList	= new Vector<ControlTable>(1, 1);
	private String					sortDireccio	= null;
	private MessageResources		messages		= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";

		try {
			out = response.getOutputStream();

			inizializeParams(request);

			getSortedCSOs();
			BuildControlTableRows(request);
			result = createJson();

		} catch (ErrorParamsException nfe) {
			result = setErrorJSON(this.messages.getMessage("error.ajax.params"));
			log.error(es.intos.exception.IntosException.getStackTrace(nfe));

		} catch (Exception e) {
			result = setErrorJSON(this.messages.getMessage("error.ajax"));
			log.error(es.intos.exception.IntosException.getStackTrace(e));
		}

		if (null != out) {
			if (!Utils.isValidJSON(result)) {
				ActionMessages errors = new ActionMessages();
				errors.add("error", new ActionMessage("JSON.erroni"));
				addErrors(request, errors);
				result = setErrorJSON(this.messages.getMessage("error.ajax.json.notvalid"));
			}
			log.info(result);
			out.print(result);
		}
		return null;
	}

	// FUNCTIONS
	private void inizializeParams( HttpServletRequest request ) throws ErrorParamsException{

		try {
			this.messages = getResources(request);
			// Params del filtre
			this.idany = (request.getParameter("idany") != null && !request.getParameter("idany").equals("")) ? Integer.parseInt(request
					.getParameter("idany")) : null;
			this.idcso = (request.getParameter("idcso") != null && !request.getParameter("idcso").equals("")) ? Integer.parseInt(request
					.getParameter("idcso")) : null;
			// Params de datatables
			this.echo = request.getParameter("sEcho");
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));

			if (idany == null && idany.equals(0)) {
				idany = Calendar.getInstance().get(Calendar.YEAR);
			}
			this.sortDireccio = request.getParameter("sSortDir_0");
			if (this.sortDireccio == null)
				this.sortDireccio = "ASC";
			this.csolist.clear();
			this.totalRecords = 0;
			this.contrTableList.clear();
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
	}

	@SuppressWarnings("unchecked")
	private void getSortedCSOs() throws Exception{

		Vector<Basic> csos = new Vector<Basic>(1, 1);
		if (idcso != null) {
			csos = LNCso.getCSO(idcso);
		} else {
			csos = LNCso.getCSOs();
		}
		this.totalRecords = csos.size();
		if (this.totalRecords > lenght) {
			if ((inici + lenght) < csos.size()) {
				csolist = csos.subList(inici, inici + lenght);
			} else {
				csolist = csos.subList(inici, this.totalRecords);
			}
		} else {
			csolist.addAll(csos);
		}
		Collections.sort(csolist);
		if (this.sortDireccio.equals("desc")) {
			Collections.reverse(csolist);
		}
	}

	private void BuildControlTableRows( HttpServletRequest request ) throws Exception{

		String context = request.getSession().getServletContext().getInitParameter("context");

		for (Basic cso : csolist) {
			int numOfMonth = 1;
			int rows = 0;
			ControlTable ctrTable = new ControlTable();
			ctrTable.setCso(cso.getDescripcio());
			for (numOfMonth = 1; numOfMonth <= 12; numOfMonth++) {
				// Mirem si el control és vermell
				int month = Calendar.getInstance().get(Calendar.MONTH);
				int year = Calendar.getInstance().get(Calendar.YEAR);
				if (numOfMonth > month + 2 && year == this.idany) {
					ctrTable.setMes("<a href=\"#\" style=\"text-align: center;\" onclick=\"openClient('" + cso.getId() + "','" + numOfMonth
							+ "','" + this.idany + "','" + cso.getDescripcio() + "','verd')\" ><img src=\"/" + context
							+ "/web/img/grey.png\"></a>", numOfMonth);
					continue;
				}

				rows = LNVolum.getNumSrvWithoutFact(cso.getId(), this.idany, numOfMonth);
				if (rows != 0) {
					// posem vola vermella
					ctrTable.setMes("<a href=\"#\" style=\"text-align: center;\" onclick=\"openClient('" + cso.getId() + "','" + numOfMonth
							+ "','" + this.idany + "','" + cso.getDescripcio()
							+ "','vermell')\" ><img src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/rojo.png\"></a>",
							numOfMonth);
					continue;
				}

				// Mirem si és verd o si és ambar
				Integer[] estats = new Integer[] { 2, 3, 4, 5, 8, 9 };
				rows = LNFacturas.getNumRegControl(cso.getId(), this.idany, numOfMonth, estats);
				if (rows != 0) {
					// ambar
					ctrTable.setMes("<a href=\"#\" style=\"text-align: center;\" onclick=\"openClient('" + cso.getId() + "','" + numOfMonth
							+ "','" + this.idany + "','" + cso.getDescripcio() + "','ambar')\" ><img src=\"/" + context
							+ "/web/img/naranja.png\"></a>", numOfMonth);
					continue;
				} else {

					Vector<SrvDispTable> numSrv = LNVolum.getSrvCso(idcso, this.idany, numOfMonth, "CA", 0, 10, null);

					if (numSrv.isEmpty()) {
						ctrTable.setMes("<a href=\"#\" style=\"text-align: center;\" onclick=\"openClient('" + cso.getId() + "','"
								+ numOfMonth + "','" + this.idany + "','" + cso.getDescripcio() + "','verd')\" ><img src=\"/" + context
								+ "/web/img/grey.png\"></a>", numOfMonth);
						continue;
					} else {
						// verd
						ctrTable.setMes("<a href=\"#\" style=\"text-align: center;\" onclick=\"openClient('" + cso.getId() + "','"
								+ numOfMonth + "','" + this.idany + "','" + cso.getDescripcio()
								+ "','verd')\" ><img src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/verde.png\"></a>",
								numOfMonth);
						continue;
					}

				}
			}
			contrTableList.add(ctrTable);
		}
	}

	private String createJson(){

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(contrTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + csolist.size() + "\", \"iTotalDisplayRecords\":\"" + csolist.size()
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": " + error + ", \"sEcho\": " + 0 + ", \"iTotalRecords\":\"" + 5 + "\", \"iTotalDisplayRecords\":\"" + 5
				+ "\", \"aaData\": [ ");
		jsonSB.append("]}");
		return jsonSB.toString();

	}

}
