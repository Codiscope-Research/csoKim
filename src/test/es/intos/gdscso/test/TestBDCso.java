package es.intos.gdscso.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDCso;
import es.intos.gdscso.db.test.Constants;
import es.intos.gdscso.on.Basic;
import es.intos.util.sql.ConexionBD;

public class TestBDCso{

	private static ConexionBD	conBD	= null;

	

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(Constants.conUrl, "GDS_CSO", "oracle");
		TestBDCso.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDCso.conBD.rollback();
		TestBDCso.conBD.close();
	}

	@Test
	public void test(){

		try {

			testBDCso();		

		} catch (AssertionError e) {
			fail("Assertion Error");
		} catch (Exception e) {
			fail("Exception");
		}
	}

	private void testBDCso() throws Exception{

		Vector<Basic> csos = BDCso.getCSOs(conBD);
		if (!csos.isEmpty()) {
			Basic bs = csos.get(0);
			assertNotNull(bs);
			Vector<Basic> cso = BDCso.getCSO(conBD, bs.getId());
			assertTrue(!cso.isEmpty());
		}
	}

}
