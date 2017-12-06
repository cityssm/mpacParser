package ssm.mpacparser;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MPACParser {
	
	private final File yetfFile;
	
	private List<Outputter> outputters;
	
	public MPACParser (File yetfFile) {
		this.yetfFile = yetfFile;
		outputters = new LinkedList<>();
	}
	
	public void registerOutputter (Outputter outputter) {
		outputters.add(outputter);
	}
	
	public synchronized void parseFile() throws Exception {
				
		/*
		 * Start the Scanner
		 */

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(yetfFile);
				
			while (scanner.hasNextLine()) {
				
				String rowString = scanner.nextLine();

				// Parse the row
				MPACParser_Row parsedRow = new MPACParser_Row(rowString);
				
				if (outputters.size() == 0) {
					System.out.println(parsedRow);
				} else {
					
					for (Outputter outputter : outputters) {
						outputter.outputParsedRow(parsedRow);
					}
				}
			}
			
			for (Outputter outputter : outputters) {
				outputter.flushAndClose();
			}
			
		} finally {
			scanner.close();
		}
	}
}
