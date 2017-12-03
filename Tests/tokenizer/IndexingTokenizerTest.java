/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
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
public class IndexingTokenizerTest {
    
    public IndexingTokenizerTest() {
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
     * Test of tokenize method, of class IndexingTokenizer.
     */
    @Test
    public void testTokenize() {
        System.out.println("tokenize");
        String s = "state-of-the-art";
        IndexingTokenizer instance = new IndexingTokenizer();
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList("state-of-the-art"));
        ArrayList<String> result = instance.tokenize(s);
        assertEquals(expResult, result);
       
    }
    
}
