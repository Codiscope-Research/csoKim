package es.intos.gdscso.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDEstats;
import es.intos.gdscso.db.test.Constants;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.EstatHistoric;
import es.intos.util.sql.ConexionBD;

public class TestBDEstats{

	private static ConexionBD	conBD	= null;
	private Integer facturaId=1;
	

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(Constants.conUrl, "GDS_CSO", "oracle");
		TestBDEstats.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDEstats.conBD.rollback();
		TestBDEstats.conBD.close();
	}

	@Test
	public void test(){

		try {

			
			testBDEstats();

		} catch (AssertionError e) {
			fail("Assertion Error");
		} catch (Exception e) {
			fail("Exception");
		}
	}	

	private void testBDEstats() throws Exception{
		testGetEstats();
	}

	private void testGetEstats() throws Exception{
		Vector<Basic> estats = BDEstats.getEstats(TestBDEstats.conBD, "CA");
		assertTrue(!estats.isEmpty() && !estats.get(0).getDescripcio().equals(""));
		estats = BDEstats.getEstats(TestBDEstats.conBD, "ca");
		assertTrue(!estats.isEmpty() && !estats.get(0).getDescripcio().equals(""));
	}
	
	@Test
	public void testsaveHisEstatsWithAllData() throws Exception{
		
		EstatHistoric statH = new EstatHistoric();
		statH.setId(this.facturaId);
		statH.setNewStat("2");
		statH.setOldStat("1");
		statH.setDateOfChange(new Date());
		statH.setIncidencia("Incidencia Tets");
		
		BDEstats.saveHisEstats(TestBDEstats.conBD, statH);				
	}
	
	@Test
	public void testgetHisEstatsOfFactura() throws Exception{
		testsaveHisEstatsWithAllData();
        assertTrue("list null or empty factura",BDEstats.getHisEstatsOfFactura(TestBDEstats.conBD, this.facturaId)!=null && !BDEstats.getHisEstatsOfFactura(TestBDEstats.conBD, this.facturaId).isEmpty());
	}
	
	@Test
	public void testgetHisEstatsOfNullFactura() throws Exception{
		testsaveHisEstatsWithAllData();
        assertTrue("list null or not empty of Null factura",BDEstats.getHisEstatsOfFactura(TestBDEstats.conBD, null)!=null && BDEstats.getHisEstatsOfFactura(TestBDEstats.conBD, null).isEmpty());
	}
	
	@Test 
	public void testsaveHisEstatsWithBlankState() throws Exception{
		EstatHistoric statH = new EstatHistoric();
		statH.setId(1);
		statH.setNewStat("");
		statH.setOldStat("");
		statH.setDateOfChange(new Date());
		statH.setIncidencia("Incidencia Tets");	
	
		BDEstats.saveHisEstats(TestBDEstats.conBD, statH);
	
	}

}
