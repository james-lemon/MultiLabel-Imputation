package predictionSets;

import java.util.ArrayList;

import weka.classifiers.*;
import weka.classifiers.bayes.*;
import weka.classifiers.bayes.net.*;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SGDText;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.SimpleLinearRegression;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.lazy.*;
import weka.classifiers.meta.*;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.misc.SerializedClassifier;
import weka.classifiers.pmml.consumer.GeneralRegression;
import weka.classifiers.pmml.consumer.NeuralNetwork;
import weka.classifiers.pmml.consumer.PMMLClassifier;
import weka.classifiers.pmml.consumer.Regression;
import weka.classifiers.pmml.consumer.RuleSetModel;
import weka.classifiers.pmml.consumer.SupportVectorMachineModel;
import weka.classifiers.pmml.consumer.TreeModel;
import weka.classifiers.pmml.consumer.GeneralRegression.*;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.M5Rules;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.*;
import weka.classifiers.trees.lmt.LMTNode;
import weka.classifiers.trees.lmt.LogisticBase;
import weka.classifiers.trees.m5.M5Base;
import weka.classifiers.trees.m5.PreConstructedLinearModel;
import weka.classifiers.trees.m5.RuleNode;
import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import mulan.data.MultiLabelInstances;
import Experiment.IPredictValueSet;
import Experiment.IPredictValues;
import Experiment.PredictionsWithExperiment;

public class RaKelPredictionSet implements IPredictValueSet {

	private ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
	
	public ArrayList<IPredictValues> GetPredictValues() throws InvalidDataException, ModelInitializationException, Exception{
			ArrayList<IPredictValues> output = new ArrayList<IPredictValues>();
			loadClassifiers();
			for(Classifier classifier : classifiers)
			{
				output.add(new RakelPredict("Rakel with " +classifier.getClass().toString(), classifier));
			}
			return output;
			}

	private void loadClassifiers()
	{
		// Run well
		classifiers.add(new NaiveBayes());


		//Errors Below
		//IOFileNotFoundclassifiers.add(new SerializedClassifier());
		//BaseClassifierMustBeupdateableclassifiers.add(new MultiClassClassifierUpdateable());
		//noSupportForMissingValuesExceptionclassifiers.add(new LogisticBase());
		//NoSupportForMissingValuesclassifiers.add(new NaiveBayesMultinomialUpdateable());
		//NoSupportForMissingValuesclassifiers.add(new NaiveBayesMultinomial());
		//UnsupportedAttributesTypeclassifiers.add(new M5P());
		//UnsupportedAttributesTypeclassifiers.add(new M5Rules());
		//UnsupportedAttributeTypeExceptionclassifiers.add(new LinearRegression());
		//UnsupportedAttributeATypeclassifiers.add(new GaussianProcesses());
		//UnSupportedAttributeTypeclassifiers.add(new RegressionByDiscretization());
		//UnSupportedAttributeTypeclassifiers.add(new SGD());
		//UnSupportedAttributeTypeclassifiers.add(new SGDText());
		//UnSupportedAttributeTypeclassifiers.add(new SimpleLinearRegression());
		//UnsupportedAttributeTypeclassifiers.add(new SMOreg());
		//UnSupportedAttributeTypeclassifiers.add(new VotedPerceptron());
		//fills in missing values in datasetclassifiers.add(new EditableBayesNet());
		//classifiers.add(new AdditiveRegression());
		//Sending Warningclassifiers.add(new BayesNet());
		//Sends Warningclassifiers.add(new BayesNetGenerator());
		//Sends Warningclassifiers.add(new BIFReader());
		//classifiers.add(new GeneralRegression());
		//classifiers.add(new HoeffdingTree());
		//classifiers.add(new IteratedSingleClassifierEnhancer());
		//On-Demand Cost File Doesn't existclassifiers.add(new CostSensitiveClassifier());
		//classifiers.add(new LMTNode());
		//classifiers.add(new M5Base());
		//classifiers.add(new MultipleClassifiersCombiner());
		//classifiers.add(new NeuralNetwork());
		//classifiers.add(new ParallelIteratedSingleClassifierEnhancer());
		//classifiers.add(new ParallelMultipleClassifiersCombiner());
		//classifiers.add(new PMMLClassifier());
		//classifiers.add(new PreConstructedLinearModel());
		//classifiers.add(new RandomizableClassifier());
		//classifiers.add(new RandomizableIteratedSingleClassifierEnhancer());
		//classifiers.add(new RandomizableMultipleClassifiersCombiner());
		//classifiers.add(new RandomizableParallelIteratedSingleClassifierEnhancer());
		//classifiers.add(new RandomizableParallelMultipleClassifiersCombiner());
		//classifiers.add(new RandomizableSingleClassifierEnhancer());
		//classifiers.add(new Regression());
		//classifiers.add(new RuleNode());
		//classifiers.add(new RuleSetModel());
		//classifiers.add(new SupportVectorMachineModel());
		//classifiers.add(new TreeModel());
		//classifiers.add(new SingleClassifierEnhancer());
		
		
	}


}

