package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JButton viewUsers = new JButton("Show Users");
		viewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowUsersGUI frame = new ShowUsersGUI();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(viewUsers);

		JButton createUsers = new JButton("Create Users");
		createUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUserGUI frame = new CreateUserGUI();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(createUsers);

	}

}
