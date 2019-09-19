package visoss.controller;

import visoss.view.VisoSSView;

public class VisoSSController {
	
	private VisoSSView view;
	//private VisoSSServer server;
	
	public VisoSSController(VisoSSView view)
	{
		this.view = view;
		
		
		
	}
	
	public VisoSSView getView()
	{
		return view;
	}

}
