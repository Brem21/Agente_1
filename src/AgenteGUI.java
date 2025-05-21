import javax.swing.*;
import java.awt.event.*;

public class AgenteGUI extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtID;
    private JTextField txtMision;
    private JTextField textPago;
    private JComboBox comboPeligrosidad;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnAgregar;
    private JButton btnListar;

    private ListaSimpleAgentes lista = new ListaSimpleAgentes();

    public AgenteGUI() {
        setTitle("Gestión de Agentes");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // Agregar agente
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = txtID.getText().trim();
                    String nombre = txtNombre.getText().trim();
                    String mision = txtMision.getText().trim();
                    int peligrosidad = Integer.parseInt(comboPeligrosidad.getSelectedItem().toString());
                    double pago = Double.parseDouble(textPago.getText().trim());

                    if (id.isEmpty() || nombre.isEmpty() || mision.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Completa todos los campos.");
                        return;
                    }

                    Agente nuevo = new Agente(id, nombre, mision, peligrosidad, pago);
                    lista.agregar(nuevo);
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Agente agregado correctamente.");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en datos numéricos. Revisa los campos.");
                }
            }
        });

        // Eliminar agente con ventana emergente
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Ingrese el ID del agente a eliminar:");
                if (id == null || id.trim().isEmpty()) return;

                boolean eliminado = lista.eliminar(id.trim());

                if (!eliminado) {
                    JOptionPane.showMessageDialog(null, "Agente no encontrado.");
                } else {
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Agente eliminado correctamente.");
                }
            }
        });

        // Modificar con ventanas emergentes
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idOriginal = JOptionPane.showInputDialog("Ingrese el ID del agente a modificar:");
                if (idOriginal == null || idOriginal.trim().isEmpty()) return;

                Nodo nodo = lista.buscarNodo(idOriginal.trim());
                if (nodo == null) {
                    JOptionPane.showMessageDialog(null, "Agente no encontrado.");
                    return;
                }

                try {
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", nodo.agente.nombre);
                    if (nuevoNombre == null) return;

                    String nuevoID = JOptionPane.showInputDialog("Nuevo ID:", nodo.agente.id);
                    if (nuevoID == null) return;

                    String nuevaMision = JOptionPane.showInputDialog("Nueva misión:", nodo.agente.mision);
                    if (nuevaMision == null) return;

                    String peligStr = JOptionPane.showInputDialog("Nueva peligrosidad (1 a 5):", nodo.agente.peligrosidad);
                    if (peligStr == null) return;
                    int nuevaPeligrosidad = Integer.parseInt(peligStr.trim());

                    String pagoStr = JOptionPane.showInputDialog("Nuevo pago mensual:", nodo.agente.pagoMensual);
                    if (pagoStr == null) return;
                    double nuevoPago = Double.parseDouble(pagoStr.trim());

                    nodo.agente.nombre = nuevoNombre.trim();
                    nodo.agente.id = nuevoID.trim();
                    nodo.agente.mision = nuevaMision.trim();
                    nodo.agente.peligrosidad = nuevaPeligrosidad;
                    nodo.agente.pagoMensual = nuevoPago;

                    JOptionPane.showMessageDialog(null, "Agente modificado correctamente.");
                    limpiarCampos();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ingreso numérico inválido.");
                }
            }
        });

        // Mostrar lista de agentes en una ventana emergente con scroll
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String listaTexto = lista.listar();

                if (listaTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay agentes registrados.");
                } else {
                    JTextArea textArea = new JTextArea(listaTexto);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
                    JOptionPane.showMessageDialog(null, scrollPane, "Agentes Registrados", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtID.setText("");
        txtMision.setText("");
        textPago.setText("");
        comboPeligrosidad.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AgenteGUI();
    }
}
