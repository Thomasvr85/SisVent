/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Cliente;

import Vista.Cliente.CRUD.*;
import Vista.util.Mensaje;
import controlador.CGenerico;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelos.MCliente;

/**
 *
 * @author HACK
 */
public class CCliente extends CGenerico {

    public int agregar(MCliente c) {
        int rowsAfected = 0;

        if (!c.emptyFields() && consultar(c.getDniCliente()) == null) {
            try (Connection con = Basico.Obtener.Conexion()) {

                int i = 0;
                PreparedStatement preparedStatement = con.prepareStatement("insert into cliente values (default, ?, ?, ?, ?, ?, ?, ?, default)");

                preparedStatement.setString(++i, c.getDniCliente());
                preparedStatement.setString(++i, c.getNombreClienteCliente());
                preparedStatement.setString(++i, c.getApellidoPaternoCliente());
                preparedStatement.setString(++i, c.getApellidoMaternoCliente());
                preparedStatement.setString(++i, c.getDireccionCliente());
                preparedStatement.setString(++i, c.getTelCliente());
                preparedStatement.setString(++i, c.getEmailCliente());

                rowsAfected = preparedStatement.executeUpdate();
                if (rowsAfected != 0) {
                    Mensaje.INFORMACION("Cliente agregado SATISFACTORIAMENTE");
                } else {
                    Mensaje.ERROR("Cliente NO agregado");
                }
            } catch (SQLException ex) {
                Mensaje.ERROR(ex.getMessage(), "Agregar Cliente");
            }
        } else {
            Mensaje.ERROR("El cliente ya existe en la Base de Datos");
        }

        return rowsAfected;
    }

    public List<MCliente> listar() {
        List<MCliente> clientes = new ArrayList<>();
        try (Connection con = Basico.Obtener.Conexion()) {
            PreparedStatement statement = con.prepareStatement("select * from Cliente");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int i = 0;
                MCliente cliente = new MCliente();
                cliente.setIdCliente(rs.getInt(++i));
                cliente.setDniCliente(rs.getString(++i));
                cliente.setNombreClienteCliente(rs.getString(++i));
                cliente.setApellidoPaternoCliente(rs.getString(++i));
                cliente.setApellidoMaternoCliente(rs.getString(++i));
                cliente.setDireccionCliente(rs.getString(++i));
                cliente.setTelCliente(rs.getString(++i));
                cliente.setEmailCliente(rs.getString(++i));
                cliente.setEstadoCliente(rs.getInt(++i));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Mensaje.ERROR(ex.getMessage(), "Listar Cliente");
        }

        return clientes;
    }

    public MCliente consultar(JTextField dni) {
        return consultar(dni.getText());
    }

    public MCliente consultar(JTextField dni, boolean mensaje) {
        return consultar(dni.getText(), mensaje);
    }

    public MCliente consultar(String dni) {
        return consultar(dni, false);
    }

    public MCliente consultar(String dni, boolean mensaje) {
        MCliente cliente = null;

        try (Connection con = Basico.Obtener.Conexion()) {
            PreparedStatement preparedStatement = con.prepareStatement("select * from Cliente where dniCliente = ?");
            preparedStatement.setString(1, dni);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int i = 0;
                cliente = new MCliente();
                cliente.setIdCliente(rs.getInt(++i));
                cliente.setDniCliente(rs.getString(++i));
                cliente.setNombreClienteCliente(rs.getString(++i));
                cliente.setApellidoPaternoCliente(rs.getString(++i));
                cliente.setApellidoMaternoCliente(rs.getString(++i));
                cliente.setDireccionCliente(rs.getString(++i));
                cliente.setTelCliente(rs.getString(++i));
                cliente.setEmailCliente(rs.getString(++i));
                cliente.setEstadoCliente(rs.getInt(++i));
            } else {
                if (mensaje) {
                    Mensaje.ERROR("Cliente NO ubicado");
                }
            }

        } catch (SQLException ex) {
            Mensaje.ERROR(ex.getMessage(), "Consultar Cliente");
        }

