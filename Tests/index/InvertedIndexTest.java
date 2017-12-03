/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import io.FileDocSource;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import score.TFIDFScoringFun;
import tokenizer.IndexingTokenizer;

/**
 *
 * @author lijiaw13
 */
public class InvertedIndexTest {
    
    public InvertedIndexTest() {
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
     * Test of buildIndex method, of class InvertedIndex.
     */
    @Test
    public void testBuildIndex() {
        System.out.println("buildIndex");
        FileDocSource f1 = new FileDocSource("W:\\MIE250\\project5-kizuna02\\files\\Part1\\awards_1990\\awd_1990_00");
        IndexingTokenizer i1 = new IndexingTokenizer();
        TFIDFScoringFun t1 = new TFIDFScoringFun();
        InvertedIndex instance = new InvertedIndex(f1, i1, t1);
        instance.buildIndex();
        
    }

  
    
}
