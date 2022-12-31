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
public class EmployeeTest {

    public EmployeeTest() {
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
    public void testEquals0() {
        System.out.println("equals");
        Object obj = (new Employee(1, "Peter", 15000));
        Employee instance = (new Employee(1, "Peter", 15000));
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals1() {
        System.out.println("equals");
        Object obj = (new Employee(1, "Peter", 15000));
        Employee instance = (new Employee(2, "Oscar", 120));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetEmpNameWithHighestSalary() {
        System.out.println("getEmpNameWithHighestSalary");
        String expResult = "Peter";
        String result = Employee.getEmpNameWithHighestSalary();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetHighestPaidEmployee() {
        System.out.println("getHighestPaidEmployee");
        Employee expResult = (new Employee(1, "Peter", 15000));
        Employee result = Employee.getHighestPaidEmployee();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSmartestEmployee() {
        System.out.println("getSmartestEmployee");
        Employee expResult = (new Employee(2, "Oscar", 120));
        Employee result = Employee.getSmartestEmployee();
        assertEquals(expResult, result);
    }
}