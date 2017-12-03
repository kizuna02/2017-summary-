/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lijiaw13
 */
public class SortedDocScoreTest {
    
    public SortedDocScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareTo method, of class SortedDocScore.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        SortedDocScore s = new SortedDocScore(1.5, 0, "string1");
        Object o = s;
        SortedDocScore instance = new SortedDocScore(3.0, 1, "string2");
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        
    }

    
}
