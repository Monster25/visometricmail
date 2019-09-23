package visoss.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import visoss.controller.VisoSSController;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class GUI implements VisoSSView {
	
	private JFrame mainFrame;
	private VisoSSController controller;
	private JTextArea consoleArea;
	private JPanel panel_1;
	private JScrollPane scroll;
	
	public GUI()
	{
		initialize();
	}
	
	private void initialize()
	{
		mainFrame = new JFrame();
		mainFrame.setSize(600, 400);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainFrame.setTitle("Visometric Screenshot Sender");
		
		panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("Menu.border"));
		mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		consoleArea = new JTextArea();
		consoleArea.setColumns(50);
		consoleArea.setLineWrap(true);
		consoleArea.setEditable(false);
		consoleArea.setBackground(Color.WHITE);
		panel_1.add(consoleArea);
		scroll = new JScrollPane(consoleArea);
		mainFrame.add(scroll);
		
		

	}
	
	@Override
	public void startView(VisoSSController controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public void showConsole(String text) {
		// TODO Auto-generated method stub
		System.out.println("Server: "+text);
		
	}
	
	@Override
	public void showGui(String text) {
		consoleArea.append(text + "\n");
	}

}
