import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ControladorFTP {

	public void crearDir(String direc) {
		// TODO Auto-generated constructor stub
		FTPClient cliente = new FTPClient();

		String servFTP = "192.168.2.35";

		System.out.println("Nos conectamos a: " + servFTP);
		String usuario = "prudensito";
		String clave = "1234";

		try {
			cliente.connect(servFTP);
			boolean login = cliente.login(usuario, clave);

			if (login) {
				System.out.println("Login correcto...");
			} else {

				System.out.println("Login incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
			cliente.changeWorkingDirectory("files");
			System.out.println("Directorio actual:" + cliente.printWorkingDirectory());

			FTPFile[] files = cliente.listFiles();
			System.out.println("Ficheros en el directorio actual:" + files.length);

			String tipos[] = { "Fichero", "Directorio", "Enlace simb." };

			for (int i = 0; i < files.length; i++) {
				System.out.println("\t" + files[i].getName() + "=>" + tipos[files[i].getType()]);
			}

			if (cliente.makeDirectory(direc)) {
				System.out.println("Directorio creado");
				cliente.changeWorkingDirectory(direc);
			} else {
				System.out.println("NO SE HA PODIDO CREAR DIRECTORIO");
			}

			boolean logout = cliente.logout();
			if (logout) {
				System.out.println("Logout del servidor FTP...");
			} else {
				System.out.println("Error al hacer Logout...");
			}

			cliente.disconnect();

			System.out.println("Conexi�n finalizada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ControladorFTP() {
		super();
	}

}
