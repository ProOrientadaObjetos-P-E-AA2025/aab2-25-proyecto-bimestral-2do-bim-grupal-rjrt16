package modelo;

import controlador.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void insertarCliente(Cliente c) {
        String sql = "INSERT INTO Cliente (nombre, cedula, ciudad, numeroCelular, marca, modelo, pagoMensual, edad, pais, tipoPlan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getCedula());
            pstmt.setString(3, c.getCiudad());
            pstmt.setString(4, c.getNumeroCelular());
            pstmt.setString(5, c.getMarca());
            pstmt.setString(6, c.getModelo());
            pstmt.setDouble(7, c.getPagoMensual());
            pstmt.setInt(8, c.getEdad());
            pstmt.setString(9, c.getPais());
            pstmt.setInt(10, c.getTipoPlan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        } finally {
            cerrarConexion(conn);
        }
    }

     public static List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT nombre, cedula, ciudad, numeroCelular, marca, modelo, pagoMensual, edad, pais, tipoPlan FROM Cliente";
        
        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getString("nombre"),
                    rs.getString("cedula"),
                    rs.getString("ciudad"),
                    rs.getString("numeroCelular"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getDouble("pagoMensual"),
                    rs.getInt("edad"),
                    rs.getString("pais"),
                    rs.getInt("tipoPlan")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public static Cliente buscarCliente(String cedula) {
        String sql = "SELECT * FROM cliente WHERE cedula = ?";
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getString("nombre"),
                    rs.getString("cedula"),
                    rs.getString("ciudad"),
                    rs.getString("celular"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getDouble("pago"),
                    rs.getInt("edad"),
                    rs.getString("pais"),
                    rs.getInt("tipoPlan")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        } finally {
            cerrarConexion(conn);
        }
        return null;
    }

    public static void actualizarCliente(Cliente c) {
        String sql = "UPDATE cliente SET nombre=?, ciudad=?, celular=?, marca=?, modelo=?, pago=?, edad=?, pais=?, tipoPlan=? WHERE cedula=?";
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getCiudad());
            pstmt.setString(3, c.getNumeroCelular());
            pstmt.setString(4, c.getMarca());
            pstmt.setString(5, c.getModelo());
            pstmt.setDouble(6, c.getPagoMensual());
            pstmt.setInt(7, c.getEdad());
            pstmt.setString(8, c.getPais());
            pstmt.setInt(9, c.getTipoPlan());
            pstmt.setString(10, c.getCedula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            cerrarConexion(conn);
        }
    }

    public static boolean eliminarCliente(String cedula) {
        String sql = "DELETE FROM cliente WHERE cedula = ?";
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cedula);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        } finally {
            cerrarConexion(conn);
        }
    }

    private static void cerrarConexion(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}