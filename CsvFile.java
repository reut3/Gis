
package MyPack;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
/**
 * 
 * @author reut
 *
 */

public class CsvFile {

	/**
	 * void function <br>
	 * @param String Location<br>
	 * reads all the csv file from the folder location and sends them to write function
	 * 
	 */
	public static void readCSV(String Location){
		//take files from folder, and then only the csv files
		File folder = new File(Location);
		File[] listOfFiles = folder.listFiles();
		File[] listOfFilesCSV= Checks.FileCheck(listOfFiles);
		List<String[]> collectionCSVinfo= new ArrayList<String[]>();

		//read one file at a time
		for(int i=0; i<listOfFilesCSV.length && listOfFilesCSV[i]!=null; i++){

			String fileName = listOfFilesCSV[i].getPath();

			FileReader fileReader = null;

			CSVParser csvFileParser = null;

			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord();
			try {

				//initialize FileReader object
				fileReader = new FileReader(fileName);

				//initialize CSVParser object
				csvFileParser = new CSVParser(fileReader, csvFileFormat);

				//Get a list of CSV file records
				List<CSVRecord> csvRecords = csvFileParser.getRecords(); 

				CSVRecord headerRecord = csvRecords.get(0);
				String header= headerRecord.get(5).substring(8);
				//Read the CSV file records starting from the second record to skip the header
				for (int j = 2; j < csvRecords.size(); j++) {
					CSVRecord record = csvRecords.get(j);
					if(!("1970-01-01 02:00:00").equals(record.get(3))){
					String [] line= {record.get(0),record.get(1),record.get(2),record.get(3),wifi.convertChannel(record.get(4)),
							record.get(5),record.get(6),record.get(7),record.get(8),record.get(9),record.get(10), header};
					collectionCSVinfo.add(line);
					}
				}  
			} 
			catch (Exception e) {
				System.out.println("Error in CsvFileReader !!!");
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
					csvFileParser.close();
				} catch (IOException e) {
					System.out.println("Error while closing fileReader/csvFileParser !!!");
					e.printStackTrace();
				}
			}

		}//finish treating a file at a time
//		System.out.println(Arrays.toString(collectionCSVinfo.get(0)));
		List<Sample> samples= detailsToCSV.SampleList(collectionCSVinfo);
		writeCSV("finalFile", samples);
	}


	private static final Object [] FILE_HEADER = {"Time","ID","LAT","LON","ALT", "#WiFi networks",
			"SSID1", "MAC1", "Frequncy1", "Signal1",
			"SSID2", "MAC2", "Frequncy2", "Signal2",
			"SSID3", "MAC3", "Frequncy3", "Signal3",
			"SSID4", "MAC4", "Frequncy4", "Signal4",
			"SSID5", "MAC5", "Frequncy5", "Signal5",
			"SSID6", "MAC6", "Frequncy6", "Signal6",
			"SSID7", "MAC7", "Frequncy7", "Signal7",
			"SSID8", "MAC8", "Frequncy8", "Signal8",
			"SSID9", "MAC9", "Frequncy9", "Signal9",
			"SSID10", "MAC10", "Frequncy10", "Signal10"
	};

	private static final String NEW_LINE_SEPARATOR = "\n";


	public static void writeCSV(String fileName, List<Sample> list) {
		fileName = fileName+".csv";
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		//Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		try
		{//initialize FileWriter object
			fileWriter = new FileWriter(fileName);

			//initialize CSVPrinter object 
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			//Create CSV file header
			csvFilePrinter.printRecord(FILE_HEADER);

			//Write a new student object list to the CSV file
			for (Sample s: list) {
				List<String> IdDataRecord = new ArrayList<String>();
				IdDataRecord.add(s.getTime());
				IdDataRecord.add(s.getId());
				IdDataRecord.add(s.getLocation().getLat().getCord()+"");
				IdDataRecord.add(s.getLocation().getLon().getCord()+"");
				IdDataRecord.add(s.getLocation().getAlt().getCord()+"");
				IdDataRecord.add(s.getWifiAmount());
				for(int i=0; i<s.getListOfWifi().size();i++){
					IdDataRecord.add(s.getListOfWifi().get(i).getSsid());
					IdDataRecord.add(s.getListOfWifi().get(i).getMac());
					IdDataRecord.add(s.getListOfWifi().get(i).getChannel());
					IdDataRecord.add(s.getListOfWifi().get(i).getRssi());

				}
				csvFilePrinter.printRecord(IdDataRecord);
			}
			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}


}


