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
		c.gridx = 4;
		JButton delete = new JButton("Delete Transfer");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftj.deleteJob();
				showJobs();
				ViewTransitJobs.this.setVisible(true);
			}
		});
		panel.add(delete,c);

		c.gridx = 5;
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
