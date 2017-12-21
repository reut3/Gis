package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Location.CordPI;
import Location.Location;

public class CordPITest {
	CordPI CordPI;
	@Before
	public void setUp() throws Exception {
		Location location=new Location("34.453","55","34.556");
		Double Pi=3.145;
		CordPI=new CordPI(location,Pi);
		
	}

	@Test
	public void testcompareTo() {
		Location location=new Location("34.453","55","34.556");
		Double Pi=3.145;
		CordPI CordPI2=new CordPI(location,Pi);
		assertTrue(CordPI.compareTo(CordPI2)==0);
	}


}
