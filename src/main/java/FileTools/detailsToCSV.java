package FileTools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataBase.Sample;
import DataBase.wifi;
import Location.Location;


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
	public static Set<Sample> SampleList(List<String[]> list){
		Location currenLocation= new Location(list.get(0)[6], list.get(0)[7], list.get(0)[8]);
		Location nextLocation= new Location(list.get(1)[6], list.get(1)[7], list.get(1)[8]);
		String Time= list.get(0)[3];

		Set<Sample> FinalList = new HashSet<Sample>();

		int next=0;
		for(int row=0;row<list.size();row++){
			List<wifi> tempSortArray = new ArrayList<wifi>();
			if(("GSM".equals(list.get(row)[10])==false)){
				wifi WifiIN= new wifi(list.get(row)[1], list.get(row)[0], list.get(row)[5], list.get(row)[4]);
				tempSortArray.add(WifiIN);
			}
			for(next=row+1; next<list.size() && nextLocation.equal(currenLocation) && Time.equals(list.get(next)[3]); next++){
				if(("GSM".equals(list.get(next)[10])==false)){
					wifi WifiIN= new wifi(list.get(next)[1], list.get(next)[0], list.get(next)[5], list.get(next)[4]);
					tempSortArray.add(WifiIN);
				}
				if(next+1<list.size()){
					nextLocation= new Location(list.get(next+1)[6], list.get(next+1)[7], list.get(next+1)[8]);
				}
			}
			row=next;
			if(row<list.size()){
				currenLocation= nextLocation;
				Time= list.get(row)[3];
			}
			if(row+1<list.size()){
				nextLocation= new Location(list.get(next+1)[6], list.get(next+1)[7], list.get(next+1)[8]);
			}
			
			
			tempSortArray.sort(null);

			for(int j=tempSortArray.size()-1; j>9;j--){
				tempSortArray.remove(j);
			}
			String WifiAmount= ""+(tempSortArray.size());

			if(Integer.parseInt(WifiAmount)!=0){
				FinalList.add(new Sample(list.get(next-1)[3], list.get(next-1)[11],
						list.get(next-1)[6],list.get(next-1)[7],list.get(next-1)[8],WifiAmount,tempSortArray));
			}
			row=next-1; //because the for adding 1
		}
		return FinalList;
	}
}