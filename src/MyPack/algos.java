package MyPack;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class algos {


	/**
	 * line separator
	 */
	private static final String NEW_LINE_SEPARATOR = "\n";


	public static List<Sample> readCSV(String Location){
		List<Sample> SampleList= new ArrayList<Sample>();

		FileReader fileReader = null;

		CSVParser csvFileParser = null;

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord();
		try {

			//initialize FileReader object
			fileReader = new FileReader(Location);

			//initialize CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);

			//Get a list of CSV file records
			List<CSVRecord> csvRecords = csvFileParser.getRecords(); 

			int start=0;
			String check = csvRecords.get(0).get(0);
			if(check.equals("Time")){
				start=1;

			}
			else{
				start=0;
			}

			//Read the CSV file records 
			for (int j = start; j < csvRecords.size(); j++) {

				CSVRecord record = csvRecords.get(j);
				List<wifi> listOfWifi= new ArrayList<wifi>();


				boolean end=true;
				for( int i=6; i<record.size()&&end; ){
					if(record.get(i).trim().equals("") && record.get(i+1).trim().equals("")){
						end=false;
						break;
					}
					if(i+3<record.size()){
						listOfWifi.add(new wifi(record.get(i), record.get(i+1), record.get(i+3), record.get(i+2)));
					}
					i+=4;
				}		
				String recordlat;
				String recordlon;
				String recordAlt;

				if (record.get(2).equals("?")){
					recordlat="-200";
					recordlon="-200";
					recordAlt="-200";
				}
				else{
					recordlat=record.get(2); 
					recordlon=record.get(3);
					recordAlt=record.get(4);
				}

				Sample sample= new Sample(record.get(0), record.get(1),recordlat,recordlon,recordAlt,
						record.get(5), listOfWifi);
				SampleList.add(sample);
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
		return SampleList;
	}


	public static void algo1(String fileName ,String path) {
		List<Sample> list= readCSV(path);

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

			List<String> macs= new ArrayList<String>();

			int num=0;

			for (Sample s: list) {
				for(int i=0; i<s.getListOfWifi().size(); i++){
					if(!macs.contains(s.getListOfWifi().get(i).getMac())){
						Weight weight= new Weight();
						//return new location if mac is doubled in the list
						Location location= weight.findLocation1(list, s.getListOfWifi().get(i).getMac());

						List<String> IdDataRecord = new ArrayList<String>();
						IdDataRecord.add(num+"");
						IdDataRecord.add(s.getListOfWifi().get(i).getMac());
						IdDataRecord.add(s.getListOfWifi().get(i).getSsid());
						IdDataRecord.add(s.getListOfWifi().get(i).getChannel());
						IdDataRecord.add(s.getListOfWifi().get(i).getRssi());
						IdDataRecord.add(location.getLat().getCord()+"");
						IdDataRecord.add(location.getLon().getCord()+"");
						IdDataRecord.add(location.getAlt().getCord()+"");
						IdDataRecord.add(s.getTime());
						IdDataRecord.add("Approx. w-center algo1");
						num++;
						csvFilePrinter.printRecord(IdDataRecord);
						macs.add(s.getListOfWifi().get(i).getMac());
					}
				}
			}

			System.out.println("CSV file- 'algo1' was created successfully !!!");

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


	public static void algo2(String fileName,String path, String pathDataBase, int num){
		List<Sample> listDataBase= readCSV(pathDataBase);
		List<Sample> listToFind= readCSV(path);

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
//			System.out.println(listDataBase.get(54));
			for (int s=0; s<listToFind.size();s++) {
				List<String> IdDataRecord = new ArrayList<String>();
				IdDataRecord.add(listToFind.get(s).getTime());
				IdDataRecord.add(listToFind.get(s).getId());

				List <MacSignal> macSignal= new ArrayList<MacSignal>();
				for(int x=0; x<listToFind.get(s).getListOfWifi().size(); x++){
					MacSignal temp= new MacSignal(listToFind.get(s).getListOfWifi().get(x).getRssi(), listToFind.get(s).getListOfWifi().get(x).getMac());
					macSignal.add(temp);
				}
				Location location= Weight.findLocation2(listDataBase,macSignal,num);

				IdDataRecord.add(location.getLat().getCord()+"");
				IdDataRecord.add(location.getLon().getCord()+"");
				IdDataRecord.add(location.getAlt().getCord()+"");
				IdDataRecord.add(listToFind.get(s).getWifiAmount());

				for(int i=0; i<listToFind.get(s).getListOfWifi().size(); i++){
					IdDataRecord.add(listToFind.get(s).getListOfWifi().get(i).getMac());
					IdDataRecord.add(listToFind.get(s).getListOfWifi().get(i).getSsid());
					IdDataRecord.add(listToFind.get(s).getListOfWifi().get(i).getChannel());
					IdDataRecord.add(listToFind.get(s).getListOfWifi().get(i).getRssi());

				}

				csvFilePrinter.printRecord(IdDataRecord);
			}

			System.out.println("CSV file- 'algo2' was created successfully !!!");

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
