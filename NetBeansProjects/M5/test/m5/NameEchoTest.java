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
public class NameEchoTest {

    public NameEchoTest() {
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
    public void testName() {
        System.out.println("Nombre");
        String name = "Adrian";
        NameEcho instance = new NameEcho();
        String expResult = "Adrian";
        String result = instance.Names(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testNameApellido() {
        System.out.println("Nombre y apellido");
        String name = "Adrian Garcia";
        NameEcho instance = new NameEcho();
        String expResult = "Adrian GARCIA";
        String result = instance.Names(name);
        assertEquals(expResult, result);
    }
    @Test
    public void testNamesNombre1Nombre2Apellido() {
        System.out.println("Dos nombres y apelldio");
        String name = "Adrian Adri Garcia";
        NameEcho instance = new NameEcho();
        String expResult = "Adrian Adri GARCIA";
        String result = instance.Names(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testNamesNombre1Nombre2ApellidoApellido2() {
        System.out.println("Dos nombres y dos apellidos");
        String name = "Adrian Garcia Vega Rodríguez";
        NameEcho instance = new NameEcho();
        String expResult = "Adrian Garcia VEGA RODRÍGUEZ";
        String result = instance.Names(name);
        assertEquals(expResult, result);
    }
}
