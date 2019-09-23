package visoss.controller;

import java.io.IOException;

import visoss.model.InitData;
import visoss.view.VisoSSView;

public class VisoSSController {
	
	private VisoSSView view;
	private VisoSSServer server;
	
	public VisoSSController(VisoSSView view)
	{
		this.view = view;
		
		//Get port from file
		try {
			this.server = new VisoSSServer(InitData.getInstance().getServerPort(), this);
			
		} catch (IOException e1){
			e1.printStackTrace();
			view.showGui("Server not opened! Error occured!");
		}
		
		//Start thread for server
		Thread t = new Thread(this.server);
		t.start();
	}

	
	public VisoSSView getView()
	{
		return view;
	}

}
