package Tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Location.Location;

public class LocationTest {


	Location a;
	Location b;
	String c1,c2,c3;
	@Before
	public void SetUp()
	{
	double start = 1;
	double end = 50;
	c1 = start + ( new Random().nextDouble() * (end - start))+"";
	c2 = start + ( new Random().nextDouble() * (end - start))+"";
	c3 = start + ( new Random().nextDouble() * (end - start))+"";
	a = new Location(c1,c2,c3);
	b = new Location(c1,c2,c3);
	}
		@Test
		public void equalTest() {
			assertTrue(a.equal(b));
		}

}

