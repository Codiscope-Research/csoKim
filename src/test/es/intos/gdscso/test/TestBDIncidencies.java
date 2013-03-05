package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDIncidencias;
import es.intos.gdscso.utils.Constants;
import es.intos.util.sql.ConexionBD;

public class TestBDIncidencies{

	private static ConexionBD	conBD	= null;
	private Integer				facturaId	= 1;
	

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(es.intos.gdscso.db.test.Constants.conUrl, "GDS_CSO", "oracle");
		TestBDIncidencies.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDIncidencies.conBD.rollback();
		TestBDIncidencies.conBD.close();
	}

	@Test
	public void testGetIncidencias() throws Exception{
		BDIncidencias.getIncidencias(TestBDIncidencies.conBD, "ca", "");
		BDIncidencias.getIncidencias(TestBDIncidencies.conBD, "ca", Constants.INC_FACT);
		Assert.assertTrue(BDIncidencias.getIncidencias(TestBDIncidencies.conBD, null, null)!=null && BDIncidencias.getIncidencias(TestBDIncidencies.conBD, null, null).isEmpty() );
	}
	
	@Test
	public void testGetIncidenciaActual() throws Exception{
		
		Assert.assertNotNull(BDIncidencias.getIncidenciaActual(TestBDIncidencies.conBD, null, null));
		Assert.assertNotNull(BDIncidencias.getIncidenciaActual(TestBDIncidencies.conBD, "ca", this.facturaId));
		Assert.assertNotNull(BDIncidencias.getIncidenciaActual(TestBDIncidencies.conBD, "es", this.facturaId));
	}

	

}
