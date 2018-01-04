package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import DataBase.Sample;
import DataBase.wifi;
import Filter.filter;

public class filterTest {
Set<Sample> beforFilter;
Set<Sample> afterFilter;
List<wifi> listOfWifi=new ArrayList<wifi>();


	@Before
	public void setUp() throws Exception {
		String mac= getSaltString();
		String ssid= getSaltString();
		Random rand = new Random();
		String  channel = (rand.nextInt(1000) + 1)+"";

	wifi wifi1= new wifi(ssid,mac,rand.nextInt(100) + 1+"",channel);
	wifi wifi2= new wifi(ssid,mac+"9",rand.nextInt(100) + 1+"",channel);
	for(int i=0;i<10;i++)
	{
		if(i%2==0)
		listOfWifi.add(wifi1);
		else
			listOfWifi.add(wifi2);	
	}

		afterFilter=new HashSet<Sample>();	
		beforFilter=new HashSet<Sample>();
		for (int i=1;i<11;i++)
		{
          if(i%2==0)
        	beforFilter.add(new Sample(i+"/5/1992 12:45:30","inon","3.54","54.650","35.246","0",listOfWifi));
          else
          {
        	  beforFilter.add(new Sample(i+"/5/1992 12:45:30","reut",""+i+10,"54.650","35.246","0",listOfWifi));
          }
  
		}
	}




public static String getSaltString() {
	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < 18) { // length of the random string.
		int index = (int) (rnd.nextFloat() * SALTCHARS.length());
		salt.append(SALTCHARS.charAt(index));
	}
	String saltStr = salt.toString();
	return saltStr;

}


	@Test
	public void testEqualTime() {
		afterFilter=filter.filters(beforFilter, filter.equalTime("0","1/5/1992 12:45:30", "6/5/1992 12:45:30"));
		assertTrue((afterFilter.size()==6));
		afterFilter=filter.filters(beforFilter, filter.equalTime("1","1/5/1992 12:45:30", "6/5/1992 12:45:30"));
		assertTrue((afterFilter.size()==4));
	}

	@Test
	public void testEqualId() {
		afterFilter=filter.filters(beforFilter,filter.equalId("1","reut"));
		assertTrue((afterFilter.size()==5));
		afterFilter=filter.filters(beforFilter,filter.equalId("0","reut"));
		assertTrue((afterFilter.size()==5));
	}

	@Test
	public void testEqualAltLon() {
		afterFilter=filter.filters(beforFilter, filter.equalAltLon("1","3.54","54.650","3.54","54.650"));
		assertTrue((afterFilter.size()==5));
		afterFilter=filter.filters(beforFilter, filter.equalAltLon("0","3.54","54.650","3.54","54.650"));
		assertTrue((afterFilter.size()==5));
	}
	
	@Test
	public void FilterAndtest() {
		afterFilter=filter.filterAnd(beforFilter, filter.equalAltLon("1","3.54","54.650","3.54","54.650"),filter.equalAltLon("1","3.54","54.650","3.54","54.650"));
		assertTrue((afterFilter.size()==5));
		afterFilter=filter.filterAnd(beforFilter, filter.equalAltLon("1","3.54","54.650","3.54","54.650"),filter.equalTime("1","1/5/1992 12:45:30", "6/5/1992 12:45:30"));
		assertTrue((afterFilter.size()==2));
		
	}



//	@Test
//	void testMACfilter() {
//		int count=0;
//		System.out.println(beforFilter.toString());
//		afterFilter=Filter.MACfilter(beforFilter);
//	
//		for(Sample u:afterFilter)
//		{
//			for(wifi s:u.getListOfWifi())
//			{
//				count++;
//			}
//		}
//		System.out.println(count+"--------------");
//		
//	}

}

