import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class ControladorFTP {

	private String fecha;
	
	public void crearDir(String direc, String email) {
		// TODO Auto-generated constructor stub
		FTPClient cliente = new FTPClient();

		String servFTP = "files.000webhost.com";

		System.out.println("Nos conectamos a: " + servFTP);
		String usuario = "pspdam";
		String clave = "psp.2020";

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
			cliente.changeWorkingDirectory("/");
			System.out.println("Directorio actual:" + cliente.printWorkingDirectory());

			if (cliente.makeDirectory(direc)) {
				System.out.println("Directorio creado");
				cliente.changeWorkingDirectory(direc);
				enviarEmail2(email);
			} else {
				System.out.println("NO SE HA PODIDO CREAR DIRECTORIO");
			}
			
			FTPFile[] files = cliente.listFiles();
			System.out.println("Ficheros en el directorio actual:" + files.length);

			String tipos[] = { "Fichero", "Directorio", "Enlace simb." };

			for (int i = 0; i < files.length; i++) {
				System.out.println("\t" + files[i].getName() + "=>" + tipos[files[i].getType()]);
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

	private void enviarEmail2(String email) {
		
		String usuario = "psp2dam@gmail.com"; // en este ejemplo usamos una cuenta gmail
		String pwd = "psp.2020";
		int puerto = 587;
		String cuerpo = "Mensaje enviardo";
		BodyPart adjunto = new MimeBodyPart();
		MimeMultipart multiParte = new MimeMultipart();
		
		try {
	
			KeyPairGenerator k = KeyPairGenerator.getInstance("DSA");
			k.initialize(2048); //Esto pa la longitud de la clave
	
			//KeyPair para guardar las dos claves que genera el KeyPairGenerator
			KeyPair par = k.generateKeyPair();
	
			PrivateKey clavePrivada = par.getPrivate(); //Obtener la clave privada
			PublicKey clavePublica = par.getPublic(); //Obtener la clave pública
	
			//FIRMA DIGITAL
			Signature sign = Signature.getInstance("SHA256withDSA"); //Generamos la

	
			sign.initSign(clavePrivada); //Inicializamos el objeto para poder firmar
			byte[] bytes = cuerpo.getBytes(); //Obtenemos los bytes del cuerpo del mensaje para cifrarlo
	
			sign.update(bytes); //Actualiza los datos a ser firmados
	
			byte[] signature = sign.sign(); //Firmar
	
			cuerpo += "\n \nCuerpo firmado: " + signature.toString();
	
			//MANDAR MAIL
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.user", usuario);
			props.put("mail.smtp.clave", pwd);
	
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", puerto);
	
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
	
			message.setFrom(new InternetAddress(usuario));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	
			message.setSubject("HICH");
			message.setText(cuerpo);
			
			adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/lubuntu/Descargas/" + fecha + ".pdf")));
			adjunto.setFileName("file.pdf");
			multiParte.addBodyPart(adjunto);
			message.setContent(multiParte);
			
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", usuario, pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		}catch (Exception me) {
			me.printStackTrace();
		}
	}
	
	
	public void imprimirFactura() {
		OutputStream file = null;
		Document document = null;
		PdfWriter pdfWriter = null;
		PdfContentByte pdfContentByte = null;
		Timestamp fechaAhora = null;
		fecha = null;

		try {
			fechaAhora = new Timestamp(new Date().getTime());
			fecha = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(fechaAhora);

			file = new FileOutputStream(new File("/home/lubuntu/Descargas/" + fecha + ".pdf"));
			document = new Document();
			pdfWriter = PdfWriter.getInstance(document, file);

			document.open();

			pdfContentByte = pdfWriter.getDirectContent();

			PdfWriter.getInstance(document, file);

			document.open();

			Paragraph p;

			p = new Paragraph("Bienvenido",new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL));
			p.setAlignment(Element.ALIGN_LEFT);
			document.add((Element) p);

			document.close();
		} catch (FileNotFoundException | DocumentException ex) {
			System.err.println(ex.getMessage());
		}

	}
	
}
