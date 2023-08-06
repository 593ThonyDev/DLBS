package com.dlbs.interfaces;

import com.dlbs.modelo.*;
import java.util.List;

public interface crudCliente {

    List getAll();

    Cliente getById(Integer id);

    String add(Cliente cli);

    String updateById(Cliente cli);

    String deleteById(Integer idCliente);

}
