package com.dlbs.interfaces;

import com.dlbs.modelo.*;
import java.util.List;

public interface crudVenta {

    List getAll();

    Venta getById(Integer id);

    String add(Venta ven);

    String updateById(Venta ven);

    String deleteById(Integer idVenta);
}
