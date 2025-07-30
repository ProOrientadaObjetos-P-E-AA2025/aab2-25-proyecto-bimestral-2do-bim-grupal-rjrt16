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
public class PlanPostPagoMinutosMegasEconomicoIT {
    
    public PlanPostPagoMinutosMegasEconomicoIT() {
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
     * Test of calcularPagoMensual method, of class PlanPostPagoMinutosMegasEconomico.
     */
    @Test
    public void testCalcularPagoMensual() {
        System.out.println("calcularPagoMensual");
        PlanPostPagoMinutosMegasEconomico instance = null;
        double expResult = 0.0;
        double result = instance.calcularPagoMensual();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
