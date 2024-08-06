
public class BinarySearchTree {

	BSTNode root;

	public BinarySearchTree() {

		this.root = new BSTNode(null);

	}

	/*
	 * returns the root of the tree
	 */
	public BSTNode getRoot() {
		return root;
	}

	/*
	 * Returns node storingn the key K Returns Null if not found in the tree
	 */
	public BSTNode get(BSTNode r, Key k) {
		if (r == null)
			return null;

		if (r.getRecord() != null && r.getRecord().getKey().compareTo(k) == 0) {

			return r; // check if current node is key
		}

		else if (r.isLeaf()) {
			return null;
		} else if (r.getRecord().getKey().compareTo(k) < 0) {
			return get(r.getLeftChild(), k); // left child
		} else {
			return get(r.getRightChild(), k); // right child
		}
	}

	/*
	 * Adds the record to the binary search tree with root r. Throws a
	 * DictionaryException if the tree already stores a record with the same key as
	 * d.
	 */
	public void insert(BSTNode r, Record d) throws DictionaryException {

		BSTNode node = new BSTNode(d);

		// If the root is empty, then add it as the root to the dictionary
		if (r.getRecord() == null) {
			r.setRecord(d);
			return;
		}

		if (get(r, d.getKey()) != null)
			throw new DictionaryException("Key already found in BST");

		insertHelper(r, node);

	}

	/*
	 * Helps insert, insert target into the BST, using r as the root and traversing
	 * the tree
	 */
	private void insertHelper(BSTNode r, BSTNode target) {
		// if it is greater than the current
		if (r.getRecord().getKey().compareTo(target.getRecord().getKey()) == 1) {
			if (r.getRightChild() == null) {
				target.setParent(r);
				r.setRightChild(target);
				return;
			} else {
				insertHelper(r.getRightChild(), target);
			}
		} else if (r.getRecord().getKey().compareTo(target.getRecord().getKey()) == -1) {
			if (r.getLeftChild() == null) {
				target.setParent(r);
				r.setLeftChild(target);
				return;
			} else {
				insertHelper(r.getLeftChild(), target);
			}
		}
		return;

	}

	/*
	 * Deletes the node with the given key from the tree with root r. Throws a
	 * DictionaryException if the tree does not store a record with the given key.
	 */

	public void remove(BSTNode r, Key k) throws DictionaryException {
		// Couldn't match key to a node in the tree
		BSTNode target = get(r, k);

		if (target == null)
			throw new DictionaryException("Could not remove Node, as key was not found in BST tree");
		if (target.isLeaf()) {
			if (target.getParent() == null) // checks if it is the root and sets it to null;
			{
				getRoot().setRecord(null);
			} else {
				if (target.getParent().getRightChild() == target) {
					target.getParent().setRightChild(null);
				} else if (target.getParent().getLeftChild() == target) {
					target.getParent().setLeftChild(null);
				}
			}
		} else {
			if (target.getLeftChild().isLeaf() && target.getLeftChild() != null
					|| target.getRightChild().isLeaf() && target.getRightChild() != null) {
				BSTNode cr = target.getRightChild();
				BSTNode parent = target.getParent();
				if (parent != null) {
					if (target.equals(parent.getRightChild())) {
						parent.setRightChild(cr);
					}
					parent.setLeftChild(cr);

				} else {
					// set cr as root
					r = cr;
				}
			} else {
				BSTNode s = smallest(target.getRightChild());
				target.setRecord(s.getRecord());
				remove(s, s.getRecord().getKey());
			}
		}

	}

	/*
	 * Returns the node storing the successor of the given key in the tree with root
	 * r; Returns null if the successor does not exist.
	 */
	public BSTNode successor(BSTNode r, Key k) {

		BSTNode p = get(r, k);
		if (p == null)
			return null; // not found

		// Right then Left, is correct
		if (p.getRightChild() != null) {

			return smallest(p.getRightChild());

		}

		p = p.getParent();
		while (p != null && p.getRecord().getKey().compareTo(k) > 0) {
			p = p.getParent();
		}
		return p;

	}

	/*
	 * Returns the node storing the predecessor of the given key in the tree with
	 * root r; Returns null if the predecessor does not exist.
	 */
	public BSTNode predecessor(BSTNode r, Key k) {
		BSTNode p = get(r, k);

		if (p == null)
			return null;
		// Internal Node, go left
		if (p.getLeftChild() != null) {
			return largest(p.getLeftChild());
		} 
		
		p = p.getParent();
		while (p != null && p.getRecord().getKey().compareTo(k) < 0) {
			p = p.getParent();
		}
		return p;
		
	}

	/*
	 * Returns the node with the smallest key in tree with root r.
	 */
	public BSTNode smallest(BSTNode r) {
		if (r == null)
			return null;
		else {
			BSTNode pee = r;
			while (pee.getLeftChild() != null) {
				pee = pee.getLeftChild();
			}
			return pee;
		}
	}

	/*
	 * Returns the node with the largest key in tree with root r.
	 */
	public BSTNode largest(BSTNode r) {
		if (r == null)
			return null;
		else {
			BSTNode p = r;
			while (p.getRightChild() != null) {
				p = p.getRightChild();
			}
			return p;
		}
	}

}
