package gui;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import entities.TransferJob;

public class ViewDeliveredJobs extends ViewJobs{

	@Override
	public ArrayList<TransferJob> findJobs() {
		ArrayList<TransferJob> newOutput = new ArrayList<TransferJob>(); 
		for(TransferJob tj: super.findJobs())
		{
			if(tj.status.equals("Delivered"))
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
		c.gridy = gridyCounter;	
		c.gridx = 4;
		JLabel timeCompleted = new JLabel(tj.timeCompleted.toString());
		panel.add(timeCompleted,c);

		
	}

}
