package servlets;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.InstitucionDeportivaRepetidaException;
import com.uy.Entrenamos.Fabrica;

import com.uy.Entrenamos.IControlador;

@WebServlet("/AgregarInstitucionServ")
public class AgregarInstitucionServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AgregarInstitucionServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String url = request.getParameter("url");
        Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();
        icon.altaInstitucion(nombre,descripcion,url);
        RequestDispatcher rd;
        request.setAttribute("mensaje", "Se ha ingresado correctamente la institucion deportiva " + nombre + " en el sistema.");
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}