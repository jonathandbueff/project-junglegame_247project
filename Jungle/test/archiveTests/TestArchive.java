package archiveTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import archive.Archive;
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
	
	@Test
	void testArchive() {
		List<String> input = new LinkedList<>();
		input.add("0");
		input.add("dog,1,3,4");
		input.add("mouse,0,5,5");
		Archive archive = Archive.makeArchiveForTesting(input);
		assertEquals(0,archive.getTurn());
		List<ArchiveLine> lines = new LinkedList<>();
		lines.addAll(archive.getLines());
		ArchiveLine first = lines.get(0);
		ArchiveLine second = lines.get(1);
		assertEquals(Rank.dog, first.getAnimal().getRank());
		assertEquals(1, first.getAnimal().getSide());
		assertEquals(3, first.getRow());
		assertEquals(4, first.getCol());
		assertEquals(Rank.mouse, second.getAnimal().getRank());
		assertEquals(0, second.getAnimal().getSide());
		assertEquals(5, second.getRow());
		assertEquals(5, second.getCol());
	}

}
