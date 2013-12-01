package predictionSets;

import java.util.ArrayList;

import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import mulan.classifier.MultiLabelOutput;
import mulan.classifier.lazy.BRkNN;
import mulan.data.MultiLabelInstances;
import weka.core.Instance;
import weka.core.Instances;
import Experiment.IPredictValueSet;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;

public class BRkNNPredictSet implements IPredictValueSet {
	
	
	public ArrayList<IPredictValues> GetPredictValues() throws InvalidDataException, ModelInitializationException, Exception{
			ArrayList<IPredictValues> output = new ArrayList<IPredictValues>();
			output.add(new BRkNNPredict("BRkNN"));
			return output;
			}

}
