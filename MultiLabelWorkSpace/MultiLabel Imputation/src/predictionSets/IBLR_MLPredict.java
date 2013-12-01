package predictionSets;

import java.util.ArrayList;

import mulan.classifier.MultiLabelOutput;
import mulan.classifier.lazy.BRkNN;
import mulan.classifier.lazy.IBLR_ML;
import mulan.data.MultiLabelInstances;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;

public class IBLR_MLPredict implements IPredictValues {

	private String _name;
	private Classifier _classifer;
	
	public IBLR_MLPredict(String name)
	{
		_name = name;
	}
	
	public PredictionsWithExperiment Run(MultiLabelInstances data) throws Exception {
		  IBLR_ML model = new IBLR_ML();
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