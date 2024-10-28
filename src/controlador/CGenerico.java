/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HACK
 */
public class CGenerico implements IGenerico {

    @Override
    public void enableControl(JTextField txt) {
        txt.setEnabled(true);
        txt.setEditable(true);
        txt.setBackground(new Color(255, 255, 255));
    }

    @Override
    public void enableControl(JComboBox combo) {
        combo.setEnabled(true);
        combo.setBackground(new Color(255, 255, 255));
    }

    @Override
    public void enableControl(JButton btn) {
        btn.setEnabled(true);
        btn.setBackground(new Color(255, 255, 255));
    }

    @Override
    public void disableControl(JTextField txt) {
        txt.setEnabled(false);
        txt.setEditable(false);
        txt.setBackground(new Color(0xD3D3D3));
    }

    @Override
    public void disableControl(JComboBox combo) {
        combo.setEnabled(false);
        combo.setBackground(new Color(0xD3D3D3));
    }

    @Override
    public void disableControl(JButton btn) {
        btn.setEnabled(false);
        btn.setBackground(new Color(0xD3D3D3));
    }

    @Override
    public void clear(JTextField txt) {
        txt.setText("");
    }

    @Override
    public void clear(JLabel lbl) {
        lbl.setText("");
    }

    @Override
    public void clear(JComboBox combo, boolean elminarItems) {
        if (elminarItems) {
            combo.removeAllItems();
        }
        noSelectItem(combo);
    }

    @Override
    public void clearItems(JComboBox combo) {
        combo.removeAllItems();
    }

    @Override
    public void noSelectItem(JComboBox combo) {
        combo.setSelectedIndex(-1);
    }

    @Override
    public void setContent(String content, JCheckBox chx){
        chx.setText(content);
    }
    
    @Override
    public void setContent(String content, JButton btn) {
        btn.setText(content);
    }

    @Override
    public void setContent(String content, JTextField txt) {
        txt.setText(content);
    }

    @Override
    public void setContent(int content, JTextField txt) {
        txt.setText(String.valueOf(content));
    }

    @Override
    public void setContent(double content, JTextField txt) {
        txt.setText(String.valueOf(content));
    }

    @Override
    public void setContent(String content, JLabel lbl) {
        lbl.setText(content);
    }

    @Override
    public void setContent(int content, JLabel lbl) {
        lbl.setText(String.valueOf(content));
    }

    @Override
    public void setContent(int index, JComboBox combo) {
        combo.setSelectedIndex(index);
    }

    @Override
    public void setContent(String content, JComboBox combo) {
        combo.setSelectedItem(content);
    }

    @Override
    public String textTipoEstadoProducto(int estado) {
        return estado == 0 ? "Inactivo" : "Activo";
    }

    @Override
    public String text(JTextField txt) {
        return txt.getText();
    }

    @Override
    public String text(JComboBox combo) {
        return combo.getSelectedItem() != null ? combo.getSelectedItem().toString() : "";
    }

    @Override
    public int numberEntero(JTextField txt) {

        return txt.getText().isBlank() ? -1 : Integer.parseInt(txt.getText());
    }

    @Override
    public double numberReal(JTextField txt) {
        return txt.getText().isBlank() ? -1 : Double.parseDouble(txt.getText());
    }

    @Override
    public int indexCombo(JComboBox combo) {
        return combo.getSelectedIndex();
    }

    @Override
    public void setEstablish(boolean establis, JCheckBox chx) {
        chx.setSelected(establis);
    }

    @Override
    public void focus(JTextField txt) {
        txt.requestFocus();
    }

    @Override
    public void focus(JComboBox combo) {
        combo.requestFocus();
    }

    @Override
    public void focus(JCheckBox chx) {
        chx.requestFocus();
    }

    @Override
    public void focus(JButton btn) {
        btn.requestFocus();
    }

    public boolean isEmpty(JTextField txt){
        return txt.getText().isBlank();
    }
    
}
