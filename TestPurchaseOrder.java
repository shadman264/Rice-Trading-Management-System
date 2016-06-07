/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import business.PurchaseOrderController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arif
 */
public class TestPurchaseOrder {

    final double DELTA = 1e-15;

    public TestPurchaseOrder() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestAddThem() {
        PurchaseOrderController ob = new PurchaseOrderController();
        assertEquals(0.0, ob.addThem(0.0, 0.0), DELTA);
        assertEquals(11, ob.addThem(5.25, 5.75), DELTA);
        assertEquals(1.75, ob.addThem(-1.25, 3.0), DELTA);
        assertEquals(-1.75, ob.addThem(-3, 1.25), DELTA);
        assertEquals(1.75, ob.addThem(3.0, -1.25), DELTA);
        assertEquals(-1.75, ob.addThem(1.25, -3), DELTA);
        assertEquals(-5.25, ob.addThem(-3, -2.25), DELTA);

    }

    @Test
    public void TestSubstractThem() {
        PurchaseOrderController ob = new PurchaseOrderController();
        assertEquals(0.0, ob.substractThem(0.0, 0.0), DELTA);
        assertEquals(-.5, ob.substractThem(5.25, 5.75), DELTA);
        assertEquals(-4.25, ob.substractThem(-1.25, 3.0), DELTA);
        assertEquals(-4.25, ob.substractThem(-3, 1.25), DELTA);
        assertEquals(4.25, ob.substractThem(3.0, -1.25), DELTA);
        assertEquals(4.25, ob.substractThem(1.25, -3), DELTA);
        assertEquals(-.75, ob.substractThem(-3, -2.25), DELTA);

    }

    @Test
    public void TestGetKG() {
        PurchaseOrderController ob = new PurchaseOrderController();
        assertEquals(0, ob.getKG(0, 0.0), DELTA);
        assertEquals(12, ob.getKG(3, 4), DELTA);
        assertEquals(-7.5, ob.getKG(-3, 2.5), DELTA);
        assertEquals(-7.5, ob.getKG(2.5, -3), DELTA);
        assertEquals(5.25, ob.getKG(-3.5, -1.5), DELTA);
        assertEquals(5.25, ob.getKG(1.5, 3.5), DELTA);

    }
    @Test
    public void TestGetMON() {
        PurchaseOrderController ob = new PurchaseOrderController();
        assertEquals(0, ob.getMON(0, 0.0), DELTA);
        assertEquals(12*37.324, ob.getMON(3, 4), DELTA);
        assertEquals(-7.5*37.324, ob.getMON(-3, 2.5), DELTA);
        assertEquals(-7.5*37.324, ob.getMON(2.5, -3), DELTA);
        assertEquals(5.25*37.324, ob.getMON(-3.5, -1.5), DELTA);
        assertEquals(5.25*37.324, ob.getMON(1.5, 3.5), DELTA);

    }

}
