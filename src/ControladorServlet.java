

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Formulario Alumno</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<table><tr><td>Nombre</td><td>Apellido</td><td>Curso</td></tr>");
		out.println("<tr><td><input type='text' id='nombre' placeholder='Nombre'></td>");
		out.println("<td><input type='text' id='apellido' placeholder='Apellido'></td>");
		out.println("<td> <select>\n" + 
				"  <option value='1DAM'>1DAM</option>\n" + 
				"  <option value='2DAM'>2DAM</option>\n" + 
				"</select> </td></tr>");
		out.println("</table>");
		
		out.println("<br>");
		out.println("</BODY></HTML>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
