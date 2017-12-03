/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

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
public class FileDocSourceTest {
    
    public FileDocSourceTest() {
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
     * Test of getNumDocs method, of class FileDocSource.
     */
    @Test
    public void testGetNumDocs() {
        System.out.println("getNumDocs");
        FileDocSource instance = new FileDocSource("W:\\MIE250\\project5-kizuna02\\files\\Part1\\awards_1990\\awd_1990_00");
        int expResult = 380;
        int result = instance.getNumDocs();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDoc method, of class FileDocSource.
     */
    @Test
    public void testGetDoc() {
        System.out.println("getDoc");
        int id = 0;
        FileDocSource instance = new FileDocSource("W:\\MIE250\\project5-kizuna02\\files\\Part1\\myOwnTestCases");
        String expResult = "my own test case";
        String result = instance.getDoc(id);
        assertEquals(expResult, result);
        
    }
    
}
