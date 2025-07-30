/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import java.util.List;
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
public class ClienteDAOIT {
    
    public ClienteDAOIT() {
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
     * Test of insertarCliente method, of class ClienteDAO.
     */
    @Test
    public void testInsertarCliente() {
        System.out.println("insertarCliente");
        Cliente c = null;
        ClienteDAO.insertarCliente(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarClientes method, of class ClienteDAO.
     */
    @Test
    public void testListarClientes() {
        System.out.println("listarClientes");
        List<Cliente> expResult = null;
        List<Cliente> result = ClienteDAO.listarClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCliente method, of class ClienteDAO.
     */
    @Test
    public void testBuscarCliente() {
        System.out.println("buscarCliente");
        String cedula = "";
        Cliente expResult = null;
        Cliente result = ClienteDAO.buscarCliente(cedula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCliente method, of class ClienteDAO.
     */
    @Test
    public void testActualizarCliente() {
        System.out.println("actualizarCliente");
        Cliente c = null;
        ClienteDAO.actualizarCliente(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCliente method, of class ClienteDAO.
     */
    @Test
    public void testEliminarCliente() {
        System.out.println("eliminarCliente");
        String cedula = "";
        boolean expResult = false;
        boolean result = ClienteDAO.eliminarCliente(cedula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