        return cliente;
    }

    public int desvincular(JTextField dni) {
        String _dni = dni.getText();
        int rowsAfected = 0, i = 0;
        try (Connection con = Basico.Obtener.Conexion()) {
            PreparedStatement preparedStatement = con.prepareStatement("update cliente set estado = ? where dniCliente = ?");

            preparedStatement.setInt(++i, 0);
            preparedStatement.setString(++i, _dni);

            rowsAfected = preparedStatement.executeUpdate();
            if (rowsAfected != 0) {
                Mensaje.INFORMACION("Cliente desvinculado SATISFACTORIAMENTE");
            } else {
                Mensaje.ERROR("Cliente NO desvinculado");
            }
        } catch (SQLException ex) {
            Mensaje.ERROR(ex.getMessage(), "Desvincular Cliente");
        }

        return rowsAfected;
    }

    public int modificar(MCliente mc) {
        int rowsAfected = 0;
        if (!mc.emptyFields() && mc.estructuraCamposCorrecta()) {

            try (Connection con = Basico.Obtener.Conexion()) {
                int i = 0;
                PreparedStatement preparedStatement = con.prepareStatement("update Cliente set dniCliente = ?, nombreCliente = ?, apellidoPaternoCliente = ?, apellidoMaternoCliente = ?, direccionCliente = ?, telCliente = ?, emailCliente = ?, estado = ? WHERE dniCliente = ?;");

                preparedStatement.setString(++i, mc.getDniCliente());
                preparedStatement.setString(++i, mc.getNombreClienteCliente());
                preparedStatement.setString(++i, mc.getApellidoPaternoCliente());
                preparedStatement.setString(++i, mc.getApellidoMaternoCliente());
                preparedStatement.setString(++i, mc.getDireccionCliente());
                preparedStatement.setString(++i, mc.getTelCliente());
                preparedStatement.setString(++i, mc.getEmailCliente());
                preparedStatement.setInt(++i, mc.getEstadoCliente());

                preparedStatement.setString(++i, mc.getDniCliente());

                rowsAfected = preparedStatement.executeUpdate();
                if (rowsAfected != 0) {
                    Mensaje.INFORMACION("Cliente actualizado SATISFACTORIAMENTE", "Modificacion de cliente");
                } else {
                    Mensaje.ADVERTENCIA("Cliente NO actualziado", "Modificacion de cliente");
                }
            } catch (SQLException ex) {
                Mensaje.ERROR(ex.getMessage(), "Modificar Cliente");
            }
        } else {
            Mensaje.ERROR(mc.getErrores());
        }
        return rowsAfected;
    }


    //Metodos - Modificar
    public MCliente asignarDelPanel(I_ModificarCliente p) {
        MCliente c = new MCliente();
        c.setDniCliente(p.txtDNI.getText());
        c.setNombreClienteCliente(p.txtNombre.getText());
        c.setApellidoPaternoCliente(p.txtApePat.getText());
        c.setApellidoMaternoCliente(p.txtApeMat.getText());
        c.setDireccionCliente(p.txtDireccion.getText());
        c.setTelCliente(p.txtTel.getText());
        c.setEmailCliente(p.txtEmail.getText());
        c.setEstadoCliente(p.comboEstado.getSelectedIndex());

        return c;
    }

    public MCliente asignarDelPanel(I_AgregarCliente p) {
        MCliente c = new MCliente();
        c.setDniCliente(p.txtDNI.getText());
        c.setNombreClienteCliente(p.txtNombre.getText());
        c.setApellidoPaternoCliente(p.txtApePat.getText());
        c.setApellidoMaternoCliente(p.txtApeMat.getText());
        c.setDireccionCliente(p.txtDireccion.getText());
        c.setTelCliente(p.txtTel.getText());
        c.setEmailCliente(p.txtEmail.getText());

        return c;
    }

    public void asignarAPanel(MCliente c, I_ModificarCliente p) {
        setContent(c.getIdCliente(), p.txtID);
        setContent(c.getDniCliente(), p.txtDNI);
        setContent(c.getNombreClienteCliente(), p.txtNombre);
        setContent(c.getApellidoPaternoCliente(), p.txtApePat);
        setContent(c.getApellidoMaternoCliente(), p.txtApeMat);
        setContent(c.getDireccionCliente(), p.txtDireccion);
        setContent(c.getTelCliente(), p.txtTel);
        setContent(c.getEmailCliente(), p.txtEmail);
        setContent(c.getEstadoCliente(), p.comboEstado);
    }

    public void asignarAPanel(MCliente c, I_DesvincularCliente p) {
        setContent(c.getIdCliente(), p.txtID);
        setContent(c.getDniCliente(), p.txtDNI);
        setContent(c.getNombreClienteCliente(), p.txtNombre);
        setContent(c.getApellidoPaternoCliente(), p.txtApePat);
        setContent(c.getApellidoMaternoCliente(), p.txtApeMat);
        setContent(c.getDireccionCliente(), p.txtDireccion);
        setContent(c.getTelCliente(), p.txtTel);
        setContent(c.getEmailCliente(), p.txtEmail);
        setContent(textTipoEstadoProducto(c.getEstadoCliente()), p.txtEstado);
    }

    public void asignarAPanel(MCliente c, I_ConsultarCliente p) {
        setContent(c.getIdCliente(), p.txtID);
        setContent(c.getDniCliente(), p.txtDNI);
        setContent(c.getNombreClienteCliente(), p.txtNombre);
        setContent(c.getApellidoPaternoCliente(), p.txtApePat);
        setContent(c.getApellidoMaternoCliente(), p.txtApeMat);
        setContent(c.getDireccionCliente(), p.txtDireccion);
        setContent(c.getTelCliente(), p.txtTel);
        setContent(c.getEmailCliente(), p.txtEmail);
        setContent(textTipoEstadoProducto(c.getEstadoCliente()), p.txtEstado);
    }

    public void clearFields(I_ModificarCliente p) {
        clear(p.txtID);
        clear(p.txtDNI);
        clear(p.txtNombre);
        clear(p.txtApePat);
        clear(p.txtApeMat);
        clear(p.txtDireccion);
        clear(p.txtTel);
        clear(p.txtEmail);

        noSelectItem(p.comboEstado);
    }

    public void clearFields(I_ConsultarCliente p) {
        clear(p.txtID);
        clear(p.txtDNI);
        clear(p.txtNombre);
        clear(p.txtApePat);
        clear(p.txtApeMat);
        clear(p.txtDireccion);
        clear(p.txtTel);
        clear(p.txtEmail);
        clear(p.txtEstado);

    }

    public void clearFields(I_DesvincularCliente p) {
        clear(p.txtID);
        clear(p.txtDNI);
        clear(p.txtNombre);
        clear(p.txtApePat);
        clear(p.txtApeMat);
        clear(p.txtDireccion);
        clear(p.txtTel);
        clear(p.txtEmail);
        clear(p.txtEstado);

    }

    public void clearFields(I_AgregarCliente p) {
        clear(p.lblNCliente);
        clear(p.txtDNI);
        clear(p.txtNombre);
        clear(p.txtApePat);
        clear(p.txtApeMat);
        clear(p.txtDireccion);
        clear(p.txtTel);
        clear(p.txtEmail);
    }

    public void enableControls(I_ModificarCliente p) {
        try {
            enableControl(p.txtID);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtNombre);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtDNI);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtApePat);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtApeMat);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtDireccion);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtTel);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtEmail);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.comboEstado);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.btnBuscarCliente);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.btnModificar);
        } catch (Exception ex) {
        }
    }

    public void enableControls(I_ConsultarCliente p) {
        try {
            enableControl(p.txtID);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtDNI);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtApePat);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtApeMat);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtDireccion);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtTel);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtEmail);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.txtEstado);
        } catch (Exception ex) {
        }
        try {
            enableControl(p.btnBuscarCliente);
        } catch (Exception ex) {
        }
    }

    public void disableControls(I_ModificarCliente p) {

        try {
            disableControl(p.txtID);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtNombre);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtDNI);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtApePat);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtApeMat);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtDireccion);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtTel);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtEmail);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.comboEstado);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.btnBuscarCliente);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.btnModificar);
        } catch (Exception ex) {
        }
    }

    public void disableControls(I_ConsultarCliente p) {
        try {
            disableControl(p.txtID);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtDNI);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtApePat);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtApeMat);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtDireccion);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtTel);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtEmail);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.txtEstado);
        } catch (Exception ex) {
        }
        try {
            disableControl(p.btnBuscarCliente);
        } catch (Exception ex) {
        }
    }

    public int numeroNuevoCliente() {
        return listar().size() + 1;
    }

    public void asignarNumeroNuevoCliente(JLabel lbl) {
        lbl.setText("N° Cliente: " + numeroNuevoCliente());
    }

    public void bloquearNoNumero(java.awt.event.KeyEvent evt) {
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }

    public void bloquearNumeroFueraDeRango(java.awt.event.KeyEvent evt, int limit, JTextField txt) {
        if (Character.isDigit(evt.getKeyChar())) {
            String text = txt.getText();
            if (text.length() > limit - 1) {
                evt.consume();
            }
        }
    }
}
