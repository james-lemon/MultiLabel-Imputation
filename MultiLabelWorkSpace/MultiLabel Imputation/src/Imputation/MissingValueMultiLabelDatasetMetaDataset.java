 package Imputation;

import java.util.ArrayList;

import mulan.data.MultiLabelInstances;

public class MissingValueMultiLabelDatasetMetaDataset {

	public MultiLabelInstances Dataset;
	public int NumberOfMissingLabels;
	public ArrayList<LocationOfLabelValue> MissingLabels; //String Holds names of Labels with Missing values
	public ArrayList<LocationOfLabelValue> ImputatedLabels; //String holds names of Labels with imputated Values
	public ArrayList<LocationOfLabelValue> KnownLabels; //String holds names of labels with known values
	public int knownPostives;
	public int knownNegatives;
}
