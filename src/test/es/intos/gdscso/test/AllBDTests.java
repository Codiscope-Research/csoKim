package es.intos.gdscso.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBDCso.class, TestBDEstats.class, TestBDFacturas.class,TestBDIncidencies.class, TestBDPartides.class, TestBDPreus.class, TestBDVolum.class, TestBDServeis.class,TestBDImportsPactats.class,TestUtils.class })
public class AllBDTests{

}


