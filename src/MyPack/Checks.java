package MyPack;
import java.io.File;


/**
 * check class
 * contains 2 functions: <br>
 * FileCheck- take only csv files from given folder <br>
 * emptyLine- checks whether a csv file is ok <br>
 * 
 */
public class Checks {

	/**
	 * 
	 * @param file array contains files names
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







}
