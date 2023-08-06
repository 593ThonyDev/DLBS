package com.dlbs.controlador;

import com.dlbs.modelo.*;
import com.dlbs.dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Clientes extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "vista/ClientesCrear.jsp";
    String EDITAR = "vista/ClientesActualizar.jsp";
    String REGISTROS = "vista/Clientes.jsp";

    //Objetos
    Cliente cli = new Cliente();
    ClienteDao dao = new ClienteDao();
    //
    Integer idCliente = 0;
    String cliNombre;
    String cliApellido;
    String cliCedula;
    String cliEmail;
    String cliTelefono;
    String mensaje;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "guardar":
                    cliNombre = request.getParameter("cliNombre");
                    cliApellido = request.getParameter("cliApellido");
                    cliCedula = request.getParameter("cliCedula");
                    cliEmail = request.getParameter("cliEmail");
                    cliTelefono = request.getParameter("cliTelefono");
                    cli = new Cliente(cliNombre, cliApellido, cliCedula, cliEmail, cliTelefono);
                    mensaje = dao.add(cli);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "creado") ? "visible" : "sr-only");
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idCliente = Integer.parseInt(request.getParameter("idCliente"));
                    cliNombre = request.getParameter("cliNombre");
                    cliApellido = request.getParameter("cliApellido");
                    cliCedula = request.getParameter("cliCedula");
                    cliEmail = request.getParameter("cliEmail");
                    cliTelefono = request.getParameter("cliTelefono");
                    cli = new Cliente(idCliente, cliNombre, cliApellido, cliCedula, cliEmail, cliTelefono);
                    mensaje = dao.updateById(cli);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("divMsj", (mensaje == "actualizado") ? "visible" : "sr-only");
                    System.out.println(dao.updateById(cli));
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
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "crear":
                    acceso = CREAR;
                    break;
                case "editar":
                    request.setAttribute("idCliente", request.getParameter("idCliente"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idCliente = Integer.parseInt((String) request.getParameter("idCliente"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    mensaje = dao.deleteById(idCliente);
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
