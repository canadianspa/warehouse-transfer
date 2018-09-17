package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import requests.CreateUserRequest;
import requests.Settings;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtLevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserGUI frame = new CreateUserGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateUserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Email");
		contentPane.add(lblNewLabel);
		
		txtEmail = new JTextField();
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		contentPane.add(lblNewLabel_1);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_3 = new JLabel("Level");
		contentPane.add(lblNewLabel_3);
		
		txtLevel = new JTextField();
		txtLevel.setColumns(10);
		contentPane.add(txtLevel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CreateUserRequest cur = new CreateUserRequest(txtEmail.getText(),txtPassword.getText(),Integer.parseInt(txtLevel.getText()));
				Gson g = new Gson();
				Client client = ClientBuilder.newClient();
				Response response = client.target(Settings.serverPath + "User")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.post(Entity.json(g.toJson(cur)));

				String body = response.readEntity(String.class);	
				System.out.println(body);
				

				MainGUI frame = new MainGUI();
				frame.setVisible(true);
			}
		});
		contentPane.add(btnCreate);
	}

}
