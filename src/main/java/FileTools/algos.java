package FileTools;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import DataBase.MacSignal;
import DataBase.Sample;
import DataBase.wifi;
import Filter.filter;
import Location.Location;
import Location.Weight;

public class algos {

	/**
	 * line separator
	 */
	private static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * the function reads CSV file and put all the info in a Sample list<br>
	 * @param Location of the CSV file
	 * @return list of Samples
	 */
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
				try{
					Sample sample= new Sample(record.get(0), record.get(1),recordlat,recordlon,recordAlt,
							record.get(5), listOfWifi);
					SampleList.add(sample);
				}catch(Exception e){
					System.out.println("not in format 46 CSV file");
					break;
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
		return SampleList;
	}

	/**
	 * The functions gets CSV file and read it. 
	 * if there are double macs the functions will calculate the accurate location<br>
	 * finally it will write a file with information of every mac. 
	 * @param fileName
	 * @param path- of the reading file
	 */
	public static void algo1(String fileName ,String path) {
		List<Sample> list= readCSV(path);

		HashMap<String, ArrayList<Sample>> hashMap= new HashMap<String, ArrayList<Sample>>();
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				String key= list.get(i).getListOfWifi().get(j).getMac();
				Sample toAdd= list.get(i);

				if (hashMap.get(key) == null) {
					hashMap.put(key, new ArrayList<Sample>());
				}
				hashMap.get(key).add(toAdd);
			}
		}



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
			list= filter.MACfilter(list);


			for (Sample s: list) {
				for(int i=0; i<s.getListOfWifi().size() && s.getListOfWifi().get(i).getMac()!=null ; i++){

					if(!macs.contains(s.getListOfWifi().get(i).getMac())){

						Weight weight= new Weight();
						//return new location if mac is doubled in the list
						String tempMac=s.getListOfWifi().get(i).getMac();
						Location location= weight.findLocation1(hashMap.get(tempMac), tempMac);

						if(s.getListOfWifi().get(i).getMac().equals("1c:b9:c4:16:05:38")){
							//							System.out.println("it is"+s);
							//							System.out.println(hashMap.get("1c:b9:c4:16:05:38").size());
						}
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

	/**
	 * The functions reads every line=sample in a CSV file,
	 * and calculate the accurate location of the taken sample
	 * @param pathDataBase- path of the file with the dataBase
	 * @param path- of the reading file
	 * @param fileName
	 * @param num of samples to compare with
	 */
	public static void algo2(String pathDataBase,String path,String fileName, int num){
		List<Sample> listDataBase= readCSV(pathDataBase);
		List<Sample> listToFind= readCSV(path);


		HashMap<String, ArrayList<Sample>> hashMap= new HashMap<String, ArrayList<Sample>>();
		for(int i=0; i<listDataBase.size(); i++){
			for(int j=0; j<listDataBase.get(i).getListOfWifi().size(); j++){
				String key= listDataBase.get(i).getListOfWifi().get(j).getMac();
				Sample toAdd= listDataBase.get(i);

				if (hashMap.get(key) == null) {
					hashMap.put(key, new ArrayList<Sample>());
				}
				hashMap.get(key).add(toAdd);
			}
		}


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
			for (int s=0; s<listToFind.size();s++) {
				Set<Sample> sampleSet = new HashSet<Sample>();
				List<String> IdDataRecord = new ArrayList<String>();
				IdDataRecord.add(listToFind.get(s).getTime());
				IdDataRecord.add(listToFind.get(s).getId());

				List <MacSignal> macSignal= new ArrayList<MacSignal>();
				for(int x=0; x<listToFind.get(s).getListOfWifi().size(); x++){
					String tempMac= listToFind.get(s).getListOfWifi().get(x).getMac();
					MacSignal temp= new MacSignal(listToFind.get(s).getListOfWifi().get(x).getRssi(), tempMac);
					List<Sample> tempList= hashMap.get(tempMac);
					if(tempList!=null){
						sampleSet.addAll(tempList);
					}
					macSignal.add(temp);
				}

				Location location= Weight.findLocation2(sampleSet,macSignal,num);

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
