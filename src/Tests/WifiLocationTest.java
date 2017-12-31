package Tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import DataBase.WifiLocation;
import Location.Location;

public class WifiLocationTest {
	 String signal;
	 Location location;
	 String c1,c2,c3;
	 WifiLocation wifiLocation;
	 WifiLocation wifiLocation1;
	 WifiLocation wifiLocation2;
	 WifiLocation wifiLocation3;
	@Before
	public void setUp() throws Exception {
		double start = 1;
		double end = 50;
		c1 = start + ( new Random().nextDouble() * (end - start))+"";
		c2 = start + ( new Random().nextDouble() * (end - start))+"";
		c3 = start + ( new Random().nextDouble() * (end - start))+"";
		location = new Location(c1,c2,c3);
		signal = "55";
		wifiLocation=new WifiLocation(signal,location);
		wifiLocation1=new WifiLocation(signal,location);
		wifiLocation2=new WifiLocation("80",location);
		wifiLocation3=new WifiLocation("30",location);

	}

	@Test
	public void testComperTo() {
		assertTrue(wifiLocation.compareTo(wifiLocation1)==0);
		assertTrue(wifiLocation.compareTo(wifiLocation)==0);
		assertTrue(wifiLocation.compareTo(wifiLocation2)==-1);
		assertTrue(wifiLocation.compareTo(wifiLocation3)==1);
		
	}

}
