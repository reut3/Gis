package MyPack;

import java.io.File;

public class Checks {

	public  File[] FileCheck(File[] listOfFiles){
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


	public  boolean emptyLine(String [][] data){
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
