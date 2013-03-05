package es.intos.gdscso.crontab;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.utils.EnviamentEmails;
import es.intos.gdscso.utils.Recursos;

public class AlertNoVolums implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("Cron job: Alerta de volums encara no disponible. START!");
		
		String hostSmtp = Recursos.modoServidorSeguro?Recursos.HOST_SEGURO:Recursos.HOST; //ip a voleo (per provocar k falli): 172.19.214.169

		
		
		String body="";
		String subject="";	
		try {
			String lang =context.getMergedJobDataMap().get("lang").toString();		
			boolean adaBaru = LNVolum.ckeckNewData();	
			
			if(lang.equals("es")){
				body="Aun no hay servicios con volumetrias disponibles.";
				subject="Disponibilidad de Servicios.";
			}else{
				body="Encara no hi ha serveis amb volumetries ja disponibles.";
				subject="Disponibilitat de Serveis.";
			}
			
			if(!adaBaru){
				boolean ok = EnviamentEmails.send(hostSmtp, Recursos.EMAIL_SENDER, Recursos.EMAIL_TO,"", "", subject, false, body, true, "", null);				
				System.out.println("Cron job: Alerta de volums encara no disponible no exist. END!");
			}
			System.out.println("Cron job: Alerta de volums encara no disponible exist. END!");
			
		} catch (Exception e) {
			System.out.println("Quartz error!!! enviament mail!");
		}

		
		
	}
	
}
