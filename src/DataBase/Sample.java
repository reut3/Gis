package DataBase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Location.Location;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listOfWifi == null) ? 0 : listOfWifi.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((wifiAmount == null) ? 0 : wifiAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sample other = (Sample) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listOfWifi == null) {
			if (other.listOfWifi != null)
				return false;
		} else if (!listOfWifi.equals(other.listOfWifi))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (wifiAmount == null) {
			if (other.wifiAmount != null)
				return false;
		} else if (!wifiAmount.equals(other.wifiAmount))
			return false;
		return true;
	}
	
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








}
