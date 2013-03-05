package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDImportPactat;
import es.intos.gdscso.db.test.Constants;
import es.intos.gdscso.on.Basic;
import es.intos.util.sql.ConexionBD;

public class TestBDImportsPactats{

	private static ConexionBD	conBD			= null;
	private static Double		importeZero		= 0.0;
	private static Double		importeNoZero	= 10.23;
	private static Integer		year			= 2012;

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(Constants.conUrl, "GDS_CSO", "oracle");
		TestBDImportsPactats.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDImportsPactats.conBD.rollback();
		TestBDImportsPactats.conBD.close();
	}

	@Test
	public void testgetImportPactat() throws Exception{

		Basic basic = BDImportPactat.getImportPactat(TestBDImportsPactats.conBD, null);
		Assert.assertNull(basic);
		basic = BDImportPactat.getImportPactat(TestBDImportsPactats.conBD, TestBDImportsPactats.year);
		Assert.assertNotNull(basic);
		basic = BDImportPactat.getImportPactat(TestBDImportsPactats.conBD, 0);
		Assert.assertNull(basic);
	}

	@Test
	public void testSaveOrUpdateImportPactat() throws Exception{

		BDImportPactat.saveOrUpdateImportPactat(TestBDImportsPactats.conBD, TestBDImportsPactats.year, TestBDImportsPactats.importeZero);
		BDImportPactat.saveOrUpdateImportPactat(TestBDImportsPactats.conBD, TestBDImportsPactats.year, TestBDImportsPactats.importeNoZero);
		BDImportPactat.saveOrUpdateImportPactat(TestBDImportsPactats.conBD, TestBDImportsPactats.year, null);
		BDImportPactat.saveOrUpdateImportPactat(TestBDImportsPactats.conBD, null, null);
	}

}
