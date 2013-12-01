package predictionSets;

import java.util.ArrayList;

import mulan.classifier.MultiLabelOutput;
import mulan.classifier.lazy.IBLR_ML;
import mulan.classifier.lazy.MLkNN;
import mulan.data.MultiLabelInstances;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;

public class MLkNNPredict implements IPredictValues {

	private String _name;
	private Classifier _classifer;
	
	public MLkNNPredict(String name)
	{
		_name = name;
	}
	
	public PredictionsWithExperiment Run(MultiLabelInstances data) throws Exception {
		MLkNN model = new MLkNN();
		  model.build(data);
		  Instances predicitions = new Instances(data.getDataSet(), data.getDataSet().size());
		  ArrayList<MultiLabelOutput> predictions = new ArrayList<MultiLabelOutput>();
		  for(int i = 0 ; i < data.getDataSet().size(); i++)
		  {
			  Instance tmp = (Instance) data.getDataSet().get(i).copy();
			  predictions.add(model.makePrediction(tmp));
		  }
		  MultiLabelInstances a = new MultiLabelInstances(predicitions, data.getLabelsMetaData());
		  PredictionsWithExperiment output = new PredictionsWithExperiment();
		  output.data = predictions;
		  output.experiementName = _name;
		  return output;
	}

}