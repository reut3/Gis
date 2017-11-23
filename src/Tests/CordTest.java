package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MyPack.Cord;

class CordTest {
Cord a;
Cord b;
	@BeforeEach
	void setUp() throws Exception {
		double start = 1;
		double end = 50;
		b=new Cord("0");
		a =new Cord( start + ( new Random().nextDouble() * (end - start))+"");
		b.setCord(a.getCord());
	}

	@Test
	void testEqualCord() {
		assertTrue(b.equalCord(a));
	}

}
