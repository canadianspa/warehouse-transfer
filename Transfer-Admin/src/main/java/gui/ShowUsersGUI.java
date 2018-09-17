package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.User;
import requests.Settings;

public class ShowUsersGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUsersGUI frame = new ShowUsersGUI();
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
	public ShowUsersGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(Settings.serverPath + "User")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get();

		String body = response.readEntity(String.class);
		Gson g = new Gson();
		ArrayList<User> listOfUsers = g.fromJson(body, new TypeToken<ArrayList<User>>(){}.getType());
		
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		contentPane.add(new JLabel("Email"));
		contentPane.add(new JLabel("Password"));
		contentPane.add(new JLabel("Level"));
		
		
		for(User u: listOfUsers)
		{
			contentPane.add(new JLabel(u.email));
			contentPane.add(new JLabel(u.password));
			contentPane.add(new JLabel(String.valueOf(u.level)));
		}
		
	}
	
	
	

}
