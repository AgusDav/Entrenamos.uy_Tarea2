package src.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.UsuarioRepetidoException;
import interfaces.Fabrica;
import interfaces.IControlador;

@WebServlet("/AgregarUsuario")
public class AgregarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecNac");
		String tipo = request.getParameter("tipo");
		String institucion = null;
		if ("Profesor".equals(tipo)) {
			String bio = request.getParameter("bio");
			String desc = request.getParameter("desc");
			institucion = request.getParameter("institucion");
		}

		Fabrica fabrica = Fabrica.getInstancia();
		IControlador icon = fabrica.getIControlador();
		try {
			icon.agregarUsuario(icon.obtenerUsuario(nickname), institucion);
		} catch (UsuarioRepetidoException e) {
			throw new RuntimeException(e);
		}
		RequestDispatcher rd;
		request.setAttribute("mensaje", "Se ha ingresado correctamente al socio " + nombre + " de nickname " + nickname + " en el sistema.");
		rd = request.getRequestDispatcher("/notificacion.jsp");
		rd.forward(request, response);
	}

}
