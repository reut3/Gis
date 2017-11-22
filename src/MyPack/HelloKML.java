package MyPack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * @author ashraf
 * 
 */
public class HelloKML {

	//Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	//CSV file header
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

	public static void writeCsvFile(String fileName, List<Id> list) {

		fileName = fileName+".csv";
		System.out.println(fileName);
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
			for (Id l: list) {
				List<String> IdDataRecord = new ArrayList<String>();
				IdDataRecord.add(l.Time);
				IdDataRecord.add(l.Id);
				IdDataRecord.add(l.Lat);
				IdDataRecord.add(l.Lon);
				IdDataRecord.add(l.Alt);
				IdDataRecord.add(l.WifiAmount);
				for(int i=0; i<l.ListOfWifi.size();i++){
				IdDataRecord.add(l.ListOfWifi.get(i).SSID);
				IdDataRecord.add(l.ListOfWifi.get(i).MAC);
				IdDataRecord.add(l.ListOfWifi.get(i).RSSI);
				IdDataRecord.add(l.ListOfWifi.get(i).Channel);

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
	
	
	
	
	
	
	
	
	
	
	
	public  static void writeCSV(List<Id> list, FileWriter finalFile){
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
