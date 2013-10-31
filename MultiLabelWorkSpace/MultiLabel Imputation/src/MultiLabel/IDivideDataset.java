package MultiLabel;

import java.util.ArrayList;

import mulan.data.MultiLabelInstances;

public interface IDivideDataset {

	/*
	 * Implementation will most likely have a multilabelinstances value in the constructor,
	 * to be saved as a private member.  
	 */
	
	MultiLabelInstances SubsetWithLabelsNotMissing(ArrayList<String> Labels);
	MultiLabelInstances SubsetWithLabelsMissing(ArrayList<String> Labels);
	MultiLabelInstances SubsetWithLabels(ArrayList<String> Labels); // Return a subset with only the labels listed, regardless if their values are missing or not
	
	
}
