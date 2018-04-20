/** Linked list implementation */
class LList2<E extends Comparable<? super E>> implements List<E> {

	private Link<E> head; // Pointer to list header
	private Link<E> tail; // Pointer to last element
	protected Link<E> curr; // Access to current element
	int cnt; // Size of list

	/** Constructors */
	LList2(int size) {
		this();
	} // Constructor -- Ignore size

	LList2() {
		// initially all pointers point point to same node
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}

	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}

	/** Insert "item" in its natural order */
	/* This section of code was created by John-Tyler M Cooper */
	public void insert(E item) {

		Link<E> savePointerLocation = curr;// save pointer location
		curr = head;// set pointer to head for looping

		// check if the is list is empty and if the item should be first in the lisr
		if (head.next() == null || head.next().element().compareTo(item) > 0) {

			curr.setNext(new Link<E>(item, curr.next()));// create new link and point the head to the new link

			if (tail == curr) {
				tail = curr.next();
			}
			cnt++;// add one to the list count
		} else {
			// loop through list until we find the location where the item is in natural order
			while (curr.next() != null && curr.next().element().compareTo(item) < 0) {
				curr = curr.next();
			}
			// check to see if the item is in the list
			if (curr.next().element().compareTo(item) == 0) {
				return;// items in the list, do nothing and exit method
			}
			curr.setNext(new Link<E>(item, curr.next()));// creates link and sets current links pointer to new link
			if (tail == curr) {
				tail = curr.next();
			}
			cnt++;// add one to the list count
		}
		curr = savePointerLocation;// sets pointer to location before insert method was called
	}

	/** Append "it" to list */
	public void append(E it) {
		tail = tail.setNext(new Link<E>(it, null));
		cnt++;
	}

	/** Remove and return current element */
	public E remove() {
		if (curr.next() == null)
			return null; // Nothing to remove
		E it = curr.next().element(); // Remember value
		if (tail == curr.next())
			tail = curr; // Removed last
		curr.setNext(curr.next().next()); // Remove from list
		cnt--; // Decrement count
		return it; // Return value
	}

	/* This section of code was created by John-Tyler M Cooper */
	/** Removes the item that is passed by user */
	public void remove(E item) {

		Link<E> savePointerLocation = curr;// Saves pointer location
		curr = head;// sets curr to head to loop through entire list.

		// Checks if the item is first in the list
		if (head.next().element().compareTo(item) == 0) {

			head.setNext(head.next().next());// skips the first link
			cnt--;// minus one from the list count.

		} else {
			// loops through list to find the item to remove
			while (curr.next() != null && curr.next().element().compareTo(item) != 0) {
				curr = curr.next();
			}
			curr.setNext(curr.next().next());// skips the link that contains the item to remove
			cnt--;// minus one from the count

		}
		curr = savePointerLocation;// sets pointer to location before remove method was called

	}

	/** Set curr at list start */
	public void moveToStart() {
		curr = head;
	}

	/** Set curr at list end */
	public void moveToEnd() {
		curr = tail;
	}

	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head)
			return; // No previous element
		Link<E> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr)
			temp = temp.next();
		curr = temp;
	}

	/** Move curr one step right; no change if now at end */
	public void next() {
		if (curr != tail)
			curr = curr.next();
	}

	/** @return List length */
	public int length() {
		return cnt;
	}

	/** @return The position of the current element */
	public int currPos() {
		Link<E> temp = head;
		int i;
		for (i = 0; curr != temp; i++)
			temp = temp.next();
		return i;
	}

	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <= cnt) : "Position out of range";
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next();
	}

	/** @return Current element value */
	public E getValue() {
		if (curr.next() == null)
			return null;
		return curr.next().element();
	}
	// Extra stuff not printed in the book.

	/**
	 * Generate a human-readable representation of this list's contents that looks something like this: < 1 2 3 | 4 5 6 >. The vertical bar represents the current location of the fence. This method
	 * uses toString() on the individual elements.
	 * 
	 * @return The string representation of this list
	 */
	public String toString() {
		// Save the current position of the list
		int oldPos = currPos();
		int length = length();
		StringBuffer out = new StringBuffer((length() + 1) * 4);

		moveToStart();
		out.append("< ");
		for (int i = 0; i < oldPos; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append("| ");
		for (int i = oldPos; i < length; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append(">");
		moveToPos(oldPos); // Reset the fence to its original position
		return out.toString();
	}
}