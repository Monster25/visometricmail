package mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.smtp.SMTPTransport;

public class SendMail {
	
	private static final String SMTP_SERVER = "james.local";
	private static final String USERNAME = "user01";
	private static final String PASSWORD = "1234";
	
	private static final String EMAIL_FROM = "andreicioanca25@gmail.com";
	private static final String EMAIL_TO = "andreicioanca25@gmail.com";
	
	private static final String EMAIL_TO_CC = "";
	
	private static final String EMAIL_SUBJECT = "Test Send";
	private static final String EMAIL_TEXT = "Hello!";
	
	public static void main(String[] args)
	{
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", true);
		//properties.put("mail.smtp.host", "localhost");
		//properties.put("mail.smtp.port", "25");
		
		Session session = Session.getInstance(properties, null);
		
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
			String filePath = "C:\\Users\\Visometric2x6\\Desktop\\doggo.jpg";
			FileDataSource fds = new FileDataSource(filePath);
			p2.setDataHandler(new DataHandler(fds));
			p2.setFileName(fds.getName());
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(p1);
			mp.addBodyPart(p2);
			
			msg.setContent(mp);
			
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			//connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			//send
			t.sendMessage(msg,  msg.getAllRecipients());
			
			System.out.println("Response: " + t.getLastServerResponse());
			
			t.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
