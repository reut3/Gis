package Main;

import java.util.function.Predicate;

import DataBase.Sample;
import FileTools.CsvFile;
import FileTools.KmlFile;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		long a= System.currentTimeMillis();
		CsvFile.readCSV("C:\\Users\\Inon\\git\\matala\\inputFile");
		KmlFile.readCSV("C:\\Users\\Inon\\git\\matala\\finalFile.csv");


		CsvFile.readCSV("C:\\Users\\Inon\\git\\matala\\inputFile");

		//algo1 print csv file named algo1
		algos.algo1("algo1","C:\\Users\\Inon\\git\\matala\\finalFile.csv");

		
		
//		algo2 print csv file named algo2
		algos.algo2("C:\\Users\\Inon\\git\\matala\\finalfile.csv","C:\\Users\\Inon\\git\\matala\\testAlgo2.csv","algo2", 3);
		
	//	DataBase.DataBase.FinalFilterDatabase=Filter.filter.filters(DataBase.DataBase.FinalDataBase, Filter.filter.equalAltLon(65,55,3 ), Filter.filter.equalId("inon"));
		System.out.println(DataBase.DataBase.FinalFilterDatabase);
//		long b= System.currentTimeMillis();
//		System.out.println(b-a);
		
		
		
		
		
		
		
		
		
		

	}


}





