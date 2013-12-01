package predictionSets;

import java.util.ArrayList;

import mulan.classifier.MultiLabelOutput;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.AdaBoostMH;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;

public class AdaBoostMHPredict implements IPredictValues {

	private String _name;
	
	public AdaBoostMHPredict(String name)
	{
		_name = name;
	}
	
	public PredictionsWithExperiment Run(MultiLabelInstances data) throws Exception {
		  AdaBoostMH model = new AdaBoostMH();
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
