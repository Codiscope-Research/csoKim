package es.intos.gdscso.crontab;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.utils.EnviamentEmails;
import es.intos.gdscso.utils.Recursos;

public class AlertFacturesPendents implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("Cron job: Looking for bills to check by the provider. START");
		
		String hostSmtp = Recursos.modoServidorSeguro?Recursos.HOST_SEGURO:Recursos.HOST; //ip a voleo (per provocar k falli): 172.19.214.169
		
		String body="";
		String subject="";		

		try {
			BusquedaNoConformidadesFormDTO facturaDTO = new BusquedaNoConformidadesFormDTO();
			String lang =context.getMergedJobDataMap().get("lang").toString();			 
		
			if(lang.equals("es")){
				body="Hay facturas pendientes de comfirmar";
				subject="Facturas Pendientes.";		
			}else{
				body="Hi ha factures factures pendents de comfirmar.";
				subject="Factures Pendents.";
			}
			
			int num =LNFacturas.getNumRegNoConformidades(facturaDTO);	
			
			if(num >= 0){
				for(String prov : Recursos.EMAIL_PROVS ){
					
						boolean ok = EnviamentEmails.send(hostSmtp, Recursos.EMAIL_SENDER, prov,"", "", subject, false, body, true, "", null);
				
				}
								
				System.out.println("Cron job: Looking for bills to check by the provider. END");
			}
			
			
		} catch (Exception e) {
			System.out.println("Quartz error!!! enviament mail!");
		}

		
		
	}
	
}
