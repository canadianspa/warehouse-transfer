package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.Items;
import entities.TransferJob;
import entities.Warehouse;
import requests.CreateJobRequest;
import requests.ItemsRequest;
import requests.Settings;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class TransferGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_5 = new JPanel();
	ArrayList<Items> listOfItems = new ArrayList<Items>(); 
	ArrayList<Warehouse> listofWarehouses = new ArrayList<Warehouse>();
	AddItemGUI addFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferGUI frame = new TransferGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showItems()
	{
		contentPane.remove(panel_5);
		panel_5 = new JPanel();
		panel_5.setLayout(new GridBagLayout());
		contentPane.add(panel_5);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		int gridycounter = 0;
		for(Items i: listOfItems)
		{
			final Items finalItems = i; 
			c.gridy = gridycounter;

			c.weightx = 0.7;
			c.gridx = 0;
			if(i.i.productTitle.length() > 15)
			{
				panel_5.add(new JLabel("<html>" + i.i.productTitle.substring(0, i.i.productTitle.length()/2) + "<br>" + i.i.productTitle.substring(i.i.productTitle.length()/2)),c);
			}
			else
			{
				panel_5.add(new JLabel(i.i.productTitle),c);
			}

			final JSpinner jsp = new JSpinner();
			jsp.setFont(new Font("Arial", Font.BOLD, 20));
			jsp.setModel(new SpinnerNumberModel(i.quantity, null, null, new Integer(1)));
			jsp.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {
					finalItems.quantity = (Integer) jsp.getValue();

				}

			});
			c.weightx = 0.15;
			c.gridx = 1;
			panel_5.add(jsp,c);
			JButton jbu = new JButton("Remove");
			jbu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listOfItems.remove(finalItems);
					showItems();
					setVisible(true);
				}
			});
			c.weightx = 0.15;
			c.gridx = 2;
			panel_5.add(jbu,c);

			gridycounter ++;
		}

	}
	private void findWarehouses()
	{
		Client client = ClientBuilder.newClient();
		Response response = client.target(Settings.serverPath + "Warehouse")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get();

		String body = response.readEntity(String.class);
		Gson g = new Gson();
		listofWarehouses = g.fromJson(body, new TypeToken<ArrayList<Warehouse>>(){}.getType());

	}


	/**
	 * Create the frame.
	 */
	public TransferGUI() {
		setTitle("Transfer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		findWarehouses();


		addFrame = new AddItemGUI(listOfItems,TransferGUI.this);			
		addFrame.setVisible(true);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblNewLabel = new JLabel("Dispatching Warehouse");

		panel_2.add(lblNewLabel);

		final JComboBox<Warehouse> dispCombo = new JComboBox<Warehouse>();

		panel_2.add(dispCombo);
		dispCombo.setModel(new DefaultComboBoxModel<Warehouse>(listofWarehouses.toArray(new Warehouse[listofWarehouses.size()])));

		JLabel lblNewLabel_1 = new JLabel("Recieving Warehouse");

		panel_2.add(lblNewLabel_1);

		final JComboBox<Warehouse> recvCombo = new JComboBox<Warehouse>();

		panel_2.add(recvCombo);
		recvCombo.setModel(new DefaultComboBoxModel<Warehouse>(listofWarehouses.toArray(new Warehouse[listofWarehouses.size()])));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		contentPane.add(panel_3);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnCreateJob = new JButton("Create Job");
		panel.add(btnCreateJob);

		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI frame = new MainGUI();
				frame.setVisible(true);
				
				TransferGUI.this.setVisible(false);
				addFrame.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Warehouse recvWarehouse = (Warehouse) recvCombo.getSelectedItem();
				Warehouse dispWarehouse = (Warehouse) dispCombo.getSelectedItem();
				Long dispWarehouseId = dispWarehouse.id;
				Long recvWarehouseId = recvWarehouse.id;
				ArrayList<ItemsRequest> listOfItemsRequest = new ArrayList<ItemsRequest>();
				for(Items i: listOfItems)
				{
					listOfItemsRequest.add(new ItemsRequest(i.i.id, i.quantity));
				}
				Gson g = new Gson();

				CreateJobRequest cjr = new CreateJobRequest(dispWarehouseId,recvWarehouseId,listOfItemsRequest);				
				Client client = ClientBuilder.newClient();
				Response response = client.target(Settings.serverPath + "TransferJob")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.post(Entity.json(g.toJson(cjr)));

				String body = response.readEntity(String.class);	
				System.out.println(body);
				

				MainGUI frame = new MainGUI();
				frame.setVisible(true);
				
				TransferGUI.this.setVisible(false);
				addFrame.setVisible(false);
				
				





			}
		});
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		panel_5.setLayout(gbl_panel_5);




	}
}
