package Experiment;

import mulan.data.MultiLabelInstances;

public interface IPredictValues {

	PredictionsWithExperiment Run(MultiLabelInstances data) throws Exception;
	
	
}
