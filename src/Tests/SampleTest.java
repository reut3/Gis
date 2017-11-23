package Tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import MyPack.Sample;



public class SampleTest {

	Sample sample1;
	Sample sample2;
	String time;
	Date date;
	
	@Before
	public void SetUp() throws Exception{
		time= Sample.CheckTimeForKML("22-11-2017 22:47:30");
		
//		date. "2017/11/22T22:47:30";



	}

	@Test
	public void testCheckTime(){
		assertEquals( time, "2017/11/22T22:47:30");
	}

}







