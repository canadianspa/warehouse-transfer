package gui;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

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



}
