package MyPack;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import javax.swing.*;  

/**
 * class filter- filters list of Samples <br>
 * has the functions:<br>
 * equalTime function, equalId function, equalAltLon function, none function, 
 * whichFilter function, filters function, MACfilter function
 *
 */

public class filter {
	static JFrame f;  


	/**
	 * the function gets two dates return the samples from the list that their dates between
	 * @param time1
	 * @param time2
	 * @return the filterd list of Samples
	 */
	public static Predicate<Sample> equalTime(String time1,String time2) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		time1= time1.trim();
		time2= time2.trim();

		try{
			Date d1;
			Date d2;
			if(time1.length()>1&&(time1.length()==16)){
				d1 = format.parse((time1.substring(0, 16)+":00"));
			}
			else{
				d1= format.parse(time1);
			}
			
			if(time2.length()>1&&(time2.length()==16)){
				d2 = format.parse((time2.substring(0, 16)+":00"));
			}
			else{
				d2= format.parse(time2);
			}
			return p -> ((p.GetTime().compareTo(d1)>0 && p.GetTime().compareTo(d2)<0) || p.GetTime().equals(d1)|| p.GetTime().equals(d2));
		}
		catch(Exception e){
			e.printStackTrace();
			return p ->true;	

		}	

	}

	/**
	 * the function get id and return the samples from the list that their id is identical
	 * @param wantedId
	 * @return the filterd list of Samples
	 */
	public static Predicate<Sample> equalId(String wantedId) {
		return p -> p.getId().equals(wantedId);
	}
	
	
	
	
	//reut
	public static Predicate<Sample> equalMac(List<MacSignal> macs) {

		
		
		return p -> p.contain(p.getListOfWifi(),macs);// take the Sample if contains a mac

	}
	

	
	
	

	/**
	 * the function get lat and lon and radius, 
	 * return the samples from the list that their lat, lon are in the radius from the point of lat,lon
	 * @param lat1
	 * @param lon1
	 * @param radius
	 * @return the filterd list of Samples
	 */
	public static Predicate<Sample> equalAltLon(double lat1, double lon1, double radius) {
		return p -> Point2D.distance(lat1,lon1,
				p.getLocation().getLat().getCord(), p.getLocation().getLon().getCord()) <= radius;
	}
		
	/**
	 * 
	 * @return the list of Samples with no filter affect
	 */
	public static Predicate<Sample> none() {

		return p->(true);
		
	}
	
	/**
	 * ask the user which filter he want to use, and then filter according to the answer
	 * @param list
	 * @return the wanted list of samples according to the request
	 */
	public static  List<Sample> whichFilter(List<Sample> list){
		f=new JFrame(); 
		boolean continueAsk= false;
		while(continueAsk==false){

		String filterKind=JOptionPane.showInputDialog(f,"Choose filter: id/time/location/none"); 
		if(filterKind.equals("location")){
			double lat= 10.8;
			double lon= 10.8;
			double radius= 10.8;
			try{
			String locationLat=JOptionPane.showInputDialog(f,"Enter lat");   
			String locationLon=JOptionPane.showInputDialog(f,"Enter lon"); 
			String radiusLocation=JOptionPane.showInputDialog(f,"Enter radius"); 

			 lat= Double.parseDouble(locationLat);
			 lon= Double.parseDouble(locationLon);
			 radius= Double.parseDouble(radiusLocation);
			}
			catch(Exception e)
			{
			 System.out.println(e);
			 System.out.println("Not in Format");	
			}
			f.dispose();
			continueAsk=true;
			return filters(list, equalAltLon(lat,lon, radius));
		}
		
		else if(filterKind.equals("id")){
			String name=JOptionPane.showInputDialog(f,"Enter time id"); 
			f.dispose();
			continueAsk=true;
			return filters(list, equalId(name));
		}
		else if(filterKind.equals("time")){
			String time1=JOptionPane.showInputDialog(f,"Enter start time dd/MM/yyyy HH:mm:ss");
			String time2=JOptionPane.showInputDialog(f,"Enter end time dd/MM/yyyy HH:mm:ss");
			f.dispose();
			continueAsk=true;
			return filters(list, equalTime(time1, time2));
		}
		else if(filterKind.equals("none")){
			f.dispose();
			continueAsk=true;
			return filters(list, none());
		}
	}
		return list;
	}

	/**
	 * filter function
	 * @param list of samples named sample
	 * @param predicate
	 * @return l of Samples according to the filter
	 */
	public static List<Sample> filters (List<Sample> sample, Predicate<Sample> predicate) {
		return sample.stream().filter( predicate ).collect(Collectors.<Sample>toList());
	}
	
	
	/**
	 * the function check if there are double macs in every sample's wifi list<br>
	 * if there is- delete the smaller according to the ssid
	 * @param list of samples named list
	 * @return list of samples without doplicated macs
	 */
	public static List<Sample> MACfilter(List<Sample> list){
		List <String> macs= new ArrayList<String>();
		String tempMac="";
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				tempMac= list.get(i).getListOfWifi().get(j).getMac();
				macs.add(i+ " "+ j+" "+ tempMac);
			}
		}
		
		for(int i=0; (!macs.isEmpty() && !macs.get(i).equals("null") && i<macs.size()); i++){
			String strongest= macs.get(i);
			for(int j=i+1; (!macs.get(j).equals("null") && j<macs.size()-2); j++){

				String[] mac1=strongest.split(" ");
				String[] mac2=macs.get(j).split(" ");		

				if(mac1[2].equals(mac2[2])){
					int sample1= Integer.parseInt(mac1[0]);
					int wifi1= Integer.parseInt(mac1[1]);

					int sample2= Integer.parseInt(mac2[0]);
					int wifi2= Integer.parseInt(mac2[1]);
					if(list.get(sample1).getListOfWifi().get(wifi1).compareTo
							(list.get(sample2).getListOfWifi().get(wifi2))
							==-1){
						strongest= macs.get(j);
						macs.set(i, "null");
						int wifiAmount= Integer.parseInt(list.get(sample1).getWifiAmount())-1;
						list.get(sample1).setWifiAmount(wifiAmount+"");
						list.get(sample1).getListOfWifi().set(wifi1, new wifi());	
					}
					else{
						macs.set(j, "null");
						int wifiAmount= Integer.parseInt(list.get(sample2).getWifiAmount())-1;
						list.get(sample2).setWifiAmount(wifiAmount+"");
						list.get(sample2).getListOfWifi().set(wifi2, new wifi());	
					}

				}
			}
		}
		return list;

	}





}
