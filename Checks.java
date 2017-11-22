package MyPack;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * check class
 * contains two functions <br>
 * FileCheck- take only csv files from given folder <br>
 * emptyLine- checks whether a csv file is ok 
 */
public class Checks {

	/**
	 * 
	 * @param file array contains files named
	 * @return new file array contains only csv files
	 */

	public static File[] FileCheck(File[] listOfFiles){
		File[] listOfFilesCSV= new File[listOfFiles.length];
		int position=0;
		for(int i=0; i<listOfFiles.length; i++){
			if(listOfFiles[i].getPath().contains("csv")){
				listOfFilesCSV[position]=listOfFiles[i];
				position++;
			}
		}
		return listOfFilesCSV;
	}

	/**
	 * 
	 * @param string matrix named data
	 * the function checks if file contains empty line
	 * @return true if the file is ok or false if the file contains empty line==not proper
	 */
	public  static boolean emptyLine(String [][] data){
		boolean answer= true;
		for(int i=1; i<data.length; i++){
			for(int j=0; j<data[0].length; j++){
				if (data[i][j]==null){
					return answer=false;
				}
			}
		}
		return answer;
	}


	public static List<Sample> MACfilter(List<Sample> list){
		List <String> macs= new ArrayList<String>();
		String tempMac="";
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				tempMac= list.get(i).getListOfWifi().get(j).getMac();
				macs.add(i+ " "+ j+" "+ tempMac);
			}
		}
		System.out.println(macs);
		for(int i=0; (!macs.get(i).equals("null") && i<macs.size()); i++){
			String strongest= macs.get(i);
			for(int j=i+1; (!macs.get(j).equals("null") && j<macs.size()-2); j++){

				String[] mac1=strongest.split(" ");
				String[] mac2=macs.get(j).split(" ");		

				if(mac1[2].equals(mac2[2])){
					int sample1= Integer.parseInt(mac1[0]);
					int wifi1= Integer.parseInt(mac1[1]);
					
					int sample2= Integer.parseInt(mac2[0]);
					int wifi2= Integer.parseInt(mac2[1]);
					
					System.out.println(mac1[2]);
					System.out.println(mac2[2]);
					if(list.get(sample1).getListOfWifi().get(wifi1).compareTo
							(list.get(sample2).getListOfWifi().get(wifi2))
							==-1){
						strongest= macs.get(j);
						macs.set(i, "null");
						int wifiAmount= Integer.parseInt(list.get(sample1).getWifiAmount())-1;
						list.get(sample1).setWifiAmount(wifiAmount+"");
						list.get(sample1).getListOfWifi().set(wifi1, new wifi());	
					}
					else{
						System.out.println(sample2+" "+wifi2);
						macs.set(j, "null");
						int wifiAmount= Integer.parseInt(list.get(sample2).getWifiAmount())-1;
						list.get(sample2).setWifiAmount(wifiAmount+"");
						list.get(sample2).getListOfWifi().set(wifi2, new wifi());	
					}

				}
			}
		}
		return list;

	}



}
