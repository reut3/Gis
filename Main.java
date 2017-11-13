package MyPack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		CsvFile CsvFile=new CsvFile();
		WriteKML WriteKML= new WriteKML();

		CsvFile.readCSV("C:\\Users\\reut\\Desktop\\matala2");
		WriteKML.readCSV("C:\\Users\\reut\\workspace\\matala0\\finalFile.csv");

		
	}

}
