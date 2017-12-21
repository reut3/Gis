package FileTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DataBase.Sample;
import DataBase.wifi;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
 * kmlFile class contains the functions: <br>
 * readCSV function- reads the csv file, put it into Sample list, call filter function that return filterd list,
 * call write function with the Sample list <br>
 * write function- write the Sample list to kml file
 */
public class KmlFile {

	/**
	 * the function reads csv file and put all the info in a Sample list<br>
	 * call filter function, that return filtered list of Samples<br>
	 * finally calls to write function, that write the list to kml file
	 * @param Location of the csv file
	 */
	public static void readCSV(String Location){
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

			//Read the CSV file records starting from the second record to skip the header
			for (int j = 1; j < csvRecords.size(); j++) {
				CSVRecord record = csvRecords.get(j);
				List<wifi> listOfWifi= new ArrayList<wifi>();
				for( int i=6; i<record.size(); ){
					listOfWifi.add(new wifi(record.get(i), record.get(i+1), record.get(i+3), record.get(i+2)));
					i+=4;
				}
				Sample sample= new Sample(record.get(0), record.get(1),record.get(2),record.get(3),record.get(4),
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

		
		//choose a filter
		List<Sample> list= filter.whichFilter(SampleList);	
		
		//delete double macs
		list= filter.MACfilter(list);
		
		//write the list to kml file
		write("kmlFile", list);

		
	}

	/**
	 * the function write the Sample list to a kml file<br>
	 * the function is called from the readCSV function
	 * @param fileName
	 * @param list of Samples named list
	 */
	private static void write(String fileName, List<Sample> list){
		final Kml kml = new Kml();
		Document document = kml.createAndSetDocument().withName("MyMarkers");
		for(Sample I:list){
			for(int i=0; i< I.getListOfWifi().size(); i++){
				String time= Sample.CheckTimeForKML(I.getTime());
				TimeStamp t = new TimeStamp();
				t.setWhen(time);
				document.createAndAddPlacemark().withName(I.getId()).withOpen(Boolean.TRUE).withTimePrimitive(t).withDescription(description(I.getListOfWifi().get(i))).
				createAndSetPoint().addToCoordinates((I.getLocation().getLon().getCord()), (I.getLocation().getLat().getCord()));
			}
		}
		try {
			kml.marshal(new File(fileName+".kml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * the function get wifi and return wifi's features written in a certain way<br>
	 * the function called form the write kml function
	 * @param wifi w
	 * @return string: wifi's description 
	 */
	private static String description(wifi w){
		String Descripion="mac: "+ w.getMac()+ "\nSSID: "+ w.getSsid() + " \nRSSI: "+ w.getRssi() +" \nChannel: "+w.getChannel();	
		return Descripion;
	}

}