package com.dlbs.interfaces;

import com.dlbs.modelo.*;
import java.util.List;

public interface crudUsuario {

    List getAll();

    Usuario getById(Integer id);

    String add(Usuario usu);

    String updateById(Usuario usu);

    String deleteById(Integer idUsuario);

    //Login
    Integer getIdUsuario(String usuUsuario);
}
