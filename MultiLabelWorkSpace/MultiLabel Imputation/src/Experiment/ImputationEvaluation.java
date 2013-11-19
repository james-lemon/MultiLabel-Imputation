package Experiment;

import mulan.classifier.MultiLabelOutput;
import mulan.data.MultiLabelInstances;
import java.util.ArrayList;

public class ImputationEvaluation {

	public int truePositives = 0;
	public int trueNegatives = 0;
	public int falsePositives = 0;
	public int falseNegatives = 0;
	public MultiLabelInstances GRANDTRUTH;
	public MultiLabelInstances _predicitions;
	public ArrayList<MultiLabelOutput> _prediction;
	
	public ImputationEvaluation(MultiLabelInstances GrandTruth, MultiLabelInstances predicitions)
	{
		GRANDTRUTH = GrandTruth;
		_predicitions = predicitions;
		run();
	}
	
	public ImputationEvaluation(MultiLabelInstances GrandTruth, ArrayList<MultiLabelOutput> prediction)
	{
		_prediction = prediction;
		GRANDTRUTH = GrandTruth;
		run2();
	}
	
	public double GetFMessure()
	{
		return (GetPrecision() * GetRecall()) / (GetPrecision() + GetRecall());
	}
	
	public double GetAccuracy()
	{
		double top = truePositives + trueNegatives;
		double bottom = top + falsePositives + falseNegatives;
		return top / bottom;
	}
	
	public double GetPrecision()
	{
		double top = truePositives;
		double bottom = truePositives + falsePositives;
		return top / bottom;
	}
	
	public double GetRecall()
	{
		double top = truePositives;
		double bottom = truePositives + falseNegatives;
		return top / bottom;
	}
	
	public void run2()
	{
		for(int i = 0; i < GRANDTRUTH.getNumInstances(); i++)
		{
			for(int j = 0; j < GRANDTRUTH.getNumLabels(); j++)
			{
				int labelindex = GRANDTRUTH.getLabelIndices()[j];
				if(GRANDTRUTH.getDataSet().get(i).stringValue(labelindex).contains("0"))
				{
					if(!_prediction.get(i).getBipartition()[j])
					{
						trueNegatives++;
					}
					else
					{
						falsePositives++;
					}
				}
				else
				{
					if(!_prediction.get(i).getBipartition()[j])
					{
						falseNegatives++;
					}
					else
					{
						truePositives++;

					}
				}
			}
		}
	}
	
	private void run()
	{
		for(int i = 0; i < GRANDTRUTH.getNumInstances(); i++)
		{
			for(int j = 0; j < GRANDTRUTH.getNumLabels(); j++)
			{
				int labelindex = GRANDTRUTH.getLabelIndices()[j];
				if(GRANDTRUTH.getDataSet().get(i).stringValue(labelindex).contains("0"))
				{
					if(_predicitions.getDataSet().get(i).stringValue(labelindex).contains("0"))
					{
						trueNegatives++;
					}
					else
					{
						falsePositives++;
					}
				}
				else
				{
					if(_predicitions.getDataSet().get(i).stringValue(labelindex).contains("0"))
					{
						falseNegatives++;
					}
					else
					{
						truePositives++;
					}
				}
			}
		}
	}
	
}
