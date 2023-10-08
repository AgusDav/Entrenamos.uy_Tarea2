package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetidoException;
import interfaces.Fabrica;
import interfaces.IControlador;

@WebServlet("/AgregarUsuarioServ")
public class AgregarUsuarioServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarUsuarioServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecNac");
		String tipo = request.getParameter("tipo");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date fecNacDate= null;
		try {
			fecNacDate = sdf.parse(fecha);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		LocalDate fecNacLocalDate = fecNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DtUsuario dtU = null;
		Fabrica fabrica = Fabrica.getInstancia();
		IControlador icon = fabrica.getIControlador();
		try {
			if(tipo.equals("profesor")) {
				String inst = null;
				String bio = request.getParameter("biografia");
				String desc = request.getParameter("descripcion");
				String institucion = request.getParameter("institucion");
				String web = request.getParameter("web");
				dtU = new DtProfesor(nickname, nombre, apellido, email, fecNacLocalDate, desc, bio, web);
				icon.agregarUsuario(dtU, inst);
			}else if(tipo.equals("socio")){
				dtU = new DtSocio(nickname, nombre, apellido, email, fecNacLocalDate);
				icon.agregarUsuario(dtU, "");
			}
		} catch (UsuarioRepetidoException e) {
			throw new RuntimeException(e);
		}
		RequestDispatcher rd;
		request.setAttribute("mensaje", "Se ha ingresado correctamente al socio " + nombre + " de nickname " + nickname + " en el sistema.");
		rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

}
