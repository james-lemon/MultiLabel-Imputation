package Experiment;

import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluation;
import mulan.evaluation.Evaluator;
import Imputation.ComputeMultiLabelDatasetMetaData;
import Imputation.IComputeMultiLabelDatasetMetaData;
import Imputation.MissingValueMultiLabelDatasetMetaDataset;

public class Experiment implements IExperiment {
	
	private IComputeMultiLabelDatasetMetaData _metaData;
	private MultiLabelInstances GRANDTRUTH;
	private IPredictValues _predict;
	private MultiLabelInstances _dataset;
	
	public Experiment(MultiLabelInstances dataset, MultiLabelInstances GrandTruth, IPredictValues predict)
	{
		_dataset = dataset;
		GRANDTRUTH = GrandTruth;
		_predict = predict;
	}
	
	public EvaluationWithExperiment Run() throws Exception
	{
		_metaData = new ComputeMultiLabelDatasetMetaData(_dataset);
		MissingValueMultiLabelDatasetMetaDataset info = _metaData.calculate();
		
		PredictionsWithExperiment predictions = _predict.Run(_dataset);
		EvaluationWithExperiment output = new EvaluationWithExperiment();
		output.evaluation = new ImputationEvaluation(GRANDTRUTH, predictions.data, info.NumberOfMissingLabels);
		output.experiment = predictions.experiementName;
		
		
		return output;
		
	}

}
