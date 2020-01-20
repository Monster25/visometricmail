package visoss.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;

import visoss.model.Mail;
import visoss.view.VisoSSView;

public class VisoSSThreadHandler implements Runnable {
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Socket serverSocket;
	private Socket clientSocket;
	private VisoSSView view;
	private String ip;
	
	public VisoSSThreadHandler(Socket clientSocket, VisoSSView view) throws IOException 
	{
		super();
		//Store client socket
		this.clientSocket = clientSocket;
		
		//Read from client stream
		inputStream = new DataInputStream(clientSocket.getInputStream());
		
		//Write to client stream
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
		
		this.view = view;
		
		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.showGui(ip + " connected.");
		
	}

	
	//Read data from Client
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean continueCommunicating = true;
		//System.out.println("bitch");
		int count;
		byte[] buffer = new byte[8192];
		//Keep running while stream has data.
		try {
			String str = null;
			while ((count = inputStream.read(buffer)) > 0)
			{
				//Save buffer to string
				str = new String(buffer);
				//Remove empty spaces left over from buffer
				str = str.replaceAll("\u0000", "");
				view.showConsole(ip + " -> " + str);	
				//Send mail
				Mail sendMail = new Mail(view, "tudor@visometric.dk", "horvathstefandaniel@gmail.com", "", "VisomtricDemo", "Hey! Don't forget your Visometric Demo Screenshot!", str, "E:\\AndreiUnrealEngineProjects\\VisoArchViz\\NewVisoArchViz\\Saved\\Screenshots\\Windows\\", ".png");
				sendMail.sendMessage();
				view.showConsole("Mail Sent!");

				} 
		 view.showConsole("Closing connection to client: " + ip);
		}
		catch (Exception e1) {
		// TODO Auto-generated catch block
		String message = e1.getMessage();
		if (message == null)
		{
			message = "Connection lost";
	}
		view.showConsole("Error for client: " + ip + " - Message: " + message);
		}
		
	}
	
	

}
