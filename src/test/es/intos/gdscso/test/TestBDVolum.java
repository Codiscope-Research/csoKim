package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDVolum;
import es.intos.util.sql.ConexionBD;

public class TestBDVolum{

	private static ConexionBD	conBD		= null;
	private static Integer idCso=1;
	private static Integer month=2;
	private static Integer year=2012;
	private static Integer idSrv=1;

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(es.intos.gdscso.db.test.Constants.conUrl, "GDS_CSO", "oracle");
		TestBDVolum.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDVolum.conBD.rollback();
		TestBDVolum.conBD.close();
	}

	@Test
	public void testCkeckNewData() throws Exception{
	
		BDVolum.ckeckNewData(TestBDVolum.conBD);
	}
	
	@Test
	public void testGetSrvWithVolExcel() throws Exception{
		
		Assert.assertNotNull(BDVolum.getSrvWithVolExcel(TestBDVolum.conBD, null, null,null, null));
		Assert.assertTrue(BDVolum.getSrvWithVolExcel(TestBDVolum.conBD, null, null,null, null).isEmpty());
		Assert.assertNotNull(BDVolum.getSrvWithVolExcel(TestBDVolum.conBD, TestBDVolum.idCso , TestBDVolum.year, TestBDVolum.month, "CA"));
	}
	
	@Test
	public void testGetSrv() throws Exception{
		
		Assert.assertNotNull(BDVolum.getSrv(TestBDVolum.conBD,null,null,null,null ));
		Assert.assertTrue(BDVolum.getSrv(TestBDVolum.conBD,null,null,null,null ).isEmpty());
		Assert.assertNotNull(BDVolum.getSrv(TestBDVolum.conBD,TestBDVolum.idSrv , TestBDVolum.idCso, TestBDVolum.month, TestBDVolum.year));
	}
	
	@Test
	public void testGetSrvFacts() throws Exception{
		
		Assert.assertNotNull(BDVolum.getSrvFacts(TestBDVolum.conBD, null, null, null, null, null, null, null));
		Assert.assertTrue(BDVolum.getSrvFacts(TestBDVolum.conBD, null, null, null, null, null, null, null).isEmpty());
		Assert.assertNotNull(BDVolum.getSrvFacts(TestBDVolum.conBD,TestBDVolum.idCso , TestBDVolum.year, TestBDVolum.month, "CA", 0, 20, null));
	}
	
	@Test
	public void testGetSrvCsoFacts() throws Exception{
		
		Assert.assertNotNull(BDVolum.getSrvCsoFacts(TestBDVolum.conBD, null, null, null, null, null));
		Assert.assertTrue(BDVolum.getSrvCsoFacts(TestBDVolum.conBD, null, null, null, null, null).isEmpty());
		Assert.assertNotNull(BDVolum.getSrvCsoFacts(TestBDVolum.conBD,TestBDVolum.year , TestBDVolum.month, 0, 20,null));
	}
	
	@Test
	public void testGetSrvWithVol() throws Exception{
		
		Assert.assertNotNull(BDVolum.getSrvWithVol(TestBDVolum.conBD, null, null, null, null,null));
		Assert.assertTrue(BDVolum.getSrvWithVol(TestBDVolum.conBD, null, null, null, null,null).isEmpty());
		Assert.assertNotNull(BDVolum.getSrvWithVol(TestBDVolum.conBD,TestBDVolum.idCso, TestBDVolum.year , TestBDVolum.month,null,null));
	}
		
	@Test
	public void testGetNumSrvWithoutFact() throws Exception{
		
		Assert.assertSame(0,BDVolum.getNumSrvWithoutFact(TestBDVolum.conBD, null, null, null));
		Assert.assertNotNull(BDVolum.getNumSrvWithoutFact(TestBDVolum.conBD,TestBDVolum.idCso, TestBDVolum.year , TestBDVolum.month));
	}
	
	

}
