package predictionSets;

import java.util.ArrayList;

import mulan.classifier.InvalidDataException;
import mulan.classifier.ModelInitializationException;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.lazy.LWL;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.CVParameterSelection;
import weka.classifiers.meta.ClassificationViaRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.LogitBoost;
import weka.classifiers.meta.MultiClassClassifier;
import weka.classifiers.meta.MultiScheme;
import weka.classifiers.meta.RandomCommittee;
import weka.classifiers.meta.RandomSubSpace;
import weka.classifiers.meta.Stacking;
import weka.classifiers.meta.Vote;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import Experiment.IPredictValueSet;
import Experiment.IPredictValues;

public class LabelPowerSetPredictSet implements IPredictValueSet {

	private ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
	
	public ArrayList<IPredictValues> GetPredictValues() throws InvalidDataException, ModelInitializationException, Exception{
			ArrayList<IPredictValues> output = new ArrayList<IPredictValues>();
			loadClassifiers();
			for(Classifier classifier : classifiers)
			{
				output.add(new LabelPowerSetPredict("LabelPowerSet with " +classifier.getClass().toString(), classifier));
			}
			return output;
			}

	private void loadClassifiers()
	{
		// Run well
		classifiers.add(new AdaBoostM1());
		classifiers.add(new AttributeSelectedClassifier());
		classifiers.add(new Bagging());
		classifiers.add(new ClassificationViaRegression());
		classifiers.add(new CVParameterSelection());
		classifiers.add(new J48());
		classifiers.add(new DecisionStump());
		classifiers.add(new DecisionTable());
		classifiers.add(new FilteredClassifier());	
		classifiers.add(new IBk());
		classifiers.add(new InputMappedClassifier());
		classifiers.add(new JRip());
		classifiers.add(new LMT());
		classifiers.add(new Logistic());
		classifiers.add(new LogitBoost());
		classifiers.add(new LWL());
		classifiers.add(new MultiClassClassifier());
		classifiers.add(new MultilayerPerceptron());
		classifiers.add(new MultiScheme());
		classifiers.add(new NaiveBayes());
		classifiers.add(new NaiveBayesMultinomialText());
		classifiers.add(new NaiveBayesUpdateable());
		classifiers.add(new OneR());
		classifiers.add(new PART());
		classifiers.add(new RandomCommittee());
		classifiers.add(new RandomForest());
		classifiers.add(new RandomSubSpace());
		classifiers.add(new RandomTree());
		classifiers.add(new SimpleLogistic());
		classifiers.add(new SMO());
		classifiers.add(new REPTree());
		classifiers.add(new Stacking());
		classifiers.add(new Vote());
		classifiers.add(new ZeroR());
		
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
