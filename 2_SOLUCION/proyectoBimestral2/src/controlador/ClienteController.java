package controlador;

import javax.swing.JOptionPane;
import modelo.Cliente;

public class ClienteController {
    
    public boolean validarDatosCliente(Cliente cliente) {
        if (cliente == null) {
            mostrarError("El cliente no puede ser nulo");
            return false;
        }
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            mostrarError("El nombre no puede estar vacío.");
            return false;
        }
        
        if (cliente.getCedula() == null || !cliente.getCedula().matches("\\d{10}")) {
            mostrarError("La cédula debe tener exactamente 10 dígitos.");
            return false;
        }
        
        if (cliente.getCiudad() == null || cliente.getCiudad().isBlank()) {
            mostrarError("Debe ingresar la ciudad.");
            return false;
        }
        
        if (cliente.getNumeroCelular() == null || !cliente.getNumeroCelular().matches("\\d{10}")) {
            mostrarError("El número celular debe tener 10 dígitos.");
            return false;
        }
        
        if (cliente.getMarca() == null || cliente.getMarca().isBlank()) {
            mostrarError("Debe ingresar la marca del teléfono.");
            return false;
        }
        
        if (cliente.getModelo() == null || cliente.getModelo().isBlank()) {
            mostrarError("Debe ingresar el modelo del teléfono.");
            return false;
        }
        
        if (cliente.getPagoMensual() < 0) {
            mostrarError("El pago mensual no puede ser negativo.");
            return false;
        }
        
        if (cliente.getEdad() < 12 || cliente.getEdad() > 110) {
            mostrarError("Edad inválida. Debe estar entre 12 y 110.");
            return false;
        }
        
        if (cliente.getPais() == null || cliente.getPais().isBlank()) {
            mostrarError("Debe ingresar el país.");
            return false;
        }
        
        if (cliente.getTipoPlan() < 1 || cliente.getTipoPlan() > 4) {
            mostrarError("Tipo de plan inválido. Debe ser entre 1 y 4.");
            return false;
        }
        
        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}