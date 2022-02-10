import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;

public class NumArrayListTester {
    /**
     * Creates a NumArrayList with the specified double values
     * Uses the empty constructor and the add method
     */
    private static NumArrayList createArrayList(double... values) {
        NumArrayList list = new NumArrayList();
        for (double value : values)
            list.add(value);

        return list;
    }

    /**
     * Unit tests for the NumArrayList constructors
     */
    @Test
    public void testNumArrayList() {
        // constructor with no parameters
        NumArrayList list1 = new NumArrayList();
        Assert.assertTrue("list1 size should have been 0 but it was not", list1.size() == 0);
        Assert.assertTrue("list1 capacity should have been 0 but it was not", list1.capacity() == 0);

        // constructor with capacity of 0
        NumArrayList list2 = new NumArrayList(0);
        Assert.assertTrue("list2 size should have been 0 but it was not", list2.size() == 0);
        Assert.assertTrue("list2 capacity should have been 0 but it was not", list2.capacity() == 0);

        // constructor with capacity of 3
        NumArrayList list3 = new NumArrayList(3);
        Assert.assertTrue("list3 size should have been 0 but it was not", list3.size() == 0);
        Assert.assertTrue("list3 capacity should have been 3 but it was not", list3.capacity() == 3);
    }
    
    /**
     * Unit tests for the size method
     */
    @Test
    public void testSize() {
        // size 0
        NumArrayList list1 = new NumArrayList();
        Assert.assertTrue("list1 size should have been 0 but it was not", list1.size() == 0);

        // size 1
        list1.add(1);
        Assert.assertTrue("list1 size should have been 1 but it was not", list1.size() == 1);

        // size 3
        list1.add(1);
        list1.add(1);
        Assert.assertTrue("list1 size should have been 3 but it was not", list1.size() == 3);
    }

    /**
     * Unit tests for the capacity method
     */
    @Test
    public void testCapacity() {
        // capacity 0
        NumArrayList list1 = new NumArrayList();
        Assert.assertTrue("list1 capacity should have been 0 but it was not", list1.capacity() == 0);

        // capacity 1
        list1.add(1);
        Assert.assertTrue("list1 capacity should have been 1 but it was not", list1.capacity() == 1);

        // capacity 4
        list1.add(1);
        list1.add(1);
        Assert.assertTrue("list1 capacity should have been 4 but it was not", list1.capacity() == 4);
    }

    /**
     * Unit tests for the add method
     */
    @Test
    public void testAdd() {
        // 0 elements
        NumArrayList list1 = new NumArrayList();
        Assert.assertEquals("list1 was not empty when it should have been", "", list1.toString());
        Assert.assertTrue("list1 size should have been 0 but it was not", list1.size() == 0);
        Assert.assertTrue("list1 capacity should have been 0 but it was not", list1.capacity() == 0);

        // 1 element
        list1.add(1.0);
        Assert.assertEquals("list1 did not have the correct element", "1.0", list1.toString());
        Assert.assertTrue("list1 size should have been 1 but it was not", list1.size() == 1);
        Assert.assertTrue("list1 capacity should have been 1 but it was not", list1.capacity() == 1);

        // 3 elements
        list1.add(2.0);
        list1.add(3.0);
        Assert.assertEquals("list1 did not have the correct elements", "1.0 2.0 3.0", list1.toString());
        Assert.assertTrue("list1 size should have been 3 but it was not", list1.size() == 3);
        Assert.assertTrue("list1 capacity should have been 4 but it was not", list1.capacity() == 4);
    }

    /**
     * Unit tests for the insert method
     */
    @Test
    public void testInsert() {
        // test when the list is empty
        NumArrayList list1 = new NumArrayList();
        list1.insert(0, 0.0);
        Assert.assertTrue("The method did not add the value to the list as intended", list1.lookup(0) == 0.0);

        // test when the new value displaces the one element in the list
        list1 = NumArrayListTester.createArrayList(1.0);
        list1.insert(0, 0.0);        
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);
        Assert.assertTrue("The 2nd element in the list should be 1.0", list1.lookup(1) == 1.0);

        // test when the new value goes after the one element in a list (and expands the capacity)
        list1 = NumArrayListTester.createArrayList(0.0);
        Assert.assertTrue("The capacity should be 1", list1.capacity() == 1);
        list1.insert(2, 1.0);
        Assert.assertTrue("The 2nd element in the list should be 1.0", list1.lookup(1) == 1.0);
        Assert.assertTrue("The capacity should be 2", list1.capacity() == 2);

