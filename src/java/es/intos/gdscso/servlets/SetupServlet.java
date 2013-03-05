package es.intos.gdscso.servlets;

// import java specific packages
import java.io.File;
import java.net.InetAddress;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import es.intos.gdscso.crontab.AlertFacturesPendents;
import es.intos.gdscso.crontab.AlertNoVolums;
import es.intos.gdscso.utils.Recursos;
import es.intos.sigma.log.MemLog;
import es.intos.sigma.log.ThreadMemLog;
import es.intos.util.Autorizacion;
import es.intos.util.sql.GestorConexionesBD;

/**
 * This servlet performs the task of setting up the log4j configuration.
 * <p>
 * This servlet is loaded on startup by specifying the load on startup property
 * in the web.xml. On load, it performs the following activities:
 * <ul>
 * Looks for the log4j configuration file.
 * <ul>
 * Configures the PropertyConfigurator using the log4j configuration file if it
 * finds it, otherwise throws an Error on your Tomcat screen. So make sure to
 * check the Tomcat screen once it starts up. However, you will still be able to
 * access the main application, but wont get any log statements as you would
 * expect. NOTE: This illustrates an important point about Log4J. That it is
 * fail safe. The application will not stop running because Log4J could not be
 * set up.
 * 
 * 
 */

@SuppressWarnings({ "serial" })
public class SetupServlet extends HttpServlet{
	private String			ipsreal		= null;
	private String			logsConfig	= null;
	private String			configFile	= null;
	private String			bdsDir		= null;
	// private String rppDefecto=null;
	// private String watch=null;
	private String			app			= null;
	private static Logger	__log		= Logger.getLogger(SetupServlet.class.getName());
	private ServletConfig	config		= null;

	private final void println( String msg ){

		System.out.println(app + "____" + msg);
		__log.debug(app + "____" + msg);
	}

	
	
	private final String getParametro( String parametro ) throws Exception{

		String retorno = config.getInitParameter(parametro);
		println(parametro + ": " + retorno);
		return (retorno);
	}

	public void init( ServletConfig config ) throws ServletException{

		try {
			String sOSNAME = System.getProperty("os.name");// OBTENIM EL SISTEMA
															// OPERATIU
			String inicioCorrecto = "";
			// println(sOSNAME
			// +" == "+sOSNAME.equalsIgnoreCase("Windows Vista"));

			// if (sOSNAME.equalsIgnoreCase("Windows XP") ||
			// sOSNAME.equalsIgnoreCase("Windows 2000") ||
			// sOSNAME.equalsIgnoreCase("Windows Vista")) {//INCI DE VARIABLES
			// WINDOWS
			
			// Comencem el cron per enviar correus
			
			
			
			JobDetail job = new JobDetail();
	    	job.setName("dummyJobName");
	    	job.setJobClass(AlertNoVolums.class);
	    	JobDataMap jobDataMap = new JobDataMap();
	    	jobDataMap.put("lang", "ca");
	    	job.setJobDataMap(jobDataMap);
	 
	    	CronTrigger trigger = new CronTrigger();
	    	trigger.setName("dummyTriggerName");
	    	
	    	//s'executa cada dia0 0 * * *
	    	trigger.setCronExpression("0 0 0 * * ?");
	    	//s'executa cada 30 segons
	    	//trigger.setCronExpression("0/30 * * * * ?");
	 
	    	JobDetail job2 = new JobDetail();
	    	job2.setName("jobFactures");
	    	job2.setJobClass(AlertFacturesPendents.class);
	    	JobDataMap jobDataMap2 = new JobDataMap();
	    	jobDataMap2.put("lang", "ca");
	    	job2.setJobDataMap(jobDataMap2);
	    	
	    	CronTrigger trigger2 = new CronTrigger();
	    	trigger2.setName("jobFacturesTrigger");
	    	
	    	//s'executa cada dia0 0 * * *
	    	trigger2.setCronExpression("0 0 0 * * ?");
	    	
	    	//schedule it
	    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
	    	scheduler.scheduleJob(job2, trigger2);
			
			if (sOSNAME.indexOf("Windows") >= 0) {// INCI DE VARIABLES WINDOWS
				this.config = config;
				app = getParametro("app");
				logsConfig = getParametro("logsConfig");
				configFile = getParametro("configFile");
				inicioCorrecto = establecerLog(config);

				println("******************************  SETUPSERVLET DE: " + app + " ************************");
				ipsreal = getParametro("ipsreal");
				bdsDir = getParametro("bdsDir");
				// rppDefecto = getParametro("rppDefecto");
				// watch = getParametro("watch");
			} else {// INICI VARIABLES LINUX I ALTRES
				this.config = config;
				app = getParametro("app");
				logsConfig = getParametro("logsConfigLinux");// RUTES
																// TRANSFORMADES
																// A SISTEMA
																// LINUX
				configFile = getParametro("configFileLinux");
				inicioCorrecto = establecerLog(config);

				println("******************************  SETUPSERVLET DE: " + app + " ************************");
				ipsreal = getParametro("ipsreal");
				bdsDir = getParametro("bdsDirLinux");// RUTES TRANSFORMADES A
														// SISTEMA LINUX
				// rppDefecto = getParametro("rppDefecto");
				// watch = getParametro("watch");
			}
			println("user.dir-> " + System.getProperty("user.dir"));
			println("user.home-> " + System.getProperty("user.home"));
			println("os.name->  " + System.getProperty("os.name"));

			Properties props = Recursos.doConfigureFile(configFile);

			println("Creando MemLog i Thread que los guarda ... ");
			es.intos.Recursos.memLog = new MemLog(props.getProperty("diccionario"));
			ThreadMemLog tMemLog = new ThreadMemLog();
			Thread thread = new Thread(tMemLog);
			thread.start();
			println("MemLog i Thread creados.");

			println("Realizando test de pruebas ...");
			InetAddress ina = InetAddress.getLocalHost();
			String ipTomcat = ina.getHostAddress();
			println("IP del Tomcat en ejecución: " + ipTomcat);
			Recursos.pruebas = (ipsreal.indexOf(ipTomcat) == -1);
			println("Test de pruebas realizado. PRUEBAS=" + Recursos.pruebas);

			println("Realizando test de servidor seguro ...");
			String ipServidorSeguro = config.getInitParameter("ipServidorSeguro");
			String ipServidorSeguroPruebas = config.getInitParameter("ipServidorSeguroPruebas");
			es.intos.Recursos.modoServidorSeguro = (ipServidorSeguro.indexOf(ipTomcat) != -1 || ipServidorSeguroPruebas.indexOf(ipTomcat) != -1);
			println("Test de servidor seguro realizado. SERVIDOR_SEGURO=" + es.intos.Recursos.modoServidorSeguro);

			println("Configurando Datasource ...");
			Recursos.gbd = new GestorConexionesBD(logsConfig, bdsDir);
			println("Datasource configurado");

			println("Configurando Autorizacion ...");
			// ConexionBD conDiccionario =
			// Recursos.gbd.getConexionBD("diccionario");
			// Autorizacion.setConexion(conDiccionario);
			Autorizacion.setGBD(Recursos.gbd, props.getProperty("diccionario"));
			println("Autorizacion configurado");

			println("Eliminando ficheros subidos no confirmados ...");
			// LNDocs.eliminaFicherosNoConfirmados();
			println("Ficheros subidos no confirmados eliminados");

			if (Recursos.pruebas) {
				println("******************************  ENTORNO DE PRUEBAS INICIALIZADO " + inicioCorrecto + "************************");
			} else {
				println("******************************  ENTORNO DE PRODUCCION INICIALIZADO " + inicioCorrecto + "************************");
			}

		} catch (Exception e) {
			println("Error en SetupServlet.init");
			e.printStackTrace();
		} finally {
			println("\n\nSetupServlet:end init()\n\n");
		}

	}// end init

