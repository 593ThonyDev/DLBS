package com.dlbs.dao;

import com.dlbs.database.Conexion;
import com.dlbs.interfaces.crudCliente;
import com.dlbs.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements crudCliente {

    //Estancias necesarias para la logica
    Cliente Cliente = new Cliente();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "CALL spLeerClientes()";
    String getById = "CALL spObtenerClientePorID(?)";
    String add = "CALL spCrearCliente(?,?,?,?,?)";
    String update = "CALL spActualizarCliente(?,?,?,?,?,?)";
    String delete = "CALL spEliminarCliente(?)";

    @Override
    public List getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setCliNombre(rs.getString(2));
                cli.setCliApellido(rs.getString(3));
                cli.setCliCedula(rs.getString(4));
                cli.setCliEmail(rs.getString(5));
                cli.setCliTelefono(rs.getString(6));
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                Cliente.setIdCliente(rs.getInt(1));
                Cliente.setCliNombre(rs.getString(2));
                Cliente.setCliApellido(rs.getString(3));
                Cliente.setCliCedula(rs.getString(4));
                Cliente.setCliEmail(rs.getString(5));
                Cliente.setCliTelefono(rs.getString(6));
                }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return  Cliente;
    }

    @Override
    public String add(Cliente cli) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setString(1, cli.getCliNombre());
            cs.setString(2,cli.getCliApellido());
            cs.setString(3, cli.getCliCedula());
            cs.setString(4, cli.getCliEmail());
            cs.setString(5, cli.getCliTelefono());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String updateById(Cliente cli) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(update);
            cs.setInt(1, cli.getIdCliente());
            cs.setString(2, cli.getCliNombre());
            cs.setString(3,cli.getCliApellido());
            cs.setString(4, cli.getCliCedula());
            cs.setString(5, cli.getCliEmail());
            cs.setString(6, cli.getCliTelefono());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            return "no actualizado";
        }
        return "actualizado";
    }

    @Override
    public String deleteById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(delete);
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
            return "no eliminado";
        }
        return "eliminado";
    }

}
