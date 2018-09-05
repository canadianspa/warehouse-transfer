import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AddItemGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductTitle;
	String APIKEY = "***REMOVED***";
	private JPanel panel_3;
	ArrayList<Items> itemsToAdd;


	private ArrayList<Item> queryProducts(String query)
	{
		ArrayList<Item> result = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		Response response;
		try {

			response = client.target("https://api.veeqo.com/products?query=" + URLEncoder.encode(query, "UTF-8"))
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("x-api-key", APIKEY)
					.get();
			String body = response.readEntity(String.class);
			JsonArray jsonArray = new JsonArray();
			JsonParser jparse = new JsonParser();
			jsonArray = jparse.parse(body).getAsJsonArray();
			for(JsonElement j : jsonArray)
			{
				JsonArray sellableArray = j.getAsJsonObject().get("sellables").getAsJsonArray();
				for(JsonElement e : sellableArray)
				{
					Item i = new Item(e.getAsJsonObject().get("full_title").getAsString(),e.getAsJsonObject().get("sku_code").getAsString(),e.getAsJsonObject().get("id").getAsString());
					result.add(i);
				}
			}


			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ArrayList<Item>();
		}


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
				c.anchor = GridBagConstraints.NORTH;
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
