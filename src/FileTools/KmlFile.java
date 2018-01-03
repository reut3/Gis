package FileTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DataBase.Sample;
import DataBase.wifi;
import Filter.filter;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
 * kmlFile class contains the functions: <br>
 * write function- write the Sample list to kml file
 * description- get wifi object and return string with the wifi's info
 */
public class KmlFile {



	/**
	 * the function write the Sample list to a kml file<br>
	 * the function is called from the readCSV function
	 * @param fileName
	 * @param list of Samples named list
	 */
	public static void write(String fileName, Set<Sample> list){
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