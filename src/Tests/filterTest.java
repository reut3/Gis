package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MyPack.Filter;
import MyPack.Sample;
import MyPack.wifi;

class filterTest {
List<Sample> beforFilter;
List<Sample> afterFilter;
List<wifi> listOfWifi=new ArrayList<wifi>();


	@BeforeEach
	void setUp() throws Exception {
		String mac= getSaltString();
		String ssid= getSaltString();
		//String rssi= getSaltString();
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

		afterFilter=new ArrayList<Sample>();	
		beforFilter=new ArrayList<Sample>();
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
	void testEqualTime() {
		afterFilter=Filter.filters(beforFilter, Filter.equalTime("1/5/1992 12:45:30", "6/5/1992 12:45:30"));
		assertTrue((afterFilter.size()==6));
	}

	@Test
	void testEqualId() {
		afterFilter=Filter.filters(beforFilter, Filter.equalId("reut"));
		assertTrue((afterFilter.size()==5));
	}

	@Test
	void testEqualAltLon() {
		afterFilter=Filter.filters(beforFilter, Filter.equalAltLon(3, 54));
		assertTrue((afterFilter.size()==5));
	}

	@Test
	void testNone() {
		afterFilter=Filter.filters(beforFilter, Filter.none());
		assertTrue((afterFilter.size()==10));
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
