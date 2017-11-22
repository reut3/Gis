package MyPack;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Id class<br>
 * has 7 Features: <br>
 * String Time;<br>
	String Id;<br>
	String Lat;<br>
	String Lon;<br>
	String Alt;<br>
	String WifiAmount;<br>
	List<wifi> ListOfWifi<br>
 * one constructor <br>
 * 4 functions: <br>
 * toString function, GetTime function, GetLon funtion, GetLat function. 
 */

public class Sample {
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
	 * Id constructor
	 * @param time
	 * @param id
	 * @param wifiAmount
	 * @param listOfWifi
	 */
	public Sample(String time, String id, String lat, String lon, String alt, String wifiAmount, List<wifi> listOfWifi) {
		CheckTime(time);
		this.id = id;
		location= new Location(lat, lon, alt);
		this.wifiAmount = wifiAmount;
		this.listOfWifi = listOfWifi;
	}


	/**
	 * get time by string and fix it to valid time
	 * @param time
	 */
	private void CheckTime(String time){
		String[] FixTime=time.split(" ");
		String[] FixDate=FixTime[0].split("/");
		if(FixDate[0].length()==4){
			this.time=FixDate[2]+"/"+FixDate[1]+"/"+FixDate[0]+" "+FixTime[1];
		}
		else
			this.time=time;
	}


	/**
	 * get time by string and fix it to valid time for kml file
	 * @param time
	 */
	public static String CheckTimeForKML(String time1){
		time1=time1.replace('-', '/');
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
	 * 
	 * @return fixed date 
	 */
	public Date GetTime() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date Date1=null;
		time= time.trim();

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


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getWifiAmount() {
		return wifiAmount;
	}


	public void setWifiAmount(String wifiAmount) {
		this.wifiAmount = wifiAmount;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public List<wifi> getListOfWifi() {
		return listOfWifi;
	}


	public void setListOfWifi(List<wifi> listOfWifi) {
		this.listOfWifi = listOfWifi;
	}




}
