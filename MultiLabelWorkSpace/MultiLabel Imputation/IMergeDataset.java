package MultiLabel;

import mulan.data.MultiLabelInstances;
import weka.core.Instance;

public interface IMergeDataset {

	/*
	 * Constructor will most likely contain a MultiLabelInstances to save as a private member
	 */
	
	void addPrediction(String label, Instance instance);
	MultiLabelInstances GetDataset();
	
	
}
