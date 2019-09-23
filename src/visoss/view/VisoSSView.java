package visoss.view;

import visoss.controller.VisoSSController;

public interface VisoSSView {
	
	void startView(VisoSSController controller);
	void showConsole(String text);
	void showGui(String text);
	

}
