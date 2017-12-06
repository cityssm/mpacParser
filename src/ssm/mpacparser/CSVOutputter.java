package ssm.mpacparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVOutputter extends Outputter {

	private final File outputDirectory;
	
	private Map<String, BufferedWriter> bufferedWriters;
	private Map<String, FileWriter> fileWriters;
	
	public CSVOutputter (File outputDirectory) {
		super();
		this.outputDirectory = outputDirectory;
		
		fileWriters = new HashMap<>();
		bufferedWriters = new HashMap<>();
	}

	@Override
	public boolean outputParsedRow(MPACParser_Row parsedRow) throws IOException {
		
		String propertyType = parsedRow.propertyType;
		List<MPACParser_FieldDefinition> fieldDefinitions = MPACParser_FieldDefinition.getFieldDefinitions(propertyType);
		
		FileWriter fileWriter = fileWriters.get(propertyType);
		BufferedWriter bufferedWriter = bufferedWriters.get(propertyType);
		
		if (fileWriter == null) {
			
			// Create a new FileWriter
			fileWriter = new FileWriter(new File(outputDirectory, propertyType + ".csv"));
			fileWriters.put(propertyType, fileWriter);
			
			// Create a new BufferedWriter
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriters.put(propertyType, bufferedWriter);
			
			// Output a header row
			bufferedWriter.write("rollNumber");
			
			for (MPACParser_FieldDefinition fieldDefinition : fieldDefinitions) {
				bufferedWriter.write("," + sanitizeString(fieldDefinition.getFieldName()));
			}
			
			bufferedWriter.newLine();
		}
		
		// Output the data row
		bufferedWriter.write(sanitizeString(parsedRow.rollNumber));
		
		for (MPACParser_FieldDefinition fieldDefinition : fieldDefinitions) {
			bufferedWriter.write("," + sanitizeString(parsedRow.rowData.get(fieldDefinition.getFieldName())));
		}
		
		bufferedWriter.newLine();
		
		return true;
	}
	
	
	public void flushAndClose() {
		
		for (String bufferedWriterKey : bufferedWriters.keySet()) {
			try {
				BufferedWriter bufferedWriter = bufferedWriters.get(bufferedWriterKey);
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (Exception e) {
				// ignore
			}
		}
		
		for (String fileWriterKey : fileWriters.keySet()) {
			try {
				fileWriters.get(fileWriterKey).close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
	
	private String sanitizeString (String unsanitizedString) {
		
		if (unsanitizedString.indexOf(",") != -1) {
			return "\"" + unsanitizedString + "\"";
		}
		return unsanitizedString;
	}
}
