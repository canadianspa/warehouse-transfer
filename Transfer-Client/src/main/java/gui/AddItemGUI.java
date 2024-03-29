package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.Item;
import entities.Items;
import requests.Settings;

public class AddItemGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductTitle;
	private JPanel panel_3;
	ArrayList<Items> itemsToAdd;


	private ArrayList<Item> queryProducts(String query)
	{
		Gson g = new Gson();
		ArrayList<Item> result = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		Response response;
		response = client.target(Settings.serverPath + "Item")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(query)));
		String body = response.readEntity(String.class);
	
		return g.fromJson(body, new TypeToken<ArrayList<Item>>(){}.getType());


	}

	/**
	 * Create the frame.
	 */
	public AddItemGUI(ArrayList<Items> listOfItems, final TransferGUI parent) {
		itemsToAdd = listOfItems;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(( int) (parent.getBounds().x + parent.getBounds().getWidth()), 100, 1000, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel = new JLabel("Product Title");
		panel.add(lblNewLabel);

		txtProductTitle = new JTextField();
		panel.add(txtProductTitle);
		txtProductTitle.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> results = queryProducts(txtProductTitle.getText());
				contentPane.remove(panel_3);
				panel_3 = new JPanel();
				GridBagLayout gbl_panel_3 = new GridBagLayout();		
				panel_3.setLayout(gbl_panel_3);
				contentPane.add(panel_3);
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.NORTHWEST;
				c.weighty = 1;
				int gridycounter = 0;
				for(Item i: results)
				{
					final Item finalItem = i;
					c.gridy = gridycounter;

					c.weightx = 0.8;
					c.gridx = 0;
					panel_3.add(new JLabel(i.productTitle),c);
					c.weightx = 0.1;
					c.gridx = 1;
					panel_3.add(new JLabel(i.sku),c);
					JButton add = new JButton("add");
					add.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							System.out.println(finalItem);
							itemsToAdd.add(new Items(finalItem,1));
							parent.setVisible(true);
							parent.showItems();
							parent.setVisible(true);
						}

					});
					c.weightx = 0.1;
					c.gridx = 2;
					panel_3.add(add,c);

					gridycounter ++;

				}

				setVisible(true);


			}
		});
		panel.add(btnSearch);

		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();		
		panel_3.setLayout(gbl_panel_3);
	}

}
