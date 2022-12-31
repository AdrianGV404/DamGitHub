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
public class CompteTest {
    
    public CompteTest() {
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
    public void testDiposita() {
        System.out.println("diposita");
        int pasta = 400;
        Compte instance = new Compte (50,"Adri");
        int expResult = 450;
        int result = instance.diposita(pasta);
        assertEquals(expResult, result);
    }


    @Test
    public void testRetira() {
        System.out.println("retira");
        int pasta = 200;
        String cs = "qwerty1";
        Compte instance = new Compte(200,"qwerty1");
        int expResult = 0;
        int result = instance.retira(pasta, cs);
        assertEquals(expResult, result);
    }

    @Test
    public void testRetira1() {
        System.out.println("retira");
        int pasta = 500;
        String cs = "contra";
        Compte instance = new Compte(200,"contra");
        int expResult = -1;
        int result = instance.retira(pasta, cs);
        assertEquals(expResult, result);
    }

    @Test
    public void testRetira2() {
        System.out.println("retira");
        int pasta = 10;
        String cs = "contra";
        Compte instance = new Compte(200,"password");
        int expResult = -2;
        int result = instance.retira(pasta, cs);
        assertEquals(expResult, result);
    }


    @Test
    public void testGetSaldo() {
        System.out.println("getSaldo");
        Compte instance = new Compte(200,"qwerty1");
        int expResult = 200;
        int result = instance.getSaldo();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetContrasenya() {
        System.out.println("getContrasenya");
        Compte instance = new Compte(200,"qwerty1");
        String expResult = "qwerty1";
        String result = instance.getContrasenya();
        assertEquals(expResult, result);
    }
    
}
