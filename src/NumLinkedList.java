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

    }

    /**
     * Adds a number at a specified position of the list
     * All numbers after (and the number at) the specified position are shifted down the list
     * If the list has i or fewer numbers, then the value is added in the same fashion as the add method
     * @param i the index to insert the new value at
     * @param value the value to add to the list
     */
    public void insert(int i, double value) {

    }

    /**
     * Removes the number at the specified position of the list
     * All numbers after the specified position are shifted up the list
     * The size of the list is shortened by this method if there is a number at the specified position
     * @param i the index where the value should be removed
     */
    public void remove(int i) {

    }

    /**
     * Checks whether the list contains the input value
     * @param value the value is what the method is confirming is in the list
     * @return true if the value is in the list, false otherwise
     */
    public boolean contains(double value) {
        return false;
    }

    /**
     * Returns the i-th element of the list
     * @param i the index of the list to get the value from
     * @return the value stored at the specified index
     * @throws IndexOutOfBoundsException when the input index is greater than or equal to the size of the list
     */
    public double lookup(int i) throws IndexOutOfBoundsException {
        return 0.0;
    }

    /**
     * Checks whether this list is equal to the input list
     * Two lists are equal if they have all the same numbers in the same order
     * @param otherList the other list to compare this list to
     * @return true if the two are equal, false otherwise
     */
    public boolean equals(NumList otherList) {
        return false;
    }

    /**
     * Removes duplicates in this list while preserving the current order of the numbers
     */
    public void removeDuplicates() {

    }

    /**
     * Returns whether the list is currently sorted in increasing order or not
     * @return true if the list is sorted, false otherwise
     */
    public boolean isSorted() {
        return false;
    }

    /**
     * Reverses the order of the elements in the list
     */
    public void reverse() {

    }

    /**
     * Creates a new list which has all elements in the input lists without any duplicate elements
     * If both lists are sorted, then the resulting list is also sorted
     * @param list1 the first list
     * @param list2 the second list
     * @return a NumList with all the elements of the input lists without any duplicate elements
     */
    public NumList union(NumList list1, NumList list2) {
        return new NumLinkedList();
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
        /**
         * Constructor for an iterator for NumLinkedList
         * @param list the list to iterate over
         */
        public NumLinkedListIterator(NumLinkedList list) {

        }
        
        /**
         * Checks whether there is another value ahead of the iterator in the list
         * @return true if there is a value, false otherwise
         */
        public boolean hasNext() {
            return false;
        }
        
        /**
         * Moves the iterator forward one value and returns that value
         * @return the value at iterator's current index
         */
        public double next() {
            return 0.0;
        }

        /**
         * Retrieves the value the iterator is current at without moving it forward
         * @return the value at the iterator's current index
         */
        public double peek() {
            return 0.0;
        }
    }
}
