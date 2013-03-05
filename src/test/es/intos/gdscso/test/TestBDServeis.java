package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDServeis;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.util.sql.ConexionBD;

public class TestBDServeis{

	private static ConexionBD	conBD		= null;


	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(es.intos.gdscso.db.test.Constants.conUrl, "GDS_CSO", "oracle");
		TestBDServeis.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDServeis.conBD.rollback();
		TestBDServeis.conBD.close();
	}

	
	@Test
	public void testGetComparativaServeisNumReg() throws Exception{
		
		Assert.assertSame(0,BDServeis.getComparativaServeisNumReg(TestBDServeis.conBD, null));
		BusquedaComparativaServeisFormDTO frm  = new BusquedaComparativaServeisFormDTO();
		frm.setF_cso("1");
		frm.setF_csoActual("1");
		Assert.assertNotNull(BDServeis.getComparativaServeisNumReg(TestBDServeis.conBD, frm));
		
		
	
	}
	
	@Test
	public void testGetComparativaServeis() throws Exception{
	
		Assert.assertNotNull(BDServeis.getComparativaServeis(TestBDServeis.conBD, null,null));
		Assert.assertTrue(BDServeis.getComparativaServeis(TestBDServeis.conBD, null,null).isEmpty());
		BusquedaComparativaServeisFormDTO frm = new BusquedaComparativaServeisFormDTO();
		frm.setF_cso("1");
		frm.setF_csoActual("1");
		Assert.assertNotNull(BDServeis.getComparativaServeis(TestBDServeis.conBD, frm,null));
	
	}
	
	@Test
	public void testGetServeis() throws Exception{
		Assert.assertNotNull(BDServeis.getServeis(TestBDServeis.conBD));
	
	}
	
	

}
