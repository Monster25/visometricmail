package visoss;

import visoss.controller.VisoSSController;
import visoss.view.GUI;
import visoss.view.VisoSSView;

public class VisoSSMain {
	
	public static void main(String[] args)
	{
		//view
		VisoSSView view = new GUI();
		//controller
			VisoSSController controller = new VisoSSController(view);
	}

}
