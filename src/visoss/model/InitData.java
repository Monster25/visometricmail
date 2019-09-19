package visoss.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitData {
	
	private static InitData instance;
	
	private int serverPort;
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
	
	public int getServerPort()
	{
		return serverPort;
	}
	
	public void setServerPort(int port)
	{
		this.serverPort = port;
	}

	public void getData()
	{
		try {
			scanner = new Scanner(initData);
			String[] initData = scanner.nextLine().split(" ");
			InitData.getInstance().setServerPort(Integer.parseInt(initData[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
