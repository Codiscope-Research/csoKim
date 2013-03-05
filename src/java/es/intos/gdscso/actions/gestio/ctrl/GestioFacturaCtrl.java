package es.intos.gdscso.actions.gestio.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNEstats;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNIncidencias;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.EstatHistoric;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class GestioFacturaCtrl{

	private String				procedenciaUser	= null;
	private Integer				idFactura		= null;
	private Usuario				user			= null;
	private Locale				locale			= null;
	private Vector<Basic>		estats			= new Vector<Basic>(1, 1);
	private GestionFacturasForm	frm				= null;
	private Vector<Basic>		inclist			= null;

	public String ctrl( ActionForm form, HttpServletRequest request, HttpServletResponse response, Usuario user, Locale locale )
			throws Exception, NumberFormatException{

		this.user = user;
		this.locale = locale;

		InizializeVariables(request, form);

		setFacturaToFrom();

		setActualIncidenciaToFrom();

		String type_incidencia = detectTypeOfIncidence(frm);
		inclist = LNIncidencias.getIncidencias(locale.getLanguage(), type_incidencia);

		setHistoricEstatsToForm();

		return createGraficEstatsUrl();

	}

	// FUNCTIONS
	private List<EstatHistoric> getFulltimeHistoricEstat() throws NumberFormatException, Exception{

		EstatHistoric estatHistoricCopy = new EstatHistoric();
		estatHistoricCopy.setDateOfChange(null);
		List<EstatHistoric> fulltimeHistoricEstatList = new ArrayList<EstatHistoric>(Collections.nCopies(Constants.NUMBER_OF_ESTATS,
				estatHistoricCopy));
		List<EstatHistoric> estatHistoricList = LNEstats.getHisEstatsOfFactura(this.idFactura);

		for (EstatHistoric estatHistoric : estatHistoricList) {
			fulltimeHistoricEstatList.remove((Integer.parseInt(estatHistoric.getOldStat()) - 1));
			fulltimeHistoricEstatList.add(Integer.parseInt(estatHistoric.getOldStat()) - 1, estatHistoric);
		}
		return fulltimeHistoricEstatList;
	}

	private void InizializeVariables( HttpServletRequest request, ActionForm form ) throws Exception{

		procedenciaUser();
		this.idFactura = (request.getParameter("id") != null && !request.getParameter("id").equals("")) ? Integer.parseInt(request
				.getParameter("id")) : null;
		this.estats = LNEstats.getEstats(locale.getLanguage());
		this.frm = (GestionFacturasForm) form;
		if (this.idFactura == null)
			throw new NumberFormatException();

	}

	private void procedenciaUser(){

		String procedencia = "";
		if (user.getENumCen().equals(Recursos.CENTROS_GDS[0]) || user.getENumCen().equals(Recursos.CENTROS_GDS[1])) {
			procedencia = Recursos.PROCEDENCIA_GDS;
		} else {
			procedencia = Recursos.PROCEDENCIA_CSO;
		}
		this.procedenciaUser = procedencia;
	}

	private String detectTypeOfIncidence( GestionFacturasForm frm ){

		String type_inc = "";

		if (frm.getIdEstat() == Constants.ESTAT_GENERAT) {
			type_inc = Constants.INC_VAL;
		} else if (frm.getIdEstat() == Constants.ESTAT_VALIDAT) {
			type_inc = Constants.INC_NCONFORME;
		} else {
			type_inc = Constants.INC_FACT;
		}

		return type_inc;
	}

	private void setFacturaToFrom() throws Exception{

		Vector<Factura> factList = LNFacturas.getFacturas(this.idFactura, null, null, null, null);

		if (!factList.isEmpty()) {
			Factura fct = factList.get(0);
			BeanUtils.copyProperties(frm, fct);
		}

	}

	private void setActualIncidenciaToFrom() throws Exception{

		String inc = LNIncidencias.getIncidenciaActual(locale.getLanguage(), this.idFactura);
		frm.setIncidencia(inc);
	}

	private void setHistoricEstatsToForm() throws NumberFormatException, Exception{

		List<EstatHistoric> fulltimeHistoricEstat = getFulltimeHistoricEstat();
		fulltimeHistoricEstat.remove(frm.getIdEstat() - 1);
		EstatHistoric estatHistoricNow = new EstatHistoric();
		estatHistoricNow.setDateOfChange(new Date());
		fulltimeHistoricEstat.add(frm.getIdEstat() - 1, estatHistoricNow);
		frm.setFulltimeHistoricEstat(fulltimeHistoricEstat);
	}

	private String createGraficEstatsUrl() throws Exception{

		List<EstatHistoric> fulltimeHistoricEstat = this.frm.getFulltimeHistoricEstat();
		StringBuffer urlimage = new StringBuffer("graficEstat_");
		boolean hasI1 = false;
		boolean hasNC = false;
		boolean hasI2 = false;
		boolean actualEstatisNC = false;
		boolean actualEstatisI1 = false;
		boolean actualEstatisI2 = false;

		setFirstEstat(urlimage, frm.getIdEstat());

		for (EstatHistoric estatHist : fulltimeHistoricEstat) {
			if (estatHist.getDateOfChange() != null && estatHist.getNewStat() != null && estatHist.getOldStat() != null
					&& estatHist.getOldStat().equals(Constants.ESTAT_INC_VAL.toString()) && !actualEstatisI1) {
				hasI1 = true;
			} else if (estatHist.getDateOfChange() != null && estatHist.getNewStat() != null && estatHist.getOldStat() != null
					&& estatHist.getOldStat().equals(Constants.ESTAT_NCONFORME.toString()) && !actualEstatisNC) {
				hasNC = true;
			} else if (estatHist.getDateOfChange() != null && estatHist.getNewStat() != null && estatHist.getOldStat() != null
					&& estatHist.getOldStat().equals(Constants.ESTAT_INC_FACT.toString()) && !actualEstatisI2) {
				hasI2 = true;
			}

			if (estatHist.getDateOfChange() != null && estatHist.getNewStat() != null && estatHist.getOldStat() != null
					&& estatHist.getNewStat().equals(Constants.ESTAT_CANCEL.toString())) {
				if (estatHist.getOldStat().equals(Constants.ESTAT_NCONFORME.toString())) {
					actualEstatisNC = true;
					hasNC = false;
				} else if (estatHist.getOldStat().equals(Constants.ESTAT_INC_VAL.toString())) {
					actualEstatisI1 = true;
					hasI1 = false;

				} else if (estatHist.getOldStat().equals(Constants.ESTAT_INC_FACT.toString())) {
					actualEstatisI2 = true;
					hasI2 = false;
				}
				setFirstEstat(urlimage, Integer.parseInt(estatHist.getOldStat()));
			}
		}

		if (hasI1) {
			urlimage.append("I1_");
		}
		if (hasNC) {
			urlimage.append("NC_");
		}
		if (hasI2) {
			urlimage.append("I2_");
		}

		urlimage.append(locale.getLanguage().toUpperCase() + ".png");
		return urlimage.toString();
	}

	private void setFirstEstat( StringBuffer urlimage, Integer estat ){

		if (estat != Constants.ESTAT_INC_VAL && estat != Constants.ESTAT_INC_FACT && estat != Constants.ESTAT_CONFORME
				&& estat != Constants.ESTAT_CANCEL) {
			urlimage.append("E" + (estat - 1) + "_");
		} else if (estat == Constants.ESTAT_CONFORME) {
			urlimage.append("E3_");
		} else if (estat == Constants.ESTAT_INC_VAL) {
			urlimage.append("I1_");
		} else if (estat == Constants.ESTAT_INC_FACT) {
			urlimage.append("I2_");
		}
	}

	public String getProcedenciaUser(){

		return procedenciaUser;
	}

	public Integer getIdFactura(){

		return idFactura;
	}

	public Vector<Basic> getEstats(){

		return estats;
	}

	public GestionFacturasForm getFrm(){

		return frm;
	}

	public Vector<Basic> getInclist(){

		return inclist;
	}

}
