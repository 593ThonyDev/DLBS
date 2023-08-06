package com.dlbs.controlador;

import com.dlbs.modelo.*;
import com.dlbs.dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Pedido extends HttpServlet {

    String LOGIN = "index.jsp";
    String REGISTROS = "vista/Ventas.jsp";

    //Objetos
    Detalle detalle = new Detalle();
    Venta venta = new Venta();
    Menu pro = new Menu();
    VentaDao ventaDao = new VentaDao();
    MenuDao prodDao = new MenuDao();
    int fkCliente, fkVenta, fkMenu, detCantidad;
    double venTotal, detTotal;
    String venCodigo, venFecha;

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
            // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();

            // Crear un formateador de fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Formatear la fecha y convertirla a una cadena
            String diaActual = currentDate.format(formatter);

            switch (action) {
                case "guardar":
                    //Se guarda el encabezado
                    fkCliente = Integer.parseInt(request.getParameter("fkCliente"));
                    venCodigo = request.getParameter("venCodigo");
                    venFecha = diaActual;
                    venTotal = Double.parseDouble(request.getParameter("venTotal"));

                    venta = new Venta(fkCliente, venCodigo, venFecha, venTotal);

                    fkVenta = Integer.parseInt(request.getParameter("fkVenta"));
                    fkMenu = Integer.parseInt(request.getParameter("fkMenu"));
                    detCantidad = Integer.parseInt(request.getParameter("detCantidad"));
                    detTotal = Double.parseDouble(request.getParameter("detTotal"));

                    detalle = new Detalle(fkVenta, fkMenu, detCantidad, detTotal);

                    // aqui el stock del menu a la bd.
                    pro = prodDao.getById(fkMenu);
                    int stock = pro.getMenStock();
                    int cantidadVendida = detCantidad;
                    int nuevoStock = stock - cantidadVendida;
                    pro.setMenStock(nuevoStock);
                    prodDao.updateById(pro);

                    // aqui enviamos a la bd los datos
                    ventaDao.saveSale(venta, detalle);
                     UUID uuid = UUID.randomUUID();
                    request.setAttribute("codVenta", uuid.toString());
                    request.setAttribute("idVenta", ventaDao.getLastId() + 1);
                    request.setAttribute("divMsj", "sr-only");
                    acceso = REGISTROS;
                    System.out.println("Venta guardada");
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }
}
