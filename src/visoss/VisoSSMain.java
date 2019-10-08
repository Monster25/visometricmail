package visoss;

import visoss.controller.VisoSSController;
import visoss.model.SmtpData;
import visoss.view.GUI;
import visoss.view.VisoSSView;

public class VisoSSMain {
	
	public static void main(String[] args)
	{
		//Init
		SmtpData.getInstance().getData();
		//view
		VisoSSView view = new GUI();
		//controller
		VisoSSController controller = new VisoSSController(view);
		//Start thread
		view.startView(controller);
	}

}
