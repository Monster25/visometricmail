package mail;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Base64;
import java.util.Scanner;
import java.util.concurrent.Executors;



public class UE4Server {
	

	private String messageReceived;
	
	public static void main(String[] args) throws IOException
	{
		
		try (var listener = new ServerSocket(59898))
		{
			System.out.println("Server listening...");
			var pool = Executors.newFixedThreadPool(20);
			
			while (true)
			{
				pool.execute(new TestConnection(listener.accept()));
			}
		}
	}
	
	private static class TestConnection implements Runnable
	{
		private Socket socket;
		private String receivedMessage;
		private SendMail mailSender = new SendMail();
		
		TestConnection(Socket socket)
		{
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int count;
			byte[] buffer = new byte[8192];
			//String str = null;
            System.out.println("Connected: " + socket);
			try {
					String str = null;
					while ((count = socket.getInputStream().read(buffer)) > 0)
					{
						//Save buffer to string
					//	str = Base64.getEncoder().encodeToString(buffer);
				//		str = Encoding.Unicode.GetString(bytes);
					str = new String(buffer);
					//Remove empty spaces left over from buffer
					str = str.replaceAll("\u0000", "");
				//	System.out.println(str = new String(buffer));
					System.out.println(str);
					mailSender.setName(str);
					mailSender.setPath("C:\\Users\\Visometric2x6\\Desktop\\UnrealStudioProjectsAndrei\\VisoArchViz4.23\\Saved\\Screenshots\\Windows\\");
					mailSender.sendMessage();
					}

					
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

}
	}
}

