package com.dlbs.controlador;

import com.dlbs.dao.*;
import com.dlbs.modelo.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Menus extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "vista/MenuCrear.jsp";
    String EDITAR = "vista/MenuActualizar.jsp";
    String REGISTROS = "vista/Menu.jsp";

    //Objetos
    Menu men = new Menu();
    MenuDao dao = new MenuDao();
    //
    String mensaje;
    //
    Integer idMenu = 0;
    String menNombre;
    String menCodigo;
    Integer menStock;
    Double menPrecio;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un Usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "guardar":
                    menNombre = request.getParameter("proNombre");
                    menCodigo = request.getParameter("proCodigo");
                    menStock = Integer.parseInt(request.getParameter("proStock"));
                    menPrecio = Double.parseDouble(request.getParameter("proPrecio"));
                    men = new Menu(menNombre, menCodigo, menStock, menPrecio);
                    mensaje = dao.add(men);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "creado") ? "visible" : "sr-only");
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idMenu = Integer.parseInt(request.getParameter("idProducto"));
                    menNombre = request.getParameter("proNombre");
                    menCodigo = request.getParameter("proCodigo");
                    menStock = Integer.parseInt(request.getParameter("proStock"));
                    menPrecio = Double.parseDouble(request.getParameter("proPrecio"));
                    men = new Menu(idMenu, menNombre, menCodigo, menStock, menPrecio);
                    mensaje = dao.updateById(men);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "actualizado") ? "visible" : "sr-only");
                    System.out.println(dao.updateById(men));
                    acceso = REGISTROS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un Usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "crear":
                    acceso = CREAR;
                    break;
                case "editar":
                    request.setAttribute("idMenu", request.getParameter("idMenu"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idMenu = Integer.parseInt((String) request.getParameter("idMenu"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    mensaje = dao.deleteById(idMenu);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "eliminado") ? "visible" : "sr-only");
                    acceso = REGISTROS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

}
