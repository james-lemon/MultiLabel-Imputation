package Core;

import java.util.ArrayList;

import predictionSets.RaKelPredictionSet;
import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import Experiment.EvaluationWithExperiment;
import Experiment.Experiment;
import Experiment.IPredictValues;
import Experiment.ImputationEvaluation;
import Experiment.PredictionsWithExperiment;
import Imputation.ComputeMultiLabelDatasetMetaData;
import Imputation.MissingValueGenerator;
import Imputation.MissingValueMultiLabelDatasetMetaDataset;

public class AICore {

	private ArrayList<MultiLabelInstances> GRANDTRUTHdatasets = new ArrayList<MultiLabelInstances>();
	private ArrayList<MultiLabelInstances> datasets = new ArrayList<MultiLabelInstances>();
	private ArrayList<IPredictValues> predictSets = new ArrayList<IPredictValues>();
	private ArrayList<EvaluationWithExperiment> evals = new ArrayList<EvaluationWithExperiment>();
	
	public void run() throws Exception
	{
		ArrayList<EvaluationWithExperiment> resultsList = new ArrayList<EvaluationWithExperiment>();
		for(double rate = 0.1; rate < 1; rate += 0.1)
		{
			loadData();
			System.out.println("DataLoaded");
			createMissingValues(rate);
			System.out.println("Missing Values Created");
			createPredictSet();
			System.out.println("PredictSetLoaded");
			for(int i = 0; i < GRANDTRUTHdatasets.size(); i++)
			{
				for(IPredictValues predict : predictSets)
				{
					System.out.println("Inside predict");
					Experiment experiment = new Experiment(datasets.get(i), GRANDTRUTHdatasets.get(i), predict);
					EvaluationWithExperiment tmpeval = experiment.Run();
					tmpeval.experiment = "Dataset " + i + "\n" + "Missing: " + rate + "\n" + tmpeval.experiment;
					evals.add(tmpeval);
				}
			
			}
		}
		for(EvaluationWithExperiment z : evals)
		{
			System.out.println(z.toString());

		}
	}
	
	private EvaluationWithExperiment experiment(MultiLabelInstances dataset, MultiLabelInstances GRANDTRUTH, IPredictValues predict) throws Exception
	{
		return SimpleExperiment(dataset, GRANDTRUTH, predict);
	}
	
	private EvaluationWithExperiment SimpleExperiment(MultiLabelInstances dataset, MultiLabelInstances GRANDTRUTH, IPredictValues predict) throws Exception
	{
		return new Experiment(dataset, GRANDTRUTH, predict).Run();
	}
	public static void main(String args[]) throws Exception
	{
		AICore core = new AICore();
		try {
			core.run();
		} catch (InvalidDataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadData() throws InvalidDataFormatException
	{
		GRANDTRUTHdatasets = new ArrayList<MultiLabelInstances>();
		GRANDTRUTHdatasets.add(new MultiLabelInstances("data/birds/birds-train.arff", "data/birds/birds.xml"));
		GRANDTRUTHdatasets.add(new MultiLabelInstances("data/emotions/emotions.arff", "data/emotions/emotions.xml"));
		GRANDTRUTHdatasets.add(new MultiLabelInstances("data/medical/medical.arff", "data/medical/medical.xml"));
		GRANDTRUTHdatasets.add(new MultiLabelInstances("data/scene/scene.arff", "data/scene/scene.xml"));
		GRANDTRUTHdatasets.add(new MultiLabelInstances("data/yeast/yeast-train.arff", "data/yeast/yeast.xml"));
	}
	private void createMissingValues(double percentmissing) throws InvalidDataFormatException
	{
		datasets = new ArrayList<MultiLabelInstances>();
		for(MultiLabelInstances i : GRANDTRUTHdatasets)
		{
			MultiLabelInstances missing = MissingValueGenerator.Create(i, percentmissing);
			datasets.add(missing);
		}
	}
	private void createPredictSet() throws InvalidDataException, ModelInitializationException, Exception
	{
		predictSets = new RaKelPredictionSet().GetPredictValues();
	}
}
