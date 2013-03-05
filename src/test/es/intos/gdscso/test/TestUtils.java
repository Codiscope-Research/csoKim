package es.intos.gdscso.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUtils{


	@Before
	public void setUp() throws Exception{


	}

	@After
	public void tearDown() throws Exception{

	}

	@Test
	public void testJSONisValid() throws Exception{
	
		boolean valid = true;
		valid = es.intos.gdscso.utils.Utils.isValidJSON("sdasd");
		Assert.assertEquals(false, valid);
		StringBuffer jsonSB= new StringBuffer("{\"valid\":\"pol\"}");
		valid = es.intos.gdscso.utils.Utils.isValidJSON(jsonSB.toString());
		Assert.assertEquals(true, valid);
	}
	
	
	
	

}
