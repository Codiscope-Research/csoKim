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
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Recursos;
import es.intos.sigma.log.MemLog;
import es.intos.sigma.log.ThreadMemLog;
import es.intos.util.Autorizacion;
import es.intos.util.Usuario;
import es.intos.util.sql.GestorConexionesBD;

public class TestGestioFacturaAction extends MockStrutsTestCase{

	private static String	idfactura	= "1";

	public TestGestioFacturaAction( String testName ) {

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

		Recursos.gbd = new GestorConexionesBD("C:\\Aplicaciones\\Gdsodm\\conf\\configFile.properties", null);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@172.19.31.6:1521:PRECAIXA1", "GDS_CSO", "oracle");
		ConexionBDTest conBD = new ConexionBDTest(conn);
		GestorConexionesTest conTest = new GestorConexionesTest("", "");
		conTest.setConnexionDB(conBD);
		Recursos.gbd = conTest;
		Autorizacion.setGBD(Recursos.gbd);
		setInitParameter("validating", "false");
		Vector<Factura> fac = LNFacturas.getFacturas(null, null, null, null, null);
		TestGestioFacturaAction.idfactura = fac.get(0).getId().toString();
	}

	@Test
	public void testSuccess() throws Exception{

		setRequestPathInfo("/GestioFacturaAction");
		addRequestParameter("id", TestGestioFacturaAction.idfactura);

		actionPerform();
		verifyForward("Success");
		verifyForwardPath("/web/pantallas/gestio/gestio.jsp");
		verifyNoActionErrors();
	}

	@Test
	public void testtEmptyParams() throws Exception{

		setRequestPathInfo("/GestioFacturaAction");
		addRequestParameter("id", "");

		actionPerform();
		verifyForward("Wrongparams");
		verifyForwardPath("/PreBusquedaGestionFacturasAction.do");
		verifyActionErrors(new String[] { "error.params" });
	}

	@Test
	public void testWrongParams() throws Exception{

		setRequestPathInfo("/GestioFacturaAction");
		addRequestParameter("id", "ssss");

		actionPerform();
		verifyForward("Wrongparams");
		verifyForwardPath("/PreBusquedaGestionFacturasAction.do");
		verifyActionErrors(new String[] { "error.params" });
	}

	public static void main( String[] args ){

		junit.textui.TestRunner.run(TestGestioFacturaAction.class);
	}

}
