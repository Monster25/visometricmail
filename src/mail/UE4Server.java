package mail;

import java.io.IOException;
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
			System.out.println("Server starting...");
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
			// TODO Auto-generated method stub
            System.out.println("Connected: " + socket);
            /*
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    out.println(in.nextLine().toUpperCase());
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            }
            */
          //  finally {
                try { socket.close(); } catch (IOException e) {}
                System.out.println("Closed: " + socket);
            //}
		}
		
	}

}
