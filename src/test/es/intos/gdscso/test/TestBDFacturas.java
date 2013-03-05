package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDFacturas;
import es.intos.gdscso.db.test.Constants;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.on.Factura;
import es.intos.util.sql.ConexionBD;

public class TestBDFacturas{

	private static ConexionBD	conBD		= null;
	private static Integer		facturaId	= 1;
	private static Integer		idCso		= 1;

	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(Constants.conUrl, "GDS_CSO", "oracle");
		TestBDFacturas.conBD = new ConexionBD(conn);

	}

	@After
	public void tearDown() throws Exception{

		TestBDFacturas.conBD.rollback();
		TestBDFacturas.conBD.close();
	}

	@Test
	public void testSaveFactura() throws Exception{

		Factura factura = new Factura();
		factura.setIdCSO(1);
		factura.setMonth(2);
		factura.setYear(2012);
		BDFacturas.insertFactura(TestBDFacturas.conBD, factura);
	}

	@Test
	public void testGetFacturas() throws Exception{

		testGetFacturasWithNulls();
	}

	private void testGetFacturasWithNulls() throws Exception{

		Assert.assertNotNull("Null list of factures", BDFacturas.getFacturas(TestBDFacturas.conBD, null, null, null, 1, 99));
	}

	@Test
	public void testGetInfoFacturaNoConformidades() throws Exception{

		Assert.assertNotNull("Null", BDFacturas.getInfoFacturaNoConformidades(TestBDFacturas.conBD, null, null));
		Assert.assertNotNull("Null", BDFacturas.getInfoFacturaNoConformidades(TestBDFacturas.conBD, null, "ca"));
		Assert.assertNotNull("Null", BDFacturas.getInfoFacturaNoConformidades(TestBDFacturas.conBD, null, "CA"));
		Assert.assertNotNull("Null", BDFacturas.getInfoFacturaNoConformidades(TestBDFacturas.conBD, null, ""));
		Assert.assertNotNull("Null", BDFacturas.getInfoFacturaNoConformidades(TestBDFacturas.conBD, 1, "CA"));
	}

	@Test
	public void testGetNumRegControl() throws Exception{

		Integer[] estats = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		BDFacturas.getNumRegControl(TestBDFacturas.conBD, 1, 2012, 2, estats);
		estats = new Integer[] {};
		BDFacturas.getNumRegControl(TestBDFacturas.conBD, 1, 2012, 2, estats);
		Assert.assertEquals(0, BDFacturas.getNumRegControl(TestBDFacturas.conBD, 1, 2012, 2, null));
		Assert.assertEquals(0, BDFacturas.getNumRegControl(TestBDFacturas.conBD, null, null, null, estats));
	}

	@Test
	public void TestGetTableSrvFactures() throws Exception{

		Assert.assertTrue(BDFacturas.getTableSrvFactures(TestBDFacturas.conBD, null, null).isEmpty());
		Assert.assertNotNull("null", BDFacturas.getTableSrvFactures(TestBDFacturas.conBD, TestBDFacturas.facturaId, ""));

	}

	@Test
	public void TestChangeStateFactura() throws Exception{

		BDFacturas.changeStateFactura(TestBDFacturas.conBD, TestBDFacturas.facturaId, 2);
		BDFacturas.changeStateFactura(TestBDFacturas.conBD, null, null);
	}

	@Test
	public void TestUpdateObservacionesFactura() throws Exception{

		BDFacturas.updateObservacionesFactura(TestBDFacturas.conBD, TestBDFacturas.facturaId, "proba test");
		BDFacturas.updateObservacionesFactura(TestBDFacturas.conBD, null, null);
	}

	@Test
	public void TestGetNumReg() throws Exception{

		Assert.assertNotNull(BDFacturas.getNumReg(TestBDFacturas.conBD, null));
		BusquedaGestionFacturasFormDTO facturaDTO = new BusquedaGestionFacturasFormDTO();
		Assert.assertNotNull(BDFacturas.getNumReg(TestBDFacturas.conBD, facturaDTO));

	}

	@Test
	public void TestGetNumRegNoConformidades() throws Exception{

		Assert.assertNotNull(BDFacturas.getNumRegNoConformidades(TestBDFacturas.conBD, null));
		BusquedaNoConformidadesFormDTO facturaDTO = new BusquedaNoConformidadesFormDTO();
		Assert.assertNotNull(BDFacturas.getNumRegNoConformidades(TestBDFacturas.conBD, facturaDTO));
	}

	@Test
	public void TestGetCodesOfFacturesHomonimes() throws Exception{
		Assert.assertNotNull(BDFacturas.getCodesOfFacturesHomonimes(TestBDFacturas.conBD, null));
		Assert.assertNotNull(BDFacturas.getCodesOfFacturesHomonimes(TestBDFacturas.conBD, TestBDFacturas.idCso ));
	}
	
	@Test
	public void TestUpdateImportFactura() throws Exception{
		BDFacturas.updateImportFactura(TestBDFacturas.conBD, null,0.0);
		BDFacturas.updateImportFactura(TestBDFacturas.conBD, TestBDFacturas.facturaId,0.0 );
	}


}
