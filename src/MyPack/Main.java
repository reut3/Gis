package MyPack;


public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CsvFile.readCSV("C:\\Users\\reut\\workspace\\matala\\inputFile");
		KmlFile.readCSV("C:\\Users\\reut\\workspace\\matala\\finalFile.csv");

		
		//algo1 print csv file named algo1
		algos.algo1("algo1","C:\\Users\\reut\\workspace\\matala\\finalFile.csv");
		
		//algo2 print csv file named algo2

		algos.algo2("algo2","C:\\Users\\reut\\workspace\\matala\\testAlgo2.csv","C:\\Users\\reut\\workspace\\matala\\finalfile.csv", 3);

		
	}

}
