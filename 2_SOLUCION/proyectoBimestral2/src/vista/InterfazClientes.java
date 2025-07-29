package vista;

import controlador.ClienteController;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Plan;
import modelo.PlanPostPagoMinutos;
import modelo.PlanPostPagoMegas;
import modelo.PlanPostPagoMinutosMegas;
import modelo.PlanPostPagoMinutosMegasEconomico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InterfazClientes extends JFrame {
    private JTextArea areaListado;
    private JTextField[] campos;
    private JComboBox<String> comboTipoPlan;

    public InterfazClientes() {
        setTitle("Gestión de Clientes y Planes");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Crear Cliente", crearPanelCrear());
        tabs.add("Listar Clientes", crearPanelListar());
        tabs.add("Actualizar", crearPanelActualizar());
        tabs.add("Eliminar", crearPanelEliminar());
        tabs.add("Calcular Plan", crearPanelCalcularPlan());

        add(tabs);
    }

    private JPanel crearPanelCrear() {
        JPanel panel = new JPanel(new GridLayout(12, 2, 5, 5));
        String[] etiquetas = {"Nombre:", "Cédula:", "Ciudad:", "Celular:", 
                             "Marca:", "Modelo:", "Pago Mensual:", 
                             "Edad:", "País:"};
        campos = new JTextField[etiquetas.length];

        for (int i = 0; i < etiquetas.length; i++) {
            panel.add(new JLabel(etiquetas[i]));
            campos[i] = new JTextField();
            panel.add(campos[i]);
        }

        panel.add(new JLabel("Tipo de Plan:"));
        comboTipoPlan = new JComboBox<>(new String[]{
            "1 - PostPago Minutos", 
            "2 - PostPago Megas", 
            "3 - PostPago Minutos y Megas", 
            "4 - PostPago Económico"});
        panel.add(comboTipoPlan);

        JButton btnGuardar = new JButton("Guardar Cliente");
        btnGuardar.addActionListener(this::crearCliente);
        panel.add(btnGuardar);

        return panel;
    }

    private void crearCliente(ActionEvent e) {
        try {
            String tipoStr = String.valueOf(comboTipoPlan.getSelectedItem()).split(" - ")[0];
            int tipoPlan = Integer.parseInt(tipoStr);
            
            // Obtener datos básicos
            Cliente c = new Cliente(
                campos[0].getText(), 
                campos[1].getText(), 
                campos[2].getText(), 
                campos[3].getText(),
                campos[4].getText(), 
                campos[5].getText(),
                Double.parseDouble(campos[6].getText()),
                Integer.parseInt(campos[7].getText()),
                campos[8].getText(),
                tipoPlan
            );
            
            // Obtener plan específico
            Plan plan = obtenerDatosPlan(tipoPlan);
            if (plan == null) return;
            
            // Calcular pago real
            double pagoCalculado = plan.calcularPagoMensual();
            c.setPagoMensual(pagoCalculado);
            
            // Validar y guardar
            ClienteController cc = new ClienteController();
            if (cc.validarDatosCliente(c)) {
                ClienteDAO.insertarCliente(c);
                String mensaje = String.format(
                    "Cliente guardado!\n" +
                    "Tipo Plan: %s\n" +
                    "Pago Calculado: $%.2f",
                    plan.getTipoPlan(), pagoCalculado);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Plan obtenerDatosPlan(int tipoPlan) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        switch (tipoPlan) {
            case 1: // PostPago Minutos
                JTextField minutos = new JTextField("500");
                JTextField costoMinuto = new JTextField("0.10");
                JTextField tarifaBase = new JTextField("10");
                
                agregarCampo(panel, "Minutos consumidos:", minutos);
                agregarCampo(panel, "Costo por minuto ($):", costoMinuto);
                agregarCampo(panel, "Tarifa base ($):", tarifaBase);
                
                return mostrarDialogoPlan(panel, "Plan PostPago Minutos") ?
                    new PlanPostPagoMinutos(
                        Double.parseDouble(minutos.getText()),
                        Double.parseDouble(costoMinuto.getText()),
                        Integer.parseInt(tarifaBase.getText()),
                        1) : null;
                        
            case 2: // PostPago Megas
                JTextField minsMegas = new JTextField("300");
                JTextField costoMinMegas = new JTextField("0.05");
                JTextField gigas = new JTextField("5");
                JTextField costoGiga = new JTextField("1.50");
                
                agregarCampo(panel, "Minutos consumidos:", minsMegas);
                agregarCampo(panel, "Costo por minuto ($):", costoMinMegas);
                agregarCampo(panel, "Gigas consumidos:", gigas);
                agregarCampo(panel, "Costo por giga ($):", costoGiga);
                
                return mostrarDialogoPlan(panel, "Plan PostPago Megas") ?
                    new PlanPostPagoMegas(
                        Integer.parseInt(minsMegas.getText()),
                        Double.parseDouble(costoMinMegas.getText()),
                        Integer.parseInt(gigas.getText()),
                        Double.parseDouble(costoGiga.getText()),
                        2) : null;
                        
            case 3: // PostPago Minutos y Megas
                JTextField minNacionales = new JTextField("200");
                JTextField costoMinNac = new JTextField("0.10");
                JTextField minInternacionales = new JTextField("100");
                JTextField costoMinInt = new JTextField("0.20");
                
                agregarCampo(panel, "Minutos nacionales:", minNacionales);
                agregarCampo(panel, "Costo min. nacional ($):", costoMinNac);
                agregarCampo(panel, "Minutos internacionales:", minInternacionales);
                agregarCampo(panel, "Costo min. internacional ($):", costoMinInt);
                
                return mostrarDialogoPlan(panel, "Plan Minutos y Megas") ?
                    new PlanPostPagoMinutosMegas(
                        Double.parseDouble(minNacionales.getText()),
                        Double.parseDouble(costoMinNac.getText()),
                        Double.parseDouble(minInternacionales.getText()),
                        Double.parseDouble(costoMinInt.getText()),
                        3) : null;
                        
            case 4: // Económico
                JTextField minEconomico = new JTextField("400");
                JTextField costoMinEco = new JTextField("0.08");
                JTextField megasEco = new JTextField("3");
                JTextField costoGigaEco = new JTextField("1.20");
                JTextField descuento = new JTextField("15");
                
                agregarCampo(panel, "Minutos consumidos:", minEconomico);
                agregarCampo(panel, "Costo por minuto ($):", costoMinEco);
                agregarCampo(panel, "Megas consumidos:", megasEco);
                agregarCampo(panel, "Costo por giga ($):", costoGigaEco);
                agregarCampo(panel, "Descuento (%):", descuento);
                
                return mostrarDialogoPlan(panel, "Plan Económico") ?
                    new PlanPostPagoMinutosMegasEconomico(
                        Integer.parseInt(minEconomico.getText()),
                        Double.parseDouble(costoMinEco.getText()),
                        Double.parseDouble(megasEco.getText()),
                        Double.parseDouble(costoGigaEco.getText()),
                        Double.parseDouble(descuento.getText()),
                        4) : null;
        }
        return null;
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        panel.add(new JLabel(etiqueta));
        panel.add(campo);
    }

    private boolean mostrarDialogoPlan(JPanel panel, String titulo) {
        int result = JOptionPane.showConfirmDialog(this, panel, 
                 titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return result == JOptionPane.OK_OPTION;
    }

    private JPanel crearPanelListar() {
        JPanel panel = new JPanel(new BorderLayout());
        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        
        JButton btnListar = new JButton("Actualizar Lista");
        btnListar.addActionListener(e -> mostrarClientes());
        
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnListar, BorderLayout.SOUTH);
        
        return panel;
    }

    private void mostrarClientes() {
        List<Cliente> lista = ClienteDAO.listarClientes();
        areaListado.setText("");
        
        if (lista.isEmpty()) {
            areaListado.setText("No hay clientes registrados.");
        } else {
            for (Cliente c : lista) {
                areaListado.append(formatCliente(c) + "\n----------------------\n");
            }
        }
    }

    private String formatCliente(Cliente c) {
        String tipoPlan = "";
        switch (c.getTipoPlan()) {
            case 1: tipoPlan = "PostPago Minutos"; break;
            case 2: tipoPlan = "PostPago Megas"; break;
            case 3: tipoPlan = "PostPago Minutos y Megas"; break;
            case 4: tipoPlan = "PostPago Económico"; break;
        }
        
        return String.format(
            "Nombre: %s\nCédula: %s\nCiudad: %s\nCelular: %s\n" +
            "Marca: %s\nModelo: %s\nPago Mensual: $%.2f\n" +
            "Edad: %d\nPaís: %s\nTipo Plan: %s",
            c.getNombre(), c.getCedula(), c.getCiudad(), c.getNumeroCelular(),
            c.getMarca(), c.getModelo(), c.getPagoMensual(),
            c.getEdad(), c.getPais(), tipoPlan);
    }

    private JPanel crearPanelActualizar() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        JTextField txtCedula = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtCiudad = new JTextField();
        JTextField txtPago = new JTextField();
        
        panel.add(new JLabel("Cédula del cliente:"));
        panel.add(txtCedula);
        panel.add(new JLabel("Nuevo nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nueva ciudad:"));
        panel.add(txtCiudad);
        panel.add(new JLabel("Nuevo pago mensual:"));
        panel.add(txtPago);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> {
            try {
                Cliente c = ClienteDAO.buscarCliente(txtCedula.getText());
                if (c != null) {
                    if (!txtNombre.getText().isEmpty()) c.setNombre(txtNombre.getText());
                    if (!txtCiudad.getText().isEmpty()) c.setCiudad(txtCiudad.getText());
                    if (!txtPago.getText().isEmpty()) c.setPagoMensual(Double.parseDouble(txtPago.getText()));
                    
                    ClienteDAO.actualizarCliente(c);
                    JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(btnActualizar);
        return panel;
    }

    private JPanel crearPanelEliminar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JTextField txtCedula = new JTextField(15);
        JButton btnEliminar = new JButton("Eliminar Cliente");
        
        btnEliminar.addActionListener(e -> {
            if (txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cédula", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean eliminado = ClienteDAO.eliminarCliente(txtCedula.getText());
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(new JLabel("Cédula del cliente a eliminar:"));
        panel.add(txtCedula);
        panel.add(btnEliminar);
        
        return panel;
    }

    private JPanel crearPanelCalcularPlan() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelSuperior = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JComboBox<String> comboPlanes = new JComboBox<>(new String[]{
            "1 - PostPago Minutos", 
            "2 - PostPago Megas", 
            "3 - PostPago Minutos y Megas", 
            "4 - PostPago Económico"});
        
        JTextArea txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        
        panelSuperior.add(new JLabel("Seleccione tipo de plan:"));
        panelSuperior.add(comboPlanes);
        
        JButton btnCalcular = new JButton("Calcular Plan");
        btnCalcular.addActionListener(e -> {
            int tipoPlan = Integer.parseInt(String.valueOf(comboPlanes.getSelectedItem()).split(" - ")[0]);
            Plan plan = obtenerDatosPlan(tipoPlan);
            if (plan != null) {
                double resultado = plan.calcularPagoMensual();
                txtResultado.setText(String.format(
                    "Detalles del plan:\n%s\n\n" +
                    "Pago mensual calculado: $%.2f",
                    plan.getTipoPlan(), resultado));
            }
        });
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        panel.add(btnCalcular, BorderLayout.SOUTH);
        
        return panel;
    }

    private void limpiarCampos() {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public static void lanzarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            InterfazClientes interfaz = new InterfazClientes();
            interfaz.setVisible(true);
        });
    }
}