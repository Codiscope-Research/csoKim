package es.intos.gdscso.actions.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.intos.gdscso.action.test.facturacion.TestAjaxTableDetallPartidaAction;
import es.intos.gdscso.action.test.facturacion.TestAjaxTableFacturacionPartiesAction;
import es.intos.gdscso.action.test.facturacion.TestAjaxTablePartidaAction;
import es.intos.gdscso.action.test.facturacion.TestDetallPartidaAction;
import es.intos.gdscso.action.test.facturacion.TestFacturacionPartidasAction;
import es.intos.gdscso.action.test.facturacion.TestPartidaGeneraExcelAction;
import es.intos.gdscso.action.test.gestio.TestChangeStatFacturaAction;
import es.intos.gdscso.action.test.gestio.TestEditFacturaAction;
import es.intos.gdscso.action.test.gestio.TestFacturaPDFAction;
import es.intos.gdscso.action.test.gestio.TestFacturaSaveAction;
import es.intos.gdscso.action.test.gestio.TestGestioFacturaAction;
import es.intos.gdscso.actions.test.consulta.TestBusquedaGestionAction;
import es.intos.gdscso.actions.test.consulta.TestGeneraFacturasExcelAction;
import es.intos.gdscso.actions.test.control.TestAjaxTableCtrlAction;
import es.intos.gdscso.actions.test.control.TestAjaxTableCtrlSrvAction;
import es.intos.gdscso.actions.test.control.TestControlAction;
import es.intos.gdscso.actions.test.control.TestGeneraExcelServiciosCsoAction;
import es.intos.gdscso.actions.test.excelPagades.TestGeneraFacturasPagadasExcelAction;
import es.intos.gdscso.actions.test.generar.TestAjaxLoadFactorsFacturaAction;
import es.intos.gdscso.actions.test.generar.TestAjaxSelectFacturesHomonimesAction;
import es.intos.gdscso.actions.test.generar.TestAjaxTableCsoSrvAction;
import es.intos.gdscso.actions.test.generar.TestGenerarFacturaAction;
import es.intos.gdscso.actions.test.generar.TestGuardarFactorsFacturaAction;
import es.intos.gdscso.actions.test.generar.TestSaveFacturaAction;
import es.intos.gdscso.actions.test.manteniments.TestAjaxGetImporteAction;
import es.intos.gdscso.actions.test.manteniments.TestAjaxLoadFactorsCrecimientoAction;
import es.intos.gdscso.actions.test.manteniments.TestAjaxLoadTableSrvDispAction;
import es.intos.gdscso.actions.test.manteniments.TestAjaxLoadTableSrvPartidaAction;
import es.intos.gdscso.actions.test.manteniments.TestGestioUsuarisAction;
import es.intos.gdscso.actions.test.manteniments.TestGuardaFactorsAction;
import es.intos.gdscso.actions.test.manteniments.TestMantenimentImportsPactatsAction;
import es.intos.gdscso.actions.test.manteniments.TestMantenimentLineasAction;
import es.intos.gdscso.actions.test.manteniments.TestMantenimentPreusAction;
import es.intos.gdscso.actions.test.manteniments.TestMantenimentServAction;
import es.intos.gdscso.actions.test.manteniments.TestPreGestioUsuarisAction;
import es.intos.gdscso.actions.test.manteniments.TestSaveImportPactatAction;
import es.intos.gdscso.actions.test.modificar.TestAjaxTableSrvDispAction;
import es.intos.gdscso.actions.test.modificar.TestModificarFacturaAction;
import es.intos.gdscso.actions.test.partes.TestAjaxCargaInfoNoConfirmadosAction;
import es.intos.gdscso.actions.test.partes.TestGeneraExcelIncidenciaAction;
import es.intos.gdscso.actions.test.partes.TestPartesAction;

@RunWith(Suite.class)
@SuiteClasses({ TestBusquedaGestionAction.class, TestControlAction.class, TestDetallPartidaAction.class,
		TestFacturacionPartidasAction.class, TestGeneraFacturasExcelAction.class, TestAjaxTableCtrlAction.class,
		TestGeneraExcelServiciosCsoAction.class, TestAjaxTableCtrlSrvAction.class, TestAjaxTableDetallPartidaAction.class,
		TestGeneraFacturasPagadasExcelAction.class, TestAjaxTableFacturacionPartiesAction.class,
		TestAjaxTableFacturacionPartiesAction.class, TestAjaxTablePartidaAction.class, TestPartidaGeneraExcelAction.class,
		TestPartesAction.class, TestAjaxCargaInfoNoConfirmadosAction.class, TestModificarFacturaAction.class,
		TestAjaxTableSrvDispAction.class, TestSaveImportPactatAction.class, TestPreGestioUsuarisAction.class,
		TestMantenimentServAction.class, TestMantenimentPreusAction.class, TestMantenimentLineasAction.class,
		TestMantenimentImportsPactatsAction.class, TestGuardaFactorsAction.class, TestGestioUsuarisAction.class,
		TestAjaxGetImporteAction.class, TestAjaxLoadFactorsCrecimientoAction.class, TestChangeStatFacturaAction.class,
		TestEditFacturaAction.class, TestFacturaPDFAction.class, TestGestioFacturaAction.class, TestFacturaSaveAction.class,
		TestAjaxLoadFactorsFacturaAction.class, TestAjaxSelectFacturesHomonimesAction.class, TestAjaxTableCsoSrvAction.class,
		TestAjaxTableSrvDispAction.class, TestGenerarFacturaAction.class, TestGuardarFactorsFacturaAction.class,
		TestSaveFacturaAction.class, TestGeneraExcelIncidenciaAction.class, TestAjaxLoadTableSrvPartidaAction.class, TestAjaxLoadTableSrvDispAction.class})
public class AllActionTests{

}
