package predictionSets;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import mulan.classifier.MultiLabelOutput;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;
import Imputation.ComputeMultiLabelDatasetMetaData;
import Imputation.MissingValueMultiLabelDatasetMetaDataset;
import java.util.ArrayList;

public class RakelPredict implements IPredictValues {

	private String _name;
	private Classifier _classifer;
	
	public RakelPredict(String name, Classifier classifier)
	{
		_name = name;
		_classifer = classifier;
	}
	
	public PredictionsWithExperiment Run(MultiLabelInstances data) throws Exception {
		  RAkEL model = new RAkEL(new LabelPowerset(_classifer));
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
