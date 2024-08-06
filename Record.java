
public class Record {

	Key k;
	String theData;
	/*
	 * Constructor for initializing a new record object with Key and String types.
	 */
	public Record(Key k, String theData) {
	
		this.k = k;
		this.theData = theData;
	}
	
	/*
	 * returns key
	 */
	public Key getKey() {
		return k;
	}
	
	/*
	 * returns data item
	 */
	public String getDataItem() {
		return theData;
	}
}
