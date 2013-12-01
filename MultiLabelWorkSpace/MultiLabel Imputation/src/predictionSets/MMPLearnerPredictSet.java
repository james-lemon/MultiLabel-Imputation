package predictionSets;

import java.util.ArrayList;

import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import Experiment.IPredictValueSet;
import Experiment.IPredictValues;

public class MMPLearnerPredictSet implements IPredictValueSet {
	
	
	public ArrayList<IPredictValues> GetPredictValues() throws InvalidDataException, ModelInitializationException, Exception{
			ArrayList<IPredictValues> output = new ArrayList<IPredictValues>();
			output.add(new MMPLearnerPredict("MMPLearner"));
			return output;
			}

}
