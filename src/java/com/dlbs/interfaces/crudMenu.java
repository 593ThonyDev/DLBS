package com.dlbs.interfaces;

import com.dlbs.modelo.*;
import java.util.List;

public interface crudMenu {

    List getAll();

    Menu getById(Integer id);

    String add(Menu men);

    String updateById(Menu men);

    String deleteById(Integer idMenu);
}
