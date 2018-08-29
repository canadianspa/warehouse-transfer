import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JButton newTransfer = new JButton("New Transfer");
		newTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferGUI frame = new TransferGUI();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(newTransfer);
		
		JButton viewCurrentJobs = new JButton("View Jobs in Transit");
		viewCurrentJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTransitJobs frame = new ViewTransitJobs();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(viewCurrentJobs);
		
		JButton btnViewDeliveredJobs = new JButton("View Delivered Jobs");
		btnViewDeliveredJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDeliveredJobs frame = new ViewDeliveredJobs();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(btnViewDeliveredJobs);
		
		JButton btnViewDeletedJobs = new JButton("View Deleted Jobs");
		btnViewDeletedJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDeletedJobs frame = new ViewDeletedJobs();
				frame.setVisible(true);
				MainGUI.this.setVisible(false);
			}
		});
		contentPane.add(btnViewDeletedJobs);
	}

}
