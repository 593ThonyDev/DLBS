package com.dlbs.dao;

import com.dlbs.database.Conexion;
import com.dlbs.interfaces.*;
import com.dlbs.modelo.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements crudMenu {

    //Estancias necesarias para la logica
    Menu Menu = new Menu();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "CALL spLeerMenus()";
    String getById = "CALL spObtenerMenuPorID(?)";
    String add = "CALL spCrearMenu(?,?,?,?)";
    String update = "CALL spActualizarMenu(?,?,?,?,?)";
    String delete = "CALL spEliminarMenuPorID(?)";

    @Override
    public List getAll() {
        ArrayList<Menu> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                Menu pro = new Menu();
                pro.setIdMenu(rs.getInt(1));
                pro.setMenNombre(rs.getString(2));
                pro.setMenCodigo(rs.getString(3));
                pro.setMenStock(rs.getInt(4));
                pro.setMenPrecio(rs.getDouble(5));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Menu getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                Menu.setIdMenu(rs.getInt(1));
                Menu.setMenNombre(rs.getString(2));
                Menu.setMenCodigo(rs.getString(3));
                Menu.setMenStock(rs.getInt(4));
                Menu.setMenPrecio(rs.getDouble(5));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return Menu;
    }

    @Override
    public String add(Menu men) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setString(1, men.getMenNombre());
            cs.setString(2, men.getMenCodigo());
            cs.setInt(3, men.getMenStock());
            cs.setDouble(4, men.getMenPrecio());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String updateById(Menu men) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(update);
            cs.setInt(1, men.getIdMenu());
            cs.setString(2, men.getMenNombre());
            cs.setString(3, men.getMenCodigo());
            cs.setInt(4, men.getMenStock());
            cs.setDouble(5, men.getMenPrecio());
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
