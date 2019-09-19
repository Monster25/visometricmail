package visoss.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import visoss.controller.VisoSSController;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GUI implements VisoSSView {
	
	private JFrame mainFrame;
	private VisoSSController controller;
	private JTextField txtConnectedToUe;
	private JTextField connection;
	
	public GUI()
	{
		initialize();
	}
	
	private void initialize()
	{
		mainFrame = new JFrame();
		
		mainFrame.setTitle("Visometric Screenshot Sender");
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		txtConnectedToUe = new JTextField();
		txtConnectedToUe.setEditable(false);
		txtConnectedToUe.setFocusable(false);
		txtConnectedToUe.setDisabledTextColor(UIManager.getColor("Panel.background"));
		txtConnectedToUe.setBackground(Color.WHITE);
		txtConnectedToUe.setHorizontalAlignment(SwingConstants.CENTER);
		txtConnectedToUe.setText("Connected to UE4:");
		panel.add(txtConnectedToUe);
		txtConnectedToUe.setColumns(11);
		
		connection = new JTextField() {
			@Override public void setBorder(Border border)
			{
				//No Border
			}
		};
		connection.setEditable(false);
		connection.setBorder(null);
		connection.setForeground(Color.RED);
		connection.setBackground(UIManager.getColor("Panel.background"));
		connection.setHorizontalAlignment(SwingConstants.CENTER);
		connection.setText("No");
		panel.add(connection);
		connection.setColumns(10);
		

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
	public void show(String text) {
		// TODO Auto-generated method stub
		System.out.println("Server: "+text);
		
	}

}
