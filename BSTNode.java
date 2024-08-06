
public class BSTNode {

	Record item;
	BSTNode left;
	BSTNode right;
	BSTNode parent;
	/*
	 * Constructor
	 */
	
	
	public BSTNode(Record item) {
		this.item = item;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	/*
	 * Returns the record
	 */
	public Record getRecord() {
		return item;
	}
	/*
	 * Sets the record
	 */
	public void setRecord(Record d)
	{
		this.item = d;
	}
	/*
	 * returns the left child node
	 */
	public BSTNode getLeftChild()
	{
		return left;
	}
	/*
	 * Returns the right child node
	 */
	public BSTNode getRightChild()
	{
		return right;
	}
	/*
	 * returns the parent node
	 */
	public BSTNode getParent() {
		return parent;
	}
	/*
	 * set left child
	 */
	public void setLeftChild(BSTNode u) {
		this.left = u;
	}
	
	/*
	 * set right child
	 */
	public void setRightChild(BSTNode u) {
		this.right = u;
	}
	
	/*
	 * set parent Node
	 */
	public void setParent(BSTNode u) {
		this.parent = u;
	}
	
	/*
	 * returns true if leaf node
	 * false otherwise
	 */
	public boolean isLeaf() {
		if (this.left == null && this.right == null) return true;
		else
		{
			return false;
		}
	}
	
	
	
	
}
