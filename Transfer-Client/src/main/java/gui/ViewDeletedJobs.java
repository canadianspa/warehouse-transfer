package gui;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import entities.TransferJob;

public class ViewDeletedJobs extends ViewJobs{

	@Override
	public ArrayList<TransferJob> findJobs() {
		ArrayList<TransferJob> newOutput = new ArrayList<TransferJob>(); 
		for(TransferJob tj: super.findJobs())
		{
			if(tj.status.equals("Deleted"))
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
		c.weightx = 0.2;
		
		c.gridy = 0;
		c.gridx = 3;
		JLabel deleted = new JLabel("Time Deleted");
		panel.add(deleted,c);	
		
		c.gridy = gridyCounter;	
		c.gridx = 3;
		JLabel timeCompleted = new JLabel(tj.timeCompleted.toString());
		panel.add(timeCompleted,c);
		
		c.gridx =4 ;
		panel.add(super.createViewItemsJButton(ftj.listOfItems),c);

		
	}



}
