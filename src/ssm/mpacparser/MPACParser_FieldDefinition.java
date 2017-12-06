package ssm.mpacparser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MPACParser_FieldDefinition {
	
	private final String fieldName;
	private final int substring_start;
	private final int substring_end;
	
	private MPACParser_FieldDefinition(String fieldName, int substring_start, int substring_end) {
		this.fieldName = fieldName;
		this.substring_start = substring_start;
		this.substring_end = substring_end;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public String parseFieldValue (String rowString) {
		return rowString.substring(substring_start, substring_end);
	}
	
	
	private static Map<String, List<MPACParser_FieldDefinition>> savedFieldDefinitions = new HashMap<>();
	
	public static List<MPACParser_FieldDefinition> getFieldDefinitions (String propertyType) {
		
		List<MPACParser_FieldDefinition> fields = savedFieldDefinitions.get(propertyType);
		
		if (fields == null) {
			
			fields = new LinkedList<>();
			
			switch(propertyType) {
			
			case "AA":
				
				fields.add(new MPACParser_FieldDefinition("ward", 21, 23));
				fields.add(new MPACParser_FieldDefinition("poll", 23, 26));
				fields.add(new MPACParser_FieldDefinition("pollSuffix", 26, 27));
				fields.add(new MPACParser_FieldDefinition("highSchoolCode",     27, 29));
				fields.add(new MPACParser_FieldDefinition("publicSchoolCode",   29, 31));
				fields.add(new MPACParser_FieldDefinition("separateSchoolCode", 31, 33));
				fields.add(new MPACParser_FieldDefinition("specialRateArea",    33, 39));
				fields.add(new MPACParser_FieldDefinition("pacCode", 39, 42));
				//filler 42, 48
				fields.add(new MPACParser_FieldDefinition("previousRollNumber",       48, 67));
				fields.add(new MPACParser_FieldDefinition("frenchPublicSchoolCode",   67, 69));
				fields.add(new MPACParser_FieldDefinition("frenchSeparateSchoolCode", 69, 71));
				break;
			
			case "BB":
				
				fields.add(new MPACParser_FieldDefinition("frontage", 21, 27) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(21, 25) + "." + rowString.substring(25, 27);
					}
				});
				
				fields.add(new MPACParser_FieldDefinition("siteArea", 27, 34) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(27, 32) + "." + rowString.substring(32, 34);
					}
				});
				
				fields.add(new MPACParser_FieldDefinition("unitOfMeasurement", 34, 35));
				
				fields.add(new MPACParser_FieldDefinition("depth", 35, 40) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(35, 38) + "." + rowString.substring(38, 40);
					}
				});
				
				fields.add(new MPACParser_FieldDefinition("siteImprovement", 40, 42));
				fields.add(new MPACParser_FieldDefinition("propertyCode", 42, 45));
				fields.add(new MPACParser_FieldDefinition("zoningUFFI", 45, 52));
				fields.add(new MPACParser_FieldDefinition("services", 52, 53));
				fields.add(new MPACParser_FieldDefinition("access", 53, 54));
				break;
				
			case "CC":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22));
				//filler 22, 24
				fields.add(new MPACParser_FieldDefinition("characterOfConstruction", 24, 27));
				
				fields.add(new MPACParser_FieldDefinition("quality", 27, 30) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(27, 29) + "." + rowString.substring(29, 30);
					}
				});
				
				fields.add(new MPACParser_FieldDefinition("shape",         30, 31));
				fields.add(new MPACParser_FieldDefinition("yearBuilt",     31, 35));
				fields.add(new MPACParser_FieldDefinition("yearBuiltCode", 35, 36));
				fields.add(new MPACParser_FieldDefinition("condition",     36, 37));
				fields.add(new MPACParser_FieldDefinition("fullStoreys",   37, 39));
				fields.add(new MPACParser_FieldDefinition("partStoreys",   39, 40));
				fields.add(new MPACParser_FieldDefinition("heightEffectiveYear", 40, 44));
				fields.add(new MPACParser_FieldDefinition("split",               44, 45));
				fields.add(new MPACParser_FieldDefinition("grossArea",           45, 51));
				fields.add(new MPACParser_FieldDefinition("totalBasementArea",   51, 55));
				fields.add(new MPACParser_FieldDefinition("finishedBasementArea", 55, 59));
				fields.add(new MPACParser_FieldDefinition("basementFinish",       59, 60));
				fields.add(new MPACParser_FieldDefinition("fullBaths",            60, 61));
				fields.add(new MPACParser_FieldDefinition("halfBaths",            61, 62));
				fields.add(new MPACParser_FieldDefinition("numberOfBedrooms",     62, 64));
				fields.add(new MPACParser_FieldDefinition("numberOfFireplaces",   64, 65));
				fields.add(new MPACParser_FieldDefinition("heatingType",          65, 67));
				fields.add(new MPACParser_FieldDefinition("airConditioning",      67, 68));
				fields.add(new MPACParser_FieldDefinition("garageType",           68, 69));
				fields.add(new MPACParser_FieldDefinition("garageSpaces",         69, 72));
				fields.add(new MPACParser_FieldDefinition("structureCode",        72, 75));
				break;
				
			case "DD":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22));
				
				fields.add(new MPACParser_FieldDefinition("acres", 22, 28) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(22, 26) + "." + rowString.substring(26, 28);
					}
				});
				
				fields.add(new MPACParser_FieldDefinition("texture", 28, 29));
				fields.add(new MPACParser_FieldDefinition("soilClass", 29, 30));
				fields.add(new MPACParser_FieldDefinition("tiling", 30, 31));
				fields.add(new MPACParser_FieldDefinition("climaticZone", 31, 32));
				
				fields.add(new MPACParser_FieldDefinition("woodedAcreage", 32, 38) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(32, 36) + "." + rowString.substring(36, 38);
					}
				});
				
				// filler 38, 43
				
				fields.add(new MPACParser_FieldDefinition("orchardAcreage", 43, 49) {
					public String parseFieldValue (String rowString) {
						return rowString.substring(43, 47) + "." + rowString.substring(47, 49);
					}
				});
				
				break;
				
			case "GG":
				// filler 21, 22
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 22, 25));
				fields.add(new MPACParser_FieldDefinition("name",           25, 53));
				fields.add(new MPACParser_FieldDefinition("identifier",     53, 54));
				fields.add(new MPACParser_FieldDefinition("occupancyStatus", 54, 55));
				fields.add(new MPACParser_FieldDefinition("religion",        55, 56));
				fields.add(new MPACParser_FieldDefinition("schoolSupport",   56, 57));
				fields.add(new MPACParser_FieldDefinition("residencyCode",   57, 58));
				fields.add(new MPACParser_FieldDefinition("citizenship",     58, 59));
				fields.add(new MPACParser_FieldDefinition("designatedRatepayerCode", 59, 60));
				fields.add(new MPACParser_FieldDefinition("yearOfBirth",     60, 64));
				fields.add(new MPACParser_FieldDefinition("monthOfBirth",    64, 66));
				// filler 66, 68
				fields.add(new MPACParser_FieldDefinition("frenchLanguageEducationRights", 68, 69));
				break;
				
			case "HH":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22));
				fields.add(new MPACParser_FieldDefinition("mailingAddress", 22, 50));
				break;
				
			case "JJ":
				fields.add(new MPACParser_FieldDefinition("cityProvinceCountry", 21, 42));
				fields.add(new MPACParser_FieldDefinition("postalCode",          42, 49));
				break;
				
			case "KK":
				fields.add(new MPACParser_FieldDefinition("streetNumber",      21, 26));
				fields.add(new MPACParser_FieldDefinition("upperStreetNumber", 26, 31));
				fields.add(new MPACParser_FieldDefinition("qualifier",         31, 32));
				fields.add(new MPACParser_FieldDefinition("streetName",        32, 49));
				fields.add(new MPACParser_FieldDefinition("unitNumber",        49, 54));
				break; 
				
			case "LL":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber",   21, 22));
				fields.add(new MPACParser_FieldDefinition("legalDescription", 22, 50));
				break;
				
			case "MM":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber",         21, 22));
				fields.add(new MPACParser_FieldDefinition("commentsSiteDimensions", 22, 50));
				break;
				
			case "PA":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber",     21, 22)); // always 1
				fields.add(new MPACParser_FieldDefinition("phasedInValue",      22, 32));
				fields.add(new MPACParser_FieldDefinition("unitClass",          32, 35));
				fields.add(new MPACParser_FieldDefinition("realtyTaxClass",     35, 36));
				fields.add(new MPACParser_FieldDefinition("realtyTaxQualifier", 36, 37));
				// filler 37, 54
				fields.add(new MPACParser_FieldDefinition("tenantTaxLiability",     54, 55));
				fields.add(new MPACParser_FieldDefinition("noticeIssued",           55, 56));
				fields.add(new MPACParser_FieldDefinition("previousYearAssessment", 56, 66));
				fields.add(new MPACParser_FieldDefinition("unitSupport",            66, 67));
				// filler 67, 68
				fields.add(new MPACParser_FieldDefinition("pooledTaxesUnit",    68, 69));
				fields.add(new MPACParser_FieldDefinition("partnershipCode",    69, 70));
				fields.add(new MPACParser_FieldDefinition("propertyType",       70, 71));
				fields.add(new MPACParser_FieldDefinition("propertyTotal",      71, 81));
				fields.add(new MPACParser_FieldDefinition("realtyPortionTotal", 81, 91));
				break;
			
			case "PB":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22)); // always 1
				fields.add(new MPACParser_FieldDefinition("realtyPortionEnglishPublic",   22, 32));
				fields.add(new MPACParser_FieldDefinition("realtyPortionEnglishSeparate", 32, 42));
				fields.add(new MPACParser_FieldDefinition("realtyPortionNoSupport",       42, 52));
				break;
				
			case "PC":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22)); // always 1
				fields.add(new MPACParser_FieldDefinition("realtyPortionFrenchPublic",   22, 32));
				fields.add(new MPACParser_FieldDefinition("realtyPortionFrenchSeparate", 32, 42));
				break;
				
			case "PD":
				fields.add(new MPACParser_FieldDefinition("sequenceNumber", 21, 22)); // always 1
				fields.add(new MPACParser_FieldDefinition("realtyPortionProtestantSeparate", 22, 32));
				break;
				
			case "PI":
				fields.add(new MPACParser_FieldDefinition("phaseInStartingPoint",    21, 31));
				fields.add(new MPACParser_FieldDefinition("phaseInValue",            31, 41));
				fields.add(new MPACParser_FieldDefinition("phaseInDestinationValue", 41, 51));
				break;
			}
			
			savedFieldDefinitions.put(propertyType, fields);
		}
		
		return fields;
	}
}
