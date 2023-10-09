package servlets;

import datatypes.DtProfesor;
import datatypes.DtSocio;
import excepciones.UsuarioRepetidoException;
import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarUsuario extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    public AgregarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws /*ServletException,*/ IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecNac");
		String tipo = request.getParameter("tipoUsuario");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date fecNacDate;
		try {
			fecNacDate = sdf.parse(fecha);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Fabrica fabrica = Fabrica.getInstancia();
		IControlador icon = fabrica.getIControlador();
		try {
			if(tipo.equals("P")) {
				String bio = request.getParameter("biografia");
				String desc = request.getParameter("descripcion");
				String institucion = request.getParameter("institucion");
				String web = request.getParameter("web");
				DtProfesor dtP = new DtProfesor(nickname, nombre, apellido, email,password, fecNacDate, desc, bio, web);
				icon.agregarProfesor(dtP, institucion);
			}else if(tipo.equals("S")) {
				DtSocio dts = new DtSocio(nickname, nombre, apellido, email, password, fecNacDate);
				icon.agregarSocio(dts);
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
