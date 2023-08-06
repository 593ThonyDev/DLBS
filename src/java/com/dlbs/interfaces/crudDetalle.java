package com.dlbs.interfaces;

import com.dlbs.modelo.*;
import java.util.List;

public interface crudDetalle {

    List getAll();

    Detalle getById(Integer id);

    String add(Detalle det);

    String updateById(Detalle det);

    String deleteById(Integer idDetalle);
}
