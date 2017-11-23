package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MyPack.Location;

class LocationTest {
Location a;
Location b;
String c1,c2,c3;
@BeforeEach
void SetUp()
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
	void equalTest() {
		assertTrue(a.equal(b));
	}

}