        // test when the new value displaces multiple elements in a list
        list1 = NumArrayListTester.createArrayList(0.0, 2.0, 3.0, 4.0);
        list1.insert(1, 1.0);
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);
        Assert.assertTrue("The 2nd element in the list should be 1.0", list1.lookup(1) == 1.0);
        Assert.assertTrue("The 3rd element in the list should be 2.0", list1.lookup(2) == 2.0);
        Assert.assertTrue("The 4th element in the list should be 3.0", list1.lookup(3) == 3.0);
        Assert.assertTrue("The 5th element in the list should be 4.0", list1.lookup(4) == 4.0);
    }

    /**
     * Unit tests for the remove method
     */
    @Test
    public void testRemove() {
        // test on an empty list
        NumArrayList list1 = new NumArrayList();
        list1.remove(0);
        Assert.assertTrue("The capacity of the list should be 0", list1.capacity() == 0);
        Assert.assertTrue("The size of the list should be 0", list1.size() == 0);

        // test on a list with one element with an index of 0
        list1.add(0.0);
        list1.remove(0);
        Assert.assertTrue("The capacity of the list should be 1", list1.capacity() == 1);
        Assert.assertTrue("The size of the list should be 0", list1.size() == 0);

        // test on a list with one element with an index of 1
        list1.add(0.0);
        list1.remove(1);
        Assert.assertTrue("The capacity of the list should be 1", list1.capacity() == 1);
        Assert.assertTrue("The size of the list should be 1", list1.size() == 1);
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);

        // test on a list with multiple elements with an index of 0
        list1 = NumArrayListTester.createArrayList(-1.0, 0.0, 1.0, 2.0);
        list1.remove(0);
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);
        Assert.assertTrue("The 2nd element in the list should be 1.0", list1.lookup(1) == 1.0);
        Assert.assertTrue("The 3rd element in the list should be 2.0", list1.lookup(2) == 2.0);

        // test on a list with multiple element with an index greater than the size
        list1 = NumArrayListTester.createArrayList(0.0, 1.0, 2.0, 3.0, 4.0);
        list1.remove(5);
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);
        Assert.assertTrue("The 2nd element in the list should be 1.0", list1.lookup(1) == 1.0);
        Assert.assertTrue("The 3rd element in the list should be 2.0", list1.lookup(2) == 2.0);
        Assert.assertTrue("The 4th element in the list should be 3.0", list1.lookup(3) == 3.0);
        Assert.assertTrue("The 5th element in the list should be 4.0", list1.lookup(4) == 4.0);
    }

    /**
     * Unit tests for the contains method
     */
    @Test
    public void testContains() {
        // test on an empty list
        NumArrayList list1 = new NumArrayList();
        Assert.assertFalse("The method should return false on an empty list", list1.contains(0.0));

        // test on a list with one value that is the correct value
        list1.add(0.0);
        Assert.assertTrue("The method should return true when the list contains the value", list1.contains(0.0));

        // test on a list with one value that is not the value in a parameter
        Assert.assertFalse("The method should return false when the list does not contain the value", list1.contains(1.0));

        // test on a list with multiple values with one match
        list1.add(1.0);
        list1.add(2.0);
        Assert.assertTrue("The method should return true when the list contains the value", list1.contains(1.0));

        // test on a list with multiple values with multiple matches
        list1 = NumArrayListTester.createArrayList(0.0, 0.0, 0.0);
        Assert.assertTrue("The method should return true when the list contains the value", list1.contains(0.0));

        // test on a list with multiple values with no matches
        Assert.assertFalse("The method should return false when the list does not contain the value", list1.contains(1.0));
    }

    /**
     * Unit tests for the lookup method
     */
    @Test
    public void testLookup() {
        // test on a list with no capacity
        NumArrayList list1 = new NumArrayList();
        try {
            list1.lookup(0);
            Assert.fail("The method should have thrown an exception but it did not");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch (Exception e) {
            Assert.fail("The method threw the wrong exception: " + e.toString());
        }

        // test on a list with capacity but size of 0 (empty)
        list1 = new NumArrayList(1);
        try {
            list1.lookup(0);
            Assert.fail("The method should have thrown an exception but it did not");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch (Exception e) {
            Assert.fail("The method threw the wrong exception: " + e.toString());
        }

        // tests on a list with values
        list1 = NumArrayListTester.createArrayList(0.0, 1.0, 2.0, 3.0, 4.0);
        try {
            String message = "The method returned a value from the wrong index of the list";
            Assert.assertTrue(message, list1.lookup(0) == 0.0);
            Assert.assertTrue(message, list1.lookup(1) == 1.0);
            Assert.assertTrue(message, list1.lookup(2) == 2.0);
            Assert.assertTrue(message, list1.lookup(3) == 3.0);
            Assert.assertTrue(message, list1.lookup(4) == 4.0);
        }
        catch (Exception e) {
            Assert.fail("The method threw an exception when it should not have: " + e.toString());
        }

        // test where the index is beyond the size of the list
        try {
            list1.lookup(5);
            Assert.fail("The method should have thrown an exception but it did not");
        }
        catch (IndexOutOfBoundsException e) {
        }
        catch (Exception e) {
            Assert.fail("The method threw the wrong exception: " + e.toString());
        }
    }

    /**
     * Unit tests for the equals method
     */
    @Test
    public void testEquals() {
        // two empty lists of the same or different capacities should be equal
        // the first test also checks communicativeness
        NumArrayList list1 = new NumArrayList();
        NumArrayList list2 = new NumArrayList();
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list2.equals(list1));
        // a list should also equal itself
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list1));
        // different capacities
        list2 = new NumArrayList(1);
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));

        // two lists with the same value with same or different capacities should be equal
        // different capacities
        list1 = NumArrayListTester.createArrayList(1.0);
        list2 = new NumArrayList(2);
        list2.add(1.0);
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));
        // same capacity
        list2 = NumArrayListTester.createArrayList(1.0);
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));

        // two lists with the same values with the same or different capacities should be equal
        // different capacities
        list1 = NumArrayListTester.createArrayList(1.0, 2.0);
        list2 = new NumArrayList(4);
        list2.add(1.0);
        list2.add(2.0);
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));
        // same capacity
        list2 = NumArrayListTester.createArrayList(1.0, 2.0);
        Assert.assertTrue("The method should have returned true for the two lists, but it did not", list1.equals(list2));

        // two lists with values that are not in the exact same order should not be equal
        list1 = NumArrayListTester.createArrayList(1.0, 2.0, 3.0);
        list2 = NumArrayListTester.createArrayList(1.0, 3.0, 2.0);
        Assert.assertFalse("The method should have returned false for the two lists, but it did not", list1.equals(list2));
    }

    /**
     * Unit tests for the remove duplicates method
     */
    @Test
    public void testRemoveDuplicates() {
        // empty list
        NumArrayList list1 = new NumArrayList();
        list1.removeDuplicates();
        Assert.assertTrue("The capacity of the list should be 0", list1.capacity() == 0);
        Assert.assertTrue("The size of the list should be 0", list1.size() == 0);

        // list with one element
        list1.add(0.0);
        list1.removeDuplicates();
        Assert.assertTrue("The capacity of the list should be 1", list1.capacity() == 1);
        Assert.assertTrue("The size of the list should be 1", list1.size() == 1);
        Assert.assertTrue("The 1st element in the list should be 0.0", list1.lookup(0) == 0.0);

        // list with multiple elements but no duplicates
        list1 = NumArrayListTester.createArrayList(0.0, 1.0, 2.0, 3.0, 4.0);
        list1.removeDuplicates();
        Assert.assertEquals("The contents of the array were altered when they shouldn't have been",
                            "0.0, 1.0, 2.0, 3.0, 4.0", list1.toString());

        /* a list with multiple duplicates at various locations */
        String message = "The method did not remove the duplicates correctly";
        // a list with adjacent duplicates
        list1 = NumArrayListTester.createArrayList(0.0, 0.0, 1.0, 1.0, 2.0);
        list1.removeDuplicates();
        Assert.assertEquals(message,
                            "0.0, 1.0, 2.0", list1.toString());

        // a list with duplicates at the ends
        list1 = NumArrayListTester.createArrayList(0.0, 1.0, 2.0, 1.0, 0.0);
        list1.removeDuplicates();
        Assert.assertEquals(message,
                            "0.0, 1.0, 2.0", list1.toString());

        // a list with elements that are all the same value
        list1 = NumArrayListTester.createArrayList(0.0, 0.0, 0.0, 0.0, 0.0);
        list1.removeDuplicates();
        Assert.assertEquals(message,
                            "0.0", list1.toString());
    }

    /**
     * Unit tests for the isSorted method
     */
    @Test
    public void testIsSorted() {
        // empty list

        // list with one element

        // list with two identical elements

        // list with two elements in ascending order

        // list with two elements in descending order

        // list with multiple elements in ascending order

        // list with multiple elements that are not sorted

        // check a list that becomes sorted after removing an element

        // check a list that becomes unsorted after adding a value

        // check a list that becomes unsorted after adding a value

    }

    /**
     * Unit tests for the reverse method
     */
    @Test
    public void testReverse() {
        // empty list

        // list with one element

        // list with two elements that are the same

        // list with two elements that are different

        // list with multiple elements

        // repeated calls to reverse
        
    }

    /**
     * Unit tests for the union method
     */
    @Test
    public void testUnion() {
        // two empty lists

        // one empty list and one list with one or multiple (unsorted) elements

        // two unsorted lists with multiple elements (no duplicates)

        // two unsorted lists with multiple elements (with duplicates)

        // two sorted lists (no duplicates)

        // two sorted lists (with duplicates)

    }

    /**
     * Unit tests for the toString method
     */
    @Test
    public void testToString() {
        // test on an empty array
        NumArrayList list1 = new NumArrayList();
        Assert.assertEquals("The method did not return an empty string", "", list1.toString());

        // test on an array with one element
        list1.add(0.0);
        Assert.assertEquals("The method did not return the correct string", "0.0", list1.toString());

        // test on an array with multiple elements
        list1 = NumArrayListTester.createArrayList(0.0, 1.0, 2.0, 3.0, 4.0);
        Assert.assertEquals("The method did not return the correct string", "0.0 1.0 2.0 3.0 4.0", list1.toString());
    }
}