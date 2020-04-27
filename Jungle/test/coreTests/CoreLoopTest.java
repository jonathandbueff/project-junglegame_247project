package coreTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import core.*;

class CoreLoopTest {
	
	GameLoop newLoop = new CoreLoop();

	@Test
	void testInit() {		
		Assert.assertEquals(newLoop, CoreLoop.current);
	}
	
	@Test
	void testRun() {
		TestLoop test = new TestLoop();
		CoreLoop.setLoop(test);
		Assert.assertTrue(test.initilized);
		CoreLoop.current.update();
		Assert.assertTrue(test.updating);
	}

}
