package mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class UE4Server {
	
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
		
		TestConnection(Socket socket)
		{
			this.socket = socket;
		}

		@Override
		public void run() {
			String str = (char*) data;
			// TODO Auto-generated method stub
            System.out.println("Connected: " + socket);
            try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while ((str = br.readLine())  != null)
				{
					
					System.out.println("The message: " + str);
				}
			} catch (IOException e) {
				System.out.println("Client disconnected!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
