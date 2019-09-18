package mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class SendMail {
	//smtp.gmail.com
	//smtp.office365
	private static String SMTP_SERVER;
	private static String USERNAME;
	private static String PASSWORD;
	
	private static String EMAIL_FROM = "andreicioanca25@gmail.com";
	private static final String EMAIL_TO = "andreicioanca25@gmail.com";
	
	private static final String EMAIL_TO_CC = "";
	
	private static final String EMAIL_SUBJECT = "Test Send";
	private static final String EMAIL_TEXT = "Hello!";
	
	private String path;
	private String name;
	
	public SendMail()
	{
		
	}
	
	public void sendMessage() throws FileNotFoundException
	{
		//Credential Reading
		File file = new File("C:\\Users\\Visometric2x6\\Desktop\\mailtesting\\test.txt");
		Scanner sc;
			sc = new Scanner(file);
		
		while (sc.hasNextLine())
		{
			SMTP_SERVER = sc.nextLine();
			USERNAME = sc.nextLine();
			PASSWORD = sc.nextLine();
			EMAIL_FROM = USERNAME;
		}
		
		
		//tls 587
		//ssl 465
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		
		properties.put("mail.smtp.auth", true);
		
		properties.put("mail.smtp.starttls.enable", "true");
		//properties.put("mail.smtp.socketFactory.port", "465");
		//properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//properties.put("mail.smtp.host", "localhost");

		
		Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            		protected PasswordAuthentication getPasswordAuthentication() {
            				return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
		
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
			String filePath = path+name;
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
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	

}
