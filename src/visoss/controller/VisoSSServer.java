package visoss.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VisoSSServer implements Runnable{
	
	private ServerSocket welcomeSocket;
	private VisoSSController controller;
	
	public VisoSSServer(int port, VisoSSController controller) throws IOException {
		super();
	
		
		this.controller = controller;
		this.welcomeSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			controller.getView().showConsole(("Waiting for client to connect..."));
			try {
				//Accept client socket
				Socket socket = welcomeSocket.accept();
				
				//Put new client on separate thread
				VisoSSThreadHandler a;
				a = new VisoSSThreadHandler(socket, controller.getView());
				Thread t = new Thread(a);
				t.start();
				
			}
			
			catch (IOException e) {
				controller.getView().showConsole("Error in server. Message: " + e.getMessage());
			}
		}
		
	}

}
