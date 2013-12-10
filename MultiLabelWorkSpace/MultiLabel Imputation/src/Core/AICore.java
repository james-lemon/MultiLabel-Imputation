package Core;

import java.io.PrintWriter;
import java.util.ArrayList;

import predictionSets.AdaBoostMHPredictSet;
import predictionSets.BPMLLPredictSet;
import predictionSets.BRkNNPredictSet;
import predictionSets.BinaryRelevancePredictionSet;
import predictionSets.CalibratedLabelRankingPredictSet;
import predictionSets.IBLR_MLPredictSet;
import predictionSets.IncludeLabelsClassifierPredictSet;
import predictionSets.LabelPowerSetPredictSet;
import predictionSets.MLkNNPredictSet;
import predictionSets.MMPLearnerPredictSet;
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
		for(double rate = 0.2; rate < .9; rate += 0.2)
		{
			loadData();
			System.out.println(rate);
			createMissingValues(rate);
			System.out.println("Missing Values Created");
			createPredictSet();
			System.out.println("PredictSetLoaded");
			int a = 0;
			for(int i = 0; i < GRANDTRUTHdatasets.size(); i++)
			{
				ComputeMultiLabelDatasetMetaData cm = new ComputeMultiLabelDatasetMetaData(datasets.get(i));
				System.out.println(cm.calculate().NumberOfMissingLabels);
				
				System.out.println("Dataset " + i);
				for(IPredictValues predict : predictSets)
				{
					a++;
					System.out.println(a);
					Experiment experiment = new Experiment(datasets.get(i).clone(), GRANDTRUTHdatasets.get(i), predict);
					EvaluationWithExperiment tmpeval = experiment.Run();
					System.out.println(tmpeval);
					tmpeval.experiment = "Dataset " + i + "\n" + "Missing: " + rate + "\n" + tmpeval.experiment;
					evals.add(tmpeval);
				}
				
			}
		}
		PrintWriter writer = new PrintWriter("evals.txt", "UTF-8");

		for(EvaluationWithExperiment z : evals)
		{
			writer.println(z.toString());

		}
		writer.close();
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
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/bibtex/bibtex.arff", "data/bibtex/bibtex.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/bookmarks/bookmarks.arff", "data/bookmarks/bookmarks.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/CAL500/CAL500.arff", "data/CAL500/CAL500.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/corel5k/Corel5k.arff", "data/corel5k/Corel5k.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/enron/enron.arff", "data/enron/enron.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/genbase/genbase.arff", "data/genbase/genbase.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/mediamill/mediamill.arff", "data/mediamill/mediamill.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/tmc2007/tmc2007.arff", "data/tmc2007/tmc2007.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/emotions/emotions.arff", "data/emotions/emotions.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/medical/medical.arff", "data/medical/medical.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/scene/scene.arff", "data/scene/scene.xml"));
		//GRANDTRUTHdatasets.add(new MultiLabelInstances("data/yeast/yeast-train.arff", "data/yeast/yeast.xml"));
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
		//predictSets = new RaKelPredictionSet().GetPredictValues();
		predictSets = new BinaryRelevancePredictionSet().GetPredictValues();

		ArrayList<IPredictValues> add = new BRkNNPredictSet().GetPredictValues();
		for(IPredictValues i : add)
		{
			predictSets.add(i);
		}




	}
}
