package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.HormigaBL;
import DataAccess.DTO.PRHormigaDTO;

public class HormigaPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private HormigaBL hormigaBL = new HormigaBL();
    private JTextField txtNombre, txtObservacion, txtIdHormigaTipo;
    private JButton btnNuevo, btnGuardar, btnEliminar, btnCancelar;

    public HormigaPanel() {
        setLayout(new BorderLayout());
        initializeUI();
        loadHormigas();
    }

    private void initializeUI() {
        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Observación"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);
        inputPanel.add(new JLabel("IdHormigaTipo:"));
        txtIdHormigaTipo = new JTextField();
        inputPanel.add(txtIdHormigaTipo);
        inputPanel.add(new JLabel("Observación:"));
        txtObservacion = new JTextField();
        inputPanel.add(txtObservacion);

        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNuevo);
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnCancelar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> nuevo());
        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());
        btnCancelar.addActionListener(e -> cancelar());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtNombre.setText(model.getValueAt(row, 1).toString());
                    txtObservacion.setText(model.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void loadHormigas() {
        try {
            model.setRowCount(0); // Limpiar el modelo
            for (PRHormigaDTO hormiga : hormigaBL.readAll()) {
                model.addRow(new Object[]{hormiga.getIdHormiga(), hormiga.getCodigoHormiga()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar hormigas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nuevo() {
        txtNombre.setText("");
        txtObservacion.setText("");
    }

    private void guardar() {
        try {
            PRHormigaDTO hormiga = new PRHormigaDTO();
            hormiga.setCodigoHormiga(txtNombre.getText());
            hormiga.setIdHormigaClasifacacion(Integer.parseInt(txtIdHormigaTipo.getText()));
            hormigaBL.create(hormiga);
            loadHormigas(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar hormiga: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) model.getValueAt(row, 0);
            try {
                hormigaBL.delete(id);
                loadHormigas(); // Recargar la lista
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar hormiga: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelar() {
        nuevo(); 
    }
}
