
package MyPack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CsvFile{

	
	public  void readCSV(String Location){
		Checks Checks=new Checks();
		finalDetails finalDetails=new finalDetails();
		FileWriter finalFile = null;
		try {
			finalFile = new FileWriter("finalFile.csv");
			finalFile.append("Time");
			finalFile.append(",");
			finalFile.append("ID");
			finalFile.append(",");
			finalFile.append("LAT");
			finalFile.append(",");
			finalFile.append("LON");
			finalFile.append(",");
			finalFile.append("ALT");
			finalFile.append(",");
			finalFile.append("#WiFi networks");
			finalFile.append(",");

			for(int j=1  ; j<11; j++){
				finalFile.append("SSID"+j);
				finalFile.append(",");
				finalFile.append("MAC"+j);
				finalFile.append(",");
				finalFile.append("Frequncy"+j);
				finalFile.append(",");
				finalFile.append("Signal"+j);
				finalFile.append(",");

			}
			finalFile.append("\n");


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		//take files from folder, and then only the csv files
		File folder = new File(Location);
		File[] listOfFiles = folder.listFiles();
		File[] listOfFilesCSV= Checks.FileCheck(listOfFiles);

		//read one file at a time
		for(int i=0; i<listOfFilesCSV.length && listOfFilesCSV[i]!=null; i++){
			String csvFile = listOfFilesCSV[i].getPath();
			BufferedReader br = null;
			int linesNum=0;
			String line = "";
			String cvsSplitBy = ",";


			//find how many lines the file contains
			try {
				br = new BufferedReader(new FileReader(csvFile));
				while ((br.readLine()) != null) {
					linesNum++;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			//end of finding how many lines.


			String[][] data = new String[linesNum][11];//create new array for data
			try {
				br = new BufferedReader(new FileReader(csvFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//read and put into matrix
			for(int m=0; m<linesNum; m++) {
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String strar[] = line.split(cvsSplitBy);//get contents of line as an array
				strar[3]=strar[3].replace("-","/");

				for(int j=0; j<strar.length; j++){
					data[m][j]=strar[j];
				}
			}

//			for(int rows=0; rows<data.length; rows++){
//				for(int colum=0; colum<data[1].length; colum++){
//					System.out.print((data[rows][colum])+" , ");
//				}
//				System.out.println();
//			}


			if(Checks.emptyLine(data)){
				List<Id> tempList= finalDetails.IdList(data);
				
				writeCSV(tempList, finalFile);
				//	write( "name", findSignal(data));

			}
		}//finish treating a file at a time

	}

	//write to CSV file the finalArrayList
	public  void writeCSV(List<Id> list, FileWriter finalFile){

		try {
			for(Id m:list){
				String[] line= (m.toString()).split(",");
				for(int j=0; j<line.length; j++){
					line[j]=line[j].replace("[","");
					line[j]=line[j].replace("]","");

					finalFile.append(line[j]);
					if(j<line.length-1){
						finalFile.append(",");
					}
				}
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				finalFile.flush();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

	}
}


