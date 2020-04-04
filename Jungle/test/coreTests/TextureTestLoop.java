package coreTests;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL;

import board.Box;
import board.BoxType;
import core.*;
import ui.BackgroundRenderer;
import ui.BoardRenderer;
import ui.Position;
import ui.Texture;

public class TextureTestLoop implements GameLoop {
	
	
	Box boxNormal;
	Box boxDen;
	Box boxWater;
	Box boxTrap;
	
	List<Box> boxList = new LinkedList<Box>();
	
	
	
	@Override
	public void start() {
		boxNormal = new Box(new Position(200,200), BoxType.NORMAL);
		boxDen = new Box(new Position(400,200), BoxType.DEN);
		boxWater = new Box(new Position(800,200), BoxType.WATER);
		boxTrap = new Box(new Position(1000,200), BoxType.TRAP);	
		boxList.add(boxNormal);
		boxList.add(boxDen);
		boxList.add(boxWater);
		boxList.add(boxTrap);
		
		
	}

	@Override
	public void update() {
		// BackgroundRenderer.renderBackground();
		BoardRenderer.renderBoard(boxList);
	}

}
