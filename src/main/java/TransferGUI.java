import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class TransferGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_5 = new JPanel();
	ArrayList<Items> listOfItems = new ArrayList<Items>(); 

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
		
		JLabel lblNewLabel = new JLabel("Dispatching Warehouse");
		panel_2.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Canada House", "Verran", "Farm"}));
		
		JLabel lblNewLabel_1 = new JLabel("Recieving Warehouse");
		panel_2.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Canada House", "Verran", "Farm"}));
		
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
