package visoss.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitData {
	
	private static InitData instance;
	
	private int serverPort;
	private String username;
	private String password;
	private String securityOption;
	private String smtp;
	
	private File initData = new File("initData.txt");
	private Scanner scanner;
	
	private InitData()
	{
		
	}
	
	public static InitData getInstance()
	{
		if (instance == null)
		{
			instance = new InitData();
		}
		
		return instance;
	}
	
	public void getData()
	{
		try {
			scanner = new Scanner(initData);
			String[] initData = scanner.nextLine().split(" ");
			//Server port
			InitData.getInstance().setServerPort(Integer.parseInt(initData[0]));
			//Ssl or tls
			InitData.getInstance().setSecurityOption(initData[1]);
			//Smtp 
			InitData.getInstance().setSmtp(initData[2]);
			
	
			//username
			InitData.getInstance().setUsername(initData[3]);
			//password
			InitData.getInstance().setPassword(initData[4]);
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
