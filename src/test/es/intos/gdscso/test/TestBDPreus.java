package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDPreu;
import es.intos.gdscso.on.Preu;
import es.intos.gdscso.on.Tram;
import es.intos.util.sql.ConexionBD;

public class TestBDPreus{

	private static ConexionBD	conBD		= null;
	

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(es.intos.gdscso.db.test.Constants.conUrl, "GDS_CSO", "oracle");
		TestBDPreus.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDPreus.conBD.rollback();
		TestBDPreus.conBD.close();
	}

	@Test
	public void testSelectIdPreu() throws Exception{
	
		Assert.assertNull(BDPreu.selectIdPreu(TestBDPreus.conBD, null, null));
	}
	
	@Test
	public void testGetPreu() throws Exception{	
		Assert.assertNotNull(BDPreu.getPreu(TestBDPreus.conBD, null, null));
		Assert.assertTrue(BDPreu.getPreu(TestBDPreus.conBD, null, null).isEmpty());
	}

	@Test
	public void TestInsertDeleteTram()  throws Exception{	
		int idPreu = BDPreu.selectMaxIdPreu(TestBDPreus.conBD);
		Tram oo = new Tram(1,0.0,120.0,2.0);
		Assert.assertNotNull(BDPreu.insertTram(TestBDPreus.conBD, oo, idPreu));
		Preu preu = new Preu();
		preu.setId(idPreu);
		BDPreu.deleteTrams(TestBDPreus.conBD,preu);				
	}
	
	@Test
	public void TestInsertPreu()  throws Exception{	
		int idPreu = BDPreu.selectMaxIdPreu(TestBDPreus.conBD);
		Preu preu = new Preu();
		preu.setId(idPreu);
		preu.setIdCSO(1);
		preu.setIdSRV(1);
		Assert.assertNotSame(0,BDPreu.insertPreu(TestBDPreus.conBD, preu, "false","false"));
		Assert.assertNotSame(0,BDPreu.insertPreu(TestBDPreus.conBD, preu, "true","false"));
		Assert.assertEquals(0,BDPreu.insertPreu(TestBDPreus.conBD, preu, "fasss","false"));
		Assert.assertEquals(0,BDPreu.insertPreu(TestBDPreus.conBD, null, "false","false"));
	} 
	
	@Test
	public void TestDeletePreu() throws Exception{
		int idPreu = BDPreu.selectMaxIdPreu(TestBDPreus.conBD);		
		BDPreu.deletePreu(TestBDPreus.conBD, idPreu);
	}

}
