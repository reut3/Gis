package MyPack;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import javax.swing.*;  

/**
 * class filter- filters id list <br>
 * has 5 function: equalTime function, equalId function, equalAltLon function, whichFilter function, filters function
 *
 */

public class filter {
	static JFrame f;  


	/**
	 * 
	 * @param time1
	 * @param time2
	 * @return the lines that their dates between
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
	 * 
	 * @param wantedId
	 * @return the lines with the same id
	 */
	public static Predicate<Sample> equalId(String wantedId) {
		return p -> p.getId().equals(wantedId);
	}

	/**
	 * 
	 * @param lat1
	 * @param lon1
	 * @return the lines with the same lat and lon
	 */
	public static Predicate<Sample> equalAltLon(int lat1, int lon1) {

		return p -> ( p.getLocation().getLat().getCord()==lat1 && p.getLocation().getLon().getCord()==lon1 );
		
	}
	
	
	
	public static Predicate<Sample> none() {

		return p->(true);
		
	}
	
	

	/**
	 * ask the user which filter he want to use, and then filter according to the answer
	 * @param list
	 * @return the wanted details according to the request
	 */
	public static  List<Sample> whichFilter(List<Sample> list){
		f=new JFrame(); 
		boolean continueAsk= false;
		while(continueAsk==false){

		String filterKind=JOptionPane.showInputDialog(f,"Choose filter: id/time/location/none"); 
		if(filterKind.equals("location")){
			int lat=-30;
			int lon=-10;
			try{
			String locationLat=JOptionPane.showInputDialog(f,"Enter lat");   
			String locationLon=JOptionPane.showInputDialog(f,"Enter lon"); 
			 lat= (int)Double.parseDouble(locationLat);
			 lon= (int)Double.parseDouble(locationLon);
			}
			catch(Exception e)
			{
			 System.out.println(e);
			 System.out.println("Not in Format");	
			}
			f.dispose();
			continueAsk=true;
			return filters(list, equalAltLon(lat,lon));
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


	//filter function
	/**
	 * 
	 * @param Id
	 * @param predicate
	 * @return collection of id according to the filter
	 */
	public static List<Sample> filters (List<Sample> Id, Predicate<Sample> predicate) {
		return Id.stream().filter( predicate ).collect(Collectors.<Sample>toList());
	}



}
