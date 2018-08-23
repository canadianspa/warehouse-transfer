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
import com.google.gson.reflect.TypeToken;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class TransferGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_5 = new JPanel();
	ArrayList<Items> listOfItems = new ArrayList<Items>(); 
	ArrayList<Warehouse> listofWarehouses = new ArrayList<Warehouse>();
	String APIKEY = "***REMOVED***";

	/*
	 Client client = ClientBuilder.newClient();
		Entity payload = Entity.json("");	
		Response response = client.target("https://api.veeqo.com/customers")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.post(payload);

		String body = response.readEntity(String.class);
	 */
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
		panel_5.removeAll();

		for(Items i: listOfItems)
		{
			panel_5.add(new JLabel(i.i.productTitle));
			JSpinner jsp = new JSpinner();
			jsp.setModel(new SpinnerNumberModel(i.quantity, null, null, new Integer(1)));
			panel_5.add(jsp);
			JButton jbu = new JButton("Remove");
			panel_5.add(jbu);

		}
	}
	private void findWarehouses()
	{
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://api.veeqo.com/warehouses?page_size=25")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.get();

		String body = response.readEntity(String.class);
		Gson g = new Gson();
		java.lang.reflect.Type collectionType = new TypeToken<Collection<Warehouse>>(){}.getType();
		Collection<Warehouse> enums = g.fromJson(body, collectionType);
		listofWarehouses = new ArrayList<Warehouse>(enums);
	
	}


	/**
	 * Create the frame.
	 */
	public TransferGUI() {
		setTitle("Transfer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 1, 0));

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		
		findWarehouses();
		JLabel lblNewLabel = new JLabel("Dispatching Warehouse");
		panel_2.add(lblNewLabel);

		JComboBox<Warehouse> comboBox = new JComboBox<Warehouse>();
		panel_2.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<Warehouse>(listofWarehouses.toArray(new Warehouse[listofWarehouses.size()])));

		JLabel lblNewLabel_1 = new JLabel("Recieving Warehouse");
		panel_2.add(lblNewLabel_1);

		JComboBox<Warehouse> comboBox_1 = new JComboBox<Warehouse>();
		panel_2.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel<Warehouse>(listofWarehouses.toArray(new Warehouse[listofWarehouses.size()])));

		JPanel panel = new JPanel();
		panel_2.add(panel);

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel_2.add(btnAddItem);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);

		JButton btnCreateJob = new JButton("Create Job");
		panel_2.add(btnCreateJob);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Product");
		panel_4.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Quantity");
		panel_4.add(lblNewLabel_3);

		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 3, 0, 0));

		listOfItems.add(new Items(new Item("spa"),3));
		listOfItems.add(new Items(new Item("chemicals"),5));
		showItems();

	}

}
