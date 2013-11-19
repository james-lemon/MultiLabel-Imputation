package example;

import Imputation.ComputeMultiLabelDatasetMetaData;
import Imputation.MissingValueGenerator;
import Imputation.MissingValueMultiLabelDatasetMetaDataset;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluation;
import mulan.evaluation.Evaluator;
import weka.classifiers.trees.J48;
import weka.core.Instances;


public class MulanExample2GeneratingMissingValues {

    public static void main(String[] args) throws Exception {
        MultiLabelInstances dataset = new MultiLabelInstances("data/yeast/yeast.arff", "data/yeast/yeast.xml");

        MultiLabelInstances missingDataset = MissingValueGenerator.Create(dataset, .8);
        int[] Labels = dataset.getLabelIndices();
		int numLabels = dataset.getNumLabels();
		int numInstances = dataset.getNumInstances();
		Instances instances = missingDataset.getDataSet();
		for(int i = 0; i < numInstances; i++)
		{
			for(int j = 0; j < numLabels; j++)
			{
				System.out.print(instances.get(i).isMissing(j) + "\t");
			}
			System.out.print("\n");
		}
		ComputeMultiLabelDatasetMetaData missingAfter = new ComputeMultiLabelDatasetMetaData(missingDataset);
		MissingValueMultiLabelDatasetMetaDataset DataaboutTruth = missingAfter.calculate();
		System.out.println(DataaboutTruth.NumberOfMissingLabels);
    }
}

