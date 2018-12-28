package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.Items;
import entities.TransferJob;
import requests.Settings;

public abstract class ViewJobs extends JFrame {

	private JPanel contentPane;
	JPanel panel;
	/**
	 * Launch the application.
	 */
	public int start =0;
	public int end = 10;
	
	public ArrayList<TransferJob> findJobs()
	{
		Client client = ClientBuilder.newClient();
		Response response = client.target(Settings.serverPath + "TransferJob")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get();

		String body = response.readEntity(String.class);
		Gson g = new Gson();
		System.out.println(body);
		return g.fromJson(body, new TypeToken<ArrayList<TransferJob>>(){}.getType());
		
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
		JLabel id = new JLabel(String.valueOf(tj.id));
		panel.add(id,c);
		
		c.gridy = gridyCounter;
		c.gridx = 1;
		JLabel dispLabel = new JLabel(tj.dispWarehouse.name);
		panel.add(dispLabel,c);
		
		c.gridx = 2;
		JLabel recvLabel = new JLabel(tj.recvWarehouse.name);
		panel.add(recvLabel,c);
		
		c.gridx = 3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		JLabel timeSent = new JLabel(tj.timeSent.toString());
		panel.add(timeSent,c);
		
	}
	
	public JButton createViewItemsJButton(final ArrayList<Items> listOfItems)
	{
		JButton view = new JButton("View Items");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringItems = "";
				for(Items i: listOfItems)
				{
					stringItems += i.toString() + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null,stringItems , "Items", JOptionPane.INFORMATION_MESSAGE); 
			}
		});
		return view;
	}
	
	public void pageDown()
	{
		start += 10;
		end += 10;
		showJobs();
	}
	public void pageUp()
	{
		start -= 10;
		end -= 10;
		showJobs();
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
		JLabel id = new JLabel("Job ID");
		panel.add(id,c);
		
		c.gridx = 1;
		JLabel l0 = new JLabel("Dispatching Warehouse");
		panel.add(l0,c);

		c.gridx = 2;
		JLabel l1 = new JLabel("Recieving Warehouse");
		panel.add(l1,c);

		c.gridx = 3;
		JLabel l2 = new JLabel("Date Sent");
		panel.add(l2,c);

		//find jobs
		ArrayList<TransferJob> listOfJobs = findJobs();
		Collections.sort(listOfJobs);
		int gridyCounter = 1;
		for(int i = start; i < end; i ++ )
		{
			if(i >= listOfJobs.size())
			{
				break;
			}
			System.out.println(listOfJobs.get(i));
			writeJob(gridyCounter,listOfJobs.get(i));
			gridyCounter ++;
		}
	}
	/**
	 * Create the frame.
	 */
	public ViewJobs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1700, 1000);
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
		JButton pageDownButton = new JButton("Page Down");
		pageDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageDown();
				revalidate();
			}
		});
		panel_1.add(pageDownButton);
		JButton pageUpButton = new JButton("Page Up");
		pageUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageUp();
				revalidate();
			}
		});
		panel_1.add(pageUpButton);

		showJobs();
		
		
		




	}

}
