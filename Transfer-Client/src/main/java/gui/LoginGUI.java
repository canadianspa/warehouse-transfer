package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import entities.Item;
import requests.LoginRequest;
import requests.Settings;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("Label.font",new Font("Arial", Font.BOLD, 20));
					UIManager.put("TextField.font",new Font("Arial", Font.BOLD, 20));
					UIManager.put("Button.font",new Font("Arial", Font.BOLD, 20));
					UIManager.put("ComboBox.font",new Font("Arial", Font.BOLD, 20));
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 200);
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
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginRequest lr = new LoginRequest(txtEmail.getText(),txtPassword.getText());
				Gson g = new Gson();
				ArrayList<Item> result = new ArrayList<Item>();
				Client client = ClientBuilder.newClient();
				Response response;
				response = client.target(Settings.serverPath + "Login")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.post(Entity.json(g.toJson(lr)));
				
				String body = response.readEntity(String.class);	

				if(body.equals("Failed"))
				{
					JOptionPane.showMessageDialog(null, "Failed Login");
					
				}
				else
				{
					System.out.println(body);
					Settings.userKey = body;
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
					
					LoginGUI.this.setVisible(false);
				}

				

				

			}
		});
		contentPane.add(btnLogin);
	}

}
