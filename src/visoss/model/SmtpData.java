package visoss.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SmtpData {
	
	private static SmtpData instance;
	
	private int serverPort;
	private String username;
	private String password;
	private String securityOption;
	private String smtp;
	private File initData = new File("smtpData.txt");
	private Scanner scanner;
	
	private SmtpData()
	{
		setServerPort(59989);
	}
	
	public static SmtpData getInstance()
	{
		if (instance == null)
		{
			instance = new SmtpData();
		}
		
		return instance;
	}
	
	public void getData()
	{
		try {
			scanner = new Scanner(initData);
			String[] initData = scanner.nextLine().split(" ");
			//Server port
			//SmtpData.getInstance().setServerPort(Integer.parseInt(initData[0]));
			//Ssl or tls
			SmtpData.getInstance().setSecurityOption(initData[0]);
			//Smtp 
			SmtpData.getInstance().setSmtp(initData[1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public int getServerPort()
	{
		return serverPort;
	}
	
	public void setServerPort(int port)
	{
		this.serverPort = port;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityOption() {
		return securityOption;
	}

	public void setSecurityOption(String securityOption) {
		this.securityOption = securityOption;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getSmtp()
	{
		return smtp;
	}

}
