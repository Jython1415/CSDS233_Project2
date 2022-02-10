import java.util.NoSuchElementException;

public class NumLinkedList implements NumList {
    /* Stores a reference to the first node in the list */
    private LLNode front = null;

    /* Stores a reference to the last node in the list */
    private LLNode back = null;

    /* Keeps track of how many elements are in the list */
    private int size = 0;

    /* The maximum capacity of the list */
    private final int capacity = Integer.MAX_VALUE;

    /* Keeps track of whether or not the list is sorted */
    private boolean sorted = true;

    /**
     * Constructor for an empty linked list
     */
    public NumLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
        this.sorted = true;
    }

    /**
     * Getter for the front of the list
     * @return a reference to the first node in the list
     */
    private LLNode getFront() {
        return this.front;
    }

    /**
     * Setter for the front of the list
     * @param front a reference to the first node in the list
     */
    private void setFront(LLNode front) {
        this.front = front;
    }

    /**
     * Getter for the back of the list
     * @return a reference to the last node in the list
     */
    private LLNode getBack() {
        return this.back;
    }

    /**
     * Setter for the back of the 
     * @param back
     */
    private void setBack(LLNode back) {
        this.back = back;
    }

    /**
     * Getter method for the size of the list
     * The size is the number of numbers currently in the list
     * @return the size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Increments the size field
     */
    private void incrementSize() {
        this.size++;
    }

    /**
     * Decrements the size field
     */
    private void decrementSize() {
        this.size--;
    }

    /**
     * Getter method for the capacity of the list
     * The capacity is the number of numbers the list can hold without resizing
     * @return the capacity of the list
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Setter for the sorted field
     * @param sorted whether or not the list is sorted
     */
    private void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    /**
     * Adds a number of the end of the list
     * The method expands the capacity if needed
     * @param value the number to be added to the end of the list
     */
    public void add(double value) {
        // case for an empty list
        if (getFront() == null) {
            setFront(new LLNode(value, null, null));
            setBack(getFront());
        }
        else {
            // updates sorted based on the new value if the list is sorted, no update otherwise
            setSorted(isSorted() ? value >= getBack().getValue() : isSorted());

            // adds the new node to the back & updates the next for the previous "back" node
            setBack(new LLNode(value, getBack(), null));
            getBack().getPrev().setNext(getBack());
        }

        incrementSize();
    }

    /**
     * Adds a number at a specified position of the list
     * All numbers after (and the number at) the specified position are shifted down the list
     * If the list has i or fewer numbers, then the value is added in the same fashion as the add method
     * @param i the index to insert the new value at
     * @param value the value to add to the list
     */
    public void insert(int i, double value) {
        if (i >= size() - 1) {
            add(value);
        }
        else if (i == 0) {
            // updates sorted based on the new value if the list is sorted, no update otherwise
            setSorted(isSorted() ? value <= getFront().getValue() : isSorted());

            setFront(new LLNode(value, null, getFront()));
            getFront().getNext().setPrev(getFront());
        }
        else {
            // finds the node directly before the insertion point
            LLNode nodePtr = nodeLookup(i - 1);

            // updates sorted based on the new value if the list is sorted, no update otherwise
            setSorted(isSorted() ?
                      nodePtr.getValue() <= value && value <= nodePtr.getNext().getValue() :
                      isSorted());

            nodePtr.setNext(new LLNode(value, nodePtr, nodePtr.getNext()));
            nodePtr.getNext().getNext().setPrev(nodePtr.getNext());
        }

        incrementSize();
    }

    /**
     * Removes the number at the specified position of the list
     * All numbers after the specified position are shifted up the list
     * The size of the list is shortened by this method if there is a number at the specified position
     * @param i the index where the value should be removed
     */
    public void remove(int i) {
        if (0 <= i && i < size()) {
            if (size == 1) {
                setFront(null);
                setBack(null);
            }
            else if (i == 0) {
                setFront(getFront().getNext());
            }
            else if (i == size() - 1) {
                setBack(getBack().getPrev());
            }
            else {
                LLNode nodePtr = nodeLookup(i - 1);
                nodePtr.setNext(nodePtr.getNext().getNext());
            }

            decrementSize();
            setSorted(checkIfSorted());
        }
    }

    /**
     * Checks whether the list contains the input value
     * @param value the value is what the method is confirming is in the list
     * @return true if the value is in the list, false otherwise
     */
    public boolean contains(double value) {
        LLNode nodePtr = getFront();

        for (int i = 0; i < size(); i++) {
            if (nodePtr.getValue() == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the i-th node of the list
     * @param index the index of the list to get the node from
     * @return a reference to the specified node
     * @throws IndexOutOfBoundsException when the input index is greater than or equal to the size of the list
     */
    private LLNode nodeLookup(int index) throws IndexOutOfBoundsException {
        // this check prevents a null pointer exception
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            LLNode nodePtr = getFront();

            // advance in the list index times
            for (int i = 0; i < index; i++) {
                nodePtr = nodePtr.getNext();
            }

            return nodePtr;
        }
    }

    /**
     * Returns the i-th element of the list
     * @param i the index of the list to get the value from
     * @return the value stored at the specified index
     * @throws IndexOutOfBoundsException when the input index is greater than or equal to the size of the list
     */
    public double lookup(int i) throws IndexOutOfBoundsException {
        return nodeLookup(i).getValue();
    }

    /**
     * Removes duplicates in this list while preserving the current order of the numbers
     */
    public void removeDuplicates() {

    }

    /**
     * Checks the entire list to see if it is sorted
     * @return true if the list is sorted, false otherwise
     */
    public boolean checkIfSorted() {
        LLNode nodePtr = getFront();

        for (int i = 0; i < size(); i++) {
            nodePtr = nodePtr.getNext();
            if (nodePtr.getValue() < nodePtr.getPrev().getValue()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether the list is currently sorted in increasing order or not
     * @return true if the list is sorted, false otherwise
     */
    public boolean isSorted() {
        return this.sorted;
    }

    /**
     * Reverses the order of the elements in the list
     */
    public void reverse() {
        LLNode frontSave = getFront();

        setFront(getBack());
        setBack(frontSave);

        LLNode nodePtr = frontSave;
        while (nodePtr != null) {
            LLNode nextSave = nodePtr.getNext();

            nodePtr.setNext(nodePtr.getPrev());
            nodePtr.setPrev(nextSave);

            nodePtr = nextSave;
        }

        setSorted(checkIfSorted());
    }

    /**
     * Provides a String representation of this list
     * For an empty list, an empty String is returned
     * Numbers are separated by a space and no other characters are included
     * @return a String representation of the list
     */
    public String toString() {
        return "";
    }

    /**
     * Returns an iterator for the list that begins at the front of the list
     * @return a DoubleIterator for the list the method is called on
     */
    public DoubleIterator iterator() {
        return new NumLinkedListIterator(this);
    }

    private static class LLNode {
        /* Stores the value in the node */
        private double value = 0.0;

        /* Stores a reference to the next node in the list */
        private LLNode next = null;

        /* Stores a reference to the previous node in the list */
        private LLNode prev = null;

        /**
         * Constructor for a node
         * @param value the value to store in the node
         * @param prev a reference to the previous node in the list
         * @param next a reference to the next node in the list
         */
        public LLNode(double value, LLNode prev, LLNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Getter for the value
         * @return the value stored in the node
         */
        public double getValue() {
            return this.value;
        }

        /**
         * Setter for the value
         * @param value the value to store in the node
         */
        public void setValue(double value) {
            this.value = value;
        }

        /**
         * Getter for the previous node in the list
         * @return a reference to the previous node in the list
         */
        public LLNode getPrev() {
            return this.prev;
        }

        /**
         * Setter for the previous node in the list
         * @param prev a reference to the previous node in the list
         */
        public void setPrev(LLNode prev) {
            this.prev = prev;
        }

        /**
         * Getter for the next node in the list
         * @return a reference to the next node in the list
         */
        public LLNode getNext() {
            return this.next;
        }

        /**
         * Setter for the next node in the list
         * @param next a reference to the next node in the list
         */
        public void setNext(LLNode next) {
            this.next = next;
        }
    }

    private static class NumLinkedListIterator implements DoubleIterator {
        /* Stores a reference to the current position of the iterator */
        private LLNode nodePtr = null;
        
        /**
         * Constructor for an iterator for NumLinkedList
         * @param list the list to iterate over
         */
        public NumLinkedListIterator(NumLinkedList list) {
            this.nodePtr = list.getFront();
        }
        
        /**
         * Checks whether there is another value ahead of the iterator in the list
         * @return true if there is a value, false otherwise
         */
        public boolean hasNext() {
            return nodePtr != null;
        }
        
        /**
         * Moves the iterator forward one value and returns that value
         * @return the value at iterator's current index
         */
        public double next() {
            if (hasNext()) {
                double save = nodePtr.getValue();
                nodePtr = nodePtr.getNext();

                return save;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        /**
         * Retrieves the value the iterator is current at without moving it forward
         * @return the value at the iterator's current index
         */
        public double peek() {
            if (hasNext()) {
                return nodePtr.getValue();
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
}
