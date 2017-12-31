package Tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Location.Cord;

public class CordTest {

	Cord a;
	Cord b;
		@Before
		public void setUp() throws Exception {
			double start = 1;
			double end = 50;
			b=new Cord("0");
			a =new Cord( start + ( new Random().nextDouble() * (end - start))+"");
			b.setCord(a.getCord());
		}

		@Test
	public void testEqualCord() {
			assertTrue(b.equalCord(a));
	}
}
