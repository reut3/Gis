package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DataBase.DataBase;
import DataBase.Sample;
import DataBase.wifi;

public class DataBaseTest {

	Set<Sample> beforFilter;
	Set<Sample> afterFilter;
	List<wifi> listOfWifi=new ArrayList<wifi>();
	DataBase Datbase =new DataBase();


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
			if(i%2==0) {
				beforFilter.add(new Sample(i+"/5/1992 12:45:30","inon","3.54","54.650","35.246","0",listOfWifi));
				beforFilter.add(new Sample(i+"/5/1992 12:45:30","inon","3.54","54.650","35.246","0",listOfWifi));
			}
			else
			{
				beforFilter.add(new Sample(i+"/5/1992 12:45:30","reut",""+i+10,"54.650","35.246","0",listOfWifi));
			}

		}
		Datbase.add(beforFilter);
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
	public void addTest() {
		assertTrue(Datbase.FinalDataBase.size()==10);
		assertTrue(Datbase.FinalFilterDatabase.size()==10);
	}
	@Test
	public void constractorTest() {
		DataBase Database =new DataBase();
		assertTrue(Database.FinalDataBase.size()==0);
		assertTrue(Database.FinalFilterDatabase.size()==0);
	}
	@Test
	public void RemoveAllTest() {
		Datbase.RemoveAll();
		assertTrue(Datbase.FinalDataBase.size()==0);
		assertTrue(Datbase.FinalFilterDatabase.size()==0);
	}


}
