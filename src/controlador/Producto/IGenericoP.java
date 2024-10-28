/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador.Producto;

import controlador.IGenerico;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelos.MProducto;

/**
 *
 * @author HACK
 */
public interface IGenericoP extends IGenerico{

    void listarCategoriaA(JComboBox combo);

    MProducto consultar(JTextField id);
    
    MProducto consultar(String nombreProducto);

    
}
