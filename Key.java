
public class Key {

	String theLabel;
	int theType;
	
	/*
	 * Initialize theLabel, and theType;
	 * 
	 */
	public Key (String theLabel, int theType){
		
		this.theLabel = theLabel.toLowerCase();
		this.theType = theType;
	}
	
	/*
	 * returns the String getLabel
	 */
	public String getLabel()
	{
		return theLabel;
	}
	
	/*
	 * returns the int type
	 */
	public int getType() 
	{
		return theType;
	}
	
	/*
	 * If same then return 0
	 * If smaller than return -1
	 * else return 1
	 * 
	 */

	public int compareTo(Key k)
	{
		if (this.theLabel.compareTo(k.getLabel()) == 0 && this.theType == k.getType()) return 0;
		else if (this.theLabel.compareTo(k.getLabel()) < 0) return 1;
		else if (this.theLabel.compareTo(k.getLabel()) == 0 && this.theType < k.getType()) return 1;
		else return -1;
	}
	
	
	
	
	
}
