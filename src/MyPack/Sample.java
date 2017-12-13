package MyPack;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Sample class<br>
 * create sample with 5 Features: <br>
 * String time;<br>
 * String id;<br>
 * Location location;<br>
 * String WifiAmount;<br>
 * List<wifi> ListOfWifi<br>
 * one constructor <br>
 * 4 functions: <br>
 * toString function, CheckTime function, CheckTimeForKML function, GetTime function. 
 * getters and setters functions to the features
 */

public class Sample {

	//sample features:
	private String time;
	private String id;
	private String wifiAmount;
	private Location location;
	private List<wifi> listOfWifi=new ArrayList<wifi>();






	@Override
	public String toString() {
		return "Sample [Time=" + time + ", Id=" + id + ", WifiAmount=" + wifiAmount + ", location=" + location
				+ ", ListOfWifi=" + listOfWifi + "]";
	}


	/** 
	 * Sample Explicit constructor
	 */
	public Sample(String time, String id, String lat, String lon, String alt, String wifiAmount, List<wifi> listOfWifi) {
		CheckTime(time);
		this.id = id;
		location= new Location(lat, lon, alt);
		this.wifiAmount = wifiAmount;
		this.listOfWifi = listOfWifi;
	}


	/** 
	 * the function get time and switch day and year if necessary. the final format is: dd/mm/yy hh:mm:ss
	 * @param time
	 */
	private void CheckTime(String time){
		String[] SplitTime=time.split(" "); // split the time to an array
		String[] FixDate=SplitTime[0].split("/"); //split the year, month, day to an array
		if(FixDate[0].length()==4){ //if the year is first:
			this.time=FixDate[2]+"/"+FixDate[1]+"/"+FixDate[0]+" "+SplitTime[1]; //switch year and day
		}
		else
			this.time=time;
	}


	/**
	 * get time by string and fix it to valid time for kml file
	 * @param time
	 */
	public static String CheckTimeForKML(String time1){
		time1=time1.replace('-', '/'); //replact - to /
		String[] FixTime=time1.split(" ");
		String time= "";
		String[] FixDate=FixTime[0].split("/");
		if(FixDate[0].length()==4){
			time+= FixDate[0]+"-"+FixDate[1]+"-"+FixDate[2]+"T"+FixTime[1];
			return time;
		}

		else
			time+= FixDate[2]+"-"+FixDate[1]+"-"+FixDate[0]+"T"+FixTime[1];
		return time;
	}


	/**
	 * the function check that the date is valid for filter functions
	 * @return fixed date 
	 */
	public Date GetTime() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date Date1=null;
		time= time.trim(); //remove white space at the end

		try {
			if(time.length()>1&&(time.length()==16)){
				Date1 = format.parse((time.substring(0, 16)+":00"));

			}
			else{
				Date1 = format.parse(time);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
		return Date1;
	}


	/**
	 * 
	 * @return sample's time 
	 */
	public String getTime() {
		return time;
	}


	/**
	 * set sample's time
	 * @param time
	 */
	public void setTime(String time) {
		CheckTime(time);
	}


	/**
	 * 
	 * @return sample's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * set sample's id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return sample's wifiAmount
	 */
	public String getWifiAmount() {
		return wifiAmount;
	}

	/**
	 * set sample's wifiAmount
	 * @param wifiAmount
	 */
	public void setWifiAmount(String wifiAmount) {
		this.wifiAmount = wifiAmount;
	}

	/**
	 * 
	 * @return sample's location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * set sample's location
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @return sample's listOfWifi
	 */
	public List<wifi> getListOfWifi() {
		return listOfWifi;
	}

	/**
	 * set sample's listOfWifi
	 * @param listOfWifi
	 */
	public void setListOfWifi(List<wifi> listOfWifi) {
		this.listOfWifi = listOfWifi;
	}


	public boolean contain(List<wifi>list, List<MacSignal> macs){
		boolean answer=false;
		
		List<String> macList= new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			String mac= list.get(i).getMac();
			macList.add(mac);
		}
		for(int i=0; i<macs.size(); i++){
			if(macList.contains(macs.get(i).mac)){
				answer=true;
				break;
			}
		}
		return answer;
	}






}
