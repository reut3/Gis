package MyPack;

import java.util.ArrayList;
import java.util.List;


/**
 * detailsToCSV class contains one function: <br> 
 * SampleList- gets csv info in list of strings array, put it into Sample list and return it
 *
 */
public class detailsToCSV {

	/**
	 * the function get list of strings array- <br>
	 * every string array is a line from a csv file  <br>
	 * the functions Department the lines to samples, and from every sample takes the 10 strongest wifi
	 * @param list of string array 
	 * @return Sample list
	 */
	public static List<Sample> SampleList(List<String[]> list){
		int line=0;
		Location currenLocation= new Location(list.get(0)[6], list.get(0)[7], list.get(0)[8]);
		Location nextLocation= new Location(list.get(1)[6], list.get(1)[7], list.get(1)[8]);
		String Time= list.get(1)[3];

		List<Sample> FinalList = new ArrayList<Sample>();
		for(int row=0; row<list.size(); row++){
			List<wifi> tempSortArray = new ArrayList<wifi>();
			//System.out.println(currenLocation+" "+nextLocation);
			for(line=row; (line<list.size()) && (currenLocation.equal(nextLocation)) && (Time.equals(list.get(line)[3])); line++){
				if(("GSM".equals(list.get(line)[10])==false)){
					wifi WifiIN= new wifi(list.get(line)[1], list.get(line)[0], list.get(line)[5], list.get(line)[4]);
					tempSortArray.add(WifiIN);

				}		
				if(line+1<list.size()){
					nextLocation= new Location(list.get(line+1)[6], list.get(line+1)[7], list.get(line+1)[8]);
				}
			}
			String WifiAmount= ""+(tempSortArray.size());
			tempSortArray.sort(null);

			for(int j=tempSortArray.size()-1; j>9;j--){
				tempSortArray.remove(j);
			}
			FinalList.add(new Sample(list.get(row)[3], list.get(row)[11], list.get(row)[6],list.get(row)[7],list.get(row)[8],WifiAmount,tempSortArray));

			if(line<list.size()){
				currenLocation= new Location(list.get(line)[6], list.get(line)[7], list.get(line)[8]);
				Time= list.get(line)[3];
			}
			//	System.out.println(line+" "+row);

			row=line-1;
		}
		return FinalList;

	}
}