/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HACK
 */
public interface IGenerico {

    //Generico
    void enableControl(JTextField txt);

    void enableControl(JComboBox combo);

    void enableControl(JButton btn);

    void disableControl(JTextField txt);

    void disableControl(JComboBox combo);

    void disableControl(JButton btn);

    void clear(JTextField txt);

    void clear(JLabel lbl);

    void clear(JComboBox combo, boolean elminarItems);

    void clearItems(JComboBox combo);

    void noSelectItem(JComboBox combo);

    void setContent(String content, JCheckBox chx);
    
    void setContent(String content, JButton btn);
    
    void setContent(String content, JTextField txt);

    void setContent(int content, JTextField txt);

    void setContent(double content, JTextField txt);

    void setContent(String content, JLabel lbl);

    void setContent(int content, JLabel lbl);

    void setContent(int content, JComboBox combo);

    public void setContent(String content, JComboBox combo);

    public String textTipoEstadoProducto(int estado);

    public String text(JTextField txt);

    public String text(JComboBox combo);

    public int numberEntero(JTextField txt);

    public double numberReal(JTextField txt);

    public int indexCombo(JComboBox combo);
    
    public void setEstablish(boolean establis, JCheckBox chx);
    
    public void focus(JTextField txt);
    
    public void focus(JComboBox combo);
    
    public void focus(JCheckBox chx);
    
    public void focus(JButton btn);
    
    public boolean isEmpty(JTextField txt);
}
