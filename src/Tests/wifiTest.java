package Tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import MyPack.wifi;

public class wifiTest {
	wifi wifi1;
	wifi wifi2;

	@Before
	public void SetUp() throws Exception{

		String mac= getSaltString();
		String ssid= getSaltString();
		String rssi= getSaltString();
		Random rand = new Random();
		String  channel = (rand.nextInt(1000) + 1)+"";

		wifi1= new wifi(ssid,mac,rssi,channel);
		wifi2= new wifi(ssid,mac,rssi,channel);

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
	public void testCompareTo(){
		assertTrue(wifi1.compareTo(wifi2)==0);
	}
	
	@Test
	public void testconvertChannel(){
		assertTrue(wifi.convertChannel(wifi1.getChannel())
				.equals(wifi.convertChannel(wifi2.getChannel())));
	}

}

















