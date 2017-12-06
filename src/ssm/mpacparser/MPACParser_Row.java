package ssm.mpacparser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPACParser_Row {

	protected final String rollNumber;
	protected final String propertyType;

	protected Map<String, String> rowData;
	
	public MPACParser_Row(String rowString) {
		
		rollNumber   = rowString.substring(0, 19);
		propertyType = rowString.substring(19, 21);

		rowData = new HashMap<>();
		
		List<MPACParser_FieldDefinition> fieldDefinitions = MPACParser_FieldDefinition.getFieldDefinitions(propertyType);
		
		for (MPACParser_FieldDefinition fieldDefinition : fieldDefinitions) {
			rowData.put(fieldDefinition.getFieldName(), fieldDefinition.parseFieldValue(rowString));
		}
	}
	
	
	public String toString() {
		return "{\n" +
				"\"rollNumber\":\"" + rollNumber + "\"\n" +
				",\"propertyType\":\"" + propertyType + "\"\n" +
				",\"rowData\":\"" + rowData + "\"\n" +
				"}";
	}
}
