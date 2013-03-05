package es.intos.gdscso.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.intos.gdscso.bd.BDPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactorCrecimiento;
import es.intos.gdscso.on.FactorsCorreccio;
import es.intos.gdscso.on.Partida;
import es.intos.util.sql.ConexionBD;

public class TestBDPartides{

	private static ConexionBD	conBD		= null;
	private static Integer		partidaId	= 1;
	private static Integer		yearOfTest	= 2012;
	private static Vector<Basic> years= new Vector<Basic>(1,1);
	
	@Before
	public void setUp() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(es.intos.gdscso.db.test.Constants.conUrl, "GDS_CSO", "oracle");
		TestBDPartides.conBD = new ConexionBD(conn);
		years.add(new Basic(2011,"2011"));
		years.add(new Basic(2012,"2012"));

	}

	@After
	public void tearDown() throws Exception{

		TestBDPartides.conBD.rollback();
		TestBDPartides.conBD.close();
	}

	@Test
	public void testGetNumPartides() throws Exception{

		Assert.assertNotNull(BDPartidas.getNumPartides(TestBDPartides.conBD));

	}

	@Test
	public void testGetInfoTableOfFacturacionPartidas() throws Exception{

		Assert.assertNotNull(BDPartidas.getInfoTableOfFacturacionPartidas(TestBDPartides.conBD, TestBDPartides.yearOfTest, 0, 10, ""));
		Assert.assertNotNull(BDPartidas.getInfoTableOfFacturacionPartidas(TestBDPartides.conBD, null, null, null, null));
	}

	@Test
	public void TestSelectMaxIdPartida() throws Exception{

		BDPartidas.selectMaxIdPartida(TestBDPartides.conBD);
	}

	@Test
	public void TestGetAllPartida() throws Exception{

		BDPartidas.getAllPartida(TestBDPartides.conBD);
	}

	@Test
	public void TestGetAllSrvPartidaCount() throws Exception{

		BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, true, TestBDPartides.partidaId);
		BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, false, TestBDPartides.partidaId);
		Assert.assertEquals(0, BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, false, null));
	}

	@Test
	public void TestGetAllSrvPartida() throws Exception{

		Assert.assertNotNull(BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, true, TestBDPartides.partidaId));
		Assert.assertNotNull(BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, false, TestBDPartides.partidaId));
		Assert.assertEquals(0, BDPartidas.getAllSrvPartidaCount(TestBDPartides.conBD, false, null));
	}

	@Test
	public void InsertDeleteSrvPartida() throws Exception{

		BDPartidas.insertSrvPartida(TestBDPartides.conBD, 1, TestBDPartides.partidaId, "");
		BDPartidas.deleteSrvPartida(TestBDPartides.conBD, 1, TestBDPartides.partidaId);

	}

	@Test
	public void testUpdateNomPartida() throws Exception{

		Assert.assertEquals(0, BDPartidas.updateNomPartida(TestBDPartides.conBD, null));
		int id = BDPartidas.selectMaxIdPartida(TestBDPartides.conBD);
		Partida partida = new Partida(id, "", null);

		Assert.assertNotSame(0, BDPartidas.updateNomPartida(TestBDPartides.conBD, partida));

	}

	@Test
	public void testInsertDeletePartida() throws Exception{

		Partida partida = new Partida(TestBDPartides.partidaId, "partida test", null);
		BDPartidas.insertPartida(TestBDPartides.conBD, partida);
		int id = BDPartidas.selectMaxIdPartida(TestBDPartides.conBD);
		partida.setId(id);
		BDPartidas.deletePartida(TestBDPartides.conBD, partida);
	}

	@Test
	public void testGetFactoresCrecimientoPartidas() throws Exception{

		Assert.assertNotNull(BDPartidas.getFactoresCrecimientoPartidas(TestBDPartides.conBD, null, null));
		Assert.assertTrue(BDPartidas.getFactoresCrecimientoPartidas(TestBDPartides.conBD, null, null).isEmpty());
		Assert.assertNotNull(BDPartidas.getFactoresCrecimientoPartidas(TestBDPartides.conBD, TestBDPartides.yearOfTest, TestBDPartides.partidaId));
	}

	@Test
	public void testInsertFactoresCrecimientoPartidas() throws Exception{

		List<FactorCrecimiento> list = new ArrayList<FactorCrecimiento>();
		BDPartidas.insertFactoresCrecimientoPartidas(TestBDPartides.conBD, list);
		FactorCrecimiento fc = new FactorCrecimiento();
		fc.setFactor(2.34);
		fc.setIdpartida(TestBDPartides.partidaId);
		fc.setMonth(2);
		fc.setYear(TestBDPartides.yearOfTest);
		BDPartidas.insertFactoresCrecimientoPartidas(TestBDPartides.conBD, list);

	}

	@Test
	public void testSaveImportPactatPartida() throws Exception{

		BDPartidas.saveImportPactatPartida(TestBDPartides.conBD, null);
		FactorsCorreccio factorsCorreccio = new FactorsCorreccio();
		BDPartidas.saveImportPactatPartida(TestBDPartides.conBD, factorsCorreccio);
		factorsCorreccio.setIdPartida(String.valueOf(TestBDPartides.partidaId));
		factorsCorreccio.setYear(String.valueOf(TestBDPartides.yearOfTest));
		factorsCorreccio.setImportpactat("20.89");
		BDPartidas.saveImportPactatPartida(TestBDPartides.conBD, factorsCorreccio);

	}

	@Test
	public void testGetInfoSecondTableOfFacturacionPartidas() throws Exception{		
		
		Assert.assertNotNull(BDPartidas.getInfoSecondTableOfFacturacionPartidas(TestBDPartides.conBD, null, null, null, null));
		Assert.assertNotNull(BDPartidas.getInfoSecondTableOfFacturacionPartidas(TestBDPartides.conBD, TestBDPartides.yearOfTest, null, null, null));
		
	}
	
	@Test
	public void testGetImportPactatPartida() throws Exception{		
		
		Assert.assertNull(BDPartidas.getImportPactatPartida(TestBDPartides.conBD, null,null));
		Assert.assertNull(BDPartidas.getImportPactatPartida(TestBDPartides.conBD,TestBDPartides.yearOfTest ,null));
		Assert.assertNull(BDPartidas.getImportPactatPartida(TestBDPartides.conBD,null,TestBDPartides.partidaId));
		Assert.assertNotNull(BDPartidas.getImportPactatPartida(TestBDPartides.conBD,TestBDPartides.yearOfTest,TestBDPartides.partidaId));
		
	}
	
	@Test
	public void testDeleteImportPactatPartida() throws Exception{		
	
		BDPartidas.deleteImportPactatPartida(TestBDPartides.conBD, null,null);
		BDPartidas.deleteImportPactatPartida(TestBDPartides.conBD,TestBDPartides.yearOfTest ,null);
		BDPartidas.deleteImportPactatPartida(TestBDPartides.conBD,null,TestBDPartides.partidaId);
		BDPartidas.deleteImportPactatPartida(TestBDPartides.conBD,TestBDPartides.yearOfTest,TestBDPartides.partidaId);
		
	}
	
	@Test
	public void testGetInfoOfFacturacionPartida() throws Exception{		
		
		Assert.assertNotNull(BDPartidas.getInfoOfFacturacionPartida(TestBDPartides.conBD,null,null));
		Assert.assertNotNull(BDPartidas.getInfoOfFacturacionPartida(TestBDPartides.conBD,TestBDPartides.years,TestBDPartides.partidaId));
		
	}
	
	@Test
	public void testGetNomPartida() throws Exception{		
		
		Assert.assertTrue(BDPartidas.getNomPartida(TestBDPartides.conBD,null)!=null && BDPartidas.getNomPartida(TestBDPartides.conBD,null).equals(""));
		Assert.assertNotNull(BDPartidas.getNomPartida(TestBDPartides.conBD,TestBDPartides.partidaId));		
		
	}
	
	@Test
	public void testGetInfoTableDetallPartida() throws Exception{		
			
		Assert.assertNotNull(BDPartidas.getInfoTableDetallPartida(TestBDPartides.conBD, null,null, null, null, null));		
		Assert.assertNotNull(BDPartidas.getInfoTableDetallPartida(TestBDPartides.conBD, TestBDPartides.yearOfTest,TestBDPartides.partidaId, null, null, null));	
		Assert.assertNotNull(BDPartidas.getInfoTableDetallPartida(TestBDPartides.conBD, null,TestBDPartides.partidaId, null, null, null));	
		
	}
	
	@Test
	public void testGetNumSrvPartides() throws Exception{		
			
		Assert.assertTrue(BDPartidas.getNumSrvPartides(TestBDPartides.conBD,null,TestBDPartides.partidaId )==0);
		Assert.assertTrue(BDPartidas.getNumSrvPartides(TestBDPartides.conBD,TestBDPartides.yearOfTest,null )==0);	
		
	}


}
