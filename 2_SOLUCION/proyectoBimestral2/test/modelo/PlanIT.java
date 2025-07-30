/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Smart
 */
public class PlanIT {
    
    public PlanIT() {
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
     * Test of getTipoPlan method, of class Plan.
     */
    @Test
    public void testGetTipoPlan() {
        System.out.println("getTipoPlan");
        Plan instance = new PlanImpl();
        String expResult = "";
        String result = instance.getTipoPlan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularPagoMensual method, of class Plan.
     */
    @Test
    public void testCalcularPagoMensual() {
        System.out.println("calcularPagoMensual");
        Plan instance = new PlanImpl();
        double expResult = 0.0;
        double result = instance.calcularPagoMensual();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlanImpl extends Plan {

        public double calcularPagoMensual() {
            return 0.0;
        }
    }
    
}
