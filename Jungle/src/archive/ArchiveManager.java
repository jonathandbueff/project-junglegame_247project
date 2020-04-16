package archive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import board.Box;
import board.GameBoard;

public class ArchiveManager {
	
	public static final String FileName = "./Archives/saving.txt";

	public static void saveBoard(GameBoard board) {
		List<String> lines = new LinkedList<>();
		for(Box[] row : board.getBoxes()) {
			for(Box box : row) {
				if(box.isPresent()) {
					String line = ArchiveLine.MakeLine(box);
					lines.add(line);
				}
			}
		}
		Path dir = Paths.get(FileName);
		try {
			Files.write(dir, lines);
		}
		catch(IOException e) {
			System.out.println("cannot write to archive");
		}
	}
	
	public static List<ArchiveLine> loadBoard(){
		List<ArchiveLine> lines = new LinkedList<>();
		
		Path path = Paths.get(FileName);
		try {
			List<String> inputs = Files.readAllLines(path);
			for(String line : inputs) {
				lines.add(ArchiveLine.ParseLine(line));
			}
		} catch (IOException e) {
			System.out.println("Cannot read archive");
		}
		return lines;	
	}
	
}
