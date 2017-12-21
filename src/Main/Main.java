package Main;

import FileTools.CsvFile;
import FileTools.KmlFile;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		long a= System.currentTimeMillis();
		CsvFile.readCSV("C:\\Users\\reut\\workspace\\matala\\inputFile");
		KmlFile.readCSV("C:\\Users\\reut\\workspace\\matala\\finalFile.csv");



		//algo1 print csv file named algo1
		algos.algo1("algo1","C:\\Users\\reut\\workspace\\matala\\finalFile.csv");

		

		
		
//		algo2 print csv file named algo2

//		algos.algo2("C:\\Users\\reut\\workspace\\matala\\finalfile.csv","C:\\Users\\reut\\workspace\\matala\\testAlgo2.csv","algo2", 3);
//		long b= System.currentTimeMillis();
		
		algos.algo2("C:\\Users\\reut\\workspace\\matala\\_comb_all_BM3_.csv","C:\\Users\\reut\\workspace\\matala\\_comb_no_gps_ts2_.csv","algo2_check", 4);

//		System.out.println(b-a);
		
		
		
		
		
		
		
		
		
		

	}

}





