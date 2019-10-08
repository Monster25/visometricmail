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
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import java.awt.Font;

public class GUI implements VisoSSView {
	
	private JFrame mainFrame;
	private VisoSSController controller;
	private JPanel panel_1;
	private JTextField txtReadyForWork;
	
	public GUI()
	{
		initialize();
	}
	
	private void initialize()
	{
		mainFrame = new JFrame();
		mainFrame.setSize(300, 200);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainFrame.setTitle("Visometric Service");
		ImageIcon img = new ImageIcon("C:\\Users\\Visometric2x6\\Desktop\\EclipseWorkspace\\VisometricScreenShotSender\\resources\\icon.png");
		mainFrame.setIconImage(img.getImage());
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(UIManager.getBorder("Menu.border"));
		mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		txtReadyForWork = new JTextField() {
			@Override public void setBorder(Border border) {
				//No!
			}
		};
		txtReadyForWork.setBackground(new Color(255, 255, 255));
		txtReadyForWork.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtReadyForWork.setText("Ready for Work!");
		txtReadyForWork.setEditable(false);
		txtReadyForWork.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtReadyForWork);
		txtReadyForWork.setColumns(10);		
		

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
		
	}

}
