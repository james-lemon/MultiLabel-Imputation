package Experiment;

import java.util.ArrayList;

import Imputation.LocationOfLabelValue;
import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import mulan.classifier.MultiLabelLearner;
import mulan.data.MultiLabelInstances;
import weka.core.Instance;

public interface IPredictValueSet {

	ArrayList<IPredictValues> GetPredictValues() throws InvalidDataException, ModelInitializationException, Exception;
	
}
