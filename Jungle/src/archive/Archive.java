package archive;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * An Archive is a document in the format:
 * turn (0/1) 
 * ArchiveLine #1
 * ArchiveLine #2
 * ...
 * 
 * @author teeli8
 *
 */

public class Archive {
	
	int turn; //whose turn it is
	Collection<ArchiveLine> lines;
	
	public Archive(int turn, Collection<ArchiveLine> lines) {
		this.turn = turn;
		this.lines = lines;
	}
	
	public Collection<ArchiveLine> getLines(){
		return this.lines;
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public static Archive makeArchive(List<String> inputs) {
		if(inputs.size()<=0) return null;
		
		try {
			Collection<ArchiveLine> lines = new LinkedList<>();
			String firstLine = inputs.get(0);
			int turn = Integer.parseInt(firstLine);
			
			List<String> rest = inputs.subList(1, inputs.size());
			for(String line : rest) {
				lines.add(ArchiveLine.ParseLine(line));
			}
			return new Archive(turn, lines);
			
		} catch (Exception e) {
			System.out.println("Illigal Archive");
			return null;
		}
		
	}
	
	
	/*
	 * None texture version for testing
	 */
	public static Archive makeArchiveForTesting(List<String> inputs) {
		if(inputs.size()<=0) return null;
		
		try {
			Collection<ArchiveLine> lines = new LinkedList<>();
			String firstLine = inputs.get(0);
			int turn = Integer.parseInt(firstLine);
			
			List<String> rest = inputs.subList(1, inputs.size());
			for(String line : rest) {
				lines.add(ArchiveLine.ParseLineForTesting(line));
			}
			return new Archive(turn, lines);
			
		} catch (Exception e) {
			System.out.println("Illigal Archive");
			return null;
		}
		
	}
}
