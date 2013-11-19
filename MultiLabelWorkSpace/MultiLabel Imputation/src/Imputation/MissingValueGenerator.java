package Imputation;

import java.util.Random;

import weka.core.Instances;
import mulan.data.InvalidDataFormatException;
import mulan.data.LabelsMetaData;
import mulan.data.MultiLabelInstances;



public class MissingValueGenerator {

	public static MultiLabelInstances Create(MultiLabelInstances completeDataset, double RatioOfMissingValues) throws InvalidDataFormatException
	{
		int[] Labels = completeDataset.getLabelIndices();
		int numLabels = completeDataset.getNumLabels();
		int numInstances = completeDataset.getNumInstances();
		LabelsMetaData metaData = completeDataset.getLabelsMetaData();
		Instances instances = new Instances(completeDataset.getDataSet());
		Random randomGenerator = new Random();
		
		for(int i = 0; i < numInstances; i++)
		{
			for(int j = 0; j < numLabels; j++)
			{
				if(randomGenerator.nextDouble() > RatioOfMissingValues)
				{
					instances.get(i).setMissing(j);;
				}
			}
		}
		try {
			return new MultiLabelInstances(instances, metaData);
		} catch (InvalidDataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MultiLabelInstances(instances, completeDataset.getLabelsMetaData());
	}
}
