package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DataBase.Sample;
import DataBase.wifi;



public class SampleTest {

	Sample sample1;
	String time1;
	Date date;
	
	@Before
	public void SetUp() throws Exception{
		time1= Sample.CheckTimeForKML("22/11/2017 22:47:30");
		
		List<wifi> wifi1= new ArrayList<wifi>();
		sample1= new Sample("22/11/2017 22:47:30", "MMB29K.A520FXXU1AQF3", "32.16755862", "34.8099448", "61.70000076", "9", wifi1); 


	}

	@Test
	public void testCheckTime(){
		assertEquals( time1, "2017-11-22T22:47:30");
	}
	@Test
	public void testGetTim(){
		assertTrue((sample1.GetTime()+"").equals("Wed Nov 22 22:47:30 IST 2017"));
	}

}







