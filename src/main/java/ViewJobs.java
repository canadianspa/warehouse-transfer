import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class ViewJobs extends JFrame {

	private JPanel contentPane;
	JPanel panel;
	/**
	 * Launch the application.
	 */
	
	public ArrayList<TransferJob> findJobs()
	{
		ArrayList<TransferJob> output = new ArrayList<TransferJob>();
		File Folder = new File(Settings.path);
		File files[];
		files = Folder.listFiles();
		System.out.println(Settings.path);
		System.out.println(files.length);
		for(int i = 0;i<files.length ; i++)
		{
			TransferJob tj;
			
			try {
				FileInputStream fileIn = new FileInputStream(files[i]);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				tj = (TransferJob) in.readObject();	
				System.out.println(tj);
				output.add(tj);
				in.close();
				fileIn.close();
			} catch (IOException io) {
			} catch (ClassNotFoundException c) {
			}



		}

		return output;
	}
	
	public void writeJob(int gridyCounter, TransferJob tj)
	{

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		c.weightx = 0.17;

		
		final TransferJob ftj = tj;
		c.gridy = gridyCounter;
		c.gridx = 0;
		JLabel dispLabel = new JLabel(tj.dispWarehouse.name);
		panel.add(dispLabel,c);
		
		c.gridx = 1;
		JLabel recvLabel = new JLabel(tj.recvWarehouse.name);
		panel.add(recvLabel,c);
		
		c.gridx = 2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		JLabel timeSent = new JLabel(formatter.format(tj.timeSent));
		panel.add(timeSent,c);
		
		c.gridx = 3;
		JButton view = new JButton("View Items");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringItems = "";
				for(Items i: ftj.listOfItems)
				{
					stringItems += i.toString() + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null,stringItems , "Items", JOptionPane.INFORMATION_MESSAGE); 
			}
		});
		panel.add(view,c);
		
	}
	
	
	public void showJobs()
	{
		contentPane.remove(panel);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		contentPane.add(panel);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		c.weightx = 0.17;

		c.gridy = 0;

		//headers
		c.gridx = 0;
		JLabel l0 = new JLabel("Dispatching Warehouse");
		panel.add(l0,c);

		c.gridx = 1;
		JLabel l1 = new JLabel("Recieving Warehouse");
		panel.add(l1,c);

		c.gridx = 2;
		JLabel l2 = new JLabel("Date Sent");
		panel.add(l2,c);

		//find jobs
		ArrayList<TransferJob> listOfJobs = findJobs();
		
		int gridyCounter = 1;
		for(TransferJob tj : listOfJobs)
		{
			writeJob(gridyCounter,tj);
			gridyCounter ++;
		}
	}
	/**
	 * Create the frame.
	 */
	public ViewJobs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		 panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridBagLayout());
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI frame = new MainGUI();
				frame.setVisible(true);
				
				ViewJobs.this.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);

		showJobs();
		
		
		




	}

}
