package com.dlbs.controlador;

import com.dlbs.dao.VentaDao;
import com.dlbs.modelo.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

public class Controlador extends HttpServlet {

    String LOGIN = "index.jsp";
    String VENTA = "vista/Ventas.jsp";
    String CLIENTES = "vista/Clientes.jsp";
    String MENU = "vista/Menu.jsp";
    String USUARIOS = "vista/Usuarios.jsp";
    String acceso = "";
    VentaDao dao = new VentaDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("p");
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (page) {
                case "venta":
                    UUID uuid = UUID.randomUUID();
                    request.setAttribute("codVenta", uuid.toString());
                    request.setAttribute("idVenta", dao.getLastId() + 1);
                    request.setAttribute("divMsj", "sr-only");
                    acceso = VENTA;
                    break;
                case "clientes":
                    request.setAttribute("divMsj", "sr-only");
                    acceso = CLIENTES;
                    break;
                case "menu":
                    request.setAttribute("divMsj", "sr-only");
                    acceso = MENU;
                    break;
                case "usuarios":
                    request.setAttribute("divMsj", "sr-only");
                    acceso = USUARIOS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(acceso);
            dispatcher.forward(request, response);
        }
    }

}
