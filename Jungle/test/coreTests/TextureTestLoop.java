package coreTests;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL;

import board.Box;
import board.BoxType;
import board.Enumerations.Landscape;
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
		boxNormal = new Box(Landscape.land,new Position(200,200));
		boxDen = new Box(Landscape.den1,new Position(400,200));
		boxWater = new Box(Landscape.water,new Position(800,200));
		boxTrap = new Box(Landscape.trap1,new Position(1000,200));	
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
