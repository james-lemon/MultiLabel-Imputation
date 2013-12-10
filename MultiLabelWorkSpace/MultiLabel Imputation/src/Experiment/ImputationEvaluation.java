package Experiment;

import mulan.classifier.MultiLabelOutput;
import mulan.data.MultiLabelInstances;
import Imputation.LocationOfLabelValue;
import java.util.ArrayList;

import Imputation.IComputeMultiLabelDatasetMetaData;
import Imputation.MissingValueMultiLabelDatasetMetaDataset;

public class ImputationEvaluation {

	ArrayList<Integer> Yi = new ArrayList<Integer>();
	ArrayList<Integer> Mi = new ArrayList<Integer>();
	ArrayList<Integer> Zi = new ArrayList<Integer>();
	public double truePositives = 0;
	public double trueNegatives = 0;
	public double falsePositives = 0;
	public double falseNegatives = 0;
	public double knownPostives = 0;
	public double knownNegatives = 0;
	public MissingValueMultiLabelDatasetMetaDataset metadata;
	public MultiLabelInstances GRANDTRUTH;
	public MultiLabelInstances _predicitions;
	public ArrayList<MultiLabelOutput> _prediction;
	public MultiLabelInstances _original;

	
	/*
	public ImputationEvaluation(MultiLabelInstances GrandTruth, MultiLabelInstances predicitions, int known)
	{
		GRANDTRUTH = GrandTruth;
		_predicitions = predicitions;
		numOfKnown = known;
		run();
	}
	*/
	public ImputationEvaluation(MultiLabelInstances GrandTruth, ArrayList<MultiLabelOutput> prediction, MultiLabelInstances orginal, IComputeMultiLabelDatasetMetaData icompute)
	{
		_prediction = prediction;
		GRANDTRUTH = GrandTruth;
		_original = orginal;
		metadata = icompute.calculate();
		run2();
	}
	
	public double GetHammingLoss()
	{
		double sum = 0;
		for(int i = 0 ; i < falseNegatives + falsePositives; i++)
		{
			sum += (1.0 / GRANDTRUTH.getDataSet().numInstances());
		}
		return (1.0 / GRANDTRUTH.getDataSet().numInstances()) * sum;
 	}
	
	public double GetFMessure()
	{
		return (GetPrecision() * GetRecall()) / (GetPrecision() + GetRecall());
	}
	
	public double GetAccuracy()
	{
		System.out.println("Intersect " + YiMinusYiIntersectMiIntersectZi());
		System.out.println("Union " + YiMinusYiIntersectMiUniontZi());

		double top = YiMinusYiIntersectMiIntersectZi();
		double bottom = YiMinusYiIntersectMiUniontZi();
		return top / bottom;
	}
	
	public double GetPrecision()
	{
		System.out.println("Zi: " + Zi());
		double top = YiMinusYiIntersectMiIntersectZi();
		double bottom = Zi();
		return top / bottom;
	}
	
	public double GetRecall()
	{
		System.out.println("Yi: " + Yi());
		double top = YiMinusYiIntersectMiIntersectZi();
		double bottom = Yi();
		return top / bottom;
	}
	
	public double Yi()
	{
		double output = 0;
		for(int i = 0; i < Yi.size(); i++)
		{
			if(Yi.get(i) == 1)
			{
				output++;
			}
		}
		return output;
	}
	public double YiMinusYiIntersectMiIntersectZi()
	{
		double output = 0;
		int tmp = -1;
		int tmp2 = -1; 
		for(int i = 0; i < Yi.size(); i++)
		{
			tmp = -1;
			tmp2 = -1;
			if(Yi.get(i) == 1)
			{
				tmp = Yi.get(i);
			}
			if(Zi.get(i) == 1)
			{
				tmp2 = Zi.get(i);
			}
			if(tmp == tmp2 && tmp != -1)
			{
				output ++;
			}
			
		}
		return output;
	}
	public double YiMinusYiIntersectMiUniontZi()
	{
		double output = 0;
		int tmp = -1;
		int tmp2 = -1;
		for(int i = 0; i < Yi.size(); i++)
		{
			tmp = -1;
			tmp2 = -1;
			if(Yi.get(i) == 1)
			{
				tmp = Yi.get(i);
				output++;
			}
			if(Zi.get(i) == 1)
			{
				tmp2 = Yi.get(i);
				output++;
			}
			if(tmp == 1 && tmp2 == 1)
			{
				output--;
			}
			
		}
		return output;
	}
	public double Zi()
	{
		double output = 0;
		for(int i = 0; i < Zi.size(); i++)
		{
			if(Zi.get(i) == 0 || Zi.get(i) == 1)
			{
				output += Zi.get(i);
			}
		}
		return output;
	}
	
	public void run2()
	{
		for(int i = 0; i < 100000; i++)
		{
			Yi.add(-1);
			Zi.add(-1);
		}
		int count = -2;
		for(int i = 0; i < GRANDTRUTH.getNumInstances(); i++)
		{
			count++;
			for(int j = 0; j < GRANDTRUTH.getNumLabels(); j++)
			{
				count++;
				int labelindex = GRANDTRUTH.getLabelIndices()[j];
				if(GRANDTRUTH.getDataSet().get(i).stringValue(labelindex).contains("0"))
				{

					if(_original.getDataSet().get(i).isMissing(labelindex))
					{
						Yi.add(count, 0);
						if(!_prediction.get(i).getBipartition()[j])
						{
							trueNegatives++;
							Zi.add(count, 0);
						}
						else
						{
							falsePositives++;
							Zi.add(count, 1);
						}
					}
				}
				else
				{
					{
						if(_original.getDataSet().get(i).isMissing(labelindex))
						{
							Yi.add(count, 1);
							if(_prediction.get(i).getBipartition()[j])
							{
								falseNegatives++;
								Zi.add(count, 0);
							}
							else
							{
								truePositives++;
								Zi.add(count, 1);
							}
						}
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
