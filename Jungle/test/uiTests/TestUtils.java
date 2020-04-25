package uiTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ui.*;

public class TestUtils {
	
	@Test
	void testPosition() {
		Position p1 = new Position(10,20);
		Position p2 = new Position(5,5);
		Position p3 = new Position(10,20);
		Assert.assertTrue(p1.isFurther(p2));
		Assert.assertTrue(p2.isHigher(p3));
		Assert.assertTrue(p1.equals(p3));
	}

}
