package es.intos.gdscso.action.test.gestio;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import servletunit.struts.MockStrutsTestCase;
import es.intos.gdscso.db.test.ConexionBDTest;
import es.intos.gdscso.db.test.GestorConexionesTest;
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Recursos;
import es.intos.sigma.log.MemLog;
import es.intos.sigma.log.ThreadMemLog;
import es.intos.util.Autorizacion;
import es.intos.util.Usuario;

public class TestEditFacturaAction extends MockStrutsTestCase{

	private static String	idfactura	= "1";

	public TestEditFacturaAction( String testName ) {

		super(testName);
	}

	@Before
	public void setUp() throws Exception{

		super.setUp();

		this.setContextDirectory(new File("web"));
		this.setServletConfigFile("web/WEB-INF/web.xml");
		//this.setConfigFile("WEB-INF/struts-config.xml");
		Usuario user = new Usuario("001", "7300", "U1V09134", "Amparo", "Carter", "L%C3%B3pez", "Barcelona", "CAS");
		this.getSession().setAttribute("user", user);

		es.intos.Recursos.memLog = new MemLog("diccionario_LOCAL");
		ThreadMemLog tMemLog = new ThreadMemLog();
		Thread thread = new Thread(tMemLog);
		thread.start();

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@172.19.31.6:1521:PRECAIXA1", "GDS_CSO", "oracle");
		ConexionBDTest conBD = new ConexionBDTest(conn);
		GestorConexionesTest conTest = new GestorConexionesTest("", "");
		conTest.setConnexionDB(conBD);
		Recursos.gbd = conTest;
		Autorizacion.setGBD(Recursos.gbd);
		setInitParameter("validating", "false");
		Vector<Factura> fac = LNFacturas.getFacturas(null, null, null, null, null);
		TestEditFacturaAction.idfactura = fac.get(0).getId().toString();
	}

	@Test
	public void testSuccessEditFactura() throws Exception{

		setRequestPathInfo("/EditFacturaAction");

		GestionFacturasForm frm = new GestionFacturasForm();

		frm.setId(TestEditFacturaAction.idfactura);
		addRequestParameter("test.reset", "false");
		setActionForm(frm);

		actionPerform();
		verifyForward("Success");
		verifyForwardPath("/web/pantallas/modificar/modificar.jsp");
		verifyNoActionErrors();
	}

	@Test
	public void testEditFacturaEmptyParams() throws Exception{

		setRequestPathInfo("/EditFacturaAction");

		GestionFacturasForm frm = new GestionFacturasForm();

		frm.setId("");
		addRequestParameter("test.reset", "false");
		setActionForm(frm);

		actionPerform();
		verifyForward("Wrongparams");
		verifyForwardPath("/GestioFacturaAction.do");
		verifyActionErrors(new String[] { "Parametres.incorrectes" });
	}

	@Test
	public void testEditFacturaWrongParams() throws Exception{

		setRequestPathInfo("/EditFacturaAction");

		GestionFacturasForm frm = new GestionFacturasForm();

		frm.setId("erere");
		addRequestParameter("test.reset", "false");
		setActionForm(frm);

		actionPerform();
		verifyForward("Wrongparams");
		verifyForwardPath("/GestioFacturaAction.do");
		verifyActionErrors(new String[] { "Parametres.incorrectes" });
	}

	@Test
	public void testAccesoDenegado() throws Exception{

		setRequestPathInfo("/EditFacturaAction");

		Usuario user = new Usuario("001", "7340", "U1V09134", "Amparo", "Carter", "L%C3%B3pez", "Barcelona", "CAS");
		this.getSession().setAttribute("user", user);
		GestionFacturasForm form = new GestionFacturasForm();
		addRequestParameter("test.reset", "false");
		form.setId(TestEditFacturaAction.idfactura);
		setActionForm(form);

		actionPerform();
		verifyActionErrors(new String[] { "error.acceso.denegado" });

	}

	public static void main( String[] args ){

		junit.textui.TestRunner.run(TestEditFacturaAction.class);
	}

}
