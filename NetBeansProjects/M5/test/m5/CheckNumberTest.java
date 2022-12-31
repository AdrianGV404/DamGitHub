/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m5;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian
 */
public class CheckNumberTest {
    
    public CheckNumberTest() {
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
    @Test
    public void testCheckNumber1() {
        System.out.println("CheckNumber");
        double num = 0;
        int expResult = 0;
        int result = CheckNumber.CheckNumber(num);
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void testCheckNumber2() {
        System.out.println("CheckNumber");
        double num = 10;
        int expResult = 1;
        int result = CheckNumber.CheckNumber(num);
        assertEquals(expResult, result, 0.0);
    }
        @Test
    public void testCheckNumber3() {
        System.out.println("CheckNumber");
        double num = -10;
        int expResult = -1;
        int result = CheckNumber.CheckNumber(num);
        assertEquals(expResult, result, 0.0);
    } 
}
