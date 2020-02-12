
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArrayList<Persona> personas = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si lo queremos hacer a traves de un get, tenemos que poner el codigo del post en este método o colocar el código en una función y llamar a esa función.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		response.setContentType( "text/html; charset=UTF-8" );
		PrintWriter out = response.getWriter();

		// Obtengo los datos de la peticion
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String curso = request.getParameter("curso");

		// Compruebo que los campos del formulario tienen datos para añadir a la tabla
		if (!nombre.equals("") && !apellido.equals("") && !curso.equals("")) {
			// Creo el objeto persona y lo añado al arrayList
			Persona persona = new Persona(nombre, apellido, curso);
			personas.add(persona);
		}

		out.println("<table style= cellspacing='1' bgcolor='#0099cc'>");
		out.println("<tr>");
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'> NOMBRE </td>");			
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'> APELLIDOS </td>");		
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'> CURSO </td>");
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'> FTP </td>");
		out.println("</tr>");
		for(int i=0; i<personas.size(); i++){
			Persona persona = personas.get(i);
			out.println("<tr>");
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+persona.getNombre()+"</td>");			
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+persona.getApellido()+"</td>");	
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>"+persona.getCurso()+"</td>");
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + "<input type='button' onclick='addDir()' value='Crear directorio'/>" +"</td>");
			out.println("</tr>");
		}
		out.println("</table>");

	}
}
