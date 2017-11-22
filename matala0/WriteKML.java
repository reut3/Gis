package MyPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteKML {

	public static void write(String fileName, List<Id> list){

		FileWriter kmlFile=null;
		try {
			kmlFile= new FileWriter(fileName);
			kmlFile.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			kmlFile.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>\n<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>\n<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\n<Folder>\n<name>Wifi Networks</name>\n");

			for(Id I:list){
				kmlFile.append("<Placemark>\n");
				kmlFile.append("<name><![CDATA["+I.Id+"]]></name>\n");
				kmlFile.append("<description>");
				kmlFile.append("<br/>Date: <b>"+I.Time +"</b>");
				kmlFile.append("<br/>ID: <b><![CDATA["+I.Id+"]]></b>");
				kmlFile.append("<br/>Lat: <b>"+I.Lat +"</b>");
				kmlFile.append("<br/>Lon: <b>"+I.Lon +"</b>");
				kmlFile.append("<br/>Alt: <b>"+I.Alt +"</b>\n");
				kmlFile.append("<br/>Wifi networks: <b>"+I.WifiAmount +"</b>\n");
				kmlFile.append("<table border=\"1\">");
				kmlFile.append("<tr>\n");
				kmlFile.append("<th>SSID</th>\n");
				kmlFile.append("<th>Mac</th>\n");
				kmlFile.append("<th>Frequency</th>\n");
				kmlFile.append("<th>Signal</th>\n");
				kmlFile.append("</tr>\n");
				for(int i=0; i< I.ListOfWifi.size(); i++){
					wifi w= I.ListOfWifi.get(i);
					kmlFile.append("<tr>\n");
					kmlFile.append("<td><![CDATA["+w.SSID+"]]></td>\n");
					kmlFile.append("<td><![CDATA["+w.MAC+"]]></td>\n");
					kmlFile.append("<td>"+w.Channel+"</td>\n");
					kmlFile.append("<td>"+w.RSSI+"</td>\n");
					kmlFile.append("</tr>\n");
				}
				kmlFile.append("</table>\n");
				if(!I.ListOfWifi.isEmpty() && I.ListOfWifi.get(0).RSSI.compareTo("-70")<0){
					kmlFile.append("</description><styleUrl>#green</styleUrl>\n");
				}	
				else if(!I.ListOfWifi.isEmpty() && I.ListOfWifi.get(0).RSSI.compareTo("-70")>0 && I.ListOfWifi.get(0).RSSI.compareTo("-80")<0){
					kmlFile.append("</description><styleUrl>#yellow</styleUrl>\n");
				}	
				else{
					kmlFile.append("</description><styleUrl>#red</styleUrl>\n");
				}
				kmlFile.append("<Point>\n");
				kmlFile.append("<coordinates>"+I.Lon+","+I.Lat+"</coordinates></Point>\n");
				kmlFile.append("</Placemark>\n");
			}

			kmlFile.append("</Folder>\n</Document></kml>");// סגירת קובץ

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				kmlFile.flush();
				kmlFile.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

	}


	public void readCSV(String Location){
		filter filter= new filter();

		File csvFile= new File(Location);

		BufferedReader br = null;
		int linesNum=0;
		String line = "";
		String cvsSplitBy = ",";

		//find how many lines the file contains
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((br.readLine()) != null) {
				linesNum++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//end of finding how many lines.


		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Id> list= new ArrayList<Id>();


		//read and put into matrix
		for(int m=0; m<linesNum; m++) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String strar[] = line.split(cvsSplitBy);//get contents of line as an array
			if(!("Time").equals(strar[0])){	
				List<wifi> listOfWifi= new ArrayList<wifi>();
				for(int j=6; strar[6]!=null && strar[j]!=null && j<strar.length-4; ){
					listOfWifi.add(new wifi(strar[j], strar[j+1], strar[j+2], strar[j+3]));
					j+=4;	
				}
				listOfWifi.add(new wifi(strar[strar.length-4], strar[strar.length-3], strar[strar.length-2], strar[strar.length-1]));

//				System.out.println(listOfWifi);
				list.add(new Id(strar[0], strar[1], strar[2], strar[3], strar[4], strar[5], listOfWifi));
			}

			//one line
		}//for end 
		List<Id> listToKml= filter.whichFilter(list);
		write("KMLfile.kml", listToKml);
	}

}