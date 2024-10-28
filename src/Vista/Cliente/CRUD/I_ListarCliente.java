/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Cliente.CRUD;

import controlador.Cliente.CCliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelos.MCliente;

/**
 *
 * @author HACK
 */
public class I_ListarCliente extends javax.swing.JPanel {

    DefaultTableModel modelo = new DefaultTableModel();
    
    /**
     * Creates new form I_AgregarCliente
     */
    public I_ListarCliente() {
        initComponents();
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap. Paterno");
        modelo.addColumn("Ap. Materno");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");
        modelo.addColumn("Estado");
        
        jtable.setModel(modelo);
        
        llenarTabla();
    }

    private void llenarTabla(){
        List<MCliente> clientes = (new CCliente()).listar();
        
        for (MCliente cliente : clientes) {
            modelo.addRow(cliente.toString().split(","));
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(672, 415));

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nombre", "Ap. Paterno", "Ap. Materno", "Direccion", "Teléfono", "Email", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jtable);
        if (jtable.getColumnModel().getColumnCount() > 0) {
            jtable.getColumnModel().getColumn(0).setPreferredWidth(20);
            jtable.getColumnModel().getColumn(3).setPreferredWidth(60);
            jtable.getColumnModel().getColumn(8).setMinWidth(50);
            jtable.getColumnModel().getColumn(8).setPreferredWidth(50);
            jtable.getColumnModel().getColumn(8).setMaxWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    // End of variables declaration//GEN-END:variables
}
