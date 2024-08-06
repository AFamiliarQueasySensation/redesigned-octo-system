
public class BSTDictionary extends BinarySearchTree implements BSTDictionaryADT {

	
	public Record get(Key k) {
		
		BSTNode target = get(getRoot(), k);
		if (target == null) return null;
		return target.getRecord();
	}

	/*
	 * uses BinarySearchTree's insert method to insert data object
	 * Throws exception if already exists in list
	 */
	public void put(Record d) throws DictionaryException {
		if (get(getRoot(), d.getKey()) != null) 
			{
				throw new DictionaryException("Error in putting new record in BSTDictionary");
			}
		
		insert(getRoot(),d);
	}

	/*
	 * Uses BinarySearchTree's remove method to remove data object
	 * Throws exception if already exists in list
	 */
	public void remove(Key k) throws DictionaryException {
		
		if (get(k) == null)
			{
				throw new DictionaryException("Error in finding key in BSTDictionary for removal");
			}
		else
			{
				remove(getRoot(),k);
			}
		}

	/*
	 * Uses BinarySearchTree's successor method to find successor
	 */
	public Record successor(Key k) {
		
		return successor(getRoot(),k).getRecord();
	}

	/*
	 * Usees BinarySearchTree's predecessor method to find successor
	 */
	public Record predecessor(Key k) {
		
		return predecessor(getRoot(),k).getRecord();
	}

	 /*
	  * Uses BinarySearchTree's smallest method to find smallest
	  */
	public Record smallest() {

		return smallest(getRoot()).getRecord();
	}

	 /*
	  * uses BinarySearchTree's largest method to find largest
	  */
	public Record largest() {

		return largest(getRoot()).getRecord();
	}

	

}
