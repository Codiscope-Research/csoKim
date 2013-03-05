package es.intos.gdscso.action.test.facturacion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;

import servletunit.struts.MockStrutsTestCase;
import es.intos.gdscso.db.test.ConexionBDTest;
import es.intos.gdscso.db.test.GestorConexionesTest;
import es.intos.gdscso.forms.partides.PartidaGeneraExcelForm;
import es.intos.gdscso.utils.Recursos;
import es.intos.sigma.log.MemLog;
import es.intos.sigma.log.ThreadMemLog;
import es.intos.util.Autorizacion;
import es.intos.util.Usuario;
import es.intos.util.sql.GestorConexionesBD;

public class TestPartidaGeneraExcelAction extends MockStrutsTestCase{

	private static final String	yearOfTest		= "2012";
	private static final String	partidaOfTest	= "1";

	public TestPartidaGeneraExcelAction( String testName ) {

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
	}

	@Test
	public void testFacturasExcel(){

		setRequestPathInfo("/PartidaGeneraExcelAction");
		PartidaGeneraExcelForm form = new PartidaGeneraExcelForm();

		form.setIdpartida(TestPartidaGeneraExcelAction.partidaOfTest);
		form.setYear(TestPartidaGeneraExcelAction.yearOfTest);
		form.setPage(1);
		form.setPagina(1);
		form.setRpp(50);

		setActionForm(form);
		actionPerform();

		assertEquals("application/vnd.ms-excel", response.getContentType());

		verifyForward(null);
		verifyNoActionErrors();

	}

	@Test
	public void testFacturasExcelEmptyForm(){

		setRequestPathInfo("/PartidaGeneraExcelAction");
		PartidaGeneraExcelForm form = new PartidaGeneraExcelForm();

		form.setIdpartida(null);
		form.setYear(null);
		form.setPage(1);
		form.setPagina(1);
		form.setRpp(50);
		setActionForm(form);
		actionPerform();

		assertEquals("application/vnd.ms-excel", response.getContentType());
		// assertTrue(response.getBufferSize() != 0);

		verifyForward(null);
		verifyNoActionErrors();

	}

	public static void main( String[] args ){

		junit.textui.TestRunner.run(TestPartidaGeneraExcelAction.class);
	}

}
