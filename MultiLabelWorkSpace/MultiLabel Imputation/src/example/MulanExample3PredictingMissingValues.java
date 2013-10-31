package example;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluation;
import mulan.evaluation.Evaluator;
import Imputation.MissingValueGenerator;

public class MulanExample3PredictingMissingValues {

	public static void main(String args[]) throws Exception
	{
		MultiLabelInstances dataset = new MultiLabelInstances("data/yeast/yeast.arff", "data/yeast/yeast.xml");

        MultiLabelInstances missingDataset = MissingValueGenerator.Create(dataset, .9);
        RAkEL model = new RAkEL(new LabelPowerset(new RandomForest()));

        System.out.println("Building dataset");
        model.build(missingDataset);

        Evaluator evaluator = new Evaluator();
        Evaluation results = evaluator.evaluate(model, missingDataset);
        
        System.out.println(results.toString());
	}
}
