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
	
	public static void saveBoard(GameBoard board, int turn) {
		List<String> lines = new LinkedList<>();
		lines.add(Integer.toString(turn));
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
	
	public static Archive loadBoard(){
		
		Path path = Paths.get(FileName);
		try {
			List<String> inputs = Files.readAllLines(path);
			return Archive.makeArchive(inputs);
		} catch (IOException e) {
			System.out.println("Cannot read archive");
			return null;
		}
	}
	
}
