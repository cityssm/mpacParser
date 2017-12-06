package ssm.mpacparser;

public abstract class Outputter {

	public abstract boolean outputParsedRow (MPACParser_Row parsedRow) throws Exception;
	
	public void flushAndClose() {
		
	}
}
