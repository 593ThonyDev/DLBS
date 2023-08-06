package com.dlbs.controlador;

import com.dlbs.dao.UsuarioDao;
import com.dlbs.modelo.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Usuarios extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "vista/UsuariosCrear.jsp";
    String EDITAR = "vista/UsuariosActualizar.jsp";
    String REGISTROS = "vista/Usuarios.jsp";

    //Objetos
    Usuario usu = new Usuario();
    UsuarioDao dao = new UsuarioDao();
    //
    String mensaje;
    //
    Integer idUsuario = 0;
    Integer usuIntento = 0;
    String usuUsuario;
    String usuClave;

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
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    usu = new Usuario(usuUsuario, usuClave, usuIntento);
                    mensaje = dao.add(usu);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "creado") ? "visible" : "sr-only");
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    usu = new Usuario(idUsuario, usuUsuario, usuClave, 0);
                    mensaje = dao.updateById(usu);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "actualizado") ? "visible" : "sr-only");
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
                    request.setAttribute("idUsuario", request.getParameter("idUsuario"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idUsuario = Integer.parseInt((String) request.getParameter("idUsuario"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    mensaje = dao.deleteById(idUsuario);
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
