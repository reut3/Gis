package Filter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import javax.swing.*;

import DataBase.Sample;
import FileTools.Checks;

/**
 * class filter- filters list of Samples <br>
 * has the functions:<br>
 * equalTime function, equalId function, equalAltLon function, 
 * filters function, MACfilter function, filterAnd function
 *
 */

public class filter {


	/**
	 * the function get 3 Strings: time1, time2  and Not
	 * if Not is "0" return the samples from the list that their time is between time1 and time1
	 * if Not is "1" return the samples from the list that their time is Not between time1 and time1
	 * @param Not- string "0"/"1"
	 * @param time1
	 * @param time2
	 * @return the filtered list of Samples
	 */
	public static Predicate<Sample> equalTime(String Not,String time1,String time2) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		time1= time1.trim();
		time2= time2.trim();
		time1=Checks.CheckTimeForWEB(time1);
		time2=Checks.CheckTimeForWEB(time2);
		System.out.println("the time is"+time1);
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
			if(Not.equals("0"))
				return p -> ((p.GetTime().compareTo(d1)>0 && p.GetTime().compareTo(d2)<0) || p.GetTime().equals(d1)|| p.GetTime().equals(d2));
				else
					return p -> !((p.GetTime().compareTo(d1)>0 && p.GetTime().compareTo(d2)<0) || p.GetTime().equals(d1)|| p.GetTime().equals(d2));

		}
		catch(Exception e){
			e.printStackTrace();
			return p ->true;	

		}	

	}

	/**
	 * the function get id and String Not
	 * if Not is "0" return the samples from the list that their id is identical
	 * if Not is "1" return the samples from the list that their id is Not identical
	 * @param wantedId
	 * @param Not- string "0"/"1"
	 * @return the filtered list of Samples
	 */
	public static Predicate<Sample> equalId(String Not, String wantedId) {
		if(Not.equals("0"))
			return p -> p.getId().equals(wantedId);
			else
				return p -> !(p.getId().equals(wantedId));
	}


	/**
	 * the function get 2 lat and 2 lon
	 * return the samples from the list that their lat, lon are in the rectangle created from:
	 * lat1 lon1, lat2 lon2
	 * if Not is "0" return the samples from the list that their lat, lon are in the rectangle created from:
	 * lat1 lon1, lat2 lon2
	 * if Not is "1" return the samples from the list that their lat, lon are outside the rectangle created from:
	 * lat1 lon1, lat2 lon2
	 * @param Not- string "0"/"1"
	 * @param lat1
	 * @param lon1
	 * @param radius
	 * @return the filtered list of Samples
	 */
	public static Predicate<Sample> equalAltLon(String Not,String lat1, String lon1, String lat2, String lon2) {
		double x1,y1,x2,y2;
		x1=Double.parseDouble(lat1);
		y1=Double.parseDouble(lon1);
		x2=Double.parseDouble(lat2);
		y2=Double.parseDouble(lon2);
		if(Not.equals("0"))
			return p -> (p.getLocation().getLat().getCord()>=x2 && p.getLocation().getLat().getCord()<=x1 && p.getLocation().getLon().getCord()>=y2 && p.getLocation().getLon().getCord()<=y1);
			else
				return p -> (p.getLocation().getLat().getCord()>=x2 && p.getLocation().getLat().getCord()<=x1 && p.getLocation().getLon().getCord()>=y2&& p.getLocation().getLon().getCord()<=y1);


	}


	/**
	 * The function filter the set of samples- takes sample if stands in the conditions of the 
	 * two predicates. 
	 * @param sample
	 * @param predicate
	 * @param predicate1
	 * @return
	 */
	public static Set<Sample> filterAnd (Set<Sample> sample, Predicate<Sample> predicate, Predicate<Sample> predicate1) {
		return sample.stream().parallel().filter( predicate ).filter(predicate1).collect(Collectors.<Sample>toSet());
	}


	/**
	 * The function filter the set of samples- takes sample if stands in the conditions of the predicate.
	 * @param sample- set of samples
	 * @param predicate
	 * @return filtered set of samples
	 */
	public static Set<Sample> filters (Set<Sample> sample, Predicate<Sample> predicate) {
		return sample.stream().parallel().filter( predicate ).collect(Collectors.<Sample>toSet());
	}


	/**
	 * the function check if there are double macs in every sample's wifi list<br>
	 * if there is- delete the smaller according to the ssid
	 * @param list of samples named list
	 * @return list of samples without duplicated macs
	 */
	public static List<Sample> MACfilter(List<Sample> list){

		HashMap<String, int[]> hashMap= new HashMap<String, int[]>();
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				String key= list.get(i).getListOfWifi().get(j).getMac();
				int toAdd[]= {i,j};
				if (hashMap.get(key) == null) {
					hashMap.put(key, toAdd);
				}
				else{
					int a= hashMap.get(key)[0];
					int b= hashMap.get(key)[1];
					if(list.get(i).getListOfWifi().get(j).compareTo(list.get(a).getListOfWifi().get(b))<=0){
						list.get(a).getListOfWifi().get(b).setMac(null);
						int temp[]={i,j};
						hashMap.replace(key, temp);

					}
					else{
						list.get(i).getListOfWifi().get(j).setMac(null);		
					}
				}

			}
		}
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<list.get(i).getListOfWifi().size(); j++){
				if(list.get(i).getListOfWifi().get(j).getMac()==null){
					list.get(i).getListOfWifi().remove(j);
					j--;
				}
			}
		}
		return list;
	}





}
