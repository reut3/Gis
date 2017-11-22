package MyPack;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Id {
	String Time;
	String Id;
	String Lat;
	String Lon;
	String Alt;
	String WifiAmount;
	List<wifi> ListOfWifi=new ArrayList<wifi>();


	public String toString() {
		return  Time + " , " + Id + " , " + Lat +  " , " + Lon +  " , "  + Alt +  " , " 
				+ WifiAmount +  " , "  + ListOfWifi + "]\n";
	}
	

	public Id(String time, String id, String lat, String lon, String alt, String wifiAmount, List<wifi> listOfWifi) {
		CheckTime(time);
		Id = id;
		Lat = lat;
		Lon = lon;
		Alt = alt;
		WifiAmount = wifiAmount;
		ListOfWifi = listOfWifi;
	}
	private void CheckTime(String time){
     String[] FixTime=time.split(" ");
     String[] FixDate=FixTime[0].split("/");
     if(FixDate[0].length()==4){
    	 this.Time=FixDate[2]+"/"+FixDate[1]+"/"+FixDate[0]+" "+FixTime[1];
     }
     else
    	 this.Time=time;
	}


	@SuppressWarnings("finally")
	public Date GetTime() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date Date1=null;
		Time= Time.trim();

		try {
			if(Time.length()>1&&(Time.length()==16)){
			Date1 = format.parse((Time.substring(0, 16)+":00"));

			}
			else{
				Date1 = format.parse(Time);
			}
		} catch (ParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return Date1;
		}
	}

	public int GetLon(){
		int lon= (int)Double.parseDouble(Lon);
		return lon;
	}

	public int GetLat(){
		int lat= (int)Double.parseDouble(Lat);
		return lat;
	}
	

}
