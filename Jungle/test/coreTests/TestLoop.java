package coreTests;

import core.GameLoop;

public class TestLoop implements GameLoop {
	
	public boolean initilized;
	public boolean updating;

	@Override
	public void start() {
		initilized = true;
	}

	@Override
	public void update() {
		updating = true;
	}

}
