
public class Persona {
	private String nombre;
	private String apellido;
	private String curso;
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Persona(String nombre, String apellido, String curso) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.curso = curso;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", curso=" + curso + "]";
	}
	
	
}
