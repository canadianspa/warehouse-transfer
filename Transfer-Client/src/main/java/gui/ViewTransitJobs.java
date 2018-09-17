package gui;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import entities.TransferJob;

public class ViewTransitJobs extends ViewJobs{

	@Override
	public ArrayList<TransferJob> findJobs() {
		ArrayList<TransferJob> newOutput = new ArrayList<TransferJob>(); 
		for(TransferJob tj: super.findJobs())
		{
			if(tj.status.equals("Transit"))
			{
				newOutput.add(tj);
			}
		}
		return newOutput;

	}

	@Override
	public void writeJob(int gridyCounter, TransferJob tj) {
		super.writeJob(gridyCounter, tj);



		final TransferJob ftj = tj;
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		c.weightx = 0.17;
		c.gridy = gridyCounter;	
		
		c.gridx = 3;
		panel.add(super.createViewItemsJButton(ftj.listOfItems),c);
		
		c.gridx = 4;
		JButton print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PDFCreator.print(ftj);
				
				
			}
		});
		panel.add(print,c);
		
		
		c.gridx = 5;
		JButton delete = new JButton("Delete Transfer");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftj.deleteJob();
				showJobs();
				ViewTransitJobs.this.setVisible(true);
			}
		});
		panel.add(delete,c);

		c.gridx = 6;
		JButton confirm = new JButton("Confirm Delivery");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftj.confirmJob();
				showJobs();
				ViewTransitJobs.this.setVisible(true);
			}
		});
		panel.add(confirm,c);
		
		
	}







}
