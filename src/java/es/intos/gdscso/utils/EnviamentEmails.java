 package es.intos.gdscso.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import es.intos.mail.BufferedDataSource;

public class EnviamentEmails { 

	protected static Logger __log = Logger.getRootLogger();
   
    public static boolean send(String hostSmtp, String senderAddress, String toAddress, String ccAddress, String bccAddress, String subject, 
       boolean isHTMLFormat, String body, boolean debug, String sNomFitxer, byte[] bBlobFitxer){ 

    	MimeMultipart multipart = new MimeMultipart(); 
    	Properties properties = new Properties();
    	
    	properties.put("mail.smtp.host", hostSmtp); 
    	Session session = Session.getDefaultInstance(properties, null); 
    	session.setDebug(debug); 
    	
    	try { 
    		
    		MimeMessage msg = new MimeMessage(session); 
    		msg.setFrom(new InternetAddress(senderAddress)); 
    		msg.setRecipients(Message.RecipientType.TO, toAddress); 
    		msg.setRecipients(Message.RecipientType.CC, ccAddress); 
    		msg.setRecipients(Message.RecipientType.BCC, bccAddress); 
    		msg.setSubject(subject); 
    		msg.setSentDate(new Date()); 
    		
    		// BODY 
    		MimeBodyPart mbp1 = new MimeBodyPart(); 
    		if(isHTMLFormat){ 
    			mbp1.setContent(body, "text/html"); 
    		}else{ 
    			mbp1.setText(body); 
    		} 
    		
    		//ADJUNTEM LES DIFERENTS PARTS DE L'EMAIL
    		multipart.addBodyPart(mbp1); //BODY
    		
    		//ATTACHMENT (MIREM SI CAL ADJUNTAR FITXER)
    		if (!sNomFitxer.equalsIgnoreCase("")){
    			MimeBodyPart mbpN=new MimeBodyPart();
    			
    			//AFEGIM LES DADES DEL DOCUMENT A ADJUNTAR
    			BufferedDataSource bds=(BufferedDataSource) new BufferedDataSource(bBlobFitxer, sNomFitxer);
    			mbpN.setDataHandler(new DataHandler(bds));
    			mbpN.setFileName(bds.getName());
    			multipart.addBodyPart(mbpN);
    		}
    		
    		msg.setContent(multipart);
    		msg.setHeader("X-Mailer", "Aplicacion Caixa");
    		Transport.send(msg); 
    		__log.info("Email Enviat");
    	} 
    	catch (Exception e){ 
    		__log.error (null, e);
    		return false; 
    	} 
    	return true; 
  } 
    
} 