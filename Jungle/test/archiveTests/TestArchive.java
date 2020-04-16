package archiveTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import archive.ArchiveLine;
import board.Animal;
import board.Box;
import board.Enumerations.Landscape;
import board.Enumerations.Rank;

class TestArchive {

	@Test
	void testArchiveLine() {
		Animal animal = new Animal(Rank.cat);
		animal.setSide(0);
		Box box = new Box(Landscape.land);
		box.setAnimal(animal);
		box.setX(2);
		box.setY(4);
		
		String line = ArchiveLine.MakeLine(box);
		String expected = "cat,0,2,4";
		assertEquals(expected,line);
	}
	
	@Test
	void testArchiveParse() {
		String expected = "dog,1,3,2";
		ArchiveLine aline = ArchiveLine.ParseLineForTesting(expected);
		assertEquals(expected, aline.toLine());
	}

}
