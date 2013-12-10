package Imputation;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

import weka.core.Instance;
import mulan.data.MultiLabelInstances;

public class ComputeMultiLabelDatasetMetaData implements IComputeMultiLabelDatasetMetaData{

	private MissingValueMultiLabelDatasetMetaDataset x;
	private Map<String,Integer> labeladdress;
	//Constructor
	public ComputeMultiLabelDatasetMetaData(MultiLabelInstances Muti_Instances)
	{
		x = new MissingValueMultiLabelDatasetMetaDataset();
		x.Dataset = Muti_Instances;
		x.NumberOfMissingLabels = 0;
		x.MissingLabels = new ArrayList<LocationOfLabelValue>();
		x.ImputatedLabels  = new ArrayList<LocationOfLabelValue>();
		x.KnownLabels =  new ArrayList<LocationOfLabelValue>();
		

	}
	//Method Will Generate MissingValueMultiLabelDatasetMetaDataset based on MultiLabelInstances
	@Override
	public MissingValueMultiLabelDatasetMetaDataset calculate() 
	{
		int i = 0;
		int j = 0;
		
		int no_instances = 0;
		int no_labels =0;
		int no_attributes = 0;
		x.knownNegatives = 0;
		x.knownPostives = 0;
	
		//Add Missing Values to x.MissingLabels
		no_instances = x.Dataset.getNumInstances();
		no_labels = x.Dataset.getNumLabels();
		no_attributes = x.Dataset.getDataSet().numAttributes();
		for(i = 0; i < no_instances; i++)
		{
			if(x.Dataset.hasMissingLabels(x.Dataset.getDataSet().get(i)))
			{
				for(j = 0; j< no_labels;j++)
				{
					
					if(x.Dataset.getDataSet().get(i).isMissing(x.Dataset.getLabelIndices()[j]))
					{
						LocationOfLabelValue locationdata = new LocationOfLabelValue();
						locationdata.instance = x.Dataset.getDataSet().get(i); 
						locationdata.labelname = (String)x.Dataset.getLabelsMetaData().getLabelNames().toArray()[j];
						locationdata.instanceLocation=i;
						x.MissingLabels.add(locationdata);
					}
					else
					{
						LocationOfLabelValue locationdata = new LocationOfLabelValue();
						locationdata.instance = x.Dataset.getDataSet().get(i); 
						locationdata.labelname = (String)x.Dataset.getLabelsMetaData().getLabelNames().toArray()[j];
						locationdata.instanceLocation=i;
						x.KnownLabels.add(locationdata);
						if(x.Dataset.getDataSet().get(i).stringValue(x.Dataset.getLabelIndices()[j]).contains("0"))
						{
							x.knownNegatives++;
						}
						else
						{
							x.knownPostives++;
						}
					}
	
				}
			}
			else
			{
				for(j = 0; j< no_labels;j++)
				{
					LocationOfLabelValue locationdata = new LocationOfLabelValue();
					locationdata.instance = x.Dataset.getDataSet().get(i); 
					locationdata.labelname = (String)x.Dataset.getLabelsMetaData().getLabelNames().toArray()[j];
					locationdata.instanceLocation=i;
					x.KnownLabels.add(locationdata);
				}
			}
		}
		
		//Add know values to x.Knowlabels
		//Count number of missing values, save as x.NumberOfMissingLabels
		x.NumberOfMissingLabels = x.MissingLabels.size(); 
	
		return x;
	}

	//Will change Missing values to Imputated values, and store their guesses in the MultiLabelInstances
	@Override
	public MissingValueMultiLabelDatasetMetaDataset addImputatedLabels(ArrayList<LocationOfLabelValueWithValue> PredictedLabels) 
	{
		// TODO Auto-generated method stub
		int i = 0;
		int instanceIndex = 0;
		int labelIndex = 0;
		for(i = 0; i < PredictedLabels.size() ; i++)
		{
			instanceIndex = x.Dataset.getDataSet().indexOf(PredictedLabels.get(i).instance);
			labelIndex = x.Dataset.getLabelsOrder().get(PredictedLabels.get(i).labelname);
			if(PredictedLabels.get(i).Value)
				x.Dataset.getDataSet().get(instanceIndex).setValue(labelIndex, 1);
			else
				x.Dataset.getDataSet().get(instanceIndex).setValue(labelIndex, 0);
			for(int j = 0; j < x.MissingLabels.size();j++)
			{
				if(x.MissingLabels.get(j).instance.equalHeaders(PredictedLabels.get(i).instance))
				{
					if(x.MissingLabels.get(j).labelname.equalsIgnoreCase(PredictedLabels.get(i).labelname))
					{
						x.MissingLabels.remove(j);
						x.ImputatedLabels.add(PredictedLabels.get(i));
						break;
					}
				}
			}
		}
		
		return null;
	}
	
	

}
