package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import model.Sabor;

@Controller
public class PrimerController 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/")
	public static String paginaPrincipal(Model template)
	{
		//template.addAttribute("claseHome", "active");
		return "home";
	}
	
	@GetMapping("/prueba")
	public static String paginaPrueba()
	{
		return "prueba";
	}
	

	@GetMapping("/enviar")
	public static String enviar() throws IOException
	{
		
		return "prueba";
	}

	@GetMapping("/sucursales")
	public static String paginaSucursales()
	{
		return "sucursales";
	}

	@GetMapping("/sabores")
	public static String paginaSabores(Model template) throws SQLException
	{
		Connection connection;
		connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
		
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM sabores;");
		
		ResultSet resultado = ps.executeQuery();
		
		ArrayList<Sabor> listaSabores;
		listaSabores = new ArrayList<Sabor>();
		
		while( resultado.next() ) {
			//template.addAttribute("nombreSabor", resultado.getString("nombre"));
			Sabor miSabor = new Sabor(	resultado.getInt("id"),
										resultado.getString("nombre"), 
										resultado.getString("tipo"),
										resultado.getString("descripcion"),
										resultado.getInt("stock"),
										resultado.getBoolean("apto_celiacos"));
			
			listaSabores.add( miSabor );
		}
		
		template.addAttribute("listaSabores", listaSabores);

		return "sabores";
	}
	
	@GetMapping("/nosotros")
	public static String paginaNosotros(Model template)
	{
		template.addAttribute("claseNosotros", "active");
		return "nosotros";
	}
	
	@GetMapping("/contacto")
	public static String PaginaContacto()
	{
		return "contacto"; // Formulario vacio
	}

	@PostMapping("/recibirContacto")
	public static String procesarInfoContacto(	@RequestParam String nombre, 
												@RequestParam String comentario,
												@RequestParam String email,
												Model template)
	{
		if (nombre.equals("") || comentario.equals("") || email.equals("")) { // si hubo algun error
			// Cargar formulario de vuelta
			template.addAttribute("mensajeError", "No puede haber campos vacios");
			template.addAttribute("nombreAnterior", nombre);
			template.addAttribute("emailAnterior", email);
			template.addAttribute("comentarioAnterior", comentario);

			return "contacto"; // Formulario vacio (quizas se enoje, pero bue)
		} else {
			enviarCorreo(
					"no-responder@pepito.com", 
					"francisco.j.laborda@gmail.com", 
					"Mensaje de contacto de " + nombre, 
					"nombre: " + nombre + "  email: " + email + " comentario: " + comentario);
			enviarCorreo(
					"no-responder@pepito.com",
					email,
					"Gracias por contactarte!", 
					"Recibimos tu conulta, nos vamos a contactar con vos");
			
			
			return "graciasContacto";			
		}	
	}
	
    public static void enviarCorreo(String de, String para, String asunto, String contenido){
        Email from = new Email(de);
        String subject = asunto;
        Email to = new Email(para);
        Content content = new Content("text/plain", contenido);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
          request.method = Method.POST;
          request.endpoint = "mail/send";
          request.body = mail.build();
          Response response = sg.api(request);
          System.out.println(response.statusCode);
          System.out.println(response.body);
          System.out.println(response.headers);
        } catch (IOException ex) {
          System.out.println(ex.getMessage()); ;
        }
    }
	
	
	
	
	

}
