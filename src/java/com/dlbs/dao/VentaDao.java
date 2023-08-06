package com.dlbs.dao;

import com.dlbs.database.Conexion;
import com.dlbs.interfaces.crudVenta;
import com.dlbs.modelo.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao implements crudVenta {

    //Estancias necesarias para la logica
    Venta Venta = new Venta();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "";
    String getById = "";
    String add = "call saveSale(?,?,?,?,?,?,?,?)";

    @Override
    public List getAll() {
        ArrayList<Venta> lista = new ArrayList<>();
        return null;
    }

    @Override
    public Venta getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                Venta.setIdVenta(rs.getInt(1));
                Venta.setFkCliente(rs.getInt(2));
                Venta.setCliNombre(rs.getString(3));
                Venta.setCliApellido(rs.getString(4));
                Venta.setVenCodigo(rs.getString(5));
                Venta.setVenFecha(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return Venta;
    }

    public Integer getLastId() {
        int numero = 0;
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("CALL obtenerUltimoID()");
            rs = cs.executeQuery();
            while (rs.next()) {
                numero = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("No hay id: " + ex.getMessage());
            return 0;
        }
        return numero;
    }

    public String saveSale(Venta ven, Detalle Detalle) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setInt(1, ven.getFkCliente());
            cs.setString(2, ven.getVenCodigo());
            cs.setString(3, ven.getVenFecha());
            cs.setDouble(4, ven.getVenTotal());
            cs.setInt(5, Detalle.getFkVenta());
            cs.setInt(6, Detalle.getFkMenu());
            cs.setInt(7, Detalle.getDetCantidad());
            cs.setDouble(8, Detalle.getDetTotal());
            cs.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al crear la Venta: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String add(Venta ven) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateById(Venta ven) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteById(Integer idVenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
