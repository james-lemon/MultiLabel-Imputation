package Experiment;

import mulan.evaluation.Evaluation;

public class EvaluationWithExperiment {

	public ImputationEvaluation evaluation;
	public String experiment;
	
	public String toString()
	{
		StringBuffer output = new StringBuffer();
		output.append("Experiment title: " + experiment + "\n");
		output.append("Accuracy: " + evaluation.GetAccuracy() + "\n");
		output.append("Recall: " + evaluation.GetRecall() + "\n");
		output.append("Precision: " + evaluation.GetPrecision() + "\n");
		output.append("F-Messure: " + evaluation.GetFMessure() + "\n");
		output.append("\n \n");
		return output.toString();
	}
}
