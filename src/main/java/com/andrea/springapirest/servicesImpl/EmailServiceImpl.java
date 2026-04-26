package com.andrea.springapirest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendPasswordEmail(String to, String password) {

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Recuperación de contraseña");
	        message.setText(
	        	    "Hola,\n\n" +
	        	    "Este correo es autogenerado. No respondas al correo.\n"+
	        	    "Tu nueva contraseña es: " + password + "\n\n" +
	        	    "Te recomendamos cambiarla después de iniciar sesión.\n\n" +
	        	    "Si no has solicitado este cambio, ignora este correo."
	        	);

	        mailSender.send(message);
	    }
}
