package MyPack;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import javax.swing.*;  


public class filter {
	JFrame f;  


	//gets the lines that their dates between
	public static Predicate<Id> equalTime(String time1,String time2) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		try{
//			Date d1= format.parse(time1);
//			Date d2= format.parse(time2);
//
//			return p -> (p.GetTime().compareTo(d1)>0 && p.GetTime().compareTo(d2)<0);
//		}
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
			System.out.println(d1+" "+ d2);
			return p -> ((p.GetTime().compareTo(d1)>0 && p.GetTime().compareTo(d2)<0) || p.GetTime().equals(d1)|| p.GetTime().equals(d2));
		}
		catch(Exception e){
			e.printStackTrace();
			return p ->true;	

		}	

	}

	//gets the lines with the same id
	public static Predicate<Id> equalId(String wantedId) {
		return p -> p.Id.equals(wantedId);
	}


	//gets the lines with the same lat and lon
	public static Predicate<Id> equalAltLon(String lat1, String lon1) {

		int lat= (int)Double.parseDouble(lat1);
		int lon= (int)Double.parseDouble(lon1);

		return p -> ( p.GetLat()==lat && p.GetLon()==lon );

	}


	public List<Id> whichFilter(List<Id> list){
		f=new JFrame();  
		boolean continueAsk= false;
		while(continueAsk==false){

		String filterKind=JOptionPane.showInputDialog(f,"Choose filter: id/time/location"); 
		if(filterKind.equals("location")){
			String locationLat=JOptionPane.showInputDialog(f,"Enter lat");   
			String locationLon=JOptionPane.showInputDialog(f,"Enter lon");  
			f.dispose();
			continueAsk=true;
			return filters(list, equalAltLon(locationLat,locationLon));
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
	}
		return list;
	}


	//filter function
	public static List<Id> filters (List<Id> Id, Predicate<Id> predicate) {
		return Id.stream().filter( predicate ).collect(Collectors.<Id>toList());
	}



}
