

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Persona> persona = new ArrayList<Persona>();

    public ControladorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String curso = request.getParameter("curso");
		
		Persona p = new Persona(nombre,apellido,curso);
		
		persona.add(p);
		out.println("<table><tr><td>Nombre</td><td>Apellido</td><td>Curso</td></tr>");
		for(Persona pers : persona) {
			out.println("<tr><td>" + pers.getNombre() + "</td><td>" + pers.getApellido() +"</td><td>" + pers.getCurso() + "</td></tr>");
		}
		out.println("</table>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