	public String establecerLog( ServletConfig config ) throws Exception{

		// Establecimiento del archivo de configuración de traceo para
		// aplicación on line de oficinas
		File file = new File(logsConfig);
		if (logsConfig == null || logsConfig.length() == 0 || !(file.isFile())) {

			__log.error("ERROR: No se ha podido inicializar el archivo de configuración del traceo para aplicación de oficinas. "
					+ "Por favor, comprobar el path escrito en el parámetro logsConfig en web.xml");
			return ("INCORRECTAMENTE");
		}
		String watch = getParametro("watch");
		if (watch != null && watch.equalsIgnoreCase("true"))
			PropertyConfigurator.configureAndWatch(logsConfig);
		else
			PropertyConfigurator.configure(logsConfig);
		return ("CORRECTAMENTE");
	}

	/*
	 * private static void initCache() throws Exception { if
	 * (Cache.provinciasCAS!=null && Cache.provinciasCAT!=null) return;
	 * ConexionBD con = null; try { con =
	 * SGesSTRForm.getGestor().getConexionBD("Nifsmenor",false);
	 * con.beginTrans(); Cache.provinciasCAS = BDCliProvincias.select(con,
	 * Utils.es_ES); Cache.provinciasCAT = BDCliProvincias.select(con,
	 * Utils.ca_ES);
	 * 
	 * Vector estados = BDCliEstados.select(con);
	 * 
	 * Cache.cliEstadosCAS=new HashMap(); Cache.cliEstadosCAT=new HashMap();
	 * 
	 * Cache.cliEstadosCAS.put("-1", "Pdte. Reinstalar");
	 * Cache.cliEstadosCAT.put("-1", "Pdnt. Reinstal·lar");
	 * 
	 * for (int i=0;i<estados.size();i++) { CliEstado cliEstado = (CliEstado)
	 * estados.get(i);
	 * Cache.cliEstadosCAS.put(cliEstado.getCodigo(),cliEstado.getDescCAS());
	 * Cache.cliEstadosCAT.put(cliEstado.getCodigo(),cliEstado.getDescCAT());
	 * }//end loop estados
	 * 
	 * Cache.tiposBajaCAS = BDCliTipoBajas.select(con, Utils.es_ES);
	 * Cache.tiposBajaCAT = BDCliTipoBajas.select(con, Utils.ca_ES);
	 * 
	 * con.commit(); }catch (Exception e) { if (null!=con) con.rollback(); throw
	 * e; }
	 * 
	 * }//end initCache
	 */

	public void destroy(){

		// acabo de grabar en la tabla lo que queda en memoria.
		println("\n\nSetupServlet:begin destroy()\n\n");
		/*
		 * try { synchronized(es.intos.Recursos.memLog) {
		 * es.intos.Recursos.memLog.grabaInfoBdTodo();
		 * es.intos.Recursos.memLog.setAcabar(true);
		 * Recursos.gbd.cerrarConexiones(); } } catch (Exception e) {
		 * println("Error en SetupServlet.destroy"); e.printStackTrace();
		 * }finally {
		 */
		println("\n\nSetupServlet:end destroy()\n\n");
		// }

	}// end destroy

}
