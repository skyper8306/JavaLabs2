package TwoIterators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class TwoIteratorsTest {
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    ArrayList<Integer> expected1 = new ArrayList<>();

    ArrayList<String> strList1 = new ArrayList<>();
    ArrayList<String> strList2 = new ArrayList<>();
    ArrayList<String> expected2 = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Collections.addAll(list1, 1,2,3,4,5,6);
        Collections.addAll(list2, 1,2,5);
        Collections.addAll(expected1, 3,4,6);

        Collections.addAll(strList1, "1","2","3","4","5","6");
        Collections.addAll(strList2, "1","2","5");
        Collections.addAll(expected2, "3","4","6");
    }

    @Test
    public void abc() {
        assertEquals("Integer", TwoIterators.abc(list1.iterator(), list2.iterator()), expected1);
        assertEquals("String", TwoIterators.abc(strList1.iterator(), strList2.iterator()), expected2);
    }

    @After
    public void tearDown() throws Exception {
    }
}