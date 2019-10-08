package visoss.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

import visoss.view.VisoSSView;

public class Mail {
	private String SMTP_SERVER;
	
	private String EMAIL_FROM;
	private String EMAIL_TO;
	
	private String EMAIL_TO_CC;
	
	private String EMAIL_SUBJECT;
	private String EMAIL_TEXT;
	
	private String attachmentPath;
	private String attachmentName;
	private String attachmentExtension;
	
	private VisoSSView view;
	
	public Mail(VisoSSView view, String emailFrom, String emailTo, String emailCC, String subject, String text, String attachmentName, String attachmentPath, String attachmentExtension)
	{
		this.view = view;
		
		EMAIL_FROM = emailFrom;
		EMAIL_TO = emailTo;
		EMAIL_TO_CC = emailCC;
		EMAIL_SUBJECT = subject;
		EMAIL_TEXT = text;
		
		this.attachmentName = attachmentName;
		this.attachmentPath = attachmentPath;
		this.attachmentExtension = attachmentExtension;
		
	}
	
	public void sendMessage() throws FileNotFoundException
	{
		//set properties and connection info for smpt server
		Properties properties = System.getProperties();
		if (SmtpData.getInstance().getSecurityOption().equals("tls"))
			//office365 smtp
		{
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.starttls.enable", "true");
		}
		else
		{
			//gmail smtp
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}

		Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            		protected PasswordAuthentication getPasswordAuthentication() {
            				return new PasswordAuthentication(SmtpData.getInstance().getUsername(), SmtpData.getInstance().getPassword());
            }
        });
		
		//Compose message
		Message msg = new MimeMessage(session);
		
		try {
			
			msg.setFrom(new InternetAddress(EMAIL_FROM));
			
			msg.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(EMAIL_TO, false));
			
			msg.setSubject(EMAIL_SUBJECT);
			
			//Text
			MimeBodyPart p1 = new MimeBodyPart();
			p1.setText(EMAIL_TEXT);
			
			//File
			MimeBodyPart p2 = new MimeBodyPart();
			String filePath = attachmentPath+attachmentName+attachmentExtension;
			
			FileDataSource fds = new FileDataSource(filePath);
			p2.setDataHandler(new DataHandler(fds));
			p2.setFileName(fds.getName());
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(p1);
			mp.addBodyPart(p2);
			
			msg.setContent(mp);
			
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			//connect
			t.connect(SmtpData.getInstance().getSmtp(), SmtpData.getInstance().getUsername(), SmtpData.getInstance().getPassword());
			
			//send
			t.sendMessage(msg,  msg.getAllRecipients());
			
			view.showConsole("Response: " + t.getLastServerResponse());
			view.showGui("Mail sent to "+EMAIL_TO+" successfully!");
			
			t.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
			view.showGui("Mail not sent. Error occured!");
		}
		
		
		
	}
}
