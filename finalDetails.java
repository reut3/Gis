package MyPack;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class finalDetails {

//	private List<Id> finallList;
	
	public List<Id> IdList(String[][]arr){//			String [][] finalMatrix= new String[row][46]; //creating the final matrix
		wifi wifi= new wifi();
		int line=2;
		String CurrentLatitude= arr[line][6];
		String FirstSeen= arr[line][3];

		List<Id> FinalList = new ArrayList<Id>();

		for(int row=2; row<arr.length-1; row++){

			List<wifi> tempSortArray = new ArrayList<wifi>();	
			for(line=row; (line<arr.length) && (CurrentLatitude.equals(arr[line][6])) && (FirstSeen.equals(arr[line][3])); line++){
				if(("GSM".equals(arr[line][10])==false)){
					String channel= wifi.convertChannel(arr[line][4]);
					wifi WifiIN= new wifi(arr[line][1], arr[line][0], arr[line][5], channel);
					tempSortArray.add(WifiIN);
				}		
			}
			String WifiAmount= ""+(tempSortArray.size());// כמות WIFI
			tempSortArray.sort(null);

			for(int j=tempSortArray.size()-1; j>9;j--){
				tempSortArray.remove(j);
			}
			
			FinalList.add(new Id(arr[row][3],arr[0][5].substring(8), arr[row][6],arr[row][7],arr[row][8],WifiAmount,tempSortArray));
			
			if(line<arr.length){
				CurrentLatitude= arr[line][6];
				FirstSeen= arr[line][3];
			}
			row=line-1;
		}
		
		return FinalList;

	}
}